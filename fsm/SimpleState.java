package fsm;

public class SimpleState extends State {
    String print;
    public SimpleState (String s, String print) {
        super(s);
        this.print = print;
    }
    public void run () {
        System.out.println(print);
    }
}