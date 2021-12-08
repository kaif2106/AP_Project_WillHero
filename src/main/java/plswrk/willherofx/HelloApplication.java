package plswrk.willherofx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;




public class HelloApplication extends Application {
    public static Stage Gstage;



    @Override
    public void start(Stage stage) throws IOException {
        Gstage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Gstage.setTitle("Will Hero");
        Image icon = new Image("icon.png");
        Gstage.getIcons().add(icon);
        Gstage.setScene(scene);
        Gstage.show();
    }

    @FXML
    public Button gameStartButton;

    @FXML
    void change() throws IOException {
        Game game = new Game();
        game.play(Gstage);
    }

    public static void main(String[] args) {
        launch();
    }
}