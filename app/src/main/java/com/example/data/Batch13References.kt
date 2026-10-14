package com.example.data

object Batch13References {
    private val quality = listOf(
        "مستندات المشروع: خطة الفحص والاختبار ITP، نقاط التوقف، الرسومات والمواصفات المعتمدة، إجراءات كل اختبار وحدوده ونماذج النتائج، حالة معايرة الأجهزة، وسجل عدم المطابقة والإصلاح وإعادة الاختبار.",
        "Fluke Networks — Verification, Qualification and Certification؛ يوضح أن نوع الاختبار يحدد السؤال الذي تجيب عنه النتيجة، لذلك لا تُقبل قراءة أو صورة بدل الاختبار المطلوب: https://www.flukenetworks.com/knowledge-base/cableiq/verification-qualification-and-certification",
        "Schneider Electric — Electrical Installation Guide؛ مرجع مصنع لتخطيط وفحص التركيبات الكهربائية وفق نطاقه، ولا يحدد وحده جهد اختبار أو حد قبول لكل مشروع أو جهاز: https://www.se.com/ww/en/work/products/product-launch/electrical-installation-guide/",
        "ASHRAE Standard 202؛ يعرّف عملية التكليف وتوثيق المتطلبات والتحقق والتسليم، ولا يحول التشغيل التجريبي إلى قبول لكل نظام دون إجراء المشروع: https://www.ashrae.org/technical-resources/bookstore/commissioning"
    )

    private val handover = listOf(
        "مستندات المشروع والعقد: قائمة الملاحظات ومسؤوليات الإغلاق، حدود القبول، محاضر إعادة الاختبار، رسومات المنفذ، سجل الأصول، أدلة التشغيل والصيانة، الضمانات، التدريب، المفاتيح والصلاحيات، وقطع الغيار المتفق عليها.",
        "ASHRAE Standard 202؛ يربط التسليم بسجل الأنظمة والمستندات والتدريب والتحقق، لكنه لا يحدد مدة ضمان أو قائمة قطع غيار موحدة: https://www.ashrae.org/technical-resources/bookstore/commissioning",
        "Fluke Networks — Verification, Qualification and Certification؛ مثال على أن تقرير اختبار قابل للتتبع يختلف عن مجرد تشغيل ظاهري، ولا يوثق بقية بنود التسليم: https://www.flukenetworks.com/knowledge-base/cableiq/verification-qualification-and-certification"
    )

    fun forCategory(categoryId: Int): List<String> = when (categoryId) {
        29 -> quality
        30 -> handover
        else -> emptyList()
    }
}
