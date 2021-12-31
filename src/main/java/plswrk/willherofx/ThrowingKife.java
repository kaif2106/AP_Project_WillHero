package plswrk.willherofx;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ThrowingKife extends GameElement{

    ThrowingKife(ImageView knife_image, double x, double y) {
        super(knife_image, null, x, y);
    }
    void throwknife(){
        TranslateTransition knifethrow = new TranslateTransition(Duration.millis(1000));
        knifethrow.setNode(getImage());
        knifethrow.setByX(600);
        knifethrow.setCycleCount(1);
        knifethrow.setOnFinished(actionEvent -> {getImage().setVisible(false);});
        getImage().setVisible(true);
        knifethrow.play();
    }
}
