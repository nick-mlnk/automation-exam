package models;

import apps.ui.components.Slide;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class SlideModel {

    private String reference;
    private String imgSource;

    public SlideModel(Slide slide) {
        this.reference = slide.getReference();
        this.imgSource = slide.getImageSource();
    }

    public boolean notEquals(SlideModel obj) {
        return !reference.equals(obj.reference) && !imgSource.equals(obj.imgSource);
    }

    @Override
    public String toString() {
        return "SlideModel{" +
                "reference='" + reference + '\'' +
                ", imgSource='" + imgSource + '\'' +
                '}';
    }
}
