package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.SettingsBrightness
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.data.MockData
import com.example.model.UserRole
import com.example.navigation.NavDestination
import com.example.ui.components.ButtonSize
import com.example.ui.components.ButtonVariant
import com.example.ui.components.OfflineSyncChip
import com.example.ui.components.ThruNorthHeaderLogo
import com.example.ui.components.TnButton
import com.example.ui.screens.AdminPortalScreen
import com.example.ui.screens.AiContentStudioDialog
import com.example.ui.screens.AiTutorDialog
import com.example.ui.screens.LoginScreen
import com.example.ui.screens.MicroQuizDialog
import com.example.ui.screens.ParentPortalScreen
import com.example.ui.screens.Sf10ExportDialog
import com.example.ui.screens.StudentPortalScreen
import com.example.ui.screens.TeacherPortalScreen
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.ElectricBlueSubtle
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.PearlWhitePure
import kotlinx.coroutines.launch

enum class ThemeMode {
  LIGHT, DARK, SYSTEM
}

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      // DEFAULT THEME: Day Mode (Light Mode) as specified
      var themeMode by remember { mutableStateOf(ThemeMode.LIGHT) }
      val systemIsDark = isSystemInDarkTheme()
      val isDark = when (themeMode) {
        ThemeMode.LIGHT -> false // Default Day Mode
        ThemeMode.DARK -> true   // Deep Acrylic Black
        ThemeMode.SYSTEM -> systemIsDark
      }

      MyApplicationTheme(darkTheme = isDark) {
        ThruNorthApp(
          themeMode = themeMode,
          onToggleTheme = {
            themeMode = when (themeMode) {
              ThemeMode.LIGHT -> ThemeMode.DARK
              ThemeMode.DARK -> ThemeMode.SYSTEM
              ThemeMode.SYSTEM -> ThemeMode.LIGHT
            }
          }
        )
      }
    }
  }
}

@Composable
fun ThruNorthApp(
  themeMode: ThemeMode,
  onToggleTheme: () -> Unit
) {
  val navController = rememberNavController()
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = navBackStackEntry?.destination?.route

  var isLoggedIn by remember { mutableStateOf(false) }
  var currentUserRole by remember { mutableStateOf(UserRole.STUDENT) }
  var isOfflineSimulated by remember { mutableStateOf(false) }

  // Dialog Overlays State
  var isAiTutorOpen by remember { mutableStateOf(false) }
  var isAiStudioOpen by remember { mutableStateOf(false) }
  var isMicroQuizOpen by remember { mutableStateOf(false) }
  var isSf10Open by remember { mutableStateOf(false) }

  // Student Reactive Mastery State
  val studentMastery = remember { mutableStateListOf(*MockData.INITIAL_STUDENT_MASTERY.toTypedArray()) }
  var overallQuotient by remember { mutableFloatStateOf(88.4f) }

  val snackbarHostState = remember { SnackbarHostState() }
  val coroutineScope = rememberCoroutineScope()

  val isAuthScreen = currentRoute != NavDestination.Login.route && currentRoute != null

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    containerColor = MaterialTheme.colorScheme.background,
    snackbarHost = { SnackbarHost(snackbarHostState) },
    topBar = {
      if (isAuthScreen) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
        ) {
          // Top Navigation Bar
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            // Attached Custom Brand Logo and Logotype
            ThruNorthHeaderLogo(
              iconSize = 36.dp,
              showTagline = true,
              onClick = {
                navController.navigate(NavDestination.Student.route) {
                  popUpTo(NavDestination.Student.route) { inclusive = true }
                }
              },
              modifier = Modifier.testTag("app_logo")
            )

            // Right side controls: Offline Sync + Day/Dark Toggle + Sign Out
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              OfflineSyncChip(
                isOfflineSimulated = isOfflineSimulated,
                onClick = {
                  isOfflineSimulated = !isOfflineSimulated
                  coroutineScope.launch {
                    val msg = if (isOfflineSimulated) {
                      "Local Cache: Operating on On-Premises School Hub"
                    } else {
                      "Cloud Synchronized: Connected to DepEd Central LIS"
                    }
                    snackbarHostState.showSnackbar(msg)
                  }
                }
              )

              // Theme Mode Toggle (Default: Day Mode)
              IconButton(
                onClick = onToggleTheme,
                modifier = Modifier
                  .size(34.dp)
                  .clip(CircleShape)
                  .background(MaterialTheme.colorScheme.surfaceVariant)
                  .testTag("theme_toggle_button")
              ) {
                Icon(
                  imageVector = when (themeMode) {
                    ThemeMode.LIGHT -> Icons.Default.LightMode
                    ThemeMode.DARK -> Icons.Default.DarkMode
                    ThemeMode.SYSTEM -> Icons.Default.SettingsBrightness
                  },
                  contentDescription = "Toggle Theme (Mode: ${themeMode.name})",
                  tint = MaterialTheme.colorScheme.onSurfaceVariant,
                  modifier = Modifier.size(16.dp)
                )
              }

              // Sign Out Button
              IconButton(
                onClick = {
                  isLoggedIn = false
                  navController.navigate(NavDestination.Login.route) {
                    popUpTo(0) { inclusive = true }
                  }
                },
                modifier = Modifier
                  .size(34.dp)
                  .clip(CircleShape)
                  .background(MaterialTheme.colorScheme.surfaceVariant)
                  .testTag("sign_out_button")
              ) {
                Icon(
                  imageVector = Icons.AutoMirrored.Filled.Logout,
                  contentDescription = "Sign Out",
                  tint = MaterialTheme.colorScheme.onSurfaceVariant,
                  modifier = Modifier.size(16.dp)
                )
              }
            }
          }

          // App Navigation Banner: THRU NORTH — Stay Ahead. Be Smart.
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .background(MaterialTheme.colorScheme.surface)
              .padding(horizontal = 16.dp, vertical = 9.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = "THRU NORTH — Stay Ahead. Be Smart.",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                letterSpacing = 0.5.sp
              )
              Text(
                text = "Philippine K-12 Mastery Operating System",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 10.sp
              )
            }

            // Quick AI Tools Triggers: Strictly Electric Blue Buttons with Pearl White Text
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
              TnButton(
                text = "AI Tutor",
                variant = ButtonVariant.PRIMARY,
                size = ButtonSize.SM,
                onClick = { isAiTutorOpen = true },
                testTag = "top_ai_tutor_btn"
              )

              if (currentRoute == NavDestination.Teacher.route || currentRoute == NavDestination.Admin.route) {
                TnButton(
                  text = "AI Studio",
                  variant = ButtonVariant.SECONDARY,
                  size = ButtonSize.SM,
                  onClick = { isAiStudioOpen = true },
                  testTag = "top_ai_studio_btn"
                )
              }
            }
          }

          // Role Switcher Navigation Strip
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .background(MaterialTheme.colorScheme.background)
              .padding(horizontal = 16.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            NavDestination.authenticatedDestinations.forEach { dest ->
              val isSelected = currentRoute == dest.route
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(8.dp))
                  .background(if (isSelected) ElectricBlue else MaterialTheme.colorScheme.surfaceVariant)
                  .clickable {
                    currentUserRole = when (dest) {
                      NavDestination.Student -> UserRole.STUDENT
                      NavDestination.Teacher -> UserRole.TEACHER
                      NavDestination.Admin -> UserRole.ADMIN
                      NavDestination.Parent -> UserRole.PARENT
                      else -> currentUserRole
                    }
                    navController.navigate(dest.route) {
                      popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                      }
                      launchSingleTop = true
                      restoreState = true
                    }
                  }
                  .padding(horizontal = 12.dp, vertical = 6.dp)
                  .testTag("nav_role_tab_${dest.route}"),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = dest.title.uppercase(),
                  color = if (isSelected) PearlWhitePure else MaterialTheme.colorScheme.onSurfaceVariant,
                  fontSize = 10.5.sp,
                  fontFamily = FontFamily.Monospace,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                )
              }
            }
          }
        }
      }
    },
    bottomBar = {
      if (isAuthScreen) {
        NavigationBar(
          containerColor = MaterialTheme.colorScheme.surface,
          contentColor = MaterialTheme.colorScheme.onSurface,
          tonalElevation = 0.dp
        ) {
          NavDestination.authenticatedDestinations.forEach { dest ->
            val isSelected = currentRoute == dest.route
            NavigationBarItem(
              selected = isSelected,
              onClick = {
                currentUserRole = when (dest) {
                  NavDestination.Student -> UserRole.STUDENT
                  NavDestination.Teacher -> UserRole.TEACHER
                  NavDestination.Admin -> UserRole.ADMIN
                  NavDestination.Parent -> UserRole.PARENT
                  else -> currentUserRole
                }
                navController.navigate(dest.route) {
                  popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                  }
                  launchSingleTop = true
                  restoreState = true
                }
              },
              icon = {
                dest.icon?.let {
                  Icon(
                    imageVector = it,
                    contentDescription = dest.title,
                    modifier = Modifier.size(20.dp)
                  )
                }
              },
              label = {
                Text(
                  text = dest.title,
                  fontSize = 11.sp,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
              },
              colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ElectricBlue,
                selectedTextColor = ElectricBlue,
                indicatorColor = ElectricBlueSubtle,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
              ),
              modifier = Modifier.testTag("bottom_nav_${dest.route}")
            )
          }
        }
      }
    },
    floatingActionButton = {
      if (isAuthScreen) {
        FloatingActionButton(
          onClick = { isAiTutorOpen = true },
          containerColor = ElectricBlue,
          contentColor = PearlWhitePure,
          shape = CircleShape,
          modifier = Modifier.testTag("fab_ai_socratic")
        ) {
          Icon(Icons.Default.AutoAwesome, contentDescription = "AI Socratic Tutor")
        }
      }
    }
  ) { innerPadding ->
    NavHost(
      navController = navController,
      startDestination = NavDestination.Login.route,
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(MaterialTheme.colorScheme.background)
    ) {
      // 1. Sign-In / Login Screen
      composable(NavDestination.Login.route) {
        LoginScreen(
          onLoginSuccess = { role ->
            isLoggedIn = true
            currentUserRole = role
            val targetRoute = NavDestination.forRole(role).route
            navController.navigate(targetRoute) {
              popUpTo(NavDestination.Login.route) { inclusive = true }
            }
          }
        )
      }

      // 2. Student Portal Screen
      composable(NavDestination.Student.route) {
        BackHandler {
          isLoggedIn = false
          navController.navigate(NavDestination.Login.route) {
            popUpTo(0) { inclusive = true }
          }
        }
        StudentPortalScreen(
          masteryList = studentMastery,
          overallQuotient = overallQuotient,
          onLaunchMicroQuiz = { isMicroQuizOpen = true },
          onOpenAiTutor = { isAiTutorOpen = true }
        )
      }

      // 3. Teacher Portal Screen
      composable(NavDestination.Teacher.route) {
        BackHandler {
          navController.navigate(NavDestination.Student.route) {
            popUpTo(navController.graph.findStartDestination().id)
          }
        }
        TeacherPortalScreen(
          onOpenSf10Export = { isSf10Open = true },
          onOpenAiStudio = { isAiStudioOpen = true }
        )
      }

      // 4. Administrator Portal Screen
      composable(NavDestination.Admin.route) {
        BackHandler {
          navController.navigate(NavDestination.Student.route) {
            popUpTo(navController.graph.findStartDestination().id)
          }
        }
        AdminPortalScreen(
          onOpenAiStudio = { isAiStudioOpen = true }
        )
      }

      // 5. Parent Portal Screen
      composable(NavDestination.Parent.route) {
        BackHandler {
          navController.navigate(NavDestination.Student.route) {
            popUpTo(navController.graph.findStartDestination().id)
          }
        }
        ParentPortalScreen()
      }
    }
  }

  // Interactive Dialogs
  if (isAiTutorOpen) {
    AiTutorDialog(onDismiss = { isAiTutorOpen = false })
  }

  if (isAiStudioOpen) {
    AiContentStudioDialog(onDismiss = { isAiStudioOpen = false })
  }

  if (isMicroQuizOpen) {
    MicroQuizDialog(
      onQuizCompleted = { newScorePercent ->
        val idx = studentMastery.indexOfFirst { it.code == "MATATAG-S7-LS-2" }
        if (idx != -1) {
          val item = studentMastery[idx]
          studentMastery[idx] = item.copy(
            score = (newScorePercent / 100f).coerceIn(0.70f, 1.0f),
            status = if (newScorePercent >= 70) "Mastered" else "Reinforced",
            retentionRisk = "Low (Refreshed)",
            lastAssessed = "Just now"
          )
        }
        overallQuotient = 91.2f
      },
      onDismiss = { isMicroQuizOpen = false }
    )
  }

  if (isSf10Open) {
    Sf10ExportDialog(onDismiss = { isSf10Open = false })
  }
}
