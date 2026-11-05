package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.UserRole
import com.example.ui.theme.AcrylicBlack
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.ElectricBlueSubtle
import com.example.ui.theme.LocalIsDarkTheme
import com.example.ui.theme.PearlWhitePure
import com.example.ui.theme.PearlWhiteTranslucent

enum class ButtonVariant {
  PRIMARY, SECONDARY, GHOST, OUTLINE
}

enum class ButtonSize {
  SM, MD, LG
}

@Composable
fun TnButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  variant: ButtonVariant = ButtonVariant.PRIMARY,
  size: ButtonSize = ButtonSize.MD,
  leadingIcon: (@Composable () -> Unit)? = null,
  enabled: Boolean = true,
  testTag: String = "tn_button"
) {
  val contentPadding = when (size) {
    ButtonSize.SM -> PaddingValues(horizontal = 14.dp, vertical = 7.dp)
    ButtonSize.MD -> PaddingValues(horizontal = 18.dp, vertical = 11.dp)
    ButtonSize.LG -> PaddingValues(horizontal = 24.dp, vertical = 14.dp)
  }

  val fontSize = when (size) {
    ButtonSize.SM -> 12.sp
    ButtonSize.MD -> 13.sp
    ButtonSize.LG -> 15.sp
  }

  val shape = RoundedCornerShape(10.dp)

  when (variant) {
    ButtonVariant.PRIMARY -> {
      // Strictly Electric Blue (#0052FF) with pearl white text
      Button(
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        contentPadding = contentPadding,
        colors = ButtonDefaults.buttonColors(
          containerColor = ElectricBlue,
          contentColor = PearlWhitePure,
          disabledContainerColor = ElectricBlue.copy(alpha = 0.4f),
          disabledContentColor = PearlWhitePure.copy(alpha = 0.6f)
        ),
        modifier = modifier.testTag(testTag)
      ) {
        if (leadingIcon != null) {
          leadingIcon()
          Spacer(modifier = Modifier.width(6.dp))
        }
        Text(text = text, fontSize = fontSize, fontWeight = FontWeight.Bold, letterSpacing = 0.2.sp)
      }
    }
    ButtonVariant.SECONDARY -> {
      Button(
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        contentPadding = contentPadding,
        colors = ButtonDefaults.buttonColors(
          containerColor = MaterialTheme.colorScheme.surfaceVariant,
          contentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = modifier.testTag(testTag)
      ) {
        if (leadingIcon != null) {
          leadingIcon()
          Spacer(modifier = Modifier.width(6.dp))
        }
        Text(text = text, fontSize = fontSize, fontWeight = FontWeight.SemiBold)
      }
    }
    ButtonVariant.OUTLINE -> {
      OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        contentPadding = contentPadding,
        colors = ButtonDefaults.outlinedButtonColors(
          contentColor = MaterialTheme.colorScheme.onSurface
        ),
        border = ButtonDefaults.outlinedButtonBorder.copy(
          brush = SolidColor(MaterialTheme.colorScheme.outline)
        ),
        modifier = modifier.testTag(testTag)
      ) {
        if (leadingIcon != null) {
          leadingIcon()
          Spacer(modifier = Modifier.width(6.dp))
        }
        Text(text = text, fontSize = fontSize, fontWeight = FontWeight.SemiBold)
      }
    }
    ButtonVariant.GHOST -> {
      TextButton(
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        contentPadding = contentPadding,
        colors = ButtonDefaults.textButtonColors(
          contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        modifier = modifier.testTag(testTag)
      ) {
        if (leadingIcon != null) {
          leadingIcon()
          Spacer(modifier = Modifier.width(6.dp))
        }
        Text(text = text, fontSize = fontSize, fontWeight = FontWeight.Medium)
      }
    }
  }
}

/**
 * Layered translucent light pearl surface (or deep acrylic black in dark mode)
 */
@Composable
fun TnCard(
  modifier: Modifier = Modifier,
  highlight: Boolean = false,
  onClick: (() -> Unit)? = null,
  content: @Composable () -> Unit
) {
  val shape = RoundedCornerShape(16.dp)
  val isDark = LocalIsDarkTheme.current
  val targetBg = if (isDark) {
    MaterialTheme.colorScheme.surface
  } else {
    PearlWhiteTranslucent // rgba(255, 255, 255, 0.85) layered translucent light pearl
  }

  val bgColor by animateColorAsState(targetBg, tween(250), label = "card_bg")

  Box(
    modifier = modifier
      .clip(shape)
      .background(bgColor)
      .then(
        if (highlight) {
          Modifier.border(1.5.dp, ElectricBlue.copy(alpha = 0.8f), shape)
        } else {
          Modifier.border(1.dp, MaterialTheme.colorScheme.outline, shape)
        }
      )
      .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
  ) {
    content()
  }
}

enum class BadgeVariant {
  BLUE, SILVER, SUCCESS, WARNING
}

@Composable
fun TnBadge(
  text: String,
  modifier: Modifier = Modifier,
  variant: BadgeVariant = BadgeVariant.SILVER
) {
  // STRICT COLOR CONSTRAINT: Only Pearl White, Acrylic Black, and Electric Blue
  val (bgColor, textColor) = when (variant) {
    BadgeVariant.BLUE, BadgeVariant.SUCCESS -> Pair(
      ElectricBlueSubtle,
      ElectricBlue
    )
    BadgeVariant.SILVER, BadgeVariant.WARNING -> Pair(
      MaterialTheme.colorScheme.surfaceVariant,
      MaterialTheme.colorScheme.onSurfaceVariant
    )
  }

  Box(
    modifier = modifier
      .clip(RoundedCornerShape(99.dp))
      .background(bgColor)
      .padding(horizontal = 9.dp, vertical = 3.dp),
    contentAlignment = Alignment.Center
  ) {
    Text(
      text = text,
      color = textColor,
      fontSize = 10.5.sp,
      fontFamily = FontFamily.SansSerif,
      fontWeight = FontWeight.Bold,
      letterSpacing = 0.8.sp
    )
  }
}

@Composable
fun MasteryRingIndicator(
  percentage: Int,
  modifier: Modifier = Modifier,
  size: Dp = 56.dp,
  strokeWidth: Dp = 4.5.dp
) {
  val animatedProgress by animateFloatAsState(
    targetValue = percentage / 100f,
    animationSpec = tween(durationMillis = 600),
    label = "mastery_progress"
  )

  val trackColor = MaterialTheme.colorScheme.surfaceVariant

  Box(
    modifier = modifier.size(size),
    contentAlignment = Alignment.Center
  ) {
    Canvas(modifier = Modifier.size(size)) {
      val strokePx = strokeWidth.toPx()
      val arcSize = Size(this.size.width - strokePx, this.size.height - strokePx)
      val topLeft = Offset(strokePx / 2f, strokePx / 2f)

      // Background track
      drawArc(
        color = trackColor,
        startAngle = 0f,
        sweepAngle = 360f,
        useCenter = false,
        topLeft = topLeft,
        size = arcSize,
        style = Stroke(width = strokePx)
      )

      // Progress sweep: Strictly Electric Blue
      drawArc(
        color = ElectricBlue,
        startAngle = -90f,
        sweepAngle = 360f * animatedProgress,
        useCenter = false,
        topLeft = topLeft,
        size = arcSize,
        style = Stroke(width = strokePx, cap = StrokeCap.Round)
      )
    }

    Text(
      text = "$percentage%",
      color = MaterialTheme.colorScheme.onSurface,
      fontSize = 11.sp,
      fontFamily = FontFamily.Monospace,
      fontWeight = FontWeight.Bold
    )
  }
}

@Composable
fun OfflineSyncChip(
  isOfflineSimulated: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
      .clip(RoundedCornerShape(99.dp))
      .background(MaterialTheme.colorScheme.surfaceVariant)
      .clickable { onClick() }
      .padding(horizontal = 10.dp, vertical = 5.dp)
      .testTag("offline_sync_chip")
  ) {
    // STRICT CONSTRAINT: Only Pearl White, Acrylic Black, or Electric Blue (#0052FF)
    Box(
      modifier = Modifier
        .size(7.dp)
        .clip(CircleShape)
        .background(if (isOfflineSimulated) MaterialTheme.colorScheme.onSurfaceVariant else ElectricBlue)
    )
    Spacer(modifier = Modifier.width(6.dp))
    Text(
      text = if (isOfflineSimulated) "Local Hub" else "Cloud Sync",
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      fontSize = 10.sp,
      fontFamily = FontFamily.Monospace,
      fontWeight = FontWeight.Medium
    )
  }
}

@Composable
fun RoleSelectorTabs(
  selectedRole: UserRole,
  onRoleSelected: (UserRole) -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .clip(RoundedCornerShape(10.dp))
      .background(MaterialTheme.colorScheme.surfaceVariant)
      .padding(3.dp),
    horizontalArrangement = Arrangement.spacedBy(2.dp)
  ) {
    UserRole.values().forEach { role ->
      val isSelected = role == selectedRole
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(8.dp))
          .background(if (isSelected) ElectricBlue else Color.Transparent)
          .clickable { onRoleSelected(role) }
          .padding(horizontal = 10.dp, vertical = 5.dp)
          .testTag("role_tab_${role.name.lowercase()}"),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = role.name,
          color = if (isSelected) PearlWhitePure else MaterialTheme.colorScheme.onSurfaceVariant,
          fontSize = 10.sp,
          fontFamily = FontFamily.Monospace,
          fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
      }
    }
  }
}
