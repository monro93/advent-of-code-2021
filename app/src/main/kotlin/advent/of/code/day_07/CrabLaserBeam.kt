package advent.of.code.day_07

class CrabLaserBeam(
    private val crabPositions: List<Int>
) {
    fun getCheapestAlignment(withIncrementalCost: Boolean = false): Int {
        val movementCost:  MutableMap<Int, List<Int>> = mutableMapOf()
        val maxPosition = crabPositions.maxOf { it }

        for(i in 0..maxPosition) {
            val costList = mutableListOf<Int>()
            crabPositions.forEach {
                val cost = if(withIncrementalCost) {
                    getCostIncremental(i, it)
                }else {
                    kotlin.math.abs(it - i)
                }
                costList.add(cost)
            }
            movementCost[i] = costList
        }

        val minFuel = movementCost.minByOrNull {
            it.value.sum()
        }

        return minFuel?.value?.sum() ?: 0
    }

    private fun getCostIncremental(destination: Int, crabPosition: Int): Int {
        val difference = kotlin.math.abs(destination - crabPosition)

        return ((difference * difference) + difference) / 2
    }
}
