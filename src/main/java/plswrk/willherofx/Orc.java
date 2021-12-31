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

    TranslateTransition orcDash = new TranslateTransition();

    public void dashKro(){

        orcDash.setNode(this.getImage());
        orcDash.setDuration(Duration.millis(500));
        orcDash.setByX(100);
        orcDash.setCycleCount(1);
        orcDash.play();
        orcDash.setOnFinished(actionEvent -> inAnimation = false);
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
//        orc_image.setImage(new Image("/MonsterFatality.png"));
        //System.out.println("Monster Fatality");
    }

    @Override
    public void on_collision(Hero hero_obj) {
        ImageView heroimg = hero_obj.getImage();

        if(hero_obj.getImage().getBoundsInParent().intersects(this.getImage().getBoundsInParent())) {
            System.out.println("orc collide");
            //if(heroimg.getBoundsInParent().getMaxX()>=this.getImage().getBoundsInParent().getMinX()) &&  ((heroimg.getBoundsInParent().getMinY()<=this.getImage().getBoundsInParent().getMinY() && heroimg.getBoundsInParent().getMaxY()>=getImage().getBoundsInParent().getMaxY()) || (heroimg.getBoundsInParent().getMinY()>=getImage().getBoundsInParent().getMinY()&& heroimg.getBoundsInParent().getMaxY()>=getImage().getBoundsInParent().getMaxY())){

            //}
//            if(hero_obj.getImage().getBoundsInParent().getMinY()>=this.getImage().getBoundsInParent().getMaxY()){
//                System.out.println("orc jump");
//            }
            //hero_obj.getImage().getBoundsInParent().getMaxX()>=this.getImage().getBoundsInParent().getMinX()) &&  ((herotop<=orcTop && herobot>=orcbot) || (herotop>=orcTop&& herobot>=orcbot
            double gbot = this.getImage().getBoundsInParent().getMinY();
            double gtop = this.getImage().getBoundsInParent().getMaxY();
            double hbot = hero_obj.getImage().getBoundsInParent().getMinY();
            double htop = hero_obj.getImage().getBoundsInParent().getMaxY();
            if (((htop - gbot >= 35) && ((htop <= gtop && hbot >= gbot) || (htop >= gtop && hbot >= gbot)) && hero_obj.getImage().getBoundsInParent().getCenterX()>getImage().getBoundsInParent().getMinX()+10)) {
                hero_obj.die();

            } else {
                if (!inAnimation && hero_obj.getImage().getBoundsInParent().getMaxX() > this.getImage().getBoundsInParent().getMinX() && !orcDash.getStatus().equals(Animation.Status.RUNNING)) {
                    inAnimation = true;
                    dashKro();
                }
            }
        }
        //System.out.println(this.getImage().getBoundsInParent().getMinY());




    }
}
