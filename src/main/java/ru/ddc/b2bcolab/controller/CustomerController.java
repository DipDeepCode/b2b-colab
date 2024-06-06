package ru.ddc.b2bcolab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.model.Customer;
import ru.ddc.b2bcolab.service.CustomerService;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<?> findCustomerByPhoneNumber(@PathVariable String phoneNumber) {
        Optional<Customer> customer = customerService.findCustomerByPhoneNumber(phoneNumber);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findCustomerByEmail(@PathVariable String email) {
        Optional<Customer> customer = customerService.findCustomerByEmail(email);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/exists/phoneNumber/{phoneNumber}")
    public ResponseEntity<?> existsByPhoneNumber(@PathVariable String phoneNumber) {
        boolean exists = customerService.existsByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<?> existsByEmail(@PathVariable String email) {
        boolean exists = customerService.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    @PutMapping("/update/{phoneNumber}")
    public ResponseEntity<?> updateCustomer(@PathVariable String phoneNumber, @RequestBody Customer customer) {
        Integer updatedCustomer = customerService.updateCustomer(customer);
        return ResponseEntity.ok(updatedCustomer);
    }


    @DeleteMapping("/delete/{phoneNumber}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String phoneNumber) {
        int deletedCount = customerService.deleteCustomer(phoneNumber);
        if (deletedCount > 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
