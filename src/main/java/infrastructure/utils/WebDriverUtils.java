package infrastructure.utils;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;

import java.io.IOException;

public class WebDriverUtils {
    final static String SCREENSHOT_NAME = "screenshot";

    @Attachment(value = "{name}", type = "image/png")
    static byte[] makeScreenshot(String name) throws IOException {
        return Files.toByteArray(Screenshots.takeScreenShotAsFile());
    }
}
