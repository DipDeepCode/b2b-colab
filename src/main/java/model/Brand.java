package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Brand {
    private long id;
    private String name;
    private String social_media_link;
    private String brand_values_character;
    private String target_audience;
    private String contact_person_name;
    private String founder_interests;
    private String category;
    private int subscriber_count;
    private String geo;
    private int count_ball;
    private long count_like;
    private String username_id;
    private long tariff_id;
    private Customer customer; // Связь с Customer
    private Set<Collaba> collabas = new HashSet<>(); // Множественные коллаборации
    private Set<Like> likesGiven = new HashSet<>(); // Лайки, которые бренд поставил
    private Set<Like> likesReceived = new HashSet<>(); // Лайки, которые бренд получил


    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", social_media_link='" + social_media_link + '\'' +
                ", brand_values_character='" + brand_values_character + '\'' +
                ", target_audience='" + target_audience + '\'' +
                ", contact_person_name='" + contact_person_name + '\'' +
                ", founder_interests='" + founder_interests + '\'' +
                ", category='" + category + '\'' +
                ", subscriber_count=" + subscriber_count +
                ", geo='" + geo + '\'' +
                ", count_ball=" + count_ball +
                ", count_like=" + count_like +
                ", username_id='" + username_id + '\'' +
                ", tariff_id=" + tariff_id +
                ", customer=" + customer +
                ", collabas=" + collabas +
                ", likesGiven=" + likesGiven +
                ", likesReceived=" + likesReceived +
                '}';
    }
}
