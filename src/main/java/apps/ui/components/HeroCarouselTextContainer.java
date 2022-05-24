package apps.ui.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.Color;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.join;
import static javax.swing.text.html.HTML.Attribute.*;

@Getter
public class HeroCarouselTextContainer {

    private SelenideElement container;
    private ElementsCollection textLines;

    public HeroCarouselTextContainer(SelenideElement element) {
        this.container = element;
        this.textLines = this.container.$$("[data-testid*='line']");
    }

    public List<String> getTextColors() {
        List<String> list = this.textLines.stream()
                .map(e -> e.getCssValue(COLOR.toString()))
                .collect(Collectors.toList());
        List<String> hexList = list.stream()
                .map(rgbColor -> Color.fromString(rgbColor).asHex())
                .collect(Collectors.toList());
        return hexList;
    }

    public boolean isShown() {
        return Objects.requireNonNull(this.container.getAttribute(CLASS.toString())).contains("showText");
    }

    public String getText() {
        List<String> textLines = this.textLines.stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
        return join("", textLines);
    }

    public String getReference() {
        Set<String> references = this.textLines.stream()
                .map(textLine -> textLine.getAttribute(HREF.toString()))
                .collect(Collectors.toSet());
        if (references.size() != 1) {
            throw new IllegalStateException("References size should be 1");
        }
        return references.stream().findFirst().get();
    }
}
