package advent.of.code.day_one

class Sonar(private val sonarReads: List<Int>) {
    fun calculateIncreases(): Int {
        if (sonarReads.isEmpty()) {
            return 0
        }
        var previousValue = sonarReads[0]
        var increases = 0
        sonarReads.forEach {
            if (it > previousValue) {
                increases++
            }
            previousValue = it
        }

        return increases
    }

    fun calculate3GroupsIncreases(): Int {
        if (sonarReads.size < 2) {
            return 0
        }
        var increases = 0

        for (i in 1 .. (sonarReads.size - 3)) {
            val previousSum = sonarReads[i-1] + sonarReads[i] + sonarReads[i+1]
            val comparingSum = sonarReads[i] + sonarReads[i+1] + sonarReads[i+2]

            if(comparingSum > previousSum) {
                increases++
            }
        }

        return increases
    }
}
