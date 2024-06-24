package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.TopicDiscussion;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TopicDiscussionRepository implements CrudRepository<TopicDiscussion, Long> {
    private final JdbcClient jdbcClient;

    @Override
    public TopicDiscussion save(TopicDiscussion topicDiscussion) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into topic_discussions(name, brand_id) values (:name, :brandId) returning id")
                .paramSource(topicDiscussion)
                .update(keyHolder);
        topicDiscussion.setId(keyHolder.getKeyAs(Long.class));
        return topicDiscussion;
    }

    @Override
    public List<TopicDiscussion> findAll() {
        return jdbcClient.sql("select * from topic_discussions")
                .query(new BeanPropertyRowMapper<>(TopicDiscussion.class))
                .list();
    }

    @Override
    public Optional<TopicDiscussion> findById(Long id) {
        return jdbcClient.sql("select * from topic_discussions where id = :id")
                .param("id", id)
                .query(new BeanPropertyRowMapper<>(TopicDiscussion.class))
                .optional();
    }

    @Override
    public int update(TopicDiscussion topicDiscussion) {
        return jdbcClient.sql("update topic_discussions set name = :name, brand_id = :brandId where id = :id")
                .paramSource(topicDiscussion)
                .update();
    }

    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from topic_discussions where id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public boolean exists(Long id) {
        return jdbcClient.sql("select exists(select 'x' from topic_discussions where id = :id)")
                .param("id", id)
                .query(Boolean.class)
                .single();
    }
}