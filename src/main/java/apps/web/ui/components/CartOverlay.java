package apps.web.ui.components;

import apps.web.ui.pages.LandingPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class CartOverlay {

    final SelenideElement layer;
    final SelenideElement header;
    final SelenideElement cross;

    public CartOverlay() {
        this.layer = $("#layer_cart").shouldBe(Condition.visible);
        this.header = this.layer.$(".layer_cart_product h2");
        this.cross = this.layer.$(".layer_cart_product .cross");
    }

    public LandingPage closeOverlay() {
        this.cross.click();
        return new LandingPage();
    }

    public String getHeaderText() {
        return getHeader().text().trim();
    }
}
