package dao;

import entity.NhanVien;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NhanVienDAO extends PolyProDAO<NhanVien, String> {

    @Override
    public void insert(NhanVien entity) {
        String sql = "INSERT INTO NhanVien(MaNV, MatKhau, HoTen, VaiTro) VALUES (?, ?, ?, ?)";
        JDBCHelper.executeUpdate(sql,
                entity.getMaNV(),
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.isVaiTro()
        );
    }

    @Override
    public void update(NhanVien entity) {
        String sql = "UPDATE NhanVien SET MatKhau = ?, HoTen = ?, VaiTro = ? WHERE MaNV = ?";
        JDBCHelper.executeUpdate(sql,
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.isVaiTro(),
                entity.getMaNV()
        );
    }

    @Override
    public void delete(String MaNV) {
        String sql = "DELETE FROM NhanVien WHERE MaNV = ?";
        JDBCHelper.executeUpdate(sql, MaNV);
    }

    @Override
    public List<NhanVien> selectAll() {
        String sql = "SELECT * FROM NhanVien";
        return selectBySQL(sql);
    }

    @Override
    public NhanVien selectById(String MaNV) {
        String sql = "SELECT MaNV, MatKhau, HoTen, VaiTro FROM NhanVien WHERE MaNV = ?";
        List<NhanVien> list = selectBySQL(sql, MaNV);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setVaiTro(rs.getBoolean("VaiTro"));
                list.add(nv);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
}
