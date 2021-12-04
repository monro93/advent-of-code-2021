package advent.of.code.day_04

import advent.of.code.utils.splitByLineBreaks

class Bingo(
    private val numbersOrders: List<Int>,
    private val boards: List<Board>,
) {

    fun playUntilSomeOneWinsLine(): WinnerResult? {
        numbersOrders.forEach { number ->
            boards.forEach { board ->
                board.markNumber(number)
                if (board.checkHasWonLine()) {
                    return WinnerResult(number, board)
                }
            }
        }
        return null
    }

    fun playUntilLastWins(): WinnerResult? {
        numbersOrders.forEach { number ->
            boards.forEach { board ->
                board.markNumber(number)
                if (board.checkHasWonLine()) {
                    if (boards.filter { !it.hasWon() }.isEmpty()) {
                        return WinnerResult(number, board)
                    }
                }

            }
        }
        return null
    }


    class WinnerResult(
        val number: Int,
        val board: Board,
    )

    companion object {
        val boardRegexp = """((\s+\d+\s*){5}\n){5}""".toRegex()

        fun fromStringInput(input: String): Bingo {
            val lines = input.lines()
            val numbers = lines.first().split(",").map { it.toInt() }
            val boards = mutableListOf<Board>()

            val matches = boardRegexp.findAll(input)

            matches.forEach { result ->
                val grid = mutableListOf<Array<Board.BoardNumber>>()
                result.value
                    .splitByLineBreaks()
                    .filter { it.isNotEmpty() }
                    .forEach { line ->
                        val numbersArray = line.trim().split("""\s+""".toRegex())
                            .map { Board.BoardNumber(it.toInt()) }
                            .toTypedArray()
                        grid.add(numbersArray)
                    }

                boards.add(Board(grid.toTypedArray()))
            }

            return Bingo(
                numbers,
                boards
            )
        }
    }
}
