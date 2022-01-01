package plswrk.willherofx;

import javafx.animation.SequentialTransition;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class WeaponAbs implements Serializable {
    private ImageView image;
    private boolean isEquipped;
    private boolean isOpened;
    private int range;
    private SequentialTransition transitions;

    WeaponAbs(ImageView image){
        this.image = image;
    }


    void setOpened(boolean x){
        isOpened = x;
    }

    void setEquipped(boolean x){
        isEquipped = x;
    }

    boolean getOpened(){
        return isOpened;
    }

    boolean getEquipped(){
        return isEquipped;
    }

    public ImageView getImage(){
        return image;
    }

    public abstract void attack();

}
