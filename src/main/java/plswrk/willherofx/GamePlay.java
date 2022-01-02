package plswrk.willherofx;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class GamePlay implements Serializable {
    @FXML
    transient AnchorPane layout;

    @FXML
    transient Pane pauseMenuPane, endPane;

    @FXML
    transient ImageView pause, pauseMenu, axeImg, hero, orc1, island1, island2, island3, island4, island5, weapon_chest1, coin_chest1, TNT1, t1,t2,t3,t4, orc2, axeIV, knifeImage, weapon_chest2;

    @FXML
    transient Button resume, restart_pause, restart_end, exit_pause, exit_end, save;

    @FXML
    transient Polygon dash;

    @FXML
    transient Label moveCounter;

    @FXML
    transient ImageView island6, island7, island8, island9, island10, island11, island12;

    @FXML
    transient ImageView orc3, orc4, orc5, orc6, boss, weapon_chest3, weapon_chest4;

    @FXML
    transient ProgressBar healthBar;





    private ArrayList<GameElement> gameElements;
    private ArrayList<Island> islands;
    private ArrayList<Weapon_Chest> weapon_chests = new ArrayList<>();
    private Hero hero_obj;
    private ArrayList<Orc> orcList;
//    private Orc orc_obj;
//    private Orc orc2_obj;
    private Weapon_Chest weapon_chest_obj;
    private Weapon_Chest weapon_chest_obj2;
    private Weapon_Chest weapon_chest_obj3;
    private Weapon_Chest weapon_chest_obj4;
    private Coin_Chest coin_chest_obj;
    private TNT TNT_obj;
    transient private ImageView knifeIV;
    transient private ImageView asd = new ImageView();
    private int mc = 0;
    private ArrayList<ThrowingKife> knives = new ArrayList<>();
    private WeaponAbs equippedKnife;
    private WeaponAbs equippedAxe;
    private Boss boss_obj;
    //private WeaponAbs equippedWeapon;
    //private ImageView newKnifeIV = new ImageView();

    public void InitialiseAll_FXML_Objects(Scene scene) {
        hero = (ImageView) scene.lookup("#hero");
        dash = (Polygon) scene.lookup("#dash");
        axeImg = (ImageView) scene.lookup("#axeImg");
        orc1 = (ImageView) scene.lookup("#orc1");
        orc2 = (ImageView) scene.lookup("#orc2");
        healthBar = (ProgressBar) scene.lookup("#healthBar");
        knifeIV = (ImageView) scene.lookup("#knifeImage");
        weapon_chest2 = (ImageView) scene.lookup("#weapon_chest2");
        weapon_chest3 = (ImageView) scene.lookup("#weapon_chest3");
        weapon_chest4 = (ImageView) scene.lookup("#weapon_chest4");
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
        island6 = (ImageView) scene.lookup("#island6");
        island7 = (ImageView) scene.lookup("#island7");
        island8 = (ImageView) scene.lookup("#island8");
        island9 = (ImageView) scene.lookup("#island9");
        island10 = (ImageView) scene.lookup("#island10");
        island11 = (ImageView) scene.lookup("#island11");
        island12 = (ImageView) scene.lookup("#island12");

        orc3 = (ImageView) scene.lookup("#orc3");
        orc4 = (ImageView) scene.lookup("#orc4");
        orc5 = (ImageView) scene.lookup("#orc5");
        orc6 = (ImageView) scene.lookup("#orc6");
        //orc7 = (ImageView) scene.lookup("#orc7");
        boss = (ImageView) scene.lookup("#boss");

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
        healthBar.setVisible(false);
        healthBar.setProgress(1);
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
        Island island6_obj = new Island(island6, island6.getLayoutX(), island6.getLayoutY());
        Island island7_obj = new Island(island7, island7.getLayoutX(), island7.getLayoutY());
        Island island8_obj = new Island(island8, island8.getLayoutX(), island8.getLayoutY());
        Island island9_obj = new Island(island9, island9.getLayoutX(), island9.getLayoutY());
        Island island10_obj = new Island(island10, island10.getLayoutX(), island10.getLayoutY());
        Island island11_obj = new Island(island11, island11.getLayoutX(), island11.getLayoutY());
        Island island12_obj = new Island(island12, island12.getLayoutX(), island12.getLayoutY());
        islands = new ArrayList<>();
        islands.add(island1_obj);
        islands.add(island2_obj);
        islands.add(island3_obj);
        islands.add(island4_obj);
        islands.add(island5_obj);
        islands.add(island6_obj);
        islands.add(island7_obj);
        islands.add(island8_obj);
        islands.add(island9_obj);
        islands.add(island10_obj);
        islands.add(island11_obj);
        islands.add(island12_obj);
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
        weapon_chest_obj3 = new Weapon_Chest(weapon_chest3, Weapon_chest_image_list, new ThrowingAxe(axeIV), weapon_chest3.getLayoutX(), weapon_chest3.getLayoutY());
        weapon_chest_obj4 = new Weapon_Chest(weapon_chest4, Weapon_chest_image_list, new ThrowingKife(knifeIV), weapon_chest4.getLayoutX(), weapon_chest4.getLayoutY());
        //weapon_chest_obj = new Weapon_Chest(weapon_chest1, Weapon_chest_image_list, new ThrowingAxe(axeIV), weapon_chest1.getLayoutX(), weapon_chest1.getLayoutY());
        //weapon_chest_obj = new Weapon_Chest(weapon_chest1, Weapon_chest_image_list, new ThrowingAxe(axeIV), weapon_chest1.getLayoutX(), weapon_chest1.getLayoutY());
        weapon_chests.add(weapon_chest_obj);
        weapon_chests.add(weapon_chest_obj2);
        weapon_chests.add(weapon_chest_obj3);
        weapon_chests.add(weapon_chest_obj4);
        List<Image> orc_deathImages = new ArrayList<>();
        for(int i=1; i<=4; i++) {
            orc_deathImages.add(new Image(String.format("orcDeath%s.png", i)));
        }

        Orc orc_obj = new Orc(orc1, orc_deathImages, 100, 0, orc1.getLayoutX(), orc1.getLayoutY());
        Orc orc2_obj = new Orc(orc2, orc_deathImages, 100, 0, orc2.getLayoutX(), orc2.getLayoutY());
        Orc orc3_obj = new Orc(orc3, orc_deathImages, 100, 0, orc3.getLayoutX(), orc3.getLayoutY());
        Orc orc4_obj = new Orc(orc4, orc_deathImages, 100, 0, orc4.getLayoutX(), orc4.getLayoutY());
        Orc orc5_obj = new Orc(orc5, orc_deathImages, 100, 0, orc5.getLayoutX(), orc5.getLayoutY());
        Orc orc6_obj = new Orc(orc6, orc_deathImages, 100, 0, orc6.getLayoutX(), orc6.getLayoutY());
        //Orc orc7_obj = new Orc(orc7, orc_deathImages, 100, 0, orc7.getLayoutX(), orc7.getLayoutY());
        orcList = new ArrayList<>();
        orcList.add(orc_obj);
        orcList.add(orc2_obj);
        orcList.add(orc3_obj);
        orcList.add(orc4_obj);
        orcList.add(orc5_obj);
        orcList.add(orc6_obj);
        //orcList.add(orc7_obj);

        boss_obj = new Boss(boss, orc_deathImages, 100, 0, boss.getLayoutX(), boss.getLayoutY());

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

        //healthBar = new ProgressBar();

        gameElements = new ArrayList<>();

        gameElements.addAll(islands);
        gameElements.addAll(trees);
        gameElements.add(TNT_obj);
        gameElements.add(weapon_chest_obj);
        gameElements.add(weapon_chest_obj2);
        gameElements.add(weapon_chest_obj3);
        gameElements.add(weapon_chest_obj4);
        gameElements.add(coin_chest_obj);
        gameElements.addAll(orcList);
        gameElements.add(boss_obj);
    }

    public ImageView newKnife(int yDelta){
        ImageView newKnifeIV = new ImageView();
        int knifeLevelImage = hero_obj.getEquippedWeapon().getLevel();

        newKnifeIV.setImage(new Image(String.format("ThrowingKnife%s.png", knifeLevelImage+1)));
        newKnifeIV.setX(hero_obj.getImage().getBoundsInParent().getMaxX()+45);
        newKnifeIV.setY(hero_obj.getCurr_pos_y()-yDelta); //-15
        newKnifeIV.setFitWidth(12);
        newKnifeIV.setFitHeight(60);
        newKnifeIV.setRotate(newKnifeIV.getRotate()+90);
        newKnifeIV.setVisible(false);
        return newKnifeIV;
    }

    public void start(Scene scene) {
        System.out.println(hero_obj.isAlive());
        Pair<TranslateTransition, TranslateTransition> hero_hop = hop(hero_obj);
        Pair<TranslateTransition, TranslateTransition> boss_hop = hop(boss_obj);
        boss_hop.getFirst().play();
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

                if(!TNT_obj.isExploded())
                    TNT_obj.on_collision(hero_obj);

                if(TNT_obj.inDeadly() && hero_obj.getImage().getBoundsInParent().intersects(TNT_obj.getImage().getBoundsInParent())){
                    hero_obj.die();
                }

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

                if(hero_obj.getImage().getBoundsInParent().getMaxX()+1000 >= boss_obj.getImage().getBoundsInParent().getMinX()){
                    healthBar.setVisible(true);
                }

                if(hero_obj.getEquippedAxe()!=null){
                    axeImg.setVisible(true);
                }
                if(hero_obj.getEquippedKnife()!=null){
                    knifeIV.setVisible(true);
                }
                boss_obj.on_collision(hero_obj);

                if(axeIV.isVisible() && axeIV.getBoundsInParent().intersects(boss_obj.getImage().getBoundsInParent())){
                    boss_obj.hit(25);
                    healthBar.setProgress(healthBar.getProgress()-0.25);
                    axeIV.setVisible(false);
                }

                for(Orc orc : orcList) {
                    if(orc.isAlive()) {
                        orc.on_collision(hero_obj);
                        for(Orc otherOrc : orcList){
                            if(otherOrc!=orc){
                                orc.on_collision(otherOrc);
                            }
                        }
                        if(axeIV.isVisible() && axeIV.getBoundsInParent().intersects(orc.getImage().getBoundsInParent())){
                            orc.die();
                        }

                        for (int i = 0; i < knives.size(); i++) {
                            if (!knives.get(i).getImage().isVisible()) {
                                knives.remove(i);
                            } else if (knives.get(i).getImage().isVisible() && knives.get(i).getImage().getBoundsInParent().intersects(orc.getImage().getBoundsInParent())) {
                                orc.die();
                            }
                            else if(knives.get(i).getImage().isVisible() && knives.get(i).getImage().getBoundsInParent().intersects(boss_obj.getImage().getBoundsInParent())){
                                boss_obj.hit(15);
                                healthBar.setProgress(healthBar.getProgress()-0.15);
                                knives.get(i).getImage().setVisible(false);
                            }
                        }
                    }
                }
                for (int i = 0; i < knives.size(); i++) {
                    if (!knives.get(i).getImage().isVisible()) {
                        knives.remove(i);
                    }
                    else if(knives.get(i).getImage().isVisible() && knives.get(i).getImage().getBoundsInParent().intersects(boss_obj.getImage().getBoundsInParent())){
                        boss_obj.hit(15);
                        healthBar.setProgress(healthBar.getProgress()-15);
                        knives.get(i).getImage().setVisible(false);
                    }
                }
            }
        };
        timer.start();

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE && hero_obj.isAlive()) {
                moveCounter.setText(Integer.toString(++mc));
                pressedKeys.add(e.getText());

                if(hero_obj.getEquippedAxe()!=null && hero_obj.getEquippedAxe().getLevel()==2){
                    Image upImg = new Image("ThrowingAxe2.png");
                    axeImg.setImage(upImg);
                }
                if(hero_obj.getEquippedKnife()!=null && hero_obj.getEquippedKnife().getLevel()==2){
                    Image upImg = new Image("ThrowingKnife3.png");
                    knifeIV.setImage(upImg);
                }


                if(hero_obj.getEquippedWeapon()!=null){
                    if(hero_obj.getEquippedWeapon() instanceof ThrowingKife){

                        asd = newKnife(10);
                        layout.getChildren().add(asd);
                        ThrowingKife knife = new ThrowingKife(asd);
                        knives.add(knife);
                        knife.throwknife();
                        if(hero_obj.getEquippedWeapon().getLevel()==2){
                            asd = newKnife(30);
                            layout.getChildren().add(asd);
                            ThrowingKife Secondknife = new ThrowingKife(asd);
                            knives.add(Secondknife);
                            Secondknife.throwknife();
                        }
                    }
                    else {
                        if(!axeIV.isVisible()) {
                            axeIV.setX(hero_obj.getImage().getBoundsInParent().getMaxX() - 100);
                            axeIV.setY(hero_obj.getImage().getBoundsInParent().getCenterY() - 150);
                            axeIV.setFitWidth(25);
                            axeIV.setFitHeight(90);
                            axeIV.setVisible(true);
                            ThrowingAxe axe = new ThrowingAxe(axeIV);
                            if(hero_obj.getEquippedWeapon().getLevel()==2){
                                axe.upgradeImage();
                                axe.throwAxe(300);
                            }
                            else {
                                axe.throwAxe(200);
                            }
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
            if(e.getCode() == KeyCode.DIGIT1){
                hero_obj.setEquippedWeapon(hero_obj.getEquippedKnife());
            }
            if(e.getCode() == KeyCode.DIGIT2){
                hero_obj.setEquippedWeapon(hero_obj.getEquippedAxe());
            }
            if(e.getCode() == KeyCode.DIGIT3){
                hero_obj.setEquippedWeapon(null);
            }
        });

        save.setOnMouseClicked(e -> {
            TextInputDialog gamename = new TextInputDialog();
            gamename.setTitle("Save Game");
            gamename.setHeaderText("Enter a name for your game");
            gamename.showAndWait();
            String name = gamename.getEditor().getText();
            if(this.getHero_obj()==null){
                System.out.println("No hero");
            }
            try {
                System.out.println(hero_obj.getCurr_pos_y());
                HelloApplication.serialize(name, this);
            }catch(Exception exception){
                exception.printStackTrace();
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
        TranslateTransition jump = new TranslateTransition(Duration.millis(0.1), character.getImage());
        jump.setByY(-2.5);
        jump.setCycleCount(1);
        jump.setAutoReverse(false);
        TranslateTransition fall = new TranslateTransition(Duration.millis(0.1), character.getImage());
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
                if(character.getCurr_pos_y() <= 950)
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

    public void setGameElements(ArrayList<GameElement> gameElements) {
        this.gameElements = gameElements;
    }

    public void setIslands(ArrayList<Island> islands) {
        this.islands = islands;
    }

    public void setWeapon_chests(ArrayList<Weapon_Chest> weapon_chests) {
        this.weapon_chests = weapon_chests;
    }

    public void setHero_obj(Hero hero_obj) {
        this.hero_obj = hero_obj;
    }

    public void setOrcList(ArrayList<Orc> orcList) {
        this.orcList = orcList;
    }

//    public void setOrc_obj(Orc orc_obj) {
//        this.orc_obj = orc_obj;
//    }
//
//    public void setOrc2_obj(Orc orc2_obj) {
//        this.orc2_obj = orc2_obj;
//    }

    public void setWeapon_chest_obj(Weapon_Chest weapon_chest_obj) {
        this.weapon_chest_obj = weapon_chest_obj;
    }

    public void setWeapon_chest_obj2(Weapon_Chest weapon_chest_obj2) {
        this.weapon_chest_obj2 = weapon_chest_obj2;
    }

    public void setCoin_chest_obj(Coin_Chest coin_chest_obj) {
        this.coin_chest_obj = coin_chest_obj;
    }

    public void setTNT_obj(TNT TNT_obj) {
        this.TNT_obj = TNT_obj;
    }

    public void setKnifeImage(ImageView knifeImage) {
        this.knifeImage = knifeImage;
    }

    public void setAsd(ImageView asd) {
        this.asd = asd;
    }

    public void setMc(int mc) {
        this.mc = mc;
    }

    public void setKnives(ArrayList<ThrowingKife> knives) {
        this.knives = knives;
    }

//    public void setEquippedWeapon(WeaponAbs equippedWeapon) {
//        this.equippedWeapon = equippedWeapon;
//    }

    public ArrayList<GameElement> getGameElements() {
        return gameElements;
    }

    public ArrayList<Island> getIslands() {
        return islands;
    }

    public ArrayList<Weapon_Chest> getWeapon_chests() {
        return weapon_chests;
    }

    public Hero getHero_obj() {
        return hero_obj;
    }

    public ArrayList<Orc> getOrcList() {
        return orcList;
    }

//    public Orc getOrc_obj() {
//        return orc_obj;
//    }
//
//    public Orc getOrc2_obj() {
//        return orc2_obj;
//    }

    public Weapon_Chest getWeapon_chest_obj() {
        return weapon_chest_obj;
    }

    public Weapon_Chest getWeapon_chest_obj2() {
        return weapon_chest_obj2;
    }

    public Coin_Chest getCoin_chest_obj() {
        return coin_chest_obj;
    }

    public TNT getTNT_obj() {
        return TNT_obj;
    }

    public ImageView getKnifeImage() {
        return knifeImage;
    }

    public ImageView getAsd() {
        return asd;
    }

    public int getMc() {
        return mc;
    }

    public ArrayList<ThrowingKife> getKnives() {
        return knives;
    }

//    public WeaponAbs getEquippedWeapon() {
//        return equippedWeapon;
//    }
}