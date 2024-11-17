package lk.ijse.CropMonitoring.dao;

import lk.ijse.CropMonitoring.entity.MonitoringLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<MonitoringLogEntity, String> {
}
