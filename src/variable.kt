fun diemCuoiTran(diemGoc: Int, thuongChuoi: Int, phatBoCuoc: Int = 0): Int =
    diemGoc + thuongChuoi - phatBoCuoc

fun tinhTien(donGia: Int, soLuong: Int, giamGia: Int = 0, phiSan: Int = 0): Int =
    (donGia * soLuong) - giamGia + phiSan
fun main () {
    val diem = diemCuoiTran(diemGoc = 120, thuongChuoi = 3)
    println(diem)

    val soTien = tinhTien(donGia = 12_000, soLuong = 2, giamGia =  3_000, phiSan = 1_000)
    val soTien2 = tinhTien(donGia = 13_000, soLuong = 1, giamGia =  3_000)
    val soTien3 = tinhTien(donGia = 10_000, soLuong = 4)
    val soTien4 = tinhTien(donGia = 10_000, soLuong = 4, giamGia = 0, phiSan = 0)
    println(soTien)
    println(soTien2)
    println(soTien3)
    println(soTien4)

    val nguoiChoiList = mutableListOf<String>("Hai", "Huy")
    val nguoiChoi1 = nguoiChoiList
    val nguoiChoi2 = nguoiChoiList
    val a: List<String> = nguoiChoiList
    nguoiChoi2.add("Thanh")
    println(nguoiChoi1)
    nguoiChoi2.add("Minh")
    println(a)

    // giá trị của nguoiChoi1 chính xác là 1 con trỏ, và giá trị của con trỏ là bị val khóa (ở đây là mutableList) không để đổi sang ImmutableList hay giá trị khác như Int, String,...
    // bản chất có thể add vì đó là tính chất của mutableList, không nằm ở biến
    // Nếu thêm  val a: List<String> = nguoiChoiList, thì vẫn thấy Minh vì lúc này đang trỏ tới nguoiChoiList nên vẫn thấy được toàn bộ giá trị của nguoiChoiList
    // nhưng nếu a.add("Minh") thì không thể complie vì lúc này biến a đã được khai báo bằng type List, và quyền năng đã được dodoir hteo list
}