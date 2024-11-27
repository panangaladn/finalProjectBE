package lk.ijse.CropMonitoring.entity.association;

import jakarta.persistence.*;
import lk.ijse.CropMonitoring.embedded.CropLogDetailsPK;
import lk.ijse.CropMonitoring.entity.CropEntity;
import lk.ijse.CropMonitoring.entity.MonitoringLogEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "crop_log_details")
public class CropLogDetailsEntity {
    @EmbeddedId
    private CropLogDetailsPK cropLogDetailsPK;

    @ManyToOne
    @MapsId("cropCode")
    @JoinColumn(name = "crop_code")
    private CropEntity crop;

    @ManyToOne
    @MapsId("logCode")
    @JoinColumn(name = "log_code")
    private MonitoringLogEntity log;
}
