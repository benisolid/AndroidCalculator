package com.example.calculator

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorEngineTest {
    @Test fun respectsOperatorPrecedence() {
        assertEquals(14.0, CalculatorEngine.evaluate("2+3*4"), 0.000001)
    }
    @Test fun supportsParenthesesAndUnaryMinus() {
        assertEquals(-10.0, CalculatorEngine.evaluate("-(2+3)*2"), 0.000001)
    }
    @Test fun supportsPercent() {
        assertEquals(0.5, CalculatorEngine.evaluate("50%"), 0.000001)
    }
    @Test(expected = IllegalArgumentException::class)
    fun rejectsDivisionByZero() {
        CalculatorEngine.evaluate("8/0")
    }
}
