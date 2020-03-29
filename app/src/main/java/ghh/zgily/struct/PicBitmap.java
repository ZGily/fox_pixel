package ghh.zgily.struct;
import java.io.Serializable;
import android.graphics.Bitmap;

public class PicBitmap implements Serializable {
    public int[][] bytearray;
    public int size;
    public PicBitmap()
    {
        Bitmap bitmap = Bitmap.createBitmap(16,16,Bitmap.Config.ARGB_8888);
        size = 16;
        bytearray = new int[64][64];
        int side = bitmap.getWidth();
        //int[][] bit = new int[64][64];
        for (int x=0;x<side;++x)
            for (int y=0;y<side;++y)
                bytearray[x][y] = bitmap.getPixel(x,y);
    }
    public PicBitmap(Bitmap bitmap)
    {
        size = bitmap.getWidth();
        //bitmap2byte(pic);
        bytearray = new int[64][64];
        int side = bitmap.getWidth();
        //int[][] bit = new int[64][64];
        for (int x=0;x<side;++x)
            for (int y=0;y<side;++y)
                bytearray[x][y] = bitmap.getPixel(x,y);
    }
    
    public  void bitmap2byte (Bitmap bitmap)
    {
        int side = bitmap.getWidth();
        //int[][] bit = new int[64][64];
        for (int x=0;x<side;++x)
            for (int y=0;y<side;++y)
                bytearray[x][y] = bitmap.getPixel(x,y);
    }
    
    public Bitmap byte2bitmap ()
    {
        Bitmap bitmap = Bitmap.createBitmap(size,size,Bitmap.Config.ARGB_8888);
        for (int x=0;x<size;++x)
            for (int y=0;y<size;++y)
                bitmap.setPixel(x,y,bytearray[x][y]);
       return bitmap;
    }
}
