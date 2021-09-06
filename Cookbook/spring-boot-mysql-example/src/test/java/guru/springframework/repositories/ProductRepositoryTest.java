package guru.springframework.repositories;

import guru.springframework.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductRepositoryTest {

    private static final BigDecimal BIG_DECIMAL_100 = BigDecimal.valueOf(100.00);
    private static final String PRODUCT_DESCRIPTION = "a cool product";
    private static final String IMAGE_URL = "http://an-imageurl.com/image1.jpg";

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        //given
        Product product = new Product();
        product.setDescription(PRODUCT_DESCRIPTION);
        product.setImageUrl(IMAGE_URL);
        product.setPrice(BIG_DECIMAL_100);

        //when
        productRepository.save(product);

        //then
        assertNotNull(product.getId());
        Product newProduct = productRepository.findById(product.getId()).orElse(null);
        assertEquals((Long) 1L, newProduct.getId());
        assertEquals(PRODUCT_DESCRIPTION, newProduct.getDescription());
        assertEquals(BIG_DECIMAL_100.compareTo(newProduct.getPrice()), 0);
        assertEquals(IMAGE_URL, newProduct.getImageUrl());
    }
}