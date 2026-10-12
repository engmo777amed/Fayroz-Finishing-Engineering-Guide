package com.example.data

object Batch03References {
    private const val project = "مستندات المشروع: حساب التصرف والضغط والفواقد والأقطار؛ مخطط التغذية والصرف والتهوية؛ جداول الأجهزة والمناسيب؛ تفاصيل العزل والاختراقات؛ وإجراءات الاختبار والتطهير."
    private const val ppr = "GF Piping Systems، Aquasystem PP-R/PP-RCT؛ مرجع نطاق نظام وليس اعتمادًا للصنف المورّد: https://www.gfps.com/en-ca/products-solutions/systems/aquasystem.html"
    private const val pvc = "Charlotte Pipe، Plastics Technical Manual، ص10 و20–21؛ مثال PVC DWV غير مضغوط ووصل مذيب ومعالجة لصنف محدد، وليس مقاسًا أو كودًا مصريًا: https://www.charlottepipe.com/uploads/documents/technical/BR-PK.pdf"
    private const val frame = "Geberit Duofix؛ تعدد نماذج شاسيه القاعدة المعلقة ومتطلبات توافق وتعليمات تركيب كل موديل: https://www.geberitnorthamerica.com/sanitary-piping-systems/installation-flushing-systems/geberit-duofix/"
    private const val hammer = "Watts LF150A؛ مثال مانع مطرقة مائية يمتص صدمة الضغط، وليس علاجًا عامًا قبل تشخيص الضغط والسرعة والتثبيت: https://www.watts.com/products/plumbing-flow-control-solutions/plumbing-specialties/water-hammer-arrestors/lf150a"

    fun forCategory(categoryId: Int): List<String> =
        if (categoryId == 7) listOf(project, ppr, pvc, frame, hammer) else emptyList()
}
