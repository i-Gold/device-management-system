package com.company.application.service;

import com.company.application.model.Device;
import com.company.application.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public Device create(Device device) {
        return deviceRepository.save(device);
    }

    @Transactional(readOnly = true)
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Device findById(Long id) {
        return deviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Device not found"));
    }

    public void delete(Long id) {
        deviceRepository.deleteById(id);
    }
}
