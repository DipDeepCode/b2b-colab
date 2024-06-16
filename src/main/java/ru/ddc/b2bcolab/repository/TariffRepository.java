package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.Tariff;
import ru.ddc.b2bcolab.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class TariffRepository implements CrudRepository<Tariff, Long> {

    private final JdbcClient jdbcClient;

    @Override
    public Tariff save(Tariff model) {
        return null;
    }

    @Override
    public List<Tariff> findAll() {
        return List.of();
    }

    @Override
    public Optional<Tariff> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public int update(Tariff tariff) {
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
