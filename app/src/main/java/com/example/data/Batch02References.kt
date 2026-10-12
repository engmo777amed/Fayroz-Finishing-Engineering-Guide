package com.example.data

object Batch02References {
    private const val project = "المستند المطلوب: قطاع طبقات العزل والميول، الأحمال والمناسيب والفواصل والمصارف والفائض، وإجراء اختبار النظام. لا تُستبدل هذه المستندات برقم عام."
    private const val sheet = "Sika مصر، SikaShield P24 PE 4 mm، قسم MEMBRANE OVERLAPS؛ قيم الصنف فقط: https://egy.sika.com/en/construction/97111/bituminous-sheetmembranes/sikashield-p24-pe4mm.html"
    private const val cement = "Sika مصر، SikaTop Seal-107 EL، قسما الخلط والتطبيق؛ راجع الصنف وعبوة التوريد: https://egy.sika.com/en/construction/97111/waterproofing-mortars/sikatop-seal-107el.html"
    private const val wool = "ROCKWOOL، Insulation Slabs، خواص الخامة وحدود الاختيار؛ الأداء الصوتي والحريق يخص التجميعة: https://www.rockwool.com/uk/education-and-training/product-types/insulation-slabs/"
    private const val screed = "Sika المملكة المتحدة، SikaScreed-100، قسم Layer Thickness؛ يميز المرتبط والعائم في هذا النظام ولا يضع سمكًا عامًا لمصر: https://gbr.sika.com/en/construction/flooring/cementitious-systems/residential-selflevellingscreeds/sikascreed-100.html"
    fun forCategory(categoryId: Int) = when (categoryId) {
        4 -> listOf(project, sheet, cement)
        5 -> listOf(project, wool)
        else -> listOf(project, screed)
    }
}
