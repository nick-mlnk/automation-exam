package web;

import apps.web.ui.pages.LandingPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import infrastructure.drivers.Browser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class WebTest extends Browser {

    protected LandingPage landingPage;

    @BeforeClass(alwaysRun = true)
    public void beforeClassSetup() {
        Configuration.browser = Browser.class.getName();
        Configuration.timeout = 15000;
        Configuration.browserSize = "2100x1080" ;
        String appUrl = "http://automationpractice.com/index.php"; // skip parametrization of environments
        Selenide.open(appUrl);
        landingPage = new LandingPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterClassTearDown() {
        Selenide.webdriver().object().quit();
    }
}
