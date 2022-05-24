package apps.ui.components;

import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.Objects;

import static infrastructure.utils.CollectionUtils.verifyCollectionIsNotEmpty;
import static infrastructure.utils.RegexUtils.parseUrls;

public class Slide {

    private SelenideElement slide;
    private SelenideElement carouselElement;
    private SelenideElement image;

    public Slide(SelenideElement slide) {
        this.slide = slide;
        this.carouselElement = this.slide.$("[data-testid*='homeCarouselElement']");
        this.image = this.carouselElement.$("[data-testid='heroImage']");
    }

    public boolean isSelected() {
        return Objects.requireNonNull(this.slide.getAttribute("class")).contains("selected");
    }

    public String getReference() {
        return this.carouselElement.getAttribute("href");
    }

    public String getImageSource() {
        List<String> urls = parseUrls(this.image.getAttribute("style"));
        verifyCollectionIsNotEmpty(urls);
        return urls.get(0);
    }
}
