package lk.ijse.CropMonitoring.service;

import lk.ijse.CropMonitoring.customObj.StaffResponse;
import lk.ijse.CropMonitoring.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);

    void updateStaff(String staffMemberId, StaffDTO staff);

    void deleteStaff(String staffMemberId);
//
    StaffResponse getSelectStaff(String staffMemberId);
//
    List<StaffDTO> getAllStaffs();
//
//    StaffDTO existByStaffMember(String staffMemberId);
}