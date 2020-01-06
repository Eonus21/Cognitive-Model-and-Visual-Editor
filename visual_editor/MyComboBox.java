package visual_editor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.*;
import java.util.Arrays;

public class MyComboBox extends JComboBox<String> implements ItemListener {
    private Manager m;
    private int n;
    private DefaultComboBoxModel<String> model;
    public MyComboBox (DefaultComboBoxModel<String> model, Manager m,int n) {
        super(model);
        this.model = model;
        this.m = m;
        this.n = n;
        addItemListener(this);
        setSelectedIndex(0);
    }
    @Override
    public void itemStateChanged(ItemEvent ie) {
        if (ie.getStateChange() == ItemEvent.SELECTED) {
            m.update(n,ie.getItem().toString());
        }
    }
    public void update() {
        model.removeAllElements();
        model.addAll(Arrays.asList(m.getDataArray(n)));
    }
}