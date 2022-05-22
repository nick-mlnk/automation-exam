package apps.ui.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CartItem {

    final SelenideElement item;
    final SelenideElement trashIcon;

    public CartItem(SelenideElement element) {
        this.item = element;
        this.trashIcon = this.item.$(".icon-trash");
    }

    public void clickTrashIcon() {
        this.trashIcon.click();
        this.trashIcon.shouldBe(Condition.disappear);
    }

}
