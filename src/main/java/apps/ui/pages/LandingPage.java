package apps.ui.pages;

import apps.ui.components.CartOverlay;
import apps.ui.components.ProductContainer;
import com.codeborne.selenide.SelenideDriver;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class LandingPage extends BasePage {

    List<ProductContainer> productContainers;

    public LandingPage(SelenideDriver driver) {
        super(driver);
        super.waitUntilLoaded();
        productContainers = this.driver.$$("#homefeatured .product-container")
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
        return new CartOverlay(driver);
    }

    @Step
    public String addRandomProductToCartWithClosingOverlay() {
        ProductContainer productContainer = productContainers.stream()
                .unordered()
                .parallel()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Random Product was not found"));
        productContainer.addToCart();
        new CartOverlay(driver).closeOverlay();
        return productContainer.getTitle();
    }

    public CartSummaryPage navigateToCartPage() {
        getCart().scrollTo().click();
        return new CartSummaryPage(driver);
    }
}
