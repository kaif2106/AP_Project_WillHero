package plswrk.willherofx;

import javafx.animation.Animation;
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
    Hero(ImageView hero_image, List<Image> imageList , double jumpHeight, double velocityX, double velocityY, double x, double y) {
        super(hero_image, imageList, jumpHeight, x, y);
        this.velocityX = velocityX;
        this.velocityY = velocityY;

    }



    @Override
    public void on_collision() {

    }

    @Override
    public void die() {
        try {
            this.getImage().setImage(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResource("/MonsterFatality.png")).toURI().toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public double getVelocityX() {
        return velocityX;
    }
    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

}
