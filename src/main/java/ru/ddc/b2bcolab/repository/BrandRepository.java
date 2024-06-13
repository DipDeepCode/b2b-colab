package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.Brand;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BrandRepository implements CrudRepository<Brand, Long> {
    private final JdbcClient jdbcClient;


    @Override
    public Brand save(Brand brand) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into brands (customer_phone_number, name) values (:customerPhoneNumber, :name) returning id")
                .paramSource(brand)
                .update(keyHolder);
        brand.setId(keyHolder.getKeyAs(Long.class));
        return brand;
    }

    @Override
    public List<Brand> findAll() {
        return jdbcClient.sql("select * from brands")
                .query(new BeanPropertyRowMapper<>(Brand.class))
                .list();
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return jdbcClient.sql("select * from brands where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(Brand.class))
                .optional();
    }

    @Override
    public int update(Brand brand) {
        return 0;
    }

    @Override
    public int deleteById(Long aLong) {
        return 0;
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }
}
