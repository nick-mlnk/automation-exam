package apps.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;


@Getter
public class AccountPage extends BasePage {

    private final SelenideElement pageHeading;

    public AccountPage() {
        super();
        super.waitUntilLoaded();
        this.pageHeading = this.driver.$(".page-heading");
    }

    @Step
    public boolean isHeadingDisplayed() {
        return pageHeading.isDisplayed();
    }
}
