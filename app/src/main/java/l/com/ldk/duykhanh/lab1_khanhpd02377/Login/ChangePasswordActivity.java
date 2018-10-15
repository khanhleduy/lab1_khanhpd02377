package l.com.ldk.duykhanh.lab1_khanhpd02377.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import l.com.ldk.duykhanh.lab1_khanhpd02377.Activity.QuanLySachActivity;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.DAO_NguoiDung;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.NguoiDung;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText edtPass,edtNhapLaiPass;
    DAO_NguoiDung DAONguoiDung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setTitle("ĐỔI MẬT KHẨU");
        edtPass = findViewById(R.id.edtPassChang);
        edtNhapLaiPass = findViewById(R.id.edtNhapLaiPass);
    }

    public int validateFrom(){
        int check = 1;
        if(edtPass.getText().length()==0 || edtNhapLaiPass.getText().length() == 0){
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        else{
            String pass = edtPass.getText().toString();
            String rePass = edtNhapLaiPass.getText().toString();
            if(!pass.equals(rePass)){
                Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }

    public void changePassword(View view){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String mUser = pref.getString("USERNAME","");
        DAONguoiDung = new DAO_NguoiDung(ChangePasswordActivity.this);
        NguoiDung user = new NguoiDung(mUser,edtPass.getText().toString(),"","");
        try{
            if(validateFrom()>0){
                if(DAONguoiDung.changePasswordNguoiDung(user)>0){
                    Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Lưu thất bại"+user.toString() + "d", Toast.LENGTH_SHORT).show();
                }
            }

        }
        catch (Exception e){
            Log.e("Error",e.toString());
        }
    }


    public void btnThoat(View view) {
        finish();
    }
}
