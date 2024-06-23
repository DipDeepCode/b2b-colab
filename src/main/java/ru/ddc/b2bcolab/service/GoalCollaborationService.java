package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.model.GoalCollaboration;
import ru.ddc.b2bcolab.repository.GoalCollaborationRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoalCollaborationService {
    private final GoalCollaborationRepository goalCollaborationRepository;

    @Transactional
    public GoalCollaboration save(GoalCollaboration goalCollaboration) {
        return goalCollaborationRepository.save(goalCollaboration);
    }

    public List<GoalCollaboration> findAll() {
        return goalCollaborationRepository.findAll();
    }

    public Optional<GoalCollaboration> findById(Long id) {
        return goalCollaborationRepository.findById(id);
    }

    @Transactional
    public int update(GoalCollaboration goalCollaboration) {
        return goalCollaborationRepository.update(goalCollaboration);
    }

    @Transactional
    public int deleteById(Long id) {
        return goalCollaborationRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return goalCollaborationRepository.exists(id);
    }
}