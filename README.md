# ماشین‌حساب اندروید | Android Calculator

[فارسی](#فارسی) · [English](#english)

---

<a id="فارسی"></a>
## فارسی

یک ماشین‌حساب اندرویدی ساخته‌شده با Kotlin و Jetpack Compose.

### امکانات
- جمع، تفریق، ضرب و تقسیم
- درصد، اعشار، پرانتز و اعداد منفی
- رعایت تقدم استاندارد عملگرها
- پاک‌کردن کامل (AC)، حذف آخرین نویسه (DEL) و محاسبه (=)
- نمایش نتیجه زنده و مدیریت خطاها
- رابط کاربری تیره
- تست‌های واحد برای موتور محاسبات

### اجرای پروژه
1. Android Studio و JDK 17 را نصب کنید.
2. مخزن را Clone و در Android Studio باز کنید.
3. Gradle Sync را اجرا کنید.
4. شبیه‌ساز یا گوشی با Android 7.0 (API 24) یا بالاتر را انتخاب کنید.
5. Run را بزنید.

### ساخت APK
از منوی Build گزینه Build APK(s) را انتخاب کنید. خروجی در مسیر `app/build/outputs/apk/debug/app-debug.apk` قرار می‌گیرد.

### ساخت خودکار با GitHub Actions
با هر Push یا Pull Request به شاخه main، تست‌ها اجرا و APK نسخه Debug ساخته می‌شود. برای دریافت APK، اجرای موفق را در بخش Actions باز و Artifact با نام `android-calculator-debug` را دانلود کنید.

### فناوری‌ها
- Kotlin 2.0.21
- Jetpack Compose و Material 3
- Android Gradle Plugin 8.7.3
- Minimum SDK: 24 | Target SDK: 35



---

<a id="english"></a>
## English

An Android calculator built with Kotlin and Jetpack Compose.

### Features
- Addition, subtraction, multiplication, and division
- Percentages, decimals, parentheses, and negative numbers
- Standard operator precedence
- Clear all (AC), delete the last character (DEL), and calculate (=)
- Live result preview and error handling
- Dark user interface
- Unit tests for the calculation engine

### Getting Started
1. Install Android Studio and JDK 17.
2. Clone this repository and open it in Android Studio.
3. Run Gradle Sync.
4. Select an emulator or a physical device running Android 7.0 (API 24) or later.
5. Click **Run**.

### Build the APK
In Android Studio, select **Build > Build APK(s)**. The output file is located at `app/build/outputs/apk/debug/app-debug.apk`.

### Automated Builds with GitHub Actions
On every push or pull request targeting the `main` branch, the workflow runs the tests and builds a Debug APK. To download the APK, open a successful workflow run in the **Actions** tab and download the artifact named `android-calculator-debug`.

### Technology Stack
- Kotlin 2.0.21
- Jetpack Compose and Material 3
- Android Gradle Plugin 8.7.3
- Minimum SDK: 24 | Target SDK: 35