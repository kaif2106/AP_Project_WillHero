package plswrk.willherofx;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class Orc extends Living {
    private final ImageView orc_image;
    Orc(ImageView orc_image, int jump_height, double x, double y) {
        super(orc_image, jump_height, x, y);
        this.orc_image = orc_image;
    }

    @Override
    public void die() {
        orc_image.setImage(new Image("/MonsterFatality.png"));
        System.out.println("Monster Fatality");
    }

    @Override
    public void on_collision() {

    }

    public ImageView getOrc_image() {
        return orc_image;
    }
}
