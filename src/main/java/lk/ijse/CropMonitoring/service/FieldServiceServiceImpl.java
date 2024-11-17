package lk.ijse.CropMonitoring.service;

import jakarta.transaction.Transactional;
import lk.ijse.CropMonitoring.customObj.FieldErrorResponse;
import lk.ijse.CropMonitoring.customObj.FieldResponse;
import lk.ijse.CropMonitoring.dao.FieldDao;
import lk.ijse.CropMonitoring.dto.impl.FieldDTO;
import lk.ijse.CropMonitoring.entity.FieldEntity;
import lk.ijse.CropMonitoring.exception.DataPersistFailedException;
import lk.ijse.CropMonitoring.exception.FieldNotFoundException;
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
public class FieldServiceServiceImpl implements FieldService{

    private final FieldDao fieldDao;

    @Autowired
    private final Mapping mapping;

    //Save Field
    @Override
    public void saveField(FieldDTO fieldDTO) {
        List<String> fieldCode = fieldDao.findLastFieldCode();
        String lastFieldCode = fieldCode.isEmpty() ? null : fieldCode.get(0);
        fieldDTO.setFieldCode(AppUtil.generateNextFieldId(lastFieldCode));

        FieldEntity isSaveField = fieldDao.save(mapping.convertToFieldEntity(fieldDTO));

        if (isSaveField == null) {
            throw new DataPersistFailedException("Cannot save data");
        }

        System.out.println("Saving FieldEntity:"+isSaveField.getFieldCode()+isSaveField.getFieldName());
    }

    @Override
    public void updateField(FieldDTO updateField) {
        Optional<FieldEntity> tmpField = fieldDao.findById(updateField.getFieldCode());
        if (!tmpField.isPresent()) {
            throw new FieldNotFoundException("Field not found");
        }else {
            tmpField.get().setFieldName(updateField.getFieldName());
            tmpField.get().setFieldLocation(updateField.getFieldLocation());
            tmpField.get().setFieldSize(updateField.getFieldSize());
            tmpField.get().setFieldImage1(updateField.getFieldImage1());
            tmpField.get().setFieldImage2(updateField.getFieldImage2());
            tmpField.get().setFieldCode(updateField.getFieldCode());

//            fieldDao.save(fieldEntity);
        }
    }

    //Field Delete
    @Override
    public void deleteField(String fieldCode) {
        Optional<FieldEntity> selectedFieldId = fieldDao.findById(fieldCode);
        if (!selectedFieldId.isPresent()) {
            throw new FieldNotFoundException("Field not found");
        }else {
            fieldDao.deleteById(fieldCode);
        }
    }

    //Field Get
    @Override
    public FieldResponse getSelectField(String fieldCode) {
        if (fieldDao.existsById(fieldCode)) {
            FieldEntity fieldEntityByFieldCode = fieldDao.getFieldEntityByFieldCode(fieldCode);
            return mapping.convertToFieldDTO(fieldEntityByFieldCode);
        }else {
            return new FieldErrorResponse(0,"Field not found");
        }
    }

    //Field GetAll
    @Override
    public List<FieldDTO> getAllFields() {
        List<FieldEntity> getAllFields = fieldDao.findAll();
        return mapping.convertToFieldDTOList(getAllFields);
    }



    //Custom
//    @Override
//    public FieldEntity existByField(String fieldCode) {
//        FieldEntity byFieldCode = fieldDao.findByFieldCode(fieldCode);
//        if (byFieldCode == null) {
//            throw new FieldNotFoundException("Field not found");
//        }
////        return mapping.convertToFieldDTO(byFieldCode);
//        return byFieldCode;
//
//    }
}