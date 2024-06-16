package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.Like;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LikeRepository implements CrudRepository<Like, Long> {
    private final JdbcClient jdbcClient;

    @Override
    public Like save(Like like) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("""
                insert into likes (timestamp, fromBrand_id, toBrand_id)
                values (:timestamp, :fromBrandId, :toBrandId)
                """)
                .param("timestamp", like.getTimestamp())
                .param("fromBrandId", like.getFromBrandId())
                .param("toBrandId", like.getToBrandId())
                .update(keyHolder);
        like.setId(keyHolder.getKeyAs(Long.class));
        return like;
    }

    @Override
    public List<Like> findAll() {
        return jdbcClient.sql("select * from likes")
                .query(new BeanPropertyRowMapper<>(Like.class))
                .list();
    }

    @Override
    public Optional<Like> findById(Long id) {
        return jdbcClient.sql("select * from likes where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(Like.class))
                .optional();
    }

    @Override
    public int update(Like like) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from likes where id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public boolean exists(Long id) {
        return jdbcClient.sql("select exists(select 1 from likes where id = :id)")
                .param("id", id)
                .query(Boolean.class)
                .single();
    }
}