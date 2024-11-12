package lk.ijse.CropMonitoring.entity;


import jakarta.persistence.*;
import lk.ijse.CropMonitoring.entity.association.FieldLogDetailsEntity;
import lk.ijse.CropMonitoring.entity.association.FieldStaffDetailsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "fields")
@Entity
public class FieldEntity {
    @Id
    @Column(name = "field_code")
    private String fieldCode;
    @Column(name = "field_name")
    private String fieldName;
    @Column(name = "field_location")
    private Point fieldLocation;
    @Column(name = "field_size")
    private double fieldSize;
    @Column(name = "image_1", columnDefinition = "LONGTEXT")
    private String image1;
    @Column(name = "image_2", columnDefinition = "LONGTEXT")
    private String image2;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CropEntity> cropList = new ArrayList<>();

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EquipmentEntity> equipmentList = new ArrayList<>();

    //Associate

    // Associate with FieldStaffDetailsEntity
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FieldStaffDetailsEntity> fieldStaffDetailsList = new ArrayList<>();


    // Associate with FieldLogDetailsEntity
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FieldLogDetailsEntity> fieldLogDetailsList = new ArrayList<>();

}