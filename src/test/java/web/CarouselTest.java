package web;

import infrastructure.utils.StringUtils;
import models.CarouselModel;
import models.CarouselTileModel;
import models.SlideModel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static infrastructure.utils.RegexUtils.replaceAllExceptOfLettersAndDigits;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;


public class CarouselTest extends WebTest {

    List<CarouselModel> carouselModels = new ArrayList<>();
    int modelsMaxCount;

    @BeforeClass
    public void initialSetup() {
        modelsMaxCount = mainPage.getHeroImageContainer().getCarouselTileLinkOverlays().size();
        CarouselModel carouselModel = getCarouselModel();
        carouselModels.add(carouselModel);

        int callsCounter = 0; // call to gather carousel model
        while (carouselModels.size() != modelsMaxCount || callsCounter == 500) {
            CarouselModel nextModel = getCarouselModel();
            if (carouselModel.notEquals(nextModel)) {
                carouselModels.add(nextModel);
                carouselModel = nextModel;
            }
            callsCounter++;
        }
    }

    @Test
    public void testCarouselContentIsChangedSuccessfully() {
        assertThat(carouselModels.size())
                .as("Carousel Model Collection's size")
                .isEqualTo(modelsMaxCount);
    }

    @Test
    public void testCarouselContentAttributesAreUnique() {
        Set<String> backgroundColorsList = carouselModels.stream()
                .map(CarouselModel::getBackgroundColour)
                .collect(Collectors.toSet());
        Set<String> textList = carouselModels.stream()
                .map(CarouselModel::getText)
                .collect(Collectors.toSet());
        Set<String> slideRefList = carouselModels.stream()
                .map(CarouselModel::getSlideModel)
                .map(SlideModel::getReference)
                .collect(Collectors.toSet());
        Set<String> slideImgSrcList = carouselModels.stream()
                .map(CarouselModel::getSlideModel)
                .map(SlideModel::getImgSource)
                .collect(Collectors.toSet());
        Set<String> tileRefList = carouselModels.stream()
                .map(CarouselModel::getTileModel)
                .map(CarouselTileModel::getReference)
                .collect(Collectors.toSet());
        Set<String> tileImgSrcList = carouselModels.stream()
                .map(CarouselModel::getTileModel)
                .map(CarouselTileModel::getImgSource)
                .collect(Collectors.toSet());

        softly.assertThat(backgroundColorsList.size())
                .as(format("Background Colors[%s] Collection's size", backgroundColorsList))
                .isEqualTo(modelsMaxCount);
        softly.assertThat(textList.size())
                .as("Text Collection's size")
                .isEqualTo(modelsMaxCount);
        softly.assertThat(slideRefList.size())
                .as("Slide references Collection's size")
                .isEqualTo(modelsMaxCount);
        softly.assertThat(slideImgSrcList.size())
                .as("Slide image source Collection's size")
                .isEqualTo(modelsMaxCount);
        softly.assertThat(tileRefList.size())
                .as("Tile references Collection's size")
                .isEqualTo(modelsMaxCount);
        softly.assertThat(tileImgSrcList.size())
                .as("Tile image source Collection's size")
                .isEqualTo(modelsMaxCount);
    }

    @Test
    public void testLinesOfText() {
        List<String> expectedTextColors = asList("#ffffff", "#ffffff", "#1c203c");


        carouselModels.forEach(model -> {
            String text = replaceAllExceptOfLettersAndDigits(model.getText());
            softly.assertThat(StringUtils.isUpperCase(text))
                    .as(format("Text[%s] in Uppercase", text))
                    .isTrue();
            softly.assertThat(model.getTextColors())
                    .containsExactlyElementsOf(expectedTextColors)
                    .as(format("Text colors[%s]", model.getTextColors()));
        });
    }

    @Test
    public void testReferencesAreMatchedInContent() {
        carouselModels.forEach(model -> {
            softly.assertThat(model.hasTheSameReferences())
                    .as(format("Model %s ", model.getAllReferences()))
                    .isTrue();
        });
    }

    @AfterClass(alwaysRun = true)
    public void assertAll() {
        softly.assertAll();
    }

    private CarouselModel getCarouselModel() {
        return new CarouselModel()
                .setBackgroundColour(mainPage.getTopSectionStyle())
                .setSlideModel(new SlideModel(mainPage.getHeroImageContainer().getSelectedSlide()))
                .setTileModel(new CarouselTileModel(mainPage.getHeroImageContainer().getSelectedTileLinkOverlay()))
                .setText(mainPage.getSelectedTextContainer().getText())
                .setTextColors(mainPage.getSelectedTextContainer().getTextColors())
                .setTextReference(mainPage.getSelectedTextContainer().getReference());
    }
}
