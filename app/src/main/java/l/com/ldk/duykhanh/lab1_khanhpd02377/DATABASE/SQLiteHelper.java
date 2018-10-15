package l.com.ldk.duykhanh.lab1_khanhpd02377.DATABASE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.DAO_NguoiDung;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.HoaDonChiTietDAO;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.HoaDonDAO;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.SachDAO;
import l.com.ldk.duykhanh.lab1_khanhpd02377.DAO.TheLoaiDAO;
import l.com.ldk.duykhanh.lab1_khanhpd02377.Model.HoaDonChiTiet;

public class SQLiteHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME= "dbBookManager";
    public static final int VERSION = 1;


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DAO_NguoiDung.SQL_NGUOI_DUNG);
        db.execSQL(TheLoaiDAO.SQL_THE_LOAI);
        db.execSQL(SachDAO.SQL_SACH);
        db.execSQL(HoaDonDAO.SQL_HOA_DON);
        db.execSQL(HoaDonChiTietDAO.SQL_HDCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+DAO_NguoiDung.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TheLoaiDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+SachDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+HoaDonDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+HoaDonChiTietDAO.TABLE_NAME);

        onCreate(db);

    }
}
