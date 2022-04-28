package apps.mobile.ui;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class LoginScreen extends AbstractScreen {

    // Expected we have the same accessibilityId for both mobile platforms
    final SelenideElement email_field = $(accessibilityId("login-email-input"));
    final SelenideElement continue_btn = $(accessibilityId("sign-in-btn"));

    public boolean isLoaded() {
        return email_field.isDisplayed() && continue_btn.isEnabled();
    }

    @Override
    protected void waitUntilLoaded() {
        super.waitUntilLoaded();
    }
}
