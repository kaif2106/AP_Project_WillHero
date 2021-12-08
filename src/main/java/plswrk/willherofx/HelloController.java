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
//    @FXML
//    ImageView hero, orc1;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void switchToGamePlay() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GamePlay.fxml"));
        scene = new Scene(fxmlLoader.load());
        HelloApplication.Gstage.setScene(scene);
        HelloApplication.Gstage.show();
        GamePlay gamePlay = new GamePlay();
        gamePlay.start(scene);
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