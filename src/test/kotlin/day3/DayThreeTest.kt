package day3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DayThreeTest {
    private val dayThreeTest: DayThreeClass = DayThreeClass()

    @Test
    fun testValidOneDigit() {
        val expected = listOf("2", "4")
        val input = "mul(2,4)"
        val charArray = input.toCharArray()
        assertEquals(expected, dayThreeTest.retrieveNumbers(charArray, 2))
    }

    @Test
    fun testValidDifferentDigits() {
        val expected = listOf("11", "8")
        val input = "mul(11,8)"
        val charArray = input.toCharArray()
        assertEquals(expected, dayThreeTest.retrieveNumbers(charArray, 2))
    }

    @Test
    fun corruptedInput() {
        val expected = emptyList<String>()
        val input = "mul(32,64]"
        val charArray = input.toCharArray()
        assertEquals(expected, dayThreeTest.retrieveNumbers(charArray, 2))
    }

    @Test
    fun newStatusEnabled() {
        val expected = true
        val input = "do()"
        val charArray = input.toCharArray()
        assertEquals(expected, dayThreeTest.checkConditionalStatus(charArray, 2, false))
    }

    @Test
    fun newStatusDisabled() {
        val expected = false
        val input = "don't()"
        val charArray = input.toCharArray()
        assertEquals(expected, dayThreeTest.checkConditionalStatus(charArray, 2, true))
    }

    @Test
    fun keepExistingStatus() {
        val expected = true
        val input = "dont()"
        val charArray = input.toCharArray()
        assertEquals(expected, dayThreeTest.checkConditionalStatus(charArray, 2, true))
    }

}