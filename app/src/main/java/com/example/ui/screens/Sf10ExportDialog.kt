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
fun Sf10ExportDialog(
  onDismiss: () -> Unit
) {
  var isExported by remember { mutableStateOf(false) }

  Dialog(onDismissRequest = onDismiss) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.surface)
        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(16.dp))
        .padding(16.dp)
        .testTag("sf10_export_dialog")
    ) {
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
          Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "DepEd Form 137 / SF10",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Spacer(modifier = Modifier.width(6.dp))
              TnBadge(text = "MATATAG VERIFIED", variant = BadgeVariant.SUCCESS)
            }
            Text(
              text = "Learner's Permanent Academic Record",
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              fontSize = 11.sp
            )
          }
          IconButton(onClick = onDismiss, modifier = Modifier.testTag("sf10_close_button")) {
            Icon(Icons.Default.Close, contentDescription = "Close", tint = MaterialTheme.colorScheme.onSurfaceVariant)
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Official Header Card
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
            .padding(12.dp)
        ) {
          Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("Republic of the Philippines • Department of Education", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
            Text("Makati Science Academic Center (School ID: 301)", color = MaterialTheme.colorScheme.onSurface, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text("Learner: Juan de la Cruz | LRN: 136589200451 | Grade 7 - Rizal", color = ElectricBlue, fontSize = 11.sp)
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Subject Competency Equivalency Matrix Table
        Text("Quarterly Competency Performance", color = MaterialTheme.colorScheme.onSurface, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(6.dp))

        val rows = listOf(
          Triple("Mathematics (MATATAG M7)", "94 (Outstanding)", "PASSED"),
          Triple("Science (MATATAG S7)", "88 (Very Satisfactory)", "PASSED"),
          Triple("English (MATATAG E7)", "91 (Outstanding)", "PASSED"),
          Triple("Makabansa / AP", "93 (Outstanding)", "PASSED")
        )

        Column(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
        ) {
          rows.forEachIndexed { idx, (subj, grade, remarks) ->
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .background(if (idx % 2 == 0) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant)
                .padding(horizontal = 10.dp, vertical = 8.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(subj, color = MaterialTheme.colorScheme.onSurface, fontSize = 11.sp, modifier = Modifier.weight(1.5f))
              Text(grade, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp, modifier = Modifier.weight(1.2f))
              Text(remarks, color = ElectricBlue, fontSize = 10.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
            }
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        if (isExported) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(8.dp))
              .background(ElectricBlueSubtle)
              .border(1.dp, ElectricBlue.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
              .padding(10.dp)
          ) {
            Text(
              text = "DepEd SF10 PDF bundle prepared: SF10_7-Rizal_Juandelacruz.pdf (Ready for DepEd LIS Upload)",
              color = ElectricBlue,
              fontSize = 11.sp
            )
          }
          Spacer(modifier = Modifier.height(12.dp))
        }

        // Actions
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          TnButton(
            text = if (isExported) "Re-Export" else "Generate SF10 / Form 137",
            variant = ButtonVariant.PRIMARY,
            size = ButtonSize.MD,
            onClick = { isExported = true },
            modifier = Modifier.weight(1f),
            testTag = "sf10_generate_btn"
          )
          TnButton(
            text = "Done",
            variant = ButtonVariant.SECONDARY,
            size = ButtonSize.MD,
            onClick = onDismiss,
            modifier = Modifier.weight(0.5f),
            testTag = "sf10_done_btn"
          )
        }
      }
    }
  }
}
