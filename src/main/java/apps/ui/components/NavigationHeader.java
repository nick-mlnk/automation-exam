package apps.ui.components;

import apps.ui.pages.AuthenticationPage;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import java.util.stream.Stream;


@Getter
public class NavigationHeader extends UIComponent {

    private final SelenideElement element;
    private final SelenideElement accountLink;
    private final SelenideElement userInfoLink;

    public NavigationHeader() {
        super();
        this.element = this.driver.$("#header .nav");
        this.accountLink = element.$(".account");
        this.userInfoLink = element.$("a[rel='nofollow']");
    }

    @Step
    public NavigationHeader signIn() {
        this.userInfoLink.click();
        return this;
    }

    @Step
    public boolean isUserLoggedIn() {
        return Stream.of(accountLink, userInfoLink)
                .allMatch(SelenideElement::isDisplayed);
    }

    @Step
    public AuthenticationPage goToAuthenticatePage() {
        return new AuthenticationPage();
    }
}
