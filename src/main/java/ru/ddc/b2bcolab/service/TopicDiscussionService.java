package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.model.TopicDiscussion;
import ru.ddc.b2bcolab.repository.TopicDiscussionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicDiscussionService {
    private final TopicDiscussionRepository topicDiscussionRepository;

    @Transactional
    public TopicDiscussion save(TopicDiscussion topicDiscussion) {
        return topicDiscussionRepository.save(topicDiscussion);
    }

    public List<TopicDiscussion> findAll() {
        return topicDiscussionRepository.findAll();
    }

    public Optional<TopicDiscussion> findById(Long id) {
        return topicDiscussionRepository.findById(id);
    }

    @Transactional
    public int update(TopicDiscussion topicDiscussion) {
        return topicDiscussionRepository.update(topicDiscussion);
    }

    @Transactional
    public int deleteById(Long id) {
        return topicDiscussionRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return topicDiscussionRepository.exists(id);
    }
}