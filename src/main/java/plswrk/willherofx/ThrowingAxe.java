package plswrk.willherofx;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ThrowingAxe extends GameElement {
    ThrowingAxe(ImageView axe_Image, double x, double y){
        super(axe_Image, null, x, y);
    }

    void throwAxe(){
        TranslateTransition axeMove = new TranslateTransition();
        axeMove.setNode(getImage());
        axeMove.setDuration(Duration.millis(1000));
        axeMove.setCycleCount(1);
        axeMove.setByX(300);
        TranslateTransition axeMoveBack = new TranslateTransition();
        axeMoveBack.setNode(getImage());
        axeMoveBack.setDuration(Duration.millis(1000));
        axeMoveBack.setCycleCount(1);
        axeMoveBack.setByX(-300);
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
