package com.example.model

enum class CurriculumStandard(val label: String, val badgeTitle: String) {
  MATATAG("DepEd MATATAG", "MATATAG"),
  LEGACY_K12("2016 Legacy K-12", "LEGACY K-12"),
  IB_BRIDGED("IB International Bridged", "IB BRIDGED")
}

enum class KeyStage(val code: String, val description: String) {
  KS1_K_TO_3("KS1 (K-3)", "Foundational Literacy & Makabansa"),
  KS2_4_TO_6("KS2 (4-6)", "Core Disciplines Transition"),
  KS3_7_TO_10("KS3 (7-10)", "Junior High Standard Disciplines"),
  KS4_11_TO_12("KS4 (11-12)", "Senior High Specialized Tracks (STEM, ABM, HUMSS)")
}

enum class UserRole(val label: String) {
  STUDENT("Student"),
  PARENT("Parent"),
  TEACHER("Teacher"),
  ADMIN("Administrator")
}

data class Competency(
  val id: String,
  val code: String,
  val curriculumVersion: CurriculumStandard,
  val title: String,
  val description: String,
  val keyStage: KeyStage,
  val gradeLevel: Int,
  val subject: String,
  val domain: String,
  val cohortMasteryPercentage: Int = 85,
  val prerequisiteCompetencyIds: List<String> = emptyList()
)

data class UserProfile(
  val id: String,
  val fullName: String,
  val email: String,
  val role: UserRole,
  val schoolId: String,
  val schoolName: String,
  val activeCurriculum: CurriculumStandard,
  val gradeLevel: Int? = null,
  val childrenIds: List<String> = emptyList(),
  val assignedSections: List<String> = emptyList()
)

data class StudentMasteryItem(
  val competencyId: String,
  val code: String,
  val title: String,
  val subject: String,
  val curriculumVersion: CurriculumStandard,
  val score: Float, // 0.0 to 1.0
  val status: String,
  val retentionRisk: String, // Low, Medium, High
  val lastAssessed: String,
  val description: String
)

data class QuizQuestion(
  val id: String,
  val competencyCode: String,
  val contextPrompt: String,
  val questionText: String,
  val options: List<String>,
  val correctOptionIndex: Int,
  val explanation: String
)

data class CohortObservation(
  val id: String,
  val teacherName: String,
  val section: String,
  val date: String,
  val competencyCode: String,
  val note: String,
  val recommendation: String
)
