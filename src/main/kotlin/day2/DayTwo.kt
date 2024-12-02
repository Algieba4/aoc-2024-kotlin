package day2

import java.io.File

fun main() {
    val dayTwoClass = DayTwoClass()
    dayTwoClass.dayTwoPartOne()
//    println()
//    DayTwoClass.dayOnePartTwo()
}

class DayTwoClass {

    private var reports = emptyArray<Array<String>>()

    fun dayTwoPartOne(fileName: String = "C:\\github\\aoc-2024-kotlin\\src\\main\\kotlin\\day2\\DayTwo.txt"): Int {

        var countSafe = 0

        println("Day Two - Part One:")

        readInputFile(fileName)

        for(i in reports.indices ) {
            val report = reports[i]
            if(i == 814) {
                println("breakpoint")
            }
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
        for(i in report.indices) {
            if(previous == -1) {
                ascending = report[i].toInt() < report[i + 1].toInt()
                previous = report[i].toInt()
            } else {
                if((ascending) && (report[i].toInt() > previous) && (report[i].toInt() - previous < 4)) {
                    safe = true
                    previous = report[i].toInt()
                } else if((!ascending) && (report[i].toInt() < previous) && (previous - report[i].toInt() < 4)) {
                    safe = true
                    previous = report[i].toInt()
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