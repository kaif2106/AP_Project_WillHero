package plswrk.willherofx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public abstract class GameElement {
    private ImageView imageView;
    private final List<Image> imageList;
    private double curr_pos_x;
    private double curr_pos_y;
    GameElement(ImageView imageView, List<Image>imageList, double start_pos_x, double start_pos_y) {
        this.imageView = imageView;
        this.imageList = imageList;
        this.curr_pos_x = start_pos_x;
        this.curr_pos_y = start_pos_y;
    }

    public ImageView getImage() {
        return imageView;
    }

    public double getCurr_pos_x() {
        return curr_pos_x;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public double getCurr_pos_y() {
        return curr_pos_y;
    }

    public void setCurr_pos_x(double curr_pos_x) {
        this.curr_pos_x = curr_pos_x;
    }

    public void setCurr_pos_y(double curr_pos_y) {
        this.curr_pos_y = curr_pos_y;
    }
}