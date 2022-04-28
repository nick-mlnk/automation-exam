package apps.web.ui.pages;

import apps.web.ui.components.CartOverlay;
import apps.web.ui.components.ProductContainer;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;

public class LandingPage extends BasePage {

    List<ProductContainer> productContainers;

    public LandingPage() {
        super.waitUntilLoaded();
        productContainers = $$("#homefeatured .product-container").stream()
                .map(ProductContainer::new)
                .collect(Collectors.toList());
    }

    public CartOverlay addProductToCart(String title, boolean hasDiscount) {
        ProductContainer productContainer = productContainers.stream()
                .filter(product -> product.getTitle().equalsIgnoreCase(title) &&
                        product.hasDiscount() == hasDiscount)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        format("Product was not found by title[%s] and hasDiscount[%s]", title, hasDiscount)));
        productContainer.addToCart();
        return new CartOverlay();
    }

    public CartSummaryPage navigateToCartPage() {
        getCart().scrollTo().click();
        return new CartSummaryPage();
    }
}
