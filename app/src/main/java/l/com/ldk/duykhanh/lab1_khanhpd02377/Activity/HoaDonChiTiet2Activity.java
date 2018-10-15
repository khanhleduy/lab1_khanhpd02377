package l.com.ldk.duykhanh.lab1_khanhpd02377.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import l.com.ldk.duykhanh.lab1_khanhpd02377.ADAPTER.CartAdapter;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.HoaDonChiTietDAO;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.HoaDonChiTiet;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class HoaDonChiTiet2Activity extends AppCompatActivity {
    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter = null;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet2);
        setTitle("Hoa don chi tiet");
        lvCart = (ListView) findViewById(R.id.lvHoaDonChiTiet);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTiet2Activity.this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            dsHDCT = hoaDonChiTietDAO.getAllHoaDonChiTietByID(b.getString("MAHOADON"));
        }
        adapter = new CartAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);
    }
}

