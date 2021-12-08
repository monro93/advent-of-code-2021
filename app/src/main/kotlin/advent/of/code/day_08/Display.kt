package advent.of.code.day_08

import advent.of.code.utils.splitByLineBreaks

class Display() {
    fun interpretReadsPart1(allReads: Map<List<String>, List<String>>): Int {
        var howManyUniqueDigits = 0
        allReads.forEach { displayRead ->
            howManyUniqueDigits += getCombination(displayRead.key, displayRead.value).filter { "1478".contains(it) }.length
        }

        return howManyUniqueDigits
    }

    fun interpretReads(allReads: Map<List<String>, List<String>>): Int {
        var combination = 0
        allReads.forEach { displayRead ->
            combination += getCombination(displayRead.key, displayRead.value).toInt()
        }

        return combination
    }

    private fun getCombination(reads: List<String>, digitReads: List<String>): String {
        var combination = ""
        val codificationKey = getCodificationKey(reads)
        digitReads.forEach { digitRead ->
            val orderedInput = digitRead.toCharArray().sorted().joinToString("")
            if(codificationKey[orderedInput] != null) {
                combination += codificationKey[orderedInput]
            }
        }

        return combination
    }

    private fun getCodificationKey(reads: List<String>): Map<String, Int> {
        val decodedMap = mutableMapOf(
            1 to reads.first() { it.length == 2 },
            4 to reads.first() { it.length == 4 },
            7 to reads.first() { it.length == 3 },
            8 to reads.first() { it.length == 7 },
        )

        //9 has 6 digits and includes number 4
        decodedMap[9] = reads.first() {
            it.length == 6 &&
                    decodedMap[4]!!.all { char -> it.contains(char) }
        }

        //0 has 6 digits and includes number 1 and is not 9
        decodedMap[0] = reads.first() {
            it.length == 6 &&
                    decodedMap[1]!!.all { char -> it.contains(char) } &&
                    !decodedMap.containsValue(it)
        }

        //3 has 5 digits and includes number 7
        decodedMap[3] = reads.first() {
            it.length == 5 &&
                    decodedMap[7]!!.all { char -> it.contains(char) }
        }

        //6 has 6 digits and is not 9 or 0
        decodedMap[6] = reads.first() {
            it.length == 6 &&
                    !decodedMap.containsValue(it)
        }

        //5 has 6 digits and 6 includes 5
        decodedMap[5] = reads.first() {
            it.length == 5 &&
                    it.all { char -> decodedMap[6]!!.contains(char) } &&
                    !decodedMap.containsValue(it)
        }

        //2 is the last one
        decodedMap[2] = reads.first() {
            it.length == 5 &&
                    !decodedMap.containsValue(it)
        }

        return decodedMap.entries.associateBy({
            it.value
                .toCharArray()
                .sorted()
                .joinToString("") }
        ) { it.key }
    }

    companion object {
        fun transformReads(reads: String): Map<List<String>, List<String>> {
            val allReads = mutableMapOf<List<String>, List<String>>()
            val regexp =
                """(\w+) (\w+) (\w+) (\w+) (\w+) (\w+) (\w+) (\w+) (\w+) (\w+) \|\s(\w+) (\w+) (\w+) (\w+)""".toRegex()
            reads
                .splitByLineBreaks()
                .forEach { line ->
                    val match = regexp.find(line)
                    val lineRead = (
                            listOf(
                                match!!.groups[1]!!.value,
                                match.groups[2]!!.value,
                                match.groups[3]!!.value,
                                match.groups[4]!!.value,
                                match.groups[5]!!.value,
                                match.groups[6]!!.value,
                                match.groups[7]!!.value,
                                match.groups[8]!!.value,
                                match.groups[9]!!.value,
                                match.groups[10]!!.value,
                            ) to listOf(
                                match.groups[11]!!.value,
                                match.groups[12]!!.value,
                                match.groups[13]!!.value,
                                match.groups[14]!!.value,
                            ))

                    allReads[lineRead.first] = lineRead.second
                }
            return allReads
        }
    }
}
