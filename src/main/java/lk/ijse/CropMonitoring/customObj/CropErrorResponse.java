package lk.ijse.CropMonitoring.customObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CropErrorResponse implements CropResponse,Serializable {
    private int errorCode;
    public String errorMessage;
}
