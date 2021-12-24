package plswrk.willherofx;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
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
    ImageView pause, pauseMenu;

    @FXML
    Button resume, restart_pause, restart_end, exit_pause, exit_end, save;

    @FXML
    ImageView hero, orc1, island1, island2, island3, island4, island5, weapon_chest1, coin_chest1, TNT1;

    @FXML
    ImageView t1,t2,t3,t4;

    @FXML
    Pane endPane;

    @FXML
    Polygon dash;


    private ArrayList<GameElement> gameElements;
    private ArrayList<Island> islands;
    private ArrayList<Tree> trees;
    private Hero hero_obj;
    private Orc orc_obj;
    private Weapon_Chest weapon_chest_obj;
    private Coin_Chest coin_chest_obj;
    private TNT TNT_obj;

    public void InitialiseAll_FXML_Objects(Scene scene) {
        hero = (ImageView) scene.lookup("#hero");
        dash = (Polygon) scene.lookup("#dash");
        orc1 = (ImageView) scene.lookup("#orc1");
        weapon_chest1 = (ImageView) scene.lookup("#weapon_chest1");
        coin_chest1 = (ImageView) scene.lookup("#coin_chest1");
        TNT1 = (ImageView) scene.lookup("#TNT1");
        layout = (AnchorPane) scene.lookup("#layout");
        pauseMenuPane = (Pane) scene.lookup("#pauseMenuPane");
        endPane = (Pane) scene.lookup("#endPane");
        pause = (ImageView) scene.lookup("#pause");
        resume = (Button) scene.lookup("#resume");
        HelloApplication.setEffect(resume);
        restart_pause = (Button) scene.lookup("#restart_pause");
        HelloApplication.setEffect(restart_pause);
        restart_end = (Button) scene.lookup("#restart_end");
        HelloApplication.setEffect(restart_end);
        exit_pause = (Button) scene.lookup("#exit_pause");
        HelloApplication.setEffect(exit_pause);
        exit_end = (Button) scene.lookup("#exit_end");
        HelloApplication.setEffect(exit_end);
        save = (Button) scene.lookup("#save");
        HelloApplication.setEffect(save);

        island1 = (ImageView) scene.lookup("#island1");
        island2 = (ImageView) scene.lookup("#island2");
        island3 = (ImageView) scene.lookup("#island3");
        island4 = (ImageView) scene.lookup("#island4");
        island5 = (ImageView) scene.lookup("#island5");

        t1 = (ImageView) scene.lookup("#t1");
        t2 = (ImageView) scene.lookup("#t2");
        t3 = (ImageView) scene.lookup("#t3");
        t4 = (ImageView) scene.lookup("#t4");

        dash.setVisible(false);
        pauseMenuPane.setVisible(false);
        endPane.setVisible(false);
        BackgroundSize backgroundSize = new BackgroundSize(1238, 694, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("newBG.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background bg = new Background(backgroundImage);
        layout.setBackground(bg);
    }
    public void InitializeAll_ClassObjects() {
        hero_obj = new Hero(hero, null, 100, 1.0, 2.0, hero.getLayoutX(), hero.getLayoutY());

        Island island1_obj = new Island(island1, island1.getLayoutX(), island1.getLayoutY());
        Island island2_obj = new Island(island2, island2.getLayoutX(), island2.getLayoutY());
        Island island3_obj = new Island(island3, island3.getLayoutX(), island3.getLayoutY());
        Island island4_obj = new Island(island4, island4.getLayoutX(), island4.getLayoutY());
        Island island5_obj = new Island(island5, island5.getLayoutX(), island5.getLayoutY());
        islands = new ArrayList<>();
        islands.add(island1_obj);
        islands.add(island2_obj);
        islands.add(island3_obj);
        islands.add(island4_obj);
        islands.add(island5_obj);

        Tree tree1_obj = new Tree(t1, t1.getLayoutX(), t1.getLayoutY());
        Tree tree2_obj = new Tree(t2, t2.getLayoutX(), t2.getLayoutY());
        Tree tree3_obj = new Tree(t3, t3.getLayoutX(), t3.getLayoutY());
        Tree tree4_obj = new Tree(t4, t4.getLayoutX(), t4.getLayoutY());
        trees = new ArrayList<>();
        trees.add(tree1_obj);
        trees.add(tree2_obj);
        trees.add(tree3_obj);
        trees.add(tree4_obj);

        List<Image> Weapon_chest_List = new ArrayList<>();
        for(int i=1; i<=11; i++){
            Weapon_chest_List.add(new Image(String.format("wep%s.png", i)));
        }
        weapon_chest_obj = new Weapon_Chest(weapon_chest1, Weapon_chest_List, new Weapon("Sword", 0, 30.0, new Range(3, 3)), weapon_chest1.getLayoutX(), weapon_chest1.getLayoutY());

        List<Image> orc_deathImages = new ArrayList<>();
        for(int i=1; i<=4; i++) {
            orc_deathImages.add(new Image(String.format("orcDeath%s.png", i)));
        }

        orc_obj = new Orc(orc1, orc_deathImages, 100, orc1.getLayoutX(), orc1.getLayoutY());

        List<Image> Coin_chest_List = new ArrayList<>();
        for(int i=1; i<=11; i++) {
            Coin_chest_List.add(new Image(String.format("coin%s.png", i)));
        }
        coin_chest_obj = new Coin_Chest(coin_chest1, Coin_chest_List, 50, coin_chest1.getLayoutX(), coin_chest1.getLayoutY());

        List<Image> TNT_explodeImages = new ArrayList<>();

        for(int i = 1; i<=5; i++){
            TNT_explodeImages.add(new Image(String.format("TNT_explode%s.png", i)));
        }

        for(int i = 1; i<=18; i++){
            TNT_explodeImages.add(new Image(String.format("TNT_explosion%s.png", i)));
        }
        TNT_obj = new TNT(TNT1, TNT_explodeImages, TNT1.getLayoutX(), TNT1.getLayoutY(), new Range(30, 30));


        gameElements = new ArrayList<>();

        gameElements.addAll(islands);
        gameElements.addAll(trees);
        gameElements.add(TNT_obj);
        gameElements.add(orc_obj);
        gameElements.add(weapon_chest_obj);
        gameElements.add(coin_chest_obj);
        gameElements.add(hero_obj);

    }
    public void start(Scene scene) {
        InitialiseAll_FXML_Objects(scene);
        InitializeAll_ClassObjects();
        Pair<TranslateTransition, TranslateTransition> hero_hop = hop(hero_obj);
        Pair<TranslateTransition, TranslateTransition> orc_hop = hop(orc_obj);
        ArrayList<String> pressedKeys = new ArrayList<String>();
        int moveCount = 0;

        scene.setOnKeyPressed(e -> {
//            System.out.println("herox: "+ hero_obj.getCurr_pos_x());
            if (e.getCode() == KeyCode.SPACE) {
                if(!pressedKeys.contains(e.getText())) {
                    pressedKeys.add(e.getText());

                    if (hero_hop.getFirst().getStatus() == Animation.Status.RUNNING) {
                        hero_hop.getFirst().pause();
                    }
                    if (hero_hop.getSecond().getStatus() == Animation.Status.RUNNING) {
                        hero_hop.getSecond().pause();
                    }
                    TranslateTransition hero_mov = move(hero_obj, 100);
                    dash.setLayoutY(hero_obj.getImage().getBoundsInParent().getCenterY() - 40);
                    dash.setVisible(true);
                    hero_mov.play();

                    coin_chest_obj.on_collision(hero_obj.getCurr_pos_x(), hero_obj.getCurr_pos_y());
                    weapon_chest_obj.on_collision(hero_obj.getCurr_pos_x(), hero_obj.getCurr_pos_y());
                    TNT_obj.on_collision(hero_obj.getCurr_pos_x(), hero_obj.getCurr_pos_y());

                    moveDashfn(200, 1);

                    hero_mov.setOnFinished(event -> {

                        if (hero_hop.getFirst().getStatus() == Animation.Status.PAUSED) {
                            hero_hop.getFirst().play();
                        }
                        if (hero_hop.getSecond().getStatus() == Animation.Status.PAUSED) {
                            hero_hop.getSecond().play();
                        }
                        TranslateTransition eh = hero_obj.translateLeft(0, pressedKeys);
                        eh.setOnFinished(someEvent -> {
                            pressedKeys.clear();
                        });
                        eh.play();
                        for (GameElement gameElement : gameElements) {
                            gameElement.translateLeft(0, pressedKeys).play();
                        }
                        moveDashfn(5, -1);

                    });
                }
            }
        });

        orc_obj.getImage().setOnMouseClicked(e -> {
            orc_hop.getFirst().pause();
            orc_hop.getSecond().pause();
            orc_obj.die();
        });
        Effect original_effect = pause.getEffect();
        pause.setOnMouseEntered(event -> {
            DropShadow shadow = new DropShadow(20, Color.BLACK);
            shadow.setWidth(32.68);
            shadow.setHeight(32.68);
            pause.setEffect(shadow);
        });
        pause.setOnMouseExited(event -> {
            pause.setEffect(original_effect);
        });
        pause.setOnMouseClicked(mouseEvent -> {
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
        });

        exit_pause.setOnMouseClicked(mouseEvent -> {
//            HelloController controller = new HelloController();
            try {
                new HelloApplication().start(HelloApplication.Gstage);
//                controller.switchToStartGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        restart_pause.setOnMouseClicked(mouseEvent -> {
            HelloController controller = new HelloController();
            try {
                controller.switchToGamePlay();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        restart_end.setOnMouseClicked(mouseEvent -> {
//            HelloController controller = new HelloController();
            try {
                new HelloApplication().start(HelloApplication.Gstage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        exit_end.setOnMouseClicked(mouseEvent -> {
//            HelloController controller = new HelloController();
            try {
                new HelloApplication().start(HelloApplication.Gstage);
//                controller.switchToStartGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        weapon_chest_obj.getImage().setOnMouseClicked(mouseEvent -> {
            try {
                weapon_chest_obj.open();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        });

        coin_chest_obj.getImage().setOnMouseClicked(mouseEvent -> {
            try {
                coin_chest_obj.open();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        });

        TNT_obj.getImage().setOnMouseClicked(mouseEvent -> {
            TNT_obj.explode();
        });

        HelloApplication.Gstage.setScene(scene);
        HelloApplication.Gstage.show();
    }

    public TranslateTransition jumpfn(int moveCount, Living character, TranslateTransition fall){
        ImageView character_image = character.getImage();
        TranslateTransition jump = new TranslateTransition(Duration.millis(26), character_image);
        jump.setByY(((-1)*character.getJumpHeight())/20);
        jump.setCycleCount(1);
        jump.setAutoReverse(false);
        character.setCurr_pos_y(character.getCurr_pos_y() - (character.getJumpHeight()/20));
        jump.setOnFinished(e -> {
            if(moveCount==19){
                fall.play();
                //pressedKeys.clear();
            }
            else{
                jumpfn(moveCount+1, character, fall).play();
                //move.play();
            }
        });
        //move.play();
        return jump;
    }

    public Pair<TranslateTransition, TranslateTransition> hop(Living character) {

        ImageView character_image = character.getImage();
//        TranslateTransition jump = new TranslateTransition(Duration.millis(520/20), character_image);
//        jump.setByY(((-1)*character.getJumpHeight())/20);
//        jump.setCycleCount(1);
//        jump.setAutoReverse(false);


        TranslateTransition fall = new TranslateTransition(Duration.millis(25), character_image);
        fall.setByY(5);
        fall.setCycleCount(1);
        fall.setOnFinished(actionEvent -> {
            character.setCurr_pos_y(character.getCurr_pos_y() + 5);
            //if(character instanceof Hero)
                //System.out.println("heroY: "+Double.toString(character.getCurr_pos_y()));
            boolean gameEnd = false;
            if(character == hero_obj){
                if (character.getCurr_pos_y() + character_image.getFitHeight() >= 750) {
                    endPane.setVisible(true);
                    gameEnd = true;
                }
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
                if((targetIsland.getCurr_pos_y() > character.getCurr_pos_y()) &&
                        ((character.getCurr_pos_y() + character_image.getFitHeight()) <= (targetIsland.getCurr_pos_y()+3)) &&
                        (targetIsland.getCurr_pos_y() <= (character.getCurr_pos_y() + character_image.getFitHeight()))){
                    fall.pause();
                    temp = true;
                    //jump.play();
                    jumpfn(0, character, fall).play();
                }
            }
            if(!temp && !gameEnd) {
                fall.play();
            }
        });



//        jump.setOnFinished(actionEvent -> {
//
//            fall.play();
//        });
        TranslateTransition jump = jumpfn(0, character, fall);
        jump.play();

        return new Pair<>(jump, fall);
    }

    public void moveDashfn(double dur, int state){
        TranslateTransition moveDash = new TranslateTransition();
        moveDash.setNode(dash);
        moveDash.setDuration(Duration.millis(dur));
        moveDash.setCycleCount(1);
        moveDash.setAutoReverse(false);
        moveDash.setByX(100*state);
        moveDash.setOnFinished(event -> {dash.setVisible(false);});
        moveDash.play();
    }

    public TranslateTransition move(Living character, double x) {
        ImageView character_image = character.getImage();
        TranslateTransition move = new TranslateTransition();
        move.setNode(character_image);
        move.setDuration(Duration.millis(200));
        move.setCycleCount(1);
        move.setAutoReverse(false);
        character.setCurr_pos_x(character.getCurr_pos_x() + x);
        move.setByX(x);
        return move;
    }
}