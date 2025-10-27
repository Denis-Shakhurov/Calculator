package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Calculator(
    modifier: Modifier = Modifier
) {
    val elements = listOf(
        listOf("AC", "()", "%", "/"),
        listOf("7", "8", "9", "X"),
        listOf("4", "5", "6", "-"),
        listOf("1", "2", "3", "+")
    )
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End

        ) {
            Text(
                text = "45x8",
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = "360",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                for (element in listOf("√", "π", "^", "!")) {
                    Text(
                        modifier = Modifier.weight(1f),
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
                                .background(
                                    color = when (element) {
                                        "AC" -> MaterialTheme.colorScheme.secondary
                                        "()", "%", "/", "X", "-", "+" -> MaterialTheme.colorScheme.tertiary
                                        else -> MaterialTheme.colorScheme.primary
                                    }
                                )
                                .fillMaxWidth()
                                .weight(1f)
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
                    modifier = Modifier.weight(2f)
                        .background(MaterialTheme.colorScheme.primary)
                        .aspectRatio(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "0",
                        fontSize = 40.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box(
                    modifier = Modifier.weight(1f)
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
                    modifier = Modifier.weight(1f)
                        .background(MaterialTheme.colorScheme.primary)
                        .aspectRatio(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "=",
                        fontSize = 40.sp,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }
}