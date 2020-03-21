package ghh.zgily.pixel;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CheckBox;
import ghh.zgily.struct.PicRVItem;
import java.util.List;
import android.widget.Toast;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View.OnClickListener;
import android.util.SparseBooleanArray;
import android.widget.CompoundButton;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class PicRVAdapter extends RecyclerView.Adapter<PicRVAdapter.ViewHolder>
{
    private static String path = Environment.getExternalStorageDirectory()+"/Android/data/ghh.zgily.pixel/file/";
    
    private onItemClick onItemClick;
    
    private List<PicRVItem> itemList;
    
    private SparseBooleanArray mCheckBoxStatus = new SparseBooleanArray();//储存CheckBox勾选状态
    
    private boolean isShouldShowCheckBox = false;
    
    public boolean getShouldShowCheckBox ()
    {
        return this.isShouldShowCheckBox;
    }
    
    public void setShouldShowCheckBox (boolean status)
    {
        this.isShouldShowCheckBox = status;
    }
    
    public PicRVAdapter (List<PicRVItem> list)
    {
        this.itemList = list;
    }
    
    public void setOnItemClick (PicRVAdapter.onItemClick onItemClick)
    {
        this.onItemClick = onItemClick;
    }
    
    @Override
    public void onBindViewHolder(final PicRVAdapter.ViewHolder holder,final int pos) {
        //vh.pic.setBackgroundDrawable(itemList.get(pos).pic);
        final String text = itemList.get(pos).name;
        holder.name.setText(itemList.get(pos).name);
        holder.pic.setImageBitmap(BitmapFactory.decodeFile(path+itemList.get(pos).pic));
        holder.itemView.setLongClickable(true);
        /*
        holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View p1) {
                    Toast.makeText(p1.getContext(),text,Toast.LENGTH_SHORT).show();
                }
            });
            */
            holder.check.setTag(pos);
            if (isShouldShowCheckBox)
            {
                holder.check.setVisibility(View.VISIBLE);
                holder.check.setChecked(mCheckBoxStatus.get(pos,false));
            }
            else
            {
                holder.check.setVisibility(View.GONE);
                holder.check.setChecked(false);
                mCheckBoxStatus.clear();
            }
            
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isShouldShowCheckBox) {
                        holder.check.setChecked(!holder.check.isChecked());
                    }
                    onItemClick.onClick(view,pos);
                }
            });
        //长按监听
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    holder.check.setChecked(true);
                    return onItemClick.onLongClick(view, pos);
                }
            });
        //对checkbox的监听 保存选择状态 防止checkbox显示错乱
        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    int pos = (int) compoundButton.getTag();
                    if (b) {
                        mCheckBoxStatus.put(pos, true);
                    } else {
                        mCheckBoxStatus.delete(pos);
                    }

                }
            });
    }

    @Override
    public PicRVAdapter.ViewHolder onCreateViewHolder(ViewGroup p1, int p2)
    {
        View v = LayoutInflater.from(p1.getContext()).inflate(R.layout.pic_list_item,p1,false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    
    public interface onItemClick {
        void onClick (View view,int pos);
        boolean onLongClick (View view,int pos);
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public final ImageView pic;
        public final TextView name;
        public final CheckBox check;
        
        public ViewHolder (View view)
        {
            super(view);
            pic = (ImageView) view.findViewById(R.id.pic_list_itemImageView);
            name = (TextView) view.findViewById(R.id.pic_list_itemTextView);
            check = (CheckBox) view.findViewById(R.id.pic_list_itemCheckBox);
        }
    }
    
}
