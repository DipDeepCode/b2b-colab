package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.Brand;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BrandRepository implements CrudRepository<Brand, Long> {
    private final JdbcClient jdbcClient;


    @Override
    public Brand save(Brand brand) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into brands(customer_phone_number, count_ball, count_like, tariff_plan_id, telegram_nickname, birth_date, " +
                        "position, discussion_topics, public_speaking_willingness, entrepreneur_community, name, " +
                        "business_category, brand_type, branded_social_link, brand_website_link, followers_count, " +
                        "average_check, product_uniqueness, customer_problem_solved, interaction_formats, " +
                        "collaboration_goal, preferred_business_category, brand_presence_territory, business_essence, " +
                        "brand_values, target_audience_description, target_audience_categories) " +
                        "values (:customerPhoneNumber, :countBall, :countLike, :tariffId, :telegramNick, :birthDate, " +
                        ":position, :discussionTopics, :publicSpeakingWillingness, :entrepreneurCommunity, :brandName, " +
                        ":businessCategory, :brandType, :brandedSocialLink, :brandWebsiteLink, :followersCount, " +
                        ":averageCheck, :productUniqueness, :customerProblemSolved, :interactionFormats, :collaborationGoal, " +
                        ":preferredBusinessCategory, :brandPresenceTerritory, :businessEssence, :brandValues, " +
                        ":targetAudienceDescription, :targetAudienceCategories) returning id")
                .paramSource(brand)
                .update(keyHolder);
        brand.setId(keyHolder.getKeyAs(Long.class));
        return brand;
    }

    @Override
    public List<Brand> findAll() {
        return jdbcClient.sql("select * from brands")
                .query(new BeanPropertyRowMapper<>(Brand.class))
                .list();
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return jdbcClient.sql("select * from brands where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(Brand.class))
                .optional();
    }

    public Optional<Brand> findByPhoneNumber(String phoneNumber) {
        return jdbcClient.sql("select * from brands where customer_phone_number = :phoneNumber")
                .param("phoneNumber", phoneNumber)
                .query(new BeanPropertyRowMapper<>(Brand.class))
                .optional();
    }

    @Override
    public int update(Brand brand) {
        return jdbcClient.sql("update brands set name = :name, tariff_plan_id = :tariffPlanId where id = :id")
                .paramSource(brand)
                .update();
    }

    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from brands where id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public boolean exists(Long id) {
        return jdbcClient.sql("select exists(select 'x' from brands where id = :id)")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(Boolean.class))
                .single();
    }
}
