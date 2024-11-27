package lk.ijse.CropMonitoring.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum EquipmentTypes {
    MECHANICAL,
    ELECTRICAL,
    HYDRAULIC;


    @JsonCreator
    public static EquipmentTypes fromValue(String value) {
        return EquipmentTypes.valueOf(value.toUpperCase());
    }

//
//    @JsonCreator
//    public static EquipmentTypes fromString(String value) {
//        try {
//            return EquipmentTypes.valueOf(value.toUpperCase());
//        } catch (IllegalArgumentException e) {
//            return null; // or throw custom exception if needed
//        }
//    }
}
