package visual_editor;

import java.awt.*;
import java.util.ArrayList;

public class MyCanvas extends Canvas {
    private Visualiser visualiser;
    public MyCanvas () {
        super();
    }
    @Override
    public void paint (Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        visualiser.update(g2);
    }
    public void setVisualiser (Visualiser v) {
        this.visualiser = v;
    }
}