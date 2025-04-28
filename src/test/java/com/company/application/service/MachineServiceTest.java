package com.company.application.service;

import com.company.application.model.Machine;
import com.company.application.repository.MachineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MachineServiceTest {

    @MockBean
    private MachineRepository machineRepository;

    @Autowired
    private MachineService machineService;

    private Machine machine;

    @BeforeEach
    void setUp() {
        machine = new Machine();
        machine.setName("Machine_1");
        machine.setLicense(UUID.randomUUID());
    }

    @Test
    void testCreateMachine() {
        when(machineRepository.save(any(Machine.class))).thenReturn(machine);

        Machine savedMachine = machineService.create(machine);

        assertNotNull(savedMachine);
        assertEquals("Machine_1", savedMachine.getName());
    }

    @Test
    void testFindMachineById() {
        when(machineRepository.findById(1L)).thenReturn(Optional.of(machine));

        Machine foundMachine = machineService.findById(1L);

        assertNotNull(foundMachine);
        assertEquals("Machine_1", foundMachine.getName());
    }

    @Test
    void testRemoveMachineLicense() {
        when(machineRepository.findByCustomerId(anyLong())).thenReturn(Collections.singletonList(machine));
        when(machineRepository.save(any(Machine.class))).thenReturn(machine);

        machineService.removeAllLicenses(1L);

        assertNull(machine.getLicense());
    }
}
