package lk.ijse.CropMonitoring.dao;

import lk.ijse.CropMonitoring.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffDao extends JpaRepository<StaffEntity, String> {



    @Query("SELECT i.staffMemberId FROM StaffEntity i ORDER BY i.staffMemberId DESC")
    List<String> findLastStaffMemberId();

    StaffEntity findByStaffMemberId(String staffMemberId);
}
