package day2

import java.io.File

fun main() {
    val dayTwoClass = dayTwoClass()
    dayTwoClass.dayTwoPartOne()
//    println()
//    DayTwoClass.dayOnePartTwo()
}

class dayTwoClass {

    private var reports = emptyArray<Array<String>>()

    fun dayTwoPartOne(fileName: String = "C:\\github\\aoc-2024-kotlin\\src\\main\\kotlin\\day2\\DayTwo.txt"): Int {

        var countSafe = 0

        println("Day Two - Part One:")

        readInputFile(fileName)

        for(i in reports.indices ) {
            val report = reports[i]
            val value = convertArrayToString(report)
            if(evaluateSafety(report)) {
                println("report $i is Safe with values $value")
                countSafe++
            } else {
                println("report $i is Unsafe with values $value")
            }
        }

        println("There are $countSafe safe reports")
        return countSafe
    }

    private fun readInputFile(fileName: String) {
        File(fileName).forEachLine { line ->
            val lineArray = line.split(' ')
            reports = reports.plus(lineArray.toTypedArray())
        }
    }

    fun evaluateSafety(report: Array<String>): Boolean {
        var ascending = false
        var previous = -1
        var safe = false
        for(j in report.indices) {
            if(previous == -1) {
                ascending = report[j] < report[j + 1]
                previous = report[j].toInt()
            } else {
                if((ascending) && (report[j].toInt() > previous) && (report[j].toInt() - previous < 4)) {
                    safe = true
                    previous = report[j].toInt()
                } else if((!ascending) && (report[j].toInt() < previous) && (previous - report[j].toInt() < 4)) {
                    safe = true
                    previous = report[j].toInt()
                } else {
                    safe = false
                    break
                }
            }
        }
        return safe
    }

    private fun convertArrayToString(report: Array<String>): String {
        var value = ""
        for(i in report.indices) {
            if(i == 0) {
                value = report[i]
            } else {
                value += "," + report[i]
            }
        }
        return value
    }

}