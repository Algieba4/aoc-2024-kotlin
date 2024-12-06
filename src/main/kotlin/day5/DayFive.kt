package day5

import java.io.File
import java.util.*

fun main() {
    val fileName = "C:\\github\\aoc-2024-kotlin\\src\\main\\kotlin\\day5\\DayFive.txt"
    val dayFiveClass = DayFiveClass()
    dayFiveClass.getCorrectlyOrderedUpdates(fileName)
}

class DayFiveClass {

    private var pageOrderRulesList = mutableListOf<List<Int>>()
    private var pageOrderingRules = emptyList<Int>()
    private var pages = emptyList<List<String>>()
    private var incorrectPages = mutableListOf<List<String>>()

    fun getCorrectlyOrderedUpdates(fileName: String) {

        getPagesAndPageOrderingRules(fileName)

        println("Ordering rules: $pageOrderRulesList")
        println()
        println("Ordering Format: $pageOrderingRules")
        println()


        var sumOfMiddleNumbers = 0
        for(page in pages.indices) {
            if(isReadyForUpdate(pages[page])) {
                println("Page " + pages[page] + " is ready for update")
                sumOfMiddleNumbers += getMiddleDigit(pages[page])
            } else {
                println("Page " + pages[page] + " is NOT ready for update")
                incorrectPages.add(pages[page])
            }
        }
        println("Sum of middle numbers of correct reports: $sumOfMiddleNumbers")

        println()
        var sumOfCorrectedMiddleNumbers = 0
        for(incorrectPage in incorrectPages.indices) {
            sumOfCorrectedMiddleNumbers += getFixedPageMiddleDigit(incorrectPages[incorrectPage])
        }

        println("Sum of middle numbers of correct reports: $sumOfCorrectedMiddleNumbers")
    }

    private fun getPagesAndPageOrderingRules(fileName: String) {
        var orderingRules = emptyList<Int>()
        File(fileName).forEachLine { line ->
            if(line.contains('|')) {
                val before = line.split('|')[0].toInt()
                val after = line.split('|')[1].toInt()
                orderingRules = setOrderingRules(orderingRules, before, after)
                val tempRuleSet = listOf(before, after)
                pageOrderRulesList.add(tempRuleSet)
            }
            pageOrderingRules = orderingRules
            if(line.isNotEmpty() && !line.contains('|')) {
                pages = pages.plus(listOf(line.split(',')))
            }
        }
    }

    // failed proof of concept
    fun setOrderingRules(orderingRules: List<Int>, before: Int, after: Int): List<Int> {
        val newOrderingRules = orderingRules.toMutableList()

        if(orderingRules.isEmpty()) {
            newOrderingRules.add(before)
            newOrderingRules.add(after)
        } else {
            val beforeIndex = orderingRules.indexOf(before)
            val afterIndex = orderingRules.indexOf(after)

            // Append both to the end
            if(beforeIndex == -1 && afterIndex == -1) {
                newOrderingRules.add(before)
                newOrderingRules.add(after)
            }

            // Place the first element before second element
            if(beforeIndex == -1 && afterIndex != -1) {
                newOrderingRules.add(afterIndex, before)
            }

            // Append second element
            if(beforeIndex != -1 && afterIndex == -1) {
                newOrderingRules.add(after)
            }

            // Determine if the first element needs to move
            if(beforeIndex != -1 && afterIndex != -1) {
                if(beforeIndex > afterIndex) {
                    newOrderingRules.removeAt(beforeIndex)
                    newOrderingRules.add(afterIndex, before)
                }
            }

        }

        return newOrderingRules
    }

    private fun isReadyForUpdate(page: List<String>): Boolean {

        for(i in page.indices) {
            if(i == 0) {
                for(j in pageOrderRulesList.indices) {
                    if(pageOrderRulesList[j][1] == page[i].toInt() && (page.contains(pageOrderRulesList[j][0].toString()))) {
                        return false
                    }
                }
            } else {
                for(j in pageOrderRulesList.indices) {
                    if (pageOrderRulesList[j][0] == page[i].toInt()) {
                        for (k in 0 until i) {
                            if (pageOrderRulesList[j][1] == page[k].toInt()) {
                                return false
                            }
                        }
                    }
                }
            }
        }

        return true
    }

    private fun getFixedPageMiddleDigit(incorrectPage: List<String>): Int {

        var updatedPage = incorrectPage

        while(!isReadyForUpdate(updatedPage)) {
            updatedPage = getFixedInput(updatedPage)
        }

        println("Page $updatedPage is now ready for update")
        return getMiddleDigit(updatedPage)
    }

    fun getMiddleDigit(page: List<String>): Int {
        val size = page.size
        val middle = size / 2
        return page[middle].toInt()
    }

    private fun getFixedInput(page: List<String>): List<String> {
        val tempInput = page.toMutableList()
        for(i in tempInput.indices) {
            if(i == 0) {
                for(j in pageOrderRulesList.indices) {
                    if(pageOrderRulesList[j][1] == page[i].toInt() && (page.contains(pageOrderRulesList[j][0].toString()))) {
                        Collections.swap(tempInput, i, page.indexOf(pageOrderRulesList[j][0].toString()))
                        return tempInput
                    }
                }
            } else {
                for(j in pageOrderRulesList.indices) {
                    if (pageOrderRulesList[j][0] == page[i].toInt()) {
                        for (k in 0 until i) {
                            if (pageOrderRulesList[j][1] == page[k].toInt()) {
                                Collections.swap(tempInput, i, k)
                                return tempInput
                            }
                        }
                    }
                }
            }
        }
        return tempInput
    }

}