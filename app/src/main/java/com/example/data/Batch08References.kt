package com.example.data

object Batch08References {
    private val paint = listOf(
        "مستندات المشروع: جدول التشطيبات والألوان واللمعات، تعريف الركائز، معالجة الرطوبة والشروخ، العينة المعتمدة، نشرات أرقام الأصناف وخطة الفحص.",
        "Jotun Egypt — Fenomastic Emulsion Primer؛ مثال برايمر محدد تشترط نشرته سطحًا متماسكًا ونظيفًا وجافًا، وتظل التغطية والتخفيف والجفاف خاصة بهذا الصنف والظروف ولا تعمم على كل دهان: https://www.jotun.com/eg-en/products-and-services/products/fenomastic-emulsion-primer"
    )

    private val gypsum = listOf(
        "مستندات المشروع: مخطط السقف والقواطيع والقطاعات والمناسيب، جدول النظام والطبقات والأداء، الأحمال والتدعيمات، تنسيق الخدمات وفتحات الصيانة وخطة الفحص قبل الغلق.",
        "Knauf Egypt — Download Center؛ أدلة نظم الأسقف والقواطيع والألواح والفواصل، وتؤخذ المسافات والسماكات والمثبتات من رقم النظام وإصدار وثيقته، ولا يعمم دليل نظام على نظام آخر: https://knauf.com/en-EG/tools/download-center",
        "Armstrong Ceiling Solutions — Installation & Painting FAQs؛ مرجع مصنع للأسقف المعلقة يوضح أن أداء الحريق يخص التجميعة لا المكون المنفرد، ولا يعمم على نظام جبس أو شبكة أخرى: https://www.armstrongceilings.com/commercial/en/technical-downloads/ceiling-installation-painting-faqs.html"
    )

    private val decor = listOf(
        "مستندات المشروع: مخطط الحليات والبانوهات، الخامة والوزن والركيزة، زوايا الموقع، عينات الركن والوصلات والدهان، تفاصيل الحمل والإنارة والصيانة.",
        "Orac Decor — تعليمات تركيب الكرانيش؛ مثال مصنع لنظام حليات ولاصق ووصلات محدد، ولا تعمم مواده أو زمن معالجته أو طريقة قصه على الجبس والخشب وكل البوليمرات: https://www.oracdecor.com/media/wysiwyg/assets/Installation/UK-C-MP036-010_Inst_instr_C_A4pages_2306_EN-UK.pdf"
    )

    fun forCategory(categoryId: Int): List<String> = when (categoryId) {
        18 -> paint
        19 -> gypsum
        20 -> decor
        else -> emptyList()
    }
}
