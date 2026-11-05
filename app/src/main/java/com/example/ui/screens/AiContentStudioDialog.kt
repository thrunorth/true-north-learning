package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.example.ui.components.BadgeVariant
import com.example.ui.components.ButtonSize
import com.example.ui.components.ButtonVariant
import com.example.ui.components.TnBadge
import com.example.ui.components.TnButton
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.ElectricBlueSubtle

@Composable
fun AiContentStudioDialog(
  onDismiss: () -> Unit
) {
  var generatedCount by remember { mutableStateOf(1) }

  Dialog(onDismissRequest = onDismiss) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.surface)
        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(16.dp))
        .padding(16.dp)
        .testTag("ai_content_studio_dialog")
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .verticalScroll(rememberScrollState())
      ) {
        // Top Bar
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "AI Content Studio",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
              )
              Spacer(modifier = Modifier.width(8.dp))
              TnBadge(text = "MATATAG ALIGNED", variant = BadgeVariant.BLUE)
            }
            Text(
              text = "DepEd Formative Assessment & LAS Scaffolding",
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              fontSize = 11.sp
            )
          }
          IconButton(onClick = onDismiss, modifier = Modifier.testTag("ai_studio_close")) {
            Icon(Icons.Default.Close, contentDescription = "Close", tint = MaterialTheme.colorScheme.onSurfaceVariant)
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Parameters Overview
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Box(
            modifier = Modifier
              .weight(1f)
              .clip(RoundedCornerShape(8.dp))
              .background(MaterialTheme.colorScheme.surfaceVariant)
              .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
              .padding(10.dp)
          ) {
            Column {
              Text("Target Curriculum", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)
              Text("MATATAG Key Stage 3", color = MaterialTheme.colorScheme.onSurface, fontSize = 12.sp, fontWeight = FontWeight.Bold)
              Text("Grade 7 (Science & Math)", color = ElectricBlue, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
            }
          }

          Box(
            modifier = Modifier
              .weight(1f)
              .clip(RoundedCornerShape(8.dp))
              .background(MaterialTheme.colorScheme.surfaceVariant)
              .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
              .padding(10.dp)
          ) {
            Column {
              Text("Pedagogical Anchor", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)
              Text("Constructivist Spiral", color = MaterialTheme.colorScheme.onSurface, fontSize = 12.sp, fontWeight = FontWeight.Bold)
              Text("Decongested Competency", color = ElectricBlue, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
            }
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Generated Formative Assessment Preview Card
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(ElectricBlueSubtle)
            .border(1.dp, ElectricBlue.copy(alpha = 0.35f), RoundedCornerShape(10.dp))
            .padding(12.dp)
        ) {
          Column {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              TnBadge(text = "GENERATED LAS SPECIMEN #0$generatedCount", variant = BadgeVariant.BLUE)
              Text("DepEd Standard Format", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
              text = "Topic: Real-World Rational Modeling in Filipino Daily Commerce",
              color = MaterialTheme.colorScheme.onSurface,
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
              text = "Task Prompt: 'Mang Tomas bought 12.5 kg of palay at ₱23.50/kg and incurred a ₱150 transport fee on his tricycle. If 2 kg was damp and dried to 1.8 kg, formulate the rational equation representing his net capital per usable kilogram.'",
              color = MaterialTheme.colorScheme.onSurface,
              fontSize = 11.sp,
              lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
              text = "Rubric Criteria: [1] Rational equation structure (40%), [2] Unit consistency (30%), [3] Cultural authenticity (30%).",
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              fontSize = 10.sp,
              fontFamily = FontFamily.Monospace
            )
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Action Buttons
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          TnButton(
            text = "Generate Next LAS",
            variant = ButtonVariant.PRIMARY,
            size = ButtonSize.MD,
            onClick = {
              generatedCount++
            },
            modifier = Modifier.weight(1f),
            testTag = "ai_studio_generate_btn"
          )
          TnButton(
            text = "Done",
            variant = ButtonVariant.SECONDARY,
            size = ButtonSize.MD,
            onClick = onDismiss,
            modifier = Modifier.weight(0.6f),
            testTag = "ai_studio_done_btn"
          )
        }
      }
    }
  }
}
