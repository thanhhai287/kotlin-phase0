# Chuyển project sang Gradle + JUnit5

Runbook cho buổi 2 của tuần chạy 4. Khoảng 1 giờ. Đây là **việc máy** (Luật 7) —
AI hướng dẫn toàn phần, không phải bài học Kotlin. Hai file build bên dưới cứ gõ
theo; phần **test** thì tự viết, đó mới là nội dung học.

Đã đo trên máy này ngày 05/10/2026:

- `kotlinc-jvm 2.4.20` (JRE 21.0.5+11-LTS)
- `java 21.0.5` Temurin, qua sdkman
- **chưa có `gradle`** — sdkman đang có đúng hai candidate: `java`, `kotlin`

Ngưỡng đạt: **`./gradlew test` xanh, chạy từ terminal.**

---

## Bước 0 — dọn mìn trước khi di chuyển (10')

Đây là bước quan trọng nhất, và nó là một bài học thật.

`kotlinc` cho tới giờ bạn chạy **từng file một**. Gradle thì khác: nó compile
**toàn bộ `src/main/kotlin` một lượt**. Mọi khai báo cấp file trong cùng một
package phải không trùng tên.

Hiện trạng `src/`:

| File | Khai báo cấp file |
|---|---|
| `bai-refactor-43.kt` | `class NguoiChoi`, **`class Phong`**, `timPhong`, `nhanChuPhong`, `soNguoiHienThi`, `main` |
| `variable.kt` | `diemCuoiTran`, `tinhTien`, `main` |
| `main.kt` | `main` |
| `thu-nghiem-nonnull.kt` | `main` |
| `nullsafety.kt` | *(đang bị comment hết — nhưng bên trong có `class ChuNha` và **`class Phong`**)* |

Hai chuyện:

1. **Bốn `fun main()` thì KHÔNG sao.** Đã đo thật: Kotlin gói mỗi file vào một
   class riêng (`VariableKt`, `Bai_refactor_43Kt`, …) nên chúng không đụng nhau.
2. **Hai `class Phong` thì VỠ.** `nullsafety.kt` và `bai-refactor-43.kt` đều
   muốn cái tên đó. Lúc này nullsafety đang bị comment nên chưa nổ; bỏ comment
   ra là Gradle báo `Redeclaration: Phong`.

Câu hỏi để bạn tự quyết, đừng hỏi AI đáp án:

> Hai file cùng muốn tên `Phong`. Cơ chế nào trong Kotlin/JVM sinh ra **để giải
> quyết đúng chuyện này**? Gõ `package ` vào đầu một file và xem IDE nói gì.

Chọn cách nào cũng được, nhưng phải chọn trước khi sang bước 2. Và ghi lại lý do
chọn — một dòng.

Dọn thêm, 2': `bai-refactor-43.kt` dòng 41 còn `?:` trên giá trị non-null.
`kotlinc` đã cảnh báo:

```
warning: elvis operator (?:) always returns the left operand of non-nullable type 'Int'
```

Gradle cũng sẽ nhắc lại. Việc nợ từ 28/09.

---

## Bước 1 — cài Gradle (5')

```bash
sdk install gradle
gradle --version
```

sdkman tải bản mới nhất. Không cần ghim phiên bản — bước 4 sẽ ghim hộ.

---

## Bước 2 — layout chuẩn Gradle (5')

Gradle quy ước `src/main/kotlin` cho code và `src/test/kotlin` cho test. Dùng
`git mv` chứ không phải `mv`, để git ghi lại là **đổi chỗ** thay vì xoá + thêm:

```bash
mkdir -p src/main/kotlin src/test/kotlin
git mv src/bai-refactor-43.kt src/main.kt src/nullsafety.kt \
       src/thu-nghiem-nonnull.kt src/variable.kt \
       src/main/kotlin/
git status
```

`git status` phải hiện `renamed:` cho cả năm file. Nếu nó hiện `deleted:` +
`new file:` thì bạn đã dùng `mv` — không sai, chỉ là lịch sử khó đọc hơn.

Tối nay bạn sẽ tạo thêm `src/nho-lai-43.kt` ở buổi tái nhập; nhớ `git mv` nó
luôn.

---

## Bước 3 — hai file build (15')

Gõ tay, đừng copy — mỗi dòng có một việc, và bạn phải đọc được chúng khi build
hỏng ở Phase 1.

**`settings.gradle.kts`** ở gốc repo:

```kotlin
rootProject.name = "kotlin-phase0"
```

**`build.gradle.kts`** ở gốc repo:

```kotlin
plugins {
    kotlin("jvm") version "2.4.20"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}
```

Từng dòng nói gì:

| Dòng | Việc |
|---|---|
| `kotlin("jvm") version "2.4.20"` | Nạp Kotlin Gradle plugin, khớp đúng `kotlinc` trên máy bạn. Đổi số này là đổi compiler |
| `mavenCentral()` | Tải thư viện từ đâu. Không có dòng này thì không tải được gì |
| `testImplementation(kotlin("test"))` | Thư viện test, **chỉ cho source set test** — code chính không thấy nó. Không cần ghi số phiên bản: plugin Kotlin tự chọn bản khớp |
| `jvmToolchain(21)` | Compile bằng JDK 21. Ghim ở đây để máy khác build ra **đúng** bytecode đó |
| `useJUnitPlatform()` | Chạy test bằng JUnit Platform (JUnit 5). Thiếu dòng này Gradle tìm JUnit 4 và báo "no tests found" |

Không thêm plugin `application`. Nó đòi đúng **một** `mainClass`, mà bạn đang có
bốn `main` — và bạn vẫn chạy từng file từ IntelliJ như cũ được.

---

## Bước 4 — wrapper (5')

```bash
gradle wrapper
./gradlew --version
git add gradlew gradlew.bat gradle/ settings.gradle.kts build.gradle.kts
```

Từ đây trở đi **luôn gõ `./gradlew`**, không gõ `gradle`. Wrapper ghim đúng một
phiên bản Gradle vào repo, nên máy nào clone về cũng build ra kết quả như nhau —
đó là lý do `gradlew` và `gradle/` phải được commit, không được ignore.

---

## Bước 5 — .gitignore (2')

Thêm vào cuối `.gitignore`:

```
### Gradle ###
.gradle/
build/
```

`.gradle/` là cache, `build/` là output. Cả hai sinh lại được, không commit.
`gradle/wrapper/` thì **phải** commit — đừng ignore nhầm cả hai.

---

## Bước 6 — build xanh (5')

```bash
./gradlew build
```

Lần đầu tải khá nhiều, có thể 1–3 phút. Mong đợi: `BUILD SUCCESSFUL`, kèm
cảnh báo `?:` nếu bạn chưa dọn ở bước 0.

---

## Bước 7 — một test xanh (15') — phần này TỰ VIẾT

Đây là nội dung học, không phải việc máy. AI không viết test hộ.

Tạo `src/test/kotlin/SoNguoiHienThiTest.kt`. Dùng `kotlin.test.Test` và
`kotlin.test.assertEquals` (chúng chạy trên JUnit5 nhờ `useJUnitPlatform()`).

Viết **ba** case — đúng cấu trúc test 3 tầng của tuần này:

| Tầng | Input | Kỳ vọng |
|---|---|---|
| happy | `"P1"` | `"2 nguoi"` |
| edge | `"P3"` | `"0 nguoi"` |
| error | `null` | `"khong ro"` |

Ba giá trị kỳ vọng **không phải tôi cho** — chúng nằm trong
`notes/4.3-output-goc.txt`, baseline do chính bạn chạy ra ngày 28/09.

Tầng "edge" là tầng đáng tiền: `"0 nguoi"` khác `"khong ro"`, và đó đúng là chỗ
refactor của bạn từng làm sai một lần.

```bash
./gradlew test
```

Xanh là đạt ngưỡng. Xem báo cáo chi tiết ở `build/reports/tests/test/index.html`.

**Luật từ nay:** mọi hàm logic thuần có ≥1 test.

---

## Lỗi hay gặp — đọc theo quy trình 4 bước

| Thông báo | Nhìn vào đâu |
|---|---|
| `Plugin [id: 'org.jetbrains.kotlin.jvm', version: '2.4.20'] was not found` | Số phiên bản ở `build.gradle.kts`. Chạy `kotlinc -version` lấy số thật, hoặc dùng bản mới nhất trên plugins.gradle.org |
| `Redeclaration: Phong` | Bước 0. Hai file cùng tên class trong cùng package |
| `No matching toolchains found ... languageVersion=21` | `jvmToolchain(21)` không khớp JDK đang có. `java -version` xem thật là bản nào |
| `Could not find junit-platform-launcher` | Thêm `testRuntimeOnly("org.junit.platform:junit-platform-launcher")` vào `dependencies` |
| `No tests found for given includes` | Thiếu `useJUnitPlatform()`, hoặc file test nằm sai chỗ (phải ở `src/test/kotlin`) |
| `Permission denied: ./gradlew` | `chmod +x gradlew` |

Mỗi lỗi bạn gặp thật: 3 dòng vào `notes/ERRORS.md` — tên lỗi / triệu chứng /
cách nhận diện lần sau. Build hỏng cũng là con thú, và nó không nằm trong sở thú
có sẵn.

---

## Commit

```bash
git add -A
git commit -m 'chore: chuyển project sang Gradle + JUnit5, một test xanh'
git push
```

Nháy **đơn** — nháy đôi để bash ăn mất `!!` một lần rồi (xem `notes/ERRORS.md`).
