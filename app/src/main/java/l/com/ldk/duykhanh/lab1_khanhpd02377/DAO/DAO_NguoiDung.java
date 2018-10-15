package l.com.ldk.duykhanh.lab1_khanhpd02377.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import l.com.ldk.duykhanh.lab1_khanhpd02377.DATABASE.SQLiteHelper;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.NguoiDung;

public class DAO_NguoiDung {

    private SQLiteDatabase db;
    private SQLiteHelper dbHelper;

    public static final String TABLE_NAME = "NguoiDung";
    public static final String SQL_NGUOI_DUNG = "CREATE TABLE NguoiDung (username text primary key, password text, phone text, fullName text);";
    public static final String TAG = "DAONguoiDung";

    public DAO_NguoiDung(Context context) {
        dbHelper = new SQLiteHelper(context);
        db = dbHelper.getWritableDatabase();

    }

    public int inserNguoiDung(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUsername());
        values.put("password", nd.getPassword());
        values.put("phone", nd.getPhone());
        values.put("fullName", nd.getFullName());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return 1;
    }

    public List<NguoiDung> getAllNguoiDung() {
        List<NguoiDung> dsNguoiDung = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            NguoiDung ee = new NguoiDung();
            ee.setUsername(c.getString(0));
            ee.setPassword(c.getString(1));
            ee.setPhone(c.getString(2));
            ee.setFullName(c.getString(3));
            dsNguoiDung.add(ee);
            Log.d("/====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsNguoiDung;

    }

    public int updateNguoiDung(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUsername());
        values.put("password", nd.getPassword());
        values.put("phone", nd.getPhone());
        values.put("fullName", nd.getFullName());
        int result = db.update(TABLE_NAME, values, "username=?", new
                String[]{nd.getUsername()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int changePasswordNguoiDung(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUsername());
        values.put("password", nd.getPassword());
        int result = db.update(TABLE_NAME, values, "password=?", new
                String[]{nd.getUsername()});
        if (result == 0) {

                Log.d(TAG, "changePasswordNguoiDung: "+nd.getPassword()+" \n" + nd.getUsername());
                return -1;

        }
        return 1;
    }

    public int updateInfoNguoiDung(String username, String phone, String name) {
        ContentValues values = new ContentValues();
        values.put("phone", phone);
        values.put("fullName", name);
        int result = db.update(TABLE_NAME, values, "username=?", new
                String[]{username});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int deleteNguoiDungByID(String username) {
        int result = db.delete(TABLE_NAME, "username=?", new
                String[]{username});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int checkLogin(String username, String password) {
        int id = -1;
        Cursor cursor = db.rawQuery("SELECT username FROM NguoiDung WHERE username=? AND password=?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            id = 1;
            cursor.close();
        }
        return id;

    }
}
