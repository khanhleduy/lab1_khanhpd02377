package l.com.ldk.duykhanh.lab1_khanhpd02377.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import l.com.ldk.duykhanh.lab1_khanhpd02377.ADAPTER.BookAdapter;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.SachDAO;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.Sach;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class LuotBanSachChayActivity extends AppCompatActivity {
public static List<Sach> dsSach = new ArrayList<>();
ListView lvBook;
BookAdapter adapter = null;
SachDAO sachDAO;
EditText edThang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("TOP 10 SÁCH BÁN CHẠY  ");
        setContentView(R.layout.activity_luot_ban_sach_chay);
        lvBook = findViewById(R.id.lvBookTop);
        edThang = findViewById(R.id.edThang);
    }

    public void ViewTop10(View view){
        if(Integer.parseInt(edThang.getText().toString())>13 ||
                Integer.parseInt(edThang.getText().toString())<0){
            Toast.makeText(getApplicationContext(), "Không đúng định dạng ngày tháng (1-12)", Toast.LENGTH_SHORT).show();
        }
        else{
            sachDAO = new SachDAO(LuotBanSachChayActivity.this);
            dsSach = sachDAO.getSachTop10(edThang.getText().toString());
            adapter = new BookAdapter(this,dsSach);
            lvBook.setAdapter(adapter);
        }
    }
}
