package plswrk.willherofx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Chest extends GameElement{
    private boolean isOpen;
    Chest(ImageView image, List<Image> imageList, double start_pos_x, double start_pos_y) {
        super(image, imageList, start_pos_x, start_pos_y);
        isOpen = false;
    }

    public void open() throws URISyntaxException {
        isOpen = true;
        System.out.println("Chest opened");
        AtomicInteger imageIndex = new AtomicInteger(0);
        Timeline coinChestOpenAnimationTimeline = new Timeline(new KeyFrame(Duration.millis(100), eventDispatchChain -> {
            getImage().setImage(getImageList().get(imageIndex.getAndIncrement()));
        }
        ));
        coinChestOpenAnimationTimeline.setCycleCount(getImageList().size());
        coinChestOpenAnimationTimeline.play();

    }
    public boolean getIsOpen(){
        return isOpen;
    }

    public abstract void on_collision();
}
