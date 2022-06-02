package apps.ui.pages;

import apps.ui.components.CartOverlay;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class ProductPage extends BasePage {

    private final SelenideElement addToCartBtn;
    private final SelenideElement header;

    public ProductPage() {
        super.waitUntilLoaded();
        this.addToCartBtn = $("#add_to_cart");
        this.header = $("[itemprop='name']");
    }

    public CartOverlay addToCart() {
        addToCartBtn.click();
        return new CartOverlay();
    }

    public ProductPage addToCartWithClosingOverlay() {
        addToCart().closeOverlay();
        return this;
    }
}
