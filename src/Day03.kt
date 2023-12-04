fun main() {

    data class Part(val number: Int, val x: IntRange, val y: Int)
    data class Gear(val x: Int, val y: Int, val surroundingParts: MutableList<Part>)

    fun isSymbolAdjacent(part: Part, grid: List<CharArray>): Boolean {
        part.x.map { column ->
            //check up
            val up = grid.getOrNull(part.y - 1)?.getOrNull(column)
            //check down
            val down = grid.getOrNull(part.y + 1)?.getOrNull(column)
            //check left
            val left = grid.getOrNull(part.y)?.getOrNull(column - 1)
            //check right
            val right = grid.getOrNull(part.y)?.getOrNull(column + 1)


            //check up diagonals
            val upLeft = grid.getOrNull(part.y - 1)?.getOrNull(column - 1)
            val upRight = grid.getOrNull(part.y - 1)?.getOrNull(column + 1)

            //check down diagonals
            val downLeft = grid.getOrNull(part.y + 1)?.getOrNull(column - 1)
            val downRight = grid.getOrNull(part.y + 1)?.getOrNull(column + 1)

            if (up != null && !up.isDigit() && up != '.') {
                return true
            } else if (down != null && !down.isDigit() && down != '.') {
                return true
            } else if (left != null && !left.isDigit() && left != '.') {
                return true
            } else if (right != null && !right.isDigit() && right != '.') {
                return true
            } else if (upLeft != null && !upLeft.isDigit() && upLeft != '.') {
                return true
            } else if (upRight != null && !upRight.isDigit() && upRight != '.') {
                return true
            } else if (downLeft != null && !downLeft.isDigit() && downLeft != '.') {
                return true
            } else if (downRight != null && !downRight.isDigit() && downRight != '.') {
                return true
            }
        }

        return false
    }

    fun part1(input: List<String>): Int {
        //convert input to grid
        val grid = input.map { line ->
            line.toCharArray()
        }

        val parts = mutableListOf<Part>()
        val gear = mutableListOf<Gear>()

        input.mapIndexed { row, line ->
            var number = ""
            val range = mutableSetOf<Int>()
            line.mapIndexed { column, char ->

                if (!char.isDigit()) {
                    if (number.isNotEmpty()) {
                        parts.add(
                            Part(
                                number = number.toInt(),
                                x = IntRange(range.first(), range.last()),
                                y = row
                            )
                        )
                    }
                    number = ""
                    range.clear()
                    if (char != '.') {

                    }
                } else {
                    if (char.isDigit()) {
                        number += char
                        range.add(column)
                    }
                }
            }
            //edge case for a line ending in an number
            if (number.isNotEmpty()) {
                parts.add(
                    Part(
                        number = number.toInt(),
                        x = IntRange(range.first(), range.last()),
                        y = row
                    )
                )

                number = ""
                range.clear()
            }
        }


        val filtered = parts.filter { isSymbolAdjacent(it, grid) }

        return filtered.sumOf { it.number }
    }


    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day03_test")
//    check(part1(testInput) == 4361)

    val input = readInput("Day03")
    part1(input).println()
//    part2(input).println()
}
