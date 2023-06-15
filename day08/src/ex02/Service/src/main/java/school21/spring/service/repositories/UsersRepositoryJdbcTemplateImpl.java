package school21.spring.service.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        String query = "select * from chat.users where id = :id";
        MapSqlParameterSource sqlParameter = new MapSqlParameterSource();
        sqlParameter.addValue("id", id);
        return namedParameterJdbcTemplate.query(query,sqlParameter, (rSet) -> {
            if (rSet.next()) {
                return new User(rSet.getLong("id"), rSet.getString("email"), rSet.getString("password"));
            }
        return null;
        });
    }

    @Override
    public List<User> findAll() {
        String query = "select * from chat.users";
     return jdbcTemplate.query(query,new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User entity) {
        String query = "insert into chat.users (email) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getEmail());
            return preparedStatement;
        }, keyHolder);
        entity.setId((Long) Objects.requireNonNull(keyHolder.getKeys()).get("id"));
    }

    @Override
    public void update(User entity) {
        String query = "update chat.users set email = :email where id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("email", entity.getEmail());
        parameterSource.addValue("id", entity.getId());
        if (namedParameterJdbcTemplate.update(query, parameterSource) == 0) {
            throw new IllegalArgumentException("Couldn't update");
        }
    }

    @Override
    public void delete(Long id) {
        String query = "delete from chat.users where id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        if (namedParameterJdbcTemplate.update(query, parameterSource) == 0) {
            throw new IllegalArgumentException("Couldn't delete");
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String query = "select * from chat.users where email = :email";
        MapSqlParameterSource sqlParameter = new MapSqlParameterSource();
        sqlParameter.addValue("email", email);
        return namedParameterJdbcTemplate.query(query,sqlParameter, (rSet) -> {
            if (rSet.next()) {
                return Optional.of(new User(rSet.getLong("id"), rSet.getString("email"), rSet.getString("password")));
            }
        return Optional.empty();

        });

    }
}
