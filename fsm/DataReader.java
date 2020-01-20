package fsm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;

public class DataReader extends Thread {
    BufferedReader reader;
    BufferedWriter writer;
    public DataReader (BufferedWriter bw) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = bw;
        start();
    }
    public void run () {
        while (true) {
            try {
                String event = reader.readLine();
                writeEvent(event);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public void writeEvent (String event) {
        try {
            writer.write(event + "\n");
            writer.flush();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}