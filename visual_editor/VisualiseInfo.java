package visual_editor;

public class VisualiseInfo {
    private String elementType;
    private String elementName;
    private GeometryInfo info;
    private String parentName;
    public VisualiseInfo(String elementName,String elementType, GeometryInfo info, String parentName) {
        this.elementName = elementName;
        this.elementType = elementType;
        this.parentName = parentName;
        this.info = info;
    }
    public String getElementType () {
        return this.elementType;
    }
    public String getElementName () {
        return this.elementName;
    }
    public GeometryInfo getGeometryInfo () {
        return this.info;
    }
    public String getParentName () {
        return this.parentName;
    }
}