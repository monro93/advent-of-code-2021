package advent.of.code.day_03

class Cracks(
    var gamma: Int = 0,
    var epsilon: Int = 0,
    var oxygenGenerator: Int = 0,
    var co2: Int = 0,
) {
    fun loadDiagnosticReport(report : List<String>) {
        val frequencyArray = calculateFrequencyArray(report)
        gamma = calculateGamma(frequencyArray)
        epsilon = calculateEpsilon(frequencyArray)
    }

    fun loadLifeSupport(report: List<String>) {
        oxygenGenerator = findNumber(report, 1, 0).toInt(2)
        co2 = findNumber(report, 0, 1).toInt(2)
    }

    private fun findNumber(numbers: List<String>, positiveValue: Int, negativeValue: Int, index: Int = 0): String {
        val frequencyArray = calculateFrequencyArray(numbers.map { it[index].toString() })
        val newNumbers = numbers.filter {
            it[index].toString() == if(frequencyArray[0] >= 0) positiveValue.toString() else negativeValue.toString()
        }
        if(newNumbers.size == 1) {
            return newNumbers[0]
        }

        return findNumber(newNumbers, positiveValue, negativeValue, index + 1)
    }

    private fun calculateFrequencyArray(binaryStringList: List<String>): IntArray {
        if(binaryStringList.isEmpty()) {
            return intArrayOf()
        }
        val frequencyArray = IntArray(binaryStringList[0].length)

        binaryStringList.forEach{
            for(i in it.indices) {
                frequencyArray[i] += if (it[i] == '1') 1 else -1
            }
        }
        return frequencyArray
    }

    private fun calculateGamma(frequencyArray: IntArray): Int {
        return frequencyArrayToBinaryString(frequencyArray, true).toInt(2)
    }

    private fun calculateEpsilon(frequencyArray: IntArray): Int {
        return frequencyArrayToBinaryString(frequencyArray, false).toInt(2)
    }

    private fun frequencyArrayToBinaryString(frequencyArray: IntArray, positive: Boolean = true): String {
        var binary = ""
        val positiveValue = if(positive) 1 else 0
        val negativeValue = if(positive) 0 else 1

        frequencyArray.forEach {
            val value = if(it > 0) positiveValue else negativeValue
            binary += value.toString()
        }

        return binary
    }


}
