package plswrk.willherofx;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
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
    private String Loadfile;
    @FXML
    private ListView<String> listView;
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
            listView = (ListView<String>) HelloApplication.Gstage.getScene().lookup("#savedGamesList");
            listView.setVisible(true);
            listView.setOpacity(1);
            ArrayList<String> savedGames = new ArrayList<>();
            File[] source = new File("src/main/resources/SavedGames").listFiles();
            assert source != null;
            for(File f : source){
                savedGames.add(f.getName());
            }
            listView.getItems().addAll(savedGames);
            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                    Loadfile = listView.getSelectionModel().getSelectedItem();
//                myLabel.setText(currentFood);
                }
            });
            System.out.println(Loadfile);
            in = new ObjectInputStream (new FileInputStream("G:\\Group_25\\src\\main\\resources\\SavedGames\\"+Loadfile));
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

            gamePlay1.getHero_obj().setEquippedWeapon(gamePlay.getHero_obj().getEquippedWeapon());
            gamePlay1.getHero_obj().setEquippedAxe(gamePlay.getHero_obj().getEquippedAxe());
            gamePlay1.getHero_obj().setEquippedKnife(gamePlay.getHero_obj().getEquippedKnife());
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