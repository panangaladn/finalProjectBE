package lk.ijse.CropMonitoring.repository;

import lk.ijse.CropMonitoring.entity.MonitoringLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MonitoringLogEntity, String> {
}
