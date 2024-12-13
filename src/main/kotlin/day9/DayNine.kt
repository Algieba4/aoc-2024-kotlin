package day9

import java.io.FileReader

fun main() {
    val fileName = "C:\\github\\aoc-2024-kotlin\\src\\main\\kotlin\\day9\\DayNine.txt"
    val dayNineClass = DayNineClass()
    dayNineClass.getCheckSum(fileName)
}

class DayNineClass {

    fun getCheckSum(fileName: String) {


        val diskMap = getDiskMap(fileName)

        var formattedDisk = getFormattedDiskSingle(diskMap)
        var checkSum = getCheckSum(formattedDisk)
        println("Part 1: $checkSum")

        formattedDisk = getFormattedDiskGrouped(diskMap)
        checkSum = getCheckSum(formattedDisk)
        println("Part 2: $checkSum")
    }

    private fun getCheckSum(formattedDisk: ArrayList<String>): Long {
        var checkSum:Long = 0
        for(i in formattedDisk.indices) {
            if(formattedDisk[i] != ".") {
                val digit = formattedDisk[i].toInt()
                checkSum += i * digit
            }
        }
        return checkSum
    }

    private fun getDiskMap(fileName: String): ArrayList<String> {
        val diskMap: ArrayList<String> = ArrayList()
        var value = 0
        var isFileBlock = true

        FileReader(fileName).use { reader ->
            do {
                val char = reader.read()
                val count = Character.getNumericValue(char)
                if (isFileBlock) {
                    for(i in 1..count) {
                        diskMap.add(value.toString())
                    }
                    value++
                    isFileBlock = false
                } else {
                    for(i in 1..count) {
                        diskMap.add(".")
                    }
                    isFileBlock = true
                }
            } while ((char != -1) )

        }
        return diskMap
    }

    private fun getFormattedDiskSingle(diskMap: ArrayList<String>): ArrayList<String> {
        val formattedDisk = diskMap.clone() as ArrayList<String>
        for(i in formattedDisk.size - 1 downTo 0) {
            val value = formattedDisk[i]
            if(value != ".") {
                val freeSpaceIndex = formattedDisk.indexOf(".")
                if(freeSpaceIndex < i) {
                    formattedDisk[freeSpaceIndex] = value
                    formattedDisk[i] = "."
                }
            }
        }
        return formattedDisk
    }

    private fun getFormattedDiskGrouped(diskMap: ArrayList<String>): ArrayList<String> {
        val formattedDisk = diskMap.clone() as ArrayList<String>
        var previousValue = "-1"
        for(i in formattedDisk.size - 1 downTo 0) {
            val value = formattedDisk[i]
            if(value != ".") {
                if(previousValue != value) {
                    val numOccurrences = (formattedDisk.lastIndexOf(value) - formattedDisk.indexOf(value)) + 1
                    var numEmptySpace = 0
                    var firstIndex = -1
                    for(j in formattedDisk.indices) {
                        val newValue = formattedDisk[j]
                        if(newValue == ".") {
                            if(firstIndex == -1) {
                                firstIndex = j
                            }
                            numEmptySpace++
                        } else {
                            if(numEmptySpace >= numOccurrences) {
                                if(i < firstIndex) {
                                    break
                                } else {
                                    for(k in 0 until numOccurrences) {
                                        formattedDisk[firstIndex + k] = value
                                        formattedDisk[i - k] = "."
                                    }
                                    break
                                }
                            }
                            numEmptySpace = 0
                            firstIndex = -1
                        }
                    }
                    previousValue = value
                }
            }
        }
        return formattedDisk
    }

}