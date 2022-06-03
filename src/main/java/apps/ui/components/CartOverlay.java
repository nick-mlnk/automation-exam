package apps.ui.components;

import apps.ui.pages.LandingPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;


@Getter
public class CartOverlay extends UIComponent {

    final SelenideElement layer;
    final SelenideElement header;
    final SelenideElement cross;

    public CartOverlay(SelenideDriver driver) {
        super(driver);
        this.layer = this.driver.$("#layer_cart").shouldBe(Condition.visible);
        this.header = this.layer.$(".layer_cart_product h2");
        this.cross = this.layer.$(".layer_cart_product .cross");
    }

    public LandingPage closeOverlay() {
        this.cross.click();
        this.cross.shouldBe(Condition.disappear);
        return new LandingPage(driver);
    }

    public String getHeaderText() {
        return getHeader().text().trim();
    }
}
