package plswrk.willherofx;

import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URISyntaxException;
import java.util.Objects;

public abstract class Chest extends GameElement{
    private boolean isOpen;
    Chest(ImageView chest_image, double x, double y) {
        super(chest_image, x, y);
        isOpen = false;
    }

    public void open() throws URISyntaxException {
        isOpen = true;
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
