package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.InteractionFormat;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class InteractionFormatRepository implements CrudRepository<InteractionFormat, Long> {
    private final JdbcClient jdbcClient;

    @Override
    public InteractionFormat save(InteractionFormat model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into interaction_formats(name, brand_id) values (:name, :brandId) returning id")
                .paramSource(model)
                .update(keyHolder);
        model.setId(keyHolder.getKeyAs(Long.class));
        return model;
    }

    @Override
    public List<InteractionFormat> findAll() {
        return jdbcClient.sql("select * from interaction_formats")
                .query(new BeanPropertyRowMapper<>(InteractionFormat.class))
                .list();
    }

    @Override
    public Optional<InteractionFormat> findById(Long id) {
        return jdbcClient.sql("select * from interaction_formats where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(InteractionFormat.class))
                .optional();
    }

    @Override
    public int update(InteractionFormat interactionFormat) {
        return jdbcClient.sql("update interaction_formats set name = :name, brand_id = :brandId where id = :id")
                .paramSource(interactionFormat)
                .update();
    }

    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from interaction_formats where id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public boolean exists(Long id) {
        return jdbcClient.sql("select exists(select 'x' from interaction_formats where id = :id)")
                .param("id", id)
                .query(Boolean.class)
                .single();
    }
}