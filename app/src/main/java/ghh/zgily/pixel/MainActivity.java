package ghh.zgily.pixel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.View.OnClickListener;
import android.view.View;
import android.support.design.widget.Snackbar;

public class MainActivity extends AppCompatActivity {
    
    FloatingActionButton fab;
    Toolbar toolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		toolbar=(Toolbar)findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        
		setSupportActionBar(toolbar);
        fab.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View p1) {
                    Snackbar.make(p1,"Hello",Snackbar.LENGTH_LONG).show();
                }
            });

    }


}
