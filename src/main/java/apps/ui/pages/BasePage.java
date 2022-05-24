package apps.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;


@Getter
public class BasePage {

    final protected SelenideElement logo;

    protected BasePage() {
        logo = $("#tuneInLogo");
    }

    protected void waitUntilLoaded() {
        logo.shouldBe(Condition.visible);
    }
}
