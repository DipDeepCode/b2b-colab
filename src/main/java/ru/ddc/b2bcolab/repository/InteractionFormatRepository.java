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
public class InteractionFormatRepository {
    private final JdbcClient jdbcClient;

    public InteractionFormat save(InteractionFormat interactionFormat) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into interaction_formats (name, brand_id) values (:name, :brandId) returning id")
                .paramSource(interactionFormat)
                .update(keyHolder);
        interactionFormat.setId(keyHolder.getKeyAs(Long.class));
        return interactionFormat;
    }

    public List<InteractionFormat> findAll() {
        return jdbcClient.sql("select * from interaction_formats")
                .query(new BeanPropertyRowMapper<>(InteractionFormat.class))
                .list();
    }

    public Optional<InteractionFormat> findById(Long id) {
        return jdbcClient.sql("select * from interaction_formats where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(InteractionFormat.class))
                .optional();
    }

    public int deleteById(Long id) {
        return jdbcClient.sql("delete from interaction_formats where id = :id")
                .param("id", id)
                .update();
    }
}