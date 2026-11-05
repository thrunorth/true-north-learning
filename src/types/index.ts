export type CurriculumStandard = 'MATATAG' | 'LEGACY_K12' | 'IB_BRIDGED';

export interface CurriculumStandardInfo {
  label: string;
  badgeTitle: string;
}

export const CURRICULUM_INFO: Record<CurriculumStandard, CurriculumStandardInfo> = {
  MATATAG: { label: 'DepEd MATATAG', badgeTitle: 'MATATAG' },
  LEGACY_K12: { label: '2016 Legacy K-12', badgeTitle: 'LEGACY K-12' },
  IB_BRIDGED: { label: 'IB International Bridged', badgeTitle: 'IB BRIDGED' }
};

export type KeyStageCode = 'KS1_K_TO_3' | 'KS2_4_TO_6' | 'KS3_7_TO_10' | 'KS4_11_TO_12';

export interface KeyStageInfo {
  code: string;
  description: string;
}

export const KEY_STAGE_INFO: Record<KeyStageCode, KeyStageInfo> = {
  KS1_K_TO_3: { code: 'KS1 (K-3)', description: 'Foundational Literacy & Makabansa' },
  KS2_4_TO_6: { code: 'KS2 (4-6)', description: 'Core Disciplines Transition' },
  KS3_7_TO_10: { code: 'KS3 (7-10)', description: 'Junior High Standard Disciplines' },
  KS4_11_TO_12: { code: 'KS4 (11-12)', description: 'Senior High Specialized Tracks (STEM, ABM, HUMSS)' }
};

export type UserRole = 'STUDENT' | 'PARENT' | 'TEACHER' | 'ADMIN';

export interface Competency {
  id: string;
  code: string;
  curriculumVersion: CurriculumStandard;
  title: string;
  description: string;
  keyStage: KeyStageCode;
  gradeLevel: number;
  subject: string;
  domain: string;
  cohortMasteryPercentage: number;
  prerequisiteCompetencyIds?: string[];
}

export interface UserProfile {
  id: string;
  fullName: string;
  email: string;
  role: UserRole;
  schoolId: string;
  schoolName: string;
  activeCurriculum: CurriculumStandard;
  gradeLevel?: number;
  childrenIds?: string[];
  assignedSections?: string[];
}

export interface StudentMasteryItem {
  competencyId: string;
  code: string;
  title: string;
  subject: string;
  curriculumVersion: CurriculumStandard;
  score: number; // 0.0 to 1.0
  status: string;
  retentionRisk: string; // Low, Medium, High
  lastAssessed: string;
  description: string;
}

export interface QuizQuestion {
  id: string;
  competencyCode: string;
  contextPrompt: string;
  questionText: string;
  options: string[];
  correctOptionIndex: number;
  explanation: string;
}

export interface CohortObservation {
  id: string;
  teacherName: string;
  section: string;
  date: string;
  competencyCode: string;
  note: string;
  recommendation: string;
}

export type ThemeMode = 'LIGHT' | 'DARK' | 'SYSTEM';
