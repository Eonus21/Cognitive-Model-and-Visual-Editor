package cognitive_system;

import fsm.Transition;
public class AllManagingTransition extends Transition {
    private String generateEvent;
    public AllManagingTransition (String id, String prevStateId, String nextStateId, String generateEvent) {
        super(id, prevStateId, nextStateId);
        this.generateEvent = generateEvent;
    }
    @Override
    public boolean check () {
        automat.getDataWriter().writeEvent(generateEvent);
        return true;
    }
    public String getEventName () {
        return "all";
    }
}