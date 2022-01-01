package plswrk.willherofx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.List;

public class Boss extends Living{
    private int health;

    Boss(ImageView image_view, List<Image> imageList, double jumpheight, double movedist, double x, double y){
        super(image_view, imageList, jumpheight, movedist, x, y);
        health = 100;
    }

    Boolean inAnimation = false;

    TranslateTransition orcDash = new TranslateTransition();

    public void hit(int damage){
        health-=damage;
        if(health<=0) die();
    }

    public int getHealth(){
        return health;
    }

    public void dashKro(Living character){

        orcDash.setNode(this.getImage());
        orcDash.setDuration(Duration.millis(500));
        orcDash.setByX(220);
        orcDash.setCycleCount(1);
        orcDash.play();
        orcDash.setOnFinished(actionEvent -> {inAnimation = false;
            if(character instanceof Orc) ((Orc) character).inAnimation = false;});
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
                if (!inAnimation && (character instanceof Hero) && ((htop - gbot >= 15) && ((htop <= gtop && hbot >= gbot) || (htop >= gtop && hbot >= gbot)) && character.getImage().getBoundsInParent().getMinX() > getImage().getBoundsInParent().getMinX())) {
                    System.out.println("ded here");
                    character.die();

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
                    (ActionEvent event) -> {
                        orc_imageView.setImage(orc_transitionList.get(finalI));
                        orc_imageView.setScaleX(1 - (finalI * 0.1));
                        orc_imageView.setScaleY(1 - (finalI * 0.1));
                    }
            ));
        }
        timeline.play();
        setAlive(false);
    }
}
