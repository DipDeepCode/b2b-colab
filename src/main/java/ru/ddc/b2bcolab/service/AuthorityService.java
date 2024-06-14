package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.model.Authority;
import ru.ddc.b2bcolab.repository.AuthorityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public List<Authority> getAllAuthorities() {
        return authorityRepository.findAll();
    }

    public Optional<Authority> getAuthorityByPhoneNumber(String phoneNumber) {
        return authorityRepository.findById(phoneNumber);
    }

    public boolean existsById(String phoneNumber) {
        return authorityRepository.existsById(phoneNumber);
    }
    @Transactional
    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Transactional
    public int updateAuthority(String phoneNumber, Authority authority) {
        if (!authorityRepository.existsById(phoneNumber)) {
            throw new RuntimeException("Authority with phone number " + phoneNumber + " not found");
        }
        return authorityRepository.update(authority);
    }

    @Transactional
    public void deleteAuthority(String phoneNumber) {
        if (!authorityRepository.existsById(phoneNumber)) {
            throw new RuntimeException("Authority with phone number " + phoneNumber + " not found");
        }
        authorityRepository.deleteById(phoneNumber);
    }
}
