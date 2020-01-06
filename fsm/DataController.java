package fsm;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
public class DataController {
    private PipedInputStream pInputStream;
    private PipedOutputStream pOutputStream;
    private BufferedReader reader;
    private BufferedWriter writer;
    private DataReader dataReader;
    private DataWriter dataWriter;
    public DataController () throws Exception{
        pInputStream = new PipedInputStream();
        pOutputStream = new PipedOutputStream();
        pOutputStream.connect(pInputStream);
        reader = new BufferedReader(new InputStreamReader(pInputStream));
        writer = new BufferedWriter(new OutputStreamWriter(pOutputStream));
        dataReader = new DataReader (writer);
        dataWriter = new DataWriter (reader); 
    }
    public BufferedReader getReader () {
        return this.reader;
    }
    public DataReader getDataReader () {
        return dataReader;
    }
    public void createEvent (String event) {
        dataWriter.addEvent(event);
    }
    public void addAutomat (Automat a) {
        dataWriter.addAutomat(a);
        a.setDataWriter(dataReader);
    }
}

