package visual_editor;

import java.awt.Shape;
import java.awt.Graphics2D;

public abstract class ObjectVisualiser {
    protected Shape shape;
    abstract void change(int i, Graphics2D g2);
    public Shape getVisualisation() {
        return this.shape;
    }
}