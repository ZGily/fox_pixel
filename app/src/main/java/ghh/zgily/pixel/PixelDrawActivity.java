package ghh.zgily.pixel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class PixelDrawActivity extends AppCompatActivity {

    private PixelDrawView mPixelDrawView;
    int pic_size = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic_draw_layout);
        try {
        pic_size = getIntent().getExtras().getInt(MainActivity.SIZE_KEY);
        mPixelDrawView = (PixelDrawView) findViewById(R.id.mPixelDrawView);
        mPixelDrawView.setPixelNumber(pic_size);
        } catch (Exception e){
            Toast.makeText(PixelDrawActivity.this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    
}
