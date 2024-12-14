package day10

import java.io.File

fun main() {
    val fileName = "C:\\github\\aoc-2024-kotlin\\src\\main\\kotlin\\day10\\DayTen.txt"
    val dayTenClass = DayTenClass()
    dayTenClass.getSumOfTrailheadScores(fileName)
}

class DayTenClass {

    fun getSumOfTrailheadScores(fileName: String): Int {

        val topographicMap = getTopographicMap(fileName)
        val trailheadScores = getTrailheadScores(topographicMap)

        var sum = 0
        for(trailhead in 0 until trailheadScores.size) {
            sum += trailheadScores.getValue(trailhead)
        }

        println("sum: $sum")
        return sum;
    }

    private fun getTopographicMap(fileName: String): List<List<Int>> {
        val topographicMap = mutableListOf<List<Int>>()
        File(fileName).forEachLine { line ->
            val heights = line.toCharArray().map { Character.getNumericValue(it) }
            var topographicLine = emptyList<Int>()
            for(height in heights) {
                topographicLine = topographicLine.plus(height)
            }
            topographicMap.add(topographicLine)
        }
        return topographicMap;
    }

    private fun getTrailheadScores(topographicMap: List<List<Int>>): Map<Int, Int> {
        val trailheadScores = emptyMap<Int, Int>().toMutableMap()
        var trailhead = 0

        for(row in topographicMap.indices) {
            for(column in topographicMap[row].indices) {
                if(topographicMap[row][column] == 0) {
                    val trailheadScore = getTrailheadScore(topographicMap, row, column)
                    if(trailheadScore > 0) {
                        trailheadScores[trailhead] = trailheadScore
                        trailhead++
                    }
                }
            }
        }
        return trailheadScores
    }

    private fun getTrailheadScore(topographicMap: List<List<Int>>, row: Int, column: Int): Int {
        var trailheadScore = 0

        //TODO: Keep tab of the ending 9 position (row & column). Score should only increase if they are unique

        trailheadScore++

        return trailheadScore
    }

    private fun isIncrementing(initialValue: Int, nextValue: Int): Boolean {
        return (initialValue + 1) == nextValue
    }

}