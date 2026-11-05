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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import com.example.model.CurriculumStandard
import com.example.ui.components.BadgeVariant
import com.example.ui.components.ButtonSize
import com.example.ui.components.ButtonVariant
import com.example.ui.components.TnBadge
import com.example.ui.components.TnButton
import com.example.ui.components.TnCard
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.PearlWhitePure

@Composable
fun AdminPortalScreen(
  onOpenAiStudio: () -> Unit,
  modifier: Modifier = Modifier
) {
  var isDualStackEnforced by remember { mutableStateOf(true) }
  var isOfflineHubsSyncing by remember { mutableStateOf(false) }

  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 14.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 4 Telemetry Status Cards (2x2 Grid)
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        TnCard(modifier = Modifier.weight(1f)) {
          Column(modifier = Modifier.padding(12.dp)) {
            Text("CURRICULUM ENGINE", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 9.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(2.dp))
            Text("Dual-Stack Active", color = MaterialTheme.colorScheme.onSurface, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Text("MATATAG + 2016 Legacy", color = ElectricBlue, fontSize = 10.sp)
          }
        }

        TnCard(modifier = Modifier.weight(1f)) {
          Column(modifier = Modifier.padding(12.dp)) {
            Text("REGISTERED LEARNERS", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 9.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(2.dp))
            Text("1,482", color = MaterialTheme.colorScheme.onSurface, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
            Text("Across 32 Sections", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)
          }
        }
      }

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        TnCard(modifier = Modifier.weight(1f)) {
          Column(modifier = Modifier.padding(12.dp)) {
            Text("OFFLINE SYNC NODES", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 9.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(2.dp))
            Text("8 Local Servers", color = MaterialTheme.colorScheme.onSurface, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Text("All hubs operational", color = ElectricBlue, fontSize = 10.sp)
          }
        }

        TnCard(modifier = Modifier.weight(1f)) {
          Column(modifier = Modifier.padding(12.dp)) {
            Text("AI GATEWAY STATUS", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 9.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(2.dp))
            Text("Zero Key Exposure", color = MaterialTheme.colorScheme.onSurface, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Text("Protected server proxy", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)
          }
        }
      }
    }

    // Curriculum Version Migration & Configuration Card
    TnCard(modifier = Modifier.fillMaxWidth()) {
      Column(modifier = Modifier.padding(16.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "Curriculum Version Migration & Configuration",
              color = MaterialTheme.colorScheme.onSurface,
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = "Manage DepEd standard versioning across Philippine educational stages",
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              fontSize = 11.sp
            )
          }
          TnButton(
            text = "AI Studio",
            variant = ButtonVariant.SECONDARY,
            size = ButtonSize.SM,
            onClick = onOpenAiStudio,
            testTag = "admin_ai_studio_btn"
          )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Dual Stack Switch Row
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
            .padding(12.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Text("Dual-Stack Runtime Architecture", color = MaterialTheme.colorScheme.onSurface, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Text("Guarantees historical learner records are preserved when transitioning between standards.", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp)
          }
          Switch(
            checked = isDualStackEnforced,
            onCheckedChange = { isDualStackEnforced = it },
            colors = SwitchDefaults.colors(
              checkedThumbColor = PearlWhitePure,
              checkedTrackColor = ElectricBlue,
              uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
              uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            modifier = Modifier.testTag("dual_stack_switch")
          )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Key Stages Config List
        val stageConfigs = listOf(
          Triple("Kindergarten to Grade 10 (K-10)", "Standard: DepEd MATATAG Curriculum (Decongested Framework)", "ACTIVE DEFAULT"),
          Triple("Senior High School (Grades 11 & 12)", "Standard: 2016 Legacy K-12 Specialized Track (STEM, ABM, HUMSS)", "LEGACY COMPATIBLE"),
          Triple("Special Bridged Programs", "Standard: International Baccalaureate & TVL Bridged Competencies", "STANDBY")
        )

        stageConfigs.forEach { (stage, desc, badge) ->
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 4.dp)
              .clip(RoundedCornerShape(8.dp))
              .background(MaterialTheme.colorScheme.surfaceVariant)
              .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
              .padding(12.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column(modifier = Modifier.weight(1f)) {
                Text(stage, color = MaterialTheme.colorScheme.onSurface, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                Text(desc, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp)
              }
              TnBadge(
                text = badge,
                variant = when (badge) {
                  "ACTIVE DEFAULT" -> BadgeVariant.BLUE
                  "LEGACY COMPATIBLE" -> BadgeVariant.SILVER
                  else -> BadgeVariant.WARNING
                }
              )
            }
          }
        }
      }
    }

    // Local School Hub Nodes Manager
    TnCard(modifier = Modifier.fillMaxWidth()) {
      Column(modifier = Modifier.padding(16.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text("School Local Hub Nodes (PWA Sync)", color = MaterialTheme.colorScheme.onSurface, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text("On-Premise LAN caching nodes for remote island campuses", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp)
          }
          TnButton(
            text = if (isOfflineHubsSyncing) "Syncing..." else "Sync All Hubs",
            variant = ButtonVariant.PRIMARY,
            size = ButtonSize.SM,
            onClick = { isOfflineHubsSyncing = true },
            testTag = "admin_sync_hubs_btn"
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        val hubs = listOf(
          Triple("Server-Alpha (Science Lab 1)", "192.168.1.101 • 12ms", "ONLINE (240 Cached Syncs)"),
          Triple("Server-Beta (Junior High Bldg)", "192.168.1.102 • 15ms", "ONLINE (310 Cached Syncs)"),
          Triple("Server-Gamma (Library Micro-Hub)", "192.168.1.103 • 8ms", "ONLINE (185 Cached Syncs)")
        )

        hubs.forEach { (name, ip, status) ->
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(name, color = MaterialTheme.colorScheme.onSurface, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
              Text(ip, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
            }
            TnBadge(text = status, variant = BadgeVariant.SUCCESS)
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))
  }
}
