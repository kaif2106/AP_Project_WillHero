package plswrk.willherofx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class Weapon_Chest extends Chest{
    Weapon weapon;

    public Weapon_Chest(List<Image> imageList, Weapon weapon, double x, double y) {
        super(imageList, x, y);
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public void on_collision() {

    }
}
