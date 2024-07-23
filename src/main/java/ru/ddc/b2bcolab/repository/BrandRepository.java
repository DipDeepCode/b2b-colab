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
    public Brand save(final Brand model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into brands(customer_phoneNumber, name, tariff_id) " +
                        "values (:customerPhoneNumber, :name, :tariffId)")
                .paramSource(model)
                .update(keyHolder);
        model.setId(keyHolder.getKeyAs(Long.class));
        return model;
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

    public Optional<Brand> findByCustomerPhoneNumber(String customerPhoneNumber) {
        return jdbcClient.sql("select * from brands where customer_phoneNumber = :customerPhoneNumber")
                .param("customerPhoneNumber", customerPhoneNumber)
                .query(new BeanPropertyRowMapper<>(Brand.class))
                .optional();
    }

    @Override
    public int update(Brand brand) {
        return jdbcClient.sql("update brands set customer_phoneNumber = :customerPhoneNumber, name = :name, tariff_id = :tariffId where id = :id")
                .paramSource(brand)
                .update();
    }

    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from brands where id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public boolean exists(Long id) {
        return jdbcClient.sql("select exists(select 'x' from brands where id = :id)")
                .param("id", id)
                .query(Boolean.class)
                .single();
    }
}