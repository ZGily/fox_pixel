package ghh.zgily.pixel;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.graphics.Color;
import android.widget.Toast;
import java.util.ArrayList;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import ghh.zgily.utils.PicFileTool;
import ghh.zgily.colorpicker.ColorPickerDialog;

public class PixelDrawActivity extends AppCompatActivity implements View.OnClickListener , View.OnLongClickListener,ColorPickerDialog.OnColorPickedListener {
    
    
    private PixelDrawView mPixelDrawView;
    private FloatingActionButton mUndoFab;
    private FloatingActionButton mLineFab;
    private FloatingActionButton mMoveDrawFab;
    private FloatingActionButton mSaveFab;
    private FloatingActionButton mQuitFab;
    private ColorView mColorView1;
    private ColorView mColorView2;
    private ColorView mColorView3;
    private ColorView mColorView4;
    private ColorView mColorView5;
    private ColorView mColorView6;
    private ColorView mColorView7;
    private ColorView mColorView8;
    private ColorView mTempColorView;
    
    private ColorPickerDialog mColorPickerDialog;
    
    private boolean isMovePixelDrawView = false;
    private boolean isShouldShowLine = true;
    
    private ArrayList<Integer> defColorList = new ArrayList<Integer>();
    
    int pic_size = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic_draw_layout);
        
        initDefColorList();
        
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        mPixelDrawView = (PixelDrawView) findViewById(R.id.mPixelDrawView);
        mUndoFab = (FloatingActionButton) findViewById(R.id.pic_undo_fab);
        mLineFab = (FloatingActionButton) findViewById(R.id.pic_line_fab);
        mMoveDrawFab = (FloatingActionButton) findViewById(R.id.pic_move_fab);
        mSaveFab = (FloatingActionButton) findViewById(R.id.pic_save_fab);
        mQuitFab = (FloatingActionButton) findViewById(R.id.pic_quit_fab);
        mColorView1 = (ColorView) findViewById(R.id.pic_ColorView1);
        mColorView2 = (ColorView) findViewById(R.id.pic_ColorView2);
        mColorView3 = (ColorView) findViewById(R.id.pic_ColorView3);
        mColorView4 = (ColorView) findViewById(R.id.pic_ColorView4);
        mColorView5 = (ColorView) findViewById(R.id.pic_ColorView5);
        mColorView6 = (ColorView) findViewById(R.id.pic_ColorView6);
        mColorView7 = (ColorView) findViewById(R.id.pic_ColorView7);
        mColorView8 = (ColorView) findViewById(R.id.pic_ColorView8);
        
        setColorList(defColorList);
        
        setSupportActionBar(toolbar);
        mUndoFab.setOnClickListener(this);
        mLineFab.setOnClickListener(this);
        mMoveDrawFab.setOnClickListener(this);
        mSaveFab.setOnClickListener(this);
        mQuitFab.setOnClickListener(this);
        mColorView1.setOnClickListener(this);
        mColorView2.setOnClickListener(this);
        mColorView3.setOnClickListener(this);
        mColorView4.setOnClickListener(this);
        mColorView5.setOnClickListener(this);
        mColorView6.setOnClickListener(this);
        mColorView7.setOnClickListener(this);
        mColorView8.setOnClickListener(this);
        mColorView1.setOnLongClickListener(this);
        mColorView2.setOnLongClickListener(this);
        mColorView3.setOnLongClickListener(this);
        mColorView4.setOnLongClickListener(this);
        mColorView5.setOnLongClickListener(this);
        mColorView6.setOnLongClickListener(this);
        mColorView7.setOnLongClickListener(this);
        mColorView8.setOnLongClickListener(this);
        
        pic_size = getIntent().getExtras().getInt(MainActivity.SIZE_KEY);
        
        mPixelDrawView.setPixelNumber(pic_size);
        
        PicFileTool.writeColorListToFile(this,"defColorList",defColorList);
            
    }
    
    public void initDefColorList()
    {
        defColorList.add(Color.BLACK);
        defColorList.add(Color.WHITE);
        defColorList.add(Color.RED);
        defColorList.add(Color.YELLOW);
        defColorList.add(Color.GREEN);
        defColorList.add(Color.BLUE);
        defColorList.add(Color.MAGENTA);
        defColorList.add(Color.GRAY);
    }
    
    public void setColorList(ArrayList<Integer> mColorList)
    {
        int count = 0;
        mColorView1.setColor(mColorList.get(count++));
        mColorView2.setColor(mColorList.get(count++));
        mColorView3.setColor(mColorList.get(count++));
        mColorView4.setColor(mColorList.get(count++));
        mColorView5.setColor(mColorList.get(count++));
        mColorView6.setColor(mColorList.get(count++));
        mColorView7.setColor(mColorList.get(count++));
        mColorView8.setColor(mColorList.get(count++));
    }
    
    public ArrayList<Integer> getColorList()
    {
        ArrayList<Integer> mColorList = new ArrayList<>();
        mColorList.add(mColorView1.getColor());
        mColorList.add(mColorView2.getColor());
        mColorList.add(mColorView3.getColor());
        mColorList.add(mColorView4.getColor());
        mColorList.add(mColorView5.getColor());
        mColorList.add(mColorView6.getColor());
        mColorList.add(mColorView7.getColor());
        mColorList.add(mColorView8.getColor());
        return mColorList;
    }
    
    public void showColorPickerDialog (int defColor)
    {
        mColorPickerDialog = new ColorPickerDialog.Builder(this,defColor)
            .setOnColorPickedListener(this)
            .setHexValueEnabled(true)
            .build();
        mColorPickerDialog.show();
        return;
    }
    
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.pic_undo_fab:
                mPixelDrawView.undoLastestDraw();
                break;
            case R.id.pic_line_fab:
                isShouldShowLine = !isShouldShowLine;
                mPixelDrawView.setShouldShowLine(isShouldShowLine);
                if (!isShouldShowLine)
                {
                    mLineFab.setBackgroundTintList(ColorStateList.valueOf(0xFF43CBFF));
                }
                else
                {
                    mLineFab.setBackgroundTintList(ColorStateList.valueOf(0xFFF07C82));
                }
                break;
            case R.id.pic_move_fab:
                isMovePixelDrawView = !isMovePixelDrawView;
                mPixelDrawView.setIsMoveView(isMovePixelDrawView);
                if (isMovePixelDrawView)
                {
                    mMoveDrawFab.setBackgroundTintList(ColorStateList.valueOf(0xFF43CBFF));
                }
                else
                {
                    mMoveDrawFab.setBackgroundTintList(ColorStateList.valueOf(0xFFF07C82));
                }
                break;
            case R.id.pic_save_fab:
                try {
                PicFileTool.saveImage(mPixelDrawView.getDrawPicture(),this);
                } catch (Exception e)
                {
                    log(e);
                }
                break;
            case R.id.pic_quit_fab:
                finish();
                break;
           default :
               ColorView v = (ColorView) view;
               mPixelDrawView.setPixelPaintColor(v.getColor());
               break;
        }
    }

    @Override
    public boolean onLongClick(View view)
    {
        mTempColorView = (ColorView) view;
        showColorPickerDialog(mTempColorView.getColor());
        return true;
    }

    @Override
    public void onColorPicked(int color)
    {
        mTempColorView.setColor(color);
        mPixelDrawView.setPixelPaintColor(color);
    }
    
    
    private void log(Exception e)
    {
        Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
    }
     
}
