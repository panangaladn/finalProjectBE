package lk.ijse.CropMonitoring.dto.impl;


import lk.ijse.CropMonitoring.customObj.CropResponse;
import lk.ijse.CropMonitoring.dto.SuperDTO;
import lk.ijse.CropMonitoring.entity.FieldEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CropDTO implements SuperDTO, CropResponse {
    private String cropCode;
    private String cropCommonName;
    private String cropScientificName;
    private String cropImage;
    private String category;
    private String cropSeason;
    private FieldEntity field;
}