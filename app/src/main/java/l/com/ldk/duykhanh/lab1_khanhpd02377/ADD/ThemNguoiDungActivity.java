package l.com.ldk.duykhanh.lab1_khanhpd02377.ADD;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import l.com.ldk.duykhanh.lab1_khanhpd02377.Activity.QuanLyNguoiDungActivity;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.DAO_NguoiDung;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.NguoiDung;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class ThemNguoiDungActivity extends AppCompatActivity {


    private EditText edtUser, edtPass, edtRePass, edtPhone, edtFullName;
    private Button btnThemNguoiDung;
    DAO_NguoiDung nguoiDungDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nguoi_dung);
        setTitle("THÊM NGƯỜI DÙNG");
        btnThemNguoiDung = findViewById(R.id.btnThemND);
        edtUser = findViewById(R.id.edtNickName);
        edtPass = findViewById(R.id.edtPass);
        edtRePass = findViewById(R.id.edtCheckPass);
        edtPhone = findViewById(R.id.edtSDT);
        edtFullName = findViewById(R.id.edtFullName);

    }

    public void showUser(View view) {
        finish();
    }


    public void addUser(View view) {
        if (view.getId() == R.id.btnThemND) {


            nguoiDungDAO = new DAO_NguoiDung(ThemNguoiDungActivity.this);
            NguoiDung user = new NguoiDung(edtUser.getText().toString(),
                    edtPass.getText().toString(), edtPhone.getText().toString(), edtFullName.getText().toString());
            try {
                if (validateFrom() > 0) {
                    if (nguoiDungDAO.inserNguoiDung(user) > 0) {
                        Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception e) {
                Log.e("Error", e.toString());
            }
        }
    }

    public int validateFrom() {
        int check = 1;
        if (edtUser.getText().length() == 0 || edtFullName.getText().length() == 0
                || edtPhone.getText().length() == 0 || edtPass.getText().length() == 0 || edtRePass.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edtPass.getText().toString();
            String rePass = edtRePass.getText().toString();
            if (!pass.equals(rePass)) {
                Toast.makeText(getApplicationContext(), "Mậ khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }

    public void btnDSND(View view) {
        Intent intent = new Intent(ThemNguoiDungActivity.this, QuanLyNguoiDungActivity.class);
        startActivity(intent);
        finish();
    }
}
