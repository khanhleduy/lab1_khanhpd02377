package l.com.ldk.duykhanh.lab1_khanhpd02377.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import l.com.ldk.duykhanh.lab1_khanhpd02377.DATABASE.SQLiteHelper;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.HoaDon;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.Sach;

public class SachDAO {
    private SQLiteDatabase db;
    private SQLiteHelper dbHelper;
    public static final String TABLE_NAME = "Sach";
    public static final String SQL_SACH ="CREATE TABLE Sach (maSach text primary key, maTheLoai text, tensach text," +
            "tacGia text, NXB text, giaBia double, soLuong number);";
    public static final String TAG = "SachDAO";
    public SachDAO(Context context) {
        dbHelper = new SQLiteHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //insert
    public int inserSach(Sach s){
        ContentValues values = new ContentValues();
        values.put("maSach",s.getmSach());
        values.put("maTheLoai",s.getMaTheLoai());
        values.put("tensach",s.getTenSach());
        values.put("tacGia",s.getTacGia());
        values.put("NXB",s.getNXB());
        values.put("giaBia",s.getGiaBan());
        values.put("soLuong",s.getSoLuong());
        if (checkPrimaryKey(s.getmSach())){
            int result = db.update(TABLE_NAME,values,"masach=?", new
                    String[]{s.getmSach()});
            if (result == 0){
                return -1;
            }
        }else {
            try {
                if (db.insert(TABLE_NAME, null, values) == -1) {
                    return -1;
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        return 1;
    }
    //getAll
    public List<Sach> getAllSach(){
        List<Sach> dsSach = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Sach s = new Sach();
            s.setmSach(c.getString(0));
            s.setMaTheLoai(c.getString(1));
            s.setTenSach(c.getString(2));
            s.setTacGia(c.getString(3));
            s.setNXB(c.getString(4));
            s.setGiaBan(c.getDouble(5));
            s.setSoLuong(c.getInt(6));
            dsSach.add(s);
            Log.d("//=====",s.toString());
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }

    //update
    public int updateSach(Sach s){
        ContentValues values = new ContentValues();
        values.put("maSach",s.getmSach());
        values.put("maTheLoai",s.getMaTheLoai());
        values.put("tensach",s.getTenSach());
        values.put("tacGia",s.getTacGia());
        values.put("NXB",s.getNXB());
        values.put("giaBia",s.getGiaBan());
        values.put("soLuong",s.getSoLuong());
        int result = db.update(TABLE_NAME,values,"maSach=?", new
                String[]{s.getmSach()});
        if (result == 0){
            return -1;
        }
        return 1;
    }

    public int updateSachInfo(String maSach,String maTheLoai, String tenSach, String tacGia, String NXB, Double giaBan, int soLuong){
        ContentValues values = new ContentValues();
        values.put("maSach",maSach);
        values.put("tensach",tenSach);
        values.put("tacGia",tacGia);
        values.put("NXB",NXB);
        values.put("giaBia",giaBan);
        values.put("soLuong",soLuong);
        Log.d(TAG, "updateSachInfo: " +maSach+":"+tenSach+":"+tacGia+":"+NXB+":"+giaBan+":"+soLuong+":");
        int result = db.update(TABLE_NAME,values,"maSach=?", new String[]{maSach});
        if (result == 0){
            return -1;
        }
        return 1;
    }


    //delete
    public int deleteSachByID(String maSach){
        int result = db.delete(TABLE_NAME,"maSach=?",new String[]{maSach});
        if (result == 0)
            return -1;
        return 1;
    }
    //check
    public boolean checkPrimaryKey(String strPrimaryKey){
        //SELECT
        String[] columns = {"masach"};
        //WHERE clause
        String selection = "masach=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try{
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null,
                    null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if(i <= 0){
                return false;
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    //check
    public Sach checkBook(String strPrimaryKey){
        Sach s = new Sach();
        //SELECT
        String[] columns = {"masach"};
        //WHERE clause
        String selection = "masach=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try{
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null,
                    null);
            c.moveToFirst();
            while (c.isAfterLast()==false){
                s.setmSach(c.getString(0));
                s.setMaTheLoai(c.getString(1));
                s.setTenSach(c.getString(2));
                s.setTacGia(c.getString(3));
                s.setNXB(c.getString(4));
                s.setGiaBan(c.getDouble(5));
                s.setSoLuong(c.getInt(6));
                Log.d("//=====",s.toString());
                break;
            }
            c.close();
            return s;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //getAll
    public Sach getSachByID(String maSach){
        Sach s = null;
        //WHERE clause
        String selection = "masach=?";
        //WHERE clause arguments
        String[] selectionArgs = {maSach};
        Cursor c = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        Log.d("getSachByID","===>"+ c.getCount());
        c.moveToFirst();
        while (c.isAfterLast()==false){
            s = new Sach();
            s.setmSach(c.getString(0));
            s.setMaTheLoai(c.getString(1));
            s.setTenSach(c.getString(2));
            s.setTacGia(c.getString(3));
            s.setNXB(c.getString(4));
            s.setGiaBan(c.getDouble(5));
            s.setSoLuong(c.getInt(6));
            break;
        }
        c.close();
        return s;
    }
    //getAll
    public List<Sach> getSachTop10(String month){
        List<Sach> dsSach = new ArrayList<>();
        if (Integer.parseInt(month)<10){
            month = "0"+month;
        }
        String sSQL = "SELECT maSach, SUM(soLuong) as soluong FROM HoaDonChiTiet INNER JOIN HoaDon " +
        "ON HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon WHERE strftime('%m',HoaDon.ngayMua) = '"+month+"' " +
        "GROUP BY maSach ORDER BY soluong DESC LIMIT 10";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Log.d("//=====",c.getString(0));
            Sach s = new Sach();
            s.setmSach(c.getString(0));
            s.setSoLuong(c.getInt(1));
            s.setGiaBan(0);
            s.setMaTheLoai("");
            s.setTenSach("");
            s.setTacGia("");
            s.setNXB("");
            dsSach.add(s);
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }
}
