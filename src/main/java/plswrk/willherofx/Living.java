package plswrk.willherofx;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.List;

public abstract class Living extends GameElement {
    private boolean isAlive;
    private double jumpHeight;
    private final double moveDist;
    private double xDistMoved;
    private double yDistMoved;
    Living(ImageView living_imageView, List<Image> imageList, double jumpHeight, double moveDist, double x, double y){
        super(living_imageView,imageList, x, y);
        this.jumpHeight = jumpHeight;
        this.moveDist = moveDist;
        this.isAlive = true;
        this.xDistMoved = 0;
        this.yDistMoved = 0;
    }
    public abstract void on_collision(Hero hero_obj);
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

    public double getMoveDist() {
        return moveDist;
    }

    public double getxDistMoved() {
        return xDistMoved;
    }

    public void setxDistMoved(double xDistMoved) {
        this.xDistMoved = xDistMoved;
    }

    public double getyDistMoved() {
        return yDistMoved;
    }

    public void setyDistMoved(double yDistMoved) {
        this.yDistMoved = yDistMoved;
    }
}
