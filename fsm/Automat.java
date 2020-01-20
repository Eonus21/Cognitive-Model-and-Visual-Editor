package fsm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Automat implements Runnable {
    protected DataReader writer;
    public String name;
    private boolean active;
    private double time;
    public ArrayList <String> events;
    private  HashMap <String, State> states;
    private float dt;
    private  HashMap <String, ArrayList<Transition>> transitions;
    private State activeState;
    public Automat ( HashMap <String, State> states,  HashMap <String, ArrayList<Transition>> transitions, int dt, String name) {
        this.states = states;
        this.transitions = transitions;
        this.dt = ((float)dt) / 1000;
        this.events = new ArrayList<String>();
        this.name = name;
        this.active = true;
        setAutomat();
    }
    private void setAutomat (){
        for (Entry <String, ArrayList<Transition>> entry:transitions.entrySet()) {
            for (Transition tr:entry.getValue()) {
                tr.setAutomat(this);
            }
        }
    }
    public void event (String e) {
        events.add(e);
    }
    public State getActiveState () {
        return this.activeState;
    }
    public void setActiveState( String nextStateId) {
        this.activeState = this.states.get(nextStateId);
    }
    public void run () {
        setActiveState("default");
        time = 0;
        while (true) {
            this.update();
            time += dt;
            try {
                Thread.sleep((int)(dt*1000));
            } catch ( Exception e) {
                System.out.println(e);
            }
        }
    }
    private void checkForTransition () {
        try {
            ArrayList<Transition> thTransitions = transitions.get(this.activeState.getId());
            for (int i = 0;i<thTransitions.size();i++) {
                if (thTransitions.get(i).check()) {
                    this.activeState = states.get(thTransitions.get(i).getNextStateId());
                }
            }
        } catch (NullPointerException e) {
            
        }
    }
    private void update() {
        if (active) {
            checkForTransition();
            this.activeState.run();
            if (time % 5 == 0) {
                events.clear();
            }
        }
    }
    public void setActive (boolean active) {
        this.active = active;
    }
    public void setDataWriter (DataReader br) {
        this.writer = br;
    }
    public DataReader getDataWriter () {
        return this.writer;
    }
    public HashMap <String, State> getStates () {
        return this.states;
    }
    public ArrayList <Transition> getTransitionsFromState (String state) {
        return transitions.get(state);
    }
    public boolean isActive () {
        return this.active;
    }
    public HashMap <String, ArrayList<Transition>> getAlltransitions () {
        return this.transitions;
    }
}