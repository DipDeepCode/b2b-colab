package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.TariffExtraDescription;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TariffExtraDescriptionRepository implements CrudRepository<TariffExtraDescription, Long> {
    private final JdbcClient jdbcClient;

    @Override
    public TariffExtraDescription save(TariffExtraDescription tariffExtraDescription) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into tariff_extra_description (tariff_plan_id, description, order_by, is_active, is_highlighted) " +
                        "values (:tariffPlanId, :description, :orderBy, :isActive, :isHighlighted) returning id")
                .paramSource(tariffExtraDescription)
                .update(keyHolder);
        tariffExtraDescription.setId(keyHolder.getKeyAs(Long.class));
        return tariffExtraDescription;
    }

    @Override
    public List<TariffExtraDescription> findAll() {
        return jdbcClient.sql("select * from tariff_extra_description")
                .query(new BeanPropertyRowMapper<>(TariffExtraDescription.class))
                .list();
    }

    @Override
    public Optional<TariffExtraDescription> findById(Long id) {
        return jdbcClient.sql("select * from tariff_extra_description where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(TariffExtraDescription.class))
                .optional();
    }

    public List<TariffExtraDescription> findByTariffPlanId(Long tariffPlanId) {
        return jdbcClient.sql("select * from tariff_extra_description where tariff_plan_id = :tariffPlanId order by order_by")
                .param("tariffPlanId", tariffPlanId)
                .query(new BeanPropertyRowMapper<>(TariffExtraDescription.class))
                .list();
    }

    @Override
    public int update(TariffExtraDescription tariffExtraDescription) {
        return jdbcClient.sql("update tariff_extra_description " +
                "set tariff_plan_id = :tariffPlanId, description = :description, order_by = :orderBy, " +
                "is_active = :isActive, is_highlighted = :isHighlighted where id = :id")
                .param(tariffExtraDescription)
                .update();
    }

    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from tariff_extra_description where id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public boolean exists(Long id) {
        return jdbcClient.sql("select exists(select 'x' from tariff_extra_description where id = :id)")
                .param("id", id)
                .query(Boolean.class)
                .single();
    }
}
