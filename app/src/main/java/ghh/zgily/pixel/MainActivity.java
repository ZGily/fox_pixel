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
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks,View.OnClickListener,PicRVAdapter.onItemClick {
    
    public static final String SIZE_KEY = "SIZE";
    public static final String NAME_KEY = "NAME";
    RecyclerView picRecycletView;
    FloatingActionButton fab;
    FloatingActionButton mOpenDeleteFab;
    FloatingActionButton mAddExportFab;
    boolean isSubFabShow = false;
    Toolbar toolbar;
    
    List<PicRVItem> data;
    PicRVAdapter adapter;
    
    private boolean isShowCheck = false;
    
    private List<String> checkList = new ArrayList<>();
    
    private MyDialog mDialog;
    
    
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        requestRxPermissions();
        
		toolbar=(Toolbar)findViewById(R.id.toolbar);
        picRecycletView = (RecyclerView) findViewById(R.id.recyclerview);
        fab = (FloatingActionButton) findViewById(R.id.MainFab);
        mOpenDeleteFab = (FloatingActionButton) findViewById(R.id.SubFabDown);
        mAddExportFab = (FloatingActionButton) findViewById(R.id.SubFabUp);
        mOpenDeleteFab.hide();
        mAddExportFab.hide();
		setSupportActionBar(toolbar);
        fab.setOnClickListener(this);
        
        mOpenDeleteFab.setOnClickListener(new View.OnClickListener(){
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
                        PicFileTool.deleteFile(data.get(index-haveDelete).name);
                        data.remove(index-haveDelete);
                        ++haveDelete;
                    }
                    refreshUI();
                    PicFileTool.writeToFile(MainActivity.this,"data",data);
                    fab.callOnClick();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"本功能正在开发中!",Toast.LENGTH_LONG).show();
                    }
                }
            });
            
        mAddExportFab.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View p1) {
                    if (!isShowCheck)
                    {
                        showMyDialog();
                        //data.add(new PicRVItem("i love wjy"));
                        
                    }
                    else
                    {
                        int poslist[] = new int[checkList.size()];
                        for (String pos : checkList)
                        {
                            int i = checkList.indexOf(pos);
                            poslist[i] = Integer.parseInt(pos);
                        }
                        poslist = bubbleSort(poslist);
                        for (int pos:poslist)
                        {
                            Bitmap bitmap = PicFileTool.readBitmapFromJsonFile(data.get(pos).name+".bp");
                            if (bitmap==null)
                            {
                                Toast.makeText(MainActivity.this,"导出之前一定要先保存哦😊",Toast.LENGTH_LONG).show();
                            }
                            PicFileTool.saveImage(bitmap,data.get(pos).name);
                        }
                        Snackbar.make(fab,"已成功将文件保存在/A_FOX_PIC文件夹😊",Snackbar.LENGTH_LONG).show();
                    }
                }
            });
        
        data = PicFileTool.readFromFile(this,"data");
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
                    else
                    {
                        
                        try{
                        String name = data.get(pos).name;
                        int size = data.get(pos).size;
                        startPicDrawActivity(name,size);
                        } catch (Exception e)
                        {
                            Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
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

    @Override
    protected void onStart() {
        super.onStart();
        refreshUI();
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
   
   public void startPicDrawActivity (String name,int size)
   {
       try {
       Intent intent = new Intent(MainActivity.this,PixelDrawActivity.class);
       intent.putExtra(NAME_KEY,name);
       intent.putExtra(SIZE_KEY,size);
       startActivity(intent);
       }
       catch (Exception e){
           Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
       }
   }
   
   
   public void showMyDialog ()
   {
       mDialog=new MyDialog(this, "创建画布", "请选择画布大小", "确定",new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   
                   mDialog.dismiss();
                   data.add(new PicRVItem(mDialog.getName(),mDialog.getDrawSize()));
                   refreshUI();
                   PicFileTool.writeToFile(MainActivity.this,"data",data);
                   fab.callOnClick();
                   startPicDrawActivity(mDialog.getName(),mDialog.getDrawSize());
                   //Toast.makeText(MainActivity.this,"退出了--伤心",Toast.LENGTH_LONG).show();
               }
           },"取消");
       //mDialog.setCanotBackPress();
       mDialog.setCancelable(true);
       mDialog.setCanceledOnTouchOutside(false);
       mDialog.show();
       
   }
   
   public void hideSubActionButton()
   {
       if (isShowCheck)
       {
           isShowCheck = !isShowCheck;
           adapter.setShouldShowCheckBox(false);
           refreshUI();
       }
           checkList.clear();
       mOpenDeleteFab.hide();
       mAddExportFab.hide();
       fab.setRotation(0);
   }
   
   public void showSubActionButton()
   {
       mOpenDeleteFab.show();
       mAddExportFab.show();
       fab.setRotation(180);
   }
   
   public void setActionButtonShowImage ()
   {
       if (isShowCheck)
       {
           mOpenDeleteFab.setImageResource(R.drawable.ic_delete);
           mAddExportFab.setImageResource(R.drawable.ic_export);
           //isShowCheck = !isShowCheck;
       }
       else
       {
           mOpenDeleteFab.setImageResource(R.drawable.ic_open);
           mAddExportFab.setImageResource(R.drawable.ic_add);
       }
   }
   
   public void onMainButtonClick()
   {
       setActionButtonShowImage();
       if (isSubFabShow)
           hideSubActionButton();
       else
           showSubActionButton();
       isSubFabShow = !isSubFabShow;
   }
   
   public void onAddButtonClick ()
   {
       
   }
   
   public void onDeleteButtonClick ()
   {
       
   }
   
   public void onExportButtonClick()
   {
       
   }
   
   public void onOpenButtonClick()
   {
       
   }

   //View.OnClickListener
   
   @Override
   public void onClick(View view)
   {
       switch (view.getId())
       {
           case R.id.MainFab:
               onMainButtonClick();
               break;
       }
   }

    @Override
    public void onClick(View view, int pos) {
    }

    @Override
    public boolean onLongClick(View view, int pos) {
        return false;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.zgily:
                ++i;
                Toast.makeText(MainActivity.this,"感谢您使用本软件😊",Toast.LENGTH_LONG).show();
                if (i==10){
                    Toast.makeText(MainActivity.this,"WJY我喜欢你❤️",Toast.LENGTH_LONG).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
   
}
