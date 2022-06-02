package infrastructure.utils;

import com.codeborne.selenide.WebDriverRunner;
import infrastructure.drivers.Driver;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestListener extends Driver implements IInvokedMethodListener, IAnnotationTransformer {

    @Step
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod() && !testResult.isSuccess() && WebDriverRunner.hasWebDriverStarted()) {
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