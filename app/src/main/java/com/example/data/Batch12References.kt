package com.example.data

object Batch12References {
    private val adhesivesAndJoints = listOf(
        "مستندات المشروع: جدول الفواصل ووظيفتها وحركتها وعرضها وعمقها، الخامتان والتعرض، تفصيلة الاستمرار خلال طبقات التشطيب، الصنف والبرايمر وحبل الحشو، عينة التوافق وخطة الاختبار والصيانة.",
        "Sika — Sealant Training Fundamentals؛ يوضح أن حبل الحشو يضبط العمق ويمنع الالتصاق بثلاثة أوجه، لكن نسبة أو مقاس الرسم تخص التفصيلة والمنتج ولا تعمم على كل فاصل: https://usa.sika.com/dam/dms/us01/2/pres-cpd-SealantTrainingFundamentals-us.pdf",
        "Sika Egypt — Sikaflex Floor؛ بيانات منتج محدد لتصميم فواصل أرضيات، ولا توثق كل سيليكون أو لاصق أو فاصل بالمشروع: https://egy.sika.com/en/construction/joint-sealing/floor-joints/sikaflex-floor.html",
        "Soudal — Natural stone sealing؛ اختيار مادة لا تسبب تلطيخ الحجر يتطلب منتجًا مخصصًا واختبار توافق، ولا يعني أن كل سيليكون محايد مناسب للحجر: https://www.soudal.co.uk/pro/applications/sealing-joints/natural-stone-sealing",
        "Dow — DOWSIL 817 Mirror Adhesive؛ مثال مصنع للاصق مرايا داخلي محدد، ولا يحول أي سيليكون إلى لاصق مرايا أو يحدد دعم كل مرآة: https://www.dow.com/en-us/pdp.dowsil-817-mirror-adhesive.03281914h.html"
    )

    private val finalTrims = listOf(
        "مستندات المشروع: جدول الزجاج وتكوين زجاج الأمان وحوافه وثقوبه، وزن الضلفة والمفصلات والدعامات والركيزة والمثبتات، تفاصيل العزل والميل والجوان والختم، مخطط المرايا والكهرباء والخدمات المخفية، وعينات واختبارات التشغيل والماء.",
        "Pilkington — Toughened Glass؛ الزجاج المقسى يُقص ويُثقب وتُشطب حوافه قبل التقسية، ولا يحدد المرجع نوع الزجاج أو سمكه أو مقاسه لمشروع بعينه: https://www.pilkington.com/en-gb/united-kingdom/architectural-and-technical-glass/product-categories/safety-and-security/pilkington-toughened-glass",
        "CRL — Geneva 045 shower hinge؛ مثال مفصلة محددة تقبل نطاق زجاج معلنًا من مصنعها، ولا يثبت أن 10 مم أو عددًا واحدًا من المفصلات يصلح لكل ضلفة: https://www.crlaurence.com/All-Products/Shower-Hardware/Shower-Door-Hinges/Glass-to-Glass-Hinges/Glass-To-Glass/Geneva-Hinges---Glass-To-Glass/CRL-Brass-Geneva-045-Series-135-Degree-Glass-to-Glass-Hinge/p/GEN045BR",
        "Dow — DOWSIL 817 Mirror Adhesive؛ بيانات لاصق مرايا محدد للاستخدام الداخلي، ولا تغني عن اعتماد ظهر المرآة والخلفية ونمط التطبيق والدعم المطلوب: https://www.dow.com/en-us/pdp.dowsil-817-mirror-adhesive.03281914h.html",
        "BSSA — Magnetic properties of stainless steel؛ الاستجابة للمغناطيس تتأثر بالبنية والتشغيل، فلا تصلح وحدها لإثبات درجة 304 أو مقاومة التآكل: https://bssa.org.uk/bssa_articles/magnetic-properties-of-stainless-steel/"
    )

    fun forCategory(categoryId: Int): List<String> = when (categoryId) {
        27 -> adhesivesAndJoints
        28 -> finalTrims
        else -> emptyList()
    }
}
