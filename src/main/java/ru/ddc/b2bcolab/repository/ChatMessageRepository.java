package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.model.ChatMessage;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChatMessageRepository implements CrudRepository<ChatMessage, Long> {
    private final JdbcClient jdbcClient;

    @Transactional
    @Override
    public ChatMessage save(ChatMessage model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into chat_message (chat_room_id, username, content, created_at) " +
                        "values (:chatId, :username, :content, :createdAt)")
                .paramSource(model)
                .update(keyHolder);
        model.setId(keyHolder.getKeyAs(Long.class));
        return model;
    }

    @Override
    public List<ChatMessage> findAll() {
        return jdbcClient.sql("select * from chat_message").query(ChatMessage.class).list();
    }

    @Override
    public Optional<ChatMessage> findById(Long id) {
        return jdbcClient.sql("select * from chat_message where id = :id")
                .param("id", id)
                .query(ChatMessage.class)
                .optional();
    }

    public List<ChatMessage> findByChatRoomId(Long chatRoomId) {
        return jdbcClient.sql("select * from chat_message where chat_room_id = :chatRoomId")
                .param("chatRoomId", chatRoomId)
                .query(ChatMessage.class)
                .list();
    }

    @Transactional
    @Override
    public int update(ChatMessage chatMessage) {
        return jdbcClient.sql("update chat_message set content = :content where id = :id")
                .paramSource(chatMessage)
                .update();
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from chat_message where id = :id").param("id", id).update();
    }
}
