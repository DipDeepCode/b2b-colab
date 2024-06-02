package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.model.Authority;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorityRepository implements CrudRepository<Authority, String> {
    private final JdbcClient jdbcClient;

    @Transactional
    @Override
    public Authority save(final Authority authority) {
        jdbcClient.sql("insert into authority (phone_number, role) values (:phoneNumber, :role)")
                .param("phoneNumber", authority.getPhoneNumber())
                .param("role", authority.getRole().toString())
                .update();
        return authority;
    }

    @Override
    public List<Authority> findAll() {
        return List.of();
    }

    @Override
    public Optional<Authority> findById(String id) {
        return Optional.empty();
    }

    public List<Authority> findAllByPhoneNumber(String phoneNumber) {
        return jdbcClient.sql("select * from authority where phone_number = :phoneNumber")
                .param("phoneNumber", phoneNumber)
                .query(new BeanPropertyRowMapper<>(Authority.class))
                .list();
    }

    @Transactional
    @Override
    public int update(Authority authority) {
        return 0;
    }

    @Transactional
    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public boolean exists(String id) {
        return false;
    }
}
