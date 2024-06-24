package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.controller.payload.CreateBrandRequest;
import ru.ddc.b2bcolab.model.Brand;
import ru.ddc.b2bcolab.repository.BrandRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Brand saveBrand(CreateBrandRequest request) {
        Brand brand = modelMapper.map(request, Brand.class);
        return brandRepository.save(brand);
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public Brand getById(Long id) {
        return brandRepository.findById(id).orElseThrow();
    }

    public Optional<Brand> findByCustomerPhoneNumber(String customerPhoneNumber) {
        return brandRepository.findByPhoneNumber(customerPhoneNumber);
    }

    @Transactional
    public int update(Brand brand) {
        return brandRepository.update(brand);
    }

    @Transactional
    public int deleteById(Long id) {
        return brandRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return brandRepository.exists(id);
    }

}
