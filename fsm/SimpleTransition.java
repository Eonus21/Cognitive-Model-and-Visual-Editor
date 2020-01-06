package fsm;

public class SimpleTransition extends Transition {
    private String eventName;
    private String doingStr;
    public SimpleTransition (String id, String prevStateId, String nextStateId, String eventName, String doiString) {
        super(id, prevStateId, nextStateId);
        this.eventName = eventName;
        doingStr = doiString;
    }
    @Override
    public boolean check () {
        int a = automat.events.indexOf(eventName);
        if (a>-1) {
            automat.events.remove(eventName);
            if (doingStr!="") System.out.println(doingStr);
            makeTransition();
            return true;
        }
        else {
            return false;
        }
    }
    protected void makeTransition () {

    }
}