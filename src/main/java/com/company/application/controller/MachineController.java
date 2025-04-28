package com.company.application.controller;

import com.company.application.model.Machine;
import com.company.application.service.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/machines")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MachineController {

    private final MachineService machineService;

    @PostMapping
    public Machine create(@RequestBody Machine machine) {
        return machineService.create(machine);
    }

    @GetMapping
    public List<Machine> findAll() {
        return machineService.findAll();
    }

    @GetMapping("/{id}")
    public Machine findById(@PathVariable Long id) {
        return machineService.findById(id);
    }

    @PostMapping("/{machineId}/assign-license")
    public Machine assignLicense(@PathVariable Long machineId) {
        return machineService.assignLicense(machineId);
    }

    @PostMapping("/{machineId}/associate-customer/{customerId}")
    public Machine associateToCustomer(@PathVariable Long machineId, @PathVariable Long customerId) {
        return machineService.associateToCustomer(machineId, customerId);
    }

    @PostMapping("/{machineId}/associate-device/{deviceId}")
    public Machine associateDevice(@PathVariable Long machineId, @PathVariable Long deviceId) {
        return machineService.associateDevice(machineId, deviceId);
    }

    @PostMapping("/removeAllLicences")
    public void removeAllLicenses(@RequestParam(required = false) Long customerId) {
        machineService.removeAllLicenses(customerId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        machineService.delete(id);
    }
}
