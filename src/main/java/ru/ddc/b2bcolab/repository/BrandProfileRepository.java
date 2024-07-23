package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.BrandProfile;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BrandProfileRepository implements CrudRepository<BrandProfile, Long> {
    private final JdbcClient jdbcClient;

    @Override
    public BrandProfile save(final BrandProfile model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into brand_profile(customer_phone_number, first_name, last_name, tariff_id, telegram_nick, " +
                        "birth_date, position, discussion_topics, public_speaking_willingness, entrepreneur_community, " +
                        "brand_name, business_category, brand_type, branded_social_link, brand_website_link, followers_count, " +
                        "average_check, product_uniqueness, customer_problem_solved, interaction_formats, collaboration_goal, " +
                        "preferred_business_category, brand_presence_territory, business_essence, brand_values, " +
                        "target_audience_description, target_audience_categories) " +
                        "values (:customerPhoneNumber, :firstName, :lastName, :tariffId, :telegramNick, :birthDate, :position, " +
                        ":discussionTopics, :publicSpeakingWillingness, :entrepreneurCommunity, :brandName, :businessCategory, " +
                        ":brandType, :brandedSocialLink, :brandWebsiteLink, :followersCount, :averageCheck, :productUniqueness, " +
                        ":customerProblemSolved, :interactionFormats, :collaborationGoal, :preferredBusinessCategory, " +
                        ":brandPresenceTerritory, :businessEssence, :brandValues, :targetAudienceDescription, :targetAudienceCategories)")
                .paramSource(model)
                .update(keyHolder);
        model.setId(keyHolder.getKeyAs(Long.class));
        return model;
    }

    @Override
    public List<BrandProfile> findAll() {
        return jdbcClient.sql("select * from brand_profile")
                .query(new BeanPropertyRowMapper<>(BrandProfile.class))
                .list();
    }

    @Override
    public Optional<BrandProfile> findById(Long id) {
        return jdbcClient.sql("select * from brand_profile where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(BrandProfile.class))
                .optional();
    }

    public Optional<BrandProfile> findByCustomerPhoneNumber(String customerPhoneNumber) {
        return jdbcClient.sql("select * from brand_profile where customer_phone_number = :customerPhoneNumber")
                .param("customerPhoneNumber", customerPhoneNumber)
                .query(new BeanPropertyRowMapper<>(BrandProfile.class))
                .optional();
    }

    @Override
    public int update(BrandProfile brandProfile) {
        return jdbcClient.sql("update brand_profile set customer_phone_number = :customerPhoneNumber, first_name = :firstName, " +
                        "last_name = :lastName, tariff_id = :tariffId, telegram_nick = :telegramNick, birth_date = :birthDate, " +
                        "position = :position, discussion_topics = :discussionTopics, public_speaking_willingness = :publicSpeakingWillingness, " +
                        "entrepreneur_community = :entrepreneurCommunity, brand_name = :brandName, business_category = :businessCategory, " +
                        "brand_type = :brandType, branded_social_link = :brandedSocialLink, brand_website_link = :brandWebsiteLink, " +
                        "followers_count = :followersCount, average_check = :averageCheck, product_uniqueness = :productUniqueness, " +
                        "customer_problem_solved = :customerProblemSolved, interaction_formats = :interactionFormats, " +
                        "collaboration_goal = :collaborationGoal, preferred_business_category = :preferredBusinessCategory, " +
                        "brand_presence_territory = :brandPresenceTerritory, business_essence = :businessEssence, " +
                        "brand_values = :brandValues, target_audience_description = :targetAudienceDescription, " +
                        "target_audience_categories = :targetAudienceCategories where id = :id")
                .paramSource(brandProfile)
                .update();
    }

    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from brand_profile where id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public boolean exists(Long id) {
        return jdbcClient.sql("select exists(select 'x' from brand_profile where id = :id)")
                .param("id", id)
                .query(Boolean.class)
                .single();
    }
}