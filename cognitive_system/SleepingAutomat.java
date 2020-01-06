package cognitive_system;

import java.util.ArrayList;
import java.util.HashMap;
import fsm.*;

public class SleepingAutomat extends Automat {
    private String layerPrefix;
    private String shortName;
    public SleepingAutomat ( HashMap <String, State> states,  HashMap <String, ArrayList<Transition>> transitions, int dt, String name, String layerPrefix) {
        super(states, transitions, dt, name);
        this.shortName = name.substring(1);
        this.setActive(false);
        this.layerPrefix = layerPrefix;
    }
    @Override
    public void event (String e) {
        String[] data = e.split(" ");
        if (data.length > 1) {//управление, а не событие
            if (data[0].equals(layerPrefix)) {//управление на этом слое
                if (data[1].equals(shortName)) {//запрос на активацию этого автомата
                    setActiveState("default");
                    setActive(true);
                }
                else {//запрос на активацию конкурента
                    setActive(false);
                }
            }
        }
        else {//событие
            events.add(e);
        }
    }
}