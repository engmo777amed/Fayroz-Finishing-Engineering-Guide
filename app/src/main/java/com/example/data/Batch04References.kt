package com.example.data

object Batch04References {
    private const val projectSanitary =
        "مستندات المشروع: جدول الأدوات الصحية، قوالب ومناسيب الأجهزة، تفاصيل التثبيت، التغذية والصرف والعزل، وإجراءات الاختبار."
    private const val projectHvac =
        "مستندات المشروع: جدول أجهزة التكييف والأحمال والموديلات، مسارات التبريد وصرف التكثيف والكهرباء والحوامل، وإجراءات الضغط والتفريغ والتشغيل."

    private val sanitary = listOf(
        projectSanitary,
        "Geberit Duofix Installation Manual؛ مثال نظام محدد وليس اعتمادًا عامًا لكل شاسيه: https://www.geberitnorthamerica.com/_assets/local-media/instructions/972-791-00-000.pdf",
        "GROHE Rapido SmartBox؛ عمق التركيب المذكور خاص بهذا النظام: https://www.grohe-mena.com/en_ae/for-your-bathroom/for-showers/concealed-solutions/",
        "hansgrohe ShowerSelect 15571700؛ حد الأمان الحراري خاص بالمنتج: https://www.hansgrohe.com/articledetail-showerselect-comfort-e-thermostat-for-concealed-installation-for-1-function-15571700"
    )
    private val hvac = listOf(
        projectHvac,
        "Daikin FTXTM-R/FTXTM-S Installer Reference Guide 4P728168-3 (2023)؛ مثال موديل محدد لصرف التكثيف: https://www.daikin.eu/content/dam/document-library/Installer-reference-guide/ac/split/ftxtm-s/FTXTM30R.FTXTM-S_Installer%20reference%20guide_4PEN728168-3_English.pdf",
        "Daikin FTXTM-R Installation Manual؛ مرجع موديل محدد للتركيب والتشغيل: https://www.daikin.eu/content/dam/document-library/installation-manuals/ac/split/ftxtm-r/FTXTM-R_Installation%20manual_3PEN697375-4_English.pdf"
    )

    fun forCategory(categoryId: Int): List<String> = when (categoryId) {
        8 -> sanitary
        13 -> hvac
        else -> emptyList()
    }
}
