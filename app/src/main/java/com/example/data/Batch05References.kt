package com.example.data

object Batch05References {
    private val electrical = listOf(
        "مستندات المشروع: مخطط النقاط والدوائر، جدول الأحمال والكابلات والقواطع، الأثاث والأجهزة، تفاصيل المسارات والعلب، ونموذج نتائج الاختبار.",
        "Schneider Electric Electrical Installation Guide؛ مرجع تصميم وتركيب وفق IEC وليس بديلًا عن مستندات المشروع أو الكود المصري: https://www.se.com/uk/en/work/products/product-launch/electrical-installation-guide/",
        "WAGO 221؛ مثال منتج محدد، ويجب مطابقة رقم الصنف لنوع الموصل ومقطعه وحدود تشغيله: https://www.wago.com/us/products/electrical-interconnect/discover-wire-and-splicing-connectors/221-series-levernuts"
    )
    private val panels = listOf(
        "مستندات المشروع: المخطط الأحادي، جدول الأحمال والدوائر، تيار القصر المتوقع، نظام التأريض، الانتقائية، وجدول ضبط واختبار أجهزة الحماية.",
        "Schneider Electric Electrical Installation Guide؛ مرجع تصميم وتركيب وفق IEC وليس اعتمادًا لكل لوحة أو بديلًا عن الكود المصري: https://www.se.com/uk/en/work/products/product-launch/electrical-installation-guide/",
        "Schneider RCBO/MCB Installation Guide؛ مثال توصيل لأجهزة محددة ويُرجع دائمًا إلى رقم الجهاز المركب: https://www.se.com/id/en/download/document/Installation_RCBO_MCB/"
    )
    private val lighting = listOf(
        "مستندات المشروع: مخطط الإنارة، جدول الوحدات والدرايفرات والتحكم، الحساب أو العينة الضوئية، تفاصيل التثبيت والتغذية والصيانة، ومشاهد التشغيل.",
        "MEAN WELL LED Driver Selection and Installation؛ مرجع مصنع لاختيار درايفر محدد وليس قاعدة عامة لكل ليد: https://led.meanwell.com/productManual.aspx",
        "Signify LED Downlight Dimming Compatibility؛ مثال قائمة توافق لمنتجات محددة ولا تثبت توافق أي ديمر مع أي درايفر: https://www.signify.com/api/assets/v1/file/Signify/content/LED-DIM-DL-Dimming-Compatibility-1/LED-Downlight-Dimming-Compatibility.pdf"
    )

    fun forCategory(categoryId: Int): List<String> = when (categoryId) {
        9 -> electrical
        10 -> panels
        11 -> lighting
        else -> emptyList()
    }
}
