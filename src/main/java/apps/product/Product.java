package apps.product;

import com.github.javafaker.Faker;
import lombok.Builder;
import lombok.Getter;
import java.util.HashSet;
import java.util.Set;
import static java.util.Arrays.asList;
import static infrastructure.utils.CollectionUtils.getRandomFromList;

@Getter
@Builder
public class Product {

    private Long id;
    private String name;
    private Category category;
    private Double price;
    private Set<Order> orders;

    public boolean isBookWithPriceOver100() {
        return category == Category.BOOKS && price > 100;
    }

    public static class ProductBuilder {

        public ProductBuilder generateModel() {
            Faker faker = Faker.instance();
            this.id = faker.number().randomNumber();
            this.name = faker.commerce().productName();
            this.category = getRandomFromList(asList(Category.values()));
            this.price = faker.number().randomDouble(2, 90, 150);
            this.orders = new HashSet<>();
            return this;
        }
    }
}
