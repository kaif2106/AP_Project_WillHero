package plswrk.willherofx;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class Hero extends Living{
    private double velocityX;
    private double velocityY;
    private int coinsInt;
    private WeaponAbs equippedWeapon;
    private ThrowingAxe equippedAxe;
    private ThrowingKife equippedKnife;
    private long deltaTime;
    private long totalTime;
    Hero(ImageView hero_image, List<Image> imageList , double jumpHeight, double moveDist, double x, double y) {
        super(hero_image, imageList, jumpHeight, moveDist, x, y);
        coinsInt = 0;
        equippedWeapon = null;
        equippedAxe=null;
        equippedKnife=null;
        deltaTime = 0;
        totalTime=0;
    }

    transient private TranslateTransition dieAni = new TranslateTransition();
    transient private RotateTransition dieRot = new RotateTransition();

    int getCoins(){
        return coinsInt;
    }
    void setCoins(int x){
        coinsInt = x;
    }


    public WeaponAbs getEquippedWeapon(){
        return equippedWeapon;
    }

    public void setEquippedWeapon(WeaponAbs equippedWeapon){
        this.equippedWeapon = equippedWeapon;
    }

    public ThrowingKife getEquippedKnife(){
        return equippedKnife;
    }

    public void setEquippedKnife(ThrowingKife equippedKnife){
        this.equippedKnife = equippedKnife;
    }
    public ThrowingAxe getEquippedAxe(){
        return equippedAxe;
    }
    public void setEquippedAxe(ThrowingAxe equippedAxe){
        this.equippedAxe = equippedAxe;
    }

    @Override
    public void on_collision(Living character) {

    }

    @Override
    public void die() {
        setAlive(false);
        dieAni.setNode(getImage());
        dieAni.setDuration(Duration.millis(3000));
        dieAni.setCycleCount(1);
        dieAni.setByY(500);
        dieRot.setNode(getImage());
        dieRot.setDuration(Duration.millis(500));
        dieRot.setCycleCount(6);
        dieRot.setByAngle(360);
        Image dieImage = new Image("orcDeath4.png");
        this.getImage().setImage(dieImage);
        dieAni.play();
        dieRot.play();
    }

    public long getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(long deltaTime) {
        this.deltaTime = deltaTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }
}
