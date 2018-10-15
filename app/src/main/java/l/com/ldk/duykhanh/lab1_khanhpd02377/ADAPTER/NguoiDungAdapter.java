package l.com.ldk.duykhanh.lab1_khanhpd02377.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.DAO_NguoiDung;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.NguoiDung;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class NguoiDungAdapter extends BaseAdapter{

    List<NguoiDung> arrNguoiDung;
    public Activity context;
    public LayoutInflater inflater;
    DAO_NguoiDung nguoiDungDao;

    public NguoiDungAdapter(Activity context, List<NguoiDung> arrNguoiDung) {
        super();
        this.arrNguoiDung = arrNguoiDung;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nguoiDungDao = new DAO_NguoiDung(context);
    }

    @Override
    public int getCount() {
        return arrNguoiDung.size();
    }

    @Override
    public Object getItem(int i) {
        return arrNguoiDung.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder{
        ImageView img;
        TextView txtName;
        TextView txtPhone;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.layout_nguoidung,null);
            holder.img = view.findViewById(R.id.imgCaNhan);
            holder.txtName = view.findViewById(R.id.tvTenND);
            holder.txtPhone = view.findViewById(R.id.tvPhone);
            holder.imgDelete = view.findViewById(R.id.imgDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nguoiDungDao.deleteNguoiDungByID(arrNguoiDung.get(i).getUsername());
                    arrNguoiDung.remove(i);
                    notifyDataSetChanged();
                }
            });
            view.setTag(holder);
        }
        else
            holder = (ViewHolder) view.getTag();

        NguoiDung entry = (NguoiDung) arrNguoiDung.get(i);
        if(i % 3 == 0){
            holder.img.setImageResource(R.drawable.emone);
        }
        else if(i % 3 == 1){
            holder.img.setImageResource(R.drawable.emtwo);
        }
        else{
            holder.img.setImageResource(R.drawable.emthree);
        }
        holder.txtName.setText(entry.getFullName());
        holder.txtPhone.setText(""+ entry.getPhone());
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<NguoiDung> items){
        this.arrNguoiDung = items;
        notifyDataSetChanged();
    }
}
