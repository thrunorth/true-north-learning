import {
  UserProfile,
  Competency,
  StudentMasteryItem,
  QuizQuestion,
  CohortObservation,
  UserRole
} from '../types';

export const MOCK_USERS: Record<UserRole, UserProfile> = {
  STUDENT: {
    id: 'std_juan_01',
    fullName: 'Juan de la Cruz',
    email: 'juan.delacruz@makati.deped.gov.ph',
    role: 'STUDENT',
    schoolId: 'sch_mnl_301',
    schoolName: 'Makati Science Academic Center',
    activeCurriculum: 'MATATAG',
    gradeLevel: 7
  },
  PARENT: {
    id: 'par_maria_01',
    fullName: 'Maria Elena de la Cruz',
    email: 'm.delacruz@alumni.up.edu.ph',
    role: 'PARENT',
    schoolId: 'sch_mnl_301',
    schoolName: 'Makati Science Academic Center',
    activeCurriculum: 'MATATAG',
    childrenIds: ['std_juan_01']
  },
  TEACHER: {
    id: 'tch_santos_01',
    fullName: 'Prof. Rogelio Santos, LPT',
    email: 'r.santos@true-north.ph',
    role: 'TEACHER',
    schoolId: 'sch_mnl_301',
    schoolName: 'Makati Science Academic Center',
    activeCurriculum: 'MATATAG',
    assignedSections: ['Grade 7 - Rizal', 'Grade 7 - Bonifacio']
  },
  ADMIN: {
    id: 'adm_valdez_01',
    fullName: 'Dr. Alicia Valdez, EdD',
    email: 'a.valdez@true-north.ph',
    role: 'ADMIN',
    schoolId: 'sch_mnl_301',
    schoolName: 'Makati Science Academic Center',
    activeCurriculum: 'MATATAG'
  }
};

export const MOCK_COMPETENCIES: Competency[] = [
  {
    id: 'COMP-MAT-07-01',
    code: 'MATATAG-M7-NS-1',
    curriculumVersion: 'MATATAG',
    title: 'Rational Numbers & Real-World Operations',
    description: 'Describes and represents real-life situations involving positive and negative rational numbers with precision.',
    keyStage: 'KS3_7_TO_10',
    gradeLevel: 7,
    subject: 'Mathematics',
    domain: 'Number and Algebra',
    cohortMasteryPercentage: 88
  },
  {
    id: 'COMP-SCI-07-01',
    code: 'MATATAG-S7-LS-2',
    curriculumVersion: 'MATATAG',
    title: 'Microscopy & Cellular Organization',
    description: 'Identifies parts of a compound microscope and describes levels of biological organization from cell to biosphere.',
    keyStage: 'KS3_7_TO_10',
    gradeLevel: 7,
    subject: 'Science',
    domain: 'Life Sciences',
    cohortMasteryPercentage: 68
  },
  {
    id: 'COMP-MAK-03-01',
    code: 'MATATAG-MAK-03',
    curriculumVersion: 'MATATAG',
    title: 'Pagkakakilanlang Pilipino at Pamayanan',
    description: 'Naipamamalas ang pag-unawa sa sariling kultura, wika, at tradisyon sa kinabibilangang rehiyon.',
    keyStage: 'KS1_K_TO_3',
    gradeLevel: 3,
    subject: 'Makabansa',
    domain: 'Kalinangang Pilipino',
    cohortMasteryPercentage: 93
  },
  {
    id: 'COMP-ENG-07-03',
    code: 'MATATAG-E7-RC-3',
    curriculumVersion: 'MATATAG',
    title: 'Informational Texts & Evidence Synthesis',
    description: 'Synthesizes multi-source claims and distinguishes verifiable facts from editorial opinions in Southeast Asian essays.',
    keyStage: 'KS3_7_TO_10',
    gradeLevel: 7,
    subject: 'English',
    domain: 'Reading Comprehension',
    cohortMasteryPercentage: 82
  },
  {
    id: 'COMP-LEG-10-04',
    code: 'S10LT-IIa-d-38',
    curriculumVersion: 'LEGACY_K12',
    title: 'Heredity, Inheritance and Variation',
    description: 'Explains how mutation leads to genetic diversity according to the 2016 standard DepEd syllabus.',
    keyStage: 'KS3_7_TO_10',
    gradeLevel: 10,
    subject: 'Science',
    domain: 'Genetics',
    cohortMasteryPercentage: 76
  },
  {
    id: 'COMP-LEG-12-01',
    code: 'STEM_PC11AG-Ia-1',
    curriculumVersion: 'LEGACY_K12',
    title: 'Conic Sections & Analytical Geometry',
    description: 'Illustrates parabolas, ellipses, and hyperbolas in coordinate planes with real-world engineering models.',
    keyStage: 'KS4_11_TO_12',
    gradeLevel: 11,
    subject: 'Pre-Calculus',
    domain: 'STEM Track',
    cohortMasteryPercentage: 79
  }
];

export const INITIAL_STUDENT_MASTERY: StudentMasteryItem[] = [
  {
    competencyId: 'COMP-MAT-07-01',
    code: 'MATATAG-M7-NS-1',
    title: 'Rational Numbers & Real-World Operations',
    subject: 'Mathematics',
    curriculumVersion: 'MATATAG',
    score: 0.94,
    status: 'Mastered',
    retentionRisk: 'Low',
    lastAssessed: 'Yesterday, 3:15 PM',
    description: 'Describes and represents real-life situations involving positive and negative rational numbers.'
  },
  {
    competencyId: 'COMP-SCI-07-01',
    code: 'MATATAG-S7-LS-2',
    title: 'Microscopy & Cellular Organization',
    subject: 'Science',
    curriculumVersion: 'MATATAG',
    score: 0.68,
    status: 'In Progress',
    retentionRisk: 'Medium (Decay in 7 days)',
    lastAssessed: 'Oct 28, 2026',
    description: 'Compound microscope mechanics, magnification calculation, and cellular organelles hierarchy.'
  },
  {
    competencyId: 'COMP-ENG-07-03',
    code: 'MATATAG-E7-RC-3',
    title: 'Informational Texts & Evidence Synthesis',
    subject: 'English',
    curriculumVersion: 'MATATAG',
    score: 0.86,
    status: 'Mastered',
    retentionRisk: 'Low',
    lastAssessed: 'Nov 1, 2026',
    description: 'Critical reading of local essays and distinguishing empirical claims from speculative conclusions.'
  }
];

export const SAMPLE_QUIZ_QUESTIONS: QuizQuestion[] = [
  {
    id: 'Q1',
    competencyCode: 'MATATAG-S7-LS-2',
    contextPrompt: 'Microscopy & Biological Organization',
    questionText: 'When switching from Low Power Objective (10x) to High Power Objective (40x) on a standard compound microscope, which knob should you use EXCLUSIVELY to prevent cracking the glass slide?',
    options: [
      'Coarse adjustment knob',
      'Fine adjustment knob',
      'Stage clip adjustment',
      'Diaphragm rotating wheel'
    ],
    correctOptionIndex: 1,
    explanation: 'The fine adjustment knob moves the stage by minuscule increments and is exclusively used under high power to protect both the objective lens and glass specimen slide.'
  },
  {
    id: 'Q2',
    competencyCode: 'MATATAG-S7-LS-2',
    contextPrompt: 'Cellular Hierarchy',
    questionText: 'Which sequence correctly arranges biological levels of organization from least complex to most complex?',
    options: [
      'Cell → Tissue → Organ → Organ System → Organism',
      'Tissue → Cell → Organism → Organ → Biosphere',
      'Organ → Organ System → Tissue → Cell → Community',
      'Cell → Organ → Tissue → Organism → Population'
    ],
    correctOptionIndex: 0,
    explanation: 'Cells aggregate into tissues; tissues compose organs; organs form organ systems; organ systems assemble the complete organism.'
  },
  {
    id: 'Q3',
    competencyCode: 'MATATAG-M7-NS-1',
    contextPrompt: 'Philippine Real-World Operations',
    questionText: 'A jeepney driver drives 4.5 km eastward from Taft Avenue, then reverses 1.75 km westward due to road construction. What is his net displacement?',
    options: [
      '+2.75 km East',
      '+6.25 km East',
      '-2.75 km West',
      '+3.25 km East'
    ],
    correctOptionIndex: 0,
    explanation: 'Displacement = (+4.50 km) + (-1.75 km) = +2.75 km east of starting position.'
  }
];

export const INITIAL_OBSERVATIONS: CohortObservation[] = [
  {
    id: 'obs_01',
    teacherName: 'Prof. Rogelio Santos, LPT',
    section: 'Grade 7 - Rizal',
    date: 'Nov 2, 2026',
    competencyCode: 'MATATAG-M7-NS-1',
    note: 'Juan demonstrates exemplary abstract reasoning in rational number calculations. Under the MATATAG spiral progression, we will introduce cellular biology modeling next week.',
    recommendation: 'No home interventions required. Ready for advanced algebraic synthesis.'
  },
  {
    id: 'obs_02',
    teacherName: 'Prof. Rogelio Santos, LPT',
    section: 'Grade 7 - Bonifacio',
    date: 'Oct 30, 2026',
    competencyCode: 'MATATAG-S7-LS-2',
    note: 'Cohort showed minor confusion distinguishing between coarse and fine focal adjustment during lab work. Hands-on station drill scheduled.',
    recommendation: 'Review 5-minute micro practice on laboratory safety protocols.'
  }
];
