import React, { useState } from 'react';
import { X, FileDown, CheckCircle, ShieldCheck } from 'lucide-react';
import { TnButton } from '../UiComponents';

interface Sf10ExportDialogProps {
  onDismiss: () => void;
}

export const Sf10ExportDialog: React.FC<Sf10ExportDialogProps> = ({ onDismiss }) => {
  const [isExported, setIsExported] = useState(false);

  const subjects = [
    { subject: 'Mathematics (MATATAG M7)', grade: '94 (Outstanding)', status: 'PASSED' },
    { subject: 'Science (MATATAG S7)', grade: '88 (Very Satisfactory)', status: 'PASSED' },
    { subject: 'English (MATATAG E7)', grade: '91 (Outstanding)', status: 'PASSED' },
    { subject: 'Makabansa / AP', grade: '93 (Outstanding)', status: 'PASSED' }
  ];

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-acrylic/60 dark:bg-black/75 backdrop-blur-sm animate-fade-in">
      <div className="relative w-full max-w-xl bg-pearl-pure dark:bg-acrylic-surface rounded-2xl shadow-2xl border border-pearl-border dark:border-acrylic-border overflow-hidden flex flex-col max-h-[85vh]">
        {/* Header */}
        <div className="flex items-center justify-between px-5 py-4 border-b border-pearl-border dark:border-acrylic-border bg-pearl dark:bg-acrylic-deep">
          <div className="flex items-center gap-2.5">
            <div className="w-8 h-8 rounded-lg bg-electric text-pearl-pure flex items-center justify-center shadow-sm">
              <FileDown className="w-4 h-4" />
            </div>
            <div>
              <h2 className="text-sm font-bold text-acrylic dark:text-pearl-pure font-sans">
                DepEd School Form 10 (SF10 / Form 137)
              </h2>
              <p className="text-[10px] text-charcoal dark:text-charcoal-darkMuted font-mono">
                Official Learner Permanent Record • DepEd LIS Automated Pipeline
              </p>
            </div>
          </div>
          <button
            onClick={onDismiss}
            data-testid="sf10_close_btn"
            className="p-1.5 rounded-lg text-charcoal dark:text-charcoal-darkMuted hover:bg-pearl-elevated dark:hover:bg-acrylic-elevated"
          >
            <X className="w-4 h-4" />
          </button>
        </div>

        {/* Content */}
        <div className="flex-1 overflow-y-auto p-5 space-y-4">
          {/* Metadata Card */}
          <div className="p-3.5 rounded-xl bg-pearl dark:bg-acrylic-elevated border border-pearl-border dark:border-acrylic-border text-xs space-y-1.5">
            <div className="flex justify-between">
              <span className="text-charcoal dark:text-charcoal-darkMuted">Learner Reference Number (LRN):</span>
              <span className="font-mono font-bold text-acrylic dark:text-pearl-pure">109847291048</span>
            </div>
            <div className="flex justify-between">
              <span className="text-charcoal dark:text-charcoal-darkMuted">Learner Name:</span>
              <span className="font-bold text-acrylic dark:text-pearl-pure">Juan de la Cruz</span>
            </div>
            <div className="flex justify-between">
              <span className="text-charcoal dark:text-charcoal-darkMuted">School & Division:</span>
              <span className="text-acrylic dark:text-pearl-pure">Makati Science • Division of City Schools Makati</span>
            </div>
            <div className="flex justify-between">
              <span className="text-charcoal dark:text-charcoal-darkMuted">Curriculum Standard:</span>
              <span className="font-mono text-electric font-bold">DepEd MATATAG Key Stage 3</span>
            </div>
          </div>

          {/* Table */}
          <div>
            <h4 className="text-xs font-bold text-acrylic dark:text-pearl-pure mb-2 font-mono uppercase tracking-wider">
              Quarterly Competency Performance
            </h4>
            <div className="border border-pearl-border dark:border-acrylic-border rounded-xl overflow-hidden text-xs">
              <div className="bg-pearl-elevated dark:bg-acrylic-deep px-3.5 py-2 font-mono font-bold text-[10.5px] text-charcoal dark:text-charcoal-darkMuted grid grid-cols-12">
                <span className="col-span-6">Learning Area</span>
                <span className="col-span-4">Quarter Mark</span>
                <span className="col-span-2 text-right">Remarks</span>
              </div>
              {subjects.map((s, idx) => (
                <div
                  key={idx}
                  className={`px-3.5 py-2.5 grid grid-cols-12 items-center border-t border-pearl-border/60 dark:border-acrylic-border/60 ${
                    idx % 2 === 0 ? 'bg-pearl-pure dark:bg-acrylic-surface' : 'bg-pearl/50 dark:bg-acrylic-elevated/40'
                  }`}
                >
                  <span className="col-span-6 font-medium text-acrylic dark:text-pearl-pure">{s.subject}</span>
                  <span className="col-span-4 text-charcoal dark:text-charcoal-darkMuted font-mono">{s.grade}</span>
                  <span className="col-span-2 text-right font-mono font-bold text-electric text-[10px]">{s.status}</span>
                </div>
              ))}
            </div>
          </div>

          {/* Export Success Banner */}
          {isExported && (
            <div className="p-3.5 rounded-xl border border-electric/40 bg-electric/10 space-y-1 text-xs animate-fade-in">
              <div className="flex items-center gap-1.5 font-bold text-electric">
                <CheckCircle className="w-4 h-4" />
                <span>DepEd SF10 PDF Bundle Prepared</span>
              </div>
              <p className="text-[11px] text-charcoal dark:text-charcoal-darkMuted font-mono">
                File: SF10_7-Rizal_Juandelacruz.pdf (Digitally certified for DepEd LIS Upload)
              </p>
            </div>
          )}

          <div className="flex items-center gap-2 text-[10.5px] text-charcoal dark:text-charcoal-darkMuted font-mono pt-1">
            <ShieldCheck className="w-3.5 h-3.5 text-electric" />
            <span>Cryptographically sealed under DepEd ICTS Digital Verification Guidelines</span>
          </div>
        </div>

        {/* Footer */}
        <div className="p-4 border-t border-pearl-border dark:border-acrylic-border bg-pearl dark:bg-acrylic-deep flex justify-end gap-2">
          <TnButton
            text="Done"
            variant="SECONDARY"
            size="SM"
            onClick={onDismiss}
            testTag="sf10_done_btn"
          />
          <TnButton
            text={isExported ? "Re-Generate Bundle" : "Generate SF10 / Form 137"}
            variant="PRIMARY"
            size="SM"
            leadingIcon={<FileDown className="w-3.5 h-3.5" />}
            onClick={() => setIsExported(true)}
            testTag="sf10_generate_btn"
          />
        </div>
      </div>
    </div>
  );
};
