package ghh.zgily.pixel;
import android.app.Dialog;
import android.view.View;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.text.TextUtils;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.AdapterView;
import android.widget.Adapter;

public class MyDialog extends Dialog {
    EditText nameedit;
    Spinner spinner;
    private String title;
    private String content;
    private String buttonConfirm;
    private String buttonCancel;
    private View.OnClickListener confirmClickListener;
    private View.OnClickListener cancelClickListener;
    private static final int SHOW_ONE = 1;
    private int show = 2;

    int select = 16;
    
    public MyDialog(Context context, String title, String content,
                        String buttonConfirm,
                        View.OnClickListener confirmClickListener) {
        super(context, R.style.Dialog);
        this.title = title;
        this.content = content;
        this.buttonConfirm = buttonConfirm;
        this.confirmClickListener = confirmClickListener;
        this.show = SHOW_ONE;
    }
    public MyDialog(Context context, String title, String content,
                        String buttonConfirm,
                        View.OnClickListener confirmClickListener,String buttonCancel) {
        super(context, R.style.Dialog);
        this.title = title;
        this.content = content;
        this.buttonConfirm = buttonConfirm;
        this.buttonCancel = buttonCancel;
        this.confirmClickListener = confirmClickListener;
    }
    public MyDialog(Context context, String title, String content,
                        View.OnClickListener confirmClickListener, String buttonConfirm, String buttonCancel) {
        super(context, R.style.Dialog);
        this.title = title;
        this.content = content;
        this.buttonConfirm = buttonConfirm;
        this.buttonCancel = buttonCancel;
        this.confirmClickListener = confirmClickListener;
    }

    public MyDialog(Context context, String title, String content,
                        View.OnClickListener confirmClickListener, View.OnClickListener cancelClickListener, String
                        buttonConfirm, String buttonCancel) {
        super(context, R.style.Dialog);
        this.title = title;
        this.content = content;
        this.buttonConfirm = buttonConfirm;
        this.buttonCancel = buttonCancel;
        this.confirmClickListener = confirmClickListener;
        this.cancelClickListener = cancelClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dialog_layout);
        TextView dialog_title = (TextView) findViewById(R.id.dialog_title);
        TextView dialog_content = (TextView) findViewById(R.id.dialog_content);
        TextView dialog_confirm = (TextView) findViewById(R.id.dialog_confirm);
        TextView dialog_cancel = (TextView) findViewById(R.id.dialog_cancel);
        View dialog_line = findViewById(R.id.dialog_line);
        if (!TextUtils.isEmpty(title))
            dialog_title.setText(title);
        if (!TextUtils.isEmpty(content)) {
            dialog_content.setText(content);
        } else {
            dialog_content.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(buttonConfirm))
            dialog_confirm.setText(buttonConfirm);
        if (!TextUtils.isEmpty(buttonCancel))
            dialog_cancel.setText(buttonCancel);
        if (SHOW_ONE == show) {
            dialog_line.setVisibility(View.GONE);
            dialog_cancel.setVisibility(View.GONE);
            if (null != confirmClickListener) {
                dialog_confirm.setOnClickListener(confirmClickListener);
            }
            dialog_confirm.setBackgroundResource(R.drawable.dialog_back);
        } else {
            if (null != confirmClickListener) {
                dialog_confirm.setOnClickListener(confirmClickListener);
            }
            if (null != cancelClickListener) {
                dialog_cancel.setOnClickListener(cancelClickListener);
            } else {
                dialog_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           MyDialog.this.dismiss();
                        }
                    });
            }

        }
        
        nameedit = (EditText) findViewById(R.id.nameEditText);
        
         spinner = (Spinner) findViewById(R.id.field_item_spinner_content);

        //资源转[]
        String meinv[] =getContext().getResources().getStringArray(R.array.meinv);

        //构造ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.simple_spinner_item, meinv);
        //设置下拉样式以后显示的样式
        adapter.setDropDownViewResource(R.layout.my_drop_down_item);

        spinner.setAdapter(adapter);
        
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4) {
                    switch (p3)
                    {
                        case 0:
                            select = 16;
                            break;
                       case 1:
                           select = 32;
                           break;
                       case 2:
                           select = 64;
                           break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> p1) {
                }
            });
        
        /*
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        String[] years = {"1996","1997","1998","1998"};
        ArrayAdapter<CharSequence> langAdapter = new ArrayAdapter<CharSequence>(getContext(), R.layout.spinner_text, years );
        langAdapter.setDropDownViewResource(R.drawable.simple_spinner_dropdown);
        spinner.setAdapter(langAdapter);
        */
        
    }
    
    public String getName()
    {
        return nameedit.getText().toString();
    }
    
    public int getDrawSize()
    {
        return select;
    }

}
