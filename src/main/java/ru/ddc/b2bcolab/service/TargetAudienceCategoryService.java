package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ddc.b2bcolab.model.TargetAudienceCategory;
import ru.ddc.b2bcolab.repository.TargetAudienceCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TargetAudienceCategoryService {
    private final TargetAudienceCategoryRepository targetAudienceCategoryRepository;

    public TargetAudienceCategory save(TargetAudienceCategory targetAudienceCategory) {
        return targetAudienceCategoryRepository.save(targetAudienceCategory);
    }

    public List<TargetAudienceCategory> findAll() {
        return targetAudienceCategoryRepository.findAll();
    }

    public TargetAudienceCategory findById(Long id) {
        return targetAudienceCategoryRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        targetAudienceCategoryRepository.deleteById(id);
    }
}