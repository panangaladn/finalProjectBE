package lk.ijse.CropMonitoring.dto.Impl;

import lk.ijse.CropMonitoring.customObj.VehicleResponse;
import lk.ijse.CropMonitoring.dto.SuperDTO;
import lk.ijse.CropMonitoring.entity.StaffEntity;
import lk.ijse.CropMonitoring.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDTO implements SuperDTO, VehicleResponse {
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private Status status;
    private String remarks;
//    private StaffDTO staff;
    private String staffMemberId;
}
