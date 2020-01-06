package fsm;

import java.io.BufferedReader;
import java.util.ArrayList;

public class DataWriter extends Thread {
    private BufferedReader reader;
    private ArrayList <Automat> automats;
    public DataWriter (BufferedReader reader) {
        this.reader = reader;
        automats = new ArrayList<Automat>();
        this.start();
    }
    public void run () {
        while (true) {
            try {
                String s = reader.readLine();
                System.out.println("readedEvent " + s);
                event(s);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public void addEvent (String s) {
        event(s);
    }
    public void addAutomat (Automat a) {
        automats.add(a);
    }
    private void event (String e) {
        for (Automat a:automats) {
            a.event(e);
        }
    }
}