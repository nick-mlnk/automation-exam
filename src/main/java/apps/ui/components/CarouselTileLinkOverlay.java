package apps.ui.components;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static java.lang.Boolean.parseBoolean;

@Getter
public class CarouselTileLinkOverlay {

    private SelenideElement tileLinkOverlay;
    private SelenideElement image;

    public CarouselTileLinkOverlay(SelenideElement element) {
        this.tileLinkOverlay = element;
        this.image = this.tileLinkOverlay.$(".lazyload-wrapper img");
    }

    public boolean isActive() {
        return parseBoolean(this.tileLinkOverlay.getAttribute("data-isactive"));
    }

    public String getReference() {
        return this.tileLinkOverlay.getAttribute("href");
    }

    public String getImageSource() {
        return image.getAttribute("src");
    }
}
