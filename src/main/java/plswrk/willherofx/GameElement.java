package plswrk.willherofx;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class GameElement implements Serializable {
    transient private final ImageView imageView;
    transient private final List<Image> imageList;
    private double curr_pos_x;
    private double curr_pos_y;
    private final double moveDist;
    private double xDistMoved;
    GameElement(ImageView imageView, List<Image>imageList, double start_pos_x, double start_pos_y) {
        this.imageView = imageView;
        this.imageList = imageList;
        this.curr_pos_x = start_pos_x;
        this.curr_pos_y = start_pos_y;
        this.moveDist = 100;
        this.xDistMoved=0;
    }

    public TranslateTransition translateLeft(int moveCount, ArrayList<String> pressedKeys, Pair<TranslateTransition, TranslateTransition> hero_hop, Polygon dash){
        TranslateTransition move = new TranslateTransition();
        move.setNode(imageView);
        move.setDuration(Duration.millis(0.2));
        move.setCycleCount(1);
        move.setAutoReverse(false);
        move.setByX(-5);
        move.setOnFinished(e -> {
            if(moveCount==19){
                this.setCurr_pos_x(this.getCurr_pos_x() - 5);
                pressedKeys.clear();
                if (hero_hop.getFirst().getStatus() == Animation.Status.PAUSED) {
                    hero_hop.getFirst().play();
                }
                if (hero_hop.getSecond().getStatus() == Animation.Status.PAUSED) {
                    hero_hop.getSecond().play();
                }
                dash.setVisible(false);
            }
            else{
                this.setCurr_pos_x(this.getCurr_pos_x() - 5);
                translateLeft(moveCount+1, pressedKeys, hero_hop, dash).play();
            }
        });
        //move.play();
        return move;
    }

    public ImageView getImage() {
        return imageView;
    }

    public double getCurr_pos_x() {
        return curr_pos_x;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public double getCurr_pos_y() {
        return curr_pos_y;
    }

    public void setCurr_pos_x(double curr_pos_x) {
        this.curr_pos_x = curr_pos_x;
    }

    public void setCurr_pos_y(double curr_pos_y) {
        this.curr_pos_y = curr_pos_y;
    }

    public void setxDistMoved(double xDistMoved) {
        this.xDistMoved = xDistMoved;
    }

    public double getxDistMoved() {
        return xDistMoved;
    }

    public double getMoveDist() {
        return moveDist;
    }
}