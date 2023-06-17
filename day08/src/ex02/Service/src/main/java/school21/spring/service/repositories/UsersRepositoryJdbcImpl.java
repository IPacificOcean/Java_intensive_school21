package school21.spring.service.repositories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
//@Setter
public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final DataSource dataSource;
    @Autowired
    public UsersRepositoryJdbcImpl(@Qualifier("hikariDataSource")DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        String query = "select * from chat.users where id = ?";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return new User(result.getLong(1), result.getString(2), result.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        String query = "select * from chat.users";
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(query)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                userList.add(new User(result.getLong(1), result.getString(2), result.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


    @Override
    public void save(User entity) {
        String query = "insert into chat.users (email, password) values (?, ?)";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            int countUpdate = preparedStatement.executeUpdate();
            if (countUpdate != 0) {
                ResultSet returnedId = preparedStatement.getGeneratedKeys();
                if (returnedId.next()) {
                    entity.setId(returnedId.getLong("id"));
                }
            } else {
                throw new IllegalArgumentException("Couldn't save");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        String query = "update chat.users set email = ?, password = ? where id = ?";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setLong(3, entity.getId());
            int countUpdate = preparedStatement.executeUpdate();
            if (countUpdate == 0) {
                throw new IllegalArgumentException("Couldn't update");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "delete from chat.users where id = ?";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            int countUpdate = preparedStatement.executeUpdate();
            if (countUpdate == 0) {
                throw new IllegalArgumentException("Couldn't delete");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Optional<User> findByEmail(String email) {
        String query = "select * from chat.users where email = ?";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return Optional.of(new User(result.getLong(1), result.getString(2), result.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
