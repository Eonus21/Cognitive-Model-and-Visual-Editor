package visual_editor;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class MainPanelCreator {
    private JPanel panel;
    public MainPanelCreator() {
        this.panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }
}