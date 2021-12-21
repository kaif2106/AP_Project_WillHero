package plswrk.willherofx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class Orc extends Living {
    Orc(ImageView orc_imageView, List<Image> imageList, int jump_height, double x, double y) {
        super(orc_imageView, imageList, jump_height, x, y);
    }

    @Override
    public void die() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        ImageView orc_imageView = this.getImage();
        List<Image> orc_transitionList = this.getImageList();
        for(int i=0; i<orc_transitionList.size(); i++) {
            int finalI = i;
            timeline.getKeyFrames().add(new KeyFrame(
                    Duration.millis(120*(i+1)),
                    (ActionEvent event) -> orc_imageView.setImage(orc_transitionList.get(finalI))
            ));
        }
        timeline.play();
//        orc_image.setImage(new Image("/MonsterFatality.png"));
        System.out.println("Monster Fatality");
    }

    @Override
    public void on_collision(double x, double y) {


    }
}
