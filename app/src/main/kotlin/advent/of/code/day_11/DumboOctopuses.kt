package advent.of.code.day_11

class DumboOctopuses(
    private val octopusesEnergyLevel: List<MutableList<Int>>,
) {

    private var alreadyFlashed: MutableList<Pair<Int, Int>> = mutableListOf()

    fun howManyStepsUntilAllFlashes(): Int {
        var step = 0
        while (alreadyFlashed.size != octopusesEnergyLevel.size * octopusesEnergyLevel[0].size && step < 10000) {
            step++
            howManyFlashesAfterSteps(1)
        }

        return step
    }

    fun howManyFlashesAfterSteps(steps: Int): Int {
        var flashes = 0
        println("step: 0")
        printOctopusesLevels()
        for (step in 0 until steps) {
            alreadyFlashed = mutableListOf()
            for (i in octopusesEnergyLevel.indices) {
                for (j in octopusesEnergyLevel.indices) {
                    octopusesEnergyLevel[i][j] = octopusesEnergyLevel[i][j].plus(1)
                }
            }

            for (i in octopusesEnergyLevel.indices) {
                for (j in octopusesEnergyLevel.indices) {
                    if(octopusesEnergyLevel[i][j] > 9) {
                        flashes += octopusFlashes(i, j)
                    }
                }
            }
            println("step: ${(step+1)}")
            printOctopusesLevels()
            println("total flashes: $flashes")
        }

        return flashes
    }

    private fun printOctopusesLevels() {
        for (i in octopusesEnergyLevel.indices) {
            for (j in octopusesEnergyLevel.indices) {
               print(octopusesEnergyLevel[i][j].toString())
            }
            println()
        }
    }

    private fun octopusFlashes(
        i: Int,
        j: Int,
    ): Int {
        if(alreadyFlashed.contains(i to j) ||
            i < 0 ||
            i >= octopusesEnergyLevel.size ||
            j < 0 ||
            j >= octopusesEnergyLevel[0].size
        ) {
            return 0
        }

        if(octopusesEnergyLevel[i][j] >= 9) {
            octopusesEnergyLevel[i][j] = 0
            alreadyFlashed.add(i to j)
            return 1 +
                    octopusFlashes(i + 1, j) +
                    octopusFlashes(i - 1, j) +
                    octopusFlashes(i, j + 1) +
                    octopusFlashes(i, j - 1) +
                    octopusFlashes(i + 1, j + 1) +
                    octopusFlashes(i + 1, j - 1) +
                    octopusFlashes(i - 1, j + 1) +
                    octopusFlashes(i - 1, j - 1)
        }else{
            octopusesEnergyLevel[i][j] = octopusesEnergyLevel[i][j].plus(1)
        }

        return 0
    }
}
