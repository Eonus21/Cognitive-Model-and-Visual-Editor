import java.util.HashMap;
import cognitive_system.*;
import fsm.*;
class Main {
    public static void main (String[] args) throws Exception {
        DataController dc = new DataController();
        CognitiveSystem cs = new CognitiveSystemCreator(dc, 2000).getCognitiveSystem();
        cs.start();
    }
}

class CognitiveSystemCreator {
    private CognitiveSystem cs;
    private int dt;
    public CognitiveSystemCreator (DataController dc, int dt) {
        this.dt = dt;
        cs = new CognitiveSystem("Управление трансформатором");
        cs.setDataController(dc);
        createTargetLayer();
        createScenarioLayer();
    }
    void createTargetLayer () {
        AutomatLayer targetLayer = new AutomatLayer("Слой целей");
        HashMap<String, State> states= new HashMap<String, State>();
        Transitions transitions = new Transitions();
        states.put("default", new SimpleState("default", "Default State"));
        states.put("S0", new SimpleState("S0", "Цель:Эффективность"));
        states.put("S1", new SimpleState("S1", "Цель:Продление работоспособности"));
        states.put("S2", new SimpleState("S2", "Цель:Предотвращение перегрева"));
        transitions.addTransition(new AllManagingTransition("start","default","S0","S A0"));
        transitions.addTransition(new ManagingTransition("S0toS1","S0","S1","TX0","","S A1"));
        transitions.addTransition(new ManagingTransition("S0toS2","S0","S2","TX1","","S A2"));
        transitions.addTransition(new ManagingTransition("S2toS0","S2","S0","TX2","","S A0"));
        transitions.addTransition(new ManagingTransition("S1toS0","S1","S0","TX3","","S A0"));
        Automat a = new Automat(states, transitions.getHashMap(), dt, "Target Automat");
        targetLayer.addAutomat(a);
        cs.addLayer(targetLayer);
    }
    void createScenarioLayer () {
        AutomatLayer scenarioLayer = new AutomatLayer("Слой сценариев");
        HashMap<String, State> states= new HashMap<String, State>();
        Transitions transitions = new Transitions();

        states.put("default", new SimpleState("default","Starting automat 0"));
        states.put("S0", new SimpleState("S0","Сценарий:Понижение затрат"));
        states.put("S1", new SimpleState("S1","Сценарий:Повышение эффективности"));
        transitions.addTransition(new AllTransition("start", "default", "S0"));
        transitions.addTransition(new SimpleTransition("S0toS1","S0","S1","SX0",""));
        transitions.addTransition(new SimpleTransition("S1toS0","S1","S0","SX1",""));
        SleepingAutomat sa0 = new SleepingAutomat(states, transitions.getHashMap(), dt, "A0", "S");
        scenarioLayer.addAutomat(sa0);

        states= new HashMap<String, State>();
        transitions = new Transitions();
        states.put("default", new SimpleState("default","Starting automat 1"));
        states.put("S0", new SimpleState("S0","Оптимизация выключением нагрузки"));
        states.put("S1", new SimpleState("S1","Оптимизация по времени (отложить)"));
        states.put("S2", new SimpleState("S2","Оптимизация с учетом прогноза"));
        transitions.addTransition(new AllTransition("start", "default", "S0"));
        transitions.addTransition(new SimpleTransition("S0toS1","S0","S1","SX3",""));
        transitions.addTransition(new SimpleTransition("S0toS2","S0","S2","SX4",""));
        transitions.addTransition(new SimpleTransition("S1toS0","S1","S0","SX5",""));
        transitions.addTransition(new SimpleTransition("S2toS0","S2","S0","SX6",""));
        SleepingAutomat sa1 = new SleepingAutomat(states, transitions.getHashMap(), dt, "A1", "S");
        scenarioLayer.addAutomat(sa1);

        states= new HashMap<String, State>();
        transitions = new Transitions();
        states.put("default", new SimpleState("default","Active automat 2"));
        states.put("S0", new SimpleState("S0","Управление мощностью охлаждения"));
        states.put("S1", new SimpleState("S1","Управление условиями охлаждения"));
        transitions.addTransition(new AllTransition("start", "default", "S0"));
        transitions.addTransition(new SimpleTransition("S0toS1","S0","S1","SX7",""));
        transitions.addTransition(new SimpleTransition("S1toS0","S1","S0","SX8",""));
        SleepingAutomat sa2 = new SleepingAutomat(states, transitions.getHashMap(), dt, "A2", "S");
        scenarioLayer.addAutomat(sa2);


        cs.addLayer(scenarioLayer);
    }
    public CognitiveSystem getCognitiveSystem () {
        return cs;
    }
}