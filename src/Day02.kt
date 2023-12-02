fun main() {
    fun part1(input: List<String>): Int {
        var gameIdSum = 0

        val regexGameId = """Game (?<id>\d+)""".toRegex()
        val regexBlue = """(?<number>\d+) blue""".toRegex()
        val regexRed = """(?<number>\d+) red""".toRegex()
        val regexGreen = """(?<number>\d+) green""".toRegex()

        val splitId = input.map {
            it.split(":", ";")
        }

        splitId.map {

            var possible = true
            val stringGameId = it.first()

            it.drop(1).map {
                val blueResult = regexBlue.find(it)
                val blue = blueResult?.groups?.get("number")?.value?.toIntOrNull()

                val redResult = regexRed.find(it)
                val red = redResult?.groups?.get("number")?.value?.toIntOrNull()

                val greenResult = regexGreen.find(it)
                val green = greenResult?.groups?.get("number")?.value?.toIntOrNull()

                if ((red != null && red > 12) || (green != null && green > 13) || (blue != null && blue > 14)) {
                    possible = false
                }

            }

            if (possible) {
                val gameIdResult = regexGameId.find(stringGameId)
                val gameId = gameIdResult?.groups?.get("id")?.value?.toIntOrNull()
                gameId?.let {
                    gameIdSum += it
                }
            }
        }

        return gameIdSum
    }


    fun part2(input: List<String>): Int {

        var sum = 0

        val regexBlue = """(?<number>\d+) blue""".toRegex()
        val regexRed = """(?<number>\d+) red""".toRegex()
        val regexGreen = """(?<number>\d+) green""".toRegex()

        val splitId = input.map {
            it.split(":", ";")
        }

        splitId.map {
            val blueSet = mutableSetOf<Int>()
            val redSet = mutableSetOf<Int>()
            val greenSet = mutableSetOf<Int>()

            it.drop(1).map {
                val blueResult = regexBlue.find(it)
                val blue = blueResult?.groups?.get("number")?.value?.toIntOrNull()

                val redResult = regexRed.find(it)
                val red = redResult?.groups?.get("number")?.value?.toIntOrNull()

                val greenResult = regexGreen.find(it)
                val green = greenResult?.groups?.get("number")?.value?.toIntOrNull()

                blue?.let { blueSet.add(it) }
                red?.let { redSet.add(it) }
                green?.let { greenSet.add(it) }
            }

            sum += (blueSet.maxOrNull() ?: 1) * (redSet.maxOrNull() ?: 1) * (greenSet.maxOrNull() ?: 1)
        }

        return sum
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)

    val input = readInput("Day02")
    part1(input).println()

    val testInput2 = readInput("Day02_test2")
    check(part2(testInput2) == 2286)
    part2(input).println()
}
