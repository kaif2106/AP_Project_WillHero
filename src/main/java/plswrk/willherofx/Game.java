package plswrk.willherofx;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Game {


    //private Stage stage;


    void play(Stage stage) throws IOException {

        AnchorPane layout = new AnchorPane();
        Image imgbg = new Image("newBG.jpg");
        BackgroundSize bgsize = new BackgroundSize(1000, 550, false, false, false, false);
        BackgroundImage bgimg= new BackgroundImage(imgbg, BackgroundRepeat.REPEAT , BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bgsize);
        Background bg = new Background(bgimg);
        layout.setBackground(bg);

        Image islandIMG = new Image("island.png");
        ImageView islandIV = new ImageView();
        islandIV.setImage(islandIMG);

        islandIV.setLayoutY(211);
        islandIV.setLayoutX(66);
        islandIV.setFitHeight(285);
        islandIV.setFitWidth(328);

        Image heroIMG = new Image("hero.png");
        ImageView heroIV = new ImageView();
        heroIV.setImage(heroIMG);

        heroIV.setLayoutX(202);
        heroIV.setLayoutY(314);
        heroIV.setFitHeight(32);
        heroIV.setFitWidth(45);


        ImageView island2 = new ImageView();
        island2.setImage(islandIMG);

        island2.setLayoutY(250);
        island2.setLayoutX(505);
        island2.setFitHeight(285);
        island2.setFitWidth(328);

        ImageView island3 = new ImageView();
        island3.setImage(islandIMG);

        island3.setLayoutY(214);
        island3.setLayoutX(921);
        island3.setFitHeight(285);
        island3.setFitWidth(328);



        layout.getChildren().addAll(islandIV, heroIV, island2, island3);
        Scene newScene = new Scene(layout, 1000,550);

        TranslateTransition jump = new TranslateTransition();
        jump.setNode(heroIV);
        jump.setDuration(Duration.millis(600));
        jump.setCycleCount(Animation.INDEFINITE);
        jump.setByY(-100);
        jump.setAutoReverse(true);
        jump.play();

        TranslateTransition move = new TranslateTransition();
        move.setNode(heroIV);
        move.setDuration(Duration.millis(200));
        move.setCycleCount(1);
        move.setAutoReverse(false);
        move.setByX(100);
        move.setOnFinished(e->{jump.play();});

        newScene.setOnMouseClicked(mouseEvent -> {
            jump.pause();
            move.play();
        });

        HelloApplication.Gstage.setScene(newScene);
        HelloApplication.Gstage.show();





    }
}
