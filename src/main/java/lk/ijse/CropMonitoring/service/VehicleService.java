package lk.ijse.CropMonitoring.service;

import lk.ijse.CropMonitoring.customObj.VehicleResponse;
import lk.ijse.CropMonitoring.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);


    void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO);

    void deleteVehicle(String vehicleCode);

    VehicleResponse getSelectVehicle(String vehicleCode);

    List<VehicleDTO> getAllStaffs();
}
