package apps.ui.pages;

import com.codeborne.selenide.SelenideElement;
import data.UserData;
import io.qameta.allure.Step;
import lombok.Getter;


@Getter
public class AuthenticationPage extends BasePage {

    private final SelenideElement form;
    private final SelenideElement emailField;
    private final SelenideElement passwordField;
    private final SelenideElement signInBtn;

    public AuthenticationPage() {
        super();
        super.waitUntilLoaded();
        this.form = this.driver.$("#login_form");
        this.emailField = form.$("#email");
        this.passwordField = form.$("#passwd");
        this.signInBtn = form.$("#SubmitLogin");
    }

    @Step
    public AuthenticationPage signIn(UserData userData) {
        emailField.setValue(userData.getEmail());
        passwordField.setValue(userData.getPassword());
        signInBtn.click();
        return this;
    }

    @Step
    public AccountPage login(UserData userData) {
        signIn(userData);
        return new AccountPage();
    }
}
