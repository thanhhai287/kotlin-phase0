// ĐỀ BÀI module 4.3 — 40 dòng "giọng Java".
// File này do AI SINH RA làm đề, KHÔNG phải code của learner.
//
// Nhiệm vụ: refactor thành Kotlin idiomatic, ngay trong file này.
//   1. Hành vi giữ NGUYÊN — output của main() phải giống hệt trước và sau.
//      Chạy trước, lưu output lại, refactor, chạy lại, so từng dòng.
//   2. Zero `!!`.
//   3. Mỗi dấu `?` còn sót lại phải NÓI ĐÚNG SỰ THẬT — cái nào không thể null
//      thì đừng khai `?` cho "an toàn".

class NguoiChoi(val ten: String?, val hangThanhVien: String?)

class Phong(val ma: String?, val chuPhong: NguoiChoi?, val soNguoi: Int?)

fun timPhong(ma: String?): Phong? {
    if (ma == null) {
        return null
    }
    if (ma == "P1") {
        return Phong("P1", NguoiChoi("Hai", "VANG"), 2)
    }
    if (ma == "P2") {
        return Phong("P2", NguoiChoi(null, "BAC"), 1)
    }
    if (ma == "P3") {
        return Phong("P3", null, 0)
    }
    return null
}

fun nhanChuPhong(maPhong: String?): String? {
    val phong = timPhong(maPhong)
    if (phong != null) {
        val chu = phong.chuPhong
        if (chu != null) {
            val ten = chu.ten
            if (ten != null) {
                var hang = chu.hangThanhVien
                if (hang == null) {
                    hang = "THUONG"
                }
                return "Chu phong " + ten + " (" + hang + ")"
            } else {
                return null
            }
        } else {
            return null
        }
    }
    return null
}

fun soNguoiHienThi(maPhong: String?): String {
    val phong = timPhong(maPhong)
    if (phong == null) {
        return "khong ro"
    }
    val n = phong.soNguoi
    if (n == null) {
        return "khong ro"
    }
    return n.toString() + " nguoi"
}

fun main() {
    println(nhanChuPhong("P1"))
    println(nhanChuPhong("P2"))
    println(nhanChuPhong("P3"))
    println(nhanChuPhong("P9"))
    println(nhanChuPhong(null))
    println(soNguoiHienThi("P1"))
    println(soNguoiHienThi("P3"))
    println(soNguoiHienThi(null))
}
