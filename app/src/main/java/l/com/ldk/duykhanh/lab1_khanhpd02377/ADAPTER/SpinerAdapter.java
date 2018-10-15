package l.com.ldk.duykhanh.lab1_khanhpd02377.ADAPTER;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.TheLoai;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class SpinerAdapter extends ArrayAdapter<TheLoai> {
    Context context;
    int resource;
    List<TheLoai> objects;

    public SpinerAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<TheLoai> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position,convertView,parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = inflater.inflate(R.layout.spinner_them,parent,false);

        TextView vTenNganh = (TextView) viewRow.findViewById(R.id.vSpinnerNganh);

        TheLoai tl = objects.get(position);
        vTenNganh.setText(tl.getMaTheLoai().toString());
        return viewRow;
    }
}
