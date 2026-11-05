import React, { useState } from 'react';
import { X, CheckCircle2, AlertCircle, ArrowRight, Trophy } from 'lucide-react';
import { TnButton, TnBadge } from '../UiComponents';
import { SAMPLE_QUIZ_QUESTIONS } from '../../data/mockData';

interface MicroQuizDialogProps {
  onQuizCompleted: (scorePercent: number) => void;
  onDismiss: () => void;
}

export const MicroQuizDialog: React.FC<MicroQuizDialogProps> = ({
  onQuizCompleted,
  onDismiss
}) => {
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [selectedOption, setSelectedOption] = useState<number | null>(null);
  const [isAnswerSubmitted, setIsAnswerSubmitted] = useState(false);
  const [correctAnswersCount, setCorrectAnswersCount] = useState(0);
  const [isFinished, setIsFinished] = useState(false);

  const questions = SAMPLE_QUIZ_QUESTIONS;
  const currentQ = questions[currentQuestionIndex];

  const handleSubmitOption = () => {
    if (selectedOption === null) return;
    setIsAnswerSubmitted(true);
    if (selectedOption === currentQ.correctOptionIndex) {
      setCorrectAnswersCount((prev) => prev + 1);
    }
  };

  const handleNext = () => {
    if (currentQuestionIndex < questions.length - 1) {
      setCurrentQuestionIndex((prev) => prev + 1);
      setSelectedOption(null);
      setIsAnswerSubmitted(false);
    } else {
      setIsFinished(true);
    }
  };

  const finalScorePercent = Math.round((correctAnswersCount / questions.length) * 100);

  const handleFinish = () => {
    onQuizCompleted(finalScorePercent);
    onDismiss();
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-acrylic/60 dark:bg-black/75 backdrop-blur-sm animate-fade-in">
      <div className="relative w-full max-w-xl bg-pearl-pure dark:bg-acrylic-surface rounded-2xl shadow-2xl border border-pearl-border dark:border-acrylic-border overflow-hidden flex flex-col max-h-[85vh]">
        {/* Header */}
        <div className="flex items-center justify-between px-5 py-4 border-b border-pearl-border dark:border-acrylic-border bg-pearl dark:bg-acrylic-deep">
          <div className="flex items-center gap-2.5">
            <TnBadge text={currentQ ? currentQ.competencyCode : 'ASSESSMENT'} variant="BLUE" />
            <span className="text-xs font-mono text-charcoal dark:text-charcoal-darkMuted">
              {isFinished ? 'Complete' : `Question ${currentQuestionIndex + 1} of ${questions.length}`}
            </span>
          </div>
          <button
            onClick={onDismiss}
            data-testid="quiz_close_button"
            className="p-1.5 rounded-lg text-charcoal dark:text-charcoal-darkMuted hover:bg-pearl-elevated dark:hover:bg-acrylic-elevated"
          >
            <X className="w-4 h-4" />
          </button>
        </div>

        {/* Content */}
        <div className="flex-1 overflow-y-auto p-5">
          {isFinished ? (
            <div className="text-center py-6 space-y-4">
              <div className="w-16 h-16 mx-auto rounded-full bg-electric/15 text-electric flex items-center justify-center">
                <Trophy className="w-8 h-8" />
              </div>
              <div>
                <h3 className="text-xl font-black text-acrylic dark:text-pearl-pure font-sans">
                  Diagnostic Evaluation Complete
                </h3>
                <p className="text-xs text-charcoal dark:text-charcoal-darkMuted mt-1">
                  Mastery retention verified for MATATAG Junior High Science and Math.
                </p>
              </div>

              <div className="p-4 rounded-xl bg-pearl-elevated dark:bg-acrylic-elevated border border-pearl-border dark:border-acrylic-border max-w-xs mx-auto">
                <div className="text-3xl font-black font-mono text-electric">
                  {finalScorePercent}%
                </div>
                <div className="text-xs font-medium text-charcoal dark:text-charcoal-darkMuted mt-1">
                  {correctAnswersCount} of {questions.length} Questions Correct
                </div>
              </div>

              <p className="text-[11px] text-charcoal dark:text-charcoal-darkMuted max-w-sm mx-auto">
                Your mastery records will now update in the DepEd central learner quotient log.
              </p>

              <TnButton
                text="Record Mastery & Return to Dashboard"
                onClick={handleFinish}
                size="MD"
                className="w-full"
                testTag="quiz_finish_button"
              />
            </div>
          ) : (
            <div className="space-y-4">
              <div>
                <span className="text-[11px] font-bold text-electric font-mono tracking-wide uppercase">
                  {currentQ.contextPrompt}
                </span>
                <h3 className="text-sm md:text-base font-semibold text-acrylic dark:text-pearl-pure mt-1 leading-snug">
                  {currentQ.questionText}
                </h3>
              </div>

              {/* Options */}
              <div className="space-y-2 pt-2">
                {currentQ.options.map((opt, idx) => {
                  const isSelected = selectedOption === idx;
                  const isCorrect = idx === currentQ.correctOptionIndex;

                  let borderClass = 'border-pearl-border dark:border-acrylic-border';
                  let bgClass = 'bg-pearl dark:bg-acrylic-elevated';

                  if (isAnswerSubmitted) {
                    if (isCorrect) {
                      borderClass = 'border-electric bg-electric/10 text-electric';
                    } else if (isSelected && !isCorrect) {
                      borderClass = 'border-charcoal bg-pearl-elevated dark:bg-acrylic-surface';
                    }
                  } else if (isSelected) {
                    borderClass = 'border-electric bg-electric/10';
                  }

                  return (
                    <button
                      key={idx}
                      disabled={isAnswerSubmitted}
                      onClick={() => setSelectedOption(idx)}
                      data-testid={`quiz_option_${idx}`}
                      className={`w-full text-left p-3 rounded-xl border transition-all text-xs md:text-sm flex items-center gap-3 ${borderClass} ${bgClass}`}
                    >
                      <span className={`w-6 h-6 rounded-lg flex items-center justify-center font-mono font-bold text-xs shrink-0 ${
                        isSelected ? 'bg-electric text-pearl-pure' : 'bg-pearl-elevated dark:bg-acrylic-surface text-charcoal dark:text-charcoal-darkMuted'
                      }`}>
                        {String.fromCharCode(65 + idx)}
                      </span>
                      <span className="flex-1 text-acrylic dark:text-pearl-pure font-medium">
                        {opt}
                      </span>
                    </button>
                  );
                })}
              </div>

              {/* Explanation Banner */}
              {isAnswerSubmitted && (
                <div className="p-3.5 rounded-xl border border-electric/40 bg-electric/5 space-y-1 text-xs animate-fade-in">
                  <div className="flex items-center gap-1.5 font-bold text-electric font-sans">
                    {selectedOption === currentQ.correctOptionIndex ? (
                      <>
                        <CheckCircle2 className="w-4 h-4" />
                        <span>Correct! Diagnostic Verified</span>
                      </>
                    ) : (
                      <>
                        <AlertCircle className="w-4 h-4" />
                        <span>Pedagogical Analysis</span>
                      </>
                    )}
                  </div>
                  <p className="text-[11.5px] text-charcoal dark:text-charcoal-darkMuted leading-relaxed">
                    {currentQ.explanation}
                  </p>
                </div>
              )}
            </div>
          )}
        </div>

        {/* Action Button */}
        {!isFinished && (
          <div className="p-4 border-t border-pearl-border dark:border-acrylic-border bg-pearl dark:bg-acrylic-deep flex justify-end">
            {!isAnswerSubmitted ? (
              <TnButton
                text="Submit Answer"
                onClick={handleSubmitOption}
                disabled={selectedOption === null}
                size="MD"
                testTag="quiz_submit_option_btn"
              />
            ) : (
              <TnButton
                text={currentQuestionIndex < questions.length - 1 ? "Next Question" : "View Results"}
                onClick={handleNext}
                leadingIcon={<ArrowRight className="w-4 h-4" />}
                size="MD"
                testTag="quiz_next_btn"
              />
            )}
          </div>
        )}
      </div>
    </div>
  );
};
