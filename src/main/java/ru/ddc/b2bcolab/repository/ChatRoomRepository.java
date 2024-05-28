package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.b2bcolab.model.ChatRoom;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepository implements CrudRepository<ChatRoom, Long> {
    private final JdbcClient jdbcClient;

    @Transactional
    @Override
    public ChatRoom save(ChatRoom chatRoom) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into chat_room(name, created_at) " +
                        "values (:name, :createdAt)")
                .paramSource(chatRoom)
                .update(keyHolder);
        chatRoom.setId(keyHolder.getKeyAs(Long.class));
        return chatRoom;
    }

    @Override
    public List<ChatRoom> findAll() {
        return jdbcClient.sql("select * from chat_room")
                .query(ChatRoom.class)
                .list();
    }

    @Override
    public Optional<ChatRoom> findById(Long id) {
        return jdbcClient.sql("select * from chat_room where id = :id")
                .param("id", id)
                .query(ChatRoom.class)
                .optional();
    }

    public List<ChatRoom> getChatRoomsByUserUsername(String username) {
        return jdbcClient.sql("select cr.* from users_chat_room ucr " +
                        "join chat_room cr on cr.id = ucr.chat_room_id " +
                        "where ucr.user_username = :username")
                .query(ChatRoom.class)
                .list();
    }

    @Transactional
    @Override
    public int update(ChatRoom chatRoom) {
        return jdbcClient.sql("update chat_room set name = :name where id = :id")
                .paramSource(chatRoom)
                .update();
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        return jdbcClient.sql("delete from chat_room where id = :id").param("id", id).update();
    }
}
