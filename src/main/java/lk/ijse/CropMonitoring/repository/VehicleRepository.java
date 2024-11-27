package lk.ijse.CropMonitoring.repository;

import lk.ijse.CropMonitoring.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, String> {

    @Query("SELECT i.vehicleCode FROM VehicleEntity i ORDER BY i.vehicleCode DESC")
    List<String> findLastVehicleId();
}
