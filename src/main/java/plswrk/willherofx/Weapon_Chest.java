package plswrk.willherofx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URISyntaxException;
import java.util.List;

public class Weapon_Chest extends Chest{
    Weapon weapon;

    public Weapon_Chest(ImageView chest_image, List<Image> imageList, Weapon weapon, double x, double y) {
        super(chest_image, imageList, x, y);
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public void on_collision(Hero hero_obj) {
        //System.out.println("yes\nherox: "+Double.toString(x));
        //System.out.println("chestx: "+Double.toString(getCurr_pos_x())+"-"+Double.toString(getCurr_pos_x()+getImage().getFitWidth()));
//        if(x <= this.getCurr_pos_x() + this.getImage().getFitWidth() && x >= this.getCurr_pos_x()-50
//                && y <= this.getCurr_pos_y() + this.getImage().getFitHeight() && y >= this.getCurr_pos_y()){
//            try {
//                open();
//            } catch (URISyntaxException ex) {
//                ex.printStackTrace();
//            }
//        }
        if(hero_obj.getImage().getBoundsInParent().intersects(getImage().getBoundsInParent())) {
            try {
                open();

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

    }
}
