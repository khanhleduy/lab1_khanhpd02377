package l.com.ldk.duykhanh.lab1_khanhpd02377.Model;

public class Sach {
    private String mSach;
    private String maTheLoai;
    private String tenSach;
    private String tacGia;
    private String NXB;
    private double giaBan;
    private int soLuong;

    public Sach() {
    }

    public Sach(String mSach, String maTheLoai, String tenSach, String tacGia, String NXB, double giaBan, int soLuong) {
        this.mSach = mSach;
        this.maTheLoai = maTheLoai;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.NXB = NXB;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    public String getmSach() {
        return mSach;
    }

    public void setmSach(String mSach) {
        this.mSach = mSach;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "Sach{" +
                "mSach='" + mSach + '\'' +
                ", maTheLoai='" + maTheLoai + '\'' +
                ", tenSach='" + tenSach + '\'' +
                ", tacGia='" + tacGia + '\'' +
                ", NXB='" + NXB + '\'' +
                ", giaBan=" + giaBan +
                ", soLuong=" + soLuong +
                '}';
    }
}
