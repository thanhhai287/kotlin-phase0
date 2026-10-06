package choi.phong
data class CauHinhPhong(
    val kichThuoc: Int,
    val luatThang: Int,
    val matKhau: String?,
    val thoiGianGioiHan: Int?
)

fun kiemTraCauHinhPhong(cauHinhPhong : CauHinhPhong) : List<String> {
    val loi = mutableListOf<String>()

    if (cauHinhPhong.kichThuoc !in 3..19) {
        loi.add("Kich thuoc phai tu 3 den 19, nhan ${cauHinhPhong.kichThuoc}")
    }

    if (cauHinhPhong.luatThang !in 3.. cauHinhPhong.kichThuoc) {
        loi.add("Luat thang phai tu 3 den kich thuoc ban, nhan ${cauHinhPhong.luatThang}")
    }

    if (cauHinhPhong.matKhau != null && cauHinhPhong.matKhau.length !in 4 .. 16) {
        loi.add("Mat khau phai tu 4-16 ki tu, nhan ${cauHinhPhong.matKhau.length}")
    }

    if (cauHinhPhong.thoiGianGioiHan != null && cauHinhPhong.thoiGianGioiHan !in 5 .. 120) {
        loi.add("Thoi gian gioi han tu 5 den 120, nhan ${cauHinhPhong.thoiGianGioiHan}")
    }

    return loi
}

fun main() {
    val cauHinhPhong1 = CauHinhPhong(kichThuoc = 2, luatThang =  1, matKhau = null,  thoiGianGioiHan = null)
    val cauHinhPhong2 = CauHinhPhong(kichThuoc = 1, luatThang =  2, matKhau = null, thoiGianGioiHan = null)
    val cauHinhPhong3 = CauHinhPhong(kichThuoc = 3, luatThang =  4, matKhau = "ab", thoiGianGioiHan = 4)
    val cauHinhPhong4 = CauHinhPhong(kichThuoc = 4, luatThang =  4, matKhau = null, thoiGianGioiHan = null)
    println(kiemTraCauHinhPhong(cauHinhPhong1))
    println(kiemTraCauHinhPhong(cauHinhPhong2))
    println(kiemTraCauHinhPhong(cauHinhPhong3))
    println(kiemTraCauHinhPhong(cauHinhPhong4))
}
