package com.lab.module.workstation.service;

import com.lab.common.api.ResultCode;
import com.lab.common.exception.BizException;
import com.lab.module.workstation.dto.UsageManualRecordRequest;
import com.lab.module.workstation.dto.UsageManualRecordVo;
import com.lab.module.workstation.entity.UsageManualRecord;
import com.lab.module.workstation.repository.UsageManualRecordRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsageManualRecordService {
    private final UsageManualRecordRepository repository;

    @Transactional
    public UsageManualRecordVo create(UsageManualRecordRequest req) {
        this.validate(req);
        UsageManualRecord r = new UsageManualRecord();
        r.setTeacherName(req.getTeacherName());
        r.setLabCode(req.getLabCode().trim());
        r.setQuantity(req.getQuantity() == null ? Integer.valueOf(1) : req.getQuantity());
        r.setStartMonth(req.getStartMonth());
        r.setEndMonth(req.getEndMonth());
        r.setArea(req.getArea());
        r.setRemark(req.getRemark());
        this.repository.save(r);
        return this.toVo(r);
    }

    public List<UsageManualRecordVo> list() {
        return this.repository.findAllByIsDeleted(Integer.valueOf(0)).stream().map(this::toVo).toList();
    }

    @Transactional
    public UsageManualRecordVo update(String nanoId, UsageManualRecordRequest req) {
        UsageManualRecord r = this.requireRecord(nanoId);
        this.validate(req);
        r.setTeacherName(req.getTeacherName());
        r.setLabCode(req.getLabCode().trim());
        r.setQuantity(req.getQuantity() == null ? Integer.valueOf(1) : req.getQuantity());
        r.setStartMonth(req.getStartMonth());
        r.setEndMonth(req.getEndMonth());
        r.setArea(req.getArea());
        r.setRemark(req.getRemark());
        this.repository.save(r);
        return this.toVo(r);
    }

    @Transactional
    public void delete(String nanoId) {
        UsageManualRecord r = this.requireRecord(nanoId);
        r.setIsDeleted(Integer.valueOf(1));
        this.repository.save(r);
    }

    private void validate(UsageManualRecordRequest req) {
        if (req.getLabCode() == null || req.getLabCode().isBlank()) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u623f\u95f4\u53f7\u4e0d\u80fd\u4e3a\u7a7a");
        }
        RentalCalculator.parseMonth(req.getStartMonth());
        RentalCalculator.parseMonth(req.getEndMonth());
        if (req.getStartMonth().compareTo(req.getEndMonth()) > 0) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u7ed3\u675f\u6708\u4e0d\u80fd\u65e9\u4e8e\u8d77\u59cb\u6708");
        }
        if (req.getQuantity() != null && req.getQuantity().intValue() < 1) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u6570\u91cf\u4e0d\u80fd\u5c0f\u4e8e1");
        }
        if (req.getArea() != null && req.getArea().signum() < 0) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u9762\u79ef\u4e0d\u80fd\u4e3a\u8d1f\u6570");
        }
    }

    private UsageManualRecord requireRecord(String nanoId) {
        if (nanoId == null || nanoId.isBlank()) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u8bb0\u5f55\u7f16\u53f7\u4e0d\u80fd\u4e3a\u7a7a");
        }
        UsageManualRecord r = this.repository.findByNanoId(nanoId);
        if (r == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND, "\u624b\u52a8\u8865\u5145\u8bb0\u5f55\u4e0d\u5b58\u5728");
        }
        return r;
    }

    private UsageManualRecordVo toVo(UsageManualRecord r) {
        UsageManualRecordVo vo = new UsageManualRecordVo();
        vo.setNanoId(r.getNanoId());
        vo.setTeacherName(r.getTeacherName());
        vo.setLabCode(r.getLabCode());
        vo.setQuantity(r.getQuantity());
        vo.setStartMonth(r.getStartMonth());
        vo.setEndMonth(r.getEndMonth());
        vo.setArea(r.getArea());
        vo.setRemark(r.getRemark());
        vo.setSysCreateDatetime(r.getSysCreateDatetime());
        return vo;
    }
    public UsageManualRecordService(UsageManualRecordRepository repository) {
        this.repository = repository;
    }
}
