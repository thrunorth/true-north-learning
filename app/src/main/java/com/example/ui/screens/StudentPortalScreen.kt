package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.StudentMasteryItem
import com.example.ui.components.BadgeVariant
import com.example.ui.components.ButtonSize
import com.example.ui.components.ButtonVariant
import com.example.ui.components.MasteryRingIndicator
import com.example.ui.components.TnBadge
import com.example.ui.components.TnButton
import com.example.ui.components.TnCard
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.ElectricBlueSubtle

@Composable
fun StudentPortalScreen(
  masteryList: List<StudentMasteryItem>,
  overallQuotient: Float,
  onLaunchMicroQuiz: () -> Unit,
  onOpenAiTutor: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 14.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // True North Quotient & Metric Card
    TnCard(
      modifier = Modifier.fillMaxWidth(),
      highlight = true
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "TRUE NORTH QUOTIENT",
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              fontSize = 11.sp,
              fontFamily = FontFamily.Monospace,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.Bottom) {
              Text(
                text = "${"%.1f".format(overallQuotient)}%",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (-0.5).sp
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = "Overall MATATAG Mastery",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 4.dp)
              )
            }
          }

          Box(
            modifier = Modifier
              .size(44.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(ElectricBlueSubtle)
              .border(1.dp, ElectricBlue.copy(alpha = 0.35f), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "Q1",
              color = ElectricBlue,
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Monospace
            )
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LinearProgressIndicator(
          progress = { overallQuotient / 100f },
          modifier = Modifier
            .fillMaxWidth()
            .height(7.dp)
            .clip(RoundedCornerShape(99.dp)),
          color = ElectricBlue,
          trackColor = MaterialTheme.colorScheme.surfaceVariant,
          strokeCap = StrokeCap.Round,
        )
      }
    }

    // Diagnostic Retention Notice
    TnCard(
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier.padding(14.dp),
        verticalAlignment = Alignment.Top
      ) {
        Box(
          modifier = Modifier
            .size(8.dp)
            .clip(RoundedCornerShape(99.dp))
            .background(MaterialTheme.colorScheme.onSurfaceVariant)
            .padding(top = 4.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "DIAGNOSTIC NOTICE: RETENTION DECAY ALERT",
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              fontSize = 10.sp,
              fontFamily = FontFamily.Monospace,
              fontWeight = FontWeight.Bold
            )
            TnBadge(text = "7-DAY DECAY", variant = BadgeVariant.WARNING)
          }
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Your retention vector in Microscopy & Cell Organization indicates potential cognitive drop without active spiral practice.",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp,
            lineHeight = 17.sp
          )
          Spacer(modifier = Modifier.height(10.dp))
          TnButton(
            text = "Run 5-Min Micro Quiz",
            variant = ButtonVariant.PRIMARY,
            size = ButtonSize.SM,
            onClick = onLaunchMicroQuiz,
            testTag = "diagnostic_quiz_button"
          )
        }
      }
    }

    // Active Learning Vector Title
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        Text(
          text = "Active Learning Vector",
          color = MaterialTheme.colorScheme.onBackground,
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold
        )
        Text(
          text = "DepEd MATATAG competencies currently engaged for Grade 7",
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          fontSize = 11.sp
        )
      }
      TnButton(
        text = "Socratic Tutor",
        variant = ButtonVariant.OUTLINE,
        size = ButtonSize.SM,
        onClick = onOpenAiTutor,
        testTag = "student_socratic_btn"
      )
    }

    // Active Competency Cards
    masteryList.forEach { item ->
      val isNeedsWork = item.score < 0.75f
      TnCard(
        modifier = Modifier.fillMaxWidth(),
        highlight = isNeedsWork
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
              ) {
                TnBadge(
                  text = item.status,
                  variant = if (item.score >= 0.8f) BadgeVariant.SUCCESS else BadgeVariant.BLUE
                )
                Text(
                  text = item.code,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                  fontSize = 11.sp,
                  fontFamily = FontFamily.Monospace
                )
              }

              Spacer(modifier = Modifier.height(6.dp))

              Text(
                text = item.title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
              )

              Spacer(modifier = Modifier.height(2.dp))

              Text(
                text = "Subject: ${item.subject} • ${item.description}",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp,
                lineHeight = 15.sp
              )
            }

            Spacer(modifier = Modifier.width(12.dp))

            MasteryRingIndicator(
              percentage = (item.score * 100).toInt(),
              size = 54.dp
            )
          }

          Spacer(modifier = Modifier.height(12.dp))

          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(1.dp)
              .background(MaterialTheme.colorScheme.outline)
          )

          Spacer(modifier = Modifier.height(10.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "Last verified: ${item.lastAssessed}",
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              fontSize = 10.sp,
              fontFamily = FontFamily.Monospace
            )

            TnButton(
              text = if (isNeedsWork) "Reinforce Competency" else "Deepen Knowledge",
              variant = if (isNeedsWork) ButtonVariant.PRIMARY else ButtonVariant.SECONDARY,
              size = ButtonSize.SM,
              onClick = onLaunchMicroQuiz,
              testTag = "reinforce_btn_${item.competencyId}"
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))
  }
}
