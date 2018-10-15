package l.com.ldk.duykhanh.lab1_khanhpd02377.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import l.com.ldk.duykhanh.lab1_khanhpd02377.ADAPTER.TheLoaiAdapter;
import l.com.ldk.duykhanh.lab1_khanhpd02377.ADD.ThemTheLoaiActivity;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.TheLoaiDAO;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.TheLoai;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class TheLoaiActivity extends AppCompatActivity {

    public static List<TheLoai> dsTheLoai = new ArrayList<>();
    ListView lvTheLoai;
    TheLoaiAdapter adapter = null;
    TheLoaiDAO theLoaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THỂ LOẠI");
        setContentView(R.layout.activity_the_loai);
        lvTheLoai = (ListView) findViewById(R.id.lvTheLoai);
        registerForContextMenu(lvTheLoai);
        theLoaiDAO = new TheLoaiDAO(TheLoaiActivity.this);
        dsTheLoai = theLoaiDAO.getALLTheLoai();
        adapter = new TheLoaiAdapter(this, dsTheLoai);
        lvTheLoai.setAdapter(adapter);
        lvTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new
                        Intent(TheLoaiActivity.this, DeltaiTheLoaiActivity.class);
                Bundle b = new Bundle();
                b.putString("MATHELOAI", dsTheLoai.get(position).getMaTheLoai());
                b.putString("TENTHELOAI", dsTheLoai.get(position).getTenTheLoai());
                b.putString("MOTA", dsTheLoai.get(position).getMoTa());
                b.putString("VITRI",
                        String.valueOf(dsTheLoai.get(position).getViTri()));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_theloai, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.themTl:
                Intent intent = new
                        Intent(TheLoaiActivity.this, ThemTheLoaiActivity.class);
                startActivity(intent);
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsTheLoai.clear();
        dsTheLoai = theLoaiDAO.getALLTheLoai();
        adapter.changeDataset(dsTheLoai);
    }
//        @Override
//        public void onCreateContextMenu(ContextMenu menu, View v,
//                                        ContextMenu.ContextMenuInfo menuInfo) {
//            super.onCreateContextMenu(menu, v, menuInfo);
//            MenuInflater inflater = getMenuInflater();
//            inflater.inflate(R.menu.menu_context, menu);
//            menu.setHeaderTitle("Chọn thông tin");
//        }
//        @Override
//        public boolean onContextItemSelected(MenuItem item) {
//            switch(item.getItemId()) {
//                case R.id.itSua:
//                    Intent intent1 = new
//                            Intent(TheLoaiActivity.this,DeltaiTheLoaiActivity.class);
//
//                    startActivity(intent1);
//                    return(true);
//                case R.id.itXoa:
//                    finish();
//                    return(true);
//            }
//            return super.onContextItemSelected(item);
//        }
}
