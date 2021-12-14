package advent.of.code.day_14

import advent.of.code.utils.splitByLineBreaks

class Polymerization(
    private val initialPolymerTemplate: String,
    private val rules: Map<String, String>
    ) {

    private var frequencyMap = mutableMapOf<String, Long>()
    private var chunksMap = mutableMapOf<String, Long>()

    fun getFrequencyNumber(steps: Int): Int {
        var polymerization = getPolymerizationString(steps)
        frequencyMap = mutableMapOf()
        while (polymerization.isNotEmpty()) {
            val howMany = polymerization.filter { it == polymerization[0] }.length
            frequencyMap[polymerization[0].toString()] = howMany.toLong()
            polymerization = polymerization.filter { it != polymerization[0] }
        }

        return  frequencyMap.maxOf { it.value.toInt() } - frequencyMap.minOf { it.value.toInt() }
    }

    fun getFrequencyNumberRecursive(steps: Int): Long {
        frequencyMap = mutableMapOf()
        var polymerization = initialPolymerTemplate

        polymerization.forEach {
            frequencyMap[it.toString()] = frequencyMap[it.toString()]?.plus(1L) ?: 1L
        }

        chunksMap = getChunks(polymerization)
            .groupingBy { it }
            .eachCount()
            .mapValues { it.value.toLong() }
            .toMutableMap()

        for (step in 0 until steps) {
            chunksMap.toMap().forEach{ chunk ->
                updateFrequencyMapForChunk(chunk.toPair())
            }

            chunksMap = chunksMap.filter { it.value > 0L }.toMutableMap()
        }

        return  frequencyMap.maxOf { it.value } - frequencyMap.minOf { it.value }
    }

    private fun updateFrequencyMapForChunk(chunk: Pair<String, Long>) {
        val rule = rules[chunk.first]
        if (rule != null) {
            frequencyMap[rule] = frequencyMap[rule]?.plus(chunk.second) ?: chunk.second
            chunksMap[chunk.first[0]+rule] = chunksMap[chunk.first[0]+rule]?.plus(chunk.second) ?: chunk.second
            chunksMap[rule+chunk.first[1]] = chunksMap[rule+chunk.first[1]]?.plus(chunk.second) ?: chunk.second
            chunksMap[chunk.first] = chunksMap[chunk.first]?.minus(chunk.second) ?: 0
        }
    }

    private fun getPolymerizationString(steps: Int): String {
        var polymerization = initialPolymerTemplate

        for (step in 0 until steps) {
            var newPolymerization = polymerization[0].toString()
            val chunks = getChunks(polymerization)
            chunks.forEach{ chunk ->
                val rule = rules[chunk]
                newPolymerization += if (rule != null) {
                    rule + chunk[1]
                }else {
                    chunk[0]
                }
            }
            polymerization = newPolymerization
        }

        return polymerization
    }

    private fun getChunks(string: String): List<String> {
        return (0 until string.length-1).map {
            string.substring(it, it+2)
        }
    }

    companion object {
        fun create(input: String): Polymerization {
            val ruleRegex = """(\w+) -> (\w+)""".toRegex()

            val lines = input.splitByLineBreaks().filter { it.isNotEmpty() }
            val initialPolymerTemplate = lines.first()
            val rules = mutableMapOf<String, String>()

            for (i in 1 until lines.size) {
                val line = lines[i]
                val match = ruleRegex.find(line)

                if(match != null) {
                    rules[match.groups[1]!!.value] = match.groups[2]!!.value
                }
            }

            return Polymerization(
                initialPolymerTemplate,
                rules.toMap()
            )
        }
    }
}
