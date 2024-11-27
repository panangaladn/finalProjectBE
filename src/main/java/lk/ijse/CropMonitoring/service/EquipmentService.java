package lk.ijse.CropMonitoring.service;

import lk.ijse.CropMonitoring.customObj.EquipmentResponse;
import lk.ijse.CropMonitoring.dto.impl.EquipmentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);

    ResponseEntity<String> updateEquipment(String equipmentId, EquipmentDTO equipmentDTO);

    void deleteEquipment(String equipmentId);

    EquipmentResponse getSelectEquipment(String equipmentId);

    List<EquipmentDTO> getAllEquipment();
}
