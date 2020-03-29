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
import ghh.zgily.struct.PixelPathItem;
import android.graphics.BitmapFactory;
import ghh.zgily.struct.PicBitmap;
import android.util.JsonWriter;
import java.io.Writer;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import android.util.JsonReader;
import java.io.FileReader;

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
    /*
    
    public static void writePathListToFile(Context context,String filename,List<PixelPathItem> pathList)
    {
        File f = new File(path+"path/");
        if (!f.exists())
        {
            f.mkdirs();
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f +"/"+ filename));
            oos.writeObject(pathList);
            oos.close();
        } catch (IOException e) {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public static List<PixelPathItem> readPathListFromFile(Context context,String filename)
    {
        File f = new File(path+"path/"+filename);
        if (f.exists())
        {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                List<PixelPathItem> result = (List<PixelPathItem>) ois.readObject();
                return result;
            } catch (Exception e) {
                Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            }

        }
        return new ArrayList<PixelPathItem> ();
    }
    */
    
    public static void writeBitMapToFile(Context context,String filename,PicBitmap bitmap)
    {
        File f = new File(path+"pic/");
        if (!f.exists())
        {
            f.mkdirs();
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f +"/"+ filename));
            oos.writeObject(bitmap);
            oos.close();
        } catch (IOException e) {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public static PicBitmap readBitMapFromFile(Context context,String filename)
    {
        File f = new File(path+"pic/"+filename);
        if (f.exists())
        {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                PicBitmap result = (PicBitmap) ois.readObject();
                return result;
            } catch (Exception e) {
                Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            }

        }
        return new PicBitmap();
    }
    
    public static void writePictureJSONObjectToFile (Context context,String filename,Bitmap bitmap)
    {
        File f = new File(path+"pic/");
        if (!f.exists())
        {
            f.mkdirs();
        }
        try {
            JsonWriter mPicJsonFile = new JsonWriter(new FileWriter(f+"/"+filename));
            int length = bitmap.getWidth();
            mPicJsonFile.setLenient(true);
            mPicJsonFile.beginObject();
            mPicJsonFile.name("size").value(length);
            mPicJsonFile.name("pic");
            mPicJsonFile.beginArray();
            for (int x=0;x<length;++x)
                for (int y=0;y<length;++y)
                    mPicJsonFile.value(bitmap.getPixel(x,y));
            mPicJsonFile.endArray();
            mPicJsonFile.endObject();
            mPicJsonFile.flush();
            mPicJsonFile.close();
        } catch (IOException e) {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    
    public static Bitmap readBitmapFromJsonFile(Context context,String filename)
    {
        File f = new File(path+"pic/"+filename);
        if ((!f.exists())||f.isDirectory())
            return null;
        Bitmap bitmap;
        try {
            JsonReader mPicJsonFile = new JsonReader(new FileReader(f));
            mPicJsonFile.beginObject();
            mPicJsonFile.nextName();
            int size = mPicJsonFile.nextInt();
            bitmap = Bitmap.createBitmap(size,size,Bitmap.Config.ARGB_8888);
            mPicJsonFile.nextName();
            mPicJsonFile.beginArray();
            for (int x=0;x<size;++x)
                for (int y=0;y<size;++y)
                    bitmap.setPixel(x,y,mPicJsonFile.nextInt());
            mPicJsonFile.endArray();
            mPicJsonFile.endObject();
            //Toast.makeText(context,"加载成功",5000).show();
            return bitmap;
        } catch (Exception e) {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
        return null;
    }
    
    public static Bitmap readBitmapFromJsonFile(String filename)
    {
        File f = new File(path+"pic/"+filename);
        if ((!f.exists())||f.isDirectory())
            return null;
        Bitmap bitmap;
        try {
            JsonReader mPicJsonFile = new JsonReader(new FileReader(f));
            mPicJsonFile.beginObject();
            mPicJsonFile.nextName();
            int size = mPicJsonFile.nextInt();
            bitmap = Bitmap.createBitmap(size,size,Bitmap.Config.ARGB_8888);
            mPicJsonFile.nextName();
            mPicJsonFile.beginArray();
            for (int x=0;x<size;++x)
                for (int y=0;y<size;++y)
                    bitmap.setPixel(x,y,mPicJsonFile.nextInt());
            mPicJsonFile.endArray();
            mPicJsonFile.endObject();
            //Toast.makeText(context,"加载成功",5000).show();
            return bitmap;
        } catch (Exception e) {
        }
        return null;
    }
    
    public static void deleteFile (String name)
    {
        File cp = new File(path+"color/"+name+".cp");
        File bp = new File(path+"pic/"+name+".bp");
        if (cp.exists())
        {
            cp.delete();
        }
        if (bp.exists())
        {
            bp.delete();
        }
        return;
    }
    
    public static void saveImage(Bitmap bitmap,String name)
    {
        try {
            File f = new File(Environment.getExternalStorageDirectory()+"/A_FOX_PIC/");
            if (!f.exists())
                f.mkdirs();
            f = new File(f+"/"+name+".png");
                if (!f.exists())
                    f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
        } catch (Exception e) {
            
        }
    }
    
}


