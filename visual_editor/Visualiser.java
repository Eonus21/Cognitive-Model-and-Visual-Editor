package visual_editor;

import java.awt.*;

public class Visualiser {
    Canvas canvas;
    public Visualiser(MyCanvas cs) {
        cs.setVisualiser(this);
        canvas = cs;
    }
    public void update (Graphics2D g2) {
        System.out.println("update painting");
    }
}