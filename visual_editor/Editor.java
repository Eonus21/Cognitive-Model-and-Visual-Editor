package visual_editor;

public class Editor {
    public static void main(String[] args) throws Exception {
        Manager manager = new Manager();
        manager.addFile("/home/oleksiy/Рабочий стол/Проекты/Java/cognitive_model/saves/system.txt", "-");
        MainFrame frame = new MainFrame(manager);
        Visualiser visualiser = new Visualiser(frame.getCanvas());
    }
}