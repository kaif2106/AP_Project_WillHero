package plswrk.willherofx;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.List;

public abstract class Living extends GameElement {
    private boolean isAlive;
    private double jumpHeight;
    Living(ImageView living_imageView, List<Image> imageList, double jumpHeight, double x, double y){
        super(living_imageView,imageList, x, y);
        this.jumpHeight = jumpHeight;
        this.isAlive = true;
    }
    public abstract void on_collision(double x, double y);
    public abstract void die();
    public double getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(double jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
