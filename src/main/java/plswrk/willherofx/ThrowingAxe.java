package plswrk.willherofx;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ThrowingAxe extends WeaponAbs {
    ThrowingAxe(ImageView axe_Image){
        super(axe_Image);
    }

    @Override
    public void attack() {
    }

    void upgradeImage(){
        Image newImage = new Image("ThrowingAxe2.png");
        getImage().setImage(newImage);
    }

    void throwAxe(int distance){
        TranslateTransition axeMove = new TranslateTransition();
        axeMove.setNode(getImage());
        axeMove.setDuration(Duration.millis(1000));
        axeMove.setCycleCount(1);
        axeMove.setByX(distance);
        TranslateTransition axeMoveBack = new TranslateTransition();
        axeMoveBack.setNode(getImage());
        axeMoveBack.setDuration(Duration.millis(1000));
        axeMoveBack.setCycleCount(1);
        axeMoveBack.setByX(-distance);
        axeMoveBack.setOnFinished(actionEvent -> getImage().setVisible(false));
        RotateTransition axeRotate = new RotateTransition();
        axeRotate.setNode(getImage());
        axeRotate.setDuration(Duration.millis(200));
        axeRotate.setCycleCount(10);
        axeRotate.setByAngle(360);
        axeMove.setOnFinished(actionEvent -> axeMoveBack.play());
        axeMove.play();
        axeRotate.play();
    }

}
