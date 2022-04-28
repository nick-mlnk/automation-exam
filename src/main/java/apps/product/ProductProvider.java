package apps.product;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProductProvider {

    public static List<Product> generateProducts(int amount) {
        return IntStream.range(0, amount)
                .mapToObj(x -> Product.builder().generateModel().build())
                .collect(Collectors.toList());
    }
}
