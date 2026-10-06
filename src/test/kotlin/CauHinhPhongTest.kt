package test.Kotlin

import choi.phong.CauHinhPhong
import choi.phong.kiemTraCauHinhPhong
import kotlin.test.Test
import kotlin.test.assertEquals

class CauHinhPhongTest {

    @Test
    fun `khong co loi thi tra ve danh sach rong`() {
        val cauHinhPhong = CauHinhPhong(kichThuoc = 4, luatThang =  4, matKhau = null, thoiGianGioiHan = null)
        assertEquals(emptyList<String>(), kiemTraCauHinhPhong(cauHinhPhong))
    }
}
