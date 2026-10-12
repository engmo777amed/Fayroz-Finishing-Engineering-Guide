package com.example.data

/** Reviewed sources and required project documents; foreign product examples are not Egyptian code limits. */
object Batch01References {
    const val inspectedOn = "12/10/2026"
    val masonry = listOf(
        "المستند المطلوب للتنفيذ: مخطط القواطيع والفتحات وتفاصيل الروابط والأعتاب والفواصل وحدود الاستلام المعتمدة للمشروع.",
        "Leviat/Ancon، Head Restraints، قسم IHR. يوضح ربطًا يسمح بالحركة؛ المقاسات تخص منتجه: https://www.ancon.co.uk/products/wall-ties-restraint-fixings/other-restraint-fixings/head-restraints"
    )
    val plaster = listOf(
        "Weber مصر، DRY MORTAR 300، نشرة04.24، ص1–2. بيانات الخلط والسمك تخص هذا الصنف: https://www.middleeast.weber/files/sodamco/2024-05/TDS_DRY_MORTAR_300-Egypt.pdf",
        "Sika مصر، SikaEmaco SBR 2، قسم التطبيق. طبقة الربط الرطبة تخص هذه المنظومة: https://egy.sika.com/en/construction/concrete-repair/admixtures-for-site-batchmortars/sikaemaco-sbr-2.html",
        "British Gypsum، Plaster systems، تحديث22/05/2025، ص6–8. تجهيز الخلفية والرطوبة والطبقات؛ تفاصيل Thistle ليست وصفة لكل محارة: https://www.british-gypsum.com/documents/white-book/british-gypsum-wb-plaster-systems.pdf"
    )
    fun forCategory(categoryId: Int) = if (categoryId == 1) masonry else plaster
}
