package plswrk.willherofx;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game {

    @FXML
    AnchorPane layout;

    @FXML
    ImageView heroIV;
    //ImageView heroIV= new ImageView();

    @FXML
    Pane pauseMenuPane;

    @FXML
    ImageView pauseIV;

    @FXML
    Button gameResume;

    @FXML
    Line endLine;

    @FXML
    ImageView island1;
    @FXML
    ImageView island2;
    @FXML
    ImageView island3;

    @FXML
    Pane endPane;

//    @FXML
//    ImageView pauseIV;

    void play(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("nextScene.fxml"));
        Scene newScene = new Scene(fxmlLoader.load());
        heroIV = (ImageView) newScene.lookup("#heroIV");
        layout = (AnchorPane) newScene.lookup("#layout");
        pauseMenuPane = (Pane) newScene.lookup("#pauseMenuPane");
        endPane = (Pane) newScene.lookup("#endPane");
        pauseIV = (ImageView) newScene.lookup("#pauseIV");
        gameResume = (Button) newScene.lookup("#gameResume");
        island1 = (ImageView) newScene.lookup("#island1");
        island2 = (ImageView) newScene.lookup("#island2");
        island3 = (ImageView) newScene.lookup("#island3");
        endLine = (Line) newScene.lookup("#endLine");
        ArrayList<ImageView> islands = new ArrayList<ImageView>();
        islands.add(island1);
        islands.add(island2);
        islands.add(island3);
        pauseMenuPane.setVisible(false);
        endPane.setVisible(false);

        //AnchorPane layout = new AnchorPane();
        Image imgbg = new Image("newBG.jpg");
        BackgroundSize bgsize = new BackgroundSize(1000, 550, false, false, false, false);
        BackgroundImage bgimg= new BackgroundImage(imgbg, BackgroundRepeat.REPEAT , BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bgsize);
        Background bg = new Background(bgimg);
        layout.setBackground(bg);

        TranslateTransition jump = new TranslateTransition();
        jump.setNode(heroIV);
        jump.setDuration(Duration.millis(500));
        jump.setCycleCount(1);
        jump.setByY(-100);

        TranslateTransition fall = new TranslateTransition();
        fall.setNode(heroIV);
        fall.setDuration(Duration.millis(25));
        fall.setByY(5);
        fall.setCycleCount(1);


        fall.setOnFinished(actionEvent -> {
            boolean gameEnd = false;
            if(heroIV.getBoundsInParent().intersects(endLine.getBoundsInParent())){
                endPane.setVisible(true);
                gameEnd = true;
            }
            boolean temp = false;
            for(ImageView island : islands) {
                if (heroIV.getBoundsInParent().intersects(island.getBoundsInParent())) {
                    //fall.pause();
                    temp = true;
                    jump.play();
                }
            }
            if(!temp && !gameEnd) {
                fall.play();
            }
        });


        jump.setOnFinished(actionEvent -> {
            fall.play();
        });


        jump.play();

        for(ImageView island : islands) {
            if (heroIV.getBoundsInParent().intersects(island.getBoundsInParent())) {
                fall.pause();
                jump.play();
            }
        }

        TranslateTransition move = new TranslateTransition();
        move.setNode(heroIV);
        move.setDuration(Duration.millis(200));
        move.setCycleCount(1);
        move.setAutoReverse(false);
        move.setByX(100);
        move.setOnFinished(e->{
        jump.play();});

        newScene.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.SPACE){

                jump.pause();
                move.play();

            }
        });

        pauseIV.setOnMouseClicked(mouseEvent -> {
            jump.pause();
            fall.pause();
            pauseMenuPane.setVisible(true);
        });

        gameResume.setOnMouseClicked(mouseEvent -> {
            pauseMenuPane.setVisible(false);
            //jump.play();
            fall.play();
        });




        HelloApplication.Gstage.setScene(newScene);
        HelloApplication.Gstage.show();

    }



}
