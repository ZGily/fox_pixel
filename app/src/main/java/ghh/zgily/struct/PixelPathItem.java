package ghh.zgily.struct;
import android.graphics.Path;
import android.graphics.Paint;

public class PixelPathItem {
    public Path pixelPath;
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
