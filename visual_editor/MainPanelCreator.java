package visual_editor;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class MainPanelCreator {
    private JPanel panel;
    private MyCanvas canvas;
    public MainPanelCreator() {
        this.panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        canvas = new MyCanvas();
        canvas.setSize(700,300);
        panel.add(canvas);
    }
    public JPanel getPanel () {
        return this.panel;
    }
    public MyCanvas getCanvas () {
        return this.canvas;
    }
}