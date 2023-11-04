package repository;

import java.util.List;
import model.NhanVien;
import java.sql.*;
import java.util.ArrayList;

public class NhanVienService implements Inf_NV {

    String sql = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<NhanVien> getAll() {
        sql = "SELECT MaNV, HoTen, MatKhau, VaiTro FROM NhanVien";
        List<NhanVien> listnv = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
                listnv.add(nv);
            }
            return listnv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public NhanVien getNV(String maNV) {
        sql = "SELECT MaNV, HoTen, MatKhau, VaiTro FROM NhanVien WHERE MaNV = ?";
        NhanVien nv = null;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maNV);
            rs = ps.executeQuery();
            while (rs.next()) {
                nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
            }
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insert(NhanVien nv) {
        sql = "INSERT INTO NhanVien(MaNV, HoTen, MatKhau, VaiTro) VALUES (?, ?, ?, ?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, nv.getMaNV());
            ps.setObject(2, nv.getHoTen());
            ps.setObject(3, nv.getPassWord());
            ps.setObject(4, nv.isVaiTro());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(String id, NhanVien nv) {
        sql = "UPDATE NhanVien SET HoTen = ?, MatKhau = ?, VaiTro = ? WHERE MaNV = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, nv.getHoTen());
            ps.setObject(2, nv.getPassWord());
            ps.setObject(3, nv.isVaiTro());
            ps.setObject(4, nv.getMaNV());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        sql = "DELETE FROM NhanVien WHERE MaNV = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
