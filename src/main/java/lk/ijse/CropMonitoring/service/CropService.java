package lk.ijse.CropMonitoring.service;

import lk.ijse.CropMonitoring.customObj.CropResponse;
import lk.ijse.CropMonitoring.dto.impl.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO buildCropDTO);

    void updateCrop(CropDTO updateCrop,String cropCode);

    void deleteCrop(String cropCode);

    CropResponse getSelectCrop(String cropCode);

    List<CropDTO> getAllCrops();
}
