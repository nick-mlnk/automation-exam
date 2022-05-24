package models;

import apps.ui.components.CarouselTileLinkOverlay;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CarouselTileModel {

    private String reference;
    private String imgSource;

    public CarouselTileModel(CarouselTileLinkOverlay tileLinkOverlay) {
        this.reference = tileLinkOverlay.getReference();
        this.imgSource = tileLinkOverlay.getImageSource();
    }

    public boolean notEquals(CarouselTileModel model) {
        return !reference.equals(model.reference) && !imgSource.equals(model.imgSource);
    }

    @Override
    public String toString() {
        return "TileModel{" +
                "reference='" + reference + '\'' +
                ", imgSource='" + imgSource + '\'' +
                '}';
    }
}
