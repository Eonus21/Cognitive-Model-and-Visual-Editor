package fsm;

public abstract class State {
    private  String id;
    public State ( String id) {
        this.id = id;
    }
    public String getId () {
        return this.id;
    }
    public abstract void run();
    public void setId (String s) {
        this.id = s;
    }
} 