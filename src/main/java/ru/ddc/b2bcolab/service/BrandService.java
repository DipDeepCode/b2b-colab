package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ddc.b2bcolab.controller.payload.CreateBrandRequest;
import ru.ddc.b2bcolab.model.Brand;
import ru.ddc.b2bcolab.repository.BrandRepository;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public Brand saveBrand(CreateBrandRequest request) {
        Brand brand = modelMapper.map(request, Brand.class);
        return brandRepository.save(brand);
    }

    public Brand getById(Long id) {
        return brandRepository.findById(id).orElseThrow();
    }
}
