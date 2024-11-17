package lk.ijse.CropMonitoring.dao;

import lk.ijse.CropMonitoring.entity.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldDao extends JpaRepository<FieldEntity, String> {

    @Query("SELECT i.fieldCode FROM FieldEntity i ORDER BY i.fieldCode DESC")
    List<String> findLastFieldCode();

    FieldEntity getFieldEntityByFieldCode(String fieldCode);

    FieldEntity findByFieldCode(String fieldCode);
}