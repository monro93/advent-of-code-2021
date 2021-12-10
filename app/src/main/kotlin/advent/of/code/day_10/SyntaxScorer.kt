package advent.of.code.day_10

import java.util.Stack

class SyntaxScorer {

    private val scoreCorruptedLines = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137,
    )

    private val scoreIncompleteLines = mapOf(
        ')' to 1,
        ']' to 2,
        '}' to 3,
        '>' to 4,
    )

    fun getScoreOfCorruptedLines(lines: List<String>): Int {
        var score = 0
        lines.forEach{line ->
            val expectedSymbol = Stack<Char>()

            for (i in line.indices) {
                when (val char = line[i]) {
                    '(' -> expectedSymbol.add(')')
                    '[' -> expectedSymbol.add(']')
                    '{' -> expectedSymbol.add('}')
                    '<' -> expectedSymbol.add('>')
                    ')', ']', '}', '>' ->
                        if (expectedSymbol.pop() != char) {
                            score += scoreCorruptedLines[char]!!
                            break
                        }
                }
            }
        }

        return score
    }

    fun getScoreOfIncompleteLines(lines: List<String>): Long {
        var scores = mutableListOf<Long>()
        lines.forEach{line ->
            val expectedSymbol = Stack<Char>()
            var isCorrupted = false
            for (i in line.indices) {
                when (val char = line[i]) {
                    '(' -> expectedSymbol.add(')')
                    '[' -> expectedSymbol.add(']')
                    '{' -> expectedSymbol.add('}')
                    '<' -> expectedSymbol.add('>')
                    ')', ']', '}', '>' ->
                        if (expectedSymbol.pop() != char) {
                            isCorrupted = true
                        }
                }
            }

            if(!isCorrupted) {
                var score = 0L
                while (expectedSymbol.isNotEmpty()) {
                    val char = expectedSymbol.pop()
                    score = score * 5 + scoreIncompleteLines[char]!!
                }

                scores.add(score)
            }
        }

        scores.sort()
        return scores[scores.size / 2]
    }
}
