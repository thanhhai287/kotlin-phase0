import java.util.Scanner

fun main () {
    println("Hello World!")
    println(10/3)        // 3
    println("10" + 1)    // "101"
    println(7 % 3)       // 1
    println(1_000_000)   // 1000000
    println("a" + 1 + 2) // "a12"

    val scanner = Scanner(System.`in`)

    print("Nhập số trận THẮNG: ")
    val soTranThang = scanner.nextInt()

    print("Nhập TỔNG số trận đã chơi: ")
    val tongSoTran = scanner.nextInt()


    println("Nhap ten cua ban")
    val ten = readln()

    // Kiểm tra điều kiện logic
    if (tongSoTran <= 0) {
        println("Lỗi: Tổng số trận phải lớn hơn 0!")
        return
    }
    if (soTranThang > tongSoTran) {
        println("Lỗi: Số trận thắng không thể lớn hơn tổng số trận!")
        return
    }

    val tiLe = (soTranThang.toDouble() / tongSoTran) * 100

    // In kết quả làm tròn 2 chữ số thập phân
    println("\n--- KẾT QUẢ ---")
    println("Người chơi: $ten")
    println("Tỉ lệ thắng: %.2f%%".format(tiLe))


//    val x = 10
//    x = "abc" -> lỗi ghi gõ
}

