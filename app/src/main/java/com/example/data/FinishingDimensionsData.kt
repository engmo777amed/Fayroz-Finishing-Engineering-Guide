package com.example.data

import com.example.model.DimensionRefType
import com.example.model.TechnicalDimension

object FinishingDimensionsData {
    val dimensions: List<TechnicalDimension> = listOf(
        // ==========================================
        // Category 1: أعمال المباني والشرب
        // ==========================================
        TechnicalDimension(
            id = "dim_sherb_level",
            categoryId = 1,
            itemTitle = "منسوب خط الشرب المعماري الثابت (Datum Level)",
            standardValue = "+1.00 متر (100 سم)",
            tolerance = "± 1 مم بميزان الليزر والخرطوم",
            applicationCondition = "يقاس رأسياً من ظهر بلاط التشطيب النهائي للوحدة، وينقل لجميع الغرف والأبواب",
            referenceType = DimensionRefType.EGYPTIAN_CODE,
            referenceSource = "الكود المصري للبناء ECP 203 - أصول المساحة الميدانية",
            practicalNotes = "المرجع الأساسي لضبط جميع مناسيب الأبواب والشبابيك والكهرباء والسباكة والأسقف المعلقة؛ أي خطأ في الشرب يخرب مناسيب الشقة بالكامل."
        ),
        TechnicalDimension(
            id = "dim_door_rough_opening",
            categoryId = 1,
            itemTitle = "خلوص وأبعاد فتحات الأبواب المعمارية في المباني (Door Rough Openings)",
            standardValue = "باب الشقة: 105-110 سم | الغرف: 90-95 سم | الحمامات والمطابخ: 80-85 سم (ارتفاع 215 سم من الخرسانة)",
            tolerance = "± 1.5 سم",
            applicationCondition = "يقاس بين أكتاف الطوب المصمت الصافية، والارتفاع من ظهر الخرسانة المسلحة قبل الردم",
            referenceType = DimensionRefType.EGYPTIAN_CODE,
            referenceSource = "الكود المصري للمباني ECP 204 والتصميم المعماري",
            practicalNotes = "عرض الفتحة بالمباني = عرض ضلفة الباب المعماري الصافي + 10 سم (لحساب سمك قائمي الحلق الزفر 5 سم + 5 سم للمحارة والفوم والخلوص)."
        ),
        TechnicalDimension(
            id = "dim_lintel_bearing",
            categoryId = 1,
            itemTitle = "طول ركوب الأعتاب الخرسانية المسلحة فوق الفتحات (Lintel Bearing Length)",
            standardValue = "20 سم إلى 25 سم من كل جانب على أكتاف المباني الحاملة",
            tolerance = "الحد الأدنى 20 سم صافي",
            applicationCondition = "فوق جميع فتحات الأبواب والشبابيك مع تسليح 2 إلى 3 أسياخ قطر 10 أو 12 مم",
            referenceType = DimensionRefType.EGYPTIAN_CODE,
            referenceSource = "الكود المصري للبناء ECP 204",
            practicalNotes = "ركوب العتب أقل من 15 سم يسبب شروخاً قطرية مائلة عند زوايا الفتحات بسبب تركيز الإجهادات فوق الطوب."
        ),

        // ==========================================
        // Category 3: المحارة والبياض
        // ==========================================
        TechnicalDimension(
            id = "dim_plaster_thickness",
            categoryId = 3,
            itemTitle = "سماكة بياض المحارة للحوائط والأسقف (Plaster Thickness)",
            standardValue = "الحوائط: 1.5 سم إلى 2.5 سم | الأسقف: 1.0 سم إلى 1.5 سم",
            tolerance = "لا يقل عن 1.5 سم على الحوائط ولا يزيد عن 3.0 سم في الوجه الواحد",
            applicationCondition = "في حال زاد البياض عن 3 سم يلزم التدبيس بشبك فايبر أو سلك بقلاوة مجلفن ومسامير صلب",
            referenceType = DimensionRefType.EGYPTIAN_CODE,
            referenceSource = "الكود المصري لأعمال البياض الداخلي والخارجي ECP 203",
            practicalNotes = "السقف الخرساني لا يسمح بزيادة سمك البياض فيه عن 1.5 سم نهائياً لتفادي خطر سقوط كتل البياض بالوزن الذاتي."
        ),
        TechnicalDimension(
            id = "dim_boag_spacing",
            categoryId = 3,
            itemTitle = "تباعد البؤج والأوتار في المحارة واستواء القدة (Boag & Otas Spacing)",
            standardValue = "تباعد البؤج: 1.5 م إلى 2.0 م أفقياً | استواء القدة: تفاوت لا يزيد عن 2 مم على قدة 3 أمتار",
            tolerance = "تفاوت استواء المحارة أقصاه 2 مم تحت قدة 3م",
            applicationCondition = "لتسهيل مس المونة بالقدة الألومنيوم 2.5م بين الوترين دون ترييح بالمنتصف",
            referenceType = DimensionRefType.EGYPTIAN_CODE,
            referenceSource = "الكود المصري للبياض ECP 203",
            practicalNotes = "تربيع الغرف بالبؤج (الأكسات والأوتار) يتم بتطابق وترين متساويين في الغرفة بنسبة خطأ صفر."
        ),

        // ==========================================
        // Category 6: ميول الأرضيات والصرف
        // ==========================================
        TechnicalDimension(
            id = "dim_floor_drain_slope",
            categoryId = 6,
            itemTitle = "ميول صرف أرضيات الحمامات والأسطح (Drainage Slopes)",
            standardValue = "1.0 سم إلى 1.5 سم لكل 1 متر طولي (نسبة 1% إلى 1.5%)",
            tolerance = "± 0.2 سم/م",
            applicationCondition = "باتجاه البيبة (الجاليتراب) في الحمامات، ونحو مزاريب الصرف في الأسطح والشرفات",
            referenceType = DimensionRefType.EGYPTIAN_CODE,
            referenceSource = "الكود المصري للأعمال الصحية ECP 102 وكود التشطيبات ECP 203",
            practicalNotes = "الميول الأقل من 1% تسبب ركود برك المياه؛ والميول الأكبر من 2% تسبب شعوراً بعدم الاتزان وانزلاق الأواني والأجهزة."
        ),

        // ==========================================
        // Category 7: السباكة والصرف
        // ==========================================
        TechnicalDimension(
            id = "dim_shower_mixer",
            categoryId = 7,
            itemTitle = "منسوب مخرج خلاط الدش والبانيو (Shower / Bath Mixer)",
            standardValue = "90 سم من التشطيب، والتباعد 15 سم بين البارد والساخن",
            tolerance = "المسافة بين السنترين 15.0 سم بالضبط (صفر خطأ)",
            applicationCondition = "البارد على اليمين والساخن على اليسار دائماً واستواء أفقي تام",
            referenceType = DimensionRefType.EGYPTIAN_CODE,
            referenceSource = "الكود المصري للتمديدات الصحية وشبكات المياه ECP 102",
            practicalNotes = "استخدم مسطرة ضبط مخارج الخلاط الحديدية أثناء التثبيت بالمونة لضمان عدم حدوث أي ميل أو خطأ في المسافة 15 سم."
        ),
        TechnicalDimension(
            id = "dim_washbasin_roughin",
            categoryId = 7,
            itemTitle = "مناسيب تغذية وصرف حوض الحمام (Wash Basin Rough-in)",
            standardValue = "التغذية: 55-60 سم | الصرف: 45-50 سم (منسوب وجه التشطيب)",
            tolerance = "± 2 سم",
            applicationCondition = "المسافة بين مخرجي تغذية البارد والساخن لحوض الحمام 10-12 سم",
            referenceType = DimensionRefType.EGYPTIAN_CODE,
            referenceSource = "الكود المصري للأعمال الصحية ECP 102",
            practicalNotes = "منسوب الحوض النهائي المعلق يكون 80-85 سم من أرضية التشطيب."
        ),
        TechnicalDimension(
            id = "dim_plumbing_drain_slopes",
            categoryId = 7,
            itemTitle = "ميول انحدار مواسير الصرف الصحي المعلقة والمدفونة (Drain Pipe Slopes)",
            standardValue = "صرف 4 بوصة (قواعد): 1.5 سم إلى 2 سم لكل متر (1.5-2%) | صرف 2 و3 بوصة: 2 سم لكل متر (2%)",
            tolerance = "الحد الأدنى 1 سم/متر والحد الأقصى 2.5 سم/متر",
            applicationCondition = "لمواسير الـ PVC الصرف الصحي بالانحدار الهيدروليكي الطبيعي",
            referenceType = DimensionRefType.EGYPTIAN_CODE,
            referenceSource = "الكود المصري للأعمال الصحية ECP 102",
            practicalNotes = "الميول الأقل من 1% تسبب ترسب المواد الصلبة وانسداد الماسورة؛ والميول الأكبر من 3% تجعل المياه تجري بسرعة وتترك الفضلات الصلبة خلفها فتسد الخط."
        ),

        // ==========================================
        // Category 8: الأدوات الصحية
        // ==========================================
        TechnicalDimension(
            id = "dim_wall_hung_wc_height",
            categoryId = 8,
            itemTitle = "ارتفاع قاعدة التواليت المعلقة وشاسيه الدفن (Wall-Hung WC Height)",
            standardValue = "منسوب سطح القاعدة النهائي: 40-42 سم من أرضية التشطيب | تيش التثبيت: 32-33 سم",
            tolerance = "± 1 سم",
            applicationCondition = "لراحة الجلوس للبالغين، مع ضبط التيش الصلب للشاسيه قبل صب أرضية الحمام",
            referenceType = DimensionRefType.MANUFACTURER_SPEC,
            referenceSource = "كتالوجات جروهي Grohe وجيبيريت Geberit والكود الصحي ECP 102",
            practicalNotes = "يجب مراجعة سمك طبقة السيراميك والدفان قبل تثبيت شاسيه الدفن حتى لا تصبح القاعدة منخفضة جداً أو مرتفعة بشكل معلق."
        ),

        // ==========================================
        // Category 9: الكهرباء والتأسيس
        // ==========================================
        TechnicalDimension(
            id = "dim_light_switch",
            categoryId = 9,
            itemTitle = "منسوب مفاتيح الإنارة العادية (Light Switches)",
            standardValue = "100 سم إلى 110 سم (سنتر العلبة الماجيك)",
            tolerance = "± 1 سم",
            applicationCondition = "من منسوب أرضية التشطيب النهائي، على مسافة 15-20 سم من حافة فتحة الباب من جهة المقبض",
            referenceType = DimensionRefType.EGYPTIAN_CODE,
            referenceSource = "الكود المصري للتركيبات الكهربائية في المباني ECP 201",
            practicalNotes = "يجب أن يكون المفتاح في متناول يد الداخل من الباب مباشرة دون الحاجة للالتفاف أو البحث خلف الضلفة."
        ),
        TechnicalDimension(
            id = "dim_power_socket",
            categoryId = 9,
            itemTitle = "منسوب برايز القوى والخدمات العادية (Power Sockets)",
            standardValue = "40 سم إلى 50 سم (سنتر العلبة الماجيك)",
            tolerance = "± 1 سم",
            applicationCondition = "من منسوب أرضية التشطيب النهائي في غرف النوم والصالات والممرات",
            referenceType = DimensionRefType.EGYPTIAN_CODE,
            referenceSource = "الكود المصري للتركيبات الكهربائية ECP 201",
            practicalNotes = "برايز غرف النوم بجوار السرير تحدد على منسوب 65-70 سم لتكون أعلى من الكمودينو والسرير مباشرة."
        ),
        TechnicalDimension(
            id = "dim_kitchen_counter_socket",
            categoryId = 9,
            itemTitle = "منسوب برايز المطبخ فوق الرخام (Kitchen Counter Sockets)",
            standardValue = "115 سم إلى 120 سم (سنتر العلبة)",
            tolerance = "± 1 سم",
            applicationCondition = "فوق كاونتر المطبخ (ارتفاع الرخام 90 سم + 25 سم خلوص للأجهزة)",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "دليل تصميم وتجهيز المطابخ والمواصفات المعمارية",
            practicalNotes = "تجنب وضع البرايز خلف حوض المطبخ أو البوتاجاز مباشرة؛ يجب أن تبعد مسافة 30 سم أفقياً عن أي مصدر مياه أو لهب."
        ),
        TechnicalDimension(
            id = "dim_wire_cross_sections",
            categoryId = 9,
            itemTitle = "مقاطع أسلاك الدوائر الكهربائية طبقاً للأحمال (Wire Cross-Sections)",
            standardValue = "إنارة: 1.5 مم2 | برايز: 2.5 مم2 | سخان/تكييف: 4-6 مم2 | صاعد رئيسي: 10-16 مم2",
            tolerance = "غير مسموح بتقليل المقاطع المحددة",
            applicationCondition = "نحاس نقي معتمد مع قواطع حماية متناسبة (10A للإنارة، 16A للبرايز، 20-32A للأحمال الثقيلة)",
            referenceType = DimensionRefType.EGYPTIAN_CODE,
            referenceSource = "الكود المصري للتركيبات والتوصيلات الكهربائية ECP 201",
            practicalNotes = "يجب تمديد سلك تأريض حماية مستقل (Earth 2.5-4 مم2 بلون أصفر في أخضر) لكافة البرايز والأحمال الثقيلة."
        ),

        // ==========================================
        // Category 13: التكييف والتهوية
        // ==========================================
        TechnicalDimension(
            id = "dim_ac_roughin_heights",
            categoryId = 13,
            itemTitle = "مناسيب تأسيس صرف وفريون التكييف الاسبليت (Split AC Rough-in)",
            standardValue = "مخرج الفريون والكهرباء: 220-240 سم من التشطيب (أقل من السقف بـ 35-40 سم) | صرف التكييف: منسوب 190-200 سم",
            tolerance = "± 3 سم",
            applicationCondition = "مع مراعاة ترك مسافة لا تقل عن 15 سم خلوص حر بين أعلى التكييف وسقف الجبس بورد لسحب الهواء",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "أصول التركيبات المعتمدة لشركات كاريير وشارب وإل جي",
            practicalNotes = "يجب أن يكون مخرج الصرف منخفضاً عن مخرج الفريون بـ 15-20 سم لضمان انحدار الخرطوم الداخلي دون تنقيط."
        ),

        // ==========================================
        // Category 15: السيراميك والبورسلين
        // ==========================================
        TechnicalDimension(
            id = "dim_floor_package_thickness",
            categoryId = 15,
            itemTitle = "سماكة فرشة أرضيات السيراميك والبورسلين (Floor Finish Package)",
            standardValue = "10 سم إجمالي (7 سم رمل نظيف + 2 سم مونة + 1 سم بلاطة)",
            tolerance = "8 سم كحد أدنى إلى 12 سم كحد أقصى",
            applicationCondition = "تقاس من ظهر الخرسانة المسلحة حتى سطح بلاط التشطيب النهائي",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "المواصفات الفنية المعتمدة لأعمال التشطيبات والأرضيات",
            practicalNotes = "إذا زاد عمق الردم عن 15 سم بسبب هبوط الخرسانة، يحظر ملؤه بالرمل الثقيل؛ يجب استخدام خرسانة رغوية خفيفة لتفادي زيادة الأحمال الميتة على السقف."
        ),
        TechnicalDimension(
            id = "dim_tile_expansion_joints",
            categoryId = 15,
            itemTitle = "عروض فواصل بلاط البورسلين وفواصل التمدد المحيطية (Tile Joints & Expansion Spacing)",
            standardValue = "فواصل العراميس: 1.5 إلى 2.0 مم | فاصل التمدد المحيطي بالحوائط: 8 إلى 10 مم | فواصل التمدد الإنشائية: كل 5 إلى 6 أمتار",
            tolerance = "الحد الأدنى لفاصل البورسلين 1.5 مم (يحظر تركيب زيرو فواصل إطلاقاً)",
            applicationCondition = "للبورسلين المقصوص ليزر والأرضيات الكبيرة بمناخ مصر الحار",
            referenceType = DimensionRefType.MANUFACTURER_SPEC,
            referenceSource = "المواصفات الأوروبية EN 14411 ودليل الرابطة الدولية لمصنعي البورسلين",
            practicalNotes = "تركيب البورسلين زيرو فواصل يؤدي إلى تطبيل وانفجار البلاط وكسره صيفاً عند أول موجة حرارة."
        ),

        // ==========================================
        // Category 19: الأسقف والجبس بورد
        // ==========================================
        TechnicalDimension(
            id = "dim_gypsum_grid_spacing",
            categoryId = 19,
            itemTitle = "مسافات تباعد شاسيهات الجبس بورد (Suspended Ceiling Grid Spacing)",
            standardValue = "قطاعات الأوميجا: 60 سم (أو 50 سم) | تيش التعليق: 70 سم في الاتجاهين",
            tolerance = "لا تزيد عن 60 سم للأوميجا بين المحاور",
            applicationCondition = "لألواح جبس بورد سمك 12.5 مم مع قطاعات صاج مجلفن سمك 0.55-0.60 مم",
            referenceType = DimensionRefType.MANUFACTURER_SPEC,
            referenceSource = "كتالوج التركيب المعتمد لشركة كناوف KNAUF والمواصفة ASTM C840",
            practicalNotes = "في الحمامات والمطابخ يفضل تقليل تباعد الأوميجا إلى 50 سم لزيادة جساءة السقف ومقاومة الرطوبة."
        ),
        TechnicalDimension(
            id = "dim_curtain_pelmet_box",
            categoryId = 19,
            itemTitle = "أبعاد بيت الستارة المدمج في الجبس بورد (Recessed Curtain Pelmet Box)",
            standardValue = "العرض: 18 سم إلى 22 سم | العمق/السقوط: 15 سم إلى 20 سم من السقف المعلق",
            tolerance = "عرض لا يقل عن 18 سم لاستيعاب مساري ستارة (بلاك أوت + شيفون) مع شريط ليد خفي",
            applicationCondition = "أمام جميع نوافذ وتراسات غرف النوم والاستقبال المجهزة بستائر قماشية",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "أصول التصميم الداخلي والمعماري المعاصر",
            practicalNotes = "إذا كان الشباك مجهزاً بشتر ألومنيوم (شيش حصير)، يجب زيادة عرض بيت الستارة إلى 25 سم لضمان عدم ملامسة الستارة لذراع أو مفتاح الشتر."
        ),

        // ==========================================
        // Category 24: المطابخ والخزائن
        // ==========================================
        TechnicalDimension(
            id = "dim_kitchen_cabinet_heights",
            categoryId = 24,
            itemTitle = "المقاسات والمناسيب القياسية لوحدات المطبخ (Kitchen Ergonomic Dimensions)",
            standardValue = "ارتفاع الرخامة: 90 سم | عمق الوحدات السفلية: 60 سم | عمق العلوية: 35 سم | المسافة بين العلوية والرخام: 60-65 سم",
            tolerance = "± 2 سم",
            applicationCondition = "لراحة حركة الذراعين أثناء الطهي وغسيل الأواني طبقاً لمعايير الأرجونوميكس",
            referenceType = DimensionRefType.MANUFACTURER_SPEC,
            referenceSource = "المعايير الدولية للتصميم الداخلي والأثاث المعماري",
            practicalNotes = "ارتفاع الشفاط البلت إن فوق شعلة البوتاجاز: 65-70 سم لبوتاجاز الغاز و60 سم للبوتاجاز الكهربائي الإندكشن."
        )
    )
}
