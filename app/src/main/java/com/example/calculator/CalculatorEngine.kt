package com.example.calculator

import kotlin.math.abs

/**
 * Recursive-descent evaluator with conventional operator precedence.
 * expression -> term (('+' | '-') term)*
 * term       -> unary (('*' | '/') unary)*
 * unary      -> ('+' | '-') unary | postfix
 * postfix    -> primary ('%')*
 * primary    -> number | '(' expression ')'
 */
object CalculatorEngine {
    fun evaluate(expression: String): Double {
        val parser = Parser(expression)
        val result = parser.parseExpression()
        parser.skipSpaces()
        require(parser.atEnd()) { "عبارت نامعتبر است" }
        require(result.isFinite()) { "نتیجه قابل محاسبه نیست" }
        return if (abs(result) < 1e-12) 0.0 else result
    }

    fun format(value: Double): String {
        if (!value.isFinite()) return "خطا"
        val normalized = if (abs(value) < 1e-10) 0.0 else value
        return if (normalized == normalized.toLong().toDouble()) {
            normalized.toLong().toString()
        } else {
            normalized.toBigDecimal().stripTrailingZeros().toPlainString()
        }
    }

    private class Parser(private val input: String) {
        private var index = 0
        fun atEnd() = index >= input.length
        fun skipSpaces() { while (!atEnd() && input[index].isWhitespace()) index++ }

        fun parseExpression(): Double {
            var value = parseTerm()
            while (true) {
                skipSpaces()
                value = when {
                    consume('+') -> value + parseTerm()
                    consume('-') -> value - parseTerm()
                    else -> return value
                }
            }
        }

        private fun parseTerm(): Double {
            var value = parseUnary()
            while (true) {
                skipSpaces()
                when {
                    consume('*') -> value *= parseUnary()
                    consume('/') -> {
                        val divisor = parseUnary()
                        require(abs(divisor) > 1e-15) { "تقسیم بر صفر مجاز نیست" }
                        value /= divisor
                    }
                    else -> return value
                }
            }
        }

        private fun parseUnary(): Double {
            skipSpaces()
            return when {
                consume('+') -> parseUnary()
                consume('-') -> -parseUnary()
                else -> parsePostfix()
            }
        }

        private fun parsePostfix(): Double {
            var value = parsePrimary()
            skipSpaces()
            while (consume('%')) {
                value /= 100.0
                skipSpaces()
            }
            return value
        }

        private fun parsePrimary(): Double {
            skipSpaces()
            if (consume('(')) {
                val value = parseExpression()
                skipSpaces()
                require(consume(')')) { "پرانتز بسته نشده است" }
                return value
            }
            val start = index
            var hasDigit = false
            while (!atEnd() && input[index].isDigit()) { index++; hasDigit = true }
            if (!atEnd() && input[index] == '.') {
                index++
                while (!atEnd() && input[index].isDigit()) { index++; hasDigit = true }
            }
            require(hasDigit) { "عدد نامعتبر است" }
            return input.substring(start, index).toDouble()
        }

        private fun consume(char: Char): Boolean {
            if (!atEnd() && input[index] == char) { index++; return true }
            return false
        }
    }
}
