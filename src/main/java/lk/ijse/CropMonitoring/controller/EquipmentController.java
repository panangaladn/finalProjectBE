package lk.ijse.CropMonitoring.controller;

import lk.ijse.CropMonitoring.customObj.EquipmentResponse;
import lk.ijse.CropMonitoring.dao.FieldDao;
import lk.ijse.CropMonitoring.dao.StaffDao;
import lk.ijse.CropMonitoring.dto.impl.EquipmentDTO;
import lk.ijse.CropMonitoring.entity.FieldEntity;
import lk.ijse.CropMonitoring.entity.StaffEntity;
import lk.ijse.CropMonitoring.exception.DataPersistFailedException;
import lk.ijse.CropMonitoring.exception.EquipmentNotFoundException;
import lk.ijse.CropMonitoring.service.EquipmentService;
import lk.ijse.CropMonitoring.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/equipments")
@RequiredArgsConstructor
public class EquipmentController {

    @Autowired
    private final EquipmentService equipmentService;

    private final FieldDao fieldDao;

    private final StaffDao staffDao;

    private final FieldService fieldService;

    @GetMapping("/health")
    public String healthCheck() {
        return "Equipment is running";
    }


    //Save Equipment
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveEquipment(@RequestBody EquipmentDTO equipmentDTO){
        try {
            // Proceed with business logic
            FieldEntity field = fieldDao.findByFieldCode(equipmentDTO.getField().getFieldCode());
            if (field == null) {
                return new ResponseEntity<>("Field not found", HttpStatus.NOT_FOUND);
            }
            equipmentDTO.setField(field);

            StaffEntity staff = staffDao.findByStaffMemberId(equipmentDTO.getStaff().getStaffMemberId());
            if (staff == null) {
                return new ResponseEntity<>("Staff not found", HttpStatus.NOT_FOUND);
            }
            equipmentDTO.setStaff(staff);

            // Call the service to save the equipment
            equipmentService.saveEquipment(equipmentDTO);
            return new ResponseEntity<>("Equipment saved successfully", HttpStatus.CREATED);

        } catch (DataPersistFailedException e) {
            // Return the specific exception message for debugging
            return new ResponseEntity<>("Staff member already exist: " , HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            return new ResponseEntity<>("Internal server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Update Equipment
    @PatchMapping(value = "/{equipmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateEquipment(@PathVariable("equipmentId") String equipmentId, @RequestBody EquipmentDTO equipmentDTO) {

        try {
            // Proceed with business logic
            FieldEntity field = fieldDao.findByFieldCode(equipmentDTO.getField().getFieldCode());
            if (field == null) {
                return new ResponseEntity<>("Field not found", HttpStatus.NOT_FOUND);
            }
            equipmentDTO.setField(field);


            StaffEntity staff = staffDao.findByStaffMemberId(equipmentDTO.getStaff().getStaffMemberId());
            if (staff == null) {
                return new ResponseEntity<>("Staff not found", HttpStatus.NOT_FOUND);
            }
            equipmentDTO.setStaff(staff);


            // Call the service to save the equipment
            equipmentService.updateEquipment(equipmentId,equipmentDTO);
            return new ResponseEntity<>("Equipment saved successfully", HttpStatus.CREATED);

        } catch (DataPersistFailedException e) {
            // Return the specific exception message for debugging
            return new ResponseEntity<>("Staff member already exist: " , HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            return new ResponseEntity<>("Internal server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete Equipment
    @DeleteMapping(value = "/{equipmentId}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("equipmentId") String equipmentId){
        try {
            equipmentService.deleteEquipment(equipmentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EquipmentNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Get Equipment
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentResponse getSelectEquipment(@PathVariable("id") String equipmentId){
        return equipmentService.getSelectEquipment(equipmentId);
    }

    //Get All Customers
    @GetMapping(value = "allEquipments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDTO> getAllEquipments(){
        return equipmentService.getAllEquipment();
    }



    //Get-All Equipment
}