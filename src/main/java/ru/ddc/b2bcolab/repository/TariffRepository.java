package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.Tariff;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TariffRepository {

    private final JdbcTemplate jdbcTemplate;

    public Optional<Tariff> findByName(String nameTariff) {
        String sql = "SELECT * FROM tariffs WHERE name_tariff = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tariff.class), nameTariff)
                .stream()
                .findFirst();
    }
}
