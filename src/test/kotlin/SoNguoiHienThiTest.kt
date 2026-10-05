package test.Kotlin

import choi.phong.soNguoiHienThi
import kotlin.test.Test
import kotlin.test.assertEquals

class SoNguoiHienThiTest {

    @Test
    fun `so nguoi hien thi phong 1 la 2 nguoi`() {
        val ketQua = soNguoiHienThi("P1")
        assertEquals("2 nguoi", ketQua)
    }

    @Test
    fun `so nguoi hien thi phong 3 la 0 nguoi`() {
        assertEquals("0 nguoi", soNguoiHienThi("P3"))
    }

    @Test
    fun `so nguoi hien thi khi phong null la khong ro`() {
        assertEquals("khong ro", soNguoiHienThi(null))

    }
}
