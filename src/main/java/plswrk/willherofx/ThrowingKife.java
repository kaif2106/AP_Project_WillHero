package plswrk.willherofx;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ThrowingKife extends WeaponAbs{

    ThrowingKife(ImageView knife_image) {
        super(knife_image);
    }
    void throwknife(){
        TranslateTransition knifethrow = new TranslateTransition(Duration.millis(1000));
        knifethrow.setNode(getImage());
        knifethrow.setByX(400);
        knifethrow.setCycleCount(1);
        knifethrow.setOnFinished(actionEvent -> {getImage().setVisible(false);});
        getImage().setVisible(true);
        knifethrow.play();
    }

    @Override
    public void attack() {
        throwknife();
    }
}
