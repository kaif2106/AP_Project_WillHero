package plswrk.willherofx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.List;

public class TNT extends GameElement implements Obstacle{
    private final Range range;
    TNT(ImageView imageView, List<Image> imageList, double start_pos_x, double start_pos_y, Range range) {
        super(imageView, imageList, start_pos_x, start_pos_y);
        this.range = range;
    }
    public void explode(){
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        ImageView image = this.getImage();
        List<Image> imageList = this.getImageList();
        int j=0;
        for(int i=0; i<10; i++){
            int k=0;
            for ( ; k < this.getImageList().size(); k++) {
                int finalI = k;
                timeline.getKeyFrames().add(new KeyFrame(
                        Duration.millis(70*(j++)),
                        (ActionEvent event) -> image.setImage(imageList.get(finalI))
                ));
            }
            k--;
            for ( ; k >= 0; k--) {
                int finalI = k;
                timeline.getKeyFrames().add(new KeyFrame(
                        Duration.millis(70 * (j++)),
                        (ActionEvent event) -> image.setImage(imageList.get(finalI))
                ));
            }
        }
        timeline.play();
    }

    public Range getRange() {
        return range;
    }
}
