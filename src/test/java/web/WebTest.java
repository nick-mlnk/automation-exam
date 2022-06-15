package web;

import apps.ui.pages.LandingPage;
import infrastructure.drivers.Driver;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.*;

import static infrastructure.configuration.ConfigurationManger.DOMAIN_URL;


public class WebTest extends Driver {

    protected LandingPage landingPage;
    protected SoftAssertions softAssertions;

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser"})
    public void beforeClassSetup(@Optional String browser) {
        setSelenideDriver(browser);
        getSelenideDriver().open(DOMAIN_URL);
        landingPage = new LandingPage();
        softAssertions = new SoftAssertions();
    }

    @AfterSuite(alwaysRun = true)
    public void afterClassTearDown() {
        if (getSelenideDriver().hasWebDriverStarted()) {
            getSelenideDriver().close();
        }
    }
}
