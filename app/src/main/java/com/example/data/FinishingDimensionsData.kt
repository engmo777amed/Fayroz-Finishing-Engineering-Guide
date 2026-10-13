package com.example.data

import com.example.model.*

object FinishingDimensionsData {
    val dimensions = listOf(
        TechnicalDimension(
            id = "dim_sherb_level",
            categoryId = 1,
            itemTitle = "خط الشرب: المرجع الثابت للمناسيب",
            standardValue = "خط معلوم العلاقة بمنسوب التشطيب؛ اختيار+1.00 م مثال تنظيمي فقط.",
            tolerance = "حدد حد القبول وطريقة وطول القياس في مواصفة المشروع قبل التنفيذ؛ لا توجد سماحية عامة لكل الأنظمة.",
            applicationCondition = "اربط الخط بمنسوب الأرضية النهائي وانقله إلى نقاط محمية يمكن الرجوع إليها.",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "اعتماد المشروع المطلوب: مخطط القواطيع والفتحات، تفاصيل الروابط والأعتاب والفواصل، وحدود الاستلام. هذه قيم تصميمية يلزم مستندها قبل التنفيذ.",
            practicalNotes = "راجع النقل من المرجع نفسه؛ تغيير خط الشرب بين الغرف يغير مناسيب الأبواب والخدمات."
        ),
        TechnicalDimension(
            id = "dim_door_rough_opening",
            categoryId = 1,
            itemTitle = "فتحة الباب قبل تركيب الحلق",
            standardValue = "مقاس الحلق الخارجي وخلوص تركيبه ومنسوب التشطيب من جدول الباب.",
            tolerance = "حدد حد القبول وطريقة وطول القياس في مواصفة المشروع قبل التنفيذ؛ لا توجد سماحية عامة لكل الأنظمة.",
            applicationCondition = "راجع عرض الفتحة وارتفاعها والأكتاف وطبقات الأرضية ومنسوب التشطيب النهائي واتجاه الفتح.",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "اعتماد المشروع المطلوب: مخطط القواطيع والفتحات، تفاصيل الروابط والأعتاب والفواصل، وحدود الاستلام. هذه قيم تصميمية يلزم مستندها قبل التنفيذ.",
            practicalNotes = "اطلب مقاس الحلق وطريقة تركيبه قبل البناء؛ زيادة ثابتة على مقاس الضلفة لا تصلح لكل باب."
        ),
        TechnicalDimension(
            id = "dim_lintel_bearing",
            categoryId = 1,
            itemTitle = "العتب: البحر والمساند والتسليح",
            standardValue = "مقاس العتب ومسانده وتسليحه ووقت تحميله من التفصيلة الإنشائية.",
            tolerance = "حدد حد القبول وطريقة وطول القياس في مواصفة المشروع قبل التنفيذ؛ لا توجد سماحية عامة لكل الأنظمة.",
            applicationCondition = "يحدد بحسب الفتحة والحمل والمباني وطريقة التنفيذ.",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "اعتماد المشروع المطلوب: مخطط القواطيع والفتحات، تفاصيل الروابط والأعتاب والفواصل، وحدود الاستلام. هذه قيم تصميمية يلزم مستندها قبل التنفيذ.",
            practicalNotes = "أوقف تنفيذ العتب عند غياب التفصيلة؛ لا تستنتج الركوب من صورة أو رقم متداول."
        ),
        TechnicalDimension(
            id = "dim_plaster_thickness",
            categoryId = 3,
            itemTitle = "سمك المحارة وحدود الطبقة",
            standardValue = "قِس فرق الخلفية أولًا ثم راجع سمك كل طبقة وحدود نظام المحارة.",
            tolerance = "حدد حد القبول وطريقة وطول القياس في مواصفة المشروع قبل التنفيذ؛ لا توجد سماحية عامة لكل الأنظمة.",
            applicationCondition = "مثال DRY MORTAR 300 مصر، نشرة 04.24 ص 2:10–20 مم للطبقة؛ لا يعمم على غيره.",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "Weber مصر — DRY MORTAR 300، نشرة 04.24، ص 1–2: https://www.middleeast.weber/files/sodamco/2024-05/TDS_DRY_MORTAR_300-Egypt.pdf",
            practicalNotes = "عند تجاوز الحد اعتمد معالجة الخلفية أو نظام طبقات وتثبيت مناسبًا؛ شبك الفايبر ليس بديلًا تلقائيًا عن حامل محارة معدني."
        ),
        TechnicalDimension(
            id = "dim_boag_spacing",
            categoryId = 3,
            itemTitle = "تباعد البؤج والأوتار وفحص الاستواء",
            standardValue = "تباعد يسمح للقدة الفعلية بالاستناد والتسوية؛ طول أداة الاستلام من المواصفة.",
            tolerance = "حدد حد القبول وطريقة وطول القياس في مواصفة المشروع قبل التنفيذ؛ لا توجد سماحية عامة لكل الأنظمة.",
            applicationCondition = "اضبط سطح الأوتار ومستواها ثم قِس ما بينها بالأداة المطلوبة.",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "اعتماد المشروع المطلوب: مخطط القواطيع والفتحات، تفاصيل الروابط والأعتاب والفواصل، وحدود الاستلام. هذه قيم تصميمية يلزم مستندها قبل التنفيذ.",
            practicalNotes = "سجل أكبر انحراف ومكانه وطول القدة؛ لا تكتفِ بعبارة مستوي تمامًا."
        ),
        TechnicalDimension(
            id = "dim_floor_drain_slope",
            categoryId = 6,
            itemTitle = "الميول: حساب فرق المنسوب وفحص مسار الماء",
            standardValue = "الميل%=فرق المنسوب÷المسافة الأفقية×100، بعد توحيد الوحدات. الميل المطلوب يحدد من تصميم المصارف والطبقات.",
            tolerance = "حد الاستواء وتجمع الماء وطريقة وموعد الاختبار من مواصفة المشروع ونظام الطبقات.",
            applicationCondition = "حدد أبعد نقطة والمصرف والعتبات وأقل سمك وحمل الطبقة؛ راجع اتجاه الجريان في السطح النهائي.",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "المستند المطلوب: قطاع طبقات السطح أو الأرضية، الأحمال والمناسيب والمصارف والفائض والعتبات والفواصل، وحدود اختبار واستلام النظام.",
            practicalNotes = "مثال حسابي:40 ممفرق منسوب على 3 م=40÷3000×100=1.33%. المثال يشرح الحساب ولا يفرض ميلًا على كل حمام أو سطح."
        ),
        TechnicalDimension(
            id = "dim_shower_mixer",
            categoryId = 7,
            itemTitle = "منسوب مخرج خلاط الدش والبانيو (Shower / Bath Mixer)",
            standardValue = "المحور والبروز والمنسوب من قالب الخلاط المختار ووش التشطيب.",
            tolerance = "حسب المنتج والمشروع؛ لا تعمم مسافة 15 سم إلا على موديل يطلبها.",
            applicationCondition = "بعد تثبيت سمك التشطيب ومكان البانيو أو الشاور.",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "دليل موديل الخلاط واللوحة المعمارية.",
            practicalNotes = "ثبت قالبًا يحفظ المحاور أثناء الغلق وجرب الأغطية قبل التسليم."
        ),
        TechnicalDimension(
            id = "dim_washbasin_roughin",
            categoryId = 7,
            itemTitle = "مناسيب تغذية وصرف حوض الحمام (Wash Basin Rough-in)",
            standardValue = "مواقع البارد والساخن والصرف من الحوض والخلاط والسيفون ووحدة التخزين.",
            tolerance = "حد المشروع وقالب الجهاز؛ لا توجد مسافة ثابتة لكل الأحواض.",
            applicationCondition = "بعد اعتماد الحوض والخلاط والسيفون والرخامة أو الكابينة.",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "رسومات الورشة ودليل الأجهزة المختارة.",
            practicalNotes = "جرب السيفون والأدراج وخلوص المحابس قبل الغلق."
        ),
        TechnicalDimension(
            id = "dim_plumbing_drain_slopes",
            categoryId = 7,
            itemTitle = "ميول انحدار مواسير الصرف الصحي المعلقة والمدفونة (Drain Pipe Slopes)",
            standardValue = "الميل التصميمي لكل فرع من القطر والتصرف والخدمة وطول المسار والتهوية.",
            tolerance = "حسب مخطط الصرف وطريقة القياس بالمشروع.",
            applicationCondition = "يقاس على جسم الماسورة أو مناسيب قاعها قبل الردم، لا على البلاط.",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "حساب ومخطط الصرف ودليل النظام.",
            practicalNotes = "سجل منسوب البداية والنهاية والطول واحسب الميل وافحص عدم وجود نقطة عكسية."
        ),
        TechnicalDimension(
            id = "dim_wall_hung_wc_height",
            categoryId = 8,
            itemTitle = "منسوب ومحاور القاعدة المعلقة والشاسيه",
            standardValue = "من قالب الشاسيه والقاعدة ومنسوب التشطيب النهائي واحتياجات المستخدم.",
            tolerance = "حدود الموديل والمشروع؛ لا يعمم ارتفاع أو تباعد مسامير بين الأنظمة.",
            applicationCondition = "قبل تثبيت الشاسيه وبعد تثبيت وش الأرضية والحائط النهائيين على الرسومات.",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "Geberit Duofix Installation Manual؛ مرجع تركيب لنظام وموديل محددين وليس اعتمادًا عامًا لكل شاسيه: https://www.geberitnorthamerica.com/_assets/local-media/instructions/972-791-00-000.pdf",
            practicalNotes = "سجل محور الصرف والمسامير والبروز وسمك الكسوة، وجرّب القالب قبل الغلق."
        ),
        TechnicalDimension(
            id = "dim_light_switch",
            categoryId = 9,
            itemTitle = "منسوب مفاتيح الإنارة العادية (Light Switches)",
            standardValue = "من المخطط والأثاث ومتطلبات الوصول.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "يُقاس من منسوب أرضية التشطيب النهائي ومحور الفتحة أو الإكسسوار كما تحدد الرسومات؛ مسافة حافة الباب قرار مشروع وليست رقمًا عامًا.",
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
            applicationCondition = "يُقاس من منسوب أرضية التشطيب النهائي إلى المحور المحدد بالمخطط، بعد توقيع الأثاث والوزرات والأبواب.",
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
            applicationCondition = "يُحدد بعد اعتماد منسوب الكاونتر والأجهزة والبلاطات ومناطق الماء والحرارة؛ لا يعمم ارتفاع رخام أو خلوص واحد.",
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
            applicationCondition = "موصل مطابق للصنف المحدد، وحماية منسقة مع قدرته على حمل التيار وطريقة تركيبه وتيار القصر؛ أمبيرات السوق ليست وصفة عامة.",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "الرسومات والمواصفات المعتمدة ودليل المنتج؛ يتحقق من البند والإصدار قبل نسبة رقم للكود.",
            practicalNotes = "موصل الحماية يختار مع الدائرة وطريقة حسابه وتحمل تيار العطل والحماية الميكانيكية؛ لا يعمم 2.5–4 مم² لكل حمل."
        ),
        TechnicalDimension(
            id = "dim_ac_roughin_heights",
            categoryId = 13,
            itemTitle = "موضع وحدة التكييف وخلوص الخدمة ومساراتها",
            standardValue = "من قالب ودليل موديل الوحدة ومسار الهواء والفلتر وخط التبريد وصرف التكثيف.",
            tolerance = "حدود الموديل والمخطط؛ لا يعمم خلوص 15 سم أو منسوب ثابت.",
            applicationCondition = "بعد اعتماد الموديل وموقع الوحدة الداخلية والخارجية وقبل فتح الحائط أو السقف.",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "Daikin FTXTM-R Installation Manual؛ مرجع تركيب وتشغيل لموديل محدد، لا يثبت أقطارًا أو ضغوطًا أو خلوصات لكل الأجهزة: https://www.daikin.eu/content/dam/document-library/installation-manuals/ac/split/ftxtm-r/FTXTM-R_Installation%20manual_3PEN697375-4_English.pdf",
            practicalNotes = "راجع فتح الغطاء وسحب الفلتر وانحدار الصرف وأطوال الخط والوصول للصيانة بالموقع."
        ),
        TechnicalDimension(
            id = "dim_floor_package_thickness",
            categoryId = 15,
            itemTitle = "سماكة ومنسوب طبقات أرضية البلاط",
            standardValue = "مجموع طبقات النظام المعتمد من الركيزة إلى سطح التشطيب؛ يثبت قبل العتبات والمصارف والأبواب.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "عند تنسيق البلاط مع العزل والتسوية والأبواب والسلالم والمصارف.",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "قطاع الأرضيات وجدول التشطيبات ونشرات النظام؛ لا تنسب سماكة عامة للكود دون بند أصلي.",
            practicalNotes = "احسب كل طبقة فعلية ووزنها وميلها، ولا تستخدم سماكة اللاصق لتعويض فرق التسوية."
        ),
        TechnicalDimension(
            id = "dim_tile_expansion_joints",
            categoryId = 15,
            itemTitle = "عراميس البلاط وفواصل الحركة",
            standardValue = "عرض العراميس وتوزيع فواصل الحركة وحشوها من البلاط والتعرض والخلفية والمخطط؛ لا تملأ فاصل الحركة بروبة صلبة.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "الأرضيات والحوائط والسلالم والبلكونات، مع استمرار فواصل المبنى والركيزة إلى التشطيب حسب التفصيلة.",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "مخطط الفواصل ونظام البلاط واللاصق والسيلانت؛ مراجع المصنع أمثلة وليست رقمًا موحدًا للكود المصري.",
            practicalNotes = "العراميس تضبط الرص وتستقبل الروبة؛ فاصل الحركة يسمح بالحركة وله حشو وسيلانت أو قطاع مستقل."
        ),
        TechnicalDimension(
            id = "dim_gypsum_grid_spacing",
            categoryId = 19,
            itemTitle = "تباعد هيكل ومعلقات سقف الجبس بورد",
            standardValue = "من رقم نظام السقف والبحر والأحمال وعدد ونوع الألواح؛ لا توجد مسافة واحدة لكل الأسقف.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "يحدد بعد اعتماد نظام السقف وتنسيق الفتحات والخدمات والأحمال والتدعيمات، وقبل رفع الألواح.",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "Shop Drawing ووثيقة رقم النظام من Knauf Egypt Download Center أو المورد المعتمد؛ لم ينسب رقم عام للكود المصري.",
            practicalNotes = "سجل تباعد كل نوع عنصر ومثبتاته على الرسم، وافحص الأحمال المستقلة والتدعيمات قبل الغلق؛ لون اللوح لا يحدد الهيكل."
        ),
        TechnicalDimension(
            id = "dim_curtain_pelmet_box",
            categoryId = 19,
            itemTitle = "قطاع بيت الستارة داخل السقف",
            standardValue = "من عدد المسارات وطبقات القماش وبروز الشباك أو الشتر والموتور ومساحة الفك والصيانة؛ يعتمد بعينة قطاع فعلية.",
            tolerance = "حد القبول من المواصفة المعتمدة ونظام المنتج؛ يراجع بأداة ملائمة معايرة.",
            applicationCondition = "عند وجود ستارة مخفية أو موتور أو شتر يتطلب خلوصًا ووصولًا؛ لا يضاف لكل نافذة تلقائيًا.",
            referenceType = DimensionRefType.BEST_PRACTICE,
            referenceSource = "مخطط السقف والستائر ودليل المسار والموتور وعينة القطاع؛ لم ينسب مقاس عام للكود المصري.",
            practicalNotes = "اختبر حركة القماش وفك الموتور أو الدرايفر قبل غلق الجبس، ولا تستخرج المقاس من صورة مجسمة."
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

