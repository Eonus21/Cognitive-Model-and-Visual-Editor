package visual_editor;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private Manager manager;
    private  MainPanelCreator mainPanelCreator;
    private JPanel contents;
    public MainFrame(Manager m) {
        super("Cognitive systems editor");
        manager = m;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contents = new JPanel();
        JPanel leftPanel = new LeftPanelCreator(manager).getPanel();
        mainPanelCreator = new MainPanelCreator();
        JPanel mainPanel = mainPanelCreator.getPanel();
        contents.add(leftPanel);
        contents.add(mainPanel);
        setContentPane(contents);
        setSize(1000, 700);
        setVisible(true);
    }
    public MyCanvas getCanvas () {
        return mainPanelCreator.getCanvas();
    }
}