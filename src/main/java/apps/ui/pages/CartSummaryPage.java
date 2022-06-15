package apps.ui.pages;

import apps.ui.components.CartItem;
import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;


@Getter
public class CartSummaryPage extends BasePage {

    List<CartItem> items;

    public CartSummaryPage() {
        super();
        super.waitUntilLoaded();
        items = this.driver.$$(".cart_item").stream()
                .map(CartItem::new)
                .collect(Collectors.toList());
    }

    public CartSummaryPage clearCart() {
        items.forEach(CartItem::clickTrashIcon);
        return new CartSummaryPage();
    }

    public boolean isCartEmpty() {
        return items.isEmpty();
    }

    public boolean isProductPresent(String product) {
        return items.stream().anyMatch(item -> item.getProductName().equals(product));
    }
}
