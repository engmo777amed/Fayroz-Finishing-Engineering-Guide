package com.example.data

object Batch10References {
    private val windows = listOf(
        "مستندات المشروع: جدول الشبابيك والأبواب، مقاسات الفتحات ومناسيبها، أحمال الرياح والأداء المطلوب للماء والهواء والحرارة والصوت، تكوين زجاج الأمان، قطاعات النظام والمثبتات والجوانات وبلوكات الارتكاز، تفاصيل العتبات والتصريف والعزل وخطة الاختبار.",
        "Pilkington — Glass glossary and safety-glass guidance؛ مرجع مصنع يشرح الفرق بين الزجاج المقسى والمصفح ووحدات الزجاج، ولا يحدد سمكًا أو تكوينًا صالحًا لكل فتحة: https://www.pilkington.com/en/gbl/knowledge-hub-selection/architectural-and-technical-glass-knowledge-hub/glassary",
        "Dow — DOWSIL construction submittal resources؛ بيانات سيلانت الطقس تخص رقم المنتج والركائز والتفصيلة المختارة، ولا تحول سيلانت الطقس إلى لاصق زجاج إنشائي: https://www.dow.com/en-us/market/mkt-building-construction/submittal-resources.html"
    )

    private val shutters = listOf(
        "مستندات المشروع: نوع الشتر أو الستارة ومساحتها ووزنها، جدول المحور والدلايل والصندوق، طريقة التشغيل، رقم الموتور والعزم ودورة التشغيل، التغذية والتحكم والحماية، فتحة الصيانة وإجراء الطوارئ والتشغيل والتسليم.",
        "Somfy Egypt — مركز تنزيل أدلة المنتجات؛ العزم والتوصيل وضبط النهايات ودورة التشغيل تؤخذ من رقم الموتور أو الكنترول، ولا تعمم علامة Somfy على كل موتور أو شتر: https://www.somfy.com.eg/en-eg/assistance/downloads",
        "Somfy — Roller shutter motor replacement kit instructions؛ مثال رسمي لطقم محدد يوضح ضرورة اتباع مكونات وضبط الموديل، ولا يثبت مقاس صندوق أو محور أو موتور لمشروع آخر: https://service.somfy.com/downloads/fr_v5/noticekit_remplacement_vr1.pdf"
    )

    fun forCategory(categoryId: Int): List<String> = when (categoryId) {
        22 -> windows
        25 -> shutters
        else -> emptyList()
    }
}
