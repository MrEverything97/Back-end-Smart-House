package com.cg.smart_house.service;

import com.cg.smart_house.enumm.ServiceStatus;
import lombok.Data;

@Data
public class ServiceResult {
    private ServiceStatus status = ServiceStatus.SUCCESS;
    private String message;
    private Object data;
}
