package lk.ijse.CropMonitoring.service;

import jakarta.transaction.Transactional;
import lk.ijse.CropMonitoring.customObj.StaffErrorResponse;
import lk.ijse.CropMonitoring.customObj.VehicleErrorResponse;
import lk.ijse.CropMonitoring.customObj.VehicleResponse;
import lk.ijse.CropMonitoring.dao.StaffDao;
import lk.ijse.CropMonitoring.dao.VehicleDao;
import lk.ijse.CropMonitoring.dto.impl.StaffDTO;
import lk.ijse.CropMonitoring.dto.impl.VehicleDTO;
import lk.ijse.CropMonitoring.entity.StaffEntity;
import lk.ijse.CropMonitoring.entity.VehicleEntity;
import lk.ijse.CropMonitoring.exception.DataPersistFailedException;
import lk.ijse.CropMonitoring.exception.StaffNotFoundException;
import lk.ijse.CropMonitoring.util.AppUtil;
import lk.ijse.CropMonitoring.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private final Mapping mapping;

    private final VehicleDao vehicleDao;

    private final StaffDao staffDao;


    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {

        try{
            List<String> vehicleCode = vehicleDao.findLastVehicleId();
            String lastVehicleId = vehicleCode.isEmpty() ? null : vehicleCode.get(0);
            vehicleDTO.setVehicleCode(AppUtil.generateVehicleId(lastVehicleId));

            VehicleEntity isSaveVehicle = vehicleDao.save(mapping.convertToVehicleEntity(vehicleDTO));
            if (isSaveVehicle == null) {
                throw new DataPersistFailedException("Unable to save vehicle");
            }

        } catch (DataPersistFailedException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred while saving vehicle", e);
        }

    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) {
        Optional<VehicleEntity> tmpStaffEntity = vehicleDao.findById(vehicleCode);
        if(!tmpStaffEntity.isPresent()) {
            throw new StaffNotFoundException("Staff not found");
        }else {

            StaffEntity byStaffMemberId = staffDao.findByStaffMemberId(vehicleDTO.getStaffMemberId());
            if (byStaffMemberId == null) {
//                return new ResponseEntity<>("Staff not found", HttpStatus.NOT_FOUND);
            }



            VehicleEntity vehicleEntity = tmpStaffEntity.get();


            vehicleEntity.setVehicleCategory(vehicleDTO.getVehicleCategory());
            vehicleEntity.setFuelType(vehicleDTO.getFuelType());
            vehicleEntity.setFuelType(vehicleDTO.getFuelType());
            vehicleEntity.setStatus(vehicleDTO.getStatus());
            vehicleEntity.setStaff(byStaffMemberId);
            vehicleDao.save(vehicleEntity);

        }
    }

    @Override
    public void deleteVehicle(String vehicleCode) {
        Optional<VehicleEntity> findVehicleId = vehicleDao.findById(vehicleCode);
        if(!findVehicleId.isPresent()) {
            throw new StaffNotFoundException("Vehicle not found");
        }else {
            vehicleDao.deleteById(vehicleCode);
        }
    }

    @Override
    public VehicleResponse getSelectVehicle(String vehicleCode) {
        if (vehicleDao.existsById(vehicleCode)) {
            return mapping.convertToVehicleDTO(vehicleDao.getReferenceById(vehicleCode));
        }else {
            return new VehicleErrorResponse(0,"Vehicle not save");
        }
    }

    @Override
    public List<VehicleDTO> getAllStaffs() {
        return mapping.convertToVehicleDTOList(vehicleDao.findAll());
    }
}