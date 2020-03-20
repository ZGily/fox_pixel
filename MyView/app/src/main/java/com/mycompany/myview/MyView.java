package com.mycompany.myview;
import android.view.View;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Canvas;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Color;
import android.view.MotionEvent;
import android.graphics.RectF;
import java.util.List;
import java.util.ArrayList;
import android.widget.Toast;

public class MyView extends View {
    Paint mPixelPaint;
    Paint mPaint;
    
    Path mPath;
    Path mLinePath;
    
    int mPixelColor;
    
    boolean isStartMove;
    
    List<DrawablePath> mPathList;
    
    DrawablePath lastPath;
    int step = -1;
    Context context;
    public MyView (Context context)
    {
        super(context);
        initView();
        this.context = context;
    }
    
    public MyView (Context context,AttributeSet attts)
    {
        super(context,attts);
        initView();
        this.context = context;
    }
    
    public MyView (Context context,AttributeSet attrs,int defStyleAttrs)
    {
        super(context,attrs,defStyleAttrs);
        initView();
        this.context = context;
    }
    
    public void initView()
    {
        mPaint = new Paint();
        mPixelPaint = new Paint();
        
        mPath = new Path();
        mLinePath = new Path();
        
        mPathList =new ArrayList<>();
        
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(2);
        
        mPixelPaint.setColor(Color.RED);
        mPixelPaint.setStyle(Paint.Style.FILL);
        mPixelPaint.setStrokeWidth(2);
        
        mPixelColor = Color.RED;
        
        isStartMove = false;
        
        for (int i = 0;i<=50*16;i+=50)
        {
            mLinePath.moveTo(i,0);
            mLinePath.lineTo(i,50*16);
            mLinePath.moveTo(0,i);
            mLinePath.lineTo(50*16,i);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawPath(mPath,mPixelPaint);
       
        
        if (!mPathList.isEmpty())
            for (DrawablePath draw : mPathList)
            {
                canvas.drawPath(draw.path,draw.paint);
            }
            canvas.drawPath(mLinePath,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isStartMove)
        {
        int x = (int) event.getX();
        int y = (int) event.getY();
        x = (x / 50) * 50;
        y = (y / 50) * 50;
        RectF rect = new RectF(x,y,x+50,y+50);
        Path path = new Path();
        path.addRect(rect,Path.Direction.CW);
        Paint paint = new Paint();
        paint.setColor(mPixelColor);
        lastPath = new DrawablePath(path,paint);
        mPathList.add(lastPath);
        ++step;
        mPath = new Path();
        invalidate();
        return true;
        }
        return super.onTouchEvent(event);
    }
    
    public void setPixelColor (int color)
    {
        mPixelColor = color;
    }
    
    public void undoLast ()
    {
        if (step==-1)
        {
            Toast.makeText(context,"无法再往前撤销咯",Toast.LENGTH_SHORT).show();
            return;
        }
        mPathList.remove(step);
        --step;
        invalidate();
    }
    
    public void changeMoveStatus ()
    {
        isStartMove = !isStartMove;
    }
    
}
