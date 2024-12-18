package lk.ijse.CropMonitoring.repository;

import lk.ijse.CropMonitoring.entity.MonitoringLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitoringLogRepository extends JpaRepository<MonitoringLogEntity, String> {

    @Query("SELECT i.logCode FROM MonitoringLogEntity i ORDER BY i.logCode DESC")
    List<String> findLastMonitoringLogCode();

    MonitoringLogEntity getMonitoringLogEntityByLogCode(String logCode);
}
