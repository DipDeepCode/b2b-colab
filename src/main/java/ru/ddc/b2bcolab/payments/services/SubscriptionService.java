package ru.ddc.b2bcolab.payments.services;

import org.springframework.stereotype.Service;
import ru.ddc.b2bcolab.payments.entity.Subscription;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class SubscriptionService {
    private Map<String, Subscription> subscriptions = new HashMap<>();


    public Subscription getSubscriptionByUserId(String userId) {
        return subscriptions.get(userId);
    }

    public void saveSubscription(Subscription subscription) {
        subscriptions.put(subscription.getUserId(), subscription);
    }
}