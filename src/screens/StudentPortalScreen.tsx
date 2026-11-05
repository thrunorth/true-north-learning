import React from 'react';
import { Sparkles, Play, Clock, BookOpen, AlertTriangle } from 'lucide-react';
import { StudentMasteryItem } from '../types';
import { TnCard, TnBadge, TnButton, MasteryRingIndicator } from '../components/UiComponents';

interface StudentPortalScreenProps {
  masteryList: StudentMasteryItem[];
  overallQuotient: number;
  onLaunchMicroQuiz: () => void;
  onOpenAiTutor: () => void;
}

export const StudentPortalScreen: React.FC<StudentPortalScreenProps> = ({
  masteryList,
  overallQuotient,
  onLaunchMicroQuiz,
  onOpenAiTutor
}) => {
  return (
    <div className="space-y-5 animate-fade-in">
      {/* Student Greeting */}
      <div className="flex flex-col md:flex-row md:items-center justify-between gap-3">
        <div>
          <h2 className="text-xl md:text-2xl font-black text-acrylic dark:text-pearl-pure font-sans">
            Kumusta, Juan!
          </h2>
          <p className="text-xs text-charcoal dark:text-charcoal-darkMuted font-mono mt-0.5">
            Grade 7 - Rizal • Makati Science Academic Center • DepEd MATATAG
          </p>
        </div>

        <div className="flex gap-2">
          <TnButton
            text="AI Socratic Tutor"
            onClick={onOpenAiTutor}
            size="SM"
            leadingIcon={<Sparkles className="w-3.5 h-3.5" />}
            testTag="student_tutor_btn"
          />
        </div>
      </div>

      {/* Hero Mastery Quotient Card */}
      <TnCard className="p-5 md:p-6">
        <div className="flex flex-col md:flex-row items-center gap-6">
          <MasteryRingIndicator
            percentage={Math.round(overallQuotient)}
            size={78}
            strokeWidth={6}
          />
          <div className="flex-1 text-center md:text-left space-y-1">
            <div className="flex flex-wrap items-center justify-center md:justify-start gap-2">
              <span className="font-mono text-xs font-bold text-electric uppercase tracking-wider">
                Overall Mastery Quotient
              </span>
              <TnBadge text="DEPED GRADE 7 MATATAG SPIRAL" variant="BLUE" />
            </div>
            <h3 className="text-lg font-bold text-acrylic dark:text-pearl-pure">
              Composite Academic Retention: {overallQuotient.toFixed(1)}%
            </h3>
            <p className="text-xs text-charcoal dark:text-charcoal-darkMuted leading-relaxed max-w-xl">
              Constructivist spiral competency tracking reflects your current diagnostic mastery across Mathematics, Life Sciences, and English.
            </p>
          </div>
        </div>

        {/* Progress bar */}
        <div className="mt-4 pt-4 border-t border-pearl-border/60 dark:border-acrylic-border/60">
          <div className="w-full bg-pearl-elevated dark:bg-acrylic-elevated h-2 rounded-full overflow-hidden">
            <div
              className="bg-electric h-full rounded-full transition-all duration-700 ease-out"
              style={{ width: `${overallQuotient}%` }}
            />
          </div>
        </div>
      </TnCard>

      {/* Retention Decay Notice */}
      <TnCard className="p-4 border-l-4 border-l-charcoal">
        <div className="flex items-start gap-3">
          <AlertTriangle className="w-4 h-4 text-charcoal dark:text-charcoal-darkMuted shrink-0 mt-0.5" />
          <div className="flex-1">
            <div className="flex items-center justify-between">
              <span className="text-[11px] font-mono font-bold text-charcoal dark:text-charcoal-darkMuted tracking-wider uppercase">
                DIAGNOSTIC NOTICE: RETENTION DECAY ALERT
              </span>
              <TnBadge text="7-DAY DECAY" variant="WARNING" />
            </div>
            <p className="text-xs text-acrylic dark:text-pearl-pure mt-1 font-medium">
              MATATAG-S7-LS-2 (Microscopy & Cellular Organization) is entering the spiral reinforcement window.
            </p>
            <p className="text-[11px] text-charcoal dark:text-charcoal-darkMuted mt-0.5">
              Take a 2-minute diagnostic micro-practice to refresh retention memory before your next laboratory session.
            </p>
          </div>
        </div>
      </TnCard>

      {/* Active Competencies Feed */}
      <div>
        <div className="flex items-center justify-between mb-3">
          <h3 className="text-xs font-mono font-bold text-charcoal dark:text-charcoal-darkMuted tracking-widest uppercase">
            DEPED MATATAG COMPETENCIES & RETENTION
          </h3>
          <span className="text-xs font-mono text-charcoal dark:text-charcoal-darkMuted">
            {masteryList.length} Active Records
          </span>
        </div>

        <div className="space-y-3">
          {masteryList.map((item) => {
            const isTargetQuiz = item.code === 'MATATAG-S7-LS-2';
            return (
              <TnCard
                key={item.code}
                highlight={isTargetQuiz}
                className="p-4 md:p-5 flex flex-col md:flex-row md:items-center justify-between gap-4"
              >
                <div className="space-y-1.5 flex-1">
                  <div className="flex flex-wrap items-center gap-2">
                    <span className="font-mono text-xs font-bold text-electric">
                      {item.code}
                    </span>
                    <TnBadge
                      text={item.status.toUpperCase()}
                      variant={item.score >= 0.85 ? 'BLUE' : 'SILVER'}
                    />
                    <span className="text-[10.5px] font-mono text-charcoal dark:text-charcoal-darkMuted">
                      {item.subject}
                    </span>
                  </div>

                  <h4 className="text-sm font-bold text-acrylic dark:text-pearl-pure">
                    {item.title}
                  </h4>

                  <p className="text-xs text-charcoal dark:text-charcoal-darkMuted leading-relaxed max-w-2xl">
                    {item.description}
                  </p>

                  <div className="flex items-center gap-4 text-[10.5px] font-mono text-charcoal dark:text-charcoal-darkMuted pt-1">
                    <span className="flex items-center gap-1">
                      <Clock className="w-3 h-3 text-electric" />
                      Assessed: {item.lastAssessed}
                    </span>
                    <span>Risk: {item.retentionRisk}</span>
                  </div>
                </div>

                <div className="flex md:flex-col items-center md:items-end justify-between gap-3 shrink-0 pt-3 md:pt-0 border-t md:border-t-0 border-pearl-border/60 dark:border-acrylic-border/60">
                  <div className="text-left md:text-right">
                    <div className="text-lg font-black font-mono text-electric">
                      {Math.round(item.score * 100)}%
                    </div>
                    <div className="text-[10px] text-charcoal dark:text-charcoal-darkMuted font-mono">
                      Mastery Index
                    </div>
                  </div>

                  {isTargetQuiz ? (
                    <TnButton
                      text="Launch Micro-Quiz"
                      onClick={onLaunchMicroQuiz}
                      size="SM"
                      leadingIcon={<Play className="w-3 h-3 fill-current" />}
                      testTag="launch_quiz_btn"
                    />
                  ) : (
                    <TnButton
                      text="Inquire Tutor"
                      variant="SECONDARY"
                      onClick={onOpenAiTutor}
                      size="SM"
                      leadingIcon={<BookOpen className="w-3 h-3" />}
                    />
                  )}
                </div>
              </TnCard>
            );
          })}
        </div>
      </div>
    </div>
  );
};
