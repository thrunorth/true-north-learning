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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.EscalatorWarning
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SupervisorAccount
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
import com.example.ui.theme.PearlWhitePure

@Composable
fun LoginScreen(
  onLoginSuccess: (UserRole) -> Unit,
  modifier: Modifier = Modifier
) {
  var selectedTab by remember { mutableStateOf(0) } // 0: Quick Account, 1: Email Form
  var emailInput by remember { mutableStateOf("juan.delacruz@makati.deped.gov.ph") }
  var passwordInput by remember { mutableStateOf("••••••••") }
  var selectedFormRole by remember { mutableStateOf(UserRole.STUDENT) }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 20.dp, vertical = 24.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Spacer(modifier = Modifier.height(12.dp))

    // Attached Custom Logo Hero with STAY AHEAD. BE SMART.
    ThruNorthHeroBrand(
      logoSize = 100.dp
    )

    Spacer(modifier = Modifier.height(18.dp))

    TnBadge(text = "PHILIPPINE K-12 SECURE ACCESS", variant = BadgeVariant.BLUE)

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = "Single sign-on gateway for DepEd MATATAG and Legacy K-12 learner and educator profiles.",
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      fontSize = 13.sp,
      lineHeight = 18.sp,
      textAlign = TextAlign.Center,
      modifier = Modifier.padding(horizontal = 12.dp)
    )

    Spacer(modifier = Modifier.height(24.dp))

    // Mode Toggle Tabs: "1-Tap Profiles" vs "Email & Password" (Un-bordered, modern surface)
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(MaterialTheme.colorScheme.surfaceVariant)
        .padding(4.dp)
    ) {
      Box(
        modifier = Modifier
          .weight(1f)
          .clip(RoundedCornerShape(9.dp))
          .background(if (selectedTab == 0) ElectricBlue else Color.Transparent)
          .clickable { selectedTab = 0 }
          .padding(vertical = 10.dp)
          .testTag("tab_quick_select"),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = "DEMO ACCOUNTS",
          color = if (selectedTab == 0) PearlWhitePure else MaterialTheme.colorScheme.onSurfaceVariant,
          fontSize = 11.sp,
          fontFamily = FontFamily.Monospace,
          fontWeight = FontWeight.Bold
        )
      }

      Box(
        modifier = Modifier
          .weight(1f)
          .clip(RoundedCornerShape(9.dp))
          .background(if (selectedTab == 1) ElectricBlue else Color.Transparent)
          .clickable { selectedTab = 1 }
          .padding(vertical = 10.dp)
          .testTag("tab_custom_credentials"),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = "CUSTOM SIGN-IN",
          color = if (selectedTab == 1) PearlWhitePure else MaterialTheme.colorScheme.onSurfaceVariant,
          fontSize = 11.sp,
          fontFamily = FontFamily.Monospace,
          fontWeight = FontWeight.Bold
        )
      }
    }

    Spacer(modifier = Modifier.height(18.dp))

    if (selectedTab == 0) {
      // Direct 1-Tap Account Cards (Un-bordered, generously spaced)
      Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        AccountSelectCard(
          role = UserRole.STUDENT,
          name = "Juan de la Cruz",
          detail = "Grade 7 - Rizal • Makati Science Academic Center",
          icon = Icons.Default.School,
          onSelect = { onLoginSuccess(UserRole.STUDENT) },
          testTag = "login_as_student"
        )

        AccountSelectCard(
          role = UserRole.TEACHER,
          name = "Prof. Rogelio Santos, LPT",
          detail = "Science & Math Lead • Sections Rizal & Bonifacio",
          icon = Icons.Default.SupervisorAccount,
          onSelect = { onLoginSuccess(UserRole.TEACHER) },
          testTag = "login_as_teacher"
        )

        AccountSelectCard(
          role = UserRole.ADMIN,
          name = "Dr. Alicia Valdez, EdD",
          detail = "Academic Director • Dual-Stack Systems Administrator",
          icon = Icons.Default.AdminPanelSettings,
          onSelect = { onLoginSuccess(UserRole.ADMIN) },
          testTag = "login_as_admin"
        )

        AccountSelectCard(
          role = UserRole.PARENT,
          name = "Maria Elena de la Cruz",
          detail = "Parent / Guardian of Juan de la Cruz",
          icon = Icons.Default.EscalatorWarning,
          onSelect = { onLoginSuccess(UserRole.PARENT) },
          testTag = "login_as_parent"
        )
      }
    } else {
      // Standard Credentials Form (Un-bordered card, un-bordered input fields)
      TnCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(20.dp)) {
          Text(
            text = "Role Authorization Target",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
          )
          Spacer(modifier = Modifier.height(10.dp))

          // Role selection pills
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            listOf(UserRole.STUDENT, UserRole.TEACHER, UserRole.ADMIN).forEach { role ->
              val isSelected = selectedFormRole == role
              Box(
                modifier = Modifier
                  .weight(1f)
                  .clip(RoundedCornerShape(10.dp))
                  .background(if (isSelected) ElectricBlueSubtle else MaterialTheme.colorScheme.surfaceVariant)
                  .clickable {
                    selectedFormRole = role
                    emailInput = when (role) {
                      UserRole.STUDENT -> "juan.delacruz@makati.deped.gov.ph"
                      UserRole.TEACHER -> "r.santos@true-north.ph"
                      UserRole.ADMIN -> "a.valdez@true-north.ph"
                      UserRole.PARENT -> "m.delacruz@alumni.up.edu.ph"
                    }
                  }
                  .padding(vertical = 9.dp),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = role.label,
                  color = if (isSelected) ElectricBlue else MaterialTheme.colorScheme.onSurfaceVariant,
                  fontSize = 11.sp,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(18.dp))

          Text("DepEd Account Email / LRN", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp)
          Spacer(modifier = Modifier.height(6.dp))
          OutlinedTextField(
            value = emailInput,
            onValueChange = { emailInput = it },
            leadingIcon = { Icon(Icons.Default.Mail, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant) },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = ElectricBlue,
              unfocusedBorderColor = Color.Transparent,
              focusedTextColor = MaterialTheme.colorScheme.onSurface,
              unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
              focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
              unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
              cursorColor = ElectricBlue
            ),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("login_email_input")
          )

          Spacer(modifier = Modifier.height(14.dp))

          Text("Password / Secure PIN", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp)
          Spacer(modifier = Modifier.height(6.dp))
          OutlinedTextField(
            value = passwordInput,
            onValueChange = { passwordInput = it },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant) },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = ElectricBlue,
              unfocusedBorderColor = Color.Transparent,
              focusedTextColor = MaterialTheme.colorScheme.onSurface,
              unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
              focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
              unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
              cursorColor = ElectricBlue
            ),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("login_password_input")
          )

          Spacer(modifier = Modifier.height(22.dp))

          TnButton(
            text = "Authenticate as ${selectedFormRole.label}",
            variant = ButtonVariant.PRIMARY,
            size = ButtonSize.LG,
            onClick = { onLoginSuccess(selectedFormRole) },
            modifier = Modifier.fillMaxWidth(),
            testTag = "submit_login_btn"
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(28.dp))

    // Security & Compliance Footer (Un-bordered, clean)
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colorScheme.surfaceVariant)
        .padding(14.dp)
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
      ) {
        Box(
          modifier = Modifier
            .size(6.dp)
            .clip(RoundedCornerShape(99.dp))
            .background(ElectricBlue)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "THRU NORTH • STAY AHEAD. BE SMART. • DepEd LIS Single Sign-On",
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          fontSize = 10.sp,
          fontFamily = FontFamily.Monospace
        )
      }
    }
  }
}

@Composable
fun AccountSelectCard(
  role: UserRole,
  name: String,
  detail: String,
  icon: ImageVector,
  onSelect: () -> Unit,
  testTag: String
) {
  TnCard(
    modifier = Modifier.fillMaxWidth(),
    onClick = onSelect
  ) {
    Row(
      modifier = Modifier
        .padding(16.dp)
        .testTag(testTag),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .size(44.dp)
          .clip(RoundedCornerShape(12.dp))
          .background(ElectricBlueSubtle),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon,
          contentDescription = role.name,
          tint = ElectricBlue,
          modifier = Modifier.size(24.dp)
        )
      }

      Spacer(modifier = Modifier.width(14.dp))

      Column(modifier = Modifier.weight(1f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = name,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.width(8.dp))
          TnBadge(
            text = role.name,
            variant = when (role) {
              UserRole.STUDENT -> BadgeVariant.BLUE
              UserRole.TEACHER -> BadgeVariant.SUCCESS
              UserRole.ADMIN -> BadgeVariant.WARNING
              UserRole.PARENT -> BadgeVariant.SILVER
            }
          )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Text(
          text = detail,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          fontSize = 11.sp,
          lineHeight = 15.sp
        )
      }

      Icon(
        imageVector = Icons.Default.ArrowForward,
        contentDescription = "Sign in",
        tint = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.size(16.dp)
      )
    }
  }
}
