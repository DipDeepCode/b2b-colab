package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.model.Brand;
import ru.ddc.b2bcolab.repository.BrandRepository;
import ru.ddc.b2bcolab.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public Brand save(Brand brand) {
        Brand newBrand = new Brand();
        newBrand.setCustomerPhoneNumber(brand.getCustomerPhoneNumber());
        newBrand.setName(brand.getName());
        newBrand.setTariffId(brand.getTariffId());
        return brandRepository.save(newBrand);
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }

    public Optional<Brand> findByCustomerPhoneNumber(String customerPhoneNumber) {
        return brandRepository.findByCustomerPhoneNumber(customerPhoneNumber);
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
