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
public class GoalCollaborationRepository implements CrudRepository<GoalCollaboration, Long> {
    private final JdbcClient jdbcClient;

    @Override
    public GoalCollaboration save(GoalCollaboration model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into goal_collaborations(name, brand_id) values (:name, :brandId) returning id")
                .paramSource(model)
                .update(keyHolder);
        model.setId(keyHolder.getKeyAs(Long.class));
        return model;
    }

    @Override
    public List<GoalCollaboration> findAll() {
        return jdbcClient.sql("select * from goal_collaborations")
                .query(new BeanPropertyRowMapper<>(GoalCollaboration.class))
                .list();
    }

    @Override
    public Optional<GoalCollaboration> findById(Long id) {
        return jdbcClient.sql("select * from goal_collaborations where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(GoalCollaboration.class))
                .optional();
    }

    @Override
    public int update(GoalCollaboration goalCollaboration) {
        return jdbcClient.sql("update goal_collaborations set name = :name, brand_id = :brandId where id = :id")
                .paramSource(goalCollaboration)
                .update();
    }

    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from goal_collaborations where id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public boolean exists(Long id) {
        return jdbcClient.sql("select exists(select 'x' from goal_collaborations where id = :id)")
                .param("id", id)
                .query(Boolean.class)
                .single();
    }
}