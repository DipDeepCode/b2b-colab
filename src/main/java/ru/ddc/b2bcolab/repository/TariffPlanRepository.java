package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.TariffPlan;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TariffPlanRepository implements CrudRepository<TariffPlan, Long> {
    private final JdbcClient jdbcClient;

    @Override
    public TariffPlan save(TariffPlan tariffPlan) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into tariff_plan (name, description, price_per_month, start_date, end_date, created_at) " +
                "values (:name, :description, :pricePerMonth, :startDate, :endDate, :createdAt) returning id")
                .paramSource(tariffPlan)
                .update(keyHolder);
        tariffPlan.setId(keyHolder.getKeyAs(Long.class));
        return tariffPlan;
    }

    @Override
    public List<TariffPlan> findAll() {
        return jdbcClient.sql("select * from tariff_plan")
                .query(new BeanPropertyRowMapper<>(TariffPlan.class))
                .list();
    }

    @Override
    public Optional<TariffPlan> findById(Long id) {
        return jdbcClient.sql("select * from tariff_plan where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(TariffPlan.class))
                .optional();
    }

    @Override
    public int update(TariffPlan tariffPlan) {
        return jdbcClient.sql("update tariff_plan " +
                "set name = :name, description = :description, price_per_month = :pricePerMonth, " +
                "start_date = :startDate, end_date = :endDate, created_at = :createdAt where id = :id")
                .paramSource(tariffPlan)
                .update();
    }

    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from tariff_plan where id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public boolean exists(Long id) {
        return jdbcClient.sql("select exists(select 'x' from tariff_plan where id = :id)")
                .param("id", id)
                .query(Boolean.class)
                .single();
    }
}
