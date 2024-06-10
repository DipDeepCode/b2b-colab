package ru.ddc.b2bcolab.chat.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.chat.model.ChatRoom;
import ru.ddc.b2bcolab.model.Customer;
import ru.ddc.b2bcolab.repository.CrudRepository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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

    public List<ChatRoom> findByPhoneNumber(String phoneNumber) {
        return jdbcClient.sql("select chat_room.*, customer1.PHONE_NUMBER abc_phone, customer2.* from CHAT_ROOMS chat_room join CUSTOMERS customer1 on customer1.PHONE_NUMBER = chat_room.PHONE_NUMBER_1 join CUSTOMERS customer2 on customer2.PHONE_NUMBER = chat_room.PHONE_NUMBER_2 where chat_room.PHONE_NUMBER_1 = :phoneNumber or chat_room.PHONE_NUMBER_2 = :phoneNumber")
                .param("phoneNumber", phoneNumber)
                .query(new RowMapper<ChatRoom>() {
                    @Override
                    public ChatRoom mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ResultSetMetaData rsmd = rs.getMetaData();
                        for (int i = 1; i < 10; i++) {
                            System.out.println(rsmd.getColumnName(i));
                        }
                        return null;
                    }
                })
                .list();
    }

    @Override
    public Optional<ChatRoom> findById(Long aLong) {
        return Optional.empty();
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
