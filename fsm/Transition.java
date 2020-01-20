package fsm;

public abstract class Transition {
    private  String id;
    private  String prevStateId;
    private  String nextStateId;
    protected Automat automat;
    public Transition ( String id,  String prevStateId,  String nextStateId) {
        this.id = id;
        this.prevStateId = prevStateId;
        this.nextStateId = nextStateId;
    }
    public void setAutomat (Automat a) {
        this.automat = a;
    }
    public String getId () {
        return this.id;
    }
    public void setId (String s) {
        this.id = s;
    }
    public abstract boolean check();
    public abstract String getEventName();
    public String getNextStateId () {
        return this.nextStateId;
    }
    public String getPrevStateId () {
        return this.prevStateId;
    }
    public void setPrevStateId (String s) {
        this.prevStateId = s;
    }
}