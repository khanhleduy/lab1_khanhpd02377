package l.com.ldk.duykhanh.lab1_khanhpd02377.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import l.com.ldk.duykhanh.lab1_khanhpd02377.ADAPTER.SpinerAdapter;
import l.com.ldk.duykhanh.lab1_khanhpd02377.ADD.ThemSachActivity;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.SachDAO;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.TheLoaiDAO;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.TheLoai;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class UpdateSachActivity extends AppCompatActivity {

    private static final String TAG = "loi";
    private EditText edMaSachUp, edTenSachUp, edTacGiaUp, edNXBUp, edGiaBiaUp, edSoLuongUp;
    private String maSach, tenSach, tacGia, NXB, giaBia, soLuong;
    private Button addBookUp, btnHuyUp;
    private Spinner spnTheLoaiUp;
    int po;
    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    String maTheLoai = "";
    List<TheLoai> listTheLoai = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sach);
        edMaSachUp = findViewById(R.id.edMaSachUp);
        edTenSachUp = findViewById(R.id.edTenSachUp);
        edTacGiaUp = findViewById(R.id.edTacGiaUp);
        edNXBUp = findViewById(R.id.edNXBUp);
        edGiaBiaUp = findViewById(R.id.edGiaBiaUp);
        edSoLuongUp = findViewById(R.id.edSoLuongUp);
        spnTheLoaiUp = findViewById(R.id.spnTheLoaiUp);
        addBookUp = findViewById(R.id.addBookUp);
        btnHuyUp = findViewById(R.id.addBookUp);
        sachDAO = new SachDAO(this);

        theLoaiDAO = new TheLoaiDAO(UpdateSachActivity.this);
        listTheLoai = theLoaiDAO.getALLTheLoai();
        SpinerAdapter adapter = new SpinerAdapter(this, R.layout.spinner_them, listTheLoai);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Log.d("AAAAA", "getTheLoai: " + listTheLoai);
        spnTheLoaiUp.setAdapter(adapter);

        TextView vSpinnerNganh = findViewById(R.id.vSpinnerNganh);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        maSach = b.getString("MASACH");
        int maTL = Integer.parseInt(b.getString("MATHELOAI"));
        tenSach = b.getString("TENSACH");
        tacGia = b.getString("TACGIA");
        NXB = b.getString("NXB");
        giaBia = b.getString("GIABIA");
        soLuong = b.getString("SOLUONG");


        edMaSachUp.setText(maSach);
        edMaSachUp.setEnabled(false);
        edTenSachUp.setText(tenSach);
        edTacGiaUp.setText(tacGia);
        edNXBUp.setText(NXB);
        edGiaBiaUp.setText(giaBia);
        edSoLuongUp.setText(soLuong);
        spnTheLoaiUp.setSelection(maTL-1);

        Toast.makeText(this, ""+maTL, Toast.LENGTH_SHORT).show();
        spnTheLoaiUp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTheLoai = listTheLoai.get(spnTheLoaiUp.getSelectedItemPosition()).getMaTheLoai();
                 po = Integer.parseInt(maTheLoai);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Log.d(TAG, "onCreate: " + maSach + "\n" + maTheLoai + "\n" + tenSach + "\n" + tacGia + "\n" + NXB + "\n" + giaBia + "\n" + soLuong);

        addBookUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (sachDAO.updateSachInfo(maSach, maTheLoai, edTenSachUp.getText().toString(), edTacGiaUp.getText().toString()
                            , edNXBUp.getText().toString(), Double.parseDouble(edGiaBiaUp.getText().toString()), Integer.parseInt(edSoLuongUp.getText().toString())) > 0) {
                        Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(UpdateSachActivity.this, "Cong khong thanh", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: " + e.getMessage());
                }
            }
        });
    }
}
