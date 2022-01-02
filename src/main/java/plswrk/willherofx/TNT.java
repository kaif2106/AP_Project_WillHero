package plswrk.willherofx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.security.Key;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class TNT extends GameElement implements Obstacle {
    private final Range range;
    private boolean exploded;
    private boolean deadly;
    TNT(ImageView imageView, List<Image> imageList, double start_pos_x, double start_pos_y, Range range) {
        super(imageView, imageList, start_pos_x, start_pos_y);
        this.range = range;
        exploded = false;
        deadly = false;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public boolean inDeadly(){
        return deadly;
    }

    @Override
    public void on_collision(Hero hero_obj){
        if(hero_obj.getImage().getBoundsInParent().intersects(getImage().getBoundsInParent())){
            explode();
        }

    }
    public void explode(){
        exploded = true;
        AtomicInteger imageIndex = new AtomicInteger(0);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), actionEvent -> {
            getImage().setImage(getImageList().get(imageIndex.getAndIncrement()));
        }));
        timeline.setCycleCount(5);

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(100), actionEvent -> {
            getImage().setScaleX(4);
            getImage().setScaleY(4);
            getImage().setImage(getImageList().get(imageIndex.getAndIncrement()));
        }));
        timeline2.setCycleCount(getImageList().size() - 6);
        Timeline timeline3 = new Timeline(new KeyFrame(Duration.millis(100), actionEvent -> {
            getImage().setScaleX(4);
            getImage().setScaleY(4);
            getImage().setImage(getImageList().get(imageIndex.getAndDecrement()));
        }));
        timeline3.setCycleCount(getImageList().size()-6);
        timeline3.setOnFinished(actionEvent -> {deadly=false; getImage().setVisible(false);});
        timeline2.setOnFinished(actionEvent ->  timeline3.play());
        timeline.setOnFinished(actionEvent -> {timeline2.play(); deadly = true;});
        timeline.play();
    }

    public Range getRange() {
        return range;
    }
}