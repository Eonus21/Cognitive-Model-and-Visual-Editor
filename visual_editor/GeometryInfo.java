package visual_editor;

public class GeometryInfo {
    private int x;
    private int y;
    private int x1;
    private int y1;

    public GeometryInfo (int x,int y,int x1,int y1) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
    }

    public int getX () {
        return this.x;
    }
    public int getX1 () {
        return this.x1;
    }
    public int getY () {
        return this.y;
    }
    public int getY1 () {
        return this.y1;
    }
}