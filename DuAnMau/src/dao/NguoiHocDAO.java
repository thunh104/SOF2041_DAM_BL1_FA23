package dao;

import entity.NguoiHoc;
import utils.JDBCHelper;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NguoiHocDAO extends PolyProDAO<NguoiHoc, String> {

    @Override
    public void insert(NguoiHoc entity) {
        String sql = "INSERT INTO NguoiHoc(MaNH, HoTen, GioiTinh, NgaySinh, DienThoai, Email, GhiChu, MaNV, NgayDK) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        JDBCHelper.executeUpdate(sql,
                entity.getMaNH(),
                entity.getHoTen(),
                entity.isGioiTinh(),
                entity.getNgaySinh(),
                entity.getDienThoai(),
                entity.getEmail(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayDK()
        );
    }

    @Override
    public void update(NguoiHoc entity) {
        String sql = "UPDATE NguoiHoc SET HoTen = ?, GioiTinh = ?, NgaySinh = ?, DienThoai = ?, Email = ?, GhiChu = ?, MaNV = ?, NgayDK = ? WHERE MaNH = ?";
        JDBCHelper.executeUpdate(sql,
                entity.getHoTen(),
                entity.isGioiTinh(),
                entity.getNgaySinh(),
                entity.getDienThoai(),
                entity.getEmail(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayDK(),
                entity.getMaNH()
        );
    }

    @Override
    public void delete(String MaNH) {
        String sql = "DELETE FROM NguoiHoc WHERE MaNH = ?";
        JDBCHelper.executeUpdate(sql, MaNH);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        String sql = "SELECT * FROM NguoiHoc";
        return selectBySQL(sql);
    }

    @Override
    public NguoiHoc selectById(String MaNH) {
        String sql = "SELECT MaNH, HoTen, GioiTinh, NgaySinh, DienThoai, Email, GhiChu, MaNV, NgayDK FROM NguoiHoc WHERE MaNH = ?";
        List<NguoiHoc> list = selectBySQL(sql, MaNH);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NguoiHoc> selectBySQL(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                NguoiHoc nh = new NguoiHoc();
                nh.setMaNH(rs.getString("MaNH"));
                nh.setHoTen(rs.getString("HoTen"));
                nh.setGioiTinh(rs.getBoolean("GioiTinh"));
                nh.setNgaySinh(rs.getDate("NgaySinh"));
                nh.setDienThoai(rs.getString("DienThoai"));
                nh.setEmail(rs.getString("Email"));
                nh.setGhiChu(rs.getString("GhiChu"));
                nh.setMaNV(rs.getString("MaNV"));
                nh.setNgayDK(rs.getDate("NgayDK"));
                list.add(nh);
            }// đc chưa chuwa aj :>
            //mở xem nốt để đi ngủ nào
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<NguoiHoc> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ?";
        return this.selectBySQL(sql, "%" + keyword + "%");
    }

    public List<NguoiHoc> selectNotInCourse(int MaKH, String keyword) {
        String sql = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ? AND MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH = ?)";
        return this.selectBySQL(sql, "%" + keyword + "%", MaKH);
    }
}
