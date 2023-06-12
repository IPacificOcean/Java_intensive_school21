package school21.spring.service.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.dataSource = dataSource;
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
                return new User(rSet.getLong("id"), rSet.getString("email"));
            }
        return null;
        });
    }

    @Override
    public List<User> findAll() {
        String query = "select * from chat.users";
        List<User> userList = new ArrayList<>();
     return jdbcTemplate.query(query,new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
