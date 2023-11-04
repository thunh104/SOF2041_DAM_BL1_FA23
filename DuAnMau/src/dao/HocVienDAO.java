package dao;

import entity.HocVien;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import utils.JDBCHelper;

public class HocVienDAO extends PolyProDAO<HocVien, Integer> {

    @Override
    public void insert(HocVien entity) {
        String sql = "INSERT INTO HocVien(MaKH, MaNH, Diem) VALUES (?, ?, ?)";
        JDBCHelper.executeUpdate(sql,
                entity.getMaKH(),
                entity.getMaNH(),
                entity.getDiem()
        );
    }

    @Override
    public void update(HocVien entity) {
        String sql = "UPDATE HocVien SET MaKH = ?, MaNH = ?, Diem = ? WHERE MaHV = ?";
        JDBCHelper.executeUpdate(sql,
                entity.getMaKH(),
                entity.getMaNH(),
                entity.getDiem(),
                entity.getMaHV()
        );
    }

    @Override
    public void delete(Integer MaHV) {
        String sql = "DELETE FROM HocVien WHERE MaHV = ?";
        JDBCHelper.executeUpdate(sql, MaHV);
    }

    @Override
    public List<HocVien> selectAll() {
        String sql = "SELECT * FROM HocVien";
        return selectBySQL(sql);
    }

    @Override
    public HocVien selectById(Integer MaHV) {
        String sql = "SELECT MaHV, MaKH, MaNH, Diem FROM HocVien WHERE MaHV = ?";
        List<HocVien> list = selectBySQL(sql, MaHV);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<HocVien> selectBySQL(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                HocVien hv = new HocVien();
                hv.setMaHV(rs.getInt("MaHV"));
                hv.setMaKH(rs.getInt("MaKH"));
                hv.setMaNH(rs.getString("MaNH"));
                hv.setDiem(rs.getDouble("Diem"));
                list.add(hv);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<HocVien> selectByKhoaHoc(int MaKH) {
        String sql = "SELECT MaKH, MaHV, HocVien.MaNH, NguoiHoc.HoTen, Diem FROM HocVien JOIN NguoiHoc ON HocVien.MaNH = NguoiHoc.MaNH WHERE MaKH = ?";
        return this.selectBySQL(sql, MaKH);
    }
}
