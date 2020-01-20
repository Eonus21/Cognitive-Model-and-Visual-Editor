package visual_editor;

import fsm.Transition;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Line2D;

public class TransitionVisualiser extends ObjectVisualiser {
    private Transition transition;
    private int x;
    private int y;
    private int width;
    private int height;
    public TransitionVisualiser (Transition transition, GeometryInfo gi) {
        this.transition = transition;
        x = gi.getX();
        y = gi.getY();
        width = gi.getX1();
        height = gi.getY1();
    }
    public void change (Graphics2D g2) {
        g2.setPaint(Color.MAGENTA);
        shape = new Line2D.Double(x,y,width,height);
        g2.draw(shape);
    }
}