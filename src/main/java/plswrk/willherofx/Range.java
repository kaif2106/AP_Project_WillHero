package plswrk.willherofx;

import java.io.Serializable;

public class Range implements Serializable {
    private final int x_range;
    private final int y_range;

    public Range(int x, int y) {
        x_range = x;
        y_range = y;
    }
}
