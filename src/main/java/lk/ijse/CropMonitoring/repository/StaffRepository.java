package lk.ijse.CropMonitoring.repository;

import lk.ijse.CropMonitoring.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, String> {

    @Query("SELECT i.staffMemberId FROM StaffEntity i ORDER BY i.staffMemberId DESC")
    List<String> findLastStaffMemberId();

    StaffEntity findByStaffMemberId(String staffMemberId);


    List<StaffEntity> findByStaffMemberIdIn(List<String> staffMemberIds);
}
