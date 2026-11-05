package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.MockData
import com.example.ui.components.BadgeVariant
import com.example.ui.components.ButtonSize
import com.example.ui.components.ButtonVariant
import com.example.ui.components.MasteryRingIndicator
import com.example.ui.components.TnBadge
import com.example.ui.components.TnButton
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.ElectricBlueSubtle

@Composable
fun MicroQuizDialog(
  onQuizCompleted: (newScorePercentage: Int) -> Unit,
  onDismiss: () -> Unit
) {
  val questions = MockData.SAMPLE_QUIZ_QUESTIONS
  var currentQuestionIndex by remember { mutableStateOf(0) }
  var selectedOption by remember { mutableStateOf<Int?>(null) }
  var isAnswerSubmitted by remember { mutableStateOf(false) }
  var correctCount by remember { mutableStateOf(0) }
  var isFinished by remember { mutableStateOf(false) }

  val currentQuestion = questions.getOrNull(currentQuestionIndex)

  Dialog(onDismissRequest = onDismiss) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.surface)
        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(16.dp))
        .padding(16.dp)
        .testTag("micro_quiz_dialog")
    ) {
      if (isFinished) {
        // Quiz Results Summary
        val scorePercent = ((correctCount.toFloat() / questions.size) * 100).toInt()
        Column(
          modifier = Modifier.fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = "Diagnostic Quiz Completed",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(14.dp))
          MasteryRingIndicator(percentage = scorePercent, size = 72.dp, strokeWidth = 6.dp)
          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = "$correctCount of ${questions.size} Competencies Verified",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
          )
          Text(
            text = if (scorePercent >= 70) "Mastery confirmed! Retention decay reset to safe." else "Reinforcement recorded. Spiral review queued.",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
          )

          TnButton(
            text = "Update True North Quotient",
            variant = ButtonVariant.PRIMARY,
            size = ButtonSize.MD,
            onClick = {
              onQuizCompleted(scorePercent)
              onDismiss()
            },
            modifier = Modifier.fillMaxWidth(),
            testTag = "quiz_finish_button"
          )
        }
      } else if (currentQuestion != null) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
        ) {
          // Top Header
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              TnBadge(text = currentQuestion.competencyCode, variant = BadgeVariant.BLUE)
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "${currentQuestionIndex + 1}/${questions.size}",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp,
                fontFamily = FontFamily.Monospace
              )
            }
            IconButton(onClick = onDismiss, modifier = Modifier.testTag("quiz_close_button")) {
              Icon(Icons.Default.Close, contentDescription = "Close", tint = MaterialTheme.colorScheme.onSurfaceVariant)
            }
          }

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = currentQuestion.contextPrompt,
            color = ElectricBlue,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = currentQuestion.questionText,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Options
          currentQuestion.options.forEachIndexed { index, optionText ->
            val isSelected = selectedOption == index
            val isCorrect = index == currentQuestion.correctOptionIndex

            val (bg, border, textCol) = when {
              isAnswerSubmitted && isCorrect -> Triple(ElectricBlueSubtle, ElectricBlue, MaterialTheme.colorScheme.onSurface)
              isAnswerSubmitted && isSelected && !isCorrect -> Triple(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.onSurfaceVariant, MaterialTheme.colorScheme.onSurface)
              isSelected -> Triple(ElectricBlueSubtle, ElectricBlue, MaterialTheme.colorScheme.onSurface)
              else -> Triple(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.outline, MaterialTheme.colorScheme.onSurface)
            }

            Box(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(bg)
                .border(1.dp, border, RoundedCornerShape(8.dp))
                .clickable(enabled = !isAnswerSubmitted) {
                  selectedOption = index
                }
                .padding(12.dp)
                .testTag("quiz_option_$index")
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                  text = ('A' + index) + ".",
                  color = if (isSelected) ElectricBlue else MaterialTheme.colorScheme.onSurfaceVariant,
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold,
                  fontFamily = FontFamily.Monospace,
                  modifier = Modifier.width(24.dp)
                )
                Text(
                  text = optionText,
                  color = textCol,
                  fontSize = 12.sp,
                  modifier = Modifier.weight(1f)
                )
              }
            }
          }

          // Feedback Explanation
          if (isAnswerSubmitted) {
            Spacer(modifier = Modifier.height(10.dp))
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
                .padding(10.dp)
            ) {
              Column {
                Text(
                  text = if (selectedOption == currentQuestion.correctOptionIndex) "Correct! Diagnostic Verified" else "Explanation",
                  color = ElectricBlue,
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                  text = currentQuestion.explanation,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                  fontSize = 11.sp,
                  lineHeight = 15.sp
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // Action Button
          if (!isAnswerSubmitted) {
            TnButton(
              text = "Verify Answer",
              variant = ButtonVariant.PRIMARY,
              size = ButtonSize.MD,
              enabled = selectedOption != null,
              onClick = {
                if (selectedOption != null) {
                  isAnswerSubmitted = true
                  if (selectedOption == currentQuestion.correctOptionIndex) {
                    correctCount++
                  }
                }
              },
              modifier = Modifier.fillMaxWidth(),
              testTag = "quiz_verify_button"
            )
          } else {
            TnButton(
              text = if (currentQuestionIndex < questions.size - 1) "Next Question" else "View Mastery Diagnostic",
              variant = ButtonVariant.PRIMARY,
              size = ButtonSize.MD,
              onClick = {
                if (currentQuestionIndex < questions.size - 1) {
                  currentQuestionIndex++
                  selectedOption = null
                  isAnswerSubmitted = false
                } else {
                  isFinished = true
                }
              },
              modifier = Modifier.fillMaxWidth(),
              testTag = "quiz_next_button"
            )
          }
        }
      }
    }
  }
}
