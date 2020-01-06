package cognitive_system;

import java.util.ArrayList;

import fsm.*;

public class CognitiveSystem {
    private ArrayList <AutomatLayer> layers;
    private DataController dc;
    public String id;
    public CognitiveSystem (String id) {
        this.id = id;
        layers = new ArrayList<AutomatLayer>();
    }
    public void addLayer (AutomatLayer al) {
        layers.add(al);
    }
    public ArrayList <AutomatLayer> getLayers () {
        return layers;
    }
    public void setDataController (DataController dc) {
        this.dc = dc;
    }
    public void start () {
        for (AutomatLayer al:layers) {
            for (Automat a:al.getAutomats()) {
                dc.addAutomat(a);
                Thread t = new Thread(a);
                t.start();
            }
        }
    }
}