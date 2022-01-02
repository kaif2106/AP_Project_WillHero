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
    Orc(ImageView orc_imageView, List<Image> imageList, double jump_height, double moveDist, double x, double y) {
        super(orc_imageView, imageList, jump_height, moveDist, x, y);
    }
    Boolean inAnimation = false;

    transient TranslateTransition orcDash = new TranslateTransition();

    public void dashKro(Living character){

        orcDash.setNode(this.getImage());
        orcDash.setDuration(Duration.millis(500));
        orcDash.setByX(100);
        orcDash.setCycleCount(1);
        orcDash.play();
        orcDash.setOnFinished(actionEvent -> {inAnimation = false;
            if(character instanceof Orc) ((Orc) character).inAnimation = false;});
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
        setAlive(false);
    }

    @Override
    public void on_collision(Living character) {
        if(character.isAlive()){
            ImageView heroimg = character.getImage();

            if(character.getImage().getBoundsInParent().intersects(this.getImage().getBoundsInParent())) {
                double gbot = this.getImage().getBoundsInParent().getMinY();
                double gtop = this.getImage().getBoundsInParent().getMaxY();
                double hbot = character.getImage().getBoundsInParent().getMinY();
                double htop = character.getImage().getBoundsInParent().getMaxY();
                if (!inAnimation && (character instanceof Hero) && ((htop - gbot >= 15) && ((htop <= gtop && hbot >= gbot) || (htop >= gtop && hbot >= gbot)) && character.getImage().getBoundsInParent().getCenterX() > getImage().getBoundsInParent().getMinX())) {
                    //System.out.println("ded here");
                    character.die();
                    if( character instanceof Hero){
                        ((Hero) character).setCoins(((Hero) character).getCoins()+25);
                    }

                } else {
                    if (!inAnimation && character.getImage().getBoundsInParent().getMaxX() > this.getImage().getBoundsInParent().getMinX() && !orcDash.getStatus().equals(Animation.Status.RUNNING)) {
                        inAnimation = true;
                        if(character instanceof Orc) {((Orc) character).orcDash.pause();}
                        dashKro(character);
                    }
                }
            }
        }
    }
}
