package com.company.application;

import com.company.application.model.Customer;
import com.company.application.model.Device;
import com.company.application.model.Machine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("secured")
public class ApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private Customer customer;
    private Device device;
    private Machine machine;

    private HttpHeaders headers;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setName("Richard");

        device = new Device();
        device.setName("Device_1");

        machine = new Machine();
        machine.setName("Machine_1");
        machine.setLicense(UUID.randomUUID());

        headers = new HttpHeaders();
        headers.setBasicAuth("admin", "admin123");
    }

    @Test
    void testCreateCustomer() {
        HttpEntity<Customer> request = new HttpEntity<>(customer, headers);
        ResponseEntity<Customer> response = restTemplate.postForEntity("/customers", request, Customer.class);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody().getId());
    }

    @Test
    void testCreateDevice() {
        HttpEntity<Customer> request = new HttpEntity<>(customer, headers);
        ResponseEntity<Device> response = restTemplate.postForEntity("/devices", request, Device.class);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody().getId());
    }

    @Test
    void testCreateMachine() {
        HttpEntity<Customer> request = new HttpEntity<>(customer, headers);
        ResponseEntity<Machine> response = restTemplate.postForEntity("/machines", request, Machine.class);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody().getId());
    }

    @Test
    void testRemoveAllLicenses() {
        HttpEntity<Customer> request = new HttpEntity<>(customer, headers);
        restTemplate.postForEntity("/machines", request, Machine.class);

        restTemplate.exchange("/machines/removeAllLicences?customerId=1", HttpMethod.POST, null, Void.class);

        // Make sure the license is now NULL after the call
        ResponseEntity<Machine> response = restTemplate.getForEntity("/machines/1", Machine.class);
        assertNull(response.getBody().getLicense());
    }
}


