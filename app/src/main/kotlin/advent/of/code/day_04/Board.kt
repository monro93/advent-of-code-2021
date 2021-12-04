package advent.of.code.day_04

class Board(
    val grid: Array<Array<BoardNumber>>,
) {

    private var won = false

    fun markNumber(number: Int) {
        grid.forEach {
            it.forEach { bn ->
                if (bn.number == number) {
                    bn.mark()
                }
            }
        }
    }

    fun checkHasWonLine(): Boolean {
        won = checkAllRowMarked() || checkAllColumnMarked()
        return won
    }

    fun sumOfUnmarkedNumbers(): Int {
        return grid.fold(0) { value, bn ->
            value + bn.filter { !it.marked }.sumOf { it.number }
        }
    }

    fun hasWon(): Boolean {
        return won
    }

    private fun checkAllRowMarked(): Boolean {
        return grid.any {
            it.all { bn ->
                bn.marked
            }
        }
    }

    private fun checkAllColumnMarked(): Boolean {
        for (i in grid[0].indices) {
            val notMarkedValues = getColumn(i).filter {
                !it.marked
            }
            if (notMarkedValues.isEmpty()) {
                return true
            }
        }
        return false
    }

    private fun getColumn(columnIndex: Int): Array<BoardNumber> {
        return grid.map {
            it[columnIndex]
        }.toTypedArray()
    }

    class BoardNumber(val number: Int) {
        var marked = false

        fun mark() {
            marked = true
        }
    }
}
