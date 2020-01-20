package visual_editor;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cognitive_system.CognitiveSystem;
import fsm.Automat;
import fsm.State;
import fsm.Transition;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JLabel;

public class RightPanelCreator {
    private JPanel panel;
    private JLabel typeLabel;
    private Manager manager;
    private String noSelected;
    private int active;
    private ArrayList <JTextField> textFields;//0-name, 1 - stateFrom, 2 - stateTo, 3 - event
    public RightPanelCreator (Manager m) {
        m.setRightPanelCreator(this);
        this.panel = new JPanel();
        this.manager = m;
        this.noSelected = manager.getNoSelectedString();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        typeLabel = new JLabel("type");
        panel.add(typeLabel);
        textFields = new ArrayList<JTextField>();
        textFields.add(new JTextField("name", 20));
        textFields.add(new JTextField("stateFrom", 20));
        textFields.add(new JTextField("stateTo",20));
        textFields.add(new JTextField("event",20));
        setActionListeners();
    }
    public void update (ArrayList <String> activeElements, int i) {
        if ((i == 0)&&(activeElements.get(0).equals(noSelected))) i = -1;
        active = i;
        switch (i) {
            case 0:
                setActiveNetwork(activeElements.get(0));
                break;
            case 1:
                setActiveLayer(activeElements.get(1));
                break;
            case 2:
                setActiveAutomat(activeElements.get(2));
                break;
            case 3:
                setActiveState(activeElements.get(3));
                break;
            case 4:
                setActiveTransition(activeElements.get(4));
                break;
            default:
                setType("Waiting ...");
                break;
        }
    }
    private void setActiveAutomat (String name) {
        setType("Automat");
        textFields.get(0).setText(name);
        textFields.get(0).setVisible(true);
    }
    private void setActiveLayer (String name) {
        setType("Layer");
        textFields.get(0).setText(name);
        textFields.get(0).setVisible(true);
    }
    private void setActiveNetwork (String name) {
        setType("Network");
        textFields.get(0).setText(name);
        textFields.get(0).setVisible(true);
    }
    private void setActiveState (String name) {
        setType("State");
        textFields.get(0).setText(name);
        textFields.get(0).setVisible(true);
    }
    private void setActiveTransition (String name) {
        Transition th = manager.getActiveTransition();
        setType("Transition");
        textFields.get(0).setText(name);
        textFields.get(0).setVisible(true);
        textFields.get(1).setText(th.getPrevStateId());
        textFields.get(1).setVisible(true);
        textFields.get(2).setText(th.getNextStateId());
        textFields.get(2).setVisible(true);
        textFields.get(3).setText(th.getEventName());
        textFields.get(3).setVisible(true);
    }
    private void setType (String type) {
        for (JTextField textField : textFields)
            textField.setVisible(false);
        typeLabel.setText(type);
    }
    public void setted (int i) {//i - номер текстового поля, которое обновилось
        int n = -1;
        while ((n+1 < manager.getActiveElements().size())&&(!manager.getActiveElements().get(n+1).equals(noSelected)))
            n++;
        System.out.println("active " + n);
        switch (n) {
            case 0:
                CognitiveSystem cs = manager.getActiveSystem();
                manager.getSystemsHashMap().remove(cs.id);
                cs.id = textFields.get(0).getText();
                manager.getSystemsHashMap().put(cs.id, cs);
                manager.update(n, noSelected);
                manager.update(n);
                break;
        
            case 1: 
                manager.getActiveLayer().id = textFields.get(0).getText();
                manager.update(n, noSelected);
                manager.update(n);
                break;
            case 2:
                Automat a = manager.getActiveAutomat();
                manager.getAutomatsHashMap().remove(a.name);
                a.name = textFields.get(0).getText();
                manager.getAutomatsHashMap().put(a.name, a);
                manager.update(n, noSelected);
                manager.update(n);
                break;
            case 3:
                
                Automat b = manager.getActiveAutomat();
                State s = manager.getActiveState();
                ArrayList <Transition> transitions = b.getTransitionsFromState(s.getId());
                b.getStates().remove(s.getId());
                b.getAlltransitions().remove(s.getId());
                s.setId(textFields.get(0).getText());
                b.getStates().put(s.getId(), s);
                for (Transition t: transitions)
                    t.setPrevStateId(s.getId());
                b.getAlltransitions().put(s.getId(), transitions);
                manager.update(n, noSelected);
                manager.update(n);
                break;
        }

    }
    private void setActionListeners () {
        for (int i = 0;i<textFields.size();i++) {
            textFields.get(i).addActionListener(new MyActionListener(this, i));
            panel.add(textFields.get(i));
        }
    }
    public JPanel getPanel () {
        return this.panel;
    }
}

class MyActionListener implements ActionListener {
    private RightPanelCreator rpc;
    private int i;
    public MyActionListener (RightPanelCreator rpc, int i) {
        this.rpc = rpc;
        this.i = i;
    }
    public void actionPerformed (ActionEvent ae) {
        rpc.setted(i);
    }
}