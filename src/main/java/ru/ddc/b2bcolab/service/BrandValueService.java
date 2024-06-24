package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.model.BrandValue;
import ru.ddc.b2bcolab.repository.BrandValueRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandValueService {
    private final BrandValueRepository brandValueRepository;

    @Transactional
    public BrandValue save(BrandValue brandValue) {
        return brandValueRepository.save(brandValue);
    }

    public List<BrandValue> findAll() {
        return brandValueRepository.findAll();
    }

    public Optional<BrandValue> findById(Long id) {
        return brandValueRepository.findById(id);
    }

    @Transactional
    public int update(BrandValue brandValue) {
        return brandValueRepository.update(brandValue);
    }

    @Transactional
    public int deleteById(Long id) {
        return brandValueRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return brandValueRepository.exists(id);
    }
}