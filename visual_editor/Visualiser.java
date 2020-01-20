package visual_editor;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import cognitive_system.AutomatLayer;
import cognitive_system.CognitiveSystem;
import fsm.Automat;
import fsm.Transition;

public class Visualiser {
    private ArrayList<LayerVisualiser> layers;
    private ArrayList<AutomatVisualiser> automats;
    private ArrayList<StateVisualiser> states;
    private ArrayList<TransitionVisualiser> transitions;
    private HashMap<String, Automat> automatsHashMap;
    private HashMap<String, CognitiveSystem> systemsHashMap;

    public Visualiser(MyCanvas cs, Manager m) {
        cs.setVisualiser(this);
        automatsHashMap = m.getAutomatsHashMap();
        systemsHashMap = m.getSystemsHashMap();
        layers = new ArrayList<LayerVisualiser>();
        automats = new ArrayList<AutomatVisualiser>();
        states = new ArrayList<StateVisualiser>();
        transitions = new ArrayList<TransitionVisualiser>();
        new StateChangeChecker(automatsHashMap, cs);
        ArrayList<VisualiseInfo> visualiseInfos = m.getVisualiseInfos();
        createAll(visualiseInfos);
    }

    private void createAll(ArrayList<VisualiseInfo> infos) {
        for (VisualiseInfo info : infos)
            createOne(info);
    }

    private void createOne(VisualiseInfo info) {
        try {
            switch (info.getElementType()) {
                case "layer":
                    ArrayList <AutomatLayer> thl = systemsHashMap.get(info.getParentName()).getLayers();
                    AutomatLayer al = new AutomatLayer("soso");
                    for (AutomatLayer layer:thl)
                        if (layer.id.equals(info.getElementName())) al = layer;
                    layers.add(new LayerVisualiser(al, info.getGeometryInfo()));
                    break;
                case "automat":
                    automats.add(new AutomatVisualiser(automatsHashMap.get(info.getElementName()), info.getGeometryInfo()));
                    break;
                case "state":
                    states.add(new StateVisualiser(automatsHashMap.get(info.getParentName()),info.getElementName(), info.getGeometryInfo()));
                    break;
                case "transition":
                    String[] parents = info.getParentName().split("/");
                    ArrayList <Transition> transitions = automatsHashMap.get(parents[0]).getTransitionsFromState(parents[1]);
                    Transition transition = transitions.get(0);
                    for (Transition t:transitions)
                        if (t.getId().equals(info.getElementName()))
                            transition = t;
                    this.transitions.add(new TransitionVisualiser(transition, info.getGeometryInfo()));
                    break;
                }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Graphics2D g2) {
        System.out.println("update painting");
        paintSystems(g2);
        paintLayers(g2);
        paintAutomats(g2);
        paintTransitions(g2);
        paintStates(g2);
    }
    private void paintSystems (Graphics2D g2) {
    }
    private void paintLayers (Graphics2D g2) {
        for (LayerVisualiser lv : layers)
            lv.change(g2);
    }
    private void paintAutomats (Graphics2D g2) {
        for (AutomatVisualiser al:automats)
            al.change(g2);
    }
    private void paintStates (Graphics2D g2) {
        for (StateVisualiser sv:states)
            sv.change(g2);
    }
    private void paintTransitions (Graphics2D g2) {
        for (TransitionVisualiser tv:transitions)
            tv.change(g2);
    }
}

class StateChangeChecker extends Thread {
    private int dt;
    private Automat[] automats;
    private HashMap <String, String> prevStates;
    private Canvas cs;

    public StateChangeChecker (HashMap <String, Automat> automats, Canvas cs) {
        dt = 200;
        this.automats = new Automat[automats.size()];
        this.automats = automats.values().toArray(this.automats);
        this.cs = cs;
        prevStates = new HashMap <String, String> ();
        start();
    }

    public void run () {
        setDefaultPrevStates();
        try {
            while (true) {
                if (check()) cs.repaint();
                sleep(dt);
            }            
        } catch (Exception e) {
            //e.printStackTrace();
        }
    } 

    private void setDefaultPrevStates() {
        for (Automat a:automats)
            prevStates.put(a.name, "default");
    }

    private boolean check() {
        for (Automat a:automats) 
            if (checkAutomat(a, prevStates.get(a.name)))
                return true;
        
        return false;
    }

    private boolean checkAutomat(Automat a, String prevState) {
        String activeState = a.getActiveState().getId();
        if (activeState.equals(prevState)) {
            return false;
        }
        else {
            prevStates.put(a.name, activeState);
            return true;
        }
    }
}