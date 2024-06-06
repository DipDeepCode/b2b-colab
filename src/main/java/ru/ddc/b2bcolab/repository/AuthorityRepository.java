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
public class AuthorityRepository {
    private final JdbcClient jdbcClient;

    public List<Authority> findAll() {
        return jdbcClient.sql("select * from authority")
                .query(new BeanPropertyRowMapper<>(Authority.class))
                .list();
    }

    public Optional<Authority> findById(String phoneNumber) {
        return jdbcClient.sql("select * from authority where phone_number = :phoneNumber")
                .param("phoneNumber", phoneNumber)
                .query(new BeanPropertyRowMapper<>(Authority.class))
                .optional();
    }

    public List<Authority> findAllByPhoneNumber(String phoneNumber) {
        return jdbcClient.sql("select * from authority where phone_number = :phoneNumber")
                .param("phoneNumber", phoneNumber)
                .query(new BeanPropertyRowMapper<>(Authority.class))
                .list();
    }

    public boolean existsById(String phoneNumber) {
        return jdbcClient.sql("select count(*) from authority where phone_number = :phoneNumber")
                .param("phoneNumber", phoneNumber)
                .query(rs -> rs.next() && rs.getInt(1) > 0);
    }

    @Transactional
    public int update(Authority authority) {
        return jdbcClient.sql("update authority set role = :role where phone_number = :phoneNumber")
                .param("phoneNumber", authority.getPhoneNumber())
                .param("role", authority.getRole().toString())
                .update();
    }

    @Transactional
    public Authority save(final Authority authority) {
        jdbcClient.sql("insert into authority (phone_number, role) values (:phoneNumber, :role)")
                .param("phoneNumber", authority.getPhoneNumber())
                .param("role", authority.getRole().toString())
                .update();
        return authority;
    }

    @Transactional
    public void deleteById(String phoneNumber) {
        jdbcClient.sql("delete from authority where phone_number = :phoneNumber")
                .param("phoneNumber", phoneNumber)
                .update();
    }
}
