package day7

import java.io.File

fun main() {
    val fileName = "C:\\github\\aoc-2024-kotlin\\src\\main\\kotlin\\day7\\DaySeven.txt"
    val daySevenClass = DaySevenClass()
    daySevenClass.getCalibrationResult(fileName)
}

class DaySevenClass {

    fun getCalibrationResult(fileName: String): Long {

        var totalSum: Long = 0
        File(fileName).forEachLine { line ->
            val sum = line.split(":")[0].toLong()
            val digits = line.split(":")[1].trimStart().trimEnd().split(" ")
            if(isCalibrated(sum, digits)) {
                totalSum += sum
            }
        }

        println("Calibration Result: $totalSum")
        return totalSum
    }

    private fun isCalibrated(sum: Long, digits: List<String>): Boolean {

        var workingSum: Long = 0
        val operatorList = getOperators(digits)

        for(operators in operatorList.indices) {
           for(operator in operatorList[operators].indices) {
               if(workingSum.toInt() == 0) {
                   workingSum = if(operatorList[operators][operator] == '+') {
                       digits[0].toLong() + digits[1].toLong()
                   } else {
                       digits[0].toLong() * digits[1].toLong()
                   }
               } else {
                   if(operatorList[operators][operator] == '+') {
                       workingSum += digits[operator + 1].toLong()
                   } else {
                       workingSum *= digits[operator + 1].toLong()
                   }
               }
           }
            if(sum == workingSum) {
               //println("sum $sum is calibrated using " + operatorList[operators])
                return true
            }
            //println("sum $sum is not calibrated using " + operatorList[operators] + ". $digits becomes $workingSum")
            workingSum = 0
        }
        return false
    }

    fun getOperators(digits: List<String>): List<List<Char>> {

        val rows = digits.size - 1
        val operatorList = mutableListOf<List<Char>>()
        var paddedCharacters = 0
        var multiplier = 0

        val firstOperator = mutableListOf<Char>()
        for(i in 0 until rows) {
            firstOperator.add('+')
        }
        operatorList.add(firstOperator)

        while(paddedCharacters < rows) {
            val operators = mutableListOf<Char>()
            for(i in 0 until rows) {
                if (i < paddedCharacters) {
                    operators.add('*')
                } else {
                    operators.add('+')
                }
            }
            operators[multiplier] = '*'
            operatorList.add(operators)
            multiplier++
            if (multiplier == rows) {
                paddedCharacters++
                multiplier = paddedCharacters
            }
        }

        return operatorList
    }

}