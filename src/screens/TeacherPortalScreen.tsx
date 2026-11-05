import React, { useState } from 'react';
import {
  Sparkles,
  FileDown,
  PlusCircle,
  Users,
  CheckCircle2,
  Calendar,
  ChevronUp
} from 'lucide-react';
import { CohortObservation } from '../types';
import { MOCK_COMPETENCIES, INITIAL_OBSERVATIONS } from '../data/mockData';
import { TnCard, TnBadge, TnButton } from '../components/UiComponents';

interface TeacherPortalScreenProps {
  onOpenSf10Export: () => void;
  onOpenAiStudio: () => void;
}

export const TeacherPortalScreen: React.FC<TeacherPortalScreenProps> = ({
  onOpenSf10Export,
  onOpenAiStudio
}) => {
  const [selectedSection, setSelectedSection] = useState('Grade 7 - Rizal (42 Students)');
  const [observations, setObservations] = useState<CohortObservation[]>(INITIAL_OBSERVATIONS);
  const [isLoggingObservation, setIsLoggingObservation] = useState(false);
  const [newNote, setNewNote] = useState('');
  const [newDirective, setNewDirective] = useState('');

  const handleSaveObservation = (e: React.FormEvent) => {
    e.preventDefault();
    if (!newNote.trim()) return;

    const newObs: CohortObservation = {
      id: `obs_${Date.now()}`,
      teacherName: 'Prof. Rogelio Santos, LPT',
      section: selectedSection.split(' (')[0],
      date: 'Just now',
      competencyCode: 'MATATAG-M7-NS-1',
      note: newNote,
      recommendation: newDirective || 'Spiral review integrated in next session.'
    };

    setObservations([newObs, ...observations]);
    setNewNote('');
    setNewDirective('');
    setIsLoggingObservation(false);
  };

  return (
    <div className="space-y-5 animate-fade-in">
      {/* Teacher Profile Banner */}
      <div className="flex flex-col md:flex-row md:items-center justify-between gap-3">
        <div>
          <h2 className="text-xl md:text-2xl font-black text-acrylic dark:text-pearl-pure font-sans">
            Educator Workspace: Prof. Rogelio Santos, LPT
          </h2>
          <p className="text-xs text-charcoal dark:text-charcoal-darkMuted font-mono mt-0.5">
            Science & Math Department Lead • DepEd MATATAG Cohort Tracking
          </p>
        </div>

        <div className="flex flex-wrap gap-2">
          <TnButton
            text="AI Content Studio"
            onClick={onOpenAiStudio}
            size="SM"
            leadingIcon={<Sparkles className="w-3.5 h-3.5" />}
            testTag="teacher_studio_btn"
          />
          <TnButton
            text="Generate SF10 / Form 137"
            variant="SECONDARY"
            onClick={onOpenSf10Export}
            size="SM"
            leadingIcon={<FileDown className="w-3.5 h-3.5" />}
            testTag="teacher_sf10_btn"
          />
        </div>
      </div>

      {/* Section Switcher Tabs */}
      <div className="flex flex-col sm:flex-row gap-2">
        {['Grade 7 - Rizal (42 Students)', 'Grade 7 - Bonifacio (40 Students)'].map((sec) => {
          const isSelected = sec === selectedSection;
          return (
            <button
              key={sec}
              onClick={() => setSelectedSection(sec)}
              className={`flex items-center gap-2 px-4 py-2.5 rounded-xl border text-xs font-mono font-bold transition-all ${
                isSelected
                  ? 'border-electric bg-electric/15 text-electric'
                  : 'border-pearl-border dark:border-acrylic-border bg-white/80 dark:bg-acrylic-surface/90 text-charcoal dark:text-charcoal-darkMuted hover:text-acrylic dark:hover:text-pearl-pure'
              }`}
            >
              <Users className="w-3.5 h-3.5" />
              <span>{sec}</span>
            </button>
          );
        })}
      </div>

      {/* Formative Observation Logger Card */}
      <TnCard className="p-5">
        <div className="flex items-center justify-between">
          <div>
            <h3 className="text-sm font-bold text-acrylic dark:text-pearl-pure">
              Formative Cohort Diagnostic Observation
            </h3>
            <p className="text-xs text-charcoal dark:text-charcoal-darkMuted">
              Record qualitative learner mastery notes tagged to DepEd spiral standards for {selectedSection.split(' (')[0]}.
            </p>
          </div>
          <TnButton
            text={isLoggingObservation ? "Cancel" : "Log Observation"}
            variant={isLoggingObservation ? "SECONDARY" : "PRIMARY"}
            size="SM"
            onClick={() => setIsLoggingObservation(!isLoggingObservation)}
            leadingIcon={isLoggingObservation ? <ChevronUp className="w-3.5 h-3.5" /> : <PlusCircle className="w-3.5 h-3.5" />}
            testTag="toggle_log_btn"
          />
        </div>

        {isLoggingObservation && (
          <form onSubmit={handleSaveObservation} className="mt-4 pt-4 border-t border-pearl-border/60 dark:border-acrylic-border/60 space-y-3 animate-fade-in">
            <div>
              <label className="block text-xs font-semibold text-acrylic dark:text-pearl-pure mb-1">
                Diagnostic Observation Note
              </label>
              <textarea
                value={newNote}
                onChange={(e) => setNewNote(e.target.value)}
                placeholder="e.g. Learners demonstrated high procedural fluency in negative integer addition but required scaffolding in real-world sari-sari store credit modeling..."
                rows={3}
                required
                className="w-full p-3 text-xs rounded-xl bg-pearl dark:bg-acrylic-surface border border-pearl-border dark:border-acrylic-border text-acrylic dark:text-pearl-pure focus:outline-none focus:border-electric"
              />
            </div>

            <div>
              <label className="block text-xs font-semibold text-acrylic dark:text-pearl-pure mb-1">
                Pedagogical Directive / Recommendation
              </label>
              <input
                type="text"
                value={newDirective}
                onChange={(e) => setNewDirective(e.target.value)}
                placeholder="e.g. Assign 5-minute micro practice on integer displacement before Thursday's lab"
                className="w-full px-3 py-2 text-xs rounded-xl bg-pearl dark:bg-acrylic-surface border border-pearl-border dark:border-acrylic-border text-acrylic dark:text-pearl-pure focus:outline-none focus:border-electric"
              />
            </div>

            <div className="flex justify-end gap-2">
              <TnButton
                type="submit"
                text="Save Observation to DepEd LIS Log"
                size="SM"
                testTag="save_observation_btn"
              />
            </div>
          </form>
        )}
      </TnCard>

      {/* Competencies Heatmap */}
      <div>
        <div className="flex items-center justify-between mb-3">
          <h3 className="text-xs font-mono font-bold text-charcoal dark:text-charcoal-darkMuted tracking-widest uppercase">
            DEPED COMPETENCIES HEATMAP & COHORT MASTERY
          </h3>
          <span className="text-xs font-mono text-charcoal dark:text-charcoal-darkMuted">
            {MOCK_COMPETENCIES.length} Tracked Standards
          </span>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-3">
          {MOCK_COMPETENCIES.map((comp) => {
            const isOnTarget = comp.cohortMasteryPercentage >= 80;
            return (
              <TnCard key={comp.id} className="p-4 space-y-2">
                <div className="flex items-start justify-between gap-2">
                  <div className="space-y-0.5">
                    <div className="flex items-center gap-1.5">
                      <span className="font-mono text-xs font-bold text-electric">
                        {comp.code}
                      </span>
                      <TnBadge
                        text={comp.curriculumVersion}
                        variant={comp.curriculumVersion === 'MATATAG' ? 'BLUE' : 'SILVER'}
                      />
                    </div>
                    <h4 className="text-xs font-bold text-acrylic dark:text-pearl-pure">
                      {comp.title}
                    </h4>
                  </div>
                  <TnBadge
                    text={isOnTarget ? 'ON TARGET' : 'NEEDS REVIEW'}
                    variant={isOnTarget ? 'BLUE' : 'WARNING'}
                  />
                </div>

                <p className="text-[11px] text-charcoal dark:text-charcoal-darkMuted line-clamp-2 leading-relaxed">
                  {comp.description}
                </p>

                <div className="pt-2 border-t border-pearl-border/50 dark:border-acrylic-border/50 flex items-center gap-3">
                  <span className="text-[10px] font-mono text-charcoal dark:text-charcoal-darkMuted">
                    Cohort Mastery:
                  </span>
                  <div className="flex-1 bg-pearl-elevated dark:bg-acrylic-elevated h-1.5 rounded-full overflow-hidden">
                    <div
                      className={`h-full rounded-full ${isOnTarget ? 'bg-electric' : 'bg-charcoal dark:bg-charcoal-darkMuted'}`}
                      style={{ width: `${comp.cohortMasteryPercentage}%` }}
                    />
                  </div>
                  <span className="font-mono text-xs font-bold text-acrylic dark:text-pearl-pure">
                    {comp.cohortMasteryPercentage}%
                  </span>
                </div>
              </TnCard>
            );
          })}
        </div>
      </div>

      {/* Logged Cohort Observations Feed */}
      <div>
        <h3 className="text-xs font-mono font-bold text-charcoal dark:text-charcoal-darkMuted tracking-widest uppercase mb-3">
          LOGGED COHORT OBSERVATIONS
        </h3>

        <div className="space-y-3">
          {observations.map((obs) => (
            <TnCard key={obs.id} className="p-4 space-y-1.5">
              <div className="flex items-center justify-between">
                <span className="font-mono text-xs font-bold text-electric">
                  {obs.section} • {obs.competencyCode}
                </span>
                <span className="text-[10px] font-mono text-charcoal dark:text-charcoal-darkMuted flex items-center gap-1">
                  <Calendar className="w-3 h-3 text-electric" />
                  {obs.date}
                </span>
              </div>
              <p className="text-xs text-acrylic dark:text-pearl-pure leading-relaxed">
                {obs.note}
              </p>
              <div className="pt-1 flex items-center gap-1.5 text-[11px] font-mono text-electric">
                <CheckCircle2 className="w-3.5 h-3.5 shrink-0" />
                <span>Directive: {obs.recommendation}</span>
              </div>
            </TnCard>
          ))}
        </div>
      </div>
    </div>
  );
};
