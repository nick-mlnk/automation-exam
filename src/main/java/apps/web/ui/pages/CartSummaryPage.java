package apps.web.ui.pages;

import apps.web.ui.components.CartItem;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;

@Getter
public class CartSummaryPage extends BasePage {

    List<CartItem> items;

    public CartSummaryPage() {
        super.waitUntilLoaded();
        items = $$(".cart_item").stream()
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
}
