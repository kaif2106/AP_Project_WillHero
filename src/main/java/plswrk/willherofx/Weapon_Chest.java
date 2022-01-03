package plswrk.willherofx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URISyntaxException;
import java.util.List;

public class Weapon_Chest extends Chest{
    WeaponAbs weapon;

    public Weapon_Chest(ImageView chest_image, List<Image> imageList, WeaponAbs weapon, double x, double y) {
        super(chest_image, imageList, x, y);
        this.weapon = weapon;
    }

    @Override
    public void on_collision(Hero hero_obj) {
        if(!getIsOpen()) {
            if (hero_obj.getImage().getBoundsInParent().intersects(getImage().getBoundsInParent())) {
                try {
                    open();
                    weapon.setOpened(true);
                    weapon.setEquipped(true);


                    if(weapon instanceof ThrowingAxe){
                        if(hero_obj.getEquippedAxe()!=null) hero_obj.getEquippedAxe().setLevel(2);
                        else hero_obj.setEquippedAxe((ThrowingAxe) weapon);
                        hero_obj.setEquippedWeapon(hero_obj.getEquippedAxe());
                    }
                    else{
                        if(hero_obj.getEquippedKnife()!=null) hero_obj.getEquippedKnife().setLevel(2);
                        else hero_obj.setEquippedKnife((ThrowingKife) weapon);
                        hero_obj.setEquippedWeapon(hero_obj.getEquippedKnife());
                    }

                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
