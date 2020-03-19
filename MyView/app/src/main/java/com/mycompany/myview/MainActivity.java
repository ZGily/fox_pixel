package com.mycompany.myview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    MyView myview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
        myview = (MyView) findViewById(R.id.MyView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.undo:
                myview.undoLast();
                break;
            case R.id.white:
                myview.setPixelColor(Color.WHITE);
                break;
            case R.id.black:
                myview.setPixelColor(Color.BLACK);
                break;
            case R.id.red:
                myview.setPixelColor(Color.RED);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
