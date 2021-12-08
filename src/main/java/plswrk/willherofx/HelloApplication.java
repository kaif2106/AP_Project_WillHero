package plswrk.willherofx;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    public static Stage Gstage;
    @FXML
    private Button StartButton, LoadButton;
    @Override
    public void start(Stage stage) throws IOException {
        Gstage = stage;
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartGamePage.fxml")));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("StartGamePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
//        Scene scene = new Scene(root);
        Gstage.setTitle("Will Hero");
        Image icon = new Image("WillHeroIcon.png");
//        HelloController hc = new HelloController();
//        StartButton = (Button) scene.lookup("#StartButton");
//        StartButton.setOnMouseClicked(event -> {
//            try {
//                hc.switchToGamePlay();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
        Gstage.getIcons().add(icon);
        Gstage.setScene(scene);
        Gstage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}