package ghh.zgily.utils;
import android.os.Environment;
import java.io.File;
import java.io.ObjectOutputStream;
import ghh.zgily.struct.PicRVItem;
import java.io.FileOutputStream;
import java.io.IOException;
import android.widget.Toast;
import ghh.zgily.pixel.MainActivity;
import android.content.Context;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import android.graphics.Bitmap;
import java.io.FileNotFoundException;

public class PicFileTool
{
    
    private static String path = Environment.getExternalStorageDirectory()+"/Android/data/ghh.zgily.pixel/file/";
    
    public static void writeToFile (Context context,String filename,List<PicRVItem> list)
    {
        try {
            
            File file = new File(path);
            if (!file.exists())
            {
                file.mkdirs();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path+filename));
            oos.writeObject(list);
            oos.close();
        } catch (IOException e) {
           Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(context,path+filename,Toast.LENGTH_LONG).show();
    }
    
    public static List<PicRVItem> readFromFile (Context context,String filename)
    {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + filename));
            try {
                return (List<PicRVItem>)ois.readObject();
            } catch (ClassNotFoundException e) {
                Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
        return new ArrayList<>();
    }
    
    public static void writeColorListToFile(Context context,String filename,ArrayList<Integer> colorList)
    {
        File f = new File(path+"color/");
        if (!f.exists())
        {
            f.mkdirs();
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f +"/"+ filename));
            oos.writeObject(colorList);
            oos.close();
        } catch (IOException e) {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    
    public static ArrayList<Integer> readColorListFromFile(Context context,String filename)
    {
        File f = new File(path+"color/"+filename);
        if (f.exists())
        {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                ArrayList<Integer> result = (ArrayList<Integer>) ois.readObject();
                return result;
            } catch (Exception e) {
                Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            }

        }
        return new ArrayList<Integer> ();
    }
    
    public static void saveImage(Bitmap bitmap,Context context)
    {
        try {
            FileOutputStream fos = new FileOutputStream(path + "test.png");
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
        } catch (FileNotFoundException e) {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    
}


