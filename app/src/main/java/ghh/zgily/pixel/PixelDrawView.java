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

public class PixelDrawView extends  View {
    public static final int PIXEL_SIZE_16X16 = 16;
    public static final int PIXEL_SIZE_32X32 = 32;
    public static final int PIXEL_SIZE_64X64 = 64;
    public static final int PIXEL_SIZE_128X128 = 128;
    
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
        
        for (int i = 0;i<=50*16;i+=50)
        {
            mLinePath.moveTo(i,0);
            mLinePath.lineTo(i,50*16);
            mLinePath.moveTo(0,i);
            mLinePath.lineTo(50*16,i);
        }
    }
    
    public void setPixelNumber (int size)
    {
        this.mPixelNumber = size;
    }
    
    public void setShouldShowLine(boolean status)
    {
        this.isShouldShowLine = status;
    }
    
    public RectF getPixelPoint (float x,float y)
    {
        return null;
    }
    
    public Path getPixelCircle (RectF recct)
    {
        return null;
    }
    
    public Path getCanvasLinePath ()
    {
        return null;
    }
    
    
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
    
}
