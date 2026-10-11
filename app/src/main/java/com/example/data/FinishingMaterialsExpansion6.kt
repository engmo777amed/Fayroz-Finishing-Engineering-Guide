package com.example.data

import com.example.model.MaterialCard

object FinishingMaterialsExpansion6 {
    val materials = listOf(
        MaterialCard(
            id = "mat_cement_block_hollow", categoryIds = listOf(1),
            marketNameAr = "بلوك أسمنتي مفرغ", technicalNameAr = "Hollow concrete masonry units", tradeNames = emptyList(),
            classification = "بلوك أسمنتي مفرغ", definitionAndFunction = "وحدات مباني بفراغات تقلل الوزن؛ يراجع استعمالها كقواطيع أو مباني حاملة وفق التصميم.",
            componentsAndProperties = "الأبعاد والكثافة ومقاومة الضغط ونسبة الفراغات طبق مواصفة التصميم؛ لا يختار على أساس الوزن وحده.", availableTypes = listOf("مفرغ عادي", "بلوك عازل معتمد"),
            dimensionsAndSpecs = "الأبعاد والكثافة ومقاومة الضغط ونسبة الفراغات طبق مواصفة التصميم؛ لا يختار على أساس الوزن وحده.", qualityGrades = "فحص المقاسات والتكسر والتشقق وطلب شهادات المقاومة عند اشتراطها.", standardSpecs = "الأبعاد والكثافة ومقاومة الضغط ونسبة الفراغات طبق مواصفة التصميم؛ لا يختار على أساس الوزن وحده.",
            egyptianBrands = emptyList(), usages = listOf("أعمال المباني والطوب والقواطيع"),
            prosAndCons = "أخف من المصمت؛ تثبيت الأحمال الثقيلة يحتاج أنكر مناسب للبلوك المفرغ.", installationMethod = "تجربة مدماك وتوزيع الفواصل؛ ملء المونة وربط الأطراف والأعتاب وفق الرسم؛ لا تملأ كل الفراغات دون تفصيلة.", installationTools = listOf("ميزان وخيط وقصافة بلوك"),
            storageAndHandling = "على قاعدة مستوية مع حماية من التلوث وتجنب رص يسبب تكسر الأركان.", inspectionAndAcceptance = listOf("فحص المقاسات والتكسر والتشقق وطلب شهادات المقاومة عند اشتراطها."),
            counterfeitDetection = "راجع بيانات الصنف والتشغيلة والفاتورة لدى المورد؛ الفحص البصري وحده لا يثبت الأصالة.",
            commonMisuses = listOf("تثبيت جهاز ثقيل بفيشر عادي أو كسر البلوك عشوائيًا."), lifespan = "يتحدد بالنظام وظروف الاستخدام والصيانة؛ يرجع لدليل المنتج.",
            maintenanceInstructions = "فحص الشروخ أو حركة التثبيت وعلاج المصدر.", relatedStageIds = emptyList()
        ),
        MaterialCard(
            id = "mat_portland_cement", categoryIds = listOf(1, 2, 3, 4, 6, 15, 16),
            marketNameAr = "أسمنت بورتلاندي للمونة", technicalNameAr = "Portland cement for mortar", tradeNames = emptyList(),
            classification = "أسمنت بورتلاندي للمونة", definitionAndFunction = "رابط هيدروليكي للمونة؛ تختار فئة الأسمنت وتوافقه مع الإضافات والتشطيب.",
            componentsAndProperties = "نوع وفئة الأسمنت وشهادة المطابقة حسب المونة؛ لا تُستبدل المنتجات دون اعتماد.", availableTypes = listOf("أسمنت بورتلاندي", "أنواع مخلوطة وفق المواصفة"),
            dimensionsAndSpecs = "نوع وفئة الأسمنت وشهادة المطابقة حسب المونة؛ لا تُستبدل المنتجات دون اعتماد.", qualityGrades = "رفض الأكياس المتحجرة أو المبتلة ومراجعة تاريخ الإنتاج والمصدر.", standardSpecs = "نوع وفئة الأسمنت وشهادة المطابقة حسب المونة؛ لا تُستبدل المنتجات دون اعتماد.",
            egyptianBrands = emptyList(), usages = listOf("أعمال المباني والطوب والقواطيع", "تجهيز الحوائط والأسقف قبل التشطيب", "أعمال المحارة الداخلية والخارجية", "أعمال العزل المائي للرطوبة", "أعمال ميول الأرضيات والصرف", "الأرضيات السيراميك والبورسلين", "الرخام والجرانيت والحجر الطبيعي"),
            prosAndCons = "متاح وقوي بعد المعالجة؛ مونة غنية جدًا أو كثيرة الماء قد تتشقق.", installationMethod = "الخلط بجرعات محسوبة مع الرمل والماء والإضافة ثم المعالجة؛ لا تعاد مونة بدأت الشك بإضافة الماء.", installationTools = listOf("وعاء قياس وخلاط ومعدات وقاية"),
            storageAndHandling = "على طبالي جافة وتحت غطاء؛ الأقدم أولًا مع متابعة الصلاحية.", inspectionAndAcceptance = listOf("رفض الأكياس المتحجرة أو المبتلة ومراجعة تاريخ الإنتاج والمصدر."),
            counterfeitDetection = "راجع بيانات الصنف والتشغيلة والفاتورة لدى المورد؛ الفحص البصري وحده لا يثبت الأصالة.",
            commonMisuses = listOf("خلط الجبس لتسريع الشك أو استعمال أسمنت متحجر."), lifespan = "يتحدد بالنظام وظروف الاستخدام والصيانة؛ يرجع لدليل المنتج.",
            maintenanceInstructions = "حماية المونة من الجفاف المبكر وفق مواصفة العمل.", relatedStageIds = emptyList()
        ),
        MaterialCard(
            id = "mat_latex_bonding_additive", categoryIds = listOf(2, 3, 4, 6),
            marketNameAr = "إضافة لاتكس لتحسين ربط المونة", technicalNameAr = "Polymer latex mortar additive", tradeNames = emptyList(),
            classification = "إضافة لاتكس لتحسين ربط المونة", definitionAndFunction = "إضافة بوليمرية مختارة لتحسين خواص المونة أو طبقة الربط؛ ليست بديلًا تلقائيًا لأي برايمر.",
            componentsAndProperties = "النسبة وطريقة إضافة الماء وتوقيت المونة فوق طبقة الربط حسب نشرة الصنف؛ الأديبوند اسم منتج وليس نسبة عامة.", availableTypes = listOf("لاتكس SBR", "مستحلبات ربط أخرى حسب المنتج"),
            dimensionsAndSpecs = "النسبة وطريقة إضافة الماء وتوقيت المونة فوق طبقة الربط حسب نشرة الصنف؛ الأديبوند اسم منتج وليس نسبة عامة.", qualityGrades = "مطابقة النشرة والعبوة والصلاحية؛ تجربة التصاق للنظام عند الحاجة.", standardSpecs = "النسبة وطريقة إضافة الماء وتوقيت المونة فوق طبقة الربط حسب نشرة الصنف؛ الأديبوند اسم منتج وليس نسبة عامة.",
            egyptianBrands = emptyList(), usages = listOf("تجهيز الحوائط والأسقف قبل التشطيب", "أعمال المحارة الداخلية والخارجية", "أعمال العزل المائي للرطوبة", "أعمال ميول الأرضيات والصرف"),
            prosAndCons = "تحسن التشغيل والربط في النظام الصحيح؛ زيادة الجرعة أو عدم التوافق يضر الأداء.", installationMethod = "اعتماد تجربة سطح؛ قياس الإضافة وماء الخلط؛ تطبيق الربط في التوقيت الموصى به وحماية المونة.", installationTools = listOf("وعاء تدريج وخلاط وفرشاة"),
            storageAndHandling = "عبوة محكمة بعيدًا عن الحرارة والتجمد وفق النشرة.", inspectionAndAcceptance = listOf("مطابقة النشرة والعبوة والصلاحية؛ تجربة التصاق للنظام عند الحاجة."),
            counterfeitDetection = "راجع بيانات الصنف والتشغيلة والفاتورة لدى المورد؛ الفحص البصري وحده لا يثبت الأصالة.",
            commonMisuses = listOf("خلطها عشوائيًا مع أي مونة أو ترك طبقة الربط تجف خلافًا للنشرة."), lifespan = "يتحدد بالنظام وظروف الاستخدام والصيانة؛ يرجع لدليل المنتج.",
            maintenanceInstructions = "متابعة التصاق المونة وعلاج الرطوبة أو الحركة المسببة للعيوب.", relatedStageIds = emptyList()
        ),
        MaterialCard(
            id = "mat_bitumen_primer", categoryIds = listOf(4),
            marketNameAr = "برايمر بيتوميني للعزل", technicalNameAr = "Bituminous membrane primer", tradeNames = emptyList(),
            classification = "برايمر بيتوميني للعزل", definitionAndFunction = "طبقة تمهيدية مناسبة لسطح ونظام اللفائف المختار؛ ليست غشاء عزل كاملًا.",
            componentsAndProperties = "الاستهلاك ورطوبة السطح وزمن الجفاف والتوافق طبق نشرة البرايمر واللفائف.", availableTypes = listOf("برايمر مائي", "برايمر قائم على مذيب"),
            dimensionsAndSpecs = "الاستهلاك ورطوبة السطح وزمن الجفاف والتوافق طبق نشرة البرايمر واللفائف.", qualityGrades = "فحص العبوة والصلاحية؛ سطح مغطى ومتجانس وغير لزج وفق معيار المنتج.", standardSpecs = "الاستهلاك ورطوبة السطح وزمن الجفاف والتوافق طبق نشرة البرايمر واللفائف.",
            egyptianBrands = emptyList(), usages = listOf("أعمال العزل المائي للرطوبة"),
            prosAndCons = "يحسن الالتصاق؛ الإفراط في السمك أو حبس المذيب يسبب فقاعات.", installationMethod = "تنظيف السطح وإصلاحه؛ فرد معدل منتظم وانتظار الجفاف المحدد قبل اللفائف؛ تهوية ومنع الاشتعال قرب المذيب.", installationTools = listOf("فرشاة أو رول ومعدات وقاية"),
            storageAndHandling = "عبوة مغلقة بعيدًا عن مصدر حرارة وفق بطاقة السلامة.", inspectionAndAcceptance = listOf("فحص العبوة والصلاحية؛ سطح مغطى ومتجانس وغير لزج وفق معيار المنتج."),
            counterfeitDetection = "راجع بيانات الصنف والتشغيلة والفاتورة لدى المورد؛ الفحص البصري وحده لا يثبت الأصالة.",
            commonMisuses = listOf("استخدام برايمر غير متوافق أو تسخين اللفائف قبل تبخر مذيب البرايمر."), lifespan = "يتحدد بالنظام وظروف الاستخدام والصيانة؛ يرجع لدليل المنتج.",
            maintenanceInstructions = "تُفحص منظومة العزل وتفاصيل الأطراف بعد التنفيذ، لا يعاد دهان برايمر كحل للتسريب.", relatedStageIds = emptyList()
        ),
        MaterialCard(
            id = "mat_porcelain_tiles", categoryIds = listOf(15),
            marketNameAr = "بلاط سيراميك وبورسلين", technicalNameAr = "Ceramic and porcelain tiles", tradeNames = emptyList(),
            classification = "بلاط سيراميك وبورسلين", definitionAndFunction = "كسوة أرضية أو حائط يختار امتصاصها ومقاومتها ومقاسها وفق الاستخدام.",
            componentsAndProperties = "المقاس والسمك والمعايرة والدرجة اللونية ومقاومة الانزلاق طبق الموقع؛ لا يُعتمد وصف «فرز أول» وحده.", availableTypes = listOf("سيراميك مزجج", "بورسلين", "بلاط قطع مستقيم أو حافة عادية"),
            dimensionsAndSpecs = "المقاس والسمك والمعايرة والدرجة اللونية ومقاومة الانزلاق طبق الموقع؛ لا يُعتمد وصف «فرز أول» وحده.", qualityGrades = "فحص صندوق وعينة للمقاس والتقوس واللون وطلب شهادة اختبار عند الاشتراط.", standardSpecs = "المقاس والسمك والمعايرة والدرجة اللونية ومقاومة الانزلاق طبق الموقع؛ لا يُعتمد وصف «فرز أول» وحده.",
            egyptianBrands = emptyList(), usages = listOf("الأرضيات السيراميك والبورسلين"),
            prosAndCons = "متين وسهل التنظيف؛ المقاسات الكبيرة تتطلب قاعدة واستقامة ولاصق مناسبين.", installationMethod = "فرز جاف وتحديد شبكة الفواصل ثم لاصق مناسب للقاعدة والبلاط؛ تنظيف وحماية حتى الشك؛ لا يغمر البورسلين قليل الامتصاص دون توجيه المنتج.", installationTools = listOf("قاطعة وأداة توزيع لاصق وفواصل وقدة"),
            storageAndHandling = "على قاعدة ثابتة بأمان؛ عدم خلط التشغيلات والألوان دون تجربة توزيع.", inspectionAndAcceptance = listOf("فحص صندوق وعينة للمقاس والتقوس واللون وطلب شهادة اختبار عند الاشتراط."),
            counterfeitDetection = "راجع بيانات الصنف والتشغيلة والفاتورة لدى المورد؛ الفحص البصري وحده لا يثبت الأصالة.",
            commonMisuses = listOf("إلغاء الفواصل أو فرش لاصق فوق قاعدة مفككة."), lifespan = "يتحدد بالنظام وظروف الاستخدام والصيانة؛ يرجع لدليل المنتج.",
            maintenanceInstructions = "منظف مناسب للفواصل والسطح، وصيانة فواصل الحركة.", relatedStageIds = emptyList()
        ),
        MaterialCard(
            id = "mat_double_glass", categoryIds = listOf(22),
            marketNameAr = "زجاج مزدوج محكم للشبابيك", technicalNameAr = "Insulating glass unit (IGU)", tradeNames = emptyList(),
            classification = "زجاج مزدوج محكم للشبابيك", definitionAndFunction = "وحدة زجاج بطبقتين وفاصل محكم؛ تركيبها داخل قطاع وموانع تسرب مناسبين يؤثر في أدائها.",
            componentsAndProperties = "تركيب الزجاج والفاصل والسمك والأبعاد واشتراط زجاج الأمان يحددها التصميم والمصنع، وليس مقاسًا موحدًا.", availableTypes = listOf("شفاف", "Low-E", "زجاج أمان حسب الموقع"),
            dimensionsAndSpecs = "تركيب الزجاج والفاصل والسمك والأبعاد واشتراط زجاج الأمان يحددها التصميم والمصنع، وليس مقاسًا موحدًا.", qualityGrades = "فحص الحواف والخدوش والتكاثف بين الطبقات ومطابقة تكوين الوحدة.", standardSpecs = "تركيب الزجاج والفاصل والسمك والأبعاد واشتراط زجاج الأمان يحددها التصميم والمصنع، وليس مقاسًا موحدًا.",
            egyptianBrands = emptyList(), usages = listOf("الألوميتال والزجاج وUPVC"),
            prosAndCons = "يقلل انتقال حرارة وضوضاء حسب النظام؛ تلف إحكام الحافة يسبب تكاثفًا داخليًا.", installationMethod = "تركيب فوق كعوب زجاج صحيحة مع جوانات متوافقة وعدم سد صرف القطاع؛ منع احتكاك حافة الزجاج بالمعدن.", installationTools = listOf("شفاط زجاج وقفازات وكعوب تركيب"),
            storageAndHandling = "قائمًا على حوامل مبطنة ومن دون ضغط على الحواف.", inspectionAndAcceptance = listOf("فحص الحواف والخدوش والتكاثف بين الطبقات ومطابقة تكوين الوحدة."),
            counterfeitDetection = "راجع بيانات الصنف والتشغيلة والفاتورة لدى المورد؛ الفحص البصري وحده لا يثبت الأصالة.",
            commonMisuses = listOf("تركيب الزجاج ملاصقًا للمعدن أو استخدام مادة تؤذي ختم الوحدة."), lifespan = "يتحدد بالنظام وظروف الاستخدام والصيانة؛ يرجع لدليل المنتج.",
            maintenanceInstructions = "تنظيف دون خدش وفحص الجوانات والتكاثف الداخلي.", relatedStageIds = emptyList()
        ),
        MaterialCard(
            id = "mat_neutral_silicone", categoryIds = listOf(22, 24, 27, 28),
            marketNameAr = "سيليكون نيوترال متوافق مع السطح", technicalNameAr = "Neutral cure silicone sealant", tradeNames = emptyList(),
            classification = "سيليكون نيوترال متوافق مع السطح", definitionAndFunction = "مادة سد مرنة؛ يختار الصنف للزجاج أو المناطق الصحية أو المرايا حسب اعتماد المنتج.",
            componentsAndProperties = "الحركة وعرض وعمق الفاصل وتوافق السطح ومقاومة الفطريات حسب الصنف؛ لا يفترض أن كل نيوترال مناسب للحجر أو المرايا.", availableTypes = listOf("زجاج وواجهات", "صحي", "صنف للمرايا معتمد"),
            dimensionsAndSpecs = "الحركة وعرض وعمق الفاصل وتوافق السطح ومقاومة الفطريات حسب الصنف؛ لا يفترض أن كل نيوترال مناسب للحجر أو المرايا.", qualityGrades = "مراجعة الصنف والصلاحية وتجربة التصاق وعدم تلطيخ السطح.", standardSpecs = "الحركة وعرض وعمق الفاصل وتوافق السطح ومقاومة الفطريات حسب الصنف؛ لا يفترض أن كل نيوترال مناسب للحجر أو المرايا.",
            egyptianBrands = emptyList(), usages = listOf("الألوميتال والزجاج وUPVC", "أعمال المطابخ والخزائن والأثاث الثابت", "المواد اللاصقة والفواصل والسيليكون", "التشطيبات النهائية والأعمال التكميلية"),
            prosAndCons = "مرن ومقاوم للماء بعد التصلب؛ غالبًا لا يقبل الدهان ويتطلب تحضيرًا صحيحًا.", installationMethod = "تنظيف وتجفيف وتجربة توافق؛ تركيب حبل دعم عند الحاجة وفرد الفاصل وتسويته وفق النشرة.", installationTools = listOf("مسدس سيليكون وأداة تشطيب وشريط حماية"),
            storageAndHandling = "عبوة مغلقة حسب حرارة التخزين والصلاحية.", inspectionAndAcceptance = listOf("مراجعة الصنف والصلاحية وتجربة التصاق وعدم تلطيخ السطح."),
            counterfeitDetection = "راجع بيانات الصنف والتشغيلة والفاتورة لدى المورد؛ الفحص البصري وحده لا يثبت الأصالة.",
            commonMisuses = listOf("استبدال لاصق إنشائي بالسيليكون أو لصق مرآة بمنتج غير معتمد."), lifespan = "يتحدد بالنظام وظروف الاستخدام والصيانة؛ يرجع لدليل المنتج.",
            maintenanceInstructions = "تجديد الفاصل المتلف بعد إزالة المادة القديمة وتحضير السطح.", relatedStageIds = emptyList()
        )
    )
}
