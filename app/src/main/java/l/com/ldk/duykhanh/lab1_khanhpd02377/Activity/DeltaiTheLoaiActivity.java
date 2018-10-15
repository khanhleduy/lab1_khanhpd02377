package l.com.ldk.duykhanh.lab1_khanhpd02377.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.TheLoaiDAO;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class DeltaiTheLoaiActivity extends AppCompatActivity {

    private EditText edtMaTheLoai, edtTenTheLoai, edtMoTa, edtViTri;
    private Button btnUpdateTL, btnHuyTLUpdate;
    private String matheloai, tentheloai, mota, vitri;
    TheLoaiDAO theLoaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deltai_the_loai);
        setTitle("Update thể loại");
        edtMaTheLoai = findViewById(R.id.edtMaTheLoaiUpdate);
        edtTenTheLoai = findViewById(R.id.edtTenTheLoaiUpdate);
        edtMoTa = findViewById(R.id.edtMoTaUpdate);
        edtViTri = findViewById(R.id.edtViTriUpdate);
        btnUpdateTL = findViewById(R.id.btnUpdateTL);
        btnHuyTLUpdate = findViewById(R.id.btnHuyTLUpdate);
        theLoaiDAO = new TheLoaiDAO(this);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        matheloai = b.getString("MATHELOAI");
        tentheloai = b.getString("TENTHELOAI");
        mota = b.getString("MOTA");
        vitri = b.getString("VITRI");

        edtMaTheLoai.setText(matheloai);
        edtTenTheLoai.setText(tentheloai);
        edtMoTa.setText(mota);
        edtViTri.setText(vitri);
        btnUpdateTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (theLoaiDAO.updateTheLoai(matheloai,edtMaTheLoai.getText().toString(),edtTenTheLoai.getText().toString().toString(),
                            edtMoTa.getText().toString(),edtViTri.getText().toString()) > 0)
                    {
                        Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuyTLUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

