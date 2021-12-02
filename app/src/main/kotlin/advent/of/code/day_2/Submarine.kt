package advent.of.code.day_2

class Submarine(
    var horizontalPosition: Int = 0,
    var depth: Int = 0,
    var aim: Int = 0
) {
    companion object {
        val instructionRegexp = """(\w+) (\d+)""".toRegex()
    }

    fun moveAll(instructions: List<String>) {
        instructions.forEach {
            move(it)
        }
    }

    fun move(instruction: String) {
        val match = instructionRegexp.find(instruction)

        val instructionName = match?.groups?.get(1)?.value
        val instructionValue = match?.groups?.get(2)?.value?.toInt() ?: 0
        when (instructionName) {
            "forward" -> horizontalPosition += instructionValue
            "down" -> depth += instructionValue
            "up" -> depth -= instructionValue
        }
    }

    fun moveWithAimingAll(instructions: List<String>) {
        instructions.forEach {
            moveWithAiming(it)
        }
    }

    fun moveWithAiming(instruction: String) {
        val match = instructionRegexp.find(instruction)

        val instructionName = match?.groups?.get(1)?.value
        val instructionValue = match?.groups?.get(2)?.value?.toInt() ?: 0
        when (instructionName) {
            "forward" -> {
                horizontalPosition += instructionValue
                depth += aim * instructionValue
            }
            "down" -> aim += instructionValue
            "up" -> aim -= instructionValue
        }
    }


}
