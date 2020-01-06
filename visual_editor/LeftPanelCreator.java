package visual_editor;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class LeftPanelCreator {
    private JPanel panel;
    private ArrayList<MyComboBox> boxes;
    public LeftPanelCreator (Manager m) {
        this.panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        boxes = new ArrayList<MyComboBox>();
        boxes.add(new ComboBoxCreator(m.getSystemNames(),m,0).getBox());//systems
        boxes.add(new ComboBoxCreator(m.getLayerNames(),m,1).getBox());//layers
        boxes.add(new ComboBoxCreator(m.getAutomatNames(),m,2).getBox());//automats
        boxes.add(new ComboBoxCreator(m.getStateNames(),m,3).getBox());//states
        boxes.add(new ComboBoxCreator(m.getTransitionNames(),m,4).getBox());//transitions
        m.addUpdatableBoxes(boxes);
        start();
    }
    private void start () {
        for (JComboBox<String> box : boxes){
            panel.add(box);
        }
    }
    public JPanel getPanel () {
        return this.panel;
    }
}