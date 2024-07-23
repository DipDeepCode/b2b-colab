package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.model.BrandProfile;
import ru.ddc.b2bcolab.repository.BrandProfileRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandProfileService {
    private final BrandProfileRepository brandProfileRepository;

    @Transactional
    public BrandProfile save(BrandProfile brandProfile) {
        // Создаем новый объект BrandProfile и заполняем его данными
        BrandProfile newBrandProfile = new BrandProfile();
        newBrandProfile.setCustomerPhoneNumber(brandProfile.getCustomerPhoneNumber());
        newBrandProfile.setFirstName(brandProfile.getFirstName());
        newBrandProfile.setLastName(brandProfile.getLastName());
        newBrandProfile.setTariffId(brandProfile.getTariffId());
        newBrandProfile.setTelegramNick(brandProfile.getTelegramNick());
        newBrandProfile.setBirthDate(brandProfile.getBirthDate());
        newBrandProfile.setPosition(brandProfile.getPosition());
        newBrandProfile.setDiscussionTopics(brandProfile.getDiscussionTopics());
        newBrandProfile.setPublicSpeakingWillingness(brandProfile.getPublicSpeakingWillingness());
        newBrandProfile.setEntrepreneurCommunity(brandProfile.getEntrepreneurCommunity());
        newBrandProfile.setBrandName(brandProfile.getBrandName());
        newBrandProfile.setBusinessCategory(brandProfile.getBusinessCategory());
        newBrandProfile.setBrandType(brandProfile.getBrandType());
        newBrandProfile.setBrandedSocialLink(brandProfile.getBrandedSocialLink());
        newBrandProfile.setBrandWebsiteLink(brandProfile.getBrandWebsiteLink());
        newBrandProfile.setFollowersCount(brandProfile.getFollowersCount());
        newBrandProfile.setAverageCheck(brandProfile.getAverageCheck());
        newBrandProfile.setProductUniqueness(brandProfile.getProductUniqueness());
        newBrandProfile.setCustomerProblemSolved(brandProfile.getCustomerProblemSolved());
        newBrandProfile.setInteractionFormats(brandProfile.getInteractionFormats());
        newBrandProfile.setCollaborationGoal(brandProfile.getCollaborationGoal());
        newBrandProfile.setPreferredBusinessCategory(brandProfile.getPreferredBusinessCategory());
        newBrandProfile.setBrandPresenceTerritory(brandProfile.getBrandPresenceTerritory());
        newBrandProfile.setBusinessEssence(brandProfile.getBusinessEssence());
        newBrandProfile.setBrandValues(brandProfile.getBrandValues());
        newBrandProfile.setTargetAudienceDescription(brandProfile.getTargetAudienceDescription());
        newBrandProfile.setTargetAudienceCategories(brandProfile.getTargetAudienceCategories());

        // Сохраняем новый объект в базу данных
        return brandProfileRepository.save(newBrandProfile);
    }

    public List<BrandProfile> findAll() {
        return brandProfileRepository.findAll();
    }

    public Optional<BrandProfile> findById(Long id) {
        return brandProfileRepository.findById(id);
    }

    public Optional<BrandProfile> findByCustomerPhoneNumber(String customerPhoneNumber) {
        return brandProfileRepository.findByCustomerPhoneNumber(customerPhoneNumber);
    }

    @Transactional
    public int update(BrandProfile brandProfile) {
        return brandProfileRepository.update(brandProfile);
    }

    @Transactional
    public int deleteById(Long id) {
        return brandProfileRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return brandProfileRepository.exists(id);
    }
}