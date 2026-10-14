package com.example.data

object Batch09References {
    private val doors = listOf(
        "مستندات المشروع: جدول الأبواب والفتحات واتجاهات الفتح، نوع كل ضلفة وحلق ووزنها وأداؤها، الخردوات والمثبتات والركائز، منسوب الأرضية، عينات التشطيب وخطة الاختبار والصيانة.",
        "ASSA ABLOY — Fire Door System Guide، إصدار 03/2026؛ مرجع مصنع يوضح أن باب الحريق تجميعة تشمل الضلفة والحلق والخردوات والغالق والجوانات، ولا يثبت اعتماد باب أو خلوص لمشروع آخر: https://www.assaabloy.com/ae/en/documents/brochures/fire-door/AA_ME%20Mech_Core_Door_Closers_Fire_Door_System_Brochure_WEB.pdf",
        "Häfele — Slido installation instructions؛ مثال رسمي لمسار باب جرار محدد، وتؤخذ الحمولة والمثبتات والخلوصات من رقم الطقم ولا تعمم على كل باب جرار: https://www.hafele.com/INTERSHOP/static/WFS/Haefele-HAC-Site/-/Haefele/en_US/pim/documents/ins-doc-04701153.pdf"
    )

    private val cabinetry = listOf(
        "مستندات المشروع: مخطط الوحدات والواجهات والقطاعات، جدول الألواح والحواف والخردوات، أكواد الأجهزة وقوالبها، مواقع الماء والصرف والكهرباء والتهوية، تفصيلة الكاونتر والحوض والدعامات وخطة التشغيل والصيانة.",
        "Blum — E-SERVICES وEASY ASSEMBLY؛ التخريم والخلوص والضبط والحمولة تؤخذ من رقم المفصلة أو السكة، ولا يعمم اسم Blum على كل قطعة أو مقاس: https://www.blum.com/us/en/services/e-services/",
        "Cosentino — Technical Documentation؛ أدلة تصميم وتركيب أسطح Silestone وDekton تخص المادة والدليل المختارين، ولا تعمم السماكات والبروزات والفتحات على الجرانيت أو البورسلين أو كل الأسطح: https://www.cosentino.com/professional/technical-documentation/"
    )

    fun forCategory(categoryId: Int): List<String> = when (categoryId) {
        21 -> doors
        24 -> cabinetry
        else -> emptyList()
    }
}
