package fsm;
public class AllTransition extends Transition {
    public AllTransition(String id, String prevStateId, String nextStateId) {
        super(id, prevStateId, nextStateId);
    }
    public boolean check () {
        return true;
    }
}