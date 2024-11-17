package lk.ijse.CropMonitoring.service;

import lk.ijse.CropMonitoring.customObj.MonitoringLogResponse;
import lk.ijse.CropMonitoring.dto.impl.MonitoringLogDTO;

import java.util.List;

public interface MonitoringLogService {
    void saveMonitoringLog(MonitoringLogDTO monitoringLogDTO);

    void updateMonitoringLog(MonitoringLogDTO updateMonitoringLogDTO);

    void deleteMonitoringLog(String logCode);

    MonitoringLogResponse getSelectMonitoringLog(String logCode);

    List<MonitoringLogDTO> getAllMonitoringLogs();
}
