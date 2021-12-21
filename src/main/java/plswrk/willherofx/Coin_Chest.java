package plswrk.willherofx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URISyntaxException;
import java.util.List;

public class Coin_Chest extends Chest {
    private final int coins;
    //private List<Image>images;
    Coin_Chest(ImageView chest_imageView, List<Image> imageList, int coins, double x, double y) {
        super(chest_imageView, imageList, x, y);
        this.coins = coins;
    }
    public int getCoins(){
        return coins;
    }

    //

    @Override
    public void on_collision(double x, double y) {
        if(x <= this.getCurr_pos_x() + this.getImage().getFitWidth() && x >= this.getCurr_pos_x()
                && y <= this.getCurr_pos_y() + this.getImage().getFitHeight() && y >= this.getCurr_pos_y()){
            try {
                open();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }
    }
}
