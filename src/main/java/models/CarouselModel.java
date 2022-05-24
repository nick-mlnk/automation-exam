package models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import static java.util.Arrays.asList;

@Getter
@Setter
@Accessors(chain = true)
public class CarouselModel {

    private String backgroundColour;
    private String text;
    private SlideModel slideModel;
    private CarouselTileModel tileModel;
    private List<String> textColors;
    private String textReference;

    public List<String> getAllReferences() {
        return asList(textReference, slideModel.getReference(), tileModel.getReference());
    }
    public boolean hasTheSameReferences() {
        return textReference.equals(slideModel.getReference()) && textReference.equals(tileModel.getReference());
    }
    public boolean notEquals(CarouselModel model) {
        return !backgroundColour.equals(model.backgroundColour) &&
                !text.equals(model.text) &&
                slideModel.notEquals(model.slideModel) &&
                tileModel.notEquals(model.tileModel);
    }

    @Override
    public String toString() {
        return "CarouselModel{" +
                "bgColour='" + backgroundColour + '\'' +
                ", text='" + text + '\'' +
                ", slideModel=" + slideModel.toString() +
                ", tileModel=" + tileModel.toString() +
                '}';
    }
}
