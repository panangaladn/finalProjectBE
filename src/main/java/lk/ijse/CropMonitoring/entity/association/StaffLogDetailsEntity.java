package lk.ijse.CropMonitoring.entity.association;

import jakarta.persistence.*;
import lk.ijse.CropMonitoring.embedded.FieldStaffDetailsPK;
import lk.ijse.CropMonitoring.embedded.StaffLogDetailsPK;
import lk.ijse.CropMonitoring.entity.FieldEntity;
import lk.ijse.CropMonitoring.entity.MonitoringLogEntity;
import lk.ijse.CropMonitoring.entity.StaffEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff_log_details")
public class StaffLogDetailsEntity {

    @EmbeddedId
    private StaffLogDetailsPK staffLogDetailsPK;

    @ManyToOne
    @MapsId("staffMemberId")
    @JoinColumn(name = "staff_member_id")
    private StaffEntity staff;

    @ManyToOne
    @MapsId("logCode")
    @JoinColumn(name = "log_code")
    private MonitoringLogEntity log;
}