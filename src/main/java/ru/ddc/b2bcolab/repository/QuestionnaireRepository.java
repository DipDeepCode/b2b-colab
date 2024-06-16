package ru.ddc.b2bcolab.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.Questionnaire;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuestionnaireRepository implements CrudRepository<Questionnaire, Long> {
    private final JdbcClient jdbcClient;

    @Override
    public Questionnaire save(Questionnaire questionnaire) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("""
                insert into questionnaires (favoritePhoto, telegramNick, birthDate, position, discussionTopics, 
                            publicSpeakingWillingness, entrepreneurCommunity, brandLogo, productIllustration, brandName, 
                            businessCategory, brandType, brandedSocialLink, brandWebsiteLink, followersCount, averageCheck, 
                            productUniqueness, customerProblemSolved, interactionFormats, collaborationGoal, preferredBusinessCategory, 
                            brandPresenceTerritory, businessEssence, brandValues, targetAudienceDescription, targetAudienceCategories, 
                            customer_id, brand_id)
                values (:favoritePhoto, :telegramNick, :birthDate, :position, :discussionTopics, :publicSpeakingWillingness, :entrepreneurCommunity, :brandLogo, :productIllustration, :brandName, :businessCategory, :brandType, :brandedSocialLink, :brandWebsiteLink, :followersCount, :averageCheck, :productUniqueness, :customerProblemSolved, :interactionFormats, :collaborationGoal, :preferredBusinessCategory, :brandPresenceTerritory, :businessEssence, :brandValues, :targetAudienceDescription, :targetAudienceCategories, :customer.id, :brand.id)
                returning id
                """)
                .paramSource(questionnaire)
                .update(keyHolder);
        questionnaire.setId(keyHolder.getKeyAs(Long.class));
        return questionnaire;
    }

    @Override
    public List<Questionnaire> findAll() {
        return List.of();
    }

    @Override
    public Optional<Questionnaire> findById(Long id) {
        return jdbcClient.sql("select * from questionnaires where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(Questionnaire.class))
                .optional();
    }

    @Override
    public int update(Questionnaire questionnaire) {
        return jdbcClient.sql("""
                update questionnaires
                set favoritePhoto = :favoritePhoto, telegramNick = :telegramNick, birthDate = :birthDate, position = :position, 
    discussionTopics = :discussionTopics, publicSpeakingWillingness = :publicSpeakingWillingness, 
entrepreneurCommunity = :entrepreneurCommunity, brandLogo = :brandLogo, productIllustration = :productIllustration, 
brandName = :brandName, businessCategory = :businessCategory, brandType = :brandType, brandedSocialLink = :brandedSocialLink, 
brandWebsiteLink = :brandWebsiteLink, followersCount = :followersCount, averageCheck = :averageCheck, 
productUniqueness = :productUniqueness, customerProblemSolved = :customerProblemSolved, interactionFormats = :interactionFormats, 
collaborationGoal = :collaborationGoal, preferredBusinessCategory = :preferredBusinessCategory, 
brandPresenceTerritory = :brandPresenceTerritory, businessEssence = :businessEssence, brandValues = :brandValues, 
targetAudienceDescription = :targetAudienceDescription, targetAudienceCategories = :targetAudienceCategories, 
customer_id = :customer.id, brand_id = :brand.id
                where id = :id
                """)
                .paramSource(questionnaire)
                .update();
    }

    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from questionnaires where id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public boolean exists(Long id) {
        return jdbcClient.sql("select exists(select 1 from questionnaires where id = :id)")
                .param("id", id)
                .query(Boolean.class)
                .single();
    }
}