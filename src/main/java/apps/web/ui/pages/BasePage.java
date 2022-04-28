package apps.web.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;


@Getter
public class BasePage {

    final protected SelenideElement cart;

    protected BasePage() {
        cart = $("a[title='View my shopping cart']");
    }

    protected void waitUntilLoaded() {
        cart.shouldBe(Condition.visible);
    }
}
