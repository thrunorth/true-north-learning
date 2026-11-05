import React, { useState } from 'react';
import {
  GraduationCap,
  Users,
  Shield,
  HeartHandshake,
  Sparkles
} from 'lucide-react';
import { UserRole, StudentMasteryItem } from './types';
import { INITIAL_STUDENT_MASTERY } from './data/mockData';
import { Navbar } from './components/Navbar';
import { LoginScreen } from './screens/LoginScreen';
import { StudentPortalScreen } from './screens/StudentPortalScreen';
import { TeacherPortalScreen } from './screens/TeacherPortalScreen';
import { AdminPortalScreen } from './screens/AdminPortalScreen';
import { ParentPortalScreen } from './screens/ParentPortalScreen';

// Dialogs
import { AiTutorDialog } from './components/dialogs/AiTutorDialog';
import { AiContentStudioDialog } from './components/dialogs/AiContentStudioDialog';
import { MicroQuizDialog } from './components/dialogs/MicroQuizDialog';
import { Sf10ExportDialog } from './components/dialogs/Sf10ExportDialog';

export const App: React.FC = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [currentUserRole, setCurrentUserRole] = useState<UserRole>('STUDENT');
  const [isOfflineSimulated, setIsOfflineSimulated] = useState(false);
  const [toastMessage, setToastMessage] = useState<string | null>(null);

  // Dialog Overlays State
  const [isAiTutorOpen, setIsAiTutorOpen] = useState(false);
  const [isAiStudioOpen, setIsAiStudioOpen] = useState(false);
  const [isMicroQuizOpen, setIsMicroQuizOpen] = useState(false);
  const [isSf10Open, setIsSf10Open] = useState(false);

  // Reactive Student Mastery State
  const [studentMastery, setStudentMastery] = useState<StudentMasteryItem[]>(INITIAL_STUDENT_MASTERY);
  const [overallQuotient, setOverallQuotient] = useState<number>(88.4);

  const showToast = (msg: string) => {
    setToastMessage(msg);
    setTimeout(() => setToastMessage(null), 3500);
  };

  const handleToggleOffline = () => {
    const nextState = !isOfflineSimulated;
    setIsOfflineSimulated(nextState);
    showToast(
      nextState
        ? 'Local Cache: Operating on On-Premises School Hub'
        : 'Cloud Synchronized: Connected to DepEd Central LIS'
    );
  };

  const handleLoginSuccess = (role: UserRole) => {
    setCurrentUserRole(role);
    setIsLoggedIn(true);
  };

  const handleSignOut = () => {
    setIsLoggedIn(false);
  };

  const handleQuizCompleted = (scorePercent: number) => {
    setStudentMastery((prev) =>
      prev.map((item) => {
        if (item.code === 'MATATAG-S7-LS-2') {
          return {
            ...item,
            score: Math.min(1.0, Math.max(0.7, scorePercent / 100)),
            status: scorePercent >= 70 ? 'Mastered' : 'Reinforced',
            retentionRisk: 'Low (Refreshed)',
            lastAssessed: 'Just now'
          };
        }
        return item;
      })
    );
    setOverallQuotient(91.2);
    showToast('DepEd Mastery Quotient updated: 91.2% (Diagnostic Verified)');
  };

  if (!isLoggedIn) {
    return <LoginScreen onLoginSuccess={handleLoginSuccess} />;
  }

  const navItems: { role: UserRole; label: string; icon: React.ReactNode }[] = [
    { role: 'STUDENT', label: 'Student', icon: <GraduationCap className="w-5 h-5" /> },
    { role: 'TEACHER', label: 'Teacher', icon: <Users className="w-5 h-5" /> },
    { role: 'ADMIN', label: 'Admin', icon: <Shield className="w-5 h-5" /> },
    { role: 'PARENT', label: 'Parent', icon: <HeartHandshake className="w-5 h-5" /> }
  ];

  return (
    <div className="min-h-screen flex flex-col bg-pearl dark:bg-acrylic-deep text-acrylic dark:text-pearl-pure selection:bg-electric selection:text-pearl-pure pb-20 md:pb-8 transition-colors duration-200">
      {/* Top Navbar */}
      <Navbar
        currentRole={currentUserRole}
        onSelectRole={setCurrentUserRole}
        onSignOut={handleSignOut}
        isOfflineSimulated={isOfflineSimulated}
        onToggleOffline={handleToggleOffline}
        onOpenAiTutor={() => setIsAiTutorOpen(true)}
        onOpenAiStudio={() => setIsAiStudioOpen(true)}
      />

      {/* Main Container */}
      <main className="flex-1 max-w-7xl w-full mx-auto px-4 py-6">
        {currentUserRole === 'STUDENT' && (
          <StudentPortalScreen
            masteryList={studentMastery}
            overallQuotient={overallQuotient}
            onLaunchMicroQuiz={() => setIsMicroQuizOpen(true)}
            onOpenAiTutor={() => setIsAiTutorOpen(true)}
          />
        )}

        {currentUserRole === 'TEACHER' && (
          <TeacherPortalScreen
            onOpenSf10Export={() => setIsSf10Open(true)}
            onOpenAiStudio={() => setIsAiStudioOpen(true)}
          />
        )}

        {currentUserRole === 'ADMIN' && (
          <AdminPortalScreen
            onOpenAiStudio={() => setIsAiStudioOpen(true)}
          />
        )}

        {currentUserRole === 'PARENT' && (
          <ParentPortalScreen />
        )}
      </main>

      {/* Floating Action Button for AI Socratic Tutor */}
      <button
        onClick={() => setIsAiTutorOpen(true)}
        data-testid="fab_ai_socratic"
        title="Open THRU NORTH AI Socratic Tutor"
        className="fixed bottom-20 md:bottom-6 right-6 z-40 w-13 h-13 p-3.5 rounded-full bg-electric text-pearl-pure shadow-xl hover:bg-electric-hover active:scale-95 transition-all flex items-center justify-center border-2 border-pearl-pure dark:border-acrylic-surface"
      >
        <Sparkles className="w-6 h-6" />
      </button>

      {/* Mobile Bottom Navigation Bar */}
      <nav className="md:hidden fixed bottom-0 left-0 right-0 z-40 bg-pearl-pure dark:bg-acrylic-surface border-t border-pearl-border dark:border-acrylic-border py-1 px-2 flex justify-around items-center">
        {navItems.map((item) => {
          const isSelected = item.role === currentUserRole;
          return (
            <button
              key={item.role}
              onClick={() => setCurrentUserRole(item.role)}
              data-testid={`bottom_nav_${item.role.toLowerCase()}`}
              className={`flex flex-col items-center py-1 px-3 rounded-xl transition-all ${
                isSelected
                  ? 'text-electric font-bold'
                  : 'text-charcoal dark:text-charcoal-darkMuted hover:text-acrylic dark:hover:text-pearl-pure'
              }`}
            >
              {item.icon}
              <span className="text-[10px] mt-0.5 font-medium">{item.label}</span>
            </button>
          );
        })}
      </nav>

      {/* Toast Notification */}
      {toastMessage && (
        <div className="fixed bottom-20 md:bottom-6 left-6 z-50 p-3.5 rounded-xl bg-acrylic dark:bg-pearl-pure text-pearl-pure dark:text-acrylic text-xs font-mono shadow-2xl border border-pearl-border/20 animate-fade-in flex items-center gap-2 max-w-sm">
          <span className="w-2 h-2 rounded-full bg-electric shrink-0" />
          <span>{toastMessage}</span>
        </div>
      )}

      {/* Active Modals */}
      {isAiTutorOpen && (
        <AiTutorDialog onDismiss={() => setIsAiTutorOpen(false)} />
      )}

      {isAiStudioOpen && (
        <AiContentStudioDialog onDismiss={() => setIsAiStudioOpen(false)} />
      )}

      {isMicroQuizOpen && (
        <MicroQuizDialog
          onQuizCompleted={handleQuizCompleted}
          onDismiss={() => setIsMicroQuizOpen(false)}
        />
      )}

      {isSf10Open && (
        <Sf10ExportDialog onDismiss={() => setIsSf10Open(false)} />
      )}
    </div>
  );
};
