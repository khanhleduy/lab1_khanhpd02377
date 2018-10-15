package l.com.ldk.duykhanh.lab1_khanhpd02377.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import l.com.ldk.duykhanh.lab1_khanhpd02377.DATABASE.SQLiteHelper;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.TheLoai;

public class TheLoaiDAO {
    private SQLiteDatabase db;
    private SQLiteHelper dbHelper;

    public static final String TABLE_NAME = "TheLoai";
    public static final String SQL_THE_LOAI = "CREATE TABLE TheLoai (matheloai text primary key, tentheloai text, mota text, vitri int);";
    public static final String TAG = "TheLoaiDAO";

    public TheLoaiDAO(Context context){
        dbHelper = new SQLiteHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int inserTheLoai(TheLoai theLoai){
        ContentValues values = new ContentValues();
        values.put("matheloai",theLoai.getMaTheLoai());
        values.put("tentheloai",theLoai.getTenTheLoai());
        values.put("mota",theLoai.getMoTa());
        values.put("vitri",theLoai.getViTri());
        try{
            if(db.insert(TABLE_NAME,null,values) == -1){
                return -1;
            }
        }
        catch (Exception e){
            Log.e(TAG, e.toString() );
        }
        return 1;
    }

    public List<TheLoai> getALLTheLoai(){
        List<TheLoai> dsTheLoai = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            TheLoai theLoai = new TheLoai();
            theLoai.setMaTheLoai(cursor.getString(0));
            theLoai.setTenTheLoai(cursor.getString(1));
            theLoai.setMoTa(cursor.getString(2));
            theLoai.setViTri(cursor.getInt(3));
            dsTheLoai.add(theLoai);
            cursor.moveToNext();
        }
        cursor.close();
        Log.d(TAG, "getALLTheLoai: "+dsTheLoai);
        return dsTheLoai;
    }

    public int updateTheLoai(TheLoai theLoai){
        ContentValues values = new ContentValues();
        values.put("matheloai",theLoai.getMaTheLoai());
        values.put("tentheloai",theLoai.getTenTheLoai());
        values.put("mota",theLoai.getMoTa());
        values.put("vitri",theLoai.getViTri());
        int result = db.update(TABLE_NAME,values,"matheloai=?",new String[]{theLoai.getMaTheLoai()});
        if(result == 0){
            return -1;
        }
        return 1;
    }

    public int deleteTheLoaiByID(String matheloai){
        int result = db.delete(TABLE_NAME,"matheloai=?",new String[]{matheloai});
        if(result == 0)
            return -1;
        return 1;
    }


    public int updateTheLoai(String matheloai, String s, String s1,String s2, String s3) {
        ContentValues values = new ContentValues();
        values.put("matheloai",matheloai);
        values.put("tentheloai",s);
        values.put("mota",s1);
        values.put("vitri",s2);
        int result = db.update(TABLE_NAME,values,"matheloai=?",new String[]{matheloai});
        if(result == 0){
            return -1;
        }
        return 1;
    }
}
