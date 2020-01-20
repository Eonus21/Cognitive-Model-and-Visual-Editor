import fsm.*;
import java.util.HashMap;

class Main2 {
    public static void main (String []args) throws Exception{
        DataController dc = new DataController();

        HashMap<String, State> states= new HashMap<String, State>();
        states.put("s0", new SimpleState("s0","State 0"));
        states.put("s1", new SimpleState("s1","State 1"));
        states.put("s2", new SimpleState("s2","State 2"));
        states.put("s3", new SimpleState("s3","State 3"));
        states.put("default", new SimpleState("default", "State default"));
        Transitions transitions = new Transitions();
        transitions.addTransition(new AllTransition("deef", "default", "s0"));
        transitions.addTransition(new SimpleTransition("tr1","s0","s3","5","Y4:Радоваться"));
        transitions.addTransition(new SimpleTransition("tr2","s3","s3","5","Y5:Ликовать"));
        transitions.addTransition(new SimpleTransition("tr3","s3","s1","2","Y2:Успокаивать"));
        transitions.addTransition(new SimpleTransition("tr4","s0","s1","2","Y2:Успокаивать"));
        transitions.addTransition(new SimpleTransition("tr5","s1","s0","5","Y3:Надеяться"));
        transitions.addTransition(new SimpleTransition("tr6","s1","s2","2","Y1:Ругать"));
        transitions.addTransition(new SimpleTransition("tr7","s2","s2","2","Y0:Брать ремень"));
        transitions.addTransition(new SimpleTransition("tr8","s2","s0","5","Y3:Надеяться"));
        Automat a = new Automat(states, transitions.getHashMap(), 500,"Automat 1");
        dc.addAutomat(a);
        Thread automatThread = new Thread(a);
        automatThread.start();
        Transitions transitions2 = new Transitions();
        transitions2.addTransition(new AllTransition("deef", "default", "s0"));
        transitions2.addTransition(new SimpleTransition("tr1","s0","s3","5","Y4:Радоваться"));
        transitions2.addTransition(new SimpleTransition("tr2","s3","s3","5","Y5:Ликовать"));
        transitions2.addTransition(new SimpleTransition("tr3","s3","s1","2","Y2:Успокаивать"));
        transitions2.addTransition(new SimpleTransition("tr4","s0","s1","2","Y2:Успокаивать"));
        transitions2.addTransition(new SimpleTransition("tr5","s1","s0","5","Y3:Надеяться"));
        transitions2.addTransition(new SimpleTransition("tr6","s1","s2","2","Y1:Ругать"));
        transitions2.addTransition(new SimpleTransition("tr7","s2","s2","2","Y0:Брать ремень"));
        transitions2.addTransition(new SimpleTransition("tr8","s2","s0","5","Y3:Надеяться"));
        Automat a2 = new Automat(states, transitions2.getHashMap(), 500,"Automat 2");
        dc.addAutomat(a2);
        Thread automatThread2 = new Thread(a2);
        automatThread2.start();
        //dc.createEvent("start");
    }
}

