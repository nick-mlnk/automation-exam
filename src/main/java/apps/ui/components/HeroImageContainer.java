package apps.ui.components;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HeroImageContainer {

    private SelenideElement container;
    private List<CarouselTileLinkOverlay> carouselTileLinkOverlays;
    private List<Slide> slides;

    public HeroImageContainer(SelenideElement element) {
        this.container = element;
        this.carouselTileLinkOverlays = container.$$("[data-testid='carouselTileLinkOverlay']")
                .stream()
                .map(CarouselTileLinkOverlay::new)
                .collect(Collectors.toList());
        this.slides = container.$$(".slide")
                .stream()
                .map(Slide::new)
                .collect(Collectors.toList());
    }

    public Slide getSelectedSlide() {
        return this.slides.stream()
                .filter(Slide::isSelected)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Selected carousel slide was not found"));
    }

    public CarouselTileLinkOverlay getSelectedTileLinkOverlay() {
        return this.carouselTileLinkOverlays.stream()
                .filter(CarouselTileLinkOverlay::isActive)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Selected carousel tile was not found"));
    }
}
