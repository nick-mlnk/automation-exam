package mobile;

import apps.mobile.ui.LoginScreen;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends EndToEndTest {

    LoginScreen loginScreen;

    @BeforeClass
    protected void setup() {
        // skip preconditions generation
    }

    @Test
    public void testLoginScreenLoadedSuccessfully() {
        loginScreen = openLoginScreen();

        Assertions.assertThat(loginScreen.isLoaded())
                .isTrue()
                .as("Login Screen was not loaded successfully");
    }

    @AfterClass
    protected void tearDown() {
        // skip tear down
    }

}
