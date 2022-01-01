package plswrk.willherofx;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Weapon implements Serializable {
    private final String name;
    private int level;
    private double damage;
    private Range range;
    Weapon(String name, int level, double damage, Range range) {
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.range = range;
    }
    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }
    public double getDamage() {
        return damage;
    }
    public Range getRange() {
        return range;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setDamage(double damage) {
        this.damage = damage;
    }
    public void setRange(Range range) {
        this.range = range;
    }
}
