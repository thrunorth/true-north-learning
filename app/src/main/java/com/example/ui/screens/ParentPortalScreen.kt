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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.ui.components.BadgeVariant
import com.example.ui.components.ButtonSize
import com.example.ui.components.ButtonVariant
import com.example.ui.components.TnBadge
import com.example.ui.components.TnButton
import com.example.ui.components.TnCard
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.ElectricBlueSubtle

@Composable
fun ParentPortalScreen(
  modifier: Modifier = Modifier
) {
  var showFeedbackSent by remember { mutableStateOf(false) }

  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 14.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Top Overview Cards (3 Cards)
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      TnCard(modifier = Modifier.weight(1f)) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text(
            text = "LEARNER UNDER CARE",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 9.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Juan de la Cruz",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
          )
          Text(
            text = "Grade 7 • Rizal",
            color = ElectricBlue,
            fontSize = 11.sp
          )
        }
      }

      TnCard(modifier = Modifier.weight(1f)) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text(
            text = "DEPED QUARTER EQUIV",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 9.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "92 (Outstanding)",
            color = ElectricBlue,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
          )
          Text(
            text = "Calculated from mastery",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 10.sp
          )
        }
      }

      TnCard(modifier = Modifier.weight(1f)) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text(
            text = "INTERVENTIONS",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 9.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "None Pending",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
          )
          Text(
            text = "Benchmarks verified",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 10.sp
          )
        }
      }
    }

    // Teacher Diagnostic Observations Card
    TnCard(modifier = Modifier.fillMaxWidth()) {
      Column(modifier = Modifier.padding(16.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "Teacher Diagnostic Observations",
              color = MaterialTheme.colorScheme.onSurface,
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = "Recorded by Prof. Rogelio Santos, LPT (Science & Math)",
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              fontSize = 11.sp
            )
          }
          TnBadge(text = "LATEST SYNC", variant = BadgeVariant.SUCCESS)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
            .padding(12.dp)
        ) {
          Column {
            Text(
              text = "\"Juan demonstrates exemplary abstract reasoning in rational number calculations. Under the MATATAG spiral progression, we will introduce cellular biology modeling next week. No additional home interventions required.\"",
              color = MaterialTheme.colorScheme.onSurface,
              fontSize = 12.sp,
              lineHeight = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = "Official DepEd SF9 / Form 138 Quarterly Endorsement",
              color = ElectricBlue,
              fontSize = 10.sp,
              fontFamily = FontFamily.Monospace
            )
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        if (showFeedbackSent) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(8.dp))
              .background(ElectricBlueSubtle)
              .border(1.dp, ElectricBlue.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
              .padding(10.dp)
          ) {
            Text(
              text = "Acknowledgement transmitted to Prof. Rogelio Santos. Status: Delivered.",
              color = ElectricBlue,
              fontSize = 11.sp
            )
          }
          Spacer(modifier = Modifier.height(10.dp))
        }

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          TnButton(
            text = "Acknowledge Observation",
            variant = ButtonVariant.PRIMARY,
            size = ButtonSize.SM,
            onClick = { showFeedbackSent = true },
            modifier = Modifier.weight(1f),
            testTag = "parent_ack_btn"
          )
          TnButton(
            text = "Message Teacher",
            variant = ButtonVariant.SECONDARY,
            size = ButtonSize.SM,
            onClick = { /* Teacher messaging stub */ },
            modifier = Modifier.weight(1f),
            testTag = "parent_msg_btn"
          )
        }
      }
    }

    // Weekly Learning Milestones
    TnCard(modifier = Modifier.fillMaxWidth()) {
      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = "Quarter 1 Mastery Milestones",
          color = MaterialTheme.colorScheme.onSurface,
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold
        )
        Text(
          text = "Verified student progression along the DepEd MATATAG sequence",
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          fontSize = 11.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        val milestones = listOf(
          Triple("Week 1-2", "Foundational Rational Sets (Number and Algebra)", "VERIFIED 94%"),
          Triple("Week 3-4", "Compound Microscopy Lab & Safety Operations", "VERIFIED 88%"),
          Triple("Week 5-6", "Southeast Asian Informational Synthesis (English)", "VERIFIED 91%"),
          Triple("Week 7-8", "Cellular Hierarchy & Organelles Modeling", "IN PROGRESS (68%)")
        )

        milestones.forEach { (week, desc, status) ->
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(week, color = ElectricBlue, fontSize = 10.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
              Text(desc, color = MaterialTheme.colorScheme.onSurface, fontSize = 12.sp)
            }
            TnBadge(
              text = status,
              variant = if (status.contains("VERIFIED")) BadgeVariant.SUCCESS else BadgeVariant.BLUE
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))
  }
}
