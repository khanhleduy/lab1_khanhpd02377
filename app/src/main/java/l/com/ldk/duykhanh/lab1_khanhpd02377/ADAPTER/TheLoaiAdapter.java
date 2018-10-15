package l.com.ldk.duykhanh.lab1_khanhpd02377.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.TheLoaiDAO;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.TheLoai;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class TheLoaiAdapter extends BaseAdapter {
    List<TheLoai> arrTheLoai;
    public Activity context;
    public LayoutInflater inflater;
    TheLoaiDAO theLoaiDAO;
    public TheLoaiAdapter(Activity context, List<TheLoai> arrayTheLoai) {
        super();
        this.context = context;
        this.arrTheLoai = arrayTheLoai;
        this.inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        theLoaiDAO = new TheLoaiDAO(context);
    }
    @Override
    public int getCount() {
        return arrTheLoai.size();
    }
    @Override
    public Object getItem(int position) {
        return arrTheLoai.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder {
        ImageView img;
        TextView txtMaTheLoai;
        TextView txtTenTheLoai;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custtom_theloai, null);
            holder.img = (ImageView) convertView.findViewById(R.id.imgTheLoai);
            holder.txtMaTheLoai = (TextView)
                    convertView.findViewById(R.id.txtTheLoai);
            holder.txtTenTheLoai = (TextView)
                    convertView.findViewById(R.id.txtViTRi);
            holder.imgDelete = (ImageView)convertView.findViewById(R.id.imgDeleteTL);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    theLoaiDAO.deleteTheLoaiByID(arrTheLoai.get(position).getMaTheLoai());
                    arrTheLoai.remove(position);
                    notifyDataSetChanged();
                }
            });
            Log.d( "getView: ","loi view" );
            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();
        TheLoai _entry = (TheLoai) arrTheLoai.get(position);
        holder.img.setImageResource(R.drawable.cateicon);
        holder.txtMaTheLoai.setText(_entry.getMaTheLoai());
        holder.txtTenTheLoai.setText(_entry.getTenTheLoai());
        Log.d("me", "lol: ");
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<TheLoai> items){
        this.arrTheLoai = items;
        notifyDataSetChanged();
    }
}
