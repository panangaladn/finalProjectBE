package lk.ijse.CropMonitoring.entity;


import jakarta.persistence.*;
import lk.ijse.CropMonitoring.entity.association.CropLogDetailsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "crops")
@Entity
public class CropEntity {
    @Id
    @Column(name = "crop_code")
    private String cropCode;
    @Column(name = "crop_common_name")
    private String cropCommonName;
    @Column(name = "crop_scientific_name")
    private String cropScientificName;
    @Column(name = "crop_image", columnDefinition = "LONGTEXT")
    private String cropImage;
    @Column(name = "category")
    private String category;
    @Column(name = "crop_season")
    private String cropSeason;

    @ManyToOne
    @JoinColumn(name = "field_code", referencedColumnName = "field_code")
    private FieldEntity field;

    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CropLogDetailsEntity> cropLogDetailsList = new ArrayList<>();

}