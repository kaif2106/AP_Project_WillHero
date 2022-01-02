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
//    GamePlay gamePlay;
    @FXML
    public void switchToGamePlay() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GamePlay.fxml"));
        scene = new Scene(fxmlLoader.load());
        GamePlay gamePlay = new GamePlay();
        gamePlay.InitialiseAll_FXML_Objects(scene);
        gamePlay.InitializeAll_ClassObjects();
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
            GamePlay gamePlay = (GamePlay) in.readObject();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GamePlay.fxml"));
            Parent root = fxmlLoader.load();
            GamePlay gamePlay1 = fxmlLoader.getController();
            scene = new Scene(root);
            gamePlay1.InitialiseAll_FXML_Objects(scene);
            gamePlay1.InitializeAll_ClassObjects();
            for(int i=0; i<gamePlay.getGameElements().size(); i++){
                gamePlay1.getGameElements().get(i).getImage().setLayoutX(gamePlay.getGameElements().get(i).getCurr_pos_x());
                gamePlay1.getGameElements().get(i).getImage().setLayoutY(gamePlay.getGameElements().get(i).getCurr_pos_y());
                gamePlay1.getGameElements().get(i).setCurr_pos_x(gamePlay.getGameElements().get(i).getCurr_pos_x());
                gamePlay1.getGameElements().get(i).setCurr_pos_y(gamePlay.getGameElements().get(i).getCurr_pos_y());
            }
//            gamePlay1.setGameElements(gamePlay.getGameElements());

            if(gamePlay.getHero_obj().getEquippedWeapon() == null){
                gamePlay1.getHero_obj().setEquippedWeapon(null);
                System.out.println("wnull");
            }
            else{
                gamePlay1.getHero_obj().setEquippedWeapon(gamePlay.getHero_obj().getEquippedWeapon());
            }

            if(gamePlay.getHero_obj().getEquippedAxe() == null){
                gamePlay1.getHero_obj().setEquippedAxe(null);
                System.out.println("knull");
            }
            else{
                gamePlay1.getHero_obj().setEquippedAxe(gamePlay.getHero_obj().getEquippedAxe());
            }

            if(gamePlay.getHero_obj().getEquippedKnife() == null){
                gamePlay1.getHero_obj().setEquippedKnife(null);
                System.out.println("anull");
            }
            else{
                gamePlay1.getHero_obj().setEquippedKnife(gamePlay.getHero_obj().getEquippedKnife());
            }
            //gamePlay1.getHero_obj().setEquippedAxe(gamePlay.getHero_obj().getEquippedAxe());
            //gamePlay1.getHero_obj().setEquippedKnife(gamePlay.getHero_obj().getEquippedKnife());
//            System.out.println(gamePlay.getHero_obj().getCurr_pos_y());
            gamePlay1.start(scene);
        } finally{
            assert in != null;
            in.close();
        }
    }
}