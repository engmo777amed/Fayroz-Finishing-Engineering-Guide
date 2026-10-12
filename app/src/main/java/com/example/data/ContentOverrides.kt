package com.example.data
import com.example.model.*

fun EditableContentStore.apply(value: FinishingCategory): FinishingCategory = value.copy(
    titleAr = text("category", value.id.toString(), "titleAr", value.titleAr),
    titleEn = text("category", value.id.toString(), "titleEn", value.titleEn),
    summaryAr = text("category", value.id.toString(), "summaryAr", value.summaryAr)
)

fun EditableContentStore.apply(value: ExecutionStage): ExecutionStage = value.copy(
    titleAr = text("stage", value.id.toString(), "titleAr", value.titleAr),
    objective = text("stage", value.id.toString(), "objective", value.objective),
    prerequisites = text("stage", value.id.toString(), "prerequisites", value.prerequisites),
    requiredMaterials = texts("stage", value.id.toString(), "requiredMaterials", value.requiredMaterials),
    toolsNeeded = texts("stage", value.id.toString(), "toolsNeeded", value.toolsNeeded),
    executionSteps = texts("stage", value.id.toString(), "executionSteps", value.executionSteps),
    dimensionsAndSlopes = text("stage", value.id.toString(), "dimensionsAndSlopes", value.dimensionsAndSlopes),
    mixesAndRatios = text("stage", value.id.toString(), "mixesAndRatios", value.mixesAndRatios),
    technicalRequirements = texts("stage", value.id.toString(), "technicalRequirements", value.technicalRequirements),
    mepCoordination = text("stage", value.id.toString(), "mepCoordination", value.mepCoordination),
    siteTips = texts("stage", value.id.toString(), "siteTips", value.siteTips),
    commonMistakes = texts("stage", value.id.toString(), "commonMistakes", value.commonMistakes),
    qualityTests = texts("stage", value.id.toString(), "qualityTests", value.qualityTests),
    handoverCriteria = texts("stage", value.id.toString(), "handoverCriteria", value.handoverCriteria),
    codeReferences = texts("stage", value.id.toString(), "codeReferences", value.codeReferences)
)

fun EditableContentStore.apply(value: MaterialCard): MaterialCard = value.copy(
    marketNameAr = text("material", value.id.toString(), "marketNameAr", value.marketNameAr),
    technicalNameAr = text("material", value.id.toString(), "technicalNameAr", value.technicalNameAr),
    tradeNames = texts("material", value.id.toString(), "tradeNames", value.tradeNames),
    classification = text("material", value.id.toString(), "classification", value.classification),
    definitionAndFunction = text("material", value.id.toString(), "definitionAndFunction", value.definitionAndFunction),
    componentsAndProperties = text("material", value.id.toString(), "componentsAndProperties", value.componentsAndProperties),
    availableTypes = texts("material", value.id.toString(), "availableTypes", value.availableTypes),
    dimensionsAndSpecs = text("material", value.id.toString(), "dimensionsAndSpecs", value.dimensionsAndSpecs),
    qualityGrades = text("material", value.id.toString(), "qualityGrades", value.qualityGrades),
    standardSpecs = text("material", value.id.toString(), "standardSpecs", value.standardSpecs),
    egyptianBrands = texts("material", value.id.toString(), "egyptianBrands", value.egyptianBrands),
    usages = texts("material", value.id.toString(), "usages", value.usages),
    prosAndCons = text("material", value.id.toString(), "prosAndCons", value.prosAndCons),
    installationMethod = text("material", value.id.toString(), "installationMethod", value.installationMethod),
    installationTools = texts("material", value.id.toString(), "installationTools", value.installationTools),
    storageAndHandling = text("material", value.id.toString(), "storageAndHandling", value.storageAndHandling),
    inspectionAndAcceptance = texts("material", value.id.toString(), "inspectionAndAcceptance", value.inspectionAndAcceptance),
    counterfeitDetection = text("material", value.id.toString(), "counterfeitDetection", value.counterfeitDetection),
    commonMisuses = texts("material", value.id.toString(), "commonMisuses", value.commonMisuses),
    lifespan = text("material", value.id.toString(), "lifespan", value.lifespan),
    maintenanceInstructions = text("material", value.id.toString(), "maintenanceInstructions", value.maintenanceInstructions)
)

fun EditableContentStore.apply(value: QualityCheckItem): QualityCheckItem = value.copy(
    stageId = text("check", value.id.toString(), "stageId", value.stageId),
    titleAr = text("check", value.id.toString(), "titleAr", value.titleAr),
    method = text("check", value.id.toString(), "method", value.method),
    toolUsed = text("check", value.id.toString(), "toolUsed", value.toolUsed),
    acceptanceCriterion = text("check", value.id.toString(), "acceptanceCriterion", value.acceptanceCriterion),
    technicalReference = text("check", value.id.toString(), "technicalReference", value.technicalReference)
)

fun EditableContentStore.apply(value: TroubleshootingDefect): TroubleshootingDefect = value.copy(
    stageId = text("defect", value.id.toString(), "stageId", value.stageId),
    titleAr = text("defect", value.id.toString(), "titleAr", value.titleAr),
    marketName = text("defect", value.id.toString(), "marketName", value.marketName),
    description = text("defect", value.id.toString(), "description", value.description),
    detectionSigns = texts("defect", value.id.toString(), "detectionSigns", value.detectionSigns),
    probableCauses = texts("defect", value.id.toString(), "probableCauses", value.probableCauses),
    rootCauseDiagnosis = text("defect", value.id.toString(), "rootCauseDiagnosis", value.rootCauseDiagnosis),
    impactOnWork = text("defect", value.id.toString(), "impactOnWork", value.impactOnWork),
    repairSteps = texts("defect", value.id.toString(), "repairSteps", value.repairSteps),
    repairMaterials = texts("defect", value.id.toString(), "repairMaterials", value.repairMaterials),
    preventionMethod = text("defect", value.id.toString(), "preventionMethod", value.preventionMethod),
    handoverRetest = text("defect", value.id.toString(), "handoverRetest", value.handoverRetest)
)

fun EditableContentStore.apply(value: TechnicalDimension): TechnicalDimension = value.copy(
    itemTitle = text("dimension", value.id.toString(), "itemTitle", value.itemTitle),
    standardValue = text("dimension", value.id.toString(), "standardValue", value.standardValue),
    tolerance = text("dimension", value.id.toString(), "tolerance", value.tolerance),
    applicationCondition = text("dimension", value.id.toString(), "applicationCondition", value.applicationCondition),
    referenceSource = text("dimension", value.id.toString(), "referenceSource", value.referenceSource),
    practicalNotes = text("dimension", value.id.toString(), "practicalNotes", value.practicalNotes)
)
