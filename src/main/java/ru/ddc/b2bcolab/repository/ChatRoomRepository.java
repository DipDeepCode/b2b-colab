package ru.ddc.b2bcolab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.ChatRoom;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepository implements CrudRepository<ChatRoom, Long> {
    private final JdbcClient jdbcClient;

    @Override
    public ChatRoom save(ChatRoom model) {
        return null;
    }

    @Override
    public List<ChatRoom> findAll() {
        return List.of();
    }

    @Override
    public Optional<ChatRoom> findById(Long aLong) {
        return Optional.empty();
    }

    public List<ChatRoom> findByCustomerPhoneNumber(String phoneNumber) {
        return jdbcClient.sql("select * from chat_rooms where phone_number1 = :phoneNumber or phone_number2 = :phoneNumber")
                .param("phoneNumber", phoneNumber)
                .query(new BeanPropertyRowMapper<>(ChatRoom.class))
                .list();
    }

    @Override
    public int update(ChatRoom chatRoom) {
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
