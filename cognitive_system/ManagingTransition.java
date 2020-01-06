package cognitive_system;

import fsm.*;

public class ManagingTransition extends SimpleTransition {
     String generateEvent;
    public ManagingTransition(String id, String prevStateId, String nextStateId, String eventName, String doiString, String generateEvent) {
        super(id, prevStateId, nextStateId, eventName, doiString);
        this.generateEvent = generateEvent;
    }
    @Override
    protected void makeTransition () {
        System.out.println("generateEvent " + generateEvent);
        automat.getDataWriter().writeEvent(generateEvent);
    }
}