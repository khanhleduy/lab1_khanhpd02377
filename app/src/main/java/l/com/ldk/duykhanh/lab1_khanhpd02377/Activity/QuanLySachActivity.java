package l.com.ldk.duykhanh.lab1_khanhpd02377.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import l.com.ldk.duykhanh.lab1_khanhpd02377.Login.ChangePasswordActivity;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Login.LoginActivity;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class QuanLySachActivity extends AppCompatActivity {

    String strUserName,strPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sach);


    }

    public int checkLoginShap() {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        boolean chk = pref.getBoolean("REMEMBER", false);
        if (chk) {
            strUserName = pref.getString("USERNAME", "");
            strPassword = pref.getString("PASSWORD", "");
            return 1;
        }
        return -1;
    }


    public void btn_ND(View view) {
        Intent intND = new Intent(this,QuanLyNguoiDungActivity.class);
        startActivity(intND);

    }

    public void btn_TL(View view) {
        Intent intTL = new Intent(this,TheLoaiActivity.class);
        startActivity(intTL);

    }

    public void btn_sach(View view) {
        Intent intSach = new Intent(this,SachActivity.class);
        startActivity(intSach);

    }

    public void btnHoaDon(View view) {
        Intent intHoaDon = new Intent(this,HoaDonActivity.class);
        startActivity(intHoaDon);
    }

    public void btnSachBanChay(View view){
        Intent intSachBanChay = new Intent(this,LuotBanSachChayActivity.class);
        startActivity(intSachBanChay);
    }

    public void btnThongKe(View view){
        Intent intThongKe = new Intent(this,ThongKeDoanhThuActivity.class);
        startActivity(intThongKe);
    }

}
