package advent.of.code.day_13

import advent.of.code.utils.splitByLineBreaks

class TransparentOrigami(
    listOfDots: List<Pair<Int, Int>>,
    private val folds: List<Pair<String, Int>>,
) {
    private var grid: Array<IntArray>

    init {
        grid = Array(listOfDots.maxOf { it.first+1 }) { IntArray(listOfDots.maxOf { it.second+1 }) { 0 } }
        listOfDots.forEach{
            grid[it.first][it.second] = DOT
        }
    }

    fun foldOrigamiOnce() {
        fold(folds.first())
    }

    fun foldAll() {
        folds.forEach{
            fold(it)
        }
        printGrid()
    }

    private fun fold(fold: Pair<String, Int>) {
        when(fold.first) {
            "x" -> foldVertically(fold.second)
            "y" -> foldHorizontally(fold.second)
        }
    }

    private fun foldHorizontally(line: Int) {
        val newArray = Array(grid.size) { i -> IntArray(line) { j-> grid[i][j]} }
        for (i in newArray.indices) {
            for (j in newArray[i].indices) {
                if(grid[i][grid[0].size-1-j] == DOT) {
                    newArray[i][j] = DOT
                }
            }
        }
        grid = newArray
    }

    private fun foldVertically(line: Int) {
        val newArray = Array(line) { i -> IntArray(grid[0].size) { j-> grid[i][j]} }
        for (i in newArray.indices) {
            for (j in newArray[i].indices) {
                if(grid[grid.size-1-i][j] == DOT) {
                    newArray[i][j] = DOT
                }
            }
        }
        grid = newArray
    }

    fun howManyDots(): Int {
        var dots = 0
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if(grid[i][j] == DOT) {
                    dots += 1
                }
            }
        }
        return dots
    }


    fun printGrid() {
        for (j in grid[0].indices) {
            for (i in grid.indices) {
                if (grid[i][j] == DOT) {
                    print("#")
                } else if (grid[i][j] == EMPTY) {
                    print(".")
                }
            }
            println()
        }
    }

    companion object {
        const val DOT = 1
        const val EMPTY = 0

        fun create(input: String): TransparentOrigami {
            val listOfDots: MutableList<Pair<Int, Int>> = mutableListOf()
            val folds: MutableList<Pair<String, Int>> = mutableListOf()
            val regexpDots = """(\d+),(\d+)""".toRegex()
            val regexpFold = """fold along ([x|y])=(\d+)""".toRegex()
            input
                .splitByLineBreaks()
                .filter { it.isNotEmpty() }
                .forEach { line ->
                    val matchDots = regexpDots.find(line)
                    if (matchDots != null) {
                        listOfDots.add(matchDots.groups[1]!!.value.toInt() to matchDots.groups[2]!!.value.toInt())
                    }

                    val matchFold = regexpFold.find(line)
                    if (matchFold != null) {
                        folds.add(matchFold.groups[1]!!.value to matchFold.groups[2]!!.value.toInt())
                    }
                }

            return TransparentOrigami(
                listOfDots.toList(),
                folds.toList()
            )

        }
    }
}
