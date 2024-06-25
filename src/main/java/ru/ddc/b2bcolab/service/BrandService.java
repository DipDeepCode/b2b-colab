package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import ru.ddc.b2bcolab.controller.payload.CreateBrandRequest;
import ru.ddc.b2bcolab.model.Brand;
import ru.ddc.b2bcolab.model.Customer;
import ru.ddc.b2bcolab.repository.BrandRepository;
import ru.ddc.b2bcolab.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public Brand save(Brand brand) {
        brand = Brand.builder()
                .customerPhoneNumber(brand.getCustomerPhoneNumber())
                .firstName(brand.getFirstName())
                .lastName(brand.getLastName())
                .countBall(brand.getCountBall())
                .countLike(brand.getCountLike())
                .tariffId(brand.getTariffId())
                .customer(customerRepository.findById(brand.getCustomerPhoneNumber()).orElse(null))
                .collaborations(brand.getCollaborations())
                .likesGiven(brand.getLikesGiven())
                .likesReceived(brand.getLikesReceived())
                .photo(brand.getPhoto())
                .telegramNick(brand.getTelegramNick())
                .birthDate(brand.getBirthDate())
                .position(brand.getPosition())
                .discussionTopics(brand.getDiscussionTopics())
                .publicSpeakingWillingness(brand.getPublicSpeakingWillingness())
                .entrepreneurCommunity(brand.getEntrepreneurCommunity())
                .brandName(brand.getBrandName())
                .businessCategory(brand.getBusinessCategory())
                .brandType(brand.getBrandType())
                .brandedSocialLink(brand.getBrandedSocialLink())
                .brandWebsiteLink(brand.getBrandWebsiteLink())
                .followersCount(brand.getFollowersCount())
                .averageCheck(brand.getAverageCheck())
                .productUniqueness(brand.getProductUniqueness())
                .customerProblemSolved(brand.getCustomerProblemSolved())
                .interactionFormats(brand.getInteractionFormats())
                .collaborationGoal(brand.getCollaborationGoal())
                .preferredBusinessCategory(brand.getPreferredBusinessCategory())
                .brandPresenceTerritory(brand.getBrandPresenceTerritory())
                .businessEssence(brand.getBusinessEssence())
                .brandValues(brand.getBrandValues())
                .targetAudienceDescription(brand.getTargetAudienceDescription())
                .targetAudienceCategories(brand.getTargetAudienceCategories())
                .build();
        return brandRepository.save(brand);
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }

    public Optional<Brand> findByCustomerPhoneNumber(String customerPhoneNumber) {
        return brandRepository.findByCustomerPhoneNumber(customerPhoneNumber);
    }

    @Transactional
    public int update(Brand brand) {
        return brandRepository.update(brand);
    }



    @Transactional
    public int deleteById(Long id) {
        return brandRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return brandRepository.exists(id);
    }
}
