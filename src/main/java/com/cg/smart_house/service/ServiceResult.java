package com.cg.smart_house.service;
import lombok.Data;

@Data
public class ServiceResult {
    private ServiceStatus status = ServiceStatus.SUCCESS;
    private String message;
    private Object data;

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
