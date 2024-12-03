package lk.ijse.CropMonitoring.repository;

import lk.ijse.CropMonitoring.embedded.CropLogDetailsPK;
import lk.ijse.CropMonitoring.entity.association.CropLogDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropLogDetailsRepository extends JpaRepository<CropLogDetailsEntity, CropLogDetailsPK> {
}
