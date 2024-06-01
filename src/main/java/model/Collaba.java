package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Collaba {
    private long id;
    private String with_whom;
    private long brand_id;


    @Override
    public String toString() {
        return "Collaba{" +
                "id=" + id +
                ", with_whom='" + with_whom + '\'' +
                ", brand_id=" + brand_id +
                '}';
    }
}
