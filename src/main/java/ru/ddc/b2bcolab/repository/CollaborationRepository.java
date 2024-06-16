package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.Collaboration;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CollaborationRepository implements CrudRepository<Collaboration, Long> {
    private final JdbcClient jdbcClient;

    @Override
    public Collaboration save(Collaboration model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into collaborations(brand_id1, brand_id2, created_at, updated_at) values (:brandId1, :brandId2, :createdAt, :updatedAt) returning id")
                .paramSource(model)
                .update(keyHolder);
        model.setId(keyHolder.getKeyAs(Long.class));
        return model;
    }

    @Override
    public List<Collaboration> findAll() {
        return jdbcClient.sql("select * from collaborations")
                .query(new BeanPropertyRowMapper<>(Collaboration.class))
                .list();
    }

    @Override
    public Optional<Collaboration> findById(Long id) {
        return jdbcClient.sql("select * from collaborations where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(Collaboration.class))
                .optional();
    }

    @Override
    public int update(Collaboration collaboration) {
        return jdbcClient.sql("update collaborations set brand_id1 = :brandId1, brand_id2 = :brandId2, updated_at = :updatedAt where id = :id")
                .paramSource(collaboration)
                .update();
    }

    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from collaborations where id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public boolean exists(Long id) {
        return jdbcClient.sql("select exists(select 'x' from collaborations where id = :id)")
                .param("id", id)
                .query(Boolean.class)
                .single();
    }
}