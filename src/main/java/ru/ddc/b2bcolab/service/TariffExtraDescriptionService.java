package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.model.TariffExtraDescription;
import ru.ddc.b2bcolab.repository.TariffExtraDescriptionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TariffExtraDescriptionService {
    private final TariffExtraDescriptionRepository tariffExtraDescriptionRepository;

    @Transactional
    public TariffExtraDescription createTariffExtraDescription(TariffExtraDescription tariffExtraDescription) {
        return tariffExtraDescriptionRepository.save(tariffExtraDescription);
    }

    public List<TariffExtraDescription> getAllTariffExtraDescriptions() {
        return tariffExtraDescriptionRepository.findAll();
    }

    public TariffExtraDescription getTariffExtraDescriptionById(Long id) {
        return tariffExtraDescriptionRepository.findById(id).orElseThrow();
    }

    public List<TariffExtraDescription> getTariffExtraDescriptionsByTariffPlanId(Long tariffPlanId) {
        return tariffExtraDescriptionRepository.findByTariffPlanId(tariffPlanId);
    }

    @Transactional
    public void updateTariffExtraDescription(TariffExtraDescription tariffExtraDescription) {
        tariffExtraDescriptionRepository.update(tariffExtraDescription);
    }

    @Transactional
    public void deleteTariffExtraDescriptionById(Long id) {
        tariffExtraDescriptionRepository.deleteById(id);
    }

    public boolean existsTariffExtraDescriptionById(Long id) {
        return tariffExtraDescriptionRepository.exists(id);
    }
}
