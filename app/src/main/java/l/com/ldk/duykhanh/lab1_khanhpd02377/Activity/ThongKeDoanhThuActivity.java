package l.com.ldk.duykhanh.lab1_khanhpd02377.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.HoaDonChiTietDAO;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class ThongKeDoanhThuActivity extends AppCompatActivity {
    TextView tvNgay,tvThang,tvNam;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Doanh thu");
        setContentView(R.layout.activity_thong_ke_doanh_thu);
        tvNgay = findViewById(R.id.tvThongKeNgay);
        tvThang = findViewById(R.id.tvThongKeThang);
        tvNam = (TextView) findViewById(R.id.tvThongKeNam);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        tvNgay.setText("Hôm nay:    " + hoaDonChiTietDAO.getDoanhThuTheoNgay());
        tvThang.setText("Tháng này:  " + hoaDonChiTietDAO.getDoanhThuTheoThang());
        tvNam.setText("Năm này:    " + hoaDonChiTietDAO.getDoanhThuTheoNam());
    }
}
