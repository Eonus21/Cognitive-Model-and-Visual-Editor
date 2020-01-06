package visual_editor;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Canvas;

public class MainPanelCreator {
    private JPanel panel;
    public MainPanelCreator() {
        this.panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        Canvas canvas = new Canvas ();
        canvas.setSize(700,300);
        panel.add(canvas);
    }
}