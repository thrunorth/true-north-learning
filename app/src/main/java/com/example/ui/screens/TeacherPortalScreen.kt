package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MockData
import com.example.model.CohortObservation
import com.example.model.Competency
import com.example.model.CurriculumStandard
import com.example.ui.components.BadgeVariant
import com.example.ui.components.ButtonSize
import com.example.ui.components.ButtonVariant
import com.example.ui.components.TnBadge
import com.example.ui.components.TnButton
import com.example.ui.components.TnCard
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.ElectricBlueSubtle

@Composable
fun TeacherPortalScreen(
  onOpenSf10Export: () -> Unit,
  onOpenAiStudio: () -> Unit,
  modifier: Modifier = Modifier
) {
  var selectedSection by remember { mutableStateOf("Grade 7 - Rizal (42 Students)") }
  var isLoggingObservation by remember { mutableStateOf(false) }
  var newObservationText by remember { mutableStateOf("") }
  val observations = remember { mutableStateListOf(*MockData.OBSERVATIONS.toTypedArray()) }

  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 14.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Action Bar & Section Selector
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        Text(
          text = "Competency Heatmap Matrix",
          color = MaterialTheme.colorScheme.onBackground,
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold
        )
        Text(
          text = selectedSection,
          color = ElectricBlue,
          fontSize = 12.sp,
          fontFamily = FontFamily.Monospace
        )
      }

      Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        TnButton(
          text = "DepEd SF10",
          variant = ButtonVariant.SECONDARY,
          size = ButtonSize.SM,
          onClick = onOpenSf10Export,
          testTag = "teacher_sf10_btn"
        )
        TnButton(
          text = "Log Note",
          variant = ButtonVariant.PRIMARY,
          size = ButtonSize.SM,
          onClick = { isLoggingObservation = !isLoggingObservation },
          testTag = "teacher_log_btn"
        )
      }
    }

    // Section Selector Pills
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      listOf("Grade 7 - Rizal (42 Students)", "Grade 7 - Bonifacio (40 Students)").forEach { sec ->
        val isSelected = sec == selectedSection
        Box(
          modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) ElectricBlueSubtle else MaterialTheme.colorScheme.surfaceVariant)
            .border(
              1.dp,
              if (isSelected) ElectricBlue else MaterialTheme.colorScheme.outline,
              RoundedCornerShape(8.dp)
            )
            .clickable { selectedSection = sec }
            .padding(vertical = 8.dp),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = sec,
            color = if (isSelected) ElectricBlue else MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
          )
        }
      }
    }

    // Log Observation Form (Collapsible)
    if (isLoggingObservation) {
      TnCard(
        modifier = Modifier.fillMaxWidth(),
        highlight = true
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Text(
            text = "Log Formative Cohort Diagnostic Note",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Tagged to DepEd MATATAG Spiral Progression for $selectedSection",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 11.sp
          )
          Spacer(modifier = Modifier.height(8.dp))
          OutlinedTextField(
            value = newObservationText,
            onValueChange = { newObservationText = it },
            placeholder = { Text("Enter cohort observation or intervention directive...", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant) },
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = ElectricBlue,
              unfocusedBorderColor = MaterialTheme.colorScheme.outline,
              focusedTextColor = MaterialTheme.colorScheme.onSurface,
              unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
              focusedContainerColor = MaterialTheme.colorScheme.surface,
              unfocusedContainerColor = MaterialTheme.colorScheme.surface
            ),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("observation_input_field")
          )
          Spacer(modifier = Modifier.height(10.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
          ) {
            TnButton(
              text = "Cancel",
              variant = ButtonVariant.GHOST,
              size = ButtonSize.SM,
              onClick = { isLoggingObservation = false }
            )
            Spacer(modifier = Modifier.width(8.dp))
            TnButton(
              text = "Save Observation",
              variant = ButtonVariant.PRIMARY,
              size = ButtonSize.SM,
              onClick = {
                if (newObservationText.isNotBlank()) {
                  observations.add(
                    0,
                    CohortObservation(
                      id = "obs_${System.currentTimeMillis()}",
                      teacherName = "Prof. Rogelio Santos, LPT",
                      section = selectedSection,
                      date = "Just now",
                      competencyCode = "MATATAG-M7-NS-1",
                      note = newObservationText,
                      recommendation = "Spiral review integrated in next session."
                    )
                  )
                  newObservationText = ""
                  isLoggingObservation = false
                }
              },
              testTag = "save_observation_btn"
            )
          }
        }
      }
    }

    // Competency Heatmap Matrix Cards
    Text(
      text = "DEPED COMPETENCIES HEATMAP",
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      fontSize = 11.sp,
      fontFamily = FontFamily.Monospace,
      fontWeight = FontWeight.Bold,
      letterSpacing = 1.sp
    )

    MockData.COMPETENCIES.forEach { comp ->
      TnCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(14.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Text(
                text = comp.code,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 13.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
              )
              TnBadge(
                text = comp.curriculumVersion.badgeTitle,
                variant = if (comp.curriculumVersion == CurriculumStandard.MATATAG) BadgeVariant.BLUE else BadgeVariant.SILVER
              )
            }

            TnBadge(
              text = if (comp.cohortMasteryPercentage >= 80) "On Target" else "Needs Review",
              variant = if (comp.cohortMasteryPercentage >= 80) BadgeVariant.SUCCESS else BadgeVariant.WARNING
            )
          }

          Spacer(modifier = Modifier.height(4.dp))

          Text(
            text = comp.title,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
          )

          Text(
            text = "Domain: ${comp.domain} • ${comp.description}",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 11.sp,
            lineHeight = 15.sp
          )

          Spacer(modifier = Modifier.height(10.dp))

          // Cohort Mastery Bar
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "Cohort Mastery:",
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              fontSize = 10.sp,
              fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.width(8.dp))
            LinearProgressIndicator(
              progress = { comp.cohortMasteryPercentage / 100f },
              modifier = Modifier
                .weight(1f)
                .height(6.dp)
                .clip(RoundedCornerShape(99.dp)),
              color = if (comp.cohortMasteryPercentage >= 80) ElectricBlue else MaterialTheme.colorScheme.onSurfaceVariant,
              trackColor = MaterialTheme.colorScheme.surfaceVariant,
              strokeCap = StrokeCap.Round
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "${comp.cohortMasteryPercentage}%",
              color = MaterialTheme.colorScheme.onSurface,
              fontSize = 11.sp,
              fontFamily = FontFamily.Monospace,
              fontWeight = FontWeight.Bold
            )
          }
        }
      }
    }

    // Recent Diagnostic Observations Feed
    Text(
      text = "LOGGED COHORT OBSERVATIONS",
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      fontSize = 11.sp,
      fontFamily = FontFamily.Monospace,
      fontWeight = FontWeight.Bold,
      letterSpacing = 1.sp
    )

    observations.take(3).forEach { obs ->
      TnCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(obs.section, color = ElectricBlue, fontSize = 11.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
            Text(obs.date, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
          }
          Spacer(modifier = Modifier.height(4.dp))
          Text(obs.note, color = MaterialTheme.colorScheme.onSurface, fontSize = 12.sp, lineHeight = 16.sp)
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "Directive: ${obs.recommendation}",
            color = ElectricBlue,
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))
  }
}
