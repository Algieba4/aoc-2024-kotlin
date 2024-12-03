package day3

import java.io.File

fun main() {
    val fileName = "C:\\github\\aoc-2024-kotlin\\src\\main\\kotlin\\day3\\DayThree.txt"
    val dayThreeClass = DayThreeClass()
    dayThreeClass.scanInstructions(fileName, false)
    dayThreeClass.scanInstructions(fileName, true)
}

class DayThreeClass {

    fun scanInstructions(fileName: String, considerConditionals: Boolean): Int {

        var enabled = true
        var goodNumbers = emptyList<List<String>>()

        File(fileName).forEachLine { line ->
            val charArray = line.toCharArray()
            val parser = mutableListOf<String>()
            for(i in charArray.indices) {
                if(parser.size < 2) {
                    parser.add(charArray[i].toString())
                }
                else {
                    parser.add(charArray[i].toString())
                    if(parser.joinToString() == "m, u, l" && enabled) {
                        val numbers = retrieveNumbers(charArray, i)
                        if(numbers.isNotEmpty()) {
                            goodNumbers = goodNumbers.plus(listOf(numbers))
                        }
                    } else if(parser.joinToString().startsWith("d, o") && considerConditionals) {
                        enabled = checkConditionalStatus(charArray, i, enabled)
                    }
                    parser.removeAt(0)
                }
            }
        }

        val solution = multipleNumbers(goodNumbers)
        println("Solution: $solution")
        return solution
    }

    fun retrieveNumbers(charArray: CharArray, i: Int): List<String> {

        var numbers = emptyList<String>()

        if(charArray[i + 1].toString() == "(") {

            var count = 2
            var maxCount = count + 4
            var firstNumber = ""
            var secondNumber = ""
            while(count < maxCount) {
                if(charArray[i + count].isDigit()) {
                    firstNumber += charArray[i + count]
                    count++
                } else {
                    break
                }
            }

            if(firstNumber.isNotEmpty() && charArray[i + count].toString() == ",") {
                count++
                maxCount = count + 4
                while(count < maxCount) {
                    if(charArray[i + count].isDigit()) {
                        secondNumber += charArray[i + count]
                        count++
                    } else {
                        break
                    }
                }
            }

            if(secondNumber.isNotEmpty() && charArray[i + count].toString() == ")") {
                numbers = numbers.plus(firstNumber)
                numbers = numbers.plus(secondNumber)
            }
        }

        return numbers
    }

    fun checkConditionalStatus(charArray: CharArray, i: Int, previousStatus: Boolean): Boolean {

        // do() vs don't()
        if (charArray[i] == '(') {
            if(charArray[i + 1] == ')') {
                return true
            }
        } else if (charArray[i] == 'n') {
            if(charArray[i + 1].toString() == "'" && charArray[i + 2] == 't'
                && charArray[i + 3] == '(' && charArray[i + 4] == ')' ) {
                return false
            }
        }
        return previousStatus
    }

    private fun multipleNumbers(goodNumbers: List<List<String>>): Int {
        var sum = 0

        for(i in goodNumbers.indices) {
            val firstNumber = goodNumbers[i][0]
            val secondNumber = goodNumbers[i][1]
            sum += firstNumber.toInt() * secondNumber.toInt()
        }

        return sum
    }
}