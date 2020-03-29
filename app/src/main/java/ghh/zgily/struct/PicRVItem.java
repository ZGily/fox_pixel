package ghh.zgily.struct;
import java.io.Serializable;

public class PicRVItem implements Serializable {
    
    public String name;
    
    public int size;
    
    public PicRVItem (String name,int size)
    {
        this.name = name;
        this.size = size;
    }
    
}
