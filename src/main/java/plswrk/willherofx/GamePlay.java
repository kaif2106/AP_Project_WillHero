package plswrk.willherofx;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class GamePlay {
    @FXML
    AnchorPane layout;

    @FXML
    Pane pauseMenuPane;

    @FXML
    ImageView pause;

    @FXML
    Button resume;

    @FXML
    ImageView hero, orc1, island1, island2, island3;

    @FXML
    Pane endPane;

    private ArrayList<Island> islands;
    private Hero hero_obj;
    private Orc orc_obj;


    public void initialise(Scene scene) {
        hero = (ImageView) scene.lookup("#hero");
        orc1 = (ImageView) scene.lookup("#orc1");
        hero_obj = new Hero(hero, 1.0, 2.0, hero.getLayoutX(), hero.getLayoutY());
        orc_obj = new Orc(orc1, 20, orc1.getLayoutX(), orc1.getLayoutY());
        layout = (AnchorPane) scene.lookup("#layout");
        pauseMenuPane = (Pane) scene.lookup("#pauseMenuPane");
        endPane = (Pane) scene.lookup("#endPane");
        pause = (ImageView) scene.lookup("#pause");
        resume = (Button) scene.lookup("#resume");
        island1 = (ImageView) scene.lookup("#island1");
        island2 = (ImageView) scene.lookup("#island2");
        island3 = (ImageView) scene.lookup("#island3");
        Island island1_obj = new Island(island1,island1.getLayoutX(), island1.getLayoutY());
        Island island2_obj = new Island(island2,island2.getLayoutX(), island2.getLayoutY());
        Island island3_obj = new Island(island3,island3.getLayoutX(), island3.getLayoutY());
        islands = new ArrayList<>();
        islands.add(island1_obj);
        islands.add(island2_obj);
        islands.add(island3_obj);
        pauseMenuPane.setVisible(false);
        endPane.setVisible(false);
        Image imgbg = new Image("newBG.jpg");
        BackgroundSize bgsize = new BackgroundSize(1000, 550, false, false, false, false);
        BackgroundImage bgimg= new BackgroundImage(imgbg, BackgroundRepeat.REPEAT , BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bgsize);
        Background bg = new Background(bgimg);
        layout.setBackground(bg);
    }
    public void start(Scene scene) throws IOException {
        initialise(scene);

//        Weapon_Chest weapon_chest_obj = new Weapon_Chest((ImageView) root.lookup("#weapon_chest1"), new Weapon("Sword", 0, 30.0, new Range(3,3)));
        Pair<TranslateTransition, TranslateTransition> hero_hop = hop(hero_obj);
        Pair<TranslateTransition, TranslateTransition> orc_hop = hop(orc_obj);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
//                hero_hop.getFirst().pause();
//                hero_hop.getSecond().pause();
                hero_obj.move(scene, hero_hop.getFirst()).play();
            }
        });

        orc_obj.getOrc_image().setOnMouseClicked(e -> {
            orc_hop.getFirst().pause();
            orc_hop.getSecond().pause();
            orc_obj.die();
        });

        pause.setOnMouseClicked(mouseEvent -> {
            hero_hop.getFirst().pause();
            hero_hop.getSecond().pause();
            orc_hop.getFirst().pause();
            orc_hop.getSecond().pause();
            pauseMenuPane.setVisible(true);
        });

        resume.setOnMouseClicked(mouseEvent -> {
            pauseMenuPane.setVisible(false);
            hero_hop.getFirst().pause();
            hero_hop.getSecond().pause();
            orc_hop.getFirst().pause();
            orc_hop.getSecond().pause();
//            fall.play();
        });
//        weapon_chest_obj.getWeapon_chest_image().setOnMouseClicked(e -> {
//
//        });
        HelloApplication.Gstage.setScene(scene);
        HelloApplication.Gstage.show();
    }
    public Pair<TranslateTransition, TranslateTransition> hop(Living character) {
        ImageView character_image = character.getImage();
        TranslateTransition jump = new TranslateTransition(Duration.millis(520), character_image);
        jump.setByY((-1)*character.getJumpHeight());
        jump.setCycleCount(1);
        jump.setAutoReverse(false);

//        jump.getNode().setLayoutY(character_image.getLayoutY() - character.getJumpHeight());
//        jump.play();

        TranslateTransition fall = new TranslateTransition(Duration.millis(25), character_image);
        fall.setByY(5);
        fall.setCycleCount(1);
//        fall.getNode().setLayoutY(character_image.getLayoutY() + 5);
        fall.setOnFinished(actionEvent -> {
//            character_image.setLayoutY(5);
            character.setCurr_pos_y(character.getCurr_pos_y() + 5);
            boolean gameEnd = false;
            if(character.getCurr_pos_y() + character_image.getFitHeight() >=600){
                endPane.setVisible(true);
                gameEnd = true;
            }
            boolean temp = false;
            Island targetIsland = null;
            for(Island island : islands){
                if((island.getCurr_pos_x() + island.getImage().getFitWidth()) >= character.getCurr_pos_x() && island.getCurr_pos_x() <= (character.getCurr_pos_x() + character_image.getFitWidth())){
                    targetIsland = island;
                    break;
                }
            }
            if(targetIsland != null){
                if(targetIsland.getCurr_pos_y() > character.getCurr_pos_y() && (targetIsland.getCurr_pos_y()) <= (character.getCurr_pos_y() + character_image.getFitHeight())){
                    fall.pause();
                    temp = true;
                    jump.play();
                }
            }
//            for(ImageView island : islands) {
////                if(character_image== hero_obj.getImage())
//                    System.out.println(character_image.getLayoutX() + " " + island.getLayoutX());
//                boolean land_condition = (character_image.getLayoutX()+character_image.getFitWidth())>=island.getLayoutX()
//                        && character_image.getLayoutX()<(island.getLayoutX()+island.getFitWidth())
//                        && (character_image.getLayoutY()+character_image.getFitHeight())>=island.getLayoutY() && (character_image.getLayoutY()+character_image.getFitHeight())<=(island.getLayoutY() +3);
//                if(land_condition){
////                if (character_image.getBoundsInParent().intersects(island.getBoundsInParent())) {
//                    fall.pause();
//                    temp = true;
//                    jump.play();
//                }
//            }
            if(!temp && !gameEnd) {
                fall.play();
            }
        });

        jump.setOnFinished(actionEvent -> {
//            character_image.setLayoutY((-1)*character.getJumpHeight());
            character.setCurr_pos_y(character.getCurr_pos_y() - character.getJumpHeight());
            fall.play();
        });

        jump.play();

//        for(ImageView island : islands) {
////            boolean land_condition = character_image.getLayoutX()>island.getLayoutX()
////                    && character_image.getBoundsInParent().getMinX()<island.getLayoutX()
////                    && character_image.getBoundsInParent().getMaxY()>=island.getBoundsInParent().getMinY() && character_image.getBoundsInParent().getMaxY()<=island.getBoundsInParent().getMinY()+3;
//            boolean land_condition = (character_image.getLayoutX()+character_image.getFitWidth())>=island.getLayoutX()
//                    && character_image.getLayoutX()<(island.getLayoutX()+island.getFitWidth())
//                    && (character_image.getLayoutY()+character_image.getFitHeight())>=island.getLayoutY() && (character_image.getLayoutY()+character_image.getFitHeight())<=(island.getLayoutY() +3);
//            if (land_condition) {
//                fall.pause();
//                jump.play();
//            }
//        }
        return new Pair<>(jump, fall);
    }


}
