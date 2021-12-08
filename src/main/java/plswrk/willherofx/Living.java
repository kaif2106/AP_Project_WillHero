package plswrk.willherofx;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;

import java.io.IOException;

public abstract class Living extends GameElement {
    private boolean isAlive;
    private double jumpHeight;
    Living(ImageView living_image, double jumpHeight, double x, double y){
        super(living_image, x, y);
        this.jumpHeight = jumpHeight;
        this.isAlive = true;
    }
    public abstract void on_collision();
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
