package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ddc.b2bcolab.model.Subscription;
import ru.ddc.b2bcolab.repository.SubscriptionRepository;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public Subscription getSubscriptionByUserId(String userId) {
        Optional<Subscription> subscription = subscriptionRepository.findById(userId);
        return subscription.orElse(null); // Возвращаем null, если подписка не найдена
    }

    public Optional<Subscription> getSubscriptionByEmail(String email) {
        return subscriptionRepository.findByEmail(email);
    }



    public void saveSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public boolean updateSubscription(Subscription subscription) {
        return subscriptionRepository.update(subscription) > 0;
    }

    public boolean deleteSubscription(String userId) {
        return subscriptionRepository.deleteById(userId) > 0;
    }

    public boolean subscriptionExists(String userId) {
        return subscriptionRepository.exists(userId);
    }
}