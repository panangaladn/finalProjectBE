package lk.ijse.CropMonitoring.dto.impl;


import com.fasterxml.jackson.annotation.JsonFormat;
import lk.ijse.CropMonitoring.customObj.StaffResponse;
import lk.ijse.CropMonitoring.dto.SuperDTO;
import lk.ijse.CropMonitoring.entity.enums.Gender;
import lk.ijse.CropMonitoring.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO implements SuperDTO, StaffResponse {
    private String staffMemberId;
    private String firstName;
    private String lastName;
    private String designation;
    private Gender gender;
    private Date joinedDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date DOB;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNo;
    private String email;
    private Role role;
}