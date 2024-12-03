package lk.ijse.CropMonitoring.repository;

import lk.ijse.CropMonitoring.embedded.FieldLogDetailsPK;
import lk.ijse.CropMonitoring.entity.association.FieldLogDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldLogDetailsRepository extends JpaRepository<FieldLogDetailsEntity, FieldLogDetailsPK> {
}
