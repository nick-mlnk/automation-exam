package web;

import apps.ui.pages.MainPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import infrastructure.drivers.Driver;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static infrastructure.configuration.ConfigurationManger.getApp;

public class WebTest extends Driver {

    protected SoftAssertions softly = new SoftAssertions();
    protected MainPage mainPage;

    @BeforeClass(alwaysRun = true)
    public void beforeClassSetup() {
        Configuration.browser = Driver.class.getName();
        Configuration.timeout = 25000;
        Configuration.browserSize = "991x1000";
        String appUrl = getApp().getDomainUrl();
        Selenide.open(appUrl);
        mainPage = new MainPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterClassTearDown() {
        Selenide.webdriver().object().quit();
    }
}
