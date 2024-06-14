package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.Authority;
import ru.ddc.b2bcolab.model.Customer;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepository implements CrudRepository<Customer, String> {
    private final JdbcClient jdbcClient;

    @Override
    public Customer save(Customer customer) {
        jdbcClient.sql("insert into customers (phone_number, email, password, enabled) values (:phoneNumber, :email, :password, :enabled)")
                .param("phoneNumber", customer.getPhoneNumber())
                .param("email", customer.getEmail())
                .param("password", customer.getPassword())
                .param("enabled", customer.isEnabled())
                .update();

        return customer;
    }

    public Customer addAuthorities(Customer customer, Authority authority) {
        jdbcClient.sql("insert into authority (customer_phone_number, authority_role) values (:phoneNumber, :role)")
                .param("phoneNumber", customer.getPhoneNumber())
                .param("role", authority.getRole().toString())
                .update();

        return customer;
    }


    @Override
    public List<Customer> findAll() {
        return jdbcClient.sql("select * from customers")
                .query(new BeanPropertyRowMapper<>(Customer.class))
                .list();
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
        return jdbcClient.sql("update customers set email = :email, password = :password, enabled = :enabled where phone_number = :phoneNumber")
                .param("phoneNumber", customer.getPhoneNumber())
                .param("email", customer.getEmail())
                .param("password", customer.getPassword())
                .param("enabled", customer.isEnabled())
                .update();
    }

    @Override
    public int deleteById(String phoneNumber) {
        return jdbcClient.sql("delete from customers where phone_number = :phoneNumber")
                .param("phoneNumber", phoneNumber)
                .update();
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
