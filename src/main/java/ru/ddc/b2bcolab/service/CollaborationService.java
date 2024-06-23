package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.model.Collaboration;
import ru.ddc.b2bcolab.repository.CollaborationRepository;
import ru.ddc.b2bcolab.repository.LikeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CollaborationService {
    private final CollaborationRepository collaborationRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public Collaboration save(Collaboration collaboration) {
        // Проверка, что оба бренда поставили друг другу лайк
        boolean hasLikeFromBrand1ToBrand2 = likeRepository.exists(collaboration.getBrandId1());
        boolean hasLikeFromBrand2ToBrand1 = likeRepository.exists(collaboration.getBrandId2());

        if (hasLikeFromBrand1ToBrand2 && hasLikeFromBrand2ToBrand1) {
            return collaborationRepository.save(collaboration);
        } else {
            throw new IllegalStateException("Both brands must like each other to create a collaboration.");
        }
    }

    public List<Collaboration> findAll() {
        return collaborationRepository.findAll();
    }

    public Optional<Collaboration> findById(Long id) {
        return collaborationRepository.findById(id);
    }

    @Transactional
    public int update(Collaboration collaboration) {
        return collaborationRepository.update(collaboration);
    }

    @Transactional
    public int deleteById(Long id) {
        return collaborationRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return collaborationRepository.exists(id);
    }
}