package lk.ijse.CropMonitoring.util;

import lk.ijse.CropMonitoring.dto.impl.*;
import lk.ijse.CropMonitoring.entity.*;
import lk.ijse.CropMonitoring.entity.association.FieldStaffDetailsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    /*------------------------------------------------Field---------------------------------------------------*/

    public FieldDTO convertToFieldDTO(FieldEntity field) {
        if (field == null) {
            throw new IllegalArgumentException("FieldEntity is null");
        }

        // Map basic field properties
        FieldDTO fieldDTO = modelMapper.map(field, FieldDTO.class);

        // Handle crops associated with the field
        if (field.getCropList() != null && !field.getCropList().isEmpty()) {
            List<String> cropCodes = field.getCropList().stream()
                    .map(CropEntity::getCropCode) // Extract crop codes
                    .collect(Collectors.toList());
            fieldDTO.setCropCodes(cropCodes);
        } else {
            System.out.println("No crops found for this field.");
        }

        // Handle equipment associated with the field
        if (field.getEquipmentList() != null && !field.getEquipmentList().isEmpty()) {
            List<String> equipmentCodes = field.getEquipmentList().stream()
                    .map(EquipmentEntity::getEquipmentId) // Extract equipment IDs
                    .collect(Collectors.toList());
            fieldDTO.setEquipmentIds(equipmentCodes);
        }

        // Handle staff associated with the field
        if (field.getFieldStaffDetailsList() != null && !field.getFieldStaffDetailsList().isEmpty()) {
            List<String> staffIds = field.getFieldStaffDetailsList().stream()
                    .map(FieldStaffDetailsEntity::getStaff)
                    .map(StaffEntity::getStaffMemberId)
                    .collect(Collectors.toList());
            fieldDTO.setStaffMemberIds(staffIds);
        }
        return fieldDTO;
    }

    public FieldEntity convertToFieldEntity(FieldDTO dto) {
        return modelMapper.map(dto, FieldEntity.class);
    }

    public List<FieldDTO> convertToFieldDTOList(List<FieldEntity> fields) {
        return fields.stream()
                .map(this::convertToFieldDTO)
                .toList();
    }

    /*------------------------------------------------Crop---------------------------------------------------*/
    // CropEntity to CropDTO
    public CropDTO convertToCropDTO(CropEntity crop) {
        return modelMapper.map(crop, CropDTO.class);
    }

    // CropDTO to CropEntity
    public CropEntity convertToCropEntity(CropDTO dto) {
        return modelMapper.map(dto, CropEntity.class);
    }

    // List<CropEntity> to List<CropDTO>
    public List<CropDTO> convertToCropDTOList(List<CropEntity> cropList) {
        return cropList.stream()
                .map(this::convertToCropDTO)
                .toList();
    }


    /*------------------------------------------------Staff---------------------------------------------------*/
    // StaffEntity to StaffDTO
    public StaffDTO convertToStaffDTO(StaffEntity staff) {
        return modelMapper.map(staff, StaffDTO.class);
    }

    // StaffDTO to StaffEntity
    public StaffEntity convertToStaffEntity(StaffDTO dto) {
        return modelMapper.map(dto, StaffEntity.class);
    }

    // List<StaffEntity> to List<StaffDTO>
    public List<StaffDTO> convertToStaffDTOList(List<StaffEntity> staffList) {
        return staffList.stream()
                .map(this::convertToStaffDTO)
                .toList();
    }

   /*------------------------------------------------Vehicle---------------------------------------------------*/
    // VehicleEntity to VehicleDTO
    public VehicleDTO convertToVehicleDTO(VehicleEntity vehicle) {
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    // VehicleDTO to VehicleEntity
    public VehicleEntity convertToVehicleEntity(VehicleDTO dto) {
        return modelMapper.map(dto, VehicleEntity.class);
    }

    // List<VehicleEntity> to List<VehicleDTO>
    public List<VehicleDTO> convertToVehicleDTOList(List<VehicleEntity> vehicleList) {
        return vehicleList.stream()
                .map(this::convertToVehicleDTO)
                .toList();
    }

    /*------------------------------------------------MonitoringLog---------------------------------------------------*/
    // MonitoringLogEntity to MonitoringLogDTO
    public MonitoringLogDTO convertToMonitoringLogDTO(MonitoringLogEntity log) {
        return modelMapper.map(log, MonitoringLogDTO.class);
    }

    // MonitoringLogDTO to MonitoringLogEntity
    public MonitoringLogEntity convertToMonitoringLogEntity(MonitoringLogDTO dto) {
        return modelMapper.map(dto, MonitoringLogEntity.class);
    }

    // List<MonitoringLogEntity> to List<MonitoringLogDTO>
    public List<MonitoringLogDTO> convertToMonitoringLogDTOList(List<MonitoringLogEntity> logList) {
        return logList.stream()
                .map(this::convertToMonitoringLogDTO)
                .toList();
    }


    /*------------------------------------------------Equipment---------------------------------------------------*/


    public EquipmentDTO convertToEquipmentDTO(EquipmentEntity equipment) {
        return modelMapper.map(equipment, EquipmentDTO.class);
    }

    public EquipmentEntity convertToEquipmentEntity(EquipmentDTO dto) {
        return modelMapper.map(dto, EquipmentEntity.class);
    }

    public List<EquipmentDTO> convertToEquipmentEntityDTOList(List<EquipmentEntity> equipmentList) {
        return equipmentList.stream()
                .map(this::convertToEquipmentDTO)
                .toList();
    }
}