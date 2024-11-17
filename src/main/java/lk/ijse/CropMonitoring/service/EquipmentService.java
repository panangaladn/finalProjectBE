package lk.ijse.CropMonitoring.service;

import lk.ijse.CropMonitoring.customObj.EquipmentResponse;
import lk.ijse.CropMonitoring.dto.impl.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);

    void deleteEquipment(String equipmentId);


    void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO);

    EquipmentResponse getSelectEquipment(String equipmentId);

    List<EquipmentDTO> getAllEquipment();
}
