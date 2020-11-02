import com.jayway.restassured.mapper.ObjectMapperSerializationContext;

public class VCFModel implements ObjectMapperSerializationContext {
    public String Name;
    public String Size;
    public int Width;
    public int Height;
    public String Align;
    public int XPosition;
    public int YPosition;

    public VCFModel(String Name, String Size,int Width, int Height,String Align, int XPosition, int YPosition ){
        this.Name = Name;
        this.Size = Size;
        this.Width = Width;
        this.Height = Height;
        this.Align = Align;
        this.XPosition = XPosition;
        this.YPosition = YPosition;

    }

    @Override
    public Object getObjectToSerialize() {
        return null;
    }

    @Override
    public <T> T getObjectToSerializeAs(Class<T> aClass) {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public String getCharset() {
        return null;
    }
}

