import React, { useState } from 'react';
import { X, Sparkles, FileText, CheckCircle2 } from 'lucide-react';
import { TnButton, TnBadge } from '../UiComponents';

interface AiContentStudioDialogProps {
  onDismiss: () => void;
}

export const AiContentStudioDialog: React.FC<AiContentStudioDialogProps> = ({ onDismiss }) => {
  const [selectedCompetency, setSelectedCompetency] = useState('MATATAG-M7-NS-1');
  const [outputFormat, setOutputFormat] = useState('LAS'); // LAS, QUIZ, REMEDIATION
  const [isGenerating, setIsGenerating] = useState(false);
  const [generatedCount, setGeneratedCount] = useState(1);
  const [showSpecimen, setShowSpecimen] = useState(true);

  const handleGenerate = () => {
    setIsGenerating(true);
    setTimeout(() => {
      setIsGenerating(false);
      setGeneratedCount((c) => c + 1);
      setShowSpecimen(true);
    }, 700);
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-acrylic/60 dark:bg-black/75 backdrop-blur-sm animate-fade-in">
      <div className="relative w-full max-w-xl bg-pearl-pure dark:bg-acrylic-surface rounded-2xl shadow-2xl border border-pearl-border dark:border-acrylic-border overflow-hidden flex flex-col max-h-[85vh]">
        {/* Header */}
        <div className="flex items-center justify-between px-5 py-4 border-b border-pearl-border dark:border-acrylic-border bg-pearl dark:bg-acrylic-deep">
          <div className="flex items-center gap-2.5">
            <div className="w-8 h-8 rounded-lg bg-electric text-pearl-pure flex items-center justify-center shadow-sm">
              <Sparkles className="w-4 h-4" />
            </div>
            <div>
              <h2 className="text-sm font-bold text-acrylic dark:text-pearl-pure font-sans">
                DepEd AI Content Studio
              </h2>
              <p className="text-[10px] text-charcoal dark:text-charcoal-darkMuted font-mono">
                MATATAG Lesson Exemplars & Formative Activity Generators
              </p>
            </div>
          </div>
          <button
            onClick={onDismiss}
            data-testid="ai_studio_close_btn"
            className="p-1.5 rounded-lg text-charcoal dark:text-charcoal-darkMuted hover:bg-pearl-elevated dark:hover:bg-acrylic-elevated"
          >
            <X className="w-4 h-4" />
          </button>
        </div>

        {/* Body */}
        <div className="flex-1 overflow-y-auto p-5 space-y-4">
          {/* Target Competency */}
          <div>
            <label className="block text-xs font-semibold text-acrylic dark:text-pearl-pure mb-1.5">
              Target Competency
            </label>
            <select
              value={selectedCompetency}
              onChange={(e) => setSelectedCompetency(e.target.value)}
              className="w-full px-3 py-2 text-xs rounded-xl bg-pearl-pure dark:bg-acrylic-surface border border-pearl-border dark:border-acrylic-border text-acrylic dark:text-pearl-pure focus:outline-none focus:border-electric font-mono"
            >
              <option value="MATATAG-M7-NS-1">MATATAG-M7-NS-1 (Rational Numbers & Real-World Operations)</option>
              <option value="MATATAG-S7-LS-2">MATATAG-S7-LS-2 (Microscopy & Cellular Organization)</option>
              <option value="MATATAG-MAK-03">MATATAG-MAK-03 (Pagkakakilanlang Pilipino at Pamayanan)</option>
              <option value="STEM_PC11AG-Ia-1">STEM_PC11AG-Ia-1 (Conic Sections & Analytical Geometry)</option>
            </select>
          </div>

          {/* Output Format */}
          <div>
            <label className="block text-xs font-semibold text-acrylic dark:text-pearl-pure mb-1.5">
              Material Format
            </label>
            <div className="grid grid-cols-3 gap-2">
              {[
                { id: 'LAS', label: 'Learner Activity Sheet' },
                { id: 'QUIZ', label: '5-Item Diagnostic' },
                { id: 'REMEDIATION', label: 'Spiral Remediation' }
              ].map((fmt) => (
                <button
                  key={fmt.id}
                  onClick={() => setOutputFormat(fmt.id)}
                  className={`p-2 rounded-xl text-xs text-left border transition-all ${
                    outputFormat === fmt.id
                      ? 'border-electric bg-electric/10 text-electric font-bold'
                      : 'border-pearl-border dark:border-acrylic-border bg-pearl dark:bg-acrylic-elevated text-charcoal dark:text-charcoal-darkMuted'
                  }`}
                >
                  <p className="font-mono text-[10px] uppercase font-bold">{fmt.id}</p>
                  <p className="text-[11px] truncate mt-0.5">{fmt.label}</p>
                </button>
              ))}
            </div>
          </div>

          {/* Pedagogical Metadata */}
          <div className="p-3 rounded-xl bg-pearl-elevated dark:bg-acrylic-elevated/70 border border-pearl-border dark:border-acrylic-border text-[11px] space-y-1">
            <div className="flex justify-between">
              <span className="text-charcoal dark:text-charcoal-darkMuted">Curriculum Standard:</span>
              <span className="font-mono font-bold text-electric">DepEd MATATAG (Decongested)</span>
            </div>
            <div className="flex justify-between">
              <span className="text-charcoal dark:text-charcoal-darkMuted">Pedagogical Anchor:</span>
              <span className="font-semibold text-acrylic dark:text-pearl-pure">Constructivist Spiral Progression</span>
            </div>
          </div>

          {/* Generated Specimen Preview */}
          {showSpecimen && (
            <div className="p-3.5 rounded-xl border border-electric/30 bg-electric/5 space-y-2">
              <div className="flex items-center justify-between">
                <TnBadge text={`SPECIMEN #0${generatedCount}`} variant="BLUE" />
                <span className="text-[10px] font-mono text-charcoal dark:text-charcoal-darkMuted">
                  DepEd Order No. 10 s. 2024
                </span>
              </div>
              <p className="text-xs font-bold text-acrylic dark:text-pearl-pure">
                Topic: Real-World Rational Modeling in Filipino Daily Commerce
              </p>
              <p className="text-[11px] text-charcoal dark:text-charcoal-darkMuted leading-relaxed">
                Objective: Learners demonstrate mastery of positive/negative integer operations by calculating sari-sari store credit ledgers and change computation under real-life scenarios.
              </p>
              <div className="pt-1 flex items-center gap-1.5 text-[10.5px] font-mono text-electric">
                <CheckCircle2 className="w-3.5 h-3.5" />
                <span>Ready for DepEd LMS / Offline PWA distribution</span>
              </div>
            </div>
          )}
        </div>

        {/* Footer */}
        <div className="p-4 border-t border-pearl-border dark:border-acrylic-border bg-pearl dark:bg-acrylic-deep flex justify-end gap-2">
          <TnButton
            text="Close"
            variant="SECONDARY"
            size="SM"
            onClick={onDismiss}
          />
          <TnButton
            text={isGenerating ? "Synthesizing..." : "Generate DepEd Compliant Material"}
            variant="PRIMARY"
            size="SM"
            leadingIcon={<FileText className="w-3.5 h-3.5" />}
            onClick={handleGenerate}
            disabled={isGenerating}
            testTag="ai_studio_generate_btn"
          />
        </div>
      </div>
    </div>
  );
};
