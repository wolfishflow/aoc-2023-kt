fun main() {
    fun part1(input: List<String>): Int {
        return input.toList().map { string ->
            string.filter { it.digitToIntOrNull() != null }
        }.sumOf { filtered ->
            if (filtered.length > 2) {
                "${filtered.first()}${filtered.last()}".toInt()
            } else if (filtered.length == 1) {
                "${filtered.first()}${filtered.first()}".toInt()
            } else {
                filtered.toInt()
            }
        }
    }

    fun part2(input: List<String>): Int {
        val digits = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9).map { it.toString() }
        val wordDigits = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        val reverseWordDigits = wordDigits.map { it.reversed() }

        val keywords = digits.plus(wordDigits).plus(reverseWordDigits)

        return input.toList().map { string ->
            //println("${string.findAnyOf(keywords)?.second}${string.findLastAnyOf(keywords)?.second}")
            "${string.findAnyOf(keywords)?.second}${string.findLastAnyOf(keywords)?.second}"
        }.sumOf { filtered ->
            val regex = """(one|two|three|four|five|six|seven|eight|nine)""".toRegex()
            val replacedFiltered = regex.replace(filtered) { result ->
                when (result.value) {
                    "one" -> "1"
                    "two" -> "2"
                    "three" -> "3"
                    "four" -> "4"
                    "five" -> "5"
                    "six" -> "6"
                    "seven" -> "7"
                    "eight" -> "8"
                    "nine" -> "9"
                    else -> {
                        "9"
                    }
                }
            }
            if (replacedFiltered.length > 2) {
                "${replacedFiltered.first()}${replacedFiltered.last()}".toInt()
            } else if (replacedFiltered.length == 1) {
                "${replacedFiltered.first()}${replacedFiltered.first()}".toInt()
            } else {
                replacedFiltered.toInt()
            }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val input = readInput("Day01")
    part1(input).println()


    val testInput2 = readInput("Day01_test2")
    check(part2(testInput2) == 281)
    part2(input).println()
}
