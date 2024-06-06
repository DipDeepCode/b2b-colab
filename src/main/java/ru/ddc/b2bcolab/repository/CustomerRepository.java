package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.model.Customer;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepository implements CrudRepository<Customer, String> {
    private final JdbcClient jdbcClient;

    @Override
    public Customer save(Customer model) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return List.of();
    }

    @Override
    public Optional<Customer> findById(String id) {
        return jdbcClient.sql("select * from customers where phone_number = :phoneNumber")
                .param("phoneNumber", id)
                .query(new BeanPropertyRowMapper<>(Customer.class))
                .optional();
    }

    public Optional<Customer> findByPhoneNumber(String phoneNumber) {
        return findById(phoneNumber);
    }

    public Optional<Customer> findByEmail(String email) {
        return jdbcClient.sql("select * from customers where email = :email")
                .param("email", email)
                .query(new BeanPropertyRowMapper<>(Customer.class))
                .optional();
    }

    public Optional<Customer> findByPhoneNumberOrEmail(String phoneNumberOrEmail) {
        Optional<Customer> result1 = findByPhoneNumber(phoneNumberOrEmail);
        Optional<Customer> result2 = findByEmail(phoneNumberOrEmail);
        return result1.isPresent() ? result1 : result2;
    }

    @Override
    public int update(Customer customer) {
        return jdbcClient.sql("update customers set email = :email, password = :password where phone_number = :phoneNumber")
                .paramSource(customer)
                .update();
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public boolean exists(String phoneNumber) {
        return jdbcClient.sql("select exists(select 'x' from customers where phone_number = :phoneNumber)")
                .param("phoneNumber", phoneNumber)
                .query(Boolean.class)
                .single();
    }

    public boolean existsByPhoneNumber(String phoneNumber) {
        return exists(phoneNumber);
    }

    public boolean existsByEmail(String email) {
        return jdbcClient.sql("select exists(select 'x' from customers where email = :email)")
                .param("email", email)
                .query(Boolean.class)
                .single();
    }
}
