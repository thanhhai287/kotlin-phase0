class ChuNha(val ten: String?)
class Phong(val chuNha: ChuNha?)

fun main() {
    val phong = Phong(
        chuNha =  ChuNha(null)
    )
    println(phong?.chuNha?.ten)
}