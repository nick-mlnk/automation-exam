package apps.ui.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.time.Duration;

@Getter
public class CartItem {

    final SelenideElement item;
    final SelenideElement trashIcon;
    final SelenideElement productName;

    public CartItem(SelenideElement element) {
        this.item = element;
        this.trashIcon = this.item.$(".icon-trash");
        this.productName = this.item.$(".product-name");
    }

    public void clickTrashIcon() {
        this.trashIcon.click();
        this.trashIcon.shouldBe(Condition.disappear, Duration.ofSeconds(8));
    }

    public String getProductName() {
        return this.productName.getText();
    }

}
