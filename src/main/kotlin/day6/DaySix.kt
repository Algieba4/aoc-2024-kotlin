package day6

import java.io.File

fun main() {
    val fileName = "C:\\github\\aoc-2024-kotlin\\src\\main\\kotlin\\day6\\DaySix.txt"
    val daySixClass = DaySixClass()
    daySixClass.getGuardsPath(fileName)
}

class DaySixClass {

    private var guardPath = mutableListOf(mutableListOf<Char>())
    private var guardLocation = intArrayOf() // [row (length), column (width)]
    private var guardPosition = 'N'

    fun getGuardsPath(fileName: String) {

        val floorLayout = getFloorLayout(fileName)

         while(isGuardOnFloor(floorLayout.size, floorLayout[0].size)) {
             moveGuard(floorLayout)
         }

        val distinctMovements = getDistinctGuardMovements(guardPath)
        println("The guard made $distinctMovements distinct moves before leaving the map")
    }

    private fun getFloorLayout(fileName: String): List<CharArray> {

        val map = emptyList<CharArray>().toMutableList()
        var row = 0

        File(fileName).forEachLine { line ->
            val charArray = line.toCharArray()
            for(i in charArray.indices) {
                guardPath.add(row, charArray.toMutableList())
                if(charArray[i] == '^') {
                    guardPath[row][i] = 'X'
                    guardLocation = intArrayOf(row, i)
                } else {
                    guardPath[row][i] = 'O'
                }
            }
            map.add(charArray)
            row++
        }

        return map
    }

    private fun isGuardOnFloor(floorLength: Int, floorWidth: Int): Boolean {
        return !(guardLocation[0] < 0 || guardLocation[0] == floorLength
                || guardLocation[1] < 0 || guardLocation[1] == floorWidth)
    }

    private fun moveGuard(floorLayout: List<CharArray>) {
        if(guardPosition == 'N') {
            if(!isObstructed(floorLayout, guardLocation[0] - 1, guardLocation[1])) {
                guardLocation = intArrayOf(guardLocation[0] - 1, guardLocation[1])
                if(isGuardOnFloor(floorLayout.size, floorLayout[0].size)) {
                    guardPath[guardLocation[0]][guardLocation[1]] = 'X'
                }
            } else {
                guardPosition = 'E'
            }
        } else if(guardPosition == 'E') {
            if(!isObstructed(floorLayout, guardLocation[0], guardLocation[1] + 1)) {
                guardLocation = intArrayOf(guardLocation[0], guardLocation[1] + 1)
                if(isGuardOnFloor(floorLayout.size, floorLayout[0].size)) {
                    guardPath[guardLocation[0]][guardLocation[1]] = 'X'
                }
            } else {
                guardPosition = 'S'
            }
        } else if(guardPosition == 'S') {
            if(!isObstructed(floorLayout, guardLocation[0] + 1, guardLocation[1])) {
                guardLocation = intArrayOf(guardLocation[0] + 1, guardLocation[1])
                if(isGuardOnFloor(floorLayout.size, floorLayout[0].size)) {
                    guardPath[guardLocation[0]][guardLocation[1]] = 'X'
                }
            } else {
                guardPosition = 'W'
            }
        } else if(guardPosition == 'W') {
            if (!isObstructed(floorLayout, guardLocation[0], guardLocation[1] - 1)) {
                guardLocation = intArrayOf(guardLocation[0], guardLocation[1] - 1)
                if (isGuardOnFloor(floorLayout.size, floorLayout[0].size)) {
                    guardPath[guardLocation[0]][guardLocation[1]] = 'X'
                }
            } else {
                guardPosition = 'N'
            }
        }
    }

    private fun isObstructed(floorLayout: List<CharArray>, x: Int, y: Int): Boolean {
        if(x < 0 || x == floorLayout[0].size || y < 0 || y == floorLayout.size ) {
            return false
        }

        return floorLayout[x][y] == '#'
    }

    private fun getDistinctGuardMovements(guardPath: MutableList<MutableList<Char>>): Int {
        var count = 0
        for(row in guardPath.indices) {
            for(col in guardPath[row].indices) {
                if (guardPath[row][col] == 'X') {
                    count++
                }
            }
        }
        return count
    }

}