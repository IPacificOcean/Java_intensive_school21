package school21.spring.service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersService;
import school21.spring.service.services.UsersServiceImpl;

import javax.sql.DataSource;

@Configuration
//@ComponentScan("school21.spring.service")
@PropertySource("classpath:db.properties")
public class TestApplicationConfig {
    @Value("${db.schema}")
    private String schema;
    @Value("${db.data}")
    private String data;

    @Bean
    public DataSource hsqlDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScripts("schema.sql", "data.sql")
                .build();
    }

    @Bean
    public UsersRepository usersRepositoryJdbcImpl() {
        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(hsqlDataSource());
        return usersRepositoryJdbc;
    }

    @Bean
    public UsersRepository usersRepositoryJdbcTemplateImpl() {
        return new UsersRepositoryJdbcTemplateImpl(hsqlDataSource());
    }

    @Bean
    public UsersService usersServiceImpl() {
        UsersServiceImpl usersServiceImpl = new UsersServiceImpl();
        usersServiceImpl.setUsersRepository(usersRepositoryJdbcImpl());
        return usersServiceImpl;
    }
}
