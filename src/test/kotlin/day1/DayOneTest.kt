package day1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DayOneTest {

    private val dayOneTest: DayOneClass = DayOneClass()

    @Test
    fun testDayOnePartOne() {
     val expected = 11
     assertEquals(expected, dayOneTest.dayOnePartOne("C:\\github\\aoc-2024\\src\\test\\kotlin\\day1\\DayOneTest.txt"))
    }

    @Test
    fun testDayOnePartTwo() {
     val expected = 31
     assertEquals(expected, dayOneTest.dayOnePartTwo("C:\\github\\aoc-2024\\src\\test\\kotlin\\day1\\DayOneTest.txt"))
    }
}
