package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Like {
    private long id;
    private String from_the_brand;
    private String to_the_brand;
    private long brand_id;

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", from_the_brand='" + from_the_brand + '\'' +
                ", to_the_brand='" + to_the_brand + '\'' +
                ", brand_id=" + brand_id +
                '}';
    }
}
