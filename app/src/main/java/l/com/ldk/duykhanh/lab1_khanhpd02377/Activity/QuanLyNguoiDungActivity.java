package l.com.ldk.duykhanh.lab1_khanhpd02377.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import l.com.ldk.duykhanh.lab1_khanhpd02377.ADAPTER.NguoiDungAdapter;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.DAO_NguoiDung;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Login.ChangePasswordActivity;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Login.LoginActivity;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.NguoiDung;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;
import l.com.ldk.duykhanh.lab1_khanhpd02377.ADD.ThemNguoiDungActivity;

public class QuanLyNguoiDungActivity extends AppCompatActivity {

    public static List<NguoiDung> dsNguoiDung = new ArrayList<>();
    ListView lvNguoiDung;
    NguoiDungAdapter  adapter = null;
    DAO_NguoiDung nguoiDungDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("NGƯỜI DÙNG");
        setContentView(R.layout.activity_quan_ly_nguoi_dung);
        lvNguoiDung = findViewById(R.id.lvNguoiDung);

        nguoiDungDAO = new DAO_NguoiDung(QuanLyNguoiDungActivity.this);
        dsNguoiDung = nguoiDungDAO.getAllNguoiDung();

        adapter = new NguoiDungAdapter(this,dsNguoiDung);
        lvNguoiDung.setAdapter(adapter);
        lvNguoiDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(QuanLyNguoiDungActivity.this, NguoiDungDetailActivity.class);
                Bundle b = new Bundle();
                b.putString("USERNAME",dsNguoiDung.get(i).getUsername());
                b.putString("PHONE",dsNguoiDung.get(i).getPhone());
                b.putString("FULLNAME",dsNguoiDung.get(i).getFullName());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        lvNguoiDung.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        dsNguoiDung.clear();
        dsNguoiDung = nguoiDungDAO.getAllNguoiDung();
        adapter.changeDataset(nguoiDungDAO.getAllNguoiDung());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       Intent intent;
       switch (item.getItemId()){
           case R.id.itAdd:
               intent = new Intent(QuanLyNguoiDungActivity.this,ThemNguoiDungActivity.class);
               startActivity(intent);
               return(true);
           case R.id.itChangepass:
               intent = new Intent(QuanLyNguoiDungActivity.this, ChangePasswordActivity.class);
               startActivity(intent);
               return(true);
           case R.id.itLogOut:
               SharedPreferences pref =  getSharedPreferences("USER_FILE",MODE_PRIVATE);
               SharedPreferences.Editor edit = pref.edit();

               edit.clear();
               edit.commit();
               intent = new Intent(QuanLyNguoiDungActivity.this, LoginActivity.class);
               startActivity(intent);
               break;

       }
        return super.onOptionsItemSelected(item);
    }
}
