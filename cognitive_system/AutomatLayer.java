package cognitive_system;

import java.util.ArrayList;
import fsm.*;

public class AutomatLayer {
    private ArrayList <Automat> automats;
    public String id; 
    public AutomatLayer (String id) {
        this.id = id;
        automats = new ArrayList<Automat>();
    }
    public void addAutomat (Automat a) {
        automats.add(a);
    }
    public ArrayList <Automat> getAutomats () {
        return automats;
    }
}