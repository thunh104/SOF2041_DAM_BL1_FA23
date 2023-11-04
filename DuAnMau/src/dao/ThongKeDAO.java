package dao;

import java.util.List;
import utils.JDBCHelper;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ThongKeDAO {
private List<Object[]> getListOfArray(String sql, String[] cols, Object...args){
    try {
        List<Object[]> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.executeQuery(sql, args);
        while(rs.next()){
            Object[] vals = new Object[cols.length];
            for(int i = 0; i < cols.length; i++){
                vals[i] = rs.getObject(cols[i]);
            }
            list.add(vals);
        }
        rs.getStatement().getConnection().close();
        return list;
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
    public List<Object[]> getNguoiHoc() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{CALL sp_ThongKeNguoiHoc}";
                rs = JDBCHelper.executeQuery(sql);
                while (rs.next()) {
                    Object[] tknh = {
                        rs.getInt("Nam"),
                        rs.getInt("SoLuong"),
                        rs.getDate("DauTien"),
                        rs.getDate("CuoiCung")
                    };
                    list.add(tknh);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<Object[]> getBangDiem(Integer MaKH) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{CALL sp_BangDiem (?)}";
                rs = JDBCHelper.executeQuery(sql, MaKH);
                while (rs.next()) {
                    double diem = rs.getDouble("Diem");
                    String xepLoai = "Xuất sắc";
                    if (diem < 0) {
                        xepLoai = "Chưa nhập";
                    } else if (diem < 3) {
                        xepLoai = "Kém";
                    } else if (diem < 5) {
                        xepLoai = "Yếu";
                    } else if (diem < 6.5) {
                        xepLoai = "Trung bình";
                    } else if (diem < 7.5) {
                        xepLoai = "Khá";
                    } else if (diem < 9) {
                        xepLoai = "Giỏi";
                    }
                    Object[] tk = {
                        rs.getString("MaNH"),
                        rs.getString("HoTen"),
                        diem,
                        xepLoai
                    };
                    list.add(tk);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<Object[]> getDiemTheoChuyenDe() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{CALL sp_ThongKeDiem}";
                rs = JDBCHelper.executeQuery(sql);
                while (rs.next()) {
                    Object[] tkd = {
                        rs.getString("ChuyenDe"),
                        rs.getInt("SoHV"),
                        rs.getDouble("ThapNhat"),
                        rs.getDouble("CaoNhat"),
                        rs.getDouble("TrungBinh")
                    };
                    list.add(tkd);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<Object[]> getDoanhThu(int nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{CALL sp_ThongKeDoanhThu (?)}";
                rs = JDBCHelper.executeQuery(sql, nam);
                while (rs.next()) {
                    Object[] tkdt = {
                        rs.getString("ChuyenDe"),
                        rs.getInt("SoKH"),
                        rs.getInt("SoHV"),
                        rs.getDouble("DoanhThu"),
                        rs.getDouble("ThapNhat"),
                        rs.getDouble("CaoNhat"),
                        rs.getDouble("TrungBinh")
                    };
                    list.add(tkdt);
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
