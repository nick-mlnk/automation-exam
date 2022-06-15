package apps.ui.components;

import com.codeborne.selenide.SelenideDriver;

import static infrastructure.drivers.Driver.getSelenideDriver;

public class UIComponent {

    protected SelenideDriver driver;

    public UIComponent() {
        this.driver = getSelenideDriver();
    }
}
