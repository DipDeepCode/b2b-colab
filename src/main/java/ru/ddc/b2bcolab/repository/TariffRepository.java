package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.Tariff;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TariffRepository {
    private final JdbcClient jdbcClient;

    public Tariff save(Tariff tariff) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("""
                insert into tariffs (type, startDate, endDate, features)
                values (:type, :startDate, :endDate, :features)
                returning id
                """)
                .paramSource(tariff)
                .update(keyHolder);
        tariff.setId(keyHolder.getKeyAs(Long.class));
        return tariff;
    }

    public List<Tariff> findAll() {
        return jdbcClient.sql("select * from tariffs")
                .query(new BeanPropertyRowMapper<>(Tariff.class))
                .list();
    }

    public Optional<Tariff> findById(Long id) {
        return jdbcClient.sql("select * from tariffs where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(Tariff.class))
                .optional();
    }

    public int update(Tariff tariff) {
        return jdbcClient.sql("""
                update tariffs
                set type = :type, startDate = :startDate, endDate = :endDate, features = :features
                where id = :id
                """)
                .paramSource(tariff)
                .update();
    }

    public boolean exists(Long id) {
        return jdbcClient.sql("select exists(select 1 from tariffs where id = :id)")
                .param("id", id)
                .query(Boolean.class)
                .single();
    }
}
