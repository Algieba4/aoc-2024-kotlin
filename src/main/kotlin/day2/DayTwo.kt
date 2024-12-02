package day2

import java.io.File

fun main() {
    val fileName = "C:\\github\\aoc-2024-kotlin\\src\\main\\kotlin\\day2\\DayTwo.txt"
    val dayTwoClass = DayTwoClass()
    dayTwoClass.evaluateReports(fileName, false)
    dayTwoClass.evaluateReports(fileName, true)
}

class DayTwoClass {

    private var reports = emptyArray<Array<String>>()

    fun evaluateReports(fileName: String, problemDampener: Boolean): Int {
        var countSafe = 0

        reports = emptyArray<Array<String>>()
        readInputFile(fileName)

        for(i in reports.indices ) {
            val report = reports[i]
            if(isReportSafe(report, problemDampener)) {
                countSafe++
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

    fun isReportSafe(report: Array<String>, problemDampener: Boolean): Boolean {

        if(evaluateReport(report)) {
            return true
        } else if(problemDampener) {
            for(i in report.indices) {
                val newReport = report.toMutableList()
                newReport.removeAt(i)
                if(evaluateReport(newReport.toTypedArray())) {
                    return true
                }
            }
        }

        return false
    }

    private fun evaluateReport(report: Array<String>): Boolean {
        var ascending = false
        var previous = -1

        for(i in report.indices) {
            if(previous == -1) {
                ascending = report[i].toInt() < report[i + 1].toInt()
                previous = report[i].toInt()
            } else {
                if((ascending) && (report[i].toInt() > previous) && (report[i].toInt() - previous < 4)) {
                    previous = report[i].toInt()
                } else if((!ascending) && (report[i].toInt() < previous) && (previous - report[i].toInt() < 4)) {
                    previous = report[i].toInt()
                } else {
                    return false
                }
            }
        }
        return true
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