package com.example.data

object Batch11References {
    private val facades = listOf(
        "مستندات المشروع: واجهات وقطاعات وتفاصيل الفتحات والحواف والفواصل والتصريف، الركيزة والعزل، أحمال الرياح ونظام التثبيت، متطلبات الحريق، عينات النظام وخطة الاختبار والصيانة.",
        "Jotun Egypt — Exterior products؛ بوابة مصنع لمنتجات دهان خارجية محددة، ولا تثبت أن كل جرافيتو أو دهان يمنع الماء أو يصلح لكل ركيزة: https://www.jotun.com/eg-en/decorative/products/exterior",
        "Natural Stone Institute — Design Manual library؛ مرجع مهني لتصميم وتنفيذ الحجر الطبيعي، ولا يحدد وحده سمك الحجر أو الأنكر أو حمل الرياح لمشروع بعينه: https://pubs.naturalstoneinstitute.org/resources/library/?cat1=1&event=getAdvancedSearch&gosearch=1&mode=advancedSearch",
        "ALUCOBOND — Fire safety؛ بيانات الحريق تخص نوع اللوح والتجميع والتصنيف الموثق، ولا يجوز تحويل اسم كلادينج أو لون القلب إلى اعتماد حريق: https://www.alucobond.com/en/products/fire-safety/",
        "Sto — StoTherm ci installation guide؛ مثال مصنع لنظام عزل واجهات متكامل، ولا تعمم مكوناته أو تفاصيل تثبيته أو تشطيبه على خلط منتجات من أنظمة مختلفة: https://www.stocorp.com/sdmt_documents/stotherm-ci-eifs-install-guide/"
    )

    private val metals = listOf(
        "مستندات المشروع: تصميم الحاجز أو العنصر المعدني وأحماله وارتفاعه وفتحاته، قطاعات المعدن ودرجته، تكوين زجاج الأمان وحالته بعد الكسر، الركيزة والحواف، الأنكر واللحام والحماية وخطة اختبار الحمل والصيانة.",
        "BSSA — General principles for selection of stainless steels؛ اختيار درجة الاستانلس يتبع بيئة التعرض والتصنيع والصيانة، ولا يعني اسم استانلس أو رقم 304 وحده ملاءمة كل بيئة: https://bssa.org.uk/bssa_articles/general-principles-for-selection-of-stainless-steels/",
        "BSSA — Magnetic properties of stainless steel؛ الانجذاب للمغناطيس يتأثر بالبنية والتشغيل، لذلك لا يثبت اختبار المغناطيس وحده درجة 304 أو 201: https://bssa.org.uk/bssa_articles/magnetic-properties-of-stainless-steel/",
        "Hilti — Anchor installation؛ تنظيف الثقب وخطوات التركيب تتبع نظام الأنكر وأداة الحفر وتعليمات المنتج، ولا يثبت المرجع قطرًا أو عمقًا أو عزمًا عامًا للدرابزين: https://www.hilti.com/content/hilti/W1/US/en/business/business/productivity/anchor-installation.html"
    )

    fun forCategory(categoryId: Int): List<String> = when (categoryId) {
        23 -> facades
        26 -> metals
        else -> emptyList()
    }
}
