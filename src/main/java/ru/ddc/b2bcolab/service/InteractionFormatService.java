package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ddc.b2bcolab.model.InteractionFormat;
import ru.ddc.b2bcolab.repository.InteractionFormatRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InteractionFormatService {
    private final InteractionFormatRepository interactionFormatRepository;

    public InteractionFormat save(InteractionFormat interactionFormat) {
        return interactionFormatRepository.save(interactionFormat);
    }

    public List<InteractionFormat> findAll() {
        return interactionFormatRepository.findAll();
    }

    public InteractionFormat findById(Long id) {
        return interactionFormatRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        interactionFormatRepository.deleteById(id);
    }
}
