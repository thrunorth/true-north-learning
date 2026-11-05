package com.example

import com.example.data.MockData
import com.example.model.CurriculumStandard
import com.example.model.UserRole
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
  @Test
  fun testMockUsersContainAllRoles() {
    UserRole.values().forEach { role ->
      assertNotNull("User for role $role should exist", MockData.USERS[role])
    }
  }

  @Test
  fun testMatatagAndLegacyCompetenciesPresent() {
    val matatagCount = MockData.COMPETENCIES.count { it.curriculumVersion == CurriculumStandard.MATATAG }
    val legacyCount = MockData.COMPETENCIES.count { it.curriculumVersion == CurriculumStandard.LEGACY_K12 }
    assertTrue("Should have MATATAG competencies", matatagCount > 0)
    assertTrue("Should have Legacy K-12 competencies", legacyCount > 0)
  }

  @Test
  fun testSampleQuizQuestionsValid() {
    assertTrue(MockData.SAMPLE_QUIZ_QUESTIONS.isNotEmpty())
    MockData.SAMPLE_QUIZ_QUESTIONS.forEach { q ->
      assertTrue(q.correctOptionIndex in q.options.indices)
    }
  }

  @Test
  fun testNavigationRoutesConfigured() {
    val destinations = com.example.navigation.NavDestination.authenticatedDestinations
    assertEquals(4, destinations.size)
    val routes = destinations.map { it.route }.toSet()
    assertEquals("All authenticated destinations must have unique routes", destinations.size, routes.size)
    assertEquals("student", com.example.navigation.NavDestination.forRole(UserRole.STUDENT).route)
    assertEquals("teacher", com.example.navigation.NavDestination.forRole(UserRole.TEACHER).route)
    assertEquals("admin", com.example.navigation.NavDestination.forRole(UserRole.ADMIN).route)
  }

  @Test
  fun testAdaptiveThemeColorSchemes() {
    val dark = com.example.ui.theme.DarkColorScheme
    val light = com.example.ui.theme.LightColorScheme

    // System Dark Mode: Deep acrylic black (#050505) with pearl white text (#FFFFFF) and Electric Blue (#0052FF)
    assertEquals(androidx.compose.ui.graphics.Color(0xFF050505), dark.background)
    assertEquals(androidx.compose.ui.graphics.Color(0xFFFFFFFF), dark.onBackground)
    assertEquals(androidx.compose.ui.graphics.Color(0xFF0052FF), dark.primary)

    // Default Day Mode (Light): Crisp pearl white (#F8F9FA), Acrylic black (#0A0A0A), Electric Blue (#0052FF), Charcoal (#4A4A4A)
    assertEquals(androidx.compose.ui.graphics.Color(0xFFF8F9FA), light.background)
    assertEquals(androidx.compose.ui.graphics.Color(0xFF0A0A0A), light.onBackground)
    assertEquals(androidx.compose.ui.graphics.Color(0xFF0052FF), light.primary)
    assertEquals(androidx.compose.ui.graphics.Color(0xFF4A4A4A), light.secondary)
    assertEquals(androidx.compose.ui.graphics.Color(0xFFFFFFFF), light.onPrimary)
  }
}
