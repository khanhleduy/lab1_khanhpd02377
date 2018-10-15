package l.com.ldk.duykhanh.lab1_khanhpd02377.ADD;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import l.com.ldk.duykhanh.lab1_khanhpd02377.Activity.TheLoaiActivity;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.DAO_NguoiDung;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.TheLoaiDAO;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.NguoiDung;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.TheLoai;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class ThemTheLoaiActivity extends AppCompatActivity {

    private EditText edtMaTheLoai,edtTenTheLoai,edtViTri,edtMoTa;
    private Button btnThemTL,btnHuyTL,btnShowTL;
    TheLoaiDAO DAOTheLoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_the_loai_activity);
        edtMaTheLoai =  findViewById(R.id.edtMaTheLoai);
        edtTenTheLoai =  findViewById(R.id.edtTenTheLoai);
        edtMoTa =  findViewById(R.id.edtMoTa);
        edtViTri =  findViewById(R.id.edtViTri);
    }


    public void btnThemTL(View view) {
        if (view.getId() == R.id.btnThemTL) {


            DAOTheLoai = new TheLoaiDAO(ThemTheLoaiActivity.this);
            TheLoai theLoai = new TheLoai(edtMaTheLoai.getText().toString(),
                    edtTenTheLoai.getText().toString(), edtMoTa.getText().toString(),Integer.parseInt(edtViTri.getText().toString()));
            try {
                if (validateFrom() > 0) {
                    if (DAOTheLoai.inserTheLoai(theLoai) > 0) {
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
        if (edtTenTheLoai.getText().length() == 0 || edtMaTheLoai.getText().length() == 0
                || edtViTri.getText().length() == 0 || edtMoTa.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public void btnHuyTL(View view) {
        finish();
    }

    public void btnShowTL(View view) {
        Intent intent = new Intent(ThemTheLoaiActivity.this,TheLoaiActivity.class);
        startActivity(intent);
        finish();
    }
}
