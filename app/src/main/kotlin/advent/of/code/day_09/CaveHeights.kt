package advent.of.code.day_09

import advent.of.code.utils.splitByLineBreaks

class CaveHeights {
    fun findRiskLevel(grid: List<List<Int>>): Int {
        var risk = 0

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] < getLowestAdjacent(grid, i, j)) {
                    risk += grid[i][j] + 1
                }
            }
        }

        return risk
    }

    fun findBasinSizes(grid: List<List<Int>>): Int {
        val basinSizes = mutableListOf<Int>()

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] < getLowestAdjacent(grid, i, j)) {
                    basinSizes.add(calculateBazinSize(grid, i, j))
                }
            }
        }

        if (basinSizes.size < 3) {
            return 0
        }

        basinSizes.sortDescending()

        return basinSizes[0] * basinSizes[1] * basinSizes[2]
    }

    private fun calculateBazinSize(
        grid: List<List<Int>>,
        i: Int,
        j: Int,
        visitedPoints: MutableList<Pair<Int, Int>> = mutableListOf(),
    ): Int {
        if (getValue(grid, i, j) == 9 || visitedPoints.contains(i to j)) {
            return 0
        }
        visitedPoints.add(i to j)
        return 1 + calculateBazinSize(grid, i - 1, j, visitedPoints) +
                calculateBazinSize(grid, i + 1, j, visitedPoints) +
                calculateBazinSize(grid, i, j - 1, visitedPoints) +
                calculateBazinSize(grid, i, j + 1, visitedPoints)
    }

    private fun getValue(grid: List<List<Int>>, i: Int, j: Int): Int {
        return if (i < 0 ||
            i >= grid.size ||
            j < 0 ||
            j >= grid[0].size
        ) {
            9
        } else {
            grid[i][j]
        }
    }

    private fun getLowestAdjacent(grid: List<List<Int>>, i: Int, j: Int): Int {
        return listOf(
            getValue(grid, i - 1, j),
            getValue(grid, i + 1, j),
            getValue(grid, i, j - 1),
            getValue(grid, i, j + 1),
        ).minOf { it }
    }

    companion object {
        fun parseInput(input: String): List<List<Int>> {
            return input
                .splitByLineBreaks()
                .filter { it.isNotEmpty() }
                .map {
                    it.map { char ->
                        char.toString().toInt()
                    }
                }
        }
    }
}
