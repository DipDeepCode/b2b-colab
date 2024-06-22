package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ddc.b2bcolab.model.GoalCollaboration;
import ru.ddc.b2bcolab.repository.GoalCollaborationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalCollaborationService {
    private final GoalCollaborationRepository goalCollaborationRepository;

    public GoalCollaboration save(GoalCollaboration goalCollaboration) {
        return goalCollaborationRepository.save(goalCollaboration);
    }

    public List<GoalCollaboration> findAll() {
        return goalCollaborationRepository.findAll();
    }

    public GoalCollaboration findById(Long id) {
        return goalCollaborationRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        goalCollaborationRepository.deleteById(id);
    }
}
