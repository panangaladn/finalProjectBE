package lk.ijse.CropMonitoring.service;

import jakarta.transaction.Transactional;
import lk.ijse.CropMonitoring.customObj.StaffErrorResponse;
import lk.ijse.CropMonitoring.customObj.StaffResponse;
import lk.ijse.CropMonitoring.dao.StaffDao;
import lk.ijse.CropMonitoring.dto.impl.StaffDTO;
import lk.ijse.CropMonitoring.entity.StaffEntity;
import lk.ijse.CropMonitoring.exception.DataPersistFailedException;
import lk.ijse.CropMonitoring.exception.StaffNotFoundException;
import lk.ijse.CropMonitoring.util.AppUtil;
import lk.ijse.CropMonitoring.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService{

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private Mapping mapping;


    @Override
    public void saveStaff(StaffDTO staffDTO) {
        List<String> staffIds = staffDao.findLastStaffMemberId();
        String lastStaffId = staffIds.isEmpty() ? null : staffIds.get(0);
        staffDTO.setStaffMemberId(AppUtil.generateNextStaffId(lastStaffId));

        var staffEntity = mapping.convertToStaffEntity(staffDTO);
        var isSaveStaff = staffDao.save(staffEntity);
        if (isSaveStaff == null) {
            throw new DataPersistFailedException("Can't save staff");
        }
    }

    @Override
    public void updateStaff(String staffMemberId, StaffDTO staff) {
        Optional<StaffEntity> tmpStaffEntity = staffDao.findById(staffMemberId);
        if(!tmpStaffEntity.isPresent()) {
            throw new StaffNotFoundException("Staff not found");
        }else {
            StaffEntity staffEntity = tmpStaffEntity.get();

            staffEntity.setFirstName(staff.getFirstName());
            staffEntity.setLastName(staff.getLastName());
            staffEntity.setDesignation(staff.getDesignation());
            staffEntity.setGender(staff.getGender());
            staffEntity.setJoinedDate(staff.getJoinedDate());
            staffEntity.setDOB(staff.getDOB());
            staffEntity.setAddressLine1(staff.getAddressLine1());
            staffEntity.setAddressLine2(staff.getAddressLine2());
            staffEntity.setAddressLine3(staff.getAddressLine3());
            staffEntity.setAddressLine4(staff.getAddressLine4());
            staffEntity.setAddressLine5(staff.getAddressLine5());
            staffEntity.setContactNo(staff.getContactNo());
            staffEntity.setEmail(staff.getEmail());
            staffEntity.setRole(staff.getRole());

            staffDao.save(staffEntity);
        }
    }

    @Override
    public void deleteStaff(String staffMemberId) {
        Optional<StaffEntity> findMemberId = staffDao.findById(staffMemberId);
        if(!findMemberId.isPresent()) {
            throw new StaffNotFoundException("Staff not found");
        }else {
            staffDao.deleteById(staffMemberId);
        }

    }

    @Override
    public StaffResponse getSelectStaff(String staffMemberId) {
        if (staffDao.existsById(staffMemberId)) {
            return mapping.convertToStaffDTO(staffDao.getReferenceById(staffMemberId));
        }else {
            return new StaffErrorResponse(0,"Staff not save");
        }
    }

    @Override
    public List<StaffDTO> getAllStaffs() {
        return mapping.convertToStaffDTOList(staffDao.findAll());
    }

    //custom
    @Override
    public StaffDTO existByStaffMember(String staffMemberId) {
        StaffEntity byStaffMemberId = staffDao.findByStaffMemberId(staffMemberId);
        return mapping.convertToStaffDTO(byStaffMemberId);
    }
}