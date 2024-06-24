package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.BrandValue;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BrandValueRepository implements CrudRepository<BrandValue, Long> {
    private final JdbcClient jdbcClient;

    @Override
    public BrandValue save(BrandValue model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into brand_values(value, brand_id) values (:value, :brandId)")
                .paramSource(model)
                .update(keyHolder);
        model.setId(keyHolder.getKeyAs(Long.class));
        return model;
    }

    @Override
    public List<BrandValue> findAll() {
        return jdbcClient.sql("select * from brand_values")
                .query(new BeanPropertyRowMapper<>(BrandValue.class))
                .list();
    }

    @Override
    public Optional<BrandValue> findById(Long id) {
        return jdbcClient.sql("select * from brand_values where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(BrandValue.class))
                .optional();
    }

    @Override
    public int update(BrandValue brandValue) {
        return jdbcClient.sql("update brand_values set value = :value, brand_id = :brandId where id = :id")
                .paramSource(brandValue)
                .update();
    }

    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from brand_values where id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public boolean exists(Long id) {
        return jdbcClient.sql("select exists(select 'x' from brand_values where id = :id)")
                .param("id", id)
                .query(Boolean.class)
                .single();
    }
}