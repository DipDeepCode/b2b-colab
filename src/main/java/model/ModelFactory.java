package model;


import lombok.Setter;

public class ModelFactory {

    public static User getNewUser(String username, String password, String email, String phone, Role role,
                                  boolean enabled, boolean is_moderation) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(role);
        user.setEnabled(enabled);
        user.set_moderation(is_moderation);

        return user;
    }

    public static Brand getNewBrand(long id, String name, String social_media_link, String brand_values_character,
                                   String target_audience, String contact_person_name, String founder_interests,
                                   String category, int subscriber_count, String geo, int count_ball, long count_like,
                                   String username_id, long tariff_id) {

        Brand brand = new Brand();
        brand.setId(id);
        brand.setName(name);
        brand.setSocial_media_link(social_media_link);
        brand.setBrand_values_character(brand_values_character);
        brand.setTarget_audience(target_audience);
        brand.setContact_person_name(contact_person_name);
        brand.setFounder_interests(founder_interests);
        brand.setCategory(category);
        brand.setSubscriber_count(subscriber_count);
        brand.setGeo(geo);
        brand.setCount_ball(count_ball);
        brand.setCount_like(count_like);
        brand.setUsername_id(username_id);
        brand.setTariff_id(tariff_id);

        return brand;
    }
}
