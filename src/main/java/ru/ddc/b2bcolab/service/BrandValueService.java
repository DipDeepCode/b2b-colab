package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ddc.b2bcolab.model.BrandValue;
import ru.ddc.b2bcolab.repository.BrandValueRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandValueService {
    private final BrandValueRepository brandValueRepository;

    public BrandValue save(BrandValue brandValue) {
        return brandValueRepository.save(brandValue);
    }

    public List<BrandValue> findAll() {
        return brandValueRepository.findAll();
    }

    public BrandValue findById(Long id) {
        return brandValueRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        brandValueRepository.deleteById(id);
    }
}