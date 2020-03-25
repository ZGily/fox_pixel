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

public class PixelDrawView extends  View {
    public static final int PIXEL_SIZE_16X16 = 16;
    public static final int PIXEL_SIZE_32X32 = 32;
    public static final int PIXEL_SIZE_64X64 = 64;
    //public static final int PIXEL_SIZE_128X128 = 128;
    
    private Paint mLinePaint; //draw line paint
    private Paint mPixelPaint; //draw pixel
    
    private Path mLinePath;
    private Path mTempPath;
    private List<PixelPathItem> mPixelPathList;
    
    private Canvas mPixelCanvas;
    
    private Bitmap mTempBitmap;
    private Bitmap mPixelBitmap;
    
    private Context context;
    
    private int mPixelNumber;
    
    private boolean isShouldShowLine = true;
    
    private boolean isMoveDrawable = false;
    
    private int width;
    
    private int height;
    
    private float side;
    
    public PixelDrawView (Context context)
    {
        super(context);
        initView();
        this.context = context;
    }

    public PixelDrawView (Context context,AttributeSet attts)
    {
        super(context,attts);
        initView();
        this.context = context;
    }

    public PixelDrawView (Context context,AttributeSet attrs,int defStyleAttrs)
    {
        super(context,attrs,defStyleAttrs);
        initView();
        this.context = context;
    }

    public void initView()
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
        
        
    }
    
    public void initLinePath ()
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
    
    public void setPixelNumber (int size)
    {
        this.mPixelNumber = size;
    }
    
    public void setShouldShowLine(boolean status)
    {
        this.isShouldShowLine = status;
    }
    
    public void setPixelPaintColor (int color)
    {
        mPixelPaint.setColor(color);
    }
    
    public RectF getPixelPoint (float x,float y)
    {
        int x_p =(int) ( x / side);
        int y_p = (int) (y / side);
        //Toast.makeText(this.getContext(),""+x_p+" "+y_p,Toast.LENGTH_SHORT).show();
        return new RectF(x_p*side,y_p*side,(x_p+1)*side,(y_p+1)*side);
    }
    
    public Path getPixelCircle (RectF recct)
    {
        return null;
    }
    
    public Path getCanvasLinePath ()
    {
        return null;
    }
    
    public void setIsMoveView (boolean status)
    {
        isMoveDrawable = status;
    }
    

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
        initLinePath();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(mTempPath,mPixelPaint);
        canvas.drawPath(mLinePath,mLinePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isMoveDrawable)
            return false;
        float x = event.getX();
        float y = event.getY();
        mTempPath.addRect(getPixelPoint(x,y),Path.Direction.CW);
        invalidate();
        return true;
    }
    
}
