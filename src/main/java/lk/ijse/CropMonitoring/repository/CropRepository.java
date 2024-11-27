package lk.ijse.CropMonitoring.repository;

import lk.ijse.CropMonitoring.entity.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CropRepository extends JpaRepository<CropEntity, String> {

    @Query("SELECT i.cropCode FROM CropEntity i ORDER BY i.cropCode DESC")
    List<String> findLastCropCode();

    CropEntity getCropEntityByCropCode(String cropCode);
}
