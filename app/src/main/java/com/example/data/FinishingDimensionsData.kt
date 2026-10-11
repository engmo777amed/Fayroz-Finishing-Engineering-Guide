package com.example.data

import com.example.model.*

object FinishingDimensionsData {
    val dimensions = listOf(
        TechnicalDimension(
            id = "dim_sherb_level",
            categoryId = 1,
            itemTitle = "منسوب خط الشرب المعماري الثابت (Datum Level)",
            standardValue = "مرجع ثابت معتمد؛ يمكن اختيار +1.00 م فوق التشطيب لتسهيل القياس.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "يقاس رأسياً من ظهر بلاط التشطيب النهائي للوحدة، وينقل لجميع الغرف والأبواب",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "انقل المرجع الثابت المعتمد لجميع الغرف وسجل علاقته بمنسوب التشطيب؛ لا تغير المرجع بين البنود."
        ),
        TechnicalDimension(
            id = "dim_door_rough_opening",
            categoryId = 1,
            itemTitle = "خلوص وأبعاد فتحات الأبواب المعمارية في المباني (Door Rough Openings)",
            standardValue = "من جدول الباب: الفتحة الصافية وسماكة الحلق وخلوص التركيب.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "يقاس بين أكتاف الطوب المصمت الصافية، والارتفاع من ظهر الخرسانة المسلحة قبل الردم",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "الفتحة من المقاس الخارجي للحلق وخلوص تركيبه. راجع ارتفاعها من التشطيب النهائي مع حساب طبقات الأرضية، ولا تُفرض زيادة 10 سم لكل الأبواب."
        ),
        TechnicalDimension(
            id = "dim_lintel_bearing",
            categoryId = 1,
            itemTitle = "طول ركوب الأعتاب الخرسانية المسلحة فوق الفتحات (Lintel Bearing Length)",
            standardValue = "ركوب العتب ومقطعه وتسليحه من التصميم الإنشائي ونوع الحائط والفتحة.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "يعتمد على البحر والحمل ونوع القاطع ومقاومة المباني؛ لا يحدد تسليح موحد لكل الفتحات",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "طول المساند ومقطع وتسليح العتب من التصميم ونوع المباني؛ لا يُقبل أو يرفض برقم 15 أو 20 سم عام."
        ),
        TechnicalDimension(
            id = "dim_plaster_thickness",
            categoryId = 3,
            itemTitle = "سماكة بياض المحارة للحوائط والأسقف (Plaster Thickness)",
            standardValue = "من استواء الركيزة وحدود سمك الطبقة والنظام؛ تصلح فروق كبيرة قبل التشطيب.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "في حال زاد البياض عن 3 سم يلزم التدبيس بشبك فايبر أو سلك بقلاوة مجلفن ومسامير صلب",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "راجع حد سمك الطبقة والخلفية والربط والوزن خاصة بالسقف؛ الشبك لا يجيز زيادة السمك بلا تفصيلة."
        ),
        TechnicalDimension(
            id = "dim_boag_spacing",
            categoryId = 3,
            itemTitle = "تباعد البؤج والأوتار في المحارة واستواء القدة (Boag & Otas Spacing)",
            standardValue = "مسافات تسمح بعمل القدة وتراجع الاستواء والشاقولية وفق مواصفة الاستلام.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "لتسهيل مس المونة بالقدة الألومنيوم 2.5م بين الوترين دون ترييح بالمنتصف",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "راجع الشاقولية والاستواء والتعامد والأضلاع قبل مقارنة القطرين؛ لا تعتمد عبارة تفاوت محدد."
        ),
        TechnicalDimension(
            id = "dim_floor_drain_slope",
            categoryId = 6,
            itemTitle = "ميول صرف أرضيات الحمامات والأسطح (Drainage Slopes)",
            standardValue = "الميل = فرق المنسوب ÷ المسافة الأفقية × 100؛ الاتجاه تجاه المصرف وفق الموقع والتشطيب.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "باتجاه البيبة (الجاليتراب) في الحمامات، ونحو مزاريب الصرف في الأسطح والشرفات",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "قِس فرق المنسوب والمسافة واتجاه التصريف؛ اختبر بعد المعالجة ضمن إجراء السطح. لا تفرض مجال 1–2% لكل المواقع."
        ),
        TechnicalDimension(
            id = "dim_shower_mixer",
            categoryId = 7,
            itemTitle = "منسوب مخرج خلاط الدش والبانيو (Shower / Bath Mixer)",
            standardValue = "محاور ومناسيب الخلاط من قالب الجهاز والتشطيب النهائي وخلوص الفك.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "البارد على اليمين والساخن على اليسار دائماً واستواء أفقي تام",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "استخدم قالب موديل الخلاط واضبط محاوره وبروزه على وش التشطيب؛ مسافة 15 سم تخص الموديلات المطابقة لها."
        ),
        TechnicalDimension(
            id = "dim_washbasin_roughin",
            categoryId = 7,
            itemTitle = "مناسيب تغذية وصرف حوض الحمام (Wash Basin Rough-in)",
            standardValue = "مخارج الحوض من نموذج الحوض والخلاط والسيفون والخزانة.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "المسافة بين مخرجي تغذية البارد والساخن لحوض الحمام 10-12 سم",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "حوض سطح أو معلق أو داخل خزانة يحتاج تفصيلته الخاصة؛ راجع مكان السيفون وخلوص الأرفف والأدراج."
        ),
        TechnicalDimension(
            id = "dim_plumbing_drain_slopes",
            categoryId = 7,
            itemTitle = "ميول انحدار مواسير الصرف الصحي المعلقة والمدفونة (Drain Pipe Slopes)",
            standardValue = "قطر وميل الصرف من التصميم والحمل وطول الخط ونظام التهوية؛ لا يعمم ميل واحد لكل الأقطار.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "لمواسير الـ PVC الصرف الصحي بالانحدار الهيدروليكي الطبيعي",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "حدد الميل من القطر والتصرف والمسار والتهوية. افحص مناسيب الخط نفسه؛ لا تعتمد تعميم أن كل ميل أكبر من 3% يترك الفضلات."
        ),
        TechnicalDimension(
            id = "dim_wall_hung_wc_height",
            categoryId = 8,
            itemTitle = "ارتفاع قاعدة التواليت المعلقة وشاسيه الدفن (Wall-Hung WC Height)",
            standardValue = "من موديل الشاسيه والجهاز والتشطيب النهائي ومتطلبات المستخدم.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "لراحة الجلوس للبالغين، مع ضبط التيش الصلب للشاسيه قبل صب أرضية الحمام",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "راجع الجهاز والشاسيه والتشطيب النهائي واحتياجات المستخدم قبل تثبيت المسامير أو غلق الكسوة."
        ),
        TechnicalDimension(
            id = "dim_light_switch",
            categoryId = 9,
            itemTitle = "منسوب مفاتيح الإنارة العادية (Light Switches)",
            standardValue = "من المخطط والأثاث ومتطلبات الوصول.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "من منسوب أرضية التشطيب النهائي، على مسافة 15-20 سم من حافة فتحة الباب من جهة المقبض",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "راجع اتجاه فتح الباب وسهولة الوصول؛ لا تحدد الموضع من ارتفاع عام دون الأثاث والاستخدام."
        ),
        TechnicalDimension(
            id = "dim_power_socket",
            categoryId = 9,
            itemTitle = "منسوب برايز القوى والخدمات العادية (Power Sockets)",
            standardValue = "من الاستخدام والأثاث والمنطقة الكهربائية والحماية المطلوبة.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "من منسوب أرضية التشطيب النهائي في غرف النوم والصالات والممرات",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "راجع الأثاث الفعلي وطول المقابس والأسلاك وسهولة الاستخدام؛ لا يُفرض منسوب كمودينو واحد لكل غرفة."
        ),
        TechnicalDimension(
            id = "dim_kitchen_counter_socket",
            categoryId = 9,
            itemTitle = "منسوب برايز المطبخ فوق الرخام (Kitchen Counter Sockets)",
            standardValue = "من سطح الكاونتر والأجهزة وخلوص المقابس؛ يراجع البعد من الماء والموقد.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "فوق كاونتر المطبخ (ارتفاع الرخام 90 سم + 25 سم خلوص للأجهزة)",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "راجع مناطق البلل والحرارة والحماية المطلوبة بالمخطط؛ مسافة أفقية واحدة لا تثبت صلاحية وضع البريزة."
        ),
        TechnicalDimension(
            id = "dim_wire_cross_sections",
            categoryId = 9,
            itemTitle = "مقاطع أسلاك الدوائر الكهربائية طبقاً للأحمال (Wire Cross-Sections)",
            standardValue = "من الحمل والطول وهبوط الجهد وطريقة التركيب والحرارة وتنسيق القاطع.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "نحاس نقي معتمد مع قواطع حماية متناسبة (10A للإنارة، 16A للبرايز، 20-32A للأحمال الثقيلة)",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "موصل الحماية يختار مع الدائرة وطريقة حسابه وتحمل تيار العطل والحماية الميكانيكية؛ لا يعمم 2.5–4 مم² لكل حمل."
        ),
        TechnicalDimension(
            id = "dim_ac_roughin_heights",
            categoryId = 13,
            itemTitle = "مناسيب تأسيس صرف وفريون التكييف الاسبليت (Split AC Rough-in)",
            standardValue = "من دليل التكييف ومساحات سحب وطرد الهواء وصيانة الفلتر وميول التكثيف.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "مع مراعاة ترك مسافة لا تقل عن 15 سم خلوص حر بين أعلى التكييف وسقف الجبس بورد لسحب الهواء",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "راجع قالب الوحدة وإمكان انحدار الخرطوم وصيانتها؛ لا تعمم فرقًا ثابتًا بين مخرج الصرف وخطوط التبريد."
        ),
        TechnicalDimension(
            id = "dim_floor_package_thickness",
            categoryId = 15,
            itemTitle = "سماكة فرشة أرضيات السيراميك والبورسلين (Floor Finish Package)",
            standardValue = "مجموع طبقات الأرضية الفعلية؛ يراجع عند العتبات والمصارف والسلالم.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "تقاس من ظهر الخرسانة المسلحة حتى سطح بلاط التشطيب النهائي",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "احسب سمك ووزن طبقات الأرضية الفعلية وتحقق من قدرة تحملها؛ اختيار الردم الخفيف لا يعتمد على تجاوز 15 سم وحده."
        ),
        TechnicalDimension(
            id = "dim_tile_expansion_joints",
            categoryId = 15,
            itemTitle = "عروض فواصل بلاط البورسلين وفواصل التمدد المحيطية (Tile Joints & Expansion Spacing)",
            standardValue = "من نوع ومقاس البلاط والتعرض والنظام؛ لا تلغى فواصل الحركة أو تملأ بروبة صلبة.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "للبورسلين المقصوص ليزر والأرضيات الكبيرة بمناخ مصر الحار",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "عراميس الروبة ليست فواصل الحركة؛ نفذ توزيع وعرض وحشو كل نوع حسب البلاط والموقع والنظام."
        ),
        TechnicalDimension(
            id = "dim_gypsum_grid_spacing",
            categoryId = 19,
            itemTitle = "مسافات تباعد شاسيهات الجبس بورد (Suspended Ceiling Grid Spacing)",
            standardValue = "من نظام السقف والأحمال وعدد وسمك الألواح؛ الخدمات الثقيلة تحمل مستقلاً.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "لألواح جبس بورد سمك 12.5 مم مع قطاعات صاج مجلفن سمك 0.55-0.60 مم",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "الأحمال والبحور وعدد الألواح ونظام التعليق تحدد التباعد؛ مقاومة الرطوبة لا تتحقق بتقليل المسافة وحده."
        ),
        TechnicalDimension(
            id = "dim_curtain_pelmet_box",
            categoryId = 19,
            itemTitle = "أبعاد بيت الستارة المدمج في الجبس بورد (Recessed Curtain Pelmet Box)",
            standardValue = "من المسار وطبقات القماش والموتور ومساحة الصيانة.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "أمام جميع نوافذ وتراسات غرف النوم والاستقبال المجهزة بستائر قماشية",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "راجع عدد المسارات وطيات القماش وبروز الشتر والموتور وإمكان الصيانة بعينة قطاع فعلية."
        ),
        TechnicalDimension(
            id = "dim_kitchen_cabinet_heights",
            categoryId = 24,
            itemTitle = "المقاسات والمناسيب القياسية لوحدات المطبخ (Kitchen Ergonomic Dimensions)",
            standardValue = "من احتياجات المستخدم ومقاسات الأجهزة وخلوص الأدراج والتهوية والصيانة.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "لراحة حركة الذراعين أثناء الطهي وغسيل الأواني طبقاً لمعايير الأرجونوميكس",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "ارتفاع الشفاط وخلوص الموقد والتهوية من دليلي الجهازين ونوع الوقود؛ لا تعتمد ارتفاعًا عامًا للمطابخ."
        )
    )
}
