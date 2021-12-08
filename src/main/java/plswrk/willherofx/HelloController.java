package plswrk.willherofx;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController{
    @FXML
    private Button StartButton, LoadButton;
    @FXML
    ImageView hero;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label welcomeText;

    @FXML
    public void switchToGamePlay() throws IOException {
//        System.out.println("Switching to GamePlay");
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GamePlay.fxml")));
//        StartButton = (Button) root.lookup("#StartButton");
        stage = (Stage) StartButton.getScene().getWindow();
        scene = new Scene(root);
        HelloApplication.Gstage.setScene(scene);
        HelloApplication.Gstage.show();
        Hero hero_obj = new Hero((ImageView) root.lookup("#hero"), 1.0, 2.0);

        TranslateTransition jump = hero_obj.jump();
        TranslateTransition move = hero_obj.move(scene, jump);
//        hero = (ImageView) root.lookup("#hero");
//        jump.setNode(hero);
//        jump.setDuration(Duration.millis(600));
//        jump.setCycleCount(Animation.INDEFINITE);
//        jump.setByY(-100);
//        jump.setAutoReverse(true);
//        jump.play();
//        System.out.println("Switching to GamePlay");
//        TranslateTransition move = new TranslateTransition();
//        move.setNode(hero);
//        move.setDuration(Duration.millis(200));
//        move.setCycleCount(1);
//        move.setAutoReverse(false);
//        move.setByX(100);
//        move.setOnFinished(e->{jump.play();});

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                jump.pause();
                move.play();
            }
        });
    }

    @FXML
    public void switchToStartGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartGamePage.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}