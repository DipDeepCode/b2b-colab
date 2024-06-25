package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.model.Like;
import ru.ddc.b2bcolab.repository.LikeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    @Transactional
    public Like save(Like like) {
        like = Like.builder()
                .timestamp(like.getTimestamp())
                .fromBrandId(like.getFromBrandId())
                .toBrandId(like.getToBrandId())
                .build();
        return likeRepository.save(like);
    }

    @Transactional
    public void deleteById(Long id) {
        likeRepository.deleteById(id);
    }

    public Like findById(Long id) {
        return likeRepository.findById(id).orElseThrow(() -> new RuntimeException("Like not found with id: " + id));
    }
}