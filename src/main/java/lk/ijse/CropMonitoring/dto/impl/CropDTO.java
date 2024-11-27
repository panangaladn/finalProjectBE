package lk.ijse.CropMonitoring.dto.impl;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lk.ijse.CropMonitoring.customObj.CropResponse;
import lk.ijse.CropMonitoring.dto.SuperDTO;
import lk.ijse.CropMonitoring.entity.association.CropLogDetailsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CropDTO implements SuperDTO, CropResponse {
    @NotNull(message = "Crop code cannot be null")
    @Size(min = 1, max = 50, message = "Crop code must be between 1 and 50 characters")
    private String cropCode;

    @NotNull(message = "Common name cannot be null")
    @Size(min = 1, max = 100, message = "Common name must be between 1 and 100 characters")
//    @CapitalizeFirstLetter(message = "Common name must start with a capital letter")
    private String cropCommonName;

    @NotNull(message = "Scientific name cannot be null")
    @Size(min = 1, max = 100, message = "Scientific name must be between 1 and 100 characters")
//    @CapitalizeFirstLetter(message = "Scientific name must start with a capital letter")
    private String cropScientificName;

    @Pattern(regexp = "^.+\\.(jpg|jpeg|png)$", message = "Crop image must be a valid image file (.jpg, .jpeg, .png)")
    private String cropImage;

    @NotNull(message = "Category cannot be null")
    @Size(min = 1, max = 50, message = "Category must be between 1 and 50 characters")
    private String category;

    @NotNull(message = "Season cannot be null")
    @Size(min = 1, max = 50, message = "Season must be between 1 and 50 characters")
    private String cropSeason;

    @NotNull(message = "Field code cannot be null")
    private String fieldCode;

    //Associate
    private List<CropLogDetailsEntity> cropLogDetailsList = new ArrayList<>();
}