package edu.school21.repositories;

import edu.school21.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryJdbcImpl implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from tests.products"
                , new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public Optional<Product> findById(long id) {
        return jdbcTemplate.query("select * from tests.products where id = ?"
                        , new Object[]{id}, new BeanPropertyRowMapper<>(Product.class))
                .stream().findAny();
    }

    @Override
    public void update(Product product) {
        jdbcTemplate.update("update tests.products set name = ?, price = ? where id = ?"
                , product.getName(), product.getPrice(), product.getId());
    }

    @Override
    public void save(Product product) {
        jdbcTemplate.update("insert into tests.products (name, price) values (?, ?)"
                , product.getName(), product.getPrice());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("delete from tests.products where id = ?", id);
    }
}
