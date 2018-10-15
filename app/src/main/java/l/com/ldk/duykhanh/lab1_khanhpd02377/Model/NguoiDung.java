package l.com.ldk.duykhanh.lab1_khanhpd02377.Model;

public class NguoiDung {

    private String username;
    private String password;
    private String phone;
    private String fullName;

    public NguoiDung() {
    }

    public NguoiDung(String username, String password, String phone, String fullName) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "username=" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", fullName" + fullName + '\'' +
                '}';
    }
}
