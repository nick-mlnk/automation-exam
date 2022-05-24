package apps.ui.pages;

import apps.ui.components.HeroCarouselTextContainer;
import apps.ui.components.HeroImageContainer;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class MainPage extends BasePage {

    private HeroImageContainer heroImageContainer;
    private List<HeroCarouselTextContainer> textContainers;
    private SelenideElement landingModuleTopSection;

    public MainPage() {
        waitUntilLoaded();
        this.heroImageContainer = new HeroImageContainer($("[data-testid='heroImageContainer']"));
        this.textContainers = $$("[data-testid*='heroCarouselTextContainer']")
                .stream()
                .map(HeroCarouselTextContainer::new)
                .collect(Collectors.toList());
        this.landingModuleTopSection = $("[class*='landing-module__topSection']");
    }

    public HeroCarouselTextContainer getSelectedTextContainer() {
        return this.textContainers.stream()
                .filter(HeroCarouselTextContainer::isShown)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Hero Carousel Text Container was not found"));
    }

    public String getTopSectionStyle() {
        return this.landingModuleTopSection.getAttribute("style");
    }

    @Override
    protected void waitUntilLoaded() {
        super.waitUntilLoaded();
    }
}
