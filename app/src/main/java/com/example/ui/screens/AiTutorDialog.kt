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

data class ChatMessage(val sender: String, val text: String, val isAi: Boolean)

@Composable
fun AiTutorDialog(
  onDismiss: () -> Unit
) {
  var inputText by remember { mutableStateOf("") }
  val messages = remember {
    mutableStateListOf(
      ChatMessage(
        sender = "System",
        text = "Diagnosing mastery boundary on: MATATAG-M7-NS-1 (Rational Numbers) & MATATAG-S7-LS-2 (Microscopy).",
        isAi = true
      ),
      ChatMessage(
        sender = "Socratic Tutor",
        text = "Kumusta Juan! In a real-world Philippine situation, if a jeepney travels 4.5 km east and turns around 1.75 km west, how would you represent that displacement with positive and negative rational numbers?",
        isAi = true
      )
    )
  }

  Dialog(onDismissRequest = onDismiss) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.surface)
        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(16.dp))
        .padding(16.dp)
        .testTag("ai_tutor_dialog")
    ) {
      Column(modifier = Modifier.fillMaxWidth()) {
        // Header
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "AI Socratic Tutor",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
              )
              Spacer(modifier = Modifier.width(8.dp))
              TnBadge(text = "SOCRATIC V2", variant = BadgeVariant.BLUE)
            }
            Text(
              text = "DepEd MATATAG Diagnostic Engine",
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              fontSize = 11.sp
            )
          }
          IconButton(onClick = onDismiss, modifier = Modifier.testTag("ai_tutor_close")) {
            Icon(Icons.Default.Close, contentDescription = "Close", tint = MaterialTheme.colorScheme.onSurfaceVariant)
          }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Architectural Contract Notice
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
              text = "ARCHITECTURE CONTRACT: ZERO KEY EXPOSURE",
              color = ElectricBlue,
              fontSize = 10.sp,
              fontFamily = FontFamily.Monospace,
              fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = "Backend-agnostic server-side proxy. Guided hints stimulate critical thinking rather than spoon-feeding answers.",
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              fontSize = 10.sp,
              lineHeight = 14.sp
            )
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Chat Message Log
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .verticalScroll(rememberScrollState()),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          messages.forEach { msg ->
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(if (msg.isAi) ElectricBlueSubtle else MaterialTheme.colorScheme.surfaceVariant)
                .border(
                  1.dp,
                  if (msg.isAi) ElectricBlue.copy(alpha = 0.3f) else MaterialTheme.colorScheme.outline,
                  RoundedCornerShape(8.dp)
                )
                .padding(10.dp)
            ) {
              Column {
                Text(
                  text = msg.sender,
                  color = if (msg.isAi) ElectricBlue else MaterialTheme.colorScheme.onSurfaceVariant,
                  fontSize = 10.sp,
                  fontFamily = FontFamily.Monospace,
                  fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                  text = msg.text,
                  color = MaterialTheme.colorScheme.onSurface,
                  fontSize = 12.sp,
                  lineHeight = 17.sp
                )
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Quick Socratic Prompt Suggestions
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          TnButton(
            text = "Give Hint",
            variant = ButtonVariant.SECONDARY,
            size = ButtonSize.SM,
            onClick = {
              messages.add(
                ChatMessage(
                  sender = "Socratic Tutor",
                  text = "Hint: Assign a positive (+) sign to the forward eastward direction and a negative (-) sign to the backward westward direction. Then add them together!",
                  isAi = true
                )
              )
            },
            modifier = Modifier.weight(1f),
            testTag = "ai_prompt_hint"
          )
          TnButton(
            text = "Microscope Tip",
            variant = ButtonVariant.SECONDARY,
            size = ButtonSize.SM,
            onClick = {
              messages.add(
                ChatMessage(
                  sender = "Socratic Tutor",
                  text = "Remember: When using High Power Objective (40x), turn ONLY the fine adjustment knob to prevent shattering your glass specimen cover!",
                  isAi = true
                )
              )
            },
            modifier = Modifier.weight(1f),
            testTag = "ai_prompt_microscope"
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Input Field
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically
        ) {
          OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            placeholder = { Text("Ask your Socratic question...", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = ElectricBlue,
              unfocusedBorderColor = MaterialTheme.colorScheme.outline,
              focusedTextColor = MaterialTheme.colorScheme.onSurface,
              unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
              focusedContainerColor = MaterialTheme.colorScheme.surface,
              unfocusedContainerColor = MaterialTheme.colorScheme.surface,
              cursorColor = ElectricBlue
            ),
            modifier = Modifier
              .weight(1f)
              .testTag("ai_tutor_input")
          )
          Spacer(modifier = Modifier.width(8.dp))
          TnButton(
            text = "Send",
            variant = ButtonVariant.PRIMARY,
            size = ButtonSize.MD,
            onClick = {
              if (inputText.isNotBlank()) {
                val userQuery = inputText
                messages.add(ChatMessage(sender = "Juan", text = userQuery, isAi = false))
                inputText = ""
                messages.add(
                  ChatMessage(
                    sender = "Socratic Tutor",
                    text = "Magaling! How would your equation look if the displacement went into a southern negative coordinate instead?",
                    isAi = true
                  )
                )
              }
            },
            testTag = "ai_tutor_send"
          )
        }
      }
    }
  }
}
