package visual_editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import fsm.*;
import cognitive_system.*;
import cognitive_system.file_integration.*;

public class Manager {
    private HashMap <String,CognitiveSystem> systems;
    private HashMap <String, Automat> automats;
    private ArrayList <MyComboBox> updatableBoxes;
    private RightPanelCreator rpc;
    private ArrayList <VisualiseInfo> visualiseInfos;
    public String noSelected;
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
    public void updateArray(int i) {
        for (int j = i;j<activeElements.size();j++)
            activeElements.set(j, noSelected);
    }
    public void addFile (String way, String parcer) {
        try {
            FileReader fr = new FileReader(way, parcer);
            systems = fr.getCognitiveSystems();
            automats = fr.getAutomats();
            visualiseInfos = fr.getVisualiseInfos();
            systemNames = new String[systems.size()+1];
            systemNames[0] = noSelected;
            int i = 1;
            for (Entry <String, CognitiveSystem> entry:systems.entrySet()) {
                entry.getValue().setDataController(dc);
                //entry.getValue().start();//TODO убрать потом, заменить на кнопку запуска
                systemNames[i] = entry.getKey();
                i++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String[] getSystemNames () {
        String [] arr = new String [systems.size()+1];
        arr[0] = noSelected;
        int i = 1;
        for (Entry <String, CognitiveSystem> entry : systems.entrySet()) {
            arr[i] = entry.getKey();
            i++;
        }
        return arr;
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
        systemNames = getSystemNames();
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
        rpc.update(activeElements, i);
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
    public HashMap <String, Automat> getAutomatsHashMap () {
        return this.automats;
    }
    public HashMap <String,CognitiveSystem> getSystemsHashMap () {
        return this.systems;
    }
    public ArrayList <VisualiseInfo> getVisualiseInfos () {
        return this.visualiseInfos;
    }
    public void setRightPanelCreator (RightPanelCreator rpc) {
        this.rpc = rpc;
    }
    public String getNoSelectedString () {
        return this.noSelected;
    }
    public CognitiveSystem getActiveSystem () {
        if (activeElements.get(0).equals(noSelected))
            return null;
        else 
            return systems.get(activeElements.get(0));
    }
    public AutomatLayer getActiveLayer () {
        if (activeElements.get(1).equals(noSelected))
            return null;
        else {
            ArrayList <AutomatLayer> layers = getActiveSystem().getLayers();
            for (AutomatLayer layer:layers)
                if (layer.id.equals(activeElements.get(1)))
                    return layer;
            
            
            return null;
        }
    }
    public Automat getActiveAutomat () {
        if (activeElements.get(2).equals(noSelected)) {
            return null;
        }
        else {
            ArrayList <Automat> automats = getActiveLayer().getAutomats();
            for (Automat a:automats) 
                if (a.name.equals(activeElements.get(2)))  
                    return a;
            
            return null;
        }
    }
    public State getActiveState () {
        if (activeElements.get(3).equals(noSelected)) {
            return null;
        }
        else {
            try {
                return getActiveAutomat().getStates().get(activeElements.get(3));
            } catch (Exception e) {
                return null;
            }
        }
    }
    public Transition getActiveTransition () {
        if (activeElements.get(4).equals(noSelected)) {
            return null;
        }
        else {
            ArrayList <Transition> transitions = 
                getActiveAutomat().getTransitionsFromState(activeElements.get(3));
            
            for (Transition t:transitions)
                if (t.getId().equals(activeElements.get(4)))
                    return t;
            
            return null;
        }
    }
    public ArrayList <String> getActiveElements () {
        return this.activeElements;
    }
}