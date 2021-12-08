package plswrk.willherofx;

import javafx.scene.image.ImageView;

public class Coin_Chest extends Chest {
    private final int coins;
    Coin_Chest(ImageView coin_chest_image, int coins, double x, double y) {
        super(coin_chest_image, x, y);
        this.coins = coins;
    }
    public int getCoins(){
        return coins;
    }

    @Override
    public void on_collision() {

    }
}
