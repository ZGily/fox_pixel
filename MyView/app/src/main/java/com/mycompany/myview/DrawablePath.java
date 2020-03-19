package com.mycompany.myview;
import android.graphics.Path;
import android.graphics.Paint;

public class DrawablePath {
    Path path;
    Paint paint;
    
    public DrawablePath (Path path,Paint paint)
    {
        this.path = path;
        this.paint = paint;
    }
    
    public DrawablePath setPaint(Paint mPaint)
    {
        this.paint = mPaint;
        return this;
    }
    
    public DrawablePath setPath(Path mPath)
    {
        this.path = mPath;
        return this;
    }
}
