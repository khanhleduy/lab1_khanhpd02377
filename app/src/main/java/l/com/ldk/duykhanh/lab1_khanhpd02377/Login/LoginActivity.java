package l.com.ldk.duykhanh.lab1_khanhpd02377.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import l.com.ldk.duykhanh.lab1_khanhpd02377.Activity.QuanLySachActivity;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.DAO_NguoiDung;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences sdf;
    EditText edtUser,edtPass;
    String mUser,mPass;
    Button btnLogin, btnExit;
    CheckBox chkRememberPass;
    DAO_NguoiDung DAONguoiDung;
    String strUserName,strPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        sdf = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnExit = findViewById(R.id.btnExit);
        chkRememberPass = findViewById(R.id.chkRemember);
        edtUser.setText(sdf.getString("USERNAME", ""));
        edtPass.setText(sdf.getString("PASSWORD", ""));
        chkRememberPass.setChecked(sdf.getBoolean("REMEMBER", false));
        DAONguoiDung = new DAO_NguoiDung(LoginActivity.this);

        if(checkLoginShap()>0){
            Intent i = new Intent(LoginActivity.this,QuanLySachActivity.class);
            startActivity(i);
            finish();
        }
        else{

        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUser = edtUser.getText().toString();
                mPass = edtPass.getText().toString();

                if(mUser.isEmpty() || mPass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
                }
                else{
            if(DAONguoiDung.checkLogin(mUser,mPass) > 0 ){

                Toast.makeText(getApplicationContext(), "Login thành công", Toast.LENGTH_SHORT).show();
                finish();

            }
            else if(mUser.equals("admin") && mPass.equals("adminkhanh")){
                        rememberUser(mUser,mPass,chkRememberPass.isChecked());

                        Intent intent = new Intent(getApplicationContext(),QuanLySachActivity.class);
                        startActivity(intent);
                        finish();
            }
            else{
                        Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
             }
             String pattern = ".{6,}";
             if(!mPass.matches(pattern)){
                        edtPass.setError("Ít nhất 6 ký tự");
                    }
                }
            }
        });


    }


    public void rememberUser (String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            edit.clear();
        }
        else{
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        edit.apply();
    }

    public void btn_exit(View view) {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startActivity(startMain);
        finish();

    }

    public int checkLoginShap() {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        boolean chk = pref.getBoolean("REMEMBER", false);
        if (chk) {
            strUserName = pref.getString("USERNAME", "");
            strPassword = pref.getString("PASSWORD", "");
            return 1;
        }
        return -1;
    }
}
