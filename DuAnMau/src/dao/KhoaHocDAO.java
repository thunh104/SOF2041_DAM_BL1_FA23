package dao;

import entity.KhoaHoc;
import java.sql.*;
import utils.JDBCHelper;
import java.util.ArrayList;
import java.util.List;

public class KhoaHocDAO extends PolyProDAO<KhoaHoc, Integer> {

    @Override
    public void insert(KhoaHoc entity) {
        String sql = "INSERT INTO KhoaHoc(MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV, NgayTao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        JDBCHelper.executeUpdate(sql,
                entity.getMaCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getNgayKG(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayTao()
        );
    }

    @Override
    public void update(KhoaHoc entity) {
        String sql = "UPDATE KhoaHoc SET MaCD = ?, HocPhi = ?, ThoiLuong = ?, NgayKG = ?, GhiChu = ?, MaNV = ?, NgayTao = ? WHERE MaKH = ?";
        JDBCHelper.executeUpdate(sql,
                entity.getMaCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getNgayKG(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayTao(),
                entity.getMaKH()
        );
    }

    @Override
    public void delete(Integer MaKH) {
        String sql = "DELETE FROM KhoaHoc WHERE MaKH = ?";
        JDBCHelper.executeUpdate(sql, MaKH);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        String sql = "SELECT DISTINCT * FROM KhoaHoc";
        return selectBySQL(sql);
    }

    @Override
    public KhoaHoc selectById(Integer MaKH) {
        String sql = "SELECT MaKH, MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV, NgayTao FROM KhoaHoc WHERE MaKH = ?";
        List<KhoaHoc> list = selectBySQL(sql, MaKH);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<KhoaHoc> selectByChuyenDe(String MaCD) {
        String sql = "SELECT * FROM KhoaHoc WHERE MaCD = ?";
        return this.selectBySQL(sql, MaCD);
    }

    @Override
    protected List<KhoaHoc> selectBySQL(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    KhoaHoc kh = new KhoaHoc();
                    kh.setMaKH(rs.getInt("MaKH"));
                    kh.setMaCD(rs.getString("MaCD"));
                    kh.setHocPhi(rs.getDouble("HocPhi"));
                    kh.setThoiLuong(rs.getInt("ThoiLuong"));
                    kh.setNgayKG(rs.getDate("NgayKG"));
                    kh.setGhiChu(rs.getString("GhiChu"));
                    kh.setMaNV(rs.getString("MaNV"));
                    kh.setNgayTao(rs.getDate("NgayTao"));
                    list.add(kh);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<Integer> selectYears() {
        String sql = "SELECT DISTINCT year(NgayKG) as Year FROM KhoaHoc ORDER BY Year DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
