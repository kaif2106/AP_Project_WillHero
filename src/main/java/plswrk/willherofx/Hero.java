package plswrk.willherofx;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class Hero extends Living{

    private double velocityX;
    private double velocityY;
    private boolean Alive;
    Hero(ImageView hero_image, List<Image> imageList , double jumpHeight, double moveDist, double x, double y) {
        super(hero_image, imageList, jumpHeight, moveDist, x, y);
        Alive = true;
    }

    private TranslateTransition dieAni = new TranslateTransition();
    private RotateTransition dieRot = new RotateTransition();


    public boolean isAlive(){
        return Alive;
    }

    @Override
    public void on_collision(Hero hero_obj) {

    }

    @Override
    public void die() {
        Alive = false;
        dieAni.setNode(getImage());
        dieAni.setDuration(Duration.millis(3000));
        dieAni.setCycleCount(1);
        dieAni.setByY(500);
        dieRot.setNode(getImage());
        dieRot.setDuration(Duration.millis(500));
        dieRot.setCycleCount(6);
        dieRot.setByAngle(360);
        Image dieImage = new Image("orcDeath4.png");
        this.getImage().setImage(dieImage);
        dieAni.play();
        dieRot.play();
    }
}
