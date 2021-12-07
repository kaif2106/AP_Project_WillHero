package plswrk.willherofx;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Game {


    //private Stage stage;


    void play(Stage stage) throws IOException {


        AnchorPane layout = new AnchorPane();
        Image background_image = new Image("newBG.jpg");
        BackgroundSize bgSize = new BackgroundSize(1000, 550, false, false, false, false);
        BackgroundImage bgImg= new BackgroundImage(background_image, BackgroundRepeat.REPEAT , BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bgSize);
        Background bg = new Background(bgImg);
        layout.setBackground(bg);

//        Image islandIMG = new Image("island.png");
        ImageView islandIV = new ImageView("island.png");
//        islandIV.setImage(islandIMG);

        islandIV.setLayoutY(211);
        islandIV.setLayoutX(66);
        islandIV.setFitHeight(285);
        islandIV.setFitWidth(328);

//        Hero hero = new Hero(new Location(202, 314), "hero.png", 1, 10/55, 1, 1);
//        Image heroIMG = new Image("hero.png");
        ImageView heroIV = new ImageView("hero.png");
//        heroIV.setImage(heroIMG);

        heroIV.setLayoutX(202);
        heroIV.setLayoutY(314);
        heroIV.setFitHeight(32);
        heroIV.setFitWidth(45);


        ImageView island2 = new ImageView("island.png");

        island2.setLayoutY(250);
        island2.setLayoutX(505);
        island2.setFitHeight(285);
        island2.setFitWidth(328);

        ImageView island3 = new ImageView("island.png");

        island3.setLayoutY(214);
        island3.setLayoutX(921);
        island3.setFitHeight(285);
        island3.setFitWidth(328);



        layout.getChildren().addAll(islandIV, heroIV, island2, island3);
        Scene newScene = new Scene(layout, 1000,550);

        TranslateTransition jump = new TranslateTransition();
        jump.setNode(heroIV);
        jump.setDuration(Duration.millis(550));
        jump.setCycleCount(Animation.INDEFINITE);
        jump.setByY(-100);
        jump.setAutoReverse(true);
        jump.play();

        TranslateTransition move = new TranslateTransition();
        move.setNode(heroIV);
        move.setDuration(Duration.millis(100));
        move.setCycleCount(1);
        move.setAutoReverse(false);
        move.setByX(100);
        move.setOnFinished(e-> jump.play());

        newScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                jump.pause();
                move.play();
            }
        });
        HelloApplication.Gstage.setScene(newScene);
        HelloApplication.Gstage.show();
    }
}
