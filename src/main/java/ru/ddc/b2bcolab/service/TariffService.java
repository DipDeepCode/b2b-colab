package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.model.Brand;
import ru.ddc.b2bcolab.model.Tariff;
import ru.ddc.b2bcolab.repository.TariffRepository;
import ru.ddc.b2bcolab.repository.TariffType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TariffService {
    private final TariffRepository tariffRepository;

//    @Transactional
//    public Tariff buyTariff(TariffType type) {
//        Tariff newTariff = new Tariff(type, LocalDate.now(), tariffRepository.findById());
//        return tariffRepository.save(newTariff);
//    }
    @Transactional
    public Tariff buyTariff(Tariff tariff, TariffType type) {
        tariff = Tariff.builder()
                .type(type)
                .brandId(tariff.getBrandId())
                .startDate(LocalDate.now())
                .build();
        return tariffRepository.save(tariff);
    }

    public Tariff getMyTariff(Long id) {
        Optional<Tariff> tariff = tariffRepository.findById(id);
        return tariff.orElse(null);
    }

    @Transactional
    public Tariff upgradeTariff(Long id, TariffType newType) {
        Optional<Tariff> existingTariff = tariffRepository.findById(id);
        if (existingTariff.isPresent()) {
            Tariff updatedTariff = existingTariff.get();
            updatedTariff.setType(newType);
            updatedTariff.setStartDate(LocalDate.now());
            updatedTariff.setEndDate(updatedTariff.getStartDate().plusYears(1));
            updatedTariff.setFeatures(updatedTariff.createFeatures(newType));
            tariffRepository.update(updatedTariff);
            return updatedTariff;
        }
        return null;
    }

    public LocalDate getEndDateOfTariff(Long id) {
        Optional<Tariff> tariff = tariffRepository.findById(id);
        return tariff.map(Tariff::getEndDate).orElse(null);
    }

    public Tariff extendTariff(Long id, int additionalMonths) {
        Optional<Tariff> existingTariff = tariffRepository.findById(id);
        if (existingTariff.isPresent()) {
            Tariff updatedTariff = existingTariff.get();
            updatedTariff.setEndDate(updatedTariff.getEndDate().plusMonths(additionalMonths));
            tariffRepository.update(updatedTariff);
            return updatedTariff;
        }
        return null;
    }

    public List<Tariff> getAllTariffs() {
        return tariffRepository.findAll();
    }
}


