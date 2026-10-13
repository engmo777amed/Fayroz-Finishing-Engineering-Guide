package com.example.data

object Batch07References {
    private val tiles = listOf(
        "مستندات المشروع: جدول التشطيبات، مخطط الرص، المناسيب والميول والمصارف، نوع البلاط والخلفية والعزل، فواصل الحركة، وحدود الاستلام.",
        "Sika مصر — SikaCeram-30؛ بيانات الخلط والسماكة والزمن والتجهيز تخص هذا الصنف وظروف نشرته ولا تعمم على كل لاصق: https://egy.sika.com/en/construction/tiling/sikaceram-30.html",
        "MAPEI — Manual for Installing Large Format Ceramic Tiles؛ مرجع مصنع لتجهيز الخلفية والمناولة والتغطية والفواصل، وليس كودًا مصريًا أو وصفة لكل بلاط: https://cdnmedia.mapei.com/docs/librariesprovider21/line-technical-documentation-documents/manual-for-installing-large-format-ceramic-tileslow.pdf"
    )

    private val stone = listOf(
        "مستندات المشروع: جدول الحجر والدفعات، Shop Drawings للقص والعروق والفواصل، تصميم الحوامل والأنكر، تفاصيل الفتحات والبروزات، عينات المواد وخطة الفحص.",
        "Natural Stone Institute — Dimension Stone Design Manual 2024؛ مرجع صناعي لأنظمة الحجر الطبيعي الأفقية والرأسية والكاونترات والاختبارات، ولا يغني عن تصميم مثبتات المشروع: https://pubs.naturalstoneinstitute.org/resources/library/?cat1=1&event=getAdvancedSearch&gosearch=1&mode=advancedSearch"
    )

    private val floors = listOf(
        "مستندات المشروع: جدول الأرضيات، نوع المنتج ورقم الموديل، الركيزة واختبار الرطوبة، التدفئة الأرضية إن وجدت، الطبقة السفلية واللاصق، الانتقالات والحماية وخطة التسليم.",
        "Quick-Step — Laminate Installation؛ مثال مصنع لنظام لامينيت كليك محدد، وحدوده وطبقته السفلية لا تعمم على HDF أو SPC أو باركيه من مورد آخر: https://int.quick-step.com/en/laminate/installation",
        "Interface — LVT Installation Instructions، Rev. 06/01/2025؛ مثال مصنع لفينيل LVT محدد يربط القبول باختبار الركيزة والرطوبة واللاصق والحماية: https://www.interface.com/content/dam/interfaceinc/interface/technical/ams/installation-instructions/wc_am-lvtinstallinstructions-ams-11-2020.pdf"
    )

    fun forCategory(categoryId: Int): List<String> = when (categoryId) {
        15 -> tiles
        16 -> stone
        17 -> floors
        else -> emptyList()
    }
}
