package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.ChatMessage;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChatMessageRepository implements CrudRepository<ChatMessage, Long> {
    private final JdbcClient jdbcClient;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into chat_message (chat_room_id, sender_phone_number, content, created_at) " +
                "values (:chatRoomId, :senderPhoneNumber, :content, :createdAt) returning id")
                .paramSource(chatMessage)
                .update(keyHolder);
        chatMessage.setId(keyHolder.getKeyAs(Long.class));
        return chatMessage;
    }

    @Override
    public List<ChatMessage> findAll() {
        return List.of();
    }

    @Override
    public Optional<ChatMessage> findById(Long aLong) {
        return Optional.empty();
    }

    public Optional<ChatMessage> findLastChatMessageByChatRoomId(Long chatRoomId) {
        return jdbcClient.sql(
                        "select * from (" +
                                "select cm.*, row_number() over(order by created_at desc) as rn from chat_message cm " +
                                "where chat_room_id = :chatRoomId) t " +
                                "where t.rn = 1")
                .param("chatRoomId", chatRoomId)
                .query(new BeanPropertyRowMapper<>(ChatMessage.class))
                .optional();
    }

    public List<ChatMessage> findLatestChatMessagesByChatRoomId(Long chatRoomId) {
        return jdbcClient.sql("select * from chat_message cm where chat_room_id = :chatRoomId order by created_at")
                .param("chatRoomId", chatRoomId)
                .query(new BeanPropertyRowMapper<>(ChatMessage.class))
                .list();
    }

    @Override
    public int update(ChatMessage chatMessage) {
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
