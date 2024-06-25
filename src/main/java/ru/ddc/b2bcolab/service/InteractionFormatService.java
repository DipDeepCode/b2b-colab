package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.model.InteractionFormat;
import ru.ddc.b2bcolab.repository.InteractionFormatRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InteractionFormatService {
    private final InteractionFormatRepository interactionFormatRepository;

    @Transactional
    public InteractionFormat save(InteractionFormat interactionFormat) {
        interactionFormat = InteractionFormat.builder()
                .name(interactionFormat.getName())
                .brandId(interactionFormat.getBrandId())
                .build();
        return interactionFormatRepository.save(interactionFormat);
    }

    public List<InteractionFormat> findAll() {
        return interactionFormatRepository.findAll();
    }

    public Optional<InteractionFormat> findById(Long id) {
        return interactionFormatRepository.findById(id);
    }

    @Transactional
    public int update(InteractionFormat interactionFormat) {
        return interactionFormatRepository.update(interactionFormat);
    }

    @Transactional
    public int deleteById(Long id) {
        return interactionFormatRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return interactionFormatRepository.exists(id);
    }
}