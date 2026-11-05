package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.UserRole
import com.example.ui.components.BadgeVariant
import com.example.ui.components.ButtonSize
import com.example.ui.components.ButtonVariant
import com.example.ui.components.ThruNorthHeroBrand
import com.example.ui.components.TnBadge
import com.example.ui.components.TnButton
import com.example.ui.components.TnCard
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.ElectricBlueSubtle

@Composable
fun LandingScreen(
  onEnterPortals: () -> Unit,
  onSelectRole: (UserRole) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 20.dp, vertical = 20.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Spacer(modifier = Modifier.height(10.dp))

    // Brand Hero Logo & Logotype
    ThruNorthHeroBrand(
      logoSize = 110.dp
    )

    Spacer(modifier = Modifier.height(18.dp))

    TnBadge(text = "PHILIPPINE K-12 RE-IMAGINED", variant = BadgeVariant.BLUE)

    Spacer(modifier = Modifier.height(12.dp))

    Text(
      text = "The sovereign learning operating system engineered for Philippine schools. Seamlessly bridging the legacy 2016 K-12 framework and the modern DepEd MATATAG curriculum.",
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      fontSize = 14.sp,
      lineHeight = 22.sp,
      textAlign = TextAlign.Center,
      modifier = Modifier.padding(horizontal = 10.dp)
    )

    Spacer(modifier = Modifier.height(26.dp))

    // Action CTA Row
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      TnButton(
        text = "Enter Portal Cockpit",
        variant = ButtonVariant.PRIMARY,
        size = ButtonSize.LG,
        onClick = onEnterPortals,
        modifier = Modifier.weight(1f),
        testTag = "landing_enter_btn"
      )
      TnButton(
        text = "Teacher Matrix",
        variant = ButtonVariant.OUTLINE,
        size = ButtonSize.LG,
        onClick = { onSelectRole(UserRole.TEACHER) },
        modifier = Modifier.weight(1f),
        testTag = "landing_teacher_btn"
      )
    }

    Spacer(modifier = Modifier.height(30.dp))

    // Quick Portal Launcher Row (Un-bordered, clean modern aesthetic)
    Text(
      text = "SELECT ROLE COCKPIT",
      color = MaterialTheme.colorScheme.onSurface,
      fontSize = 11.sp,
      fontFamily = FontFamily.Monospace,
      fontWeight = FontWeight.Bold,
      letterSpacing = 1.sp
    )

    Spacer(modifier = Modifier.height(10.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      val roles = listOf(UserRole.STUDENT, UserRole.PARENT, UserRole.TEACHER, UserRole.ADMIN)
      roles.forEach { role ->
        Box(
          modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable { onSelectRole(role) }
            .padding(vertical = 12.dp),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = role.label,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(30.dp))

    // Architecture Pillars Cards (01, 02, 03) (Un-bordered, modern layout)
    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      PillarCard(
        number = "01",
        title = "Curriculum Decoupled",
        description = "Curriculum progression is decoupled from student mastery. Supports MATATAG's decongested competencies alongside legacy K-12 records without schema rewrites."
      )
      PillarCard(
        number = "02",
        title = "Offline-Ready Architecture",
        description = "Engineered for Philippine island connectivity conditions. Mastery verification caches locally and reconciles automatically upon reconnection to school micro-hubs."
      )
      PillarCard(
        number = "03",
        title = "Vendor-Neutral AI Layer",
        description = "Inference endpoints run server-side behind unified contracts. Upgrade models from Anthropic to DeepSeek or self-hosted DepEd on-prem LLMs with zero vendor lock-in."
      )
    }

    Spacer(modifier = Modifier.height(30.dp))

    // Bottom Compliance Note (Un-bordered, clean)
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(MaterialTheme.colorScheme.surfaceVariant)
        .padding(14.dp)
    ) {
      Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Text(
          text = "THRU NORTH • STAY AHEAD. BE SMART. © 2026",
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          fontSize = 10.sp,
          fontFamily = FontFamily.Monospace,
          fontWeight = FontWeight.Bold
        )
        Text(
          text = "MATATAG K-10 Ready • Legacy SHS 2016 Compliant • Local-First Architecture",
          color = ElectricBlue,
          fontSize = 10.sp,
          fontFamily = FontFamily.Monospace,
          modifier = Modifier.padding(top = 2.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(20.dp))
  }
}

@Composable
fun PillarCard(
  number: String,
  title: String,
  description: String
) {
  TnCard(
    modifier = Modifier.fillMaxWidth()
  ) {
    Row(
      modifier = Modifier.padding(18.dp),
      verticalAlignment = Alignment.Top
    ) {
      Box(
        modifier = Modifier
          .size(40.dp)
          .clip(RoundedCornerShape(10.dp))
          .background(ElectricBlueSubtle),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = number,
          color = ElectricBlue,
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold,
          fontFamily = FontFamily.Monospace
        )
      }

      Spacer(modifier = Modifier.width(14.dp))

      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = title,
          color = MaterialTheme.colorScheme.onSurface,
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = description,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          fontSize = 12.sp,
          lineHeight = 18.sp
        )
      }
    }
  }
}
