package lk.ijse.CropMonitoring.dto.Impl;


import lk.ijse.CropMonitoring.customObj.EquipmentResponse;
import lk.ijse.CropMonitoring.dto.SuperDTO;
import lk.ijse.CropMonitoring.entity.FieldEntity;
import lk.ijse.CropMonitoring.entity.StaffEntity;
import lk.ijse.CropMonitoring.entity.enums.EquipmentTypes;
import lk.ijse.CropMonitoring.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDTO implements SuperDTO, EquipmentResponse {
    private String equipmentId;
    private String name;
    private EquipmentTypes equipmentType;
    private Status status;
    private FieldEntity field;
    private StaffEntity staff;
}