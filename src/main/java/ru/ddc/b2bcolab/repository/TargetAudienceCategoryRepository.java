package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.TargetAudienceCategory;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TargetAudienceCategoryRepository implements CrudRepository<TargetAudienceCategory, Long> {
    private final JdbcClient jdbcClient;

    @Override
    public TargetAudienceCategory save(TargetAudienceCategory model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into target_audience_categories(category, brand_id) values (:category, :brandId) returning id")
                .paramSource(model)
                .update(keyHolder);
        model.setId(keyHolder.getKeyAs(Long.class));
        return model;
    }

    @Override
    public List<TargetAudienceCategory> findAll() {
        return jdbcClient.sql("select * from target_audience_categories")
                .query(new BeanPropertyRowMapper<>(TargetAudienceCategory.class))
                .list();
    }

    @Override
    public Optional<TargetAudienceCategory> findById(Long id) {
        return jdbcClient.sql("select * from target_audience_categories where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(TargetAudienceCategory.class))
                .optional();
    }

    @Override
    public int update(TargetAudienceCategory targetAudienceCategory) {
        return jdbcClient.sql("update target_audience_categories set category = :category, brand_id = :brandId where id = :id")
                .paramSource(targetAudienceCategory)
                .update();
    }

    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from target_audience_categories where id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public boolean exists(Long id) {
        return jdbcClient.sql("select exists(select 'x' from target_audience_categories where id = :id)")
                .param("id", id)
                .query(Boolean.class)
                .single();
    }
}