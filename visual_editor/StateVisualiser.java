package visual_editor;

import fsm.Automat;
import fsm.State;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class StateVisualiser extends ObjectVisualiser {
    private String stateName;
    private Automat parent;
    private int x;
    private int y;
    private int width;
    private int height;
    public StateVisualiser (Automat parent,String stateName, GeometryInfo gi) {
        this.stateName = stateName;
        this.parent = parent;
        x = gi.getX();
        y = gi.getY();
        width = gi.getX1();
        height = gi.getY1();
    }
    public void change (Graphics2D g2) {
        try {
            if (parent.getActiveState().getId().equals(stateName))
            g2.setPaint(Color.ORANGE);
        else 
            g2.setPaint(Color.PINK);
        }
        catch (Exception e) {
            g2.setPaint(Color.PINK);
        }
        shape = new Ellipse2D.Double(x*scale,y*scale,width*scale,height*scale);
        g2.fill(shape);
    }
}