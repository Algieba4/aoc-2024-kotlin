package day2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DayTwoTest {
    private val dayTwoTest: DayTwoClass = DayTwoClass()

    @Test
    fun testAllAscending() {
        val expected = true
        val array = arrayOf("1", "3", "6", "7", "9")
        assertEquals(expected, dayTwoTest.isReportSafe(array, false))
    }

    @Test
    fun testAllDescending() {
        val expected = true
        val array = arrayOf("7", "6", "4", "2", "1")
        assertEquals(expected, dayTwoTest.isReportSafe(array, false))
    }

    @Test
    fun testGreaterThanThree() {
        val expected = false
        val array = arrayOf("1", "2", "7", "8", "9")
        assertEquals(expected, dayTwoTest.isReportSafe(array, false))
    }

    @Test
    fun testDuplicateNumber() {
        val expected = false
        val array = arrayOf("8", "6", "4", "4", "1")
        assertEquals(expected, dayTwoTest.isReportSafe(array, false))
    }

    @Test
    fun testJumpingAround() {
        val expected = false
        val array = arrayOf("1", "3", "2", "4", "5")
        assertEquals(expected, dayTwoTest.isReportSafe(array, false))
    }

    @Test
    fun testSafeByRemovingSecondIndex() {
        val expected = true
        val array = arrayOf("1", "3", "2", "4", "5")
        assertEquals(expected, dayTwoTest.isReportSafe(array, true))
    }

    @Test
    fun testSafeByRemovingThirdIndex() {
        val expected = true
        val array = arrayOf("8", "6", "4", "4", "1")
        assertEquals(expected, dayTwoTest.isReportSafe(array, true))
    }

    @Test
    fun testUnsafeByRemovingIndex() {
        val expected = false
        val array = arrayOf("1", "2", "7", "8", "9")
        assertEquals(expected, dayTwoTest.isReportSafe(array, true))
    }
}