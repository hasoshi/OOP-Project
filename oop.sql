-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 31, 2019 lúc 04:53 PM
-- Phiên bản máy phục vụ: 10.4.6-MariaDB
-- Phiên bản PHP: 7.2.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `oop`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khach_hang`
--

CREATE TABLE `khach_hang` (
  `id` varchar(3) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` int(11) NOT NULL,
  `Date of birth` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `khach_hang`
--

INSERT INTO `khach_hang` (`id`, `name`, `phone`, `Date of birth`) VALUES
('A02', 'Nguyen Van A', 123456789, '11/03/1992'),
('A01', 'Nguyen Van Duong', 123671832, '10/10/2000'),
('B01', 'Pham Hong Ngoc', 378291827, '03/12/2000'),
('B02', 'Nguyen Quang Hung', 678473782, '06/03/1996'),
('A11', 'Nguyen Thu Ha', 675893820, '22/10/1993'),
('A45', 'Nguyen Quynh Anh', 767837190, '06/02/1998'),
('B81', 'Phan Quynh Anh', 567821009, '17/09/1997'),
('B12', 'Tran Ngoc Anh', 567398271, '28/12/1999'),
('A10', 'Le Ha Thu', 127831980, '12/04/2000'),
('B46', 'Pham Ha Linh', 91673829, '09/02/2000'),
('B10', 'Dong Vu Ha', 672918022, '11/08/2001'),
('B03', 'Trieu Quoc Khanh', 567123901, '04/02/2002'),
('A04', 'Pham Ha Anh', 109237846, '13/12/1999'),
('A05', 'Nguyen Hong Ha', 678391211, '22/03/1998'),
('B04', 'Tran Quoc Dat', 677191223, '05/05/1998'),
('B09', 'Pham Thu Huong', 192011204, '29/12/2000'),
('B07', 'Nguyen Thi Hong', 676389731, '11/06/2003'),
('A06', 'Dao Nhu Anh', 789345123, '04/01/1996'),
('A08', 'Le Anh Duc', 789120909, '20/01/2000'),
('B21', 'Dao Huy Manh', 678290182, '05/11/1998'),
('B31', 'Le Hoang Anh', 678391281, '01/12/2001'),
('B19', 'Pham thi Ha', 671112281, '30/06/2000'),
('A19', 'Nguyen Yen Nhi', 381967247, '30/06/1999'),
('A18', 'Do Kieu Hanh', 56700102, '28/07/2000'),
('A22', 'Nguyen Van G', 678391098, '01/01/2000'),
('A22', 'Nguyen van G', 678391098, '01/01/2000'),
('C11', 'Nguyen Van Nam', 678347198, '10/10/1996');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `khach_hang`
--
ALTER TABLE `khach_hang`
  ADD KEY `id` (`id`,`name`,`phone`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
