package com.example.calculator

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = viewModel()
) {
    val state = viewModel.state.collectAsState()

    val elements = listOf(
        listOf("AC", "( )", "%", "/"),
        listOf("7", "8", "9", "X"),
        listOf("4", "5", "6", "-"),
        listOf("1", "2", "3", "+")
    )

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 50.dp,
                        bottomEnd = 50.dp
                    )
                )
                .background(MaterialTheme.colorScheme.primaryContainer)
                .weight(1f)
                .padding(bottom = 16.dp, start = 40.dp, end = 40.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End

        ) {
            when (val currentState = state.value) {
                is CalculatorState.Error -> {
                    Text(
                        text = currentState.expression,
                        lineHeight = 36.sp,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.error
                    )

                    Text(
                        text = "",
                        lineHeight = 18.sp,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
                CalculatorState.Initial -> {}
                is CalculatorState.Input -> {
                    Text(
                        text = currentState.expression,
                        lineHeight = 36.sp,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Text(
                        text = currentState.result,
                        lineHeight = 18.sp,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
                is CalculatorState.Success -> {
                    Text(
                        text = currentState.result,
                        lineHeight = 36.sp,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Text(
                        text = "",
                        lineHeight = 18.sp,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (element in listOf("√", "π", "^", "!")) {
                Text(
                    modifier = Modifier.weight(1f)
                        .clickable {
                            when (element) {
                                "√" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.SQRT)) }
                                "π" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.PI)) }
                                "^" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.POWER)) }
                                "!" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.FACTORIAL)) }
                            }
                        },
                    text = element,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }

        elements.forEachIndexed { index, listElements ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (element in listElements) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(CircleShape)
                            .clickable {
                                Log.d("Calculator", "The button $element clicked")
                                when (element) {
                                    "AC" -> { viewModel.processCommand(CalculatorCommand.Clear) }
                                    "( )" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.PARENTHESIS))}
                                    "%" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.PERCENT))}
                                    "/" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIVIDE))}
                                    "X" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.MULTIPLY))}
                                    "-" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.SUBTRACT))}
                                    "+" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.ADD))}
                                    "1" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_1))}
                                    "2" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_2))}
                                    "3" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_3))}
                                    "4" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_4))}
                                    "5" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_5))}
                                    "6" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_6))}
                                    "7" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_7))}
                                    "8" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_8))}
                                    "9" -> { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_9))}

                                }
                            }
                            .background(
                                color = when (element) {
                                    "AC" -> MaterialTheme.colorScheme.secondary
                                    "( )", "%", "/", "X", "-", "+" -> MaterialTheme.colorScheme.tertiary
                                    else -> MaterialTheme.colorScheme.primary
                                }
                            )
                            .aspectRatio(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            textAlign = TextAlign.Center,
                            text = element,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 40.sp
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(2f)
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_0))
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(2f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "0",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DOT))
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = ",",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Evaluate)
                    }
                    .background(MaterialTheme.colorScheme.tertiary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "=",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}