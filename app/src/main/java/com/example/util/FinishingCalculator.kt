package com.example.util

import kotlin.math.ceil

object FinishingCalculator {

    data class PlasterResult(
        val areaM2: Double,
        val cementBags50kg: Int,
        val sandM3: Double,
        val addibondKg: Double,
        val waterLiters: Double
    )

    fun calculatePlaster(areaM2: Double, thicknessCm: Double = 2.0): PlasterResult {
        val volumeM3 = areaM2 * (thicknessCm / 100.0) * 1.15 // 15% waste and compaction
        val sandM3 = volumeM3 * 0.95
        // Standard Egyptian mortar: 350 kg cement per m3 sand -> 7 bags of 50kg
        val cementKg = sandM3 * 350
        val cementBags = ceil(cementKg / 50.0).toInt()
        val addibondKg = sandM3 * 20.0
        val waterLiters = cementKg * 0.5
        return PlasterResult(
            areaM2 = areaM2,
            cementBags50kg = cementBags,
            sandM3 = (sandM3 * 100).toInt() / 100.0,
            addibondKg = (addibondKg * 10).toInt() / 10.0,
            waterLiters = (waterLiters).toInt().toDouble()
        )
    }

    data class TilesResult(
        val grossAreaM2: Double,
        val sandM3: Double,
        val cementBags50kg: Int,
        val adhesiveBags25kg: Int,
        val groutKg: Double
    )

    fun calculateTiles(netAreaM2: Double, wastePercent: Double = 7.0, isPorcelain: Boolean = true): TilesResult {
        val grossArea = netAreaM2 * (1.0 + wastePercent / 100.0)
        // Bedding sand: 7 cm average layer
        val sandM3 = netAreaM2 * 0.07 * 1.10
        // Bedding mortar cement: 4-5 bags per 100 m2 bedding, or adhesive
        val cementBags = if (!isPorcelain) ceil(netAreaM2 * 0.35).toInt() else ceil(netAreaM2 * 0.20).toInt()
        // Porcelain adhesive C2TE: 4.5 kg per m2 -> 25 kg bag covers ~5.5 m2
        val adhesiveBags = if (isPorcelain) ceil(netAreaM2 / 5.5).toInt() else ceil(netAreaM2 / 10.0).toInt()
        // Grout: 0.35 kg per m2 for 2-3mm joints
        val groutKg = netAreaM2 * 0.35
        return TilesResult(
            grossAreaM2 = (grossArea * 100).toInt() / 100.0,
            sandM3 = (sandM3 * 100).toInt() / 100.0,
            cementBags50kg = cementBags,
            adhesiveBags25kg = adhesiveBags,
            groutKg = (groutKg * 10).toInt() / 10.0
        )
    }

    data class PaintResult(
        val areaM2: Double,
        val sealerPails14L: Int,
        val puttyBags20kg: Int,
        val finishPaintPails14L: Int
    )

    fun calculatePaint(wallAreaM2: Double, coatsOfPutty: Int = 3): PaintResult {
        // Sealer: 14L pail covers ~120-140 m2 (one coat)
        val sealerPails = ceil(wallAreaM2 / 130.0).toInt()
        // Putty: 1 kg covers ~1 m2 per coat -> 20 kg bag covers ~20 m2 per coat
        val totalPuttyKg = wallAreaM2 * coatsOfPutty * 0.9
        val puttyBags = ceil(totalPuttyKg / 20.0).toInt()
        // Top coat plastic: 14L pail covers ~60-70 m2 for 2 coats
        val finishPails = ceil(wallAreaM2 / 65.0).toInt()
        return PaintResult(
            areaM2 = wallAreaM2,
            sealerPails14L = sealerPails.coerceAtLeast(1),
            puttyBags20kg = puttyBags.coerceAtLeast(1),
            finishPaintPails14L = finishPails.coerceAtLeast(1)
        )
    }

    data class GypsumResult(
        val ceilingAreaM2: Double,
        val boardsCount: Int,
        val omegaProfiles: Int,
        val cProfiles: Int,
        val angleProfiles: Int,
        val threadedRods: Int,
        val jointFillerBags25kg: Int,
        val fiberTapeRolls: Int
    )

    fun calculateGypsum(areaM2: Double, perimeterMeters: Double = 0.0): GypsumResult {
        // Sheet: 1.2 x 2.4 m = 2.88 m2 + 10% waste
        val boards = ceil((areaM2 * 1.10) / 2.88).toInt()
        // Omega profiles (every 60 cm): ~ 1.8 linear meters per m2 / 3m profile length
        val omegaCount = ceil((areaM2 * 1.8) / 3.0).toInt()
        // C-channel: ~ 1 linear meter per m2 / 3m
        val cCount = ceil((areaM2 * 1.0) / 3.0).toInt()
        // Perimeter angle: perimeter length / 3m profile length
        val effectivePerimeter = if (perimeterMeters > 0) perimeterMeters else (4 * kotlin.math.sqrt(areaM2))
        val angleCount = ceil(effectivePerimeter / 3.0).toInt()
        // Threaded rods (tie rods every 70x70 cm = ~2 rods per m2)
        val rods = ceil(areaM2 * 1.8).toInt()
        // Joint filler: 1 bag per 50 m2
        val fillerBags = ceil(areaM2 / 50.0).toInt()
        // Fiber tape (90m roll covers ~80 m2 ceiling)
        val tapeRolls = ceil(areaM2 / 80.0).toInt()

        return GypsumResult(
            ceilingAreaM2 = areaM2,
            boardsCount = boards.coerceAtLeast(1),
            omegaProfiles = omegaCount.coerceAtLeast(2),
            cProfiles = cCount.coerceAtLeast(1),
            angleProfiles = angleCount.coerceAtLeast(2),
            threadedRods = rods.coerceAtLeast(4),
            jointFillerBags25kg = fillerBags.coerceAtLeast(1),
            fiberTapeRolls = tapeRolls.coerceAtLeast(1)
        )
    }

    data class MasonryResult(
        val wallAreaM2: Double,
        val blocksCount: Int,
        val cementBags50kg: Int,
        val sandM3: Double
    )

    fun calculateMasonry(areaM2: Double, isRedBrick: Boolean = true): MasonryResult {
        // Red brick 25x12x6 cm: ~ 55 bricks per m2 for half-brick wall (12 cm) + 5% waste -> 58 bricks
        // Cement block 40x20x12 cm: ~ 12.5 blocks per m2 + 5% waste -> 13.5 blocks
        val blocks = if (isRedBrick) ceil(areaM2 * 58).toInt() else ceil(areaM2 * 13.5).toInt()
        // Mortar: For 1000 red bricks: ~ 0.8 m3 sand + 6 bags cement
        val sandM3 = if (isRedBrick) (blocks / 1000.0) * 0.8 else (blocks / 100.0) * 0.25
        val cementBags = if (isRedBrick) ceil((blocks / 1000.0) * 6).toInt() else ceil((blocks / 100.0) * 2.2).toInt()
        return MasonryResult(
            wallAreaM2 = areaM2,
            blocksCount = blocks,
            cementBags50kg = cementBags.coerceAtLeast(1),
            sandM3 = (sandM3 * 100).toInt() / 100.0
        )
    }
}
