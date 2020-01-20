package visual_editor;

import java.awt.Shape;
import java.awt.Graphics2D;

public abstract class ObjectVisualiser {
    protected Shape shape;
    protected double scale = 1;
    abstract void change(Graphics2D g2);
    public void setScale (double k) {
        scale = k;
    }
    public double getScale () {
        return this.scale;
    }
}