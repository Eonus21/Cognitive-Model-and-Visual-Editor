package visual_editor;

import java.awt.geom.Rectangle2D;
import java.awt.*;

public class AutomatVisualiser extends ObjectVisualiser {
    private int x,y,width,height;
    public AutomatVisualiser() {
        setPosition(0, 0);
        setSize(1, 1);
    }

    @Override
    void change(int i, Graphics2D g2) {
        g2.setPaint(Color.GREEN);
        if (i == 0) shape = new Rectangle2D.Double(x,y,width,height);
    }

    public void setPosition (int x,int y) {
        this.x = x;
        this.y = y;
    }
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}