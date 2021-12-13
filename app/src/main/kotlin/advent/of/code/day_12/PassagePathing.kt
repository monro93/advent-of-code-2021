package advent.of.code.day_12

class PassagePathing(
    val validPaths: List<Pair<String, String>>
) {

    fun calculateNumberOfPaths(isPart1: Boolean) : Int {
        val goodPaths: MutableList<List<String>> = mutableListOf()
        getPossiblePaths(isPart1, "start", goodPaths)

        goodPaths.forEach {
            println(it)
        }

        return goodPaths.filter { it.isNotEmpty() }.size
    }

    private fun getPossiblePaths(isPart1: Boolean, point: String, goodPaths: MutableList<List<String>>, visited: List<String> = mutableListOf()): List<String> {
        val newVisited = visited.toMutableList()
        newVisited.add(point)
        if(point == "end") {
            return newVisited.toList()
        }

        val newPaths = validPaths.get(point)
            .filter { newPoint ->
                if(isPart1) {
                    canVisitCave(newVisited, newPoint)
                }else{
                    canVisitCavePt2(newVisited, newPoint)
                }
            }
        newPaths.forEach{ newPoint ->
                goodPaths.add(getPossiblePaths(isPart1, newPoint, goodPaths, newVisited.toList()))
        }
        return listOf()
    }

    private fun canVisitCave(
        newVisited: MutableList<String>,
        newPoint: String,
    ) = !newVisited.contains(newPoint) || newPoint.lowercase() != newPoint

    private fun canVisitCavePt2(
        newVisited: MutableList<String>,
        newPoint: String,
    ): Boolean {
        if(newPoint == "start") {
            return false
        }
        if(newPoint.lowercase() != newPoint) {
            return true
        }
        val lowerCasePoints = newVisited.filter { it.lowercase() == it && it != "start" }

        return lowerCasePoints.distinct().size == lowerCasePoints.size || !lowerCasePoints.contains(newPoint)


    }
    fun List<Pair<String, String>>.get(key: String): List<String>
    {
        return validPaths.filter { it.first == key }.map { it.second} +
                validPaths.filter { it.second == key }.map { it.first }
    }
}
