package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.GoalCollaboration;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GoalCollaborationRepository {
    private final JdbcClient jdbcClient;

    public GoalCollaboration save(GoalCollaboration goalCollaboration) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into goal_collaborations (name, brand_id) values (:name, :brandId) returning id")
                .paramSource(goalCollaboration)
                .update(keyHolder);
        goalCollaboration.setId(keyHolder.getKeyAs(Long.class));
        return goalCollaboration;
    }

    public List<GoalCollaboration> findAll() {
        return jdbcClient.sql("select * from goal_collaborations")
                .query(new BeanPropertyRowMapper<>(GoalCollaboration.class))
                .list();
    }

    public Optional<GoalCollaboration> findById(Long id) {
        return jdbcClient.sql("select * from goal_collaborations where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(GoalCollaboration.class))
                .optional();
    }

    public int deleteById(Long id) {
        return jdbcClient.sql("delete from goal_collaborations where id = :id")
                .param("id", id)
                .update();
    }
}