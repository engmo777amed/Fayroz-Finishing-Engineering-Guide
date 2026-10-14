package com.example.data

object Batch06References {
    private val lowCurrent = listOf(
        "مستندات المشروع: مخطط النقاط والمسارات والراك والتغذية، جدول المنافذ والأجهزة، متطلبات الأداء وPoE والتخزين، وخطة الاختبار والتسليم.",
        "Fluke Networks؛ يفرق بين Verification وQualification وCertification، ولا يجعل نجاح Wiremap اعتمادًا لأداء الوصلة: https://www.flukenetworks.com/knowledge-base/cableiq/verification-qualification-and-certification",
        "ONVIF؛ عند اشتراط التكامل يُراجع ملف التعريف وقائمة المنتجات المتوافقة بأرقامها، ولا يكفي وجود الشعار: https://www.onvif.org/conformant-products/"
    )

    private val fire = listOf(
        "مستندات المشروع والجهة المختصة: مخطط الحريق والحسابات، جداول الأجهزة، مصفوفة السبب والنتيجة، خطة الاختبار، واعتمادات النظام المنفذ.",
        "Tyco؛ تعليمات وتحذيرات الرشاش تخص رقم الرأس وتركيبه ولا تعطي توزيعًا عامًا لكل الحالات: https://docs.johnsoncontrols.com/tycofire/v/u/Tyco/en-US/Sprinkler-Spray-Patterns/01-2026",
        "Honeywell Morley LT؛ مثال لوحة محددة يطلب اتباع اللوائح المحلية ووثائق أجهزة المجال المتوافقة: https://buildings.honeywell.com/content/dam/hbtbt/ae/documents/downloads/M-168.1-SERIE-LT-EN%20Version%202024_KSA.pdf",
        "Hilti Firestop؛ اختيار النظام وتوثيقه يرتبطان بنوع الحاجز والاختراق والفتحة الفعلية: https://www.hilti.com/content/landing-page/local/firestop-resource-center"
    )

    fun forCategory(categoryId: Int): List<String> = when (categoryId) {
        12 -> lowCurrent
        14 -> fire
        else -> emptyList()
    }
}
