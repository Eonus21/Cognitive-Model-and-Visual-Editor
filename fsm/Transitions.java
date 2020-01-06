package fsm;

import java.util.ArrayList;
import java.util.HashMap;

public class Transitions {
    private HashMap <String, ArrayList<Transition>> map;
    public Transitions () {
        this.map = new HashMap <String, ArrayList<Transition>> ();
    }
    public void addTransition ( Transition t) {
        ArrayList <Transition> arr;
        try {
            arr = map.get(t.getPrevStateId());
            arr.add(t);
        }
        catch (NullPointerException e){
            arr = new ArrayList<Transition>();
            arr.add(t);
            map.put(t.getPrevStateId(), arr);
        }
    }
    public HashMap <String, ArrayList<Transition>> getHashMap () {
        return this.map;
    }
}