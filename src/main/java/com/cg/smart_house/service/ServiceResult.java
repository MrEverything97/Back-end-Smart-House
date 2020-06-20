package com.cg.smart_house.service;

import lombok.Data;

@Data
public class ServiceResult {
    private ServiceStatus status = ServiceStatus.SUCCESS;
    private String message;
    private Object data;
}
