# notes/RECALL.md — 1 dòng = 1 thẻ: [hộp] [đến hạn] câu hỏi → đáp án 1 dòng
# Hộp: h1 1 ngày · h2 3 ngày · h3 1 tuần · h4 3 tuần · h5 2 tháng. Sai ở hộp nào cũng về h1.
# Trả lời ra miệng hoặc gõ 1 dòng TRƯỚC khi nhìn đáp án — "à đúng rồi" tính là sai.

[h1] 13/10  Cấu hình kichThuoc=2, luatThang=3: người dùng sai mấy chỗ, hàm báo mấy lỗi, vì sao lệch? → sai 1 chỗ (kichThuoc), hàm báo 2: luật so luatThang với kichThuoc chạy trên field đã hỏng → lỗi dây chuyền (quyết định 12/10: giữ)
[h1] 13/10  Vì sao 3 !in 3..2 cho true? → .. chỉ đếm lên nên 3..2 là range rỗng; không phần tử nào ở trong thì !in luôn true (đếm xuống dùng downTo)
[h1] 13/10  Cái gì quyết định file thuộc package nào, và test khác package cần gì để thấy class? → dòng package đầu file, không phải folder (lệch folder chỉ là cảnh báo quy ước); khác package thì phải import
