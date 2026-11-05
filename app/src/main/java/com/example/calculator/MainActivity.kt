package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Screen = Color(0xFF101114)
private val Display = Color(0xFF191B20)
private val NumberKey = Color(0xFF292C33)
private val FunctionKey = Color(0xFF3A3E47)
private val Accent = Color(0xFFFF9F0A)
private val MainText = Color(0xFFF7F7F7)
private val MutedText = Color(0xFF9DA3AE)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CalculatorApp() }
    }
}

@Composable
private fun CalculatorApp() {
    var expression by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("0") }
    var justEvaluated by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(false) }

    fun updateResult() {
        if (expression.isBlank()) { result = "0"; error = false; return }
        try {
            result = CalculatorEngine.format(CalculatorEngine.evaluate(expression))
            error = false
        } catch (_: Exception) {
            result = ""
            error = false
        }
    }

    fun press(key: String) {
        when (key) {
            "AC" -> { expression = ""; result = "0"; error = false; justEvaluated = false }
            "DEL" -> {
                expression = expression.dropLast(1)
                justEvaluated = false
                error = false
                updateResult()
            }
            "=" -> {
                try {
                    val value = CalculatorEngine.evaluate(expression)
                    result = CalculatorEngine.format(value)
                    expression = result
                    justEvaluated = true
                    error = false
                } catch (e: Exception) {
                    result = e.message ?: "خطا"
                    error = true
                    justEvaluated = false
                }
            }
            else -> {
                val isOperator = key in listOf("+", "−", "×", "÷")
                if (justEvaluated && !isOperator) expression = ""
                justEvaluated = false
                error = false
                val token = when (key) { "−" -> "-"; "×" -> "*"; "÷" -> "/"; else -> key }
                expression += token
                updateResult()
            }
        }
    }

    val rows = listOf(
        listOf("AC", "DEL", "%", "÷"),
        listOf("7", "8", "9", "×"),
        listOf("4", "5", "6", "−"),
        listOf("1", "2", "3", "+"),
        listOf("(", "0", ".", ")"),
        listOf("=")
    )

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Screen) {
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier.weight(1f).fillMaxWidth()
                        .background(Display, RoundedCornerShape(28.dp)).padding(22.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        Text(expression.ifBlank { " " }, color = MutedText, fontSize = 27.sp,
                            textAlign = TextAlign.End, maxLines = 2, overflow = TextOverflow.Ellipsis)
                        Text(result, color = if (error) Color(0xFFFF6B6B) else MainText,
                            fontSize = if (result.length > 12) 30.sp else 46.sp,
                            fontWeight = FontWeight.Light, textAlign = TextAlign.End,
                            maxLines = 2, overflow = TextOverflow.Ellipsis)
                    }
                }
                rows.forEach { row ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        row.forEach { key ->
                            val isEqual = key == "="
                            val isFunction = key in listOf("AC", "DEL", "%")
                            val isOperator = key in listOf("÷", "×", "−", "+")
                            val background = when {
                                isEqual || isOperator -> Accent
                                isFunction -> FunctionKey
                                else -> NumberKey
                            }
                            Box(
                                modifier = Modifier.weight(if (isEqual) 4f else 1f).height(66.dp)
                                    .background(background, RoundedCornerShape(22.dp))
                                    .clickable { press(key) },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(key, color = MainText,
                                    fontSize = if (key == "AC" || key == "DEL") 19.sp else 25.sp,
                                    fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }
                Text("ماشین‌حساب", modifier = Modifier.fillMaxWidth(), color = MutedText,
                    fontSize = 12.sp, textAlign = TextAlign.Center)
            }
        }
    }
}
