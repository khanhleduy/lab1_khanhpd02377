package l.com.ldk.duykhanh.lab1_khanhpd02377.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.DAO_NguoiDung;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class NguoiDungDetailActivity extends AppCompatActivity {

    EditText edtHoTen,edtSDT;
    DAO_NguoiDung nguoiDungDAO;
    String username,fullname,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung_detail);
        setTitle("CHI TIẾT NGƯỜI DÙNG");
        edtHoTen = findViewById(R.id.edtFullNameDeltaiND);
        edtSDT = findViewById(R.id.edtPhoneDeltaiND);

        nguoiDungDAO = new DAO_NguoiDung(this);
        Intent in = getIntent();
        Bundle b = in.getExtras();


        username = b.getString("USERNAME");
        phone = b.getString("PHONE");
        fullname = b.getString("FULLNAME");
        edtHoTen.setText(fullname);
        edtSDT.setText(phone);

    }


    public void updateUser(View view) {
try {
    if (nguoiDungDAO.updateInfoNguoiDung(username, edtSDT.getText().toString(), edtHoTen.getText().toString()) > 0) {
        Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
    }
}
catch (Exception e){
    Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
}

    }

    public void btnExitND(View view) {
        finish();
    }
}
