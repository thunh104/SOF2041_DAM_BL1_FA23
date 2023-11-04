package dao;

import entity.ChuyenDe;
import java.util.List;
import java.util.ArrayList;
import utils.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChuyenDeDAO extends PolyProDAO<ChuyenDe, String> {

    @Override
    public void insert(ChuyenDe entity) {
        String sql = "INSERT INTO ChuyenDe(MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa) VALUES (?, ?, ?, ?, ?, ?)";
        JDBCHelper.executeUpdate(sql,
                entity.getMaCD(),
                entity.getTenCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getHinh(),
                entity.getMoTa()
        );
    }

    @Override
    public void update(ChuyenDe entity) {
        String sql = "UPDATE ChuyenDe SET TenCD = ?, HocPhi = ?, ThoiLuong = ?, Hinh = ?, MoTa = ? WHERE MaCD = ?";
        JDBCHelper.executeUpdate(sql,
                entity.getTenCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getHinh(),
                entity.getMoTa(),
                entity.getMaCD()
        );
    }

    @Override
    public void delete(String MaCD) {
        String sql = "DELETE FROM ChuyenDe WHERE MaCD = ?";
        JDBCHelper.executeUpdate(sql, MaCD);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        String sql = "SELECT * FROM ChuyenDe";
        return selectBySQL(sql);
    }

    @Override
    public ChuyenDe selectById(String MaCD) {
        String sql = "SELECT MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa FROM ChuyenDe WHERE MaCD = ?";
        List<ChuyenDe> list = selectBySQL(sql, MaCD);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<ChuyenDe> selectBySQL(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    ChuyenDe cd = new ChuyenDe();
                    cd.setMaCD(rs.getString("MaCD"));
                    cd.setTenCD(rs.getString("TenCD"));
                    cd.setHocPhi(rs.getDouble("HocPhi"));
                    cd.setThoiLuong(rs.getInt("ThoiLuong"));
                    cd.setHinh(rs.getString("Hinh"));
                    cd.setMoTa(rs.getString("MoTa"));
                    list.add(cd);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
}
