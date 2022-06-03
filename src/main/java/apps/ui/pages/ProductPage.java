package apps.ui.pages;

import apps.ui.components.CartOverlay;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;


@Getter
public class ProductPage extends BasePage {

    private final SelenideElement addToCartBtn;
    private final SelenideElement header;

    public ProductPage(SelenideDriver driver) {
        super(driver);
        super.waitUntilLoaded();
        this.addToCartBtn = driver.$("#add_to_cart");
        this.header = driver.$("[itemprop='name']");
    }

    public CartOverlay addToCart() {
        addToCartBtn.click();
        return new CartOverlay(driver);
    }

    public ProductPage addToCartWithClosingOverlay() {
        addToCart().closeOverlay();
        return this;
    }
}
