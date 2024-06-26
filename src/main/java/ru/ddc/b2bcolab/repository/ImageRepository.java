package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.Image;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ImageRepository implements CrudRepository<Image, Long> {
    private final JdbcClient jdbcClient;


    @Override
    public Image save(Image image) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into images (brand_id, filename, image_type) values (:brandId, :filename, :imageType) returning id")
                .param("brandId", image.getBrandId())
                .param("filename", image.getFilename())
                .param("imageType", image.getImageType().toString())
                .update(keyHolder);
        image.setId(keyHolder.getKeyAs(Long.class));
        return image;
    }

    @Override
    public List<Image> findAll() {
        return List.of();
    }

    @Override
    public Optional<Image> findById(Long id) {
        return jdbcClient.sql("select * from images where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(Image.class))
                .optional();
    }

    public Image findLogoImageByBrandId(Long brandId) {
        return jdbcClient.sql(
                "select * from " +
                        "(select i.*, row_number() over (order by i.id) rn " +
                        "from images i where i.brand_id = :brandId and i.image_type = 'LOGO') t " +
                        "where t.rn = 1")
                .param("brandId", brandId)
                .query(new BeanPropertyRowMapper<>(Image.class))
                .single();
    }

    @Override
    public int update(Image image) {
        return 0;
    }

    @Override
    public int deleteById(Long aLong) {
        return 0;
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }
}
