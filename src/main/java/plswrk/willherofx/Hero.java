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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class Hero extends GameElement{
    private ImageView hero_image;
    private double velocityX;
    private double velocityY;
    Hero(ImageView hero_image, double velocityX, double velocityY) {
        this.hero_image = hero_image;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }
    //    public void update(double time)
//    {
//        this.setLocation(this.getLocation().getX()+velocityX * time, this.getLocation().getY()+ velocityY * time);
//    }
//    @FXML
    public TranslateTransition jump() throws IOException {
        TranslateTransition jump = new TranslateTransition();
        jump.setNode(hero_image);
        jump.setDuration(Duration.millis(600));
        jump.setCycleCount(1);
        jump.setByY(-100);

//        jump.setAutoReverse(true);
        jump.play();

        return jump;
    }
//
//        stage.setScene(scene);
//        stage.show();
//        TranslateTransition jump = new TranslateTransition();
//        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        jump.setNode(imageView);
//        jump.setDuration(Duration.millis(600));
//        jump.setCycleCount(Animation.INDEFINITE);
//        jump.setByY(-100);
//        jump.setAutoReverse(true);
//        jump.play();
//    public ImageView getImageView() {
//        return imageView;
//    }

    public TranslateTransition move(Scene scene, TranslateTransition jump){
        TranslateTransition move = new TranslateTransition();
        move.setNode(hero_image);
        move.setDuration(Duration.millis(200));
        move.setCycleCount(1);
        move.setAutoReverse(false);
        move.setByX(100);
        move.setOnFinished(e->{jump.play();});
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                jump.pause();
                move.play();
            }
        });
        return move;
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
