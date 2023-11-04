USE master
GO

CREATE DATABASE ThiThu
GO

USE ThiThu
GO

CREATE TABLE NhanVien
(
	MaNV VARCHAR(10) PRIMARY KEY,
	HoTen NVARCHAR(50) NOT NULL,
	MatKhau VARCHAR(20) NOT NULL,
	VaiTro BIT NOT NULL
);

INSERT INTO NhanVien(MaNV, HoTen, MatKhau, VaiTro)
VALUES
('NV01', N'Nguyễn Hoài Thu', 'hoaithu', 1),
('NV02', N'Hoàng Thu Trang', 'thutrang', 0),
('NV03', N'Nguyễn Hồng Ánh', 'honganh', 1),
('NV04', N'Nguyễn Ngọc Lan', 'ngoclan', 0),
('NV05', N'Đào Thị Thương', 'thuong04', 1)

SELECT MaNV, HoTen, MatKhau, VaiTro FROM NhanVien
SELECT HoTen, MatKhau, VaiTro FROM NhanVien WHERE MaNV = ?
INSERT INTO NhanVien(MaNV, HoTen, MatKhau, VaiTro) VALUES (?, ?, ?, ?)
UPDATE NhanVien SET HoTen = ?, MatKhau = ?, VaiTro = ? WHERE MaNV = ?
DELETE FROM NhanVien WHERE MaNV = ?