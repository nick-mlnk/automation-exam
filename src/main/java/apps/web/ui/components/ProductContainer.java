package apps.web.ui.components;

import com.codeborne.selenide.SelenideElement;

import static org.openqa.selenium.By.cssSelector;

public class ProductContainer {

    final SelenideElement container;
    final SelenideElement addToCartBtn;
    final SelenideElement title;

    public ProductContainer(SelenideElement element) {
        container = element;
        addToCartBtn = container.find(cssSelector("a[title='Add to cart']"));
        title = container.find(cssSelector(".product-name"));
    }

    public void addToCart() {
        this.container.scrollTo().hover();
        addToCartBtn.click();
    }

    public String getTitle() {
        return title.getText().trim();
    }

    public boolean hasDiscount() {
        return !container.findAll(".price-percent-reduction").isEmpty();
    }
}
