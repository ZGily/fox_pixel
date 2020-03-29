package ghh.zgily.pixel;
import android.view.View;
import android.graphics.Paint;
import android.graphics.Path;
import ghh.zgily.struct.PixelPathItem;
import java.util.List;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.content.Context;
import android.util.AttributeSet;
import java.util.ArrayList;
import android.view.MotionEvent;
import android.graphics.RectF;
import android.graphics.Color;
import android.widget.Toast;
import android.graphics.BitmapFactory;
import ghh.zgily.struct.PicBitmap;
import ghh.zgily.utils.PicFileTool;

public class PixelDrawView extends  View {
    public static final int PIXEL_SIZE_16X16 = 16;
    public static final int PIXEL_SIZE_32X32 = 32;
    public static final int PIXEL_SIZE_64X64 = 64;
    //public static final int PIXEL_SIZE_128X128 = 128;
    public static final String BIT_PIC = ".bp";
    public String filename = "ilovewjy";
    private Paint mLinePaint; //draw line paint
    private Paint mPixelPaint; //draw pixel
    
    private Path mLinePath;
    private Path mTempPath;
    private List<PixelPathItem> mPixelPathList;
    
    private Canvas mPixelCanvas;
    
    private Bitmap mPixelBitmap;
    
    private Context context;
    
    private int mPixelNumber;
    
    private boolean isShouldShowLine = true;
    
    private boolean isMoveDrawable = false;
    
    private boolean isTouching = false;
    
    private int width;
    
    private int height;
    
    private int mStepCounter = -1;
    
    private int[][] mPixelPic = new int[128][128];
    
    private float side;
    
    public PixelDrawView (Context context)
    {
        super(context);
        initView();
        this.context = context;
        filename = PixelDrawActivity.filename;
    }

    public PixelDrawView (Context context,AttributeSet attts)
    {
        super(context,attts);
        initView();
        this.context = context;
        filename = PixelDrawActivity.filename;
    }

    public PixelDrawView (Context context,AttributeSet attrs,int defStyleAttrs)
    {
        super(context,attrs,defStyleAttrs);
        initView();
        this.context = context;
        filename = PixelDrawActivity.filename;
    }

    private void initView()
    {
        mLinePaint = new Paint();
        mPixelPaint = new Paint();
        
        mLinePath = new Path();
        mTempPath = new Path();
        mPixelPathList = new ArrayList<>();
        
        mPixelCanvas = new Canvas();
        
        mPixelPaint.setStyle(Paint.Style.FILL);
        mPixelPaint.setStrokeWidth(2);
        mPixelPaint.setColor(Color.RED);
        
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mLinePaint.setStrokeWidth(2);
        
        mPixelPaint.setColor(Color.BLACK);
        
        this.setDrawingCacheEnabled(true);
    }
    
    private void initLinePath ()
    {
        side =( (float) width) / mPixelNumber;
        //if (mLinePath.isEmpty())

        for (int i=0;i<=mPixelNumber;++i)
        {
            mLinePath.moveTo(i*side,0);
            mLinePath.lineTo(i*side,width);
            mLinePath.moveTo(0,i*side);
            mLinePath.lineTo(width,i*side);
        }
        
        //Toast.makeText(this.getContext(),""+side,Toast.LENGTH_SHORT).show();
        //mLinePath.addRect(0,0,800,800,Path.Direction.CW);
        invalidate();
        
    }
    
    public void setDrawPathList(List<PixelPathItem> pl)
    {
        this.mPixelPathList = pl;
        invalidate();
    }
    
    public void setPixelNumber (int size)
    {
        this.mPixelNumber = size;
    }
    
    public void setShouldShowLine(boolean status)
    {
        this.isShouldShowLine = status;
        invalidate();
    }
    
    public void setPixelPaintColor (int color)
    {
        mPixelPaint.setColor(color);
    }
    
    private RectF getPixelPoint (float x,float y)
    {
        int x_p =(int) ( x / side);
        int y_p = (int) (y / side);
        //Toast.makeText(this.getContext(),""+x_p+" "+y_p,Toast.LENGTH_SHORT).show();
        return new RectF(x_p*side,y_p*side,(x_p+1)*side,(y_p+1)*side);
    }
    
    private void getPixelArray()
    {
        Bitmap bitmap = getDrawingCache();
        float half = side / 2;
        for (int x=0;x<mPixelNumber;++x)
        {
            for (int y=0;y<mPixelNumber;++y)
            {
                int p = bitmap.getPixel((int)(x*side+half),(int)(y*side+half));
                if (p==Color.WHITE)
                {
                    mPixelPic[x][y] = Color.TRANSPARENT;
                }
                else
                {
                    mPixelPic[x][y] = p;
                }
            }
        }
    }
    
    public void setFileName(String name)
    {
        this.filename = name;
        //this.loadPictureFromBitmap(PicFileTool.readBitmapFromJsonFile(context,filename+BIT_PIC));
        //invalidate();
    }
    
    
    public void loadPictureFromBitmap(Bitmap bitmap)
    {
        if (bitmap==null)
            return;
        int length = bitmap.getWidth();
        for (int x=0;x<length;++x)
            for (int y=0;y<length;++y)
            {
                int p_c = bitmap.getPixel(x,y);
                if (p_c==Color.WHITE||p_c==Color.TRANSPARENT)
                    continue;
                Path temp = new Path();
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeWidth(2);
                temp.addRect(new RectF(x*side,y*side,(x+1)*side,(y+1)*side),Path.Direction.CW);
                paint.setColor(p_c);
                mPixelPathList.add(new PixelPathItem(temp,paint));
                ++mStepCounter;
            }
       //Toast.makeText(context,"绘制成功"+mPixelPathList.size(),Toast.LENGTH_LONG).show();
       invalidate();
    }
    
    /*
    public List<PixelPathItem> getPixelPathList()
    {
        return this.mPixelPathList;
    }
    */
    /*
    private int getPixelX(float x)
    {
        int x_p =(int) ( x / side);
        return x_p;
    }
    
    private int getPixelY(float y)
    {
        int y_p = (int) (y / side);
        return y_p;
    }
    */
    /*
    private Path getPixelCircle (RectF recct)
    {
        return null;
    }
    
    private Path getCanvasLinePath ()
    {
        return null;
    }
    */
    public Bitmap getDrawPicture ()
    {
        getPixelArray();
        Bitmap bitmap = Bitmap.createBitmap(mPixelNumber,mPixelNumber,Bitmap.Config.ARGB_8888);
        for (int x = 0;x<mPixelNumber;++x)
            for (int y = 0;y<mPixelNumber;++y)
                bitmap.setPixel(x,y,mPixelPic[x][y]);
        return bitmap;
    }
    
    public void setIsMoveView (boolean status)
    {
        isMoveDrawable = status;
    }
    
    public void undoLastestDraw()
    {
        if (mStepCounter<0)
        {
            Toast.makeText(context,"不能再往前撤销了ヾ(･ε･｀*)",Toast.LENGTH_LONG).show();
            return;
        }
        mPixelPathList.remove(mStepCounter);
        mStepCounter--;
        invalidate();
    }
    /*
    public void setPixelPoint(int x,int y,int color)
    {
        mPixelPic[x][y] = color;
    }
    */
    private void onTouchStart(RectF rect)
    {
        mTempPath .addRect(rect,Path.Direction.CW);
        isTouching = true;
    }
    
    private void onTouchEnd()
    {
        ++mStepCounter;
        isTouching = false;
        Paint paint = new Paint();
        paint.set(mPixelPaint);
        mPixelPathList.add(new PixelPathItem(mTempPath,paint));
        mTempPath = new Path();
        invalidate();
    }
    
    private void redraw(Canvas cns)
    {
        for (PixelPathItem item:mPixelPathList)
        {
            cns.drawPath(item.getPixelPath(),item.getPixelPaint());
        }
    }
    

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
        this.loadPictureFromBitmap(PicFileTool.readBitmapFromJsonFile(context,filename+BIT_PIC));
        initLinePath();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        redraw(canvas);
        if (isTouching)
            canvas.drawPath(mTempPath,mPixelPaint);
        if (isShouldShowLine)
            canvas.drawPath(mLinePath,mLinePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isMoveDrawable)
            return false;
        float x = event.getX();
        float y = event.getY();
        RectF rect = getPixelPoint(x,y);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                onTouchStart(rect);
                break;
            case MotionEvent.ACTION_UP:
                onTouchStart(rect);
                onTouchEnd();
                return true;
        }
        //setPixelPoint(getPixelX(x),getPixelY(y),mPixelPaint.getColor());
        mTempPath.addRect(getPixelPoint(x,y),Path.Direction.CW);
        invalidate();
        return true;
    }
    
}
