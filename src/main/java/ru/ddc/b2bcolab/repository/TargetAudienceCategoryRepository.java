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
public class TargetAudienceCategoryRepository {
    private final JdbcClient jdbcClient;

    public TargetAudienceCategory save(TargetAudienceCategory targetAudienceCategory) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into target_audience_categories (category, brand_id) values (:category, :brandId) returning id")
                .paramSource(targetAudienceCategory)
                .update(keyHolder);
        targetAudienceCategory.setId(keyHolder.getKeyAs(Long.class));
        return targetAudienceCategory;
    }

    public List<TargetAudienceCategory> findAll() {
        return jdbcClient.sql("select * from target_audience_categories")
                .query(new BeanPropertyRowMapper<>(TargetAudienceCategory.class))
                .list();
    }

    public Optional<TargetAudienceCategory> findById(Long id) {
        return jdbcClient.sql("select * from target_audience_categories where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(TargetAudienceCategory.class))
                .optional();
    }

    public int deleteById(Long id) {
        return jdbcClient.sql("delete from target_audience_categories where id = :id")
                .param("id", id)
                .update();
    }
}
