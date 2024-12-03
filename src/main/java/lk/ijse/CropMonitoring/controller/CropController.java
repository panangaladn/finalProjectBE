package lk.ijse.CropMonitoring.controller;


import lk.ijse.CropMonitoring.customObj.CropResponse;
import lk.ijse.CropMonitoring.dto.impl.CropDTO;
import lk.ijse.CropMonitoring.exception.CropNotFoundException;
import lk.ijse.CropMonitoring.exception.DataPersistFailedException;
import lk.ijse.CropMonitoring.service.CropService;
import lk.ijse.CropMonitoring.util.AppUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crops")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CropController {
    @Autowired
    private final CropService cropService;

    @GetMapping("/health")
    public String healthCheck(){
        return "Crop is running";
    }

    /**To Do CRUD Operation**/

    //Save Crop
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveCrop(
            @RequestParam("cropCommonName") String cropCommonName,
            @RequestParam("cropScientificName") String cropScientificName,
            @RequestParam("cropImage") MultipartFile cropImage,
            @RequestParam("category") String category,
            @RequestParam("cropSeason") String cropSeason,
            @RequestParam("fieldCode") String fieldCode) {

        // Input Validation
        if (cropCommonName == null || cropCommonName.isEmpty()) {
            return new ResponseEntity<>("Crop common name is required", HttpStatus.BAD_REQUEST);
        }
        if (cropScientificName == null || cropScientificName.isEmpty()) {
            return new ResponseEntity<>("Crop scientific name is required", HttpStatus.BAD_REQUEST);
        }
        if (fieldCode == null || fieldCode.isEmpty()) {
            return new ResponseEntity<>("Field code is required", HttpStatus.BAD_REQUEST);
        }
        if (cropImage == null || cropImage.isEmpty()) {
            return new ResponseEntity<>("Crop image is required", HttpStatus.BAD_REQUEST);
        }
        if (!cropImage.getContentType().matches("image/(jpeg|png|jpg)")) {
            return new ResponseEntity<>("Only JPEG or PNG images are allowed", HttpStatus.BAD_REQUEST);
        }

        try {
            // Base64 Encode Image
            byte[] imageBytes = cropImage.getBytes();
            String base64Image = AppUtil.toBase64ProfilePic(imageBytes);

            // Build DTO
            CropDTO buildCropDTO = new CropDTO();
            buildCropDTO.setCropCommonName(cropCommonName);
            buildCropDTO.setCropScientificName(cropScientificName);
            buildCropDTO.setCropImage(base64Image);
            buildCropDTO.setCategory(category);
            buildCropDTO.setCropSeason(cropSeason);
            buildCropDTO.setFieldCode(fieldCode);

            // Save Crop
            cropService.saveCrop(buildCropDTO);
            return new ResponseEntity<>("Crop saved successfully", HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>("Crop not saved: Field not found for the provided fieldCode", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Update Crop
    @PatchMapping(value = "/{cropCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateCrop(
            @PathVariable("cropCode") String cropCode,
            @RequestParam("cropCommonName") String cropCommonName,
            @RequestParam("cropScientificName") String cropScientificName,
            @RequestParam("cropImage") MultipartFile cropImage,
            @RequestParam("category") String category,
            @RequestParam("cropSeason") String cropSeason,
            @RequestParam("fieldCode") String fieldCode) {

        try {
            byte[] imageByteCollection1 = cropImage.getBytes();
            String base64ProfilePic1 = AppUtil.toBase64ProfilePic(imageByteCollection1);

            CropDTO updateCrop = new CropDTO();
            updateCrop.setCropCommonName(cropCommonName);
            updateCrop.setCropScientificName(cropScientificName);
            updateCrop.setCropImage(base64ProfilePic1);
            updateCrop.setCategory(category);
            updateCrop.setCropSeason(cropSeason);
            updateCrop.setFieldCode(fieldCode);

            cropService.updateCrop(updateCrop, cropCode);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCrop(@PathVariable("id") String cropCode) {
        try {
            cropService.deleteCrop(cropCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get Crop
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CropResponse getSelectedCrop(@PathVariable("id") String cropCode){
        return cropService.getSelectCrop(cropCode);
    }

    //Get All Crop
    @GetMapping(value = "allCrops", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCrops() {
        return cropService.getAllCrops();
    }
}