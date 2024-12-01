package day1

import java.io.File
import kotlin.math.abs

var leftNumbers = emptyList<Int>()
var rightNumbers = emptyList<Int>()

fun dayOnePartOne(fileName: String = "C:\\github\\aoc-2024\\src\\main\\kotlin\\day1\\DayOne.txt"): Int {

    println("Day One - Part One:")

    readInputFile(fileName)

    var leftNumbersSorted = leftNumbers.sortedDescending().asReversed()
    var rightNumbersSorted = rightNumbers.sortedDescending().asReversed()

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
    leftNumbers = emptyList<Int>()
    rightNumbers = emptyList<Int>()

    println("Day One - Part Two:")

    readInputFile(fileName)

    for(i in leftNumbers.indices){
        var count = 0;
        rightNumbers.forEach { rightNumber ->
            if (rightNumber.equals(leftNumbers[i])) {
                count++
            }
        }
        leftNumbersSimilarity = leftNumbersSimilarity.plus(leftNumbers[i] * count)
    }

    println("leftNumbersSimilarity = $leftNumbersSimilarity")

    var similartiyScore = 0

    for(i in leftNumbersSimilarity.indices){
        similartiyScore += leftNumbersSimilarity[i]
    }

    println("similartyScore = $similartiyScore")

    return similartiyScore

}

fun readInputFile(fileName: String) {
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

fun main() {
    dayOnePartOne()
    println()
    dayOnePartTwo()
}