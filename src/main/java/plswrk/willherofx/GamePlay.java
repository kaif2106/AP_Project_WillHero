package plswrk.willherofx;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

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
    ImageView pause, pauseMenu, axeImg;

    @FXML
    Button resume, restart_pause, restart_end, exit_pause, exit_end, save;

    @FXML
    ImageView hero, orc1, island1, island2, island3, island4, island5, weapon_chest1, coin_chest1, TNT1, knifeIV;

    @FXML
    ImageView t1,t2,t3,t4, orc2, axeIV, weapon_chest2;

    @FXML
    Pane endPane;

    @FXML
    Polygon dash;

    @FXML
    Label moveCounter;



    private ArrayList<GameElement> gameElements;
    private ArrayList<Island> islands;
    private ArrayList<Weapon_Chest> weapon_chests = new ArrayList<Weapon_Chest>();
    private Hero hero_obj;
    private ArrayList<Orc> orcList;
    private Orc orc_obj;
    private Orc orc2_obj;
    private Weapon_Chest weapon_chest_obj;
    private Weapon_Chest weapon_chest_obj2;
    private Coin_Chest coin_chest_obj;
    private TNT TNT_obj;
    private Image knifeImage = new Image("ThrowingKnife2.png");
    private ImageView asd = new ImageView();
    private int mc = 0;
    private ArrayList<ThrowingKife> knives = new ArrayList<ThrowingKife>();
    private WeaponAbs equippedWeapon;
    //private ImageView newKnifeIV = new ImageView();

    public void InitialiseAll_FXML_Objects(Scene scene) {
        hero = (ImageView) scene.lookup("#hero");
        dash = (Polygon) scene.lookup("#dash");
        axeImg = (ImageView) scene.lookup("#axeImg");
        orc1 = (ImageView) scene.lookup("#orc1");
        orc2 = (ImageView) scene.lookup("#orc2");
        knifeIV = (ImageView) scene.lookup("#knifeImage");
        weapon_chest2 = (ImageView) scene.lookup("#weapon_chest2");
        axeIV = (ImageView) scene.lookup("#axeIV");
        weapon_chest1 = (ImageView) scene.lookup("#weapon_chest1");
        coin_chest1 = (ImageView) scene.lookup("#coin_chest1");
        TNT1 = (ImageView) scene.lookup("#TNT1");
        layout = (AnchorPane) scene.lookup("#layout");
        pauseMenuPane = (Pane) scene.lookup("#pauseMenuPane");
        endPane = (Pane) scene.lookup("#endPane");
        pause = (ImageView) scene.lookup("#pause");
        resume = (Button) scene.lookup("#resume");
        moveCounter  = (Label) scene.lookup("#moveCounter");
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
        axeIV.setVisible(false);
        axeImg.setVisible(false);
        knifeIV.setVisible(false);
        pauseMenuPane.setVisible(false);
        endPane.setVisible(false);
        moveCounter.setText(Integer.toString(mc));
        BackgroundSize backgroundSize = new BackgroundSize(1238, 694, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("newBG.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background bg = new Background(backgroundImage);
        layout.setBackground(bg);
    }
    public void InitializeAll_ClassObjects() {
        hero_obj = new Hero(hero, null, 150, 100,hero.getLayoutX(), hero.getLayoutY());

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
        ArrayList<Tree> trees = new ArrayList<>();
        trees.add(tree1_obj);
        trees.add(tree2_obj);
        trees.add(tree3_obj);
        trees.add(tree4_obj);

        List<Image> Weapon_chest_image_list = new ArrayList<>();
        for(int i=1; i<=11; i++){
            Weapon_chest_image_list.add(new Image(String.format("coin%s.png", i)));
        }
        //ArrayList<Weapon_Chest> weapon_chests = new ArrayList<Weapon_Chest>();
        weapon_chest_obj = new Weapon_Chest(weapon_chest1, Weapon_chest_image_list, new ThrowingAxe(axeIV), weapon_chest1.getLayoutX(), weapon_chest1.getLayoutY());
        weapon_chest_obj2 = new Weapon_Chest(weapon_chest2, Weapon_chest_image_list, new ThrowingKife(knifeIV), weapon_chest1.getLayoutX(), weapon_chest1.getLayoutY());
        //weapon_chest_obj = new Weapon_Chest(weapon_chest1, Weapon_chest_image_list, new ThrowingAxe(axeIV), weapon_chest1.getLayoutX(), weapon_chest1.getLayoutY());
        //weapon_chest_obj = new Weapon_Chest(weapon_chest1, Weapon_chest_image_list, new ThrowingAxe(axeIV), weapon_chest1.getLayoutX(), weapon_chest1.getLayoutY());
        weapon_chests.add(weapon_chest_obj);
        weapon_chests.add(weapon_chest_obj2);
        List<Image> orc_deathImages = new ArrayList<>();
        for(int i=1; i<=4; i++) {
            orc_deathImages.add(new Image(String.format("orcDeath%s.png", i)));
        }

        orc_obj = new Orc(orc1, orc_deathImages, 100, 0, orc1.getLayoutX(), orc1.getLayoutY());
        orc2_obj = new Orc(orc2, orc_deathImages, 100, 0, orc2.getLayoutX(), orc2.getLayoutY());
        orcList = new ArrayList<Orc>();
        orcList.add(orc_obj);
        orcList.add(orc2_obj);

        List<Image> Coin_chest_List = new ArrayList<>();
        for(int i=1; i<=11; i++) {
            Coin_chest_List.add(new Image(String.format("wep%s.png", i)));
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
        gameElements.add(weapon_chest_obj2);
        gameElements.add(coin_chest_obj);
        gameElements.add(orc2_obj);

    }

    public ImageView newKnife(){
        ImageView newKnifeIV = new ImageView();
        newKnifeIV.setImage(knifeImage);
        newKnifeIV.setX(hero_obj.getImage().getBoundsInParent().getMaxX()+45);
        newKnifeIV.setY(hero_obj.getCurr_pos_y()-15);
        newKnifeIV.setFitWidth(12);
        newKnifeIV.setFitHeight(60);
        newKnifeIV.setRotate(newKnifeIV.getRotate()+90);
        newKnifeIV.setVisible(false);
        return newKnifeIV;
    }

    public void start(Scene scene) {
        InitialiseAll_FXML_Objects(scene);
        InitializeAll_ClassObjects();
        Pair<TranslateTransition, TranslateTransition> hero_hop = hop(hero_obj);
        ArrayList<Pair<TranslateTransition, TranslateTransition>> orcHops = new ArrayList<>();
        for(Orc orc : orcList)
            orcHops.add(hop(orc));
        hero_hop.getFirst().play();
        for(Pair<TranslateTransition, TranslateTransition> orcHop : orcHops)
            orcHop.getFirst().play();

        ArrayList<String> pressedKeys = new ArrayList<>();
        dash.setLayoutX(hero_obj.getImage().getBoundsInParent().getMinX()-40);


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                coin_chest_obj.on_collision(hero_obj);
                for(Weapon_Chest weapon_chest_obj : weapon_chests)
                    weapon_chest_obj.on_collision(hero_obj);
                TNT_obj.on_collision(hero_obj);
                if(!hero_obj.isAlive()){
                    if (hero_hop.getFirst().getStatus() == Animation.Status.RUNNING) {
                        hero_hop.getFirst().pause();
                    }
                    if (hero_hop.getSecond().getStatus() == Animation.Status.RUNNING) {
                        hero_hop.getSecond().pause();
                    }
                }
                if(hero_obj.getImage().getBoundsInParent().getMinY()>=750){
                    endPane.setVisible(true);
                }

                for(Weapon_Chest weapon_chest_obj : weapon_chests) {

                    if (weapon_chest_obj.getIsOpen()) {
                        //knifeIV.setVisible(true);
                        //weapon_chest_obj.getWeapon().getImage().setVisible(true);
                        equippedWeapon = weapon_chest_obj.getWeapon();
                        if (weapon_chest_obj.getWeapon() instanceof ThrowingAxe) {
                            axeImg.setVisible(true);

                        } else {
                            knifeIV.setVisible(true);

                        }

                    }
                }

                for(Orc orc : orcList) {
                    if(orc.isAlive()) {
                        orc.on_collision(hero_obj);
                        if (axeIV.getBoundsInParent().intersects(orc.getImage().getBoundsInParent())) {
                            orc.die();
                        }
                        for (int i = 0; i < knives.size(); i++) {
                            if (!knives.get(i).getImage().isVisible()) {
                                knives.remove(i);
                                continue;
                            } else if (knives.get(i).getImage().isVisible() && knives.get(i).getImage().getBoundsInParent().intersects(orc.getImage().getBoundsInParent()))
                                orc.die();
                        }
                    }
                }

            }
        };
        timer.start();

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                moveCounter.setText(Integer.toString(++mc));
                pressedKeys.add(e.getText());
                //System.out.println(pressedKeys.size());

                //if (weapon_chest_obj.getIsOpen()) {
                if(equippedWeapon!=null){
                    //if(weapon_chest_obj.getWeapon() instanceof ThrowingKife) {
                    if(equippedWeapon instanceof ThrowingKife){
                        asd = newKnife();
                        layout.getChildren().add(asd);
                        ThrowingKife knife = new ThrowingKife(asd);
                        knives.add(knife);
                        knife.throwknife();
                    }
                    else {
                        if(!axeIV.isVisible()) {
                            axeIV.setX(hero_obj.getImage().getBoundsInParent().getMaxX() - 100);
                            axeIV.setY(hero_obj.getImage().getBoundsInParent().getCenterY() - 150);
                            axeIV.setFitWidth(25);
                            axeIV.setFitHeight(90);
                            axeIV.setVisible(true);
                            ThrowingAxe axe = new ThrowingAxe(axeIV);
                            axe.throwAxe();
                        }
                    }
                }



                if (hero_hop.getFirst().getStatus() == Animation.Status.RUNNING) {
                    hero_hop.getFirst().pause();
                }
                if (hero_hop.getSecond().getStatus() == Animation.Status.RUNNING) {
                    hero_hop.getSecond().pause();
                }
                dash.setLayoutY(hero_obj.getImage().getBoundsInParent().getCenterY() - 40);
                dash.setVisible(true);

                for (GameElement gameElement : gameElements) {
                    gameElement.translateLeft(0, pressedKeys, hero_hop, dash).play();
                }
            }
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
            for(Pair<TranslateTransition, TranslateTransition> orcHop : orcHops){
                if(orcHop.getFirst().getStatus()== Animation.Status.RUNNING) {
                    orcHop.getFirst().pause();
                }
                if(orcHop.getSecond().getStatus()== Animation.Status.RUNNING) {
                    orcHop.getSecond().pause();
                }
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

            for(Pair<TranslateTransition, TranslateTransition> orcHop : orcHops){
                if(orcHop.getFirst().getStatus()== Animation.Status.PAUSED) {
                    orcHop.getFirst().play();
                }
                if(orcHop.getSecond().getStatus()== Animation.Status.PAUSED) {
                    orcHop.getSecond().play();
                }
            }
        });

        exit_pause.setOnMouseClicked(mouseEvent -> {
            try {
                new HelloApplication().start(HelloApplication.Gstage);
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
            try {
                new HelloApplication().start(HelloApplication.Gstage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        exit_end.setOnMouseClicked(mouseEvent -> {
            try {
                new HelloApplication().start(HelloApplication.Gstage);
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

    public Pair<TranslateTransition, TranslateTransition> hop(Living character) {
        TranslateTransition jump = new TranslateTransition(Duration.millis(0.3), character.getImage());
        jump.setByY(-2.5);
        jump.setCycleCount(1);
        jump.setAutoReverse(false);
        TranslateTransition fall = new TranslateTransition(Duration.millis(0.3), character.getImage());
        fall.setByY(2.5);
        fall.setCycleCount(1);
        jump.setOnFinished(event->{
            if(character.getyDistMoved()< character.getJumpHeight()){
                character.setCurr_pos_y(character.getCurr_pos_y()-2.5);
                character.setyDistMoved(character.getyDistMoved()+2.5);
                jump.play();
            }
            else{
                character.setCurr_pos_y(character.getCurr_pos_y()-2.5);
                character.setyDistMoved(0);
                fall.play();
            }
        });


        fall.setOnFinished(actionEvent -> {
            character.setCurr_pos_y(character.getCurr_pos_y()+2.5);
            if(!character.isAlive()){
                if(character.getCurr_pos_y() <= 750)
                    fall.play();
                return;
            }
            boolean gameEnd = false;
            if(character == hero_obj){
                if (character.getCurr_pos_y() + character.getImage().getFitHeight() >= 750) {
                    endPane.setVisible(true);
                    gameEnd = true;
                }
            }
            boolean temp = false;
            Island targetIsland = null;
            for(Island island : islands){
                if((island.getImage().getBoundsInParent().getMaxX()) >= character.getImage().getBoundsInParent().getMinX() && island.getImage().getBoundsInParent().getMinX() <= (character.getImage().getBoundsInParent().getMaxX())){
                    targetIsland = island;
                    break;
                }
            }
            if(targetIsland != null){
                if((targetIsland.getCurr_pos_y() > character.getCurr_pos_y()) &&
                        ((character.getCurr_pos_y() + character.getImage().getFitHeight()) <= (targetIsland.getCurr_pos_y()+2.5)) &&
                        (targetIsland.getCurr_pos_y() <= (character.getCurr_pos_y() + character.getImage().getFitHeight()))){
                    fall.pause();
                    temp = true;
                    jump.play();
                }
            }
            if(!temp && !gameEnd){
                fall.play();
            }
        });

        return new Pair<>(jump, fall);
    }

}