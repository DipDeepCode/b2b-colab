package ru.ddc.b2bcolab.FileDAO;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.ddc.b2bcolab.model.modFile.FileInfo;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
@RequiredArgsConstructor
public class FileDAOImpl implements FileDAO {

    private static final String CREATE_FILE = "INSERT INTO files3 (name, size, key) VALUES (?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public FileInfo create(final FileInfo file) {

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(x -> {
            PreparedStatement preparedStatement = x.prepareStatement(CREATE_FILE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, file.getName());
            preparedStatement.setLong(2, file.getSize());
            preparedStatement.setString(3, file.getKey());

            return preparedStatement;
        }, keyHolder);

        return file.toBuilder()
                .id(keyHolder.getKey().longValue())

                .build();
    }
}
