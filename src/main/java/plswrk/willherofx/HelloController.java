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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController{
    @FXML
    transient private Button gameStartButton, LoadButton;
//    @FXML
//    ImageView hero, orc1;

    private Stage stage;
    private Scene scene;
    private Parent root;
    GamePlay gamePlay;
    @FXML
    public void switchToGamePlay() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GamePlay.fxml"));
        scene = new Scene(fxmlLoader.load());
        gamePlay = new GamePlay();
        gamePlay.start(scene);
    }

    @FXML
    public void switchToStartGame()throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("StartGamePage.fxml"));
        scene = new Scene(fxmlLoader.load());
        HelloApplication.Gstage.setScene(scene);
        HelloApplication.Gstage.show();
    }

    @FXML
    public void load() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream (new FileInputStream("out.txt"));
            gamePlay = (GamePlay) in.readObject();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GamePlay.fxml"));
            Parent root = fxmlLoader.load();
            GamePlay gamePlay1 = fxmlLoader.getController();
//            if(gamePlay1 == null) {
//                System.out.println("GamePlay is null");
//            }
//            else{
//                System.out.println("GamePlay is not null");
//            }
//            System.out.println(fxmlLoader.getController()));
            gamePlay1.setGameElements(gamePlay.getGameElements());
            gamePlay1.setHero_obj(gamePlay.getHero_obj());
            scene = new Scene(root);
            gamePlay.start(scene);
        } finally{
            in.close();
        }
    }
}