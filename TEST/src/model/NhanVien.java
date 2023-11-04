package model;

public class NhanVien {

    private String maNV;
    private String hoTen;
    private String passWord;
    private boolean vaiTro;

    public NhanVien() {
    }

    public NhanVien(String maNV, String hoTen, String passWord, boolean vaiTro) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.passWord = passWord;
        this.vaiTro = vaiTro;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.maNV,
            this.hoTen,
            this.passWord,
            this.vaiTro ? "Quản lí" : "Nhân viên"
        };
    }
}
