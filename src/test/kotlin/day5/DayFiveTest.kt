package day5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DayFiveTest {
    private val dayFiveTest: DayFiveClass = DayFiveClass()

    @Test
    fun testAppendToEmptyList() {
        val input = emptyList<Int>()
        val expected = listOf(47, 53)
        assertEquals(expected, dayFiveTest.setOrderingRules(input, 47, 53))
    }

    @Test
    fun testAppendBothToList() {
        val input = listOf(47, 53)
        val expected = listOf(47, 53, 97, 13)
        assertEquals(expected, dayFiveTest.setOrderingRules(input, 97, 13))
    }

    @Test
    fun testAppendSecondDigitToList() {
        val input = listOf(47, 53, 97, 13)
        val expected = listOf(47, 53, 97, 13, 61)
        assertEquals(expected, dayFiveTest.setOrderingRules(input, 97, 61))
    }

    @Test
    fun testMoveFirstDigit() {
        val input = listOf(47, 53, 97, 13, 61)
        val expected = listOf(97, 47, 53, 13, 61)
        assertEquals(expected, dayFiveTest.setOrderingRules(input, 97, 47))
    }

    @Test
    fun testNoChange() {
        val input = listOf(97, 47, 75, 53, 61, 29, 13)
        val expected = listOf(97, 47, 75, 53, 61, 29, 13)
        assertEquals(expected, dayFiveTest.setOrderingRules(input, 97, 29))
    }

    @Test
    fun testMiddleDigitOfThree() {
        val input = listOf("75","29","13")
        val expected = 29
        assertEquals(expected, dayFiveTest.getMiddleDigit(input))
    }

    @Test
    fun testMiddleDigitOfFive() {
        val input = listOf("75","47","61","53","29")
        val expected = 61
        assertEquals(expected, dayFiveTest.getMiddleDigit(input))
    }

}