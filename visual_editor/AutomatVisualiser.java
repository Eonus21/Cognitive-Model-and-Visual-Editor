package visual_editor;

import java.awt.geom.Rectangle2D;

import fsm.Automat;

import java.awt.*;

public class AutomatVisualiser extends ObjectVisualiser {
    private int x,y,width,height;
    private Automat automat;
    public AutomatVisualiser(Automat a, GeometryInfo gi) {
        this.automat = a;
        setPosition(gi.getX(), gi.getY());
        setSize(gi.getX1(), gi.getY1());
    }

    @Override
    void change(Graphics2D g2) {
        if (automat.isActive()) 
            g2.setPaint(Color.GREEN);
        else 
            g2.setPaint(Color.DARK_GRAY);
            
        shape = new Rectangle2D.Double(x*scale,y*scale,width*scale,height*scale);
        g2.fill(shape);
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