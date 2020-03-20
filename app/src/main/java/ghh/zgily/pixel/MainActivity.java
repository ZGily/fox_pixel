package ghh.zgily.pixel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.View.OnClickListener;
import android.view.View;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import java.util.List;
import ghh.zgily.struct.PicRVItem;
import java.util.ArrayList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.DividerItemDecoration;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView picRecycletView;
    FloatingActionButton fab;
    FloatingActionButton fab1;
    FloatingActionButton fab2;
    boolean isSubFabShow = false;
    Toolbar toolbar;
    
    List<PicRVItem> data;
    PicRVAdapter adapter;
    
    private boolean isShowCheck = false;
    
    private List<String> checkList = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		toolbar=(Toolbar)findViewById(R.id.toolbar);
        picRecycletView = (RecyclerView) findViewById(R.id.recyclerview);
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
                        if (isShowCheck)
                        {
                            isShowCheck = !isShowCheck;
                            adapter.setShouldShowCheckBox(false);
                            refreshUI();
                            checkList.clear();
                        }
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
        fab1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View p1) {
                    int poslist[] = new int[checkList.size()];
                    for (String pos : checkList)
                    {
                        int i = checkList.indexOf(pos);
                        poslist[i] = Integer.parseInt(pos);
                    }
                    poslist = bubbleSort(poslist);
                    int haveDelete = 0;
                    for (int index: poslist)
                    {
                        data.remove(index-haveDelete);
                        ++haveDelete;
                    }
                    refreshUI();
                    fab.callOnClick();
                }
            });
        data = new ArrayList<>();
        data.add(new PicRVItem("你好"));
        data.add(new PicRVItem("你好"));
        data.add(new PicRVItem("高欢欢"));
        data.add(new PicRVItem("喜欢"));
        data.add(new PicRVItem("wjy"));
        data.add(new PicRVItem("你好"));
        data.add(new PicRVItem("你好"));
        data.add(new PicRVItem("高欢欢"));
        data.add(new PicRVItem("喜欢"));
        data.add(new PicRVItem("wjy"));
        data.add(new PicRVItem("你好"));
        data.add(new PicRVItem("你好"));
        data.add(new PicRVItem("高欢欢"));
        data.add(new PicRVItem("喜欢"));
        data.add(new PicRVItem("wjy"));
        data.add(new PicRVItem("你好"));
        data.add(new PicRVItem("你好"));
        data.add(new PicRVItem("高欢欢"));
        data.add(new PicRVItem("喜欢"));
        data.add(new PicRVItem("wjy"));
        adapter = new PicRVAdapter(data);
        picRecycletView.setLayoutManager(new LinearLayoutManager(this));
        picRecycletView.setAdapter(adapter);
        picRecycletView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        picRecycletView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter.setOnItemClick(new PicRVAdapter.onItemClick() {
                @Override
                public void onClick(View view, int pos) {
                    if (isShowCheck)
                    {
                    if (checkList.contains(String.valueOf(pos))) {
                        checkList.remove(String.valueOf(pos));
                        Toast.makeText(MainActivity.this,"remove:"+pos,Toast.LENGTH_SHORT).show();
                    } else {
                        checkList.add(String.valueOf(pos));
                        Toast.makeText(MainActivity.this,"add:"+pos,Toast.LENGTH_SHORT).show();
                    }
                    }
                }
                @Override
                public boolean onLongClick(View view, int pos) {
                    if (isShowCheck) {
                        //mBtn.setVisibility(View.GONE);
                        adapter.setShouldShowCheckBox(false);
                        refreshUI();
                        checkList.clear();
                    } else {
                        adapter.setShouldShowCheckBox(true);
                        refreshUI();
                        checkList.add(String.valueOf(pos));
                        Toast.makeText(MainActivity.this,"add:"+pos,Toast.LENGTH_SHORT).show();
                        //mBtn.setVisibility(View.VISIBLE);
                        fab1.setBackgroundColor(0xFFFFFF);
                        
                    }
                    
                    isShowCheck = !isShowCheck;
                    fab.callOnClick();
                    return true;
                }

            });
    }

    public static int[] bubbleSort(int[] array){
        if(array == null)
            return array;
        int len = array.length;
        for(int i = len - 1; i > 0; i--){
            for(int j = 0; j < i; j++){
                if(array[j] > array[j + 1]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
    
    private void refreshUI()
    {
        if (adapter == null) {
            adapter = new PicRVAdapter(data);
            picRecycletView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
   }
}
