package web;

import apps.ui.pages.LandingPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import infrastructure.drivers.Driver;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static infrastructure.configuration.ConfigurationManger.DOMAIN_URL;

public class WebTest extends Driver {

    protected LandingPage landingPage;
    protected SoftAssertions softAssertions;

    @BeforeClass(alwaysRun = true)
    public void beforeClassSetup() {
        Configuration.browser = Driver.class.getName();
        Configuration.timeout = 15000;
        Configuration.browserSize = "2100x1080" ;
        String appUrl = DOMAIN_URL;
        Selenide.open(appUrl);
        landingPage = new LandingPage();
        softAssertions = new SoftAssertions();
    }

    @AfterClass(alwaysRun = true)
    public void afterClassTearDown() {
        Selenide.webdriver().object().quit();
    }
}
