package visual_editor;

import java.awt.geom.Rectangle2D;

import cognitive_system.AutomatLayer;
import java.awt.Color;
import java.awt.Graphics2D;

public class LayerVisualiser extends ObjectVisualiser {
    private int x,y,width,height;
    private AutomatLayer layer;
    public LayerVisualiser (AutomatLayer layer, GeometryInfo gi) {
        this.layer = layer;
        setPosition(gi.getX(), gi.getY());
        setSize(gi.getX1(), gi.getY1());
    }
    public void change (Graphics2D g2) {
        g2.setPaint(Color.RED);
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