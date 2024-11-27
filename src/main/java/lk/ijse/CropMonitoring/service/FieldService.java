package lk.ijse.CropMonitoring.service;

import lk.ijse.CropMonitoring.customObj.FieldResponse;
import lk.ijse.CropMonitoring.dto.impl.FieldDTO;


import java.util.List;

public interface FieldService {

    void saveField(FieldDTO buildFieldDTO);

    void updateField(FieldDTO updateField);

    void deleteField(String fieldCode);

    FieldResponse getSelectField(String fieldCode);

    List<FieldDTO> getAllFields();
}
