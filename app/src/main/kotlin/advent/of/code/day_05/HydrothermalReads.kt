package advent.of.code.day_05

import advent.of.code.utils.splitByLineBreaks

class HydrothermalReads(
    val hydrothermalVents: List<HydrothermalVent>,
) {
    private val frequencyMatrix: MutableMap<HydrothermalVent.Point, Int> = mutableMapOf()

    fun calculateFrequencyMatrix(withDiagonals: Boolean = false) {
        hydrothermalVents.forEach { vent ->
            val line = if (!withDiagonals) vent.getLine() else vent.getLineWithDiagonals()
            line.forEach { point ->
                val value = frequencyMatrix.getOrDefault(point, 0) + 1
                frequencyMatrix[point] = value
            }
        }
    }

    fun getHowManyCollisions(): Int {
        return frequencyMatrix.filter { it.value > 1 }.size
    }

    companion object {
        fun fromString(input: String): HydrothermalReads {
            val regexp = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex()
            val vents = mutableListOf<HydrothermalVent>()
            input
                .splitByLineBreaks()
                .forEach { line ->
                    val match = regexp.find(line)
                    if (match != null) {
                        vents.add(
                            HydrothermalVent(
                                HydrothermalVent.Point(match.groups[1]!!.value.toInt(),
                                    match.groups[2]!!.value.toInt()),
                                HydrothermalVent.Point(match.groups[3]!!.value.toInt(),
                                    match.groups[4]!!.value.toInt()),
                            )
                        )
                    }
                }
            return HydrothermalReads(vents)
        }
    }
}

