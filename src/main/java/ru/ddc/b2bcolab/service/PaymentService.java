package ru.ddc.b2bcolab.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.ddc.b2bcolab.controller.payload.PaymentCurrency;
import ru.ddc.b2bcolab.controller.payload.PaymentChargeRequest;
import ru.ddc.b2bcolab.controller.payload.PaymentCheckoutResponse;
import ru.ddc.b2bcolab.model.Brand;
import ru.ddc.b2bcolab.model.Customer;
import ru.ddc.b2bcolab.model.TariffPlan;
import ru.ddc.b2bcolab.repository.BrandRepository;
import ru.ddc.b2bcolab.repository.TariffPlanRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final TariffPlanRepository tariffPlanRepository;
    private final BrandRepository brandRepository;
    private final HttpSession session;

    @Value("${stripe_secret_key}")
    private String secretKey;

    @Value("${stripe_public_key}")
    private String stripePublicKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public PaymentCheckoutResponse checkout(Long tariffPlanId) {
        TariffPlan tariffPlan = tariffPlanRepository.findById(tariffPlanId).orElseThrow();
        session.setAttribute("tariffPlan", tariffPlan);
        return PaymentCheckoutResponse.builder()
                .amount(tariffPlan.getPricePerMonth().multiply(BigDecimal.valueOf(100)).intValue())
                .stripePublicKey(stripePublicKey)
                .paymentCurrency(PaymentCurrency.RUB)
                .build();
    }

    public Charge charge(PaymentChargeRequest paymentChargeRequest) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", paymentChargeRequest.getAmount());
        chargeParams.put("currency", paymentChargeRequest.getPaymentCurrency().name().toLowerCase());
        chargeParams.put("description", paymentChargeRequest.getDescription());
        chargeParams.put("source", paymentChargeRequest.getStripeToken());

        TariffPlan tariffPlan = (TariffPlan) session.getAttribute("tariffPlan");
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Customer customer = (Customer) authentication.getPrincipal();
        Brand brand = brandRepository.findByPhoneNumber(customer.getPhoneNumber()).orElseThrow();
        brand.setTariffId(tariffPlan.getId());
        brandRepository.update(brand);

        return Charge.create(chargeParams);
    }
}
