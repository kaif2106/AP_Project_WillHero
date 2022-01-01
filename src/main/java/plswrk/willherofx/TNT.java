package plswrk.willherofx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.util.List;

public class TNT extends GameElement implements Obstacle {
    private final Range range;
    TNT(ImageView imageView, List<Image> imageList, double start_pos_x, double start_pos_y, Range range) {
        super(imageView, imageList, start_pos_x, start_pos_y);
        this.range = range;
    }
    @Override
    public void on_collision(Hero hero_obj){
        //System.out.println("herox: "+Double.toString(x)+" heroy: "+Double.toString(y));
        //System.out.println("TNTx: "+Double.toString(getCurr_pos_x())+" - "+Double.toString(getCurr_pos_x() + getImage().getFitWidth())+" TNTy: "+Double.toString(getCurr_pos_y()) + " - " + Double.toString(getCurr_pos_y()+getImage().getFitHeight()));
//        if(x <= this.getCurr_pos_x() + this.getImage().getFitWidth() && x >= this.getCurr_pos_x()-75
//                && y <= this.getCurr_pos_y() + this.getImage().getFitHeight() && y >= this.getCurr_pos_y()){
//            explode();
//        }
        if(hero_obj.getImage().getBoundsInParent().intersects(getImage().getBoundsInParent())){
            explode();
        }
//        if(this.getImage().intersects(hero.getImage().getBoundsInParent())){
//            explode();
//        }

    }
    public void explode(){
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        ImageView image = this.getImage();
        List<Image> imageList = this.getImageList();
        int j=0;
        //for(int i=0; i<5; i++){
            int k=0;
            for ( ; k < this.getImageList().size(); k++) {
                int finalI = k;
                timeline.getKeyFrames().add(new KeyFrame(
                        Duration.millis(60*(j++)),
                        (ActionEvent event) -> {
                            image.setImage(imageList.get(finalI));
                            if(finalI>5) {
                                image.setScaleX(1.5+(finalI*0.1));
                                image.setScaleY(1.5+(finalI*0.1));
                            }
                        }
                ));
            }
            k--;
            for ( ; k >= 0; k--) {
                int finalI = k;
                timeline.getKeyFrames().add(new KeyFrame(
                        Duration.millis(60 * (j++)),
                        (ActionEvent event) -> {
                            image.setImage(imageList.get(finalI));
                            if (finalI <= 5) {
                                image.setScaleX(1);
                                image.setScaleY(1);
                            }
                            else{
                                image.setScaleX(1.5+(finalI*0.1));
                                image.setScaleY(1.5+(finalI*0.1));
                            }
                        }
                ));
            }
            timeline.setOnFinished(event -> image.setVisible(false));
        timeline.play();
    }

    public Range getRange() {
        return range;
    }
}
