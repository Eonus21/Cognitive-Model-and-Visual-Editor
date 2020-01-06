package visual_editor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Canvas;

public class MainFrame extends JFrame {
    private Manager manager;
    private JPanel leftPanel;
    private JPanel mainPanel;
    private JPanel contents;
    public MainFrame(Manager m) {
        super("Cognitive systems editor");
        manager = m;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contents = new JPanel();
        leftPanel = new LeftPanelCreator(manager).getPanel();
        mainPanel = new MainPanelCreator().getPanel();
        contents.add(leftPanel);
        contents.add(mainPanel);
        setContentPane(contents);
        setSize(1000, 700);
        setVisible(true);
    }
    public Canvas getCanvas () {

    }
}