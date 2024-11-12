package lk.ijse.CropMonitoring.entity;

import jakarta.persistence.*;
import lk.ijse.CropMonitoring.entity.association.CropLogDetailsEntity;
import lk.ijse.CropMonitoring.entity.association.FieldLogDetailsEntity;
import lk.ijse.CropMonitoring.entity.association.StaffLogDetailsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "monitoring_log")
public class MonitoringLogEntity {
    @Id
    @Column(name = "log_code")
    private String logCode;
    private Date logDate;
    private String logObservation;
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;


    //Associate

    @OneToMany(mappedBy = "log", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StaffLogDetailsEntity> staffLogDetailsList = new ArrayList<>();


    @OneToMany(mappedBy = "log", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FieldLogDetailsEntity> fieldLogDetailsList = new ArrayList<>();


    @OneToMany(mappedBy = "log", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CropLogDetailsEntity> cropLogDetailsList = new ArrayList<>();

}