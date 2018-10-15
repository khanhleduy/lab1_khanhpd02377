package l.com.ldk.duykhanh.lab1_khanhpd02377.ADD;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import l.com.ldk.duykhanh.lab1_khanhpd02377.ADAPTER.SpinerAdapter;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Activity.SachActivity;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Activity.UpdateSachActivity;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.SachDAO;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.TheLoaiDAO;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.Sach;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.TheLoai;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class ThemSachActivity extends AppCompatActivity {

    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    Spinner spnTheLoai;
    EditText edMaSach, edTenSach, edNXB, edTacGia, edGiaBia, edSoLuong;
    String maTheLoai = "";
    List<TheLoai> listTheLoai = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sach);
        setTitle("THÊM SÁCH");
        spnTheLoai = (Spinner) findViewById(R.id.spnTheLoai);
        getTheLoai();
        edMaSach = (EditText) findViewById(R.id.edMaSach);
        edTenSach = (EditText) findViewById(R.id.edTenSach);
        edNXB = (EditText) findViewById(R.id.edNXB);
        edTacGia = (EditText) findViewById(R.id.edTacGia);
        edGiaBia = (EditText) findViewById(R.id.edGiaBia);
        edSoLuong = (EditText) findViewById(R.id.edSoLuong);

        //
        spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTheLoai = listTheLoai.get(spnTheLoai.getSelectedItemPosition()).getMaTheLoai();
                Sach sach = (Sach) parent.getItemAtPosition(position);

                Toast.makeText(ThemSachActivity.this, ""+position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaSach.setText(b.getString("MASACH"));
            String maTheLoai2 = b.getString("MATHELOAI",maTheLoai);
            edTenSach.setText(b.getString("TENSACH"));
            edNXB.setText(b.getString("NXB"));
            edTacGia.setText(b.getString("TACGIA"));
            edGiaBia.setText(b.getString("GIABIA"));
            edSoLuong.setText(b.getString("SOLUONG"));
            spnTheLoai.setSelection(checkPositionTheLoai(maTheLoai));
        }
    }

    public void showSpinner(View view) {
        sachDAO = new SachDAO(ThemSachActivity.this);
        sachDAO.getAllSach();
    }

    public void getTheLoai() {
        theLoaiDAO = new TheLoaiDAO(ThemSachActivity.this);
        listTheLoai = theLoaiDAO.getALLTheLoai();
        SpinerAdapter adapter = new SpinerAdapter(this,R.layout.spinner_them,listTheLoai);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Log.d("AAAAA", "getTheLoai: " + listTheLoai);
        spnTheLoai.setAdapter(adapter);
    }

    public void addBook(View view) {
        sachDAO = new SachDAO(ThemSachActivity.this);
        Sach sach = new
                Sach(edMaSach.getText().toString(), maTheLoai, edTenSach.getText().toString(),
                edTacGia.getText().toString(), edNXB.getText().toString(),

                Double.parseDouble(edGiaBia.getText().toString()), Integer.parseInt(edSoLuong.getText
                ().toString()));

        try {
            if (sachDAO.inserSach(sach) > 0) {
                Toast.makeText(getApplicationContext(), "Thêm thành công",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Thêm thất bại",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public void showBook(View view) {
        finish();
    }

    public int checkPositionTheLoai(String strTheLoai) {
        for (int i = 0; i < listTheLoai.size(); i++) {
            if (strTheLoai.equals(listTheLoai.get(i).getMaTheLoai())) {
                return i;
            }
        }
        return 0;
    }
}
