package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.model.TariffPlan;
import ru.ddc.b2bcolab.repository.TariffPlanRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TariffPlanService {
    private final TariffPlanRepository tariffPlanRepository;

    @Transactional
    public TariffPlan createTariffPlan(TariffPlan tariffPlan) {
        return tariffPlanRepository.save(tariffPlan);
    }

    public List<TariffPlan> getAllTariffPlans() {
        return tariffPlanRepository.findAll();
    }

    public TariffPlan getTariffPlanById(Long id) {
        return tariffPlanRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void updateTariffPlan(TariffPlan tariffPlan) {
        tariffPlan.setUpdatedAt(LocalDateTime.now());
        tariffPlanRepository.update(tariffPlan);
    }

    @Transactional
    public void deleteTariffPlanById(Long id) {
        tariffPlanRepository.deleteById(id);
    }

    public boolean existsTariffPlanById(Long id) {
        return tariffPlanRepository.exists(id);
    }
}
