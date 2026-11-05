# ماشین‌حساب اندروید

ماشین‌حساب اندرویدی با Kotlin و Jetpack Compose.

## امکانات
- جمع، تفریق، ضرب و تقسیم
- درصد، اعشار، پرانتز و اعداد منفی
- رعایت تقدم استاندارد عملگرها
- پاک‌کردن کامل (AC)، حذف آخرین نویسه (DEL) و محاسبه (=)
- نمایش نتیجه زنده و مدیریت خطاها
- رابط کاربری تیره
- تست‌های واحد برای موتور محاسبات

## اجرای پروژه
1. Android Studio و JDK 17 را نصب کنید.
2. مخزن را Clone و در Android Studio باز کنید.
3. Gradle Sync را اجرا کنید.
4. شبیه‌ساز یا گوشی با Android 7.0 (API 24) یا بالاتر را انتخاب کنید.
5. Run را بزنید.

## ساخت APK
از منوی Build گزینه Build APK(s) را انتخاب کنید. خروجی در مسیر `app/build/outputs/apk/debug/app-debug.apk` قرار می‌گیرد.

## ساخت خودکار با GitHub Actions
با هر Push یا Pull Request به شاخه main، تست‌ها اجرا و APK نسخه Debug ساخته می‌شود. برای دریافت APK، اجرای موفق را در بخش Actions باز و Artifact با نام `android-calculator-debug` را دانلود کنید.

## فناوری‌ها
- Kotlin 2.0.21
- Jetpack Compose و Material 3
- Android Gradle Plugin 8.7.3
- Minimum SDK: 24 | Target SDK: 35

> توجه: پروژه هنوز روی دستگاه واقعی آزمایش نشده است. خروجی APK پس از اجرای موفق workflow قابل دریافت خواهد بود.
