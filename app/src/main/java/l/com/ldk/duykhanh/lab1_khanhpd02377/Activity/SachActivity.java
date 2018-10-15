package l.com.ldk.duykhanh.lab1_khanhpd02377.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import l.com.ldk.duykhanh.lab1_khanhpd02377.ADAPTER.BookAdapter;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.SachDAO;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.Sach;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;
import l.com.ldk.duykhanh.lab1_khanhpd02377.ADD.ThemSachActivity;

public class SachActivity extends AppCompatActivity {

    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBook;
    BookAdapter adapter = null;
    SachDAO sachDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("QUẢN LÝ SÁCH");
        setContentView(R.layout.activity_sach);
        lvBook = (ListView) findViewById(R.id.lvBook);
        sachDAO = new SachDAO(SachActivity.this);
        dsSach = sachDAO.getAllSach();
        adapter = new BookAdapter(this, dsSach);
        lvBook.setAdapter(adapter);
        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Sach sach = (Sach) parent.getItemAtPosition(position);
                Intent intent = new
                        Intent(SachActivity.this,UpdateSachActivity.class);
                Bundle b = new Bundle();
                b.putString("MASACH", sach.getmSach());
                b.putString("MATHELOAI", sach.getMaTheLoai());
                b.putString("TENSACH", sach.getTenSach());
                b.putString("TACGIA", sach.getTacGia());
                b.putString("NXB", sach.getNXB());
                b.putString("GIABIA", String.valueOf(sach.getGiaBan()));
                b.putString("SOLUONG", String.valueOf(sach.getSoLuong()));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        // TextFilter
        lvBook.setTextFilterEnabled(true);
        EditText edSeach = (EditText) findViewById(R.id.edSearchBook);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    adapter.resetData();
                }
                adapter.getFilter().filter(s.toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_them_sach,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.itThemSach){
            Intent intent = new Intent(this,ThemSachActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
