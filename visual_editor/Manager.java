package visual_editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import fsm.*;
import cognitive_system.*;
import cognitive_system.file_integration.*;

public class Manager {
    private HashMap <String,CognitiveSystem> systems;
    private ArrayList <MyComboBox> updatableBoxes;
    private String noSelected;
    private String[] systemNames;
    private String[] automatNames;
    private String[] layerNames;
    private String[] stateNames;
    private String[] transitionNames;
    private DataController dc;
    private ArrayList <String> activeElements;//system-0,layer-1,automat-2,state-3,transition-4
    public Manager () throws Exception {
        activeElements = new ArrayList<String>();
        systems = new HashMap<String,CognitiveSystem>();
        noSelected = "";
        for (int i = 0; i < 5;i++)
            activeElements.add(noSelected);
        dc = new DataController();
        updatableBoxes = new ArrayList<MyComboBox>();
        update();
    }
    private void updateArray(int i) {
        for (int j = i;j<activeElements.size();j++)
            activeElements.set(j, noSelected);
    }
    public void addFile (String way, String parcer) {
        try {
            systems = new FileReader(way, parcer).getCognitiveSystems();
            systemNames = new String[systems.size()+1];
            systemNames[0] = noSelected;
            int i = 1;
            for (Entry <String, CognitiveSystem> entry:systems.entrySet()) {
                entry.getValue().setDataController(dc);
                systemNames[i] = entry.getKey();
                i++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String[] getSystemNames () {
        return systemNames;
    }
    public String[] getLayerNames () {
        if (activeElements.get(0).equals(noSelected)) 
            return new String[] {noSelected};
        else 
            {
                ArrayList <AutomatLayer> layers = systems.get(activeElements.get(0)).getLayers();
                String[] arr = new String[layers.size()+1];
                arr[0] = noSelected;
                int i = 1;
                for (AutomatLayer al:layers) {
                    arr[i] = al.id;
                    i++;
                }                    
                return arr;
            }
    }
    public String[] getAutomatNames () {
        if (activeElements.get(1).equals(noSelected)) {
            return new String[] {noSelected};
        } 
        else {
            ArrayList <Automat> automats = getAutomats();
            String[] arr = new String[automats.size()+1];
            arr[0] = noSelected;
            int i = 1;
            for (Automat a:automats) {
                arr[i] = a.name;
                i++;
            }
            return arr;
        }
    }
    public String[] getStateNames () {
        if (activeElements.get(2).equals(noSelected)) {
            return new String[] {noSelected};
        } 
        else {
            Automat thAutomat = getAutomat(activeElements.get(2));

            HashMap <String, State> states = thAutomat.getStates();
            String[] arr = new String[states.size()+1];
            arr[0] = noSelected;
            int i = 1;
            for (Entry <String,State> entry:states.entrySet()) {
                arr[i] = entry.getValue().getId();
                i++;
            }
            return arr;
        }
    }
    public String[] getTransitionNames () {
        if (activeElements.get(3).equals(noSelected)) {
            return new String[] {noSelected};
        } 
        else {
            Automat a = getAutomat(activeElements.get(2));
            ArrayList <Transition> transitions = a.getTransitionsFromState(activeElements.get(3));
            String[] arr = new String[transitions.size()+1];
            arr[0] = noSelected;
            int i = 1;
            for (Transition t:transitions) {
                arr[i] = t.getId();
                i++;
            }
            return arr;
        }
    }
    private ArrayList<Automat> getAutomats () {
        ArrayList<AutomatLayer> layers = systems.get(activeElements.get(0)).getLayers();
        AutomatLayer activeLayer = new AutomatLayer("id");
        for (AutomatLayer al:layers) {
            if (al.id.equals(activeElements.get(1))) activeLayer = al;
        }
        ArrayList <Automat> automats = activeLayer.getAutomats();
        return automats;
    }
    private Automat getAutomat (String id) {
        ArrayList<Automat> automats = getAutomats();
        for (Automat a:automats)
            if (a.name.equals(id)) 
                return a;

            return null;
    }
    private void update() {
        layerNames = getLayerNames();
        automatNames = getAutomatNames();
        stateNames = getStateNames();
        transitionNames = getTransitionNames();
    }
    public void update (int i) {
        //systemNames = getSystemNames();
        update();
        for (int j = i;j<updatableBoxes.size();j++) {
            updatableBoxes.get(j).update();
        }
    }
    public void update (int i, String s) {
        System.out.println("update " + i + " selected " + s);
        activeElements.set(i,s);
        updateArray(i+1);
        update(i+1);
    }
    public void addUpdatableBoxes (ArrayList<MyComboBox>boxes) {
        updatableBoxes.addAll(boxes);
    }
    public String[] getDataArray (int i) {
        switch (i) {
            case 0:
                return systemNames;
            case 1:
                return layerNames;
            case 2:
                return automatNames;
            case 3:
                return stateNames;
            case 4:
                return transitionNames;
            default:
                return new String[]{};
        }
    }
}