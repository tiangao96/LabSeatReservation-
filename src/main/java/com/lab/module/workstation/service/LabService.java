package com.lab.module.workstation.service;

import com.lab.common.api.ResultCode;
import com.lab.common.entity.BaseEntity;
import com.lab.common.exception.BizException;
import com.lab.common.security.DeptResolver;
import com.lab.common.security.DeptTreeNode;
import com.lab.module.workstation.dto.LabCreateRequest;
import com.lab.module.workstation.dto.LabLayoutRequest;
import com.lab.module.workstation.dto.LabVo;
import com.lab.module.workstation.dto.WorkstationLayoutItem;
import com.lab.module.workstation.entity.Lab;
import com.lab.module.workstation.entity.Workstation;
import com.lab.module.workstation.repository.EquipmentRepository;
import com.lab.module.workstation.repository.LabRepository;
import com.lab.module.workstation.repository.WorkstationRepository;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LabService {
    private final LabRepository labRepository;
    private final WorkstationRepository workstationRepository;
    private final EquipmentRepository equipmentRepository;
    private final DeptResolver deptResolver;

    public List<DeptTreeNode> deptTree(int maxLevels) {
        return this.deptResolver.deptTree(maxLevels);
    }

    public List<LabVo> list() {
        return this.labRepository.findAll(Sort.by(Sort.Direction.ASC, (String[])new String[]{"sort"})).stream().map(this::toVo).toList();
    }

    public LabVo getByNanoId(String nanoId) {
        return this.toVo(this.requireLab(nanoId));
    }

    @Transactional
    public String create(LabCreateRequest req) {
        if (req.getCode() != null && !req.getCode().isBlank() && this.labRepository.findByCode(req.getCode()) != null) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5b9e\u9a8c\u5ba4\u7f16\u7801\u5df2\u5b58\u5728");
        }
        Lab lab = new Lab();
        this.applyToEntity(lab, req);
        lab = (Lab)this.labRepository.save(lab);
        return lab.getNanoId();
    }

    @Transactional
    public void update(String nanoId, LabCreateRequest req) {
        Lab other;
        Lab lab = this.requireLab(nanoId);
        if (req.getCode() != null && !req.getCode().isBlank() && (other = this.labRepository.findByCode(req.getCode())) != null && !other.getId().equals(lab.getId())) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5b9e\u9a8c\u5ba4\u7f16\u7801\u5df2\u5b58\u5728");
        }
        this.applyToEntity(lab, req);
        this.labRepository.save(lab);
    }

    @Transactional
    public void delete(String nanoId) {
        Lab lab = this.requireLab(nanoId);
        if (this.workstationRepository.existsByLabId(lab.getId())) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5b9e\u9a8c\u5ba4\u4e0b\u5b58\u5728\u5de5\u4f4d\uff0c\u8bf7\u5148\u5220\u9664");
        }
        if (this.equipmentRepository.existsByLabId(lab.getId())) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5b9e\u9a8c\u5ba4\u4e0b\u5b58\u5728\u8bbe\u5907\uff0c\u8bf7\u5148\u5220\u9664");
        }
        lab.setIsDeleted(Integer.valueOf(1));
        this.labRepository.save(lab);
    }

    @Transactional
    public void updateLayout(String labNanoId, LabLayoutRequest req) {
        Lab lab = this.requireLab(labNanoId);
        List<Workstation> wss = this.workstationRepository.findByLabId(lab.getId());
        Map<String, Workstation> byNano = wss.stream().collect(Collectors.toMap(BaseEntity::getNanoId, Function.identity(), (a, b) -> a));
        for (WorkstationLayoutItem item : req.getItems()) {
            if (byNano.containsKey(item.getWorkstationNanoId())) continue;
            throw new BizException(ResultCode.BAD_REQUEST, "\u5de5\u4f4d\u4e0d\u5c5e\u4e8e\u8be5\u5b9e\u9a8c\u5ba4: " + item.getWorkstationNanoId());
        }
        Map<String, WorkstationLayoutItem> itemMap = req.getItems().stream().collect(Collectors.toMap(WorkstationLayoutItem::getWorkstationNanoId, Function.identity(), (a, b) -> a));
        for (Workstation ws : wss) {
            WorkstationLayoutItem item = (WorkstationLayoutItem)itemMap.get(ws.getNanoId());
            if (item != null) {
                ws.setPosX(item.getPosX());
                ws.setPosY(item.getPosY());
                ws.setWidth(item.getWidth());
                ws.setHeight(item.getHeight());
                ws.setZIndex(item.getZIndex());
                ws.setRotation(item.getRotation());
            } else {
                ws.setPosX(null);
                ws.setPosY(null);
                ws.setWidth(null);
                ws.setHeight(null);
                ws.setZIndex(null);
                ws.setRotation(null);
            }
            this.workstationRepository.save(ws);
        }
    }

    private void applyToEntity(Lab lab, LabCreateRequest req) {
        lab.setName(req.getName());
        lab.setCode(req.getCode());
        lab.setLocation(req.getLocation());
        lab.setCapacity(req.getCapacity());
        if (req.getStatus() != null && !req.getStatus().isBlank()) {
            lab.setStatus(req.getStatus());
        }
        lab.setManagerId(req.getManagerId());
        lab.setMentorGroupId(req.getMentorGroupId());
        lab.setDeptId(this.resolveDeptId(req.getDeptNanoId()));
        lab.setDescription(req.getDescription());
        lab.setArea(req.getArea());
        lab.setCanvasWidth(req.getCanvasWidth());
        lab.setCanvasHeight(req.getCanvasHeight());
    }

    private Long resolveDeptId(String deptNanoId) {
        if (deptNanoId == null || deptNanoId.isBlank()) {
            return null;
        }
        Long deptId = this.deptResolver.resolveDeptId(deptNanoId);
        if (deptId == null) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u90e8\u95e8\u4e0d\u5b58\u5728: " + deptNanoId);
        }
        return deptId;
    }

    private Lab requireLab(String nanoId) {
        Lab lab = this.labRepository.findByNanoId(nanoId);
        if (lab == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND);
        }
        return lab;
    }

    private LabVo toVo(Lab lab) {
        LabVo vo = new LabVo();
        vo.setNanoId(lab.getNanoId());
        vo.setName(lab.getName());
        vo.setCode(lab.getCode());
        vo.setLocation(lab.getLocation());
        vo.setCapacity(lab.getCapacity());
        vo.setStatus(lab.getStatus());
        vo.setDescription(lab.getDescription());
        vo.setDeptNanoId(this.deptResolver.resolveNanoId(lab.getDeptId()));
        vo.setDeptName(this.deptResolver.resolveName(lab.getDeptId()));
        vo.setArea(lab.getArea());
        vo.setCanvasWidth(lab.getCanvasWidth());
        vo.setCanvasHeight(lab.getCanvasHeight());
        return vo;
    }

    public LabService(LabRepository labRepository, WorkstationRepository workstationRepository, EquipmentRepository equipmentRepository, DeptResolver deptResolver) {
        this.labRepository = labRepository;
        this.workstationRepository = workstationRepository;
        this.equipmentRepository = equipmentRepository;
        this.deptResolver = deptResolver;
    }
}

