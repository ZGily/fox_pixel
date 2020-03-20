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
    FloatingActionButton fab1;
    FloatingActionButton fab2;
    boolean isSubFabShow = false;
    Toolbar toolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		toolbar=(Toolbar)findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab1.hide();
        fab2.hide();
		setSupportActionBar(toolbar);
        fab.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View p1) {
                    //Snackbar.make(p1,"Hello",Snackbar.LENGTH_LONG).show();
                    if (isSubFabShow)
                    {
                        fab1.hide();
                        fab2.hide();
                        fab.setRotation(0);
                    }
                    else
                    {
                        fab1.show();
                        fab2.show();
                        fab.setRotation(45);
                    }
                    isSubFabShow = !isSubFabShow;
                }
            });

    }


}
