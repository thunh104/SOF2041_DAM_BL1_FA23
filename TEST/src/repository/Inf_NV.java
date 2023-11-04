package repository;

import model.NhanVien;

public interface Inf_NV {

    public int insert(NhanVien nv);

    public int update(String id, NhanVien nv);

    public int delete(String id);
}
