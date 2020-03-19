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

public class MyView extends View {
    Paint mPixelPaint;
    Paint mPaint;
    
    Path mPath;
    Path mLinePath;
    
    public MyView (Context context)
    {
        super(context);
        initView();
    }
    
    public MyView (Context context,AttributeSet attts)
    {
        super(context,attts);
        initView();
    }
    
    public MyView (Context context,AttributeSet attrs,int defStyleAttrs)
    {
        super(context,attrs,defStyleAttrs);
        initView();
    }
    
    public void initView()
    {
        mPaint = new Paint();
        mPixelPaint = new Paint();
        
        mPath = new Path();
        mLinePath = new Path();
        
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(2);
        
        mPixelPaint.setColor(Color.RED);
        mPixelPaint.setStyle(Paint.Style.FILL);
        mPixelPaint.setStrokeWidth(2);
        
        for (int i = 0;i<=500;i+=50)
        {
            mLinePath.moveTo(i,0);
            mLinePath.lineTo(i,500);
            mLinePath.moveTo(0,i);
            mLinePath.lineTo(500,i);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPixelPaint);
        canvas.drawPath(mLinePath,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        x = (x / 50) * 50;
        y = (y / 50) * 50;
        mPath.addRect(new RectF(x,y,x+50,y+50),Path.Direction.CCW);
        
        invalidate();
        return super.onTouchEvent(event);
    }
    
}
