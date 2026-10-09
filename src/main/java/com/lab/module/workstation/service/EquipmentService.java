package com.lab.module.workstation.service;

import com.lab.common.api.ResultCode;
import com.lab.common.exception.BizException;
import com.lab.module.workstation.dto.EquipmentCreateRequest;
import com.lab.module.workstation.dto.EquipmentVo;
import com.lab.module.workstation.entity.Equipment;
import com.lab.module.workstation.entity.Lab;
import com.lab.module.workstation.repository.EquipmentRepository;
import com.lab.module.workstation.repository.LabRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final LabRepository labRepository;

    public List<EquipmentVo> listByLab(String labNanoId) {
        Lab lab = this.requireLab(labNanoId);
        return this.equipmentRepository.findByLabId(lab.getId()).stream().map(e -> this.toVo(e, lab.getNanoId())).toList();
    }

    public EquipmentVo getByNanoId(String nanoId) {
        Equipment eq = this.equipmentRepository.findByNanoId(nanoId);
        if (eq == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND);
        }
        Lab lab = this.labRepository.findById(eq.getLabId()).orElse(null);
        return this.toVo(eq, lab != null ? lab.getNanoId() : null);
    }

    @Transactional
    public String create(EquipmentCreateRequest req) {
        Lab lab = this.requireLab(req.getLabNanoId());
        Equipment eq = new Equipment();
        this.applyToEntity(eq, req, lab.getId());
        eq = (Equipment)this.equipmentRepository.save(eq);
        return eq.getNanoId();
    }

    @Transactional
    public void update(String nanoId, EquipmentCreateRequest req) {
        Equipment eq = this.equipmentRepository.findByNanoId(nanoId);
        if (eq == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND);
        }
        Lab lab = this.requireLab(req.getLabNanoId());
        this.applyToEntity(eq, req, lab.getId());
        this.equipmentRepository.save(eq);
    }

    @Transactional
    public void delete(String nanoId) {
        Equipment eq = this.equipmentRepository.findByNanoId(nanoId);
        if (eq == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND);
        }
        eq.setIsDeleted(Integer.valueOf(1));
        this.equipmentRepository.save(eq);
    }

    private void applyToEntity(Equipment eq, EquipmentCreateRequest req, Long labId) {
        eq.setName(req.getName());
        eq.setCode(req.getCode());
        eq.setLabId(labId);
        eq.setCategory(req.getCategory());
        eq.setModel(req.getModel());
        eq.setSerialNumber(req.getSerialNumber());
        if (req.getStatus() != null && !req.getStatus().isBlank()) {
            eq.setStatus(req.getStatus());
        }
        eq.setPurchasedAt(req.getPurchasedAt());
        eq.setDescription(req.getDescription());
    }

    private Lab requireLab(String nanoId) {
        Lab lab = this.labRepository.findByNanoId(nanoId);
        if (lab == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND, "\u5b9e\u9a8c\u5ba4\u4e0d\u5b58\u5728");
        }
        return lab;
    }

    private EquipmentVo toVo(Equipment eq, String labNanoId) {
        EquipmentVo vo = new EquipmentVo();
        vo.setNanoId(eq.getNanoId());
        vo.setName(eq.getName());
        vo.setCode(eq.getCode());
        vo.setLabNanoId(labNanoId);
        vo.setCategory(eq.getCategory());
        vo.setModel(eq.getModel());
        vo.setSerialNumber(eq.getSerialNumber());
        vo.setStatus(eq.getStatus());
        vo.setPurchasedAt(eq.getPurchasedAt());
        vo.setDescription(eq.getDescription());
        return vo;
    }

    public EquipmentService(EquipmentRepository equipmentRepository, LabRepository labRepository) {
        this.equipmentRepository = equipmentRepository;
        this.labRepository = labRepository;
    }
}

