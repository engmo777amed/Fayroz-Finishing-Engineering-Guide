package com.example.data

import com.example.model.InspectionPhase
import com.example.model.QualityCheckItem

object FinishingChecklistsData {
    val checkItems: List<QualityCheckItem> = listOf(
        // ==========================================
        // أعمال المباني (Category 1)
        // ==========================================
        QualityCheckItem(
            id = "chk_mas_01",
            categoryId = 1,
            stageId = "stage_masonry_01",
            titleAr = "مطابقة نوع ورتبة الطوب للمواصفات والمدماك الأول مصمت",
            phase = InspectionPhase.BEFORE,
            method = "فحص بصري ومراجعة أبعاد عينات الطوب الأسمنتي المصمت",
            toolUsed = "شريط قياس وكاليبر",
            acceptanceCriterion = "طوب أسمنتي مصمت سليم خالٍ من الشروخ، المدماك الأول أسمنتي مصمت بكامل الوحدة",
            technicalReference = "الكود المصري للبناء ECP 204"
        ),
        QualityCheckItem(
            id = "chk_mas_02",
            categoryId = 1,
            stageId = "stage_masonry_01",
            titleAr = "تربيع الغرف والزوايا القائمة للغرف 90 درجة",
            phase = InspectionPhase.BEFORE,
            method = "قياس وتري الغرفة ومطابقة أضلاع المثلث 3:4:5",
            toolUsed = "شريط قياس صلب وزاوية حديد 90°",
            acceptanceCriterion = "تساوي الوترين تماماً، التفاوت لا يزيد عن ±5 مم في كامل الغرفة",
            technicalReference = "أصول الصناعة والرسومات المعمارية"
        ),
        QualityCheckItem(
            id = "chk_mas_03",
            categoryId = 1,
            stageId = "stage_masonry_02",
            titleAr = "تثبيت كانات الربط المجلفنة مع الأعمدة الخرسانية",
            phase = InspectionPhase.DURING,
            method = "معاينة وجود الكانات وتثبيتها بمسامير وفيشر ومغموسة بالمونة",
            toolUsed = "فحص بصري ومطرقة فحص خفيفة",
            acceptanceCriterion = "كانة مجلفنة كل 4 مداميك (40 سم)، مثبتة بإحكام في العمود وبطول 20 سم بالمدماك",
            technicalReference = "الكود المصري للمباني ECP 204"
        ),
        QualityCheckItem(
            id = "chk_mas_04",
            categoryId = 1,
            stageId = "stage_masonry_02",
            titleAr = "ركوب الأعتاب الخرسانية المسلحة فوق الفتحات",
            phase = InspectionPhase.DURING,
            method = "قياس طول الركوب على جانبي فتحة الباب أو الشباك",
            toolUsed = "شريط قياس صلب",
            acceptanceCriterion = "ركوب العتب لا يقل عن 20 سم من كل جانب على المباني الحاملة",
            technicalReference = "المواصفات الفنية المصرية للمباني"
        ),
        QualityCheckItem(
            id = "chk_mas_05",
            categoryId = 1,
            stageId = "stage_masonry_02",
            titleAr = "رأسية واستواء الحائط وخلوه من الميول والتكحيل",
            phase = InspectionPhase.AFTER,
            method = "إسقاط خيط الشاغول ووضع القدة 3م رأسياً وأفقياً وقطرياً",
            toolUsed = "ميزان خيط (شاغول) وقدة ألومنيوم 3م",
            acceptanceCriterion = "رأسية تامة دون أي ميول، والفراغ تحت القدة أقل من 3 مم، والعراميس مكحولة",
            technicalReference = "الكود المصري للبناء ECP 204"
        ),

        // ==========================================
        // أعمال المحارة (Category 3)
        // ==========================================
        QualityCheckItem(
            id = "chk_pls_01",
            categoryId = 3,
            stageId = "stage_plaster_01",
            titleAr = "تثبيت شبك الفواصل الفايبر/المجلفن عند فواصل الخرسانة والمباني",
            phase = InspectionPhase.BEFORE,
            method = "معاينة تركيب الشبك بعرض 20 سم فوق الفواصل وتمديدات الكهرباء",
            toolUsed = "شريط قياس ومعاينة بالعين واللمس",
            acceptanceCriterion = "الشبك مشدود ومثبت بمسامير ووردات كل 25 سم زجزاج بنصفين متساويين (10 سم بكل جانب)",
            technicalReference = "الكود المصري لأعمال البياض ECP 203"
        ),
        QualityCheckItem(
            id = "chk_pls_02",
            categoryId = 3,
            stageId = "stage_plaster_01",
            titleAr = "جودة وخشونة وتغطية الطرطشة المسمارية",
            phase = InspectionPhase.DURING,
            method = "فحص تغطية السطح بالطرطشة وملمسها وخلوها من السيلان والتفتت",
            toolUsed = "فحص باللمس باليد وحك بمسمار صلب",
            acceptanceCriterion = "تغطية 100% للمسطح، مسمارية حادة لا تنفرك باليد بعد رشها 3 أيام بالماء",
            technicalReference = "المواصفات المصرية لأعمال البياض"
        ),
        QualityCheckItem(
            id = "chk_pls_03",
            categoryId = 3,
            stageId = "stage_plaster_02",
            titleAr = "رأسية وتعامد أوتار وبؤج المحارة بميزان الليزر",
            phase = InspectionPhase.AFTER,
            method = "توجيه خطوط الليزر ومطابقة ميزان الخيط والزاوية الحديدية الكبيرة 90°",
            toolUsed = "ميزان ليزر 360° وزاوية حديد 100×100 سم",
            acceptanceCriterion = "رأسية صفر خطأ، تعامد الأركان 90°، تطابق توازي الحوائط المتقابلة",
            technicalReference = "الكود المصري ECP 203"
        ),
        QualityCheckItem(
            id = "chk_pls_04",
            categoryId = 3,
            stageId = "stage_plaster_03",
            titleAr = "فرد البطانة والملء وتدريع القدة الألومنيوم رأسياً وأفقياً وقطرياً",
            phase = InspectionPhase.DURING,
            method = "فحص استواء البطانة بالقدة 3م وكبس المونة بين الأوتار دون ريجة أو تموجات",
            toolUsed = "قدة ألومنيوم 3م وكشاف إضاءة مائل",
            acceptanceCriterion = "فراغ نفاذ الضوء أقل من 2 مم، وخلو كامل المسطح من الجبس أو السواقط",
            technicalReference = "الكود المصري لأعمال البياض ECP 203"
        ),
        QualityCheckItem(
            id = "chk_pls_05",
            categoryId = 3,
            stageId = "stage_plaster_05",
            titleAr = "تثبيت زوايا السوك المعدنية المجلفنة وتطابق عروض الفتحات المعمارية",
            phase = InspectionPhase.BEFORE,
            method = "مراجعة رأسية أنف الزاوية بميزان الشاغول وشريط القياس وتثبيتها بالمونة والأديبوند",
            toolUsed = "ميزان خيط شاغول وميزان ليزر وشريط قياس صلب",
            acceptanceCriterion = "رأسية تامة بنسبة خطأ صفر، وتطابق أبعاد الفتحة في 3 مناسيب بدون جبس",
            technicalReference = "المواصفة الأمريكية ASTM C1063"
        ),
        QualityCheckItem(
            id = "chk_pls_06",
            categoryId = 3,
            stageId = "stage_plaster_04",
            titleAr = "جودة تخشين ومس الضهارة بالرابونة وتجانس الملمس الحبيبي",
            phase = InspectionPhase.AFTER,
            method = "معاينة سطح التخشين باللمس والضوء المائل لكشف التسويس والحفر والريجة",
            toolUsed = "كشاف ليد مائل ورابونة فحص",
            acceptanceCriterion = "ملمس ناعم متجانس خالي من الثقوب والتكتلات والشروخ الشعرية",
            technicalReference = "أصول الصناعة المعمارية والكود ECP 203"
        ),
        QualityCheckItem(
            id = "chk_pls_07",
            categoryId = 3,
            stageId = "stage_plaster_04",
            titleAr = "بياض المصيص للأسقف وخلوه من الاصفرار والتفرك",
            phase = InspectionPhase.AFTER,
            method = "فحص بياض ونعومة أسقف المصيص المعجون بماء الجير السلطاني المصفى",
            toolUsed = "ميزان مياه 100 سم وقدة استلام مسطحة",
            acceptanceCriterion = "سطح أبيض ناصع حريري الملمس، صلب ومستوٍ دون أي شروخ أو طبقات متبودرة",
            technicalReference = "المواصفة المصرية لجبس البياض ES 188"
        ),
        QualityCheckItem(
            id = "chk_pls_08",
            categoryId = 3,
            stageId = "stage_plaster_06",
            titleAr = "عراميس وفواصل تمدد الواجهات وميول الجلسات وبراطيم قطرات المطر",
            phase = InspectionPhase.DURING,
            method = "معاينة استقامة العراميس الأفقية وميول جلسات الشبابيك وتجويف قطرة المطر",
            toolUsed = "ميزان ليزر وشريط قياس وميزان مياه",
            acceptanceCriterion = "أفقية تامة للعراميس، ميول جلسات ≥ 5% للخارج، وتجويف قطرة مطر سليم 2×2 سم",
            technicalReference = "الكود المصري للبياض الخارجي ECP 203"
        ),
        QualityCheckItem(
            id = "chk_pls_09",
            categoryId = 3,
            stageId = "stage_plaster_07",
            titleAr = "اختبار التطبيل بالمطرقة البلاستيكية لجميع الحوائط والأسقف",
            phase = InspectionPhase.AFTER,
            method = "النقر المنتظم بمطرقة فحص خفيفة على كامل المسطح والأركان وحول علب الكهرباء",
            toolUsed = "مطرقة خشبية/بلاستيكية فحص خفيفة وطباشير تعليم",
            acceptanceCriterion = "صوت صلب رنان بنسبة 100%؛ وانعدام أي صوت طبلة أجوف (تكسير فوري لأي تطبيل)",
            technicalReference = "دليل مهندس الجودة QA/QC والكود ECP 203"
        ),
        QualityCheckItem(
            id = "chk_pls_10",
            categoryId = 3,
            stageId = "stage_plaster_07",
            titleAr = "قياس نسبة الرطوبة السطحية للحوائط وخلوها من التزهير الملحي",
            phase = InspectionPhase.AFTER,
            method = "قياس رطوبة المحارة بجهاز قياس الرطوبة الرقمي قبل السماح ببدء مرحلة الدهان",
            toolUsed = "جهاز قياس الرطوبة الرقمي (Moisture Meter)",
            acceptanceCriterion = "نسبة الرطوبة ≤ 4%، مع خلو تام من أي بودرة أملاح بيضاء متزهيرة",
            technicalReference = "المواصفات القياسية لأعمال الدهانات والتشطيبات"
        ),

        // ==========================================
        // أعمال العزل المائي (Category 4)
        // ==========================================
        QualityCheckItem(
            id = "chk_iso_01",
            categoryId = 4,
            stageId = "stage_waterproofing_01",
            titleAr = "تنفيذ رقبة الزجاجة بكامل المحيط ونظافة السطح الخرساني",
            phase = InspectionPhase.BEFORE,
            method = "معاينة دوران مونة رقبة الزجاجة عند التقاء الحوائط بالأرضيات",
            toolUsed = "فحص بصري وكاليبر قياس النصف قطر",
            acceptanceCriterion = "دوران ناعم بنصف قطر 5-7 سم بمونة أسمنتية غنية بأديبوند 65 دون زوايا قائمة",
            technicalReference = "كود عزل الرطوبة ECP 104"
        ),
        QualityCheckItem(
            id = "chk_iso_02",
            categoryId = 4,
            stageId = "stage_waterproofing_02",
            titleAr = "تراكب وركوب لفائف الممبرين 4 مم وانصهار اللحامات",
            phase = InspectionPhase.DURING,
            method = "قياس ركوب اللحامات وفحص خروج عصير البيتومين المنصهر",
            toolUsed = "مسطرة قياس ومسطرين فحص غير مدبب",
            acceptanceCriterion = "ركوب طولي 10 سم وعرضي 15 سم، خروج لمعة بيتومين بعرض 5 مم دون أي فتحات",
            technicalReference = "المواصفات القياسية المصرية ES 4337"
        ),
        QualityCheckItem(
            id = "chk_iso_03",
            categoryId = 4,
            stageId = "stage_waterproofing_02",
            titleAr = "اختبار الغمر بالمياه لمدة 48 ساعة متواصلة",
            phase = InspectionPhase.AFTER,
            method = "ملء الفراغ بالماء لارتفاع 10-15 سم ومعاينة سقف الدور السفلي يومياً",
            toolUsed = "علامة منسوب على الحائط وسدادات مواسير ميكانيكية",
            acceptanceCriterion = "ثبات المنسوب، جفاف سقف الدور الأسفل 100% دون أي رطوبة أو تنديه أو تسريب",
            technicalReference = "الكود المصري لعزل الرطوبة ECP 104"
        ),

        // ==========================================
        // أعمال السباكة والتغذية (Category 7)
        // ==========================================
        QualityCheckItem(
            id = "chk_plm_01",
            categoryId = 7,
            stageId = "stage_plumbing_01",
            titleAr = "مناسيب ومسافات مخارج الخلاطات واستواؤها الأفقي",
            phase = InspectionPhase.DURING,
            method = "قياس المسافة بين مخرجي البارد والساخن ومستواهما الأفقي بالنسبة للشرب",
            toolUsed = "شريط قياس وميزان مياه ومسطرة كرنك حديدية",
            acceptanceCriterion = "المسافة 15 سم بالضبط بين السنترين، الاستواء الأفقي تام، المنسوب 90 سم من التشطيب",
            technicalReference = "الكود المصري للأعمال الصحية ECP 102"
        ),
        QualityCheckItem(
            id = "chk_plm_02",
            categoryId = 7,
            stageId = "stage_plumbing_01",
            titleAr = "اختبار كبس شبكة التغذية بالباروميتر عند 15-20 بار لمدة 24 ساعة",
            phase = InspectionPhase.AFTER,
            method = "ملء الشبكة بالماء وتفريغ الهواء ورفع الضغط ومراقبة قراءة الباروميتر المعاير",
            toolUsed = "طلمبة كبس هيدروليكية مع باروميتر معاير وشهادة معايرة",
            acceptanceCriterion = "ثبات الضغط عند 15-20 بار طوال 24 ساعة دون أي انخفاض واعتماد شهادة الضمان",
            technicalReference = "المواصفات الألمانية DIN 8077/8078 والكود المصري ECP 102"
        ),

        // ==========================================
        // الأدوات الصحية وشاسيهات الدفن (Category 8)
        // ==========================================
        QualityCheckItem(
            id = "chk_san_01",
            categoryId = 8,
            stageId = "stage_sanitary_01",
            titleAr = "رأسية وصلابة تثبيت شاسيه التواليت المعلق ومنسوب التيش",
            phase = InspectionPhase.BEFORE,
            method = "مراجعة رأسية الشاسيه بميزان المياه وقياس ارتفاع التيش من شرب +1.00م",
            toolUsed = "ميزان مياه ألومنيوم وشريط قياس صلب",
            acceptanceCriterion = "ارتفاع التيش 32-33 سم من التشطيب النهائي، رأسية تامة، عدم وجود أي تخلخل تحت ثقل 100 كجم",
            technicalReference = "كتالوج جروهي وجيبيريت والكود الصحي ECP 102"
        ),

        // ==========================================
        // أعمال الكهرباء والتأسيس (Category 9)
        // ==========================================
        QualityCheckItem(
            id = "chk_ele_01",
            categoryId = 9,
            stageId = "stage_electric_01",
            titleAr = "مناسيب واستواء العلب الماجيك وبروزها بالنسبة للبؤج",
            phase = InspectionPhase.DURING,
            method = "مقارنة منسوب العلب مع خط الشرب ومراجعة تسطحها مع بؤجة المحارة",
            toolUsed = "ميزان ليزر وشريط قياس وقدة ألومنيوم",
            acceptanceCriterion = "تطابق المنسوب مع المخطط، بروز شفة العلبة على وش البؤجة تماماً، استواء أفقي تام",
            technicalReference = "الكود المصري للتركيبات الكهربائية ECP 201"
        ),
        QualityCheckItem(
            id = "chk_ele_02",
            categoryId = 9,
            stageId = "stage_electric_01",
            titleAr = "مطابقة مقاطع الأسلاك وخلو الخراطيم من اللحامات",
            phase = InspectionPhase.AFTER,
            method = "فحص سحب الأسلاك الملونة المعتمدة وتطابق مقاطع الإنارة والبرايز والقوى",
            toolUsed = "كاليبر ميكرومتر وفحص بصري للعلب والبوطات",
            acceptanceCriterion = "سلك معتمد السويدي، 1.5 مم2 للإنارة، 2.5 مم2 للبرايز، 4-6 مم2 للتكييف، بدون أي لحام داخلي",
            technicalReference = "كود التمديدات الكهربائية ECP 201"
        ),

        // ==========================================
        // لوحات الكهرباء والحماية (Category 10)
        // ==========================================
        QualityCheckItem(
            id = "chk_db_01",
            categoryId = 10,
            stageId = "stage_electric_02",
            titleAr = "فصل قاطع التسريب الأرضي RCCB 30mA بزمن الاستجابة وزر التيست",
            phase = InspectionPhase.AFTER,
            method = "حقن تيار تسريب تجريبي 30mA وفحص زمن الفصل بالمللي ثانية والضغط على زر T",
            toolUsed = "جهاز فاحص قواطع التسريب RCD Tester",
            acceptanceCriterion = "فصل فوري للقاطع في زمن أقل من 40 مللي ثانية وثبات القاطع عند التشغيل الطبيعي",
            technicalReference = "المواصفة القياسية العالمية IEC 61008-1 وكود الكهرباء ECP 201"
        ),

        // ==========================================
        // أنظمة التكييف والتهوية (Category 13)
        // ==========================================
        QualityCheckItem(
            id = "chk_hvac_01",
            categoryId = 13,
            stageId = "stage_hvac_01",
            titleAr = "اختبار كبس مواسير نحاس التكييف بالنيتروجين المضغوط 30 بار",
            phase = InspectionPhase.DURING,
            method = "ضغط شبكة النحاس بغاز النيتروجين الجاف الخامل ومراقبة المانومتر 24 ساعة",
            toolUsed = "أسطوانة نيتروجين مضغوط ومنظم ضغط عالي مع مانومتر معاير",
            acceptanceCriterion = "ثبات الضغط عند 30-35 بار لمدة 24 ساعة دون أي انخفاض والتأكد من عدم وجود خفس بالنحاس",
            technicalReference = "المواصفة الأمريكية لأنظمة التبريد ASTM B280"
        ),

        // ==========================================
        // أعمال السيراميك والبورسلين (Category 15)
        // ==========================================
        QualityCheckItem(
            id = "chk_cer_01",
            categoryId = 15,
            stageId = "stage_flooring_01",
            titleAr = "تسليخ ظهر البورسلين واستخدام مادة لاصقة معتمدة C2TE",
            phase = InspectionPhase.BEFORE,
            method = "معاينة دهان مادة اللصق على ظهر البلاطة ونوع المادة المستخدمة",
            toolUsed = "فحص بصري لشكائر المادة وظهر البلاطة",
            acceptanceCriterion = "استخدام مادة C2TE معتمدة حصراً، تسليخ كامل ظهر البلاطة بروبة المادة قبل التركيب",
            technicalReference = "المواصفة الأوروبية EN 12004"
        ),
        QualityCheckItem(
            id = "chk_cer_02",
            categoryId = 15,
            stageId = "stage_flooring_01",
            titleAr = "استواء الأرضية وخلوها من التسنين بالقدة واللمس",
            phase = InspectionPhase.AFTER,
            method = "وضع القدة 3م في كافة الاتجاهات وتمرير اليد فوق فواصل البلاط",
            toolUsed = "قدة ألومنيوم 3م وفيلر قياس الخلوص",
            acceptanceCriterion = "فراغ تحت القدة أقل من 1 مم، التسنين بين البلاطات صفر مم، الفواصل مستقيمة 2-3 مم",
            technicalReference = "الكود المصري للأرضيات والتشطيبات ECP 203"
        ),
        QualityCheckItem(
            id = "chk_cer_03",
            categoryId = 15,
            stageId = "stage_flooring_01",
            titleAr = "اختبار التطبيل وفراغات الهواء تحت البلاط",
            phase = InspectionPhase.AFTER,
            method = "النقر الخفيف على البلاطات بمطرقة خشبية أو قضيب معدني في الأركان والوسط",
            toolUsed = "مطرقة فحص خشبية خاصة",
            acceptanceCriterion = "صوت مصمت تماماً وخلو الأرضية من أي صوت رنان أجوف (تطبيل) بنسبة 100%",
            technicalReference = "أصول الاستلام الهندسي وضبط الجودة QA/QC"
        ),

        // ==========================================
        // الرخام والجرانيت (Category 16)
        // ==========================================
        QualityCheckItem(
            id = "chk_mar_01",
            categoryId = 16,
            stageId = "stage_marble_01",
            titleAr = "مادية كانات الاستانلس 304 وعمق شق تثبيت الرخام الميكانيكي",
            phase = InspectionPhase.DURING,
            method = "فحص الكانات بالمغناطيس وقياس عمق الشق وسماكة الصاج بالكاليبر",
            toolUsed = "مغناطيس قوي وكاليبر قياس إلكتروني",
            acceptanceCriterion = "عدم انجذاب الكانة للمغناطيس إطلاقاً (ستانلس 304)، سمك الكانة 3-4 مم، عمق الشق 15 مم",
            technicalReference = "المواصفة البريطانية لتركيب الرخام BS 8298"
        ),

        // ==========================================
        // الأرضيات الخشبية وHDF (Category 17)
        // ==========================================
        QualityCheckItem(
            id = "chk_wood_01",
            categoryId = 17,
            stageId = "stage_wood_floor_01",
            titleAr = "فاصل التمدد المحيطي 10 مم وجفاف الأرضية الخرسانية",
            phase = InspectionPhase.BEFORE,
            method = "قياس رطوبة الأرضية الخرسانية وفحص صلايب التباعد حول الحوائط",
            toolUsed = "جهاز قياس الرطوبة الرقمي Moisture Meter ومسطرة قياس",
            acceptanceCriterion = "رطوبة الخرسانة أقل من 10%، فاصل تمدد حر 8-10 مم بكامل محيط الغرفة تحت الوزرات",
            technicalReference = "المواصفة الأوروبية للأرضيات العائمة EN 13329"
        ),

        // ==========================================
        // أعمال الدهانات (Category 18)
        // ==========================================
        QualityCheckItem(
            id = "chk_pnt_01",
            categoryId = 18,
            stageId = "stage_paint_01",
            titleAr = "استواء المعجون والصنفرة باختبار الإضاءة الزاوية (باللمبة)",
            phase = InspectionPhase.AFTER,
            method = "تسليط كشاف ليد قوي بمحاذاة الحائط في غرفة مظلمة وفحص أي ريجة أو تمويج",
            toolUsed = "كشاف ليد متنقل 50 وات ومسطرة قدة",
            acceptanceCriterion = "سطح حريري أملس كالمرآة خالي من أي خطوط سكاكين أو ريجة أو ثقوب دائرية",
            technicalReference = "أصول ضبط جودة الدهانات المعمارية الفاخرة"
        ),

        // ==========================================
        // أعمال الجبس بورد (Category 19)
        // ==========================================
        QualityCheckItem(
            id = "chk_gyp_01",
            categoryId = 19,
            stageId = "stage_gypsum_01",
            titleAr = "تباعد قطاعات الأوميجا والتيش وسمك الصاج المجلفن",
            phase = InspectionPhase.DURING,
            method = "قياس المسافات بين محاور قطاعات الأوميجا ونقاط التيش المعلق وكاليبر للصاج",
            toolUsed = "شريط قياس وكاليبر إلكتروني",
            acceptanceCriterion = "تباعد الأوميجا 50-60 سم كحد أقصى، التيش كل 70 سم، سمك الصاج لا يقل عن 0.55-0.60 مم",
            technicalReference = "دليل تركيب كناوف والمواصفة ASTM C840"
        ),
        QualityCheckItem(
            id = "chk_gyp_02",
            categoryId = 19,
            stageId = "stage_gypsum_01",
            titleAr = "معالجة الفواصل بشاش الفايبر ومعجون الفواصل الخاص",
            phase = InspectionPhase.AFTER,
            method = "فحص نوع شريط التسليح والمعجون المستخدم واستواء السطح بالقدة",
            toolUsed = "قدة ألومنيوم 2.5م وميزان ليزر",
            acceptanceCriterion = "شريط فايبر مدمج بمنتصف الفاصل، معجون فواصل كناوف Fugenfüller، استواء أفقي تام",
            technicalReference = "دليل الجودة الفنية لأعمال الجبس بورد"
        ),

        // ==========================================
        // النجارة والأبواب (Category 21)
        // ==========================================
        QualityCheckItem(
            id = "chk_crp_01",
            categoryId = 21,
            stageId = "stage_carpentry_01",
            titleAr = "رأسية وتربيع الحلق الزفر وحقن فوم البولي يوريثان",
            phase = InspectionPhase.AFTER,
            method = "فحص ميزان الخيط للقوائم ومقارنة وتري الحلق وتغطية الفوم بكامل العمق",
            toolUsed = "ميزان خيط شاغول وشريط قياس صلب",
            acceptanceCriterion = "رأسية تامة صفر ميل، تساوي وتري الحلق، امتلاء الفراغ خلف الحلق بفوم صلب دون فراغات",
            technicalReference = "المواصفة القياسية المصرية لأعمال النجارة ES 347"
        ),

        // ==========================================
        // الألوميتال والواجهات (Category 22)
        // ==========================================
        QualityCheckItem(
            id = "chk_alu_01",
            categoryId = 22,
            stageId = "stage_aluminum_01",
            titleAr = "اختبار عزل مياه الأمطار والرش بضغط المياه على نوافذ الألوميتال",
            phase = InspectionPhase.AFTER,
            method = "توجيه خرطوم مياه مضغوط من الخارج على ضلف الشباك وفواص السيليكون 10 دقائق",
            toolUsed = "خرطوم مياه مضغوط مع فحص بصري للداخل بمنديل ورقي",
            acceptanceCriterion = "عدم نفاذ قطرة ماء واحدة للداخل، انسياب مياه المجرى عبر فتحات التصريف Weep Holes بنجاح",
            technicalReference = "المواصفة الأوروبية لاختبار نفاذية النوافذ EN 1027"
        )
    )
}
