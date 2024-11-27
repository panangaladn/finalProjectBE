package lk.ijse.CropMonitoring.service;

import jakarta.transaction.Transactional;
import lk.ijse.CropMonitoring.customObj.EquipmentErrorResponse;
import lk.ijse.CropMonitoring.customObj.EquipmentResponse;
import lk.ijse.CropMonitoring.dto.impl.EquipmentDTO;
import lk.ijse.CropMonitoring.repository.EquipmentRepository;
import lk.ijse.CropMonitoring.repository.FieldRepository;
import lk.ijse.CropMonitoring.repository.StaffRepository;
import lk.ijse.CropMonitoring.entity.EquipmentEntity;
import lk.ijse.CropMonitoring.entity.FieldEntity;
import lk.ijse.CropMonitoring.entity.StaffEntity;
import lk.ijse.CropMonitoring.exception.DataPersistFailedException;
import lk.ijse.CropMonitoring.exception.EquipmentNotFoundException;
import lk.ijse.CropMonitoring.util.AppUtil;
import lk.ijse.CropMonitoring.util.Mapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private final Mapping mapping;

    private final EquipmentRepository equipmentDao;

    private final FieldRepository fieldDao;

    private final StaffRepository staffDao;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        try {
            List<String> equipmentId = equipmentDao.findLastEquipmentId();
            String lastEquipmentId = equipmentId.isEmpty() ? null : equipmentId.get(0);
            equipmentDTO.setEquipmentId(AppUtil.generateNextEquipmentId(lastEquipmentId));

            //this equipment table exist
            if (equipmentDao.existsByStaff_StaffMemberId(equipmentDTO.getStaffMemberId())) {
                throw new DataPersistFailedException("Staff member is already assigned to another equipment.");
            }

            FieldEntity field = fieldDao.findByFieldCode(equipmentDTO.getFieldCode());
            if (field == null) {
                throw new DataPersistFailedException("Field not found");
            }

            StaffEntity staff = staffDao.findByStaffMemberId(equipmentDTO.getStaffMemberId());
            if (staff == null) {
                throw new DataPersistFailedException("Staff not found");
            }

            EquipmentEntity savedEquipment = equipmentDao.save(mapping.convertToEquipmentEntity(equipmentDTO));
            if (savedEquipment == null) {
                throw new DataPersistFailedException("Unable to save equipment");
            }

        } catch (DataPersistFailedException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred while saving equipment", e);
        }
    }

    @Override
    public ResponseEntity<String> updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        try {
            // Step 1: Check if the equipment with the given ID exists
            Optional<EquipmentEntity> tmpEquipmentEntity = equipmentDao.findById(equipmentId);
            if (!tmpEquipmentEntity.isPresent()) {
                throw new EquipmentNotFoundException("Equipment not found");
            }

            // Get the existing equipment entity
            EquipmentEntity existingEquipment = tmpEquipmentEntity.get();

            // Step 2: Get the staff member by staffMemberId
            StaffEntity staff = staffDao.findByStaffMemberId(equipmentDTO.getStaffMemberId());
            if (staff == null) {
                throw new DataPersistFailedException("Staff not found");
            }

            // Step 3: Ensure that the field exists in the database
            FieldEntity field = fieldDao.findByFieldCode(equipmentDTO.getFieldCode());
            if (field == null) {
                throw new DataPersistFailedException("Field not found");
            }

            // Step 4: Check if the staff is assigned to another equipment, but not the current one
            Optional<EquipmentEntity> staffAssignedEquipment = equipmentDao.findByStaff_StaffMemberIdAndEquipmentIdNot(
                    equipmentDTO.getStaffMemberId(), equipmentId);

            // Debugging log to check what equipment the staff is assigned to
            if (staffAssignedEquipment.isPresent()) {
                System.out.println("Staff member " + equipmentDTO.getStaffMemberId() +
                        " is already assigned to another equipment: " +
                        staffAssignedEquipment.get().getEquipmentId());
                throw new DataPersistFailedException("Staff member is already assigned to another equipment");
            }

            // Step 5: Proceed with updating the equipment if all conditions are met
            existingEquipment.setName(equipmentDTO.getName());
            existingEquipment.setEquipmentType(equipmentDTO.getEquipmentType());
            existingEquipment.setStatus(equipmentDTO.getStatus());
            existingEquipment.setField(field);
            existingEquipment.setStaff(staff);

            // Step 6: Save the updated equipment
            equipmentDao.save(existingEquipment);

            // Return success message
            return new ResponseEntity<>("Equipment updated successfully", HttpStatus.OK);
        } catch (DataPersistFailedException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred while updating equipment", e);
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        Optional<EquipmentEntity> findId = equipmentDao.findById(equipmentId);
        if (!findId.isPresent()) {
            throw new EquipmentNotFoundException("Customer not found");
        } else {
            equipmentDao.deleteById(equipmentId);
        }
    }

    @Override
    public EquipmentResponse getSelectEquipment(String equipmentId) {
        if (equipmentDao.existsById(equipmentId)) {
//           EquipmentEntity equipment  = equipmentDao.getEquipmentEntityByEquipmentId(equipmentId);
            return mapping.convertToEquipmentDTO(equipmentDao.getReferenceById(equipmentId));
//           return mapping.convertToEquipmentDTO(equipment);
        }else {
            return new EquipmentErrorResponse(0,"Equipment not found");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        List<EquipmentEntity> getAllEquipment = equipmentDao.findAll();
        return mapping.convertToEquipmentEntityDTOList(getAllEquipment);
    }
}