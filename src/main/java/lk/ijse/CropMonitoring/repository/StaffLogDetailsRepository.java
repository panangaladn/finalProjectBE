package lk.ijse.CropMonitoring.repository;

import lk.ijse.CropMonitoring.embedded.StaffLogDetailsPK;
import lk.ijse.CropMonitoring.entity.association.StaffLogDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffLogDetailsRepository extends JpaRepository<StaffLogDetailsEntity, StaffLogDetailsPK> {
}
