package plswrk.willherofx;

import javafx.scene.image.ImageView;

public class Coin extends GameElement{
    private boolean isCollected;
    Coin(ImageView coin_image, double x, double y) {
        super(coin_image, null, x, y);
        isCollected = false;
    }
    void collect() {
        isCollected = true;
    }
    boolean isCollected() {
        return isCollected;
    }
}
