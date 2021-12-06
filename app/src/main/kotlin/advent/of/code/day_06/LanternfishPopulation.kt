package advent.of.code.day_06

class LanternfishPopulation(
    val lanternfishes: MutableList<Lanternfish>
) {
    fun simulate(days: Int) {
        for(i in 0 until days) {
            for(fish in lanternfishes.toList()) {
                val newBorn = fish.age1Day()
                if(newBorn != null) {
                    lanternfishes.add(newBorn)
                }
            }
        }
    }

    fun calculatePopulationAfterDays(days: Int):  Long {
        var fishByDaysToReproduce: MutableMap<Int, Long> = lanternfishes
            .map { it.daysToReproduce }
            .groupBy { it }
            .mapValues { it.value.size.toLong() }
            .toMutableMap()

        for (i in 0 until days) {
            val fishesNextIteration = mutableMapOf<Int, Long>()
            fishByDaysToReproduce.map { (age, count) ->
                if(age == 0) {
                    fishesNextIteration[Lanternfish.DAYS_TO_REPRODUCE_AFTER_REPRODUCING] = fishesNextIteration[Lanternfish.DAYS_TO_REPRODUCE_AFTER_REPRODUCING]?.plus(count) ?: count
                }else {
                    fishesNextIteration[age - 1] = fishesNextIteration[age - 1]?.plus(count) ?: count
                }
            }
            fishByDaysToReproduce[0]?.let {
                fishesNextIteration[Lanternfish.DAYS_TO_REPRODUCE_FOR_NEW_BORN] = it
            }
            fishByDaysToReproduce = fishesNextIteration
        }
        return fishByDaysToReproduce.values.sum()
    }

    override fun toString(): String {
        return lanternfishes.toString()
    }
}
