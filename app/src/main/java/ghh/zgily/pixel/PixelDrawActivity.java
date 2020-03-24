package ghh.zgily.pixel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import com.jwchuang.colorpickerdialog.SimpleColorPickerDialog;
import com.jwchuang.colorpickerdialog.ColorPickerDialogListener;
import android.support.annotation.ColorInt;
import android.graphics.Color;
import android.text.SpannableString;

public class PixelDrawActivity extends AppCompatActivity {

    private static final int DIALGE_ID = 520;
    
    private PixelDrawView mPixelDrawView;
    int pic_size = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
        setContentView(R.layout.pic_draw_layout);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
        pic_size = getIntent().getExtras().getInt(MainActivity.SIZE_KEY);
        mPixelDrawView = (PixelDrawView) findViewById(R.id.mPixelDrawView);
        mPixelDrawView.setPixelNumber(pic_size);
        } catch (Exception e){
            Toast.makeText(PixelDrawActivity.this,e.toString(),Toast.LENGTH_LONG).show();
        }
        } catch( Exception e){
            Toast.makeText(PixelDrawActivity.this,e.toString(),Toast.LENGTH_LONG).show();
        }
        showColorDialog();
    }
    /**
     * define dialog func
     */
    private void showColorDialog() {
        SimpleColorPickerDialog colorPickerDialog = SimpleColorPickerDialog.newBuilder()
            //set default color
            .setColor(0xFFFFEB3B)
            //set titile
            .setDialogTitle(R.string.app_name)
            //set presets / custom mode
            .setDialogType(SimpleColorPickerDialog.TYPE_PRESETS)
            //show alpha slider
            .setShowAlphaSlider(true)
            //set callbasck id
            .setDialogId(DIALGE_ID)
            //set if enable custom
            .setAllowCustom(true)
            //set preset colors
            //.setPresets(MY_COLORS)
            //set preset enabled
            .setAllowPresets(false)
            .create();
        colorPickerDialog.setColorPickerDialogListener(pickerDialogListener);
        colorPickerDialog.show(getFragmentManager(), "color-dialog");
    
}

    /**
     * callback for select event
     */
    private ColorPickerDialogListener pickerDialogListener = new ColorPickerDialogListener() {
        @Override
        public void onColorSelected(int dialogId, @ColorInt int color) {
            if (dialogId == DIALGE_ID) {
                //SpannableString sstr = new SpannableString("这是颜色");
                Toast.makeText(PixelDrawActivity.this,""+color,5000).show();
            }
        }

        @Override
        public void onDialogDismissed(int dialogId) {
            if (dialogId == DIALGE_ID) {

            }
        }
    };
    
}
