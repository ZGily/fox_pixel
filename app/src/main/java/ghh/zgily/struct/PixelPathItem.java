package ghh.zgily.struct;
import android.graphics.Paint;
import android.graphics.Path;

public class PixelPathItem{
    public Path pixelPath;
    //public int paintColor;
    public Paint pixelPaint; 
    
    public PixelPathItem (Path path,Paint paint)
    {
        this.pixelPath = path;
        this.pixelPaint = paint;
    }
    
    public Path getPixelPath ()
    {
        return this.pixelPath;
    }
    
    public Paint getPixelPaint ()
    {
        return this.pixelPaint;
    }
    
}
