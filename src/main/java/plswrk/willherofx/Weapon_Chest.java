package plswrk.willherofx;

import javafx.scene.image.ImageView;

public class Weapon_Chest extends Chest{
    Weapon weapon;
    public Weapon_Chest(ImageView weapon_chest_image, Weapon weapon, double x, double y) {
        super(weapon_chest_image, x, y);
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public ImageView getWeapon_chest_image() {
        return this.getImage();
    }

    @Override
    public void on_collision() {

    }
}
