package ru.ddc.b2bcolab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.model.Authority;
import ru.ddc.b2bcolab.repository.AuthorityRepository;

import java.util.List;

@RestController
@RequestMapping("/authorities")
@RequiredArgsConstructor
public class AuthorityController {
    private final AuthorityRepository authorityRepository;

    @PostMapping
    public ResponseEntity<Authority> createAuthority(@RequestBody Authority authority) {
        Authority savedAuthority = authorityRepository.save(authority);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthority);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Authority>> getAllAuthorities() {
        List<Authority> authorities = authorityRepository.findAll();
        return ResponseEntity.ok(authorities);
    }

    @GetMapping("/get/phone/{phoneNumber}")
    public ResponseEntity<Authority> getAuthorityByPhoneNumber(@PathVariable String phoneNumber) {
        return authorityRepository.findById(phoneNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{phoneNumber}")
    public ResponseEntity<Authority> updateAuthority(@PathVariable String phoneNumber, @RequestBody Authority authority) {
        if (!authorityRepository.existsById(phoneNumber)) {
            return ResponseEntity.notFound().build();
        }
        authority.setPhoneNumber(phoneNumber);
        Authority updatedAuthority = authorityRepository.save(authority);
        return ResponseEntity.ok(updatedAuthority);
    }

    @DeleteMapping("/delete/{phoneNumber}")
    public ResponseEntity<Void> deleteAuthority(@PathVariable String phoneNumber) {
        if (!authorityRepository.existsById(phoneNumber)) {
            return ResponseEntity.notFound().build();
        }
        authorityRepository.deleteById(phoneNumber);
        return ResponseEntity.noContent().build();
    }
}