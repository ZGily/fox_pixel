package ghh.zgily.struct;
import android.graphics.drawable.Drawable;
import java.io.Serializable;
import ghh.zgily.pixel.R;
import org.xmlpull.v1.XmlPullParser;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.graphics.Canvas;
import android.graphics.Matrix;
import ghh.zgily.utils.PicFileTool;
import android.os.Environment;

public class PicRVItem implements Serializable{
    public String colorpicker;
    public String pic;
    public String name;
    private static String path = Environment.getExternalStorageDirectory()+"/Android/data/ghh.zgily.pixel/file/";
    
    //public boolean isChecked;
    
    public PicRVItem (String name)
    {
        pic = "def";
        this.name = name;
    }
    
    public PicRVItem (String name,String pic)
    {
        this.pic = pic;
        this.name = name;
    }
}
