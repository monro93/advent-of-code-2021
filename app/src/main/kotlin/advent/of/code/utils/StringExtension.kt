package advent.of.code.utils

fun String.splitByLineBreaks(): List<String> = this.split("\n").map { it.trimIndent() }
