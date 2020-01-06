import cognitive_system.file_integration.*;
import cognitive_system.*;
import fsm.*;
class Main3 {
    public static void main (String[] args) throws Exception {
        DataController dc = new DataController();
        FileReader fr = new FileReader("./saves/system.txt", "-");
        CognitiveSystem cs = fr.getCognitiveSystems().get("Управление трансформатором");
        cs.setDataController(dc);
        cs.start();
    }
}