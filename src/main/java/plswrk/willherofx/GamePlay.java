package plswrk.willherofx;

import javafx.animation.Animation;
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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class GamePlay {
    @FXML
    AnchorPane layout;

    @FXML
    Pane pauseMenuPane;

    @FXML
    ImageView pause;

    @FXML
    Button resume, restart;

    @FXML
    ImageView hero, orc1, island1, island2, island3, weapon_chest1;

    @FXML
    Pane endPane;

    private ArrayList<Island> islands;
    private Hero hero_obj;
    private Orc orc_obj;
    private Weapon_Chest weapon_chest_obj;

    public void initialise(Scene scene) {
        hero = (ImageView) scene.lookup("#hero");
        orc1 = (ImageView) scene.lookup("#orc1");
        weapon_chest1 = (ImageView) scene.lookup("#weapon_chest1");
        hero_obj = new Hero(hero, 1.0, 2.0, hero.getLayoutX(), hero.getLayoutY());
        orc_obj = new Orc(orc1, 20, orc1.getLayoutX(), orc1.getLayoutY());
        layout = (AnchorPane) scene.lookup("#layout");
        pauseMenuPane = (Pane) scene.lookup("#pauseMenuPane");
        endPane = (Pane) scene.lookup("#endPane");
        pause = (ImageView) scene.lookup("#pause");
        resume = (Button) scene.lookup("#resume");
        restart = (Button) scene.lookup("#restart");
        island1 = (ImageView) scene.lookup("#island1");
        island2 = (ImageView) scene.lookup("#island2");
        island3 = (ImageView) scene.lookup("#island3");
        Island island1_obj = new Island(island1,island1.getLayoutX(), island1.getLayoutY());
        Island island2_obj = new Island(island2,island2.getLayoutX(), island2.getLayoutY());
        Island island3_obj = new Island(island3,island3.getLayoutX(), island3.getLayoutY());
        Image image1 = new Image("wep_0000 #50076.png");
        Image image2 = new Image("wep_0001 #18659.png");
        Image image3 = new Image("wep_0002 #18442.png");
        Image image4 = new Image("wep_0003.png");
        Image image5 = new Image("wep_0004 #36957.png");
        Image image6 = new Image("wep_0005 #37946.png");
        Image image7 = new Image("wep_0006 #42713.png");
        Image image8 = new Image("wep_0007 #45764.png");
        Image image9 = new Image("wep_0008 #32756.png");
        Image image10 = new Image("wep_0009 #50124.png");
        Image image11 = new Image("wep_0010 #21871.png");
        List<Image> imageList = new ArrayList<>();
        imageList.add(image1);
        imageList.add(image2);
        imageList.add(image3);
        imageList.add(image4);
        imageList.add(image5);
        imageList.add(image6);
        imageList.add(image7);
        imageList.add(image8);
        imageList.add(image9);
        imageList.add(image10);
        imageList.add(image11);
        weapon_chest_obj = new Weapon_Chest(imageList, new Weapon("Sword", 0, 30.0, new Range(3,3)), weapon_chest1.getLayoutX(), weapon_chest1.getLayoutY());
        weapon_chest_obj.getImage().setImage(image1);
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
        boolean isjumping = true;
        boolean islanding = false;
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
//            System.out.println(hero_obj.getCurr_pos_y() + " " + hero_obj.getImage().getLayoutY());
            if(hero_hop.getFirst().getStatus()== Animation.Status.RUNNING) {
                hero_hop.getFirst().pause();
            }
            if(hero_hop.getSecond().getStatus()== Animation.Status.RUNNING) {
                hero_hop.getSecond().pause();
            }
            if(orc_hop.getFirst().getStatus()== Animation.Status.RUNNING) {
                orc_hop.getFirst().pause();
            }
            if(orc_hop.getSecond().getStatus()== Animation.Status.RUNNING) {
                orc_hop.getSecond().pause();
            }
            pauseMenuPane.setVisible(true);
        });

        resume.setOnMouseClicked(mouseEvent -> {
//            System.out.println(hero_obj.getCurr_pos_y() + " " + hero_obj.getImage().getLayoutY());
            pauseMenuPane.setVisible(false);
            if(hero_hop.getFirst().getStatus()== Animation.Status.PAUSED) {
                hero_hop.getFirst().play();
            }
            if(hero_hop.getSecond().getStatus()== Animation.Status.PAUSED) {
                hero_hop.getSecond().play();
            }
            if(orc_hop.getFirst().getStatus()== Animation.Status.PAUSED) {
                orc_hop.getFirst().play();
            }
            if(orc_hop.getSecond().getStatus()== Animation.Status.PAUSED) {
                orc_hop.getSecond().play();
            }
//            fall.play();
        });

        restart.setOnMouseClicked(mouseEvent -> {
            HelloController controller = new HelloController();
                    try {
                        controller.switchToGamePlay();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        weapon_chest_obj.getImage().setOnMouseClicked(e -> {
            try {
                weapon_chest_obj.open();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
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
                if(targetIsland.getCurr_pos_y() > character.getCurr_pos_y() && targetIsland.getCurr_pos_y() <= (character.getCurr_pos_y() + character_image.getFitHeight())){
                    fall.pause();
                    temp = true;
                    jump.play();
                    System.out.println();

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
