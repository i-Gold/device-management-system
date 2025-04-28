package com.company.application.service;

import com.company.application.model.Customer;
import com.company.application.model.Device;
import com.company.application.model.Machine;
import com.company.application.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MachineService {

    private final MachineRepository machineRepository;
    private final CustomerService customerService;
    private final DeviceService deviceService;

    @Transactional
    public Machine create(Machine machine) {
        return machineRepository.save(machine);
    }

    @Transactional(readOnly = true)
    public List<Machine> findAll() {
        return machineRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Machine findById(Long id) {
        return machineRepository.findById(id).orElseThrow(() -> new RuntimeException("Machine not found"));
    }

    @Transactional
    public void delete(Long id) {
        machineRepository.deleteById(id);
    }

    @Transactional
    public Machine associateToCustomer(Long machineId, Long customerId) {
        Machine machine = findById(machineId);
        Customer customer = customerService.findById(customerId);
        machine.setCustomer(customer);
        return machineRepository.save(machine);
    }

    @Transactional
    public Machine associateDevice(Long machineId, Long deviceId) {
        Machine machine = findById(machineId);
        Device device = deviceService.findById(deviceId);
        machine.setDevice(device);
        return machineRepository.save(machine);
    }

    @Transactional
    public void removeAllLicenses(Long customerId) {
        List<Machine> machines = customerId == null ?
                machineRepository.findAll() :
                machineRepository.findByCustomerId(customerId);

        machines.forEach(machine -> machine.setLicense(null));
        machineRepository.saveAll(machines);
    }

    @Transactional
    public Machine assignLicense(Long machineId) {
        Machine machine = findById(machineId);
        machine.setLicense(UUID.randomUUID());
        return machineRepository.save(machine);
    }
}
