package plswrk.willherofx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class Coin_Chest extends Chest {
    private final int coins;
    Coin_Chest(ImageView chest_image, List<Image> imageList, int coins, double x, double y) {
        super(chest_image, imageList, x, y);
        this.coins = coins;
    }
    public int getCoins(){
        return coins;
    }

    @Override
    public void on_collision() {

    }
}
