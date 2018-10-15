package l.com.ldk.duykhanh.lab1_khanhpd02377.ADD;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import l.com.ldk.duykhanh.lab1_khanhpd02377.Activity.HoaDonChiTietActivity;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.HoaDonDAO;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.HoaDon;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class ThemHoaDonActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText edNgayMua, edMaHoaDon;
    HoaDonDAO hoaDonDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THÊM HOÁ ĐƠN");
        setContentView(R.layout.activity_them_hoa_don);
        edNgayMua = (EditText) findViewById(R.id.edNgayMua);
        edMaHoaDon = (EditText) findViewById(R.id.edMaHoaDon);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }
    private void setDate(final Calendar calendar) {
        edNgayMua.setText(sdf.format(calendar.getTime()));
    }
    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }

        public void show(FragmentManager fragmentManager, String date) {
        }
    }
    public void datePicker(View view){
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(),"date");
    }
    public void ADDHoaDon(View view) {
        hoaDonDAO = new HoaDonDAO(ThemHoaDonActivity.this);
        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                HoaDon hoaDon = new
                        HoaDon(edMaHoaDon.getText().toString(),sdf.parse(edNgayMua.getText().toString()));
                if (hoaDonDAO.inserHoaDon(hoaDon) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new
                            Intent(ThemHoaDonActivity.this,HoaDonChiTietActivity.class);
                    Bundle b = new Bundle();
                    b.putString("MAHOADON", edMaHoaDon.getText().toString());
                    intent.putExtras(b);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    public int validation(){
        if
                (edMaHoaDon.getText().toString().isEmpty()||edNgayMua.getText().toString().isEmpty()
                ){
            return -1;
        }
        return 1;
    }
}
