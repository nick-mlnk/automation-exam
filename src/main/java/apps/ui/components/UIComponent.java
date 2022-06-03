package apps.ui.components;

import com.codeborne.selenide.SelenideDriver;

public class UIComponent {

    protected SelenideDriver driver;

    public UIComponent(SelenideDriver driver) {
        this.driver = driver;
    }
}
