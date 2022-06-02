package apps.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class AccountPage extends BasePage {

    private final SelenideElement pageHeading;

    public AccountPage() {
        super.waitUntilLoaded();
        this.pageHeading = $(".page-heading");
    }

    @Step
    public boolean isHeadingDisplayed() {
        return pageHeading.isDisplayed();
    }
}
