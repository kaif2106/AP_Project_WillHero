package plswrk.willherofx;

import javafx.scene.image.ImageView;

public abstract class GameElement {
    private ImageView image;
    private double curr_pos_x;
    private double curr_pos_y;
    GameElement(ImageView image, double start_pos_x, double start_pos_y) {
        this.image = image;
        this.curr_pos_x = start_pos_x;
        this.curr_pos_y = start_pos_y;
    }

    public ImageView getImage() {
        return image;
    }

    public double getCurr_pos_x() {
        return curr_pos_x;
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
    private static float gravityValue;
    GameElement() {
        gravityValue = 0.1f;
    }
}