package day4

import java.io.File

fun main() {
    val fileName = "C:\\github\\aoc-2024-kotlin\\src\\main\\kotlin\\day4\\DayFour.txt"
    val dayFourClass = DayFourClass()
    dayFourClass.getCountOfXmas(fileName)
    dayFourClass.getCountOfMasInXForm(fileName)
}

class DayFourClass {

    fun getCountOfXmas(fileName: String): Int {
        var count = 0
        var tempCharArray: CharArray

        val input = getInput(fileName)
        val rows = input.size
        val columns = input[0].size

        for(row in input.indices) {
            for (column in input[row].indices) {
                if(columns - column > 3) {
                    // Check Horizontal
                    tempCharArray = (charArrayOf(input[row][column], input[row][column + 1], input[row][column + 2], input[row][column + 3]))
                    if(isAMatch(tempCharArray)) {
                        count++
                    }
                    if(rows - row > 3) {
                        // check diagonal down
                        tempCharArray = charArrayOf(input[row][column], input[row + 1][column + 1], input[row + 2][column + 2], input[row + 3][column + 3])
                        if(isAMatch(tempCharArray)) {
                            count++
                        }
                    }
                    if(row > 2) {
                        // check diagonal up
                        tempCharArray = charArrayOf(input[row][column], input[row - 1][column + 1], input[row - 2][column + 2], input[row - 3][column + 3])
                        if(isAMatch(tempCharArray)) {
                            count++
                        }
                    }
                }
                if(rows - row > 3) {
                    // check vertical
                    tempCharArray = charArrayOf(input[row][column], input[row + 1][column], input[row + 2][column], input[row + 3][column])
                    if(isAMatch(tempCharArray)) {
                        count++
                    }
                }
            }
        }

        println("There are $count instances of XMAS in the crossword puzzle")

        return count
    }

    fun getCountOfMasInXForm(fileName: String): Int {
        var count = 0

        val input = getInput(fileName)
        val rows = input.size
        val columns = input[0].size

        for(row in input.indices) {
            for (column in input[row].indices) {
                if((columns - column > 2) && (rows - row > 2)) {
                    val charArrayOne = charArrayOf(input[row][column], input[row + 1][column + 1], input[row + 2][column + 2])
                    val charArrayTwo = charArrayOf(input[row + 2][column], input[row + 1][column + 1], input[row][column + 2])
                    if(isAnX(charArrayOne, charArrayTwo)) {
                        count++
                    }
                }
            }
        }

        println("There are $count instances of the words MAS in the crossword puzzle making an X")
        return count
    }

    private fun getInput(fileName: String): ArrayList<CharArray> {

        val charArrayList = ArrayList<CharArray>()

        File(fileName).forEachLine { line ->
            charArrayList.add(line.toCharArray())
        }

        return charArrayList
    }

    private fun isAMatch(charArray: CharArray): Boolean {
        return charArray.concatToString() == "XMAS" || charArray.reversedArray().concatToString() == "XMAS"
    }

    private fun isAnX(charArrayOne: CharArray, charArrayTwo: CharArray): Boolean {
        return (charArrayOne.concatToString() == "MAS" || charArrayOne.concatToString() == "SAM")
                && (charArrayTwo.concatToString() == "MAS" || charArrayTwo.concatToString() == "SAM")
    }

}