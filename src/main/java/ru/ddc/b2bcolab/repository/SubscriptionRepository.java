package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.Subscription;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SubscriptionRepository implements CrudRepository<Subscription, String> {

    private final JdbcClient jdbcClient;

    @Override
    public Subscription save(Subscription model) {
        String sql = "INSERT INTO subscriptions (customer_username, current_plan, subscription_start_date, subscription_end_date, is_active) VALUES (?, ?, ?, ?, ?)";
        jdbcClient.sql(sql)
                .paramSource(model)
                .update();
        return model;
    }

    @Override
    public List<Subscription> findAll() {
        return List.of();
    }

    @Override
    public Optional<Subscription> findById(String id) {
        String sql = "SELECT * FROM subscriptions WHERE customer_username =:customerUsername";
        return jdbcClient.sql(sql)
                .param("customerUsername",id)
                .query(Subscription.class)
                .optional();
    }

    @Override
    public int update(Subscription subscription) {
        String sql = "UPDATE subscriptions SET current_plan =:currentPlan, subscription_start_date =:subscriptionStartDate , subscription_end_date =:subscriptionEndDate , is_active =:isActive WHERE customer_username =:customerUsername";
        return jdbcClient.sql(sql)
                .paramSource(subscription)
                .update();
    }

    @Override
    public int deleteById(String s) {
        return 0;
    }

    @Override
    public boolean exists(String s) {
        return false;
    }
}
