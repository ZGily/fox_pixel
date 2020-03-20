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

public class PicRVAdapter extends RecyclerView.Adapter<PicRVAdapter.ViewHolder>
{
    private List<PicRVItem> itemList;
    
    public PicRVAdapter (List<PicRVItem> list)
    {
        this.itemList = list;
    }

    @Override
    public void onBindViewHolder(PicRVAdapter.ViewHolder vh, int pos) {
        //vh.pic.setBackgroundDrawable(itemList.get(pos).pic);
        final String text = itemList.get(pos).name;
        vh.name.setText(itemList.get(pos).name);
        vh.check.setVisibility(0);
        vh.itemView.setLongClickable(true);
        vh.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View p1) {
                    Toast.makeText(p1.getContext(),text,Toast.LENGTH_SHORT).show();
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
