package apps.ui.pages;

import apps.ui.components.CartOverlay;
import apps.ui.components.ProductContainer;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;

public class LandingPage extends BasePage {

    List<ProductContainer> productContainers;

    public LandingPage() {
        super.waitUntilLoaded();
        productContainers = $$("#homefeatured .product-container")
                .stream()
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

    @Step
    public String addRandomProductToCartWithClosingOverlay() {
        ProductContainer productContainer = productContainers.stream()
                .unordered()
                .parallel()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Random Product was not found"));
        productContainer.addToCart();
        new CartOverlay().closeOverlay();
        return productContainer.getTitle();
    }

    public CartSummaryPage navigateToCartPage() {
        getCart().scrollTo().click();
        return new CartSummaryPage();
    }
}
