package day1

import java.io.File
import kotlin.math.abs

fun main() {
    val dayOneClass = DayOneClass()
    dayOneClass.dayOnePartOne()
    println()
    dayOneClass.dayOnePartTwo()
}

class DayOneClass {
    private var leftNumbers = emptyList<Int>()
    private var rightNumbers = emptyList<Int>()

    fun dayOnePartOne(fileName: String = "C:\\github\\aoc-2024\\src\\main\\kotlin\\day1\\DayOne.txt"): Int {

        println("Day One - Part One:")

        readInputFile(fileName)

        val leftNumbersSorted = leftNumbers.sortedDescending().asReversed()
        val rightNumbersSorted = rightNumbers.sortedDescending().asReversed()

        println("leftNumbers = $leftNumbersSorted")
        println("rightNumbers = $rightNumbersSorted")

        var totalDistance = 0

        for(i in leftNumbersSorted.indices){
            totalDistance += abs(leftNumbersSorted[i] - rightNumbersSorted[i])
        }

        println("Total distance $totalDistance")

        return totalDistance
    }

    fun dayOnePartTwo(fileName: String = "C:\\github\\aoc-2024\\src\\main\\kotlin\\day1\\DayOne.txt"): Int {

        var leftNumbersSimilarity = emptyList<Int>()
        leftNumbers = emptyList()
        rightNumbers = emptyList()

        println("Day One - Part Two:")

        readInputFile(fileName)

        for(i in leftNumbers.indices){
            var count = 0
            rightNumbers.forEach { rightNumber ->
                if (rightNumber == leftNumbers[i]) {
                    count++
                }
            }
            leftNumbersSimilarity = leftNumbersSimilarity.plus(leftNumbers[i] * count)
        }

        println("leftNumbersSimilarity = $leftNumbersSimilarity")

        var similarityScore = 0

        for(i in leftNumbersSimilarity.indices){
            similarityScore += leftNumbersSimilarity[i]
        }

        println("similarityScore = $similarityScore")

        return similarityScore
    }

    private fun readInputFile(fileName: String) {
        File(fileName).forEachLine{ line ->
            if (fileName.contains("DayOneTest.txt")) {
                leftNumbers = leftNumbers.plus(line.substring(0,1).toInt())
                rightNumbers = rightNumbers.plus(line.substring(4,5).toInt())
            } else {
                leftNumbers = leftNumbers.plus(line.substring(0, 5).toInt())
                rightNumbers = rightNumbers.plus(line.substring(8, 13).toInt())
            }
        }
    }
}