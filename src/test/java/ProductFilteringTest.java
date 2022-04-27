import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import apps.product.Product;
import apps.product.ProductProvider;

import java.util.List;
import java.util.stream.Collectors;

public class ProductFilteringTest {

    @Test
    public void testProductIsBookWithPriceOver100() {
        List<Product> products = ProductProvider.generateProducts(5)
                .stream()
                .filter(Product::isBookWithPriceOver100)
                .collect(Collectors.toList());

        Assertions.assertThat(products)
                .as("Products should be with category 'Books' and price over '100'")
                .isNotEmpty()
                .allMatch(Product::isBookWithPriceOver100);
    }
}
