package com.example.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.EscalatorWarning
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SupervisorAccount
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.model.UserRole

sealed class NavDestination(
  val route: String,
  val title: String,
  val icon: ImageVector? = null
) {
  object Login : NavDestination(route = "login", title = "Sign In")
  object Student : NavDestination(route = "student", title = "Student", icon = Icons.Default.School)
  object Teacher : NavDestination(route = "teacher", title = "Teacher", icon = Icons.Default.SupervisorAccount)
  object Admin : NavDestination(route = "admin", title = "Admin", icon = Icons.Default.AdminPanelSettings)
  object Parent : NavDestination(route = "parent", title = "Parent", icon = Icons.Default.EscalatorWarning)

  companion object {
    val authenticatedDestinations = listOf(Student, Teacher, Admin, Parent)

    fun forRole(role: UserRole): NavDestination {
      return when (role) {
        UserRole.STUDENT -> Student
        UserRole.TEACHER -> Teacher
        UserRole.ADMIN -> Admin
        UserRole.PARENT -> Parent
      }
    }
  }
}
