package com.company.application.service;

import com.company.application.model.Device;
import com.company.application.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DeviceServiceTest {

    @MockBean
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceService deviceService;

    private Device device;

    @BeforeEach
    void setUp() {
        device = new Device();
        device.setName("Device 1");
    }

    @Test
    void testCreateDevice() {
        when(deviceRepository.save(any(Device.class))).thenReturn(device);

        Device savedDevice = deviceService.create(device);

        assertNotNull(savedDevice);
        assertEquals("Device 1", savedDevice.getName());
    }

    @Test
    void testFindDeviceById() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));

        Device foundDevice = deviceService.findById(1L);

        assertNotNull(foundDevice);
        assertEquals("Device 1", foundDevice.getName());
    }
}
