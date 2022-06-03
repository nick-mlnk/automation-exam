package infrastructure.utils;

import infrastructure.drivers.Driver;
import io.qameta.allure.Step;
import org.testng.*;

import java.io.IOException;

public class TestListener extends Driver implements IInvokedMethodListener, IAnnotationTransformer {

    @Step
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod() && !testResult.isSuccess() && getSelenideDriver().hasWebDriverStarted()) {
            try {
                try {
                    String scrName = WebDriverUtils.SCREENSHOT_NAME + ".png";
                    WebDriverUtils.makeScreenshot(scrName);
                } catch (IOException e) {
                }
            } catch (NullPointerException e) {
            }
        }
    }
}