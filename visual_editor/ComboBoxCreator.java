package visual_editor;

import javax.swing.DefaultComboBoxModel;

public class ComboBoxCreator {
    private MyComboBox box;
    private DefaultComboBoxModel<String> cbModel;
    public ComboBoxCreator (String[] elements,Manager m, int n) {
        cbModel = new DefaultComboBoxModel<String>(elements);
        box = new MyComboBox(cbModel, m, n);
    }
    public MyComboBox getBox () {
        return this.box;
    }
}