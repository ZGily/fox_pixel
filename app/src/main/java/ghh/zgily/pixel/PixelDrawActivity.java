package ghh.zgily.pixel;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.graphics.Color;
import android.widget.Toast;

public class PixelDrawActivity extends AppCompatActivity implements View.OnClickListener , View.OnLongClickListener {
    
    private PixelDrawView mPixelDrawView;
    private FloatingActionButton mUndoFab;
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
    
    private boolean isMovePixelDrawView = false;
    
    int pic_size = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic_draw_layout);
        
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        mPixelDrawView = (PixelDrawView) findViewById(R.id.mPixelDrawView);
        mUndoFab = (FloatingActionButton) findViewById(R.id.pic_undo_fab);
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
        
        setSupportActionBar(toolbar);
        mUndoFab.setOnClickListener(this);
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
    }
    
    
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.pic_undo_fab:
                
                break;
            case R.id.pic_move_fab:
                isMovePixelDrawView = !isMovePixelDrawView;
                mPixelDrawView.setIsMoveView(isMovePixelDrawView);
                break;
            case R.id.pic_save_fab:
                
                break;
            case R.id.pic_quit_fab:
                finish();
                break;
           default :
               
               break;
        }
    }

    @Override
    public boolean onLongClick(View view)
    {
        ColorView v = (ColorView) view;
        v.setColor(Color.YELLOW);
        return false;
    }
    
    private void log(Exception e)
    {
        Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
    }
     
}
