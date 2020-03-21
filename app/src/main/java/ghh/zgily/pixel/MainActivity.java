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
import android.Manifest;
import pub.devrel.easypermissions.EasyPermissions;
import android.support.annotation.NonNull;
import pub.devrel.easypermissions.AppSettingsDialog;
import ghh.zgily.utils.PicFileTool;
import android.graphics.Bitmap;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
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
        
        requestRxPermissions();
        
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
                    if (isShowCheck)
                    {
                        fab1.setImageResource(R.drawable.ic_delete);
                        fab2.setImageResource(R.drawable.ic_export);
                    }
                    else
                    {
                        fab1.setImageResource(R.drawable.ic_open);
                        fab2.setImageResource(R.drawable.ic_add);
                    }
                    
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
                        fab.setRotation(180);
                    }
                    isSubFabShow = !isSubFabShow;
                }
            });
        fab1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View p1) {
                    if (isShowCheck)
                    {
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
                    PicFileTool.writeToFile(MainActivity.this,"test",data);
                    fab.callOnClick();
                    }
                }
            });
            
        fab2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View p1) {
                    if (!isShowCheck)
                    {
                        data.add(new PicRVItem("i love wjy"));
                        refreshUI();
                        PicFileTool.writeToFile(MainActivity.this,"test",data);
                        fab.callOnClick();
                    }
                }
            });
        /*
        data = new ArrayList<>();
        data.add(new PicRVItem("1"));
        data.add(new PicRVItem("2"));
        data.add(new PicRVItem("3"));
        data.add(new PicRVItem("4"));
        data.add(new PicRVItem("5"));
        data.add(new PicRVItem("6"));
        data.add(new PicRVItem("7"));
        data.add(new PicRVItem("8"));
        data.add(new PicRVItem("9"));
        data.add(new PicRVItem("10"));
        data.add(new PicRVItem("12"));
        data.add(new PicRVItem("12"));
        data.add(new PicRVItem("13"));
        data.add(new PicRVItem("14"));
        data.add(new PicRVItem("15"));
        data.add(new PicRVItem("16"));
        data.add(new PicRVItem("17"));
        data.add(new PicRVItem("高欢欢"));
        data.add(new PicRVItem("喜欢"));
        data.add(new PicRVItem("wjy"));
        PicFileTool.writeToFile(this,"test",data);
        */
        data = PicFileTool.readFromFile(this,"test");
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
                        //Toast.makeText(MainActivity.this,"remove:"+pos,Toast.LENGTH_SHORT).show();
                    } else {
                        checkList.add(String.valueOf(pos));
                        //Toast.makeText(MainActivity.this,"add:"+pos,Toast.LENGTH_SHORT).show();
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
                        //Toast.makeText(MainActivity.this,"add:"+pos,Toast.LENGTH_SHORT).show();
                        //mBtn.setVisibility(View.VISIBLE);
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
   
   //权限区
   
   
   
   private void  requestRxPermissions()
   {

       EasyPermissions.requestPermissions(this,
                                          "本软件需要储存权限",
                                          R.string.yes,
                                          R.string.no,
                                          1,
                                          Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                          Manifest.permission.READ_EXTERNAL_STORAGE);
   }

   @Override
   public void onPermissionsGranted(int p1, List<String> p2)
   {
       
   }

   @Override
   public void onPermissionsDenied(int requestCode, List<String> perms)
   {
       if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
           //Toast.makeText(this, "已拒绝权限" + sb + "并不再询问" , Toast.LENGTH_SHORT).show();
           new AppSettingsDialog
               .Builder(this)
               .setRationale("此功能需要储存权限，否则无法正常使用，是否打开设置")
               .setPositiveButton("好")
               .setNegativeButton("不行")
               .build()
               .show();
       }
       
   }
   
   
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
   
}
