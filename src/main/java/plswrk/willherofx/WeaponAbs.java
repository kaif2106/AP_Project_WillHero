package plswrk.willherofx;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class WeaponAbs implements Serializable {
    transient private ImageView image;
    private boolean isEquipped;
    private boolean isOpened;
    private int level;

    WeaponAbs(ImageView image){
        this.image = image;
        level = 1;
    }

    void setLevel(int x){
        level = x;
    }

    int getLevel(){
        return level;
    }

    void setOpened(boolean x){
        isOpened = x;
    }

    void setEquipped(boolean x){
        isEquipped = x;
    }

    public ImageView getImage(){
        return image;
    }

    public abstract void attack();

}
