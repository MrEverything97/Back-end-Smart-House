package com.cg.smart_house.service.impl;
import com.cg.smart_house.model.Host;
import com.cg.smart_house.model.Picture;
import com.cg.smart_house.repository.HostRepository;
import com.cg.smart_house.service.HostService;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostServiceImpl implements HostService {
    @Autowired
    private HostRepository hostRepository;

    @Override
    public ServiceResult createHost(Host host) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(hostRepository.save(host));
        return serviceResult;
    }

    @Override
    public ServiceResult findAll() {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(hostRepository.findAll());
        return serviceResult;
    }

    @Override
    public ServiceResult findById(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Host host = hostRepository.findById(id).orElse(null);
        if (host == null) {
            serviceResult.setMessage("Host Not Found");
        }
        serviceResult.setData(host);
        return serviceResult;
    }

    @Override
    public ServiceResult deleteHost(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Host host = hostRepository.findById(id).orElse(null);
        if (host == null) {
            serviceResult.setStatus(ServiceStatus.FAILED);
            serviceResult.setMessage("Host Not Found");
        } else {
            hostRepository.delete(host);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult updateHost(Host host) {
        ServiceResult serviceResult = new ServiceResult();
        if (! hostRepository.findById(host.getId()).isPresent()) {
            serviceResult.setMessage("Host not found");
        } else {
            serviceResult.setData(hostRepository.save(host));
        }
        return serviceResult;
    }
}
