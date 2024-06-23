package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.model.TargetAudienceCategory;
import ru.ddc.b2bcolab.repository.TargetAudienceCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TargetAudienceCategoryService {
    private final TargetAudienceCategoryRepository targetAudienceCategoryRepository;

    @Transactional
    public TargetAudienceCategory save(TargetAudienceCategory targetAudienceCategory) {
        return targetAudienceCategoryRepository.save(targetAudienceCategory);
    }

    public List<TargetAudienceCategory> findAll() {
        return targetAudienceCategoryRepository.findAll();
    }

    public Optional<TargetAudienceCategory> findById(Long id) {
        return targetAudienceCategoryRepository.findById(id);
    }

    @Transactional
    public int update(TargetAudienceCategory targetAudienceCategory) {
        return targetAudienceCategoryRepository.update(targetAudienceCategory);
    }

    @Transactional
    public int deleteById(Long id) {
        return targetAudienceCategoryRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return targetAudienceCategoryRepository.exists(id);
    }
}