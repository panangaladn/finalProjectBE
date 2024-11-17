package lk.ijse.CropMonitoring.service;

import jakarta.transaction.Transactional;
import lk.ijse.CropMonitoring.customObj.FieldErrorResponse;
import lk.ijse.CropMonitoring.customObj.MonitoringLogErrorResponse;
import lk.ijse.CropMonitoring.customObj.MonitoringLogResponse;
import lk.ijse.CropMonitoring.dao.MonitoringLogDao;
import lk.ijse.CropMonitoring.dto.impl.MonitoringLogDTO;
import lk.ijse.CropMonitoring.entity.CropEntity;
import lk.ijse.CropMonitoring.entity.FieldEntity;
import lk.ijse.CropMonitoring.entity.MonitoringLogEntity;
import lk.ijse.CropMonitoring.exception.CropNotFoundException;
import lk.ijse.CropMonitoring.exception.DataPersistFailedException;
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
public class MonitoringLogServiceImpl implements MonitoringLogService {

    private final MonitoringLogDao monitoringLogDao;

    @Autowired
    private final Mapping mapping;



    @Override
    public void saveMonitoringLog(MonitoringLogDTO monitoringLogDTO) {
        try {
            List<String> monitoringLogCode = monitoringLogDao.findLastMonitoringLogCode();
            String lastMonitoringLogCode = monitoringLogCode.isEmpty() ? null : monitoringLogCode.get(0);
            monitoringLogDTO.setLogCode(AppUtil.generateMonitoringLogId(lastMonitoringLogCode));

            MonitoringLogEntity logEntity = mapping.convertToMonitoringLogEntity(monitoringLogDTO);
            monitoringLogDao.save(logEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unexpected error occurred while saving monitoring log", e);
        }
    }


    @Override
    public void updateMonitoringLog(MonitoringLogDTO updateMonitoringLogDTO) {
        Optional<MonitoringLogEntity> tmpMonitoringLog = monitoringLogDao.findById(updateMonitoringLogDTO.getLogCode());
        if (!tmpMonitoringLog.isPresent()) {
            throw new CropNotFoundException("Monitoring with code " + updateMonitoringLogDTO.getLogCode() + " not found");
        }

        MonitoringLogEntity monitoringLogEntity = tmpMonitoringLog.get();
        monitoringLogEntity.setLogObservation(updateMonitoringLogDTO.getLogObservation());
        monitoringLogEntity.setObservedImage(updateMonitoringLogDTO.getObservedImage());

        monitoringLogDao.save(monitoringLogEntity);
    }

    @Override
    public void deleteMonitoringLog(String logCode) {
        Optional<MonitoringLogEntity> selectedMonitoringLogCode = monitoringLogDao.findById(logCode);
        if (!selectedMonitoringLogCode.isPresent()) {
            throw new CropNotFoundException("Monitoring with code " + logCode + " not found");
        }else {
            monitoringLogDao.deleteById(logCode);
        }

    }

    @Override
    public MonitoringLogResponse getSelectMonitoringLog(String logCode) {
        if (monitoringLogDao.existsById(logCode)) {
            MonitoringLogEntity monitoringLogEntityByLogCode = monitoringLogDao.getMonitoringLogEntityByLogCode(logCode);
            return mapping.convertToMonitoringLogDTO(monitoringLogEntityByLogCode);
        }else {
            return new MonitoringLogErrorResponse(0,"MonitoringLog not found");
        }
    }

    @Override
    public List<MonitoringLogDTO> getAllMonitoringLogs() {
        List<MonitoringLogEntity> getAllMonitoringLogs = monitoringLogDao.findAll();
        return mapping.convertToMonitoringLogDTOList(getAllMonitoringLogs);
    }


}