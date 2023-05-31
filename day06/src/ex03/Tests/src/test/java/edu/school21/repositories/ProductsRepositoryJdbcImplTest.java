package edu.school21.repositories;

import edu.school21.models.Product;
import edu.school21.repositories.ProductRepository;
import edu.school21.repositories.ProductRepositoryJdbcImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductsRepositoryJdbcImplTest {
    private DataSource dataSource;

    private ProductRepository productRepository;

    public final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "Pizza", 61.65),
            new Product(2L, "Cheese", 46.39),
            new Product(3L, "Macaroons", 12.89),
            new Product(4L, "Milk", 10.4),
            new Product(5L, "Cranberries", 81.36));

    private final Product EXPECTED_FIND_BY_ID_PRODUCT =
            new Product(3L, "Macaroons", 12.89);

    private final Product EXPECTED_UPDATED_PRODUCT =
            new Product(4L, "Yogurt", 15.80);

    public final List<Product> EXPECTED_PRODUCTS_AFTER_DELETE = Arrays.asList(
            new Product(1L, "Pizza", 61.65),
            new Product(3L, "Macaroons", 12.89),
            new Product(4L, "Milk", 10.4),
            new Product(5L, "Cranberries", 81.36));

    @BeforeEach
    public void init() {
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScripts("schema.sql", "data.sql")
                .build();
        productRepository = new ProductRepositoryJdbcImpl(dataSource);
    }

    @AfterEach
    public void drop() {
        if (dataSource != null && dataSource instanceof EmbeddedDatabase) {
            ((EmbeddedDatabase) dataSource).shutdown();
        }
    }

    @Test
    public void testFindAll() {
        List<Product> exists = productRepository.findAll();
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, exists);
    }

    @Test
    public void testFindById() {
        Product exists = productRepository.findById(3L).orElse(null);
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, exists);
    }

    @Test
    public void testUpdate() {
        productRepository.update(EXPECTED_UPDATED_PRODUCT);
        Product exists = productRepository.findById(EXPECTED_UPDATED_PRODUCT.getId()).orElse(null);
        assertEquals(EXPECTED_UPDATED_PRODUCT, exists);
    }

    @Test
    public void testSave() {
        productRepository.save(new Product("pork", 20.45));
        Product expected = new Product(6L, "pork", 20.45);
        List<Product> allProducts = productRepository.findAll();
        Product exists = allProducts.get(allProducts.size() - 1);
        assertEquals(expected, exists);
    }

    @Test
    public void testDelete() {
        productRepository.delete(2L);
        assertEquals(EXPECTED_PRODUCTS_AFTER_DELETE, productRepository.findAll());
    }
}
