package plswrk.willherofx;

import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Chest extends GameElement{
    private boolean isOpen;
    Chest(ImageView image, List<Image> imageList, double start_pos_x, double start_pos_y) {
        super(image, imageList, start_pos_x, start_pos_y);
        isOpen = false;
    }

    public void open() throws URISyntaxException {
        isOpen = true;
        System.out.println("Chest opened");
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        ImageView image = this.getImage();
        List<Image> imageList = this.getImageList();
        for(int i=0; i<this.getImageList().size(); i++) {
            int finalI = i;
            timeline.getKeyFrames().add(new KeyFrame(
                    Duration.millis(50*(i+1)),
                    (ActionEvent event) -> image.setImage(imageList.get(finalI))
            ));
        }
        timeline.play();
//        Image transition_image1 = new Image("file:src/main/resources/images/wep_0001 #18659.png");
//        Image transition_image2 = new Image("file:src/main/resources/images/wep_0002 #18442.png");
//        Image transition_image3 = new Image("file:src/main/resources/images/wep_0003.png");
//        Image transition_image4 = new Image("file:src/main/resources/images/wep_0004 #36957.png");
//        Image transition_image5 = new Image("file:src/main/resources/images/wep_0005 #37946.png");
//        Image transition_image6 = new Image("file:src/main/resources/images/wep_0006 #42713.png");
//        Image transition_image7 = new Image("file:src/main/resources/images/wep_0007 #45764.png");
//        Image transition_image8 = new Image("file:src/main/resources/images/wep_0008 #32756.png");
//        Image transition_image9 = new Image("file:src/main/resources/images/wep_0009 #50124.png");
//        Image transition_image10 = new Image("file:src/main/resources/images/wep_0010 #21871.png");
//        SequentialTransition sequentialTransition = new SequentialTransition(this.getImage(),transition_image1);
    }
    public boolean getIsOpen(){
        return isOpen;
    }

    public abstract void on_collision();
}
