import React, { useState } from 'react';
import {
  CheckCircle2,
  GraduationCap,
  Calendar,
  MessageSquare
} from 'lucide-react';
import { TnCard, TnBadge, TnButton } from '../components/UiComponents';
import { INITIAL_STUDENT_MASTERY } from '../data/mockData';

export const ParentPortalScreen: React.FC = () => {
  const [showFeedbackSent, setShowFeedbackSent] = useState(false);
  const [isMessagingOpen, setIsMessagingOpen] = useState(false);
  const [messageSent, setMessageSent] = useState(false);
  const [parentMessage, setParentMessage] = useState('');

  const handleSendMessage = (e: React.FormEvent) => {
    e.preventDefault();
    if (!parentMessage.trim()) return;
    setMessageSent(true);
    setParentMessage('');
    setTimeout(() => {
      setMessageSent(false);
      setIsMessagingOpen(false);
    }, 2500);
  };

  return (
    <div className="space-y-5 animate-fade-in">
      {/* Parent Greeting */}
      <div>
        <h2 className="text-xl md:text-2xl font-black text-acrylic dark:text-pearl-pure font-sans">
          Learner Guardian Portal: Maria Elena de la Cruz
        </h2>
        <p className="text-xs text-charcoal dark:text-charcoal-darkMuted font-mono mt-0.5">
          Monitoring Juan de la Cruz • Grade 7 - Rizal • Makati Science Academic Center
        </p>
      </div>

      {/* 3 Telemetry Summary Cards */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-3">
        <TnCard className="p-4 flex items-center gap-3.5">
          <div className="w-10 h-10 rounded-xl bg-electric/15 text-electric flex items-center justify-center shrink-0">
            <GraduationCap className="w-5 h-5" />
          </div>
          <div>
            <div className="text-[10px] font-mono text-charcoal dark:text-charcoal-darkMuted uppercase font-bold">
              ACTIVE LEARNER
            </div>
            <div className="text-sm font-bold text-acrylic dark:text-pearl-pure">
              Juan de la Cruz
            </div>
            <div className="text-xs text-electric font-medium">
              Grade 7 • Section Rizal
            </div>
          </div>
        </TnCard>

        <TnCard className="p-4 space-y-1">
          <div className="text-[10px] font-mono text-charcoal dark:text-charcoal-darkMuted uppercase font-bold">
            DEPED QUARTER EQUIV
          </div>
          <div className="text-sm font-bold text-electric">
            92 (Outstanding)
          </div>
          <div className="text-[11px] text-charcoal dark:text-charcoal-darkMuted">
            Calculated from verified mastery
          </div>
        </TnCard>

        <TnCard className="p-4 space-y-1">
          <div className="text-[10px] font-mono text-charcoal dark:text-charcoal-darkMuted uppercase font-bold">
            INTERVENTIONS PENDING
          </div>
          <div className="text-sm font-bold text-acrylic dark:text-pearl-pure">
            None Pending
          </div>
          <div className="text-[11px] text-charcoal dark:text-charcoal-darkMuted">
            All spiral benchmarks verified
          </div>
        </TnCard>
      </div>

      {/* Teacher Observation & Direct Communication */}
      <TnCard className="p-5 space-y-4">
        <div>
          <div className="flex items-center justify-between">
            <span className="text-[11px] font-mono font-bold text-electric uppercase tracking-wider">
              LATEST FORMATIVE OBSERVATION FROM HOMEROOM ADVISER
            </span>
            <span className="text-[10px] font-mono text-charcoal dark:text-charcoal-darkMuted flex items-center gap-1">
              <Calendar className="w-3 h-3 text-electric" />
              Nov 2, 2026
            </span>
          </div>
          <h3 className="text-sm font-bold text-acrylic dark:text-pearl-pure mt-1">
            Prof. Rogelio Santos, LPT (Science & Math Lead)
          </h3>
          <p className="text-xs text-charcoal dark:text-charcoal-darkMuted leading-relaxed mt-1">
            "Juan demonstrates exemplary abstract reasoning in rational number calculations. Under the MATATAG spiral progression, we will introduce cellular biology modeling next week. No home interventions required."
          </p>
        </div>

        {showFeedbackSent && (
          <div className="p-3 rounded-xl border border-electric/40 bg-electric/10 text-xs font-mono text-electric flex items-center gap-2 animate-fade-in">
            <CheckCircle2 className="w-4 h-4 shrink-0" />
            <span>Acknowledgement transmitted to Prof. Rogelio Santos. Status: Delivered.</span>
          </div>
        )}

        <div className="flex flex-wrap gap-2 pt-2 border-t border-pearl-border/60 dark:border-acrylic-border/60">
          <TnButton
            text="Acknowledge Observation"
            onClick={() => setShowFeedbackSent(true)}
            size="SM"
            leadingIcon={<CheckCircle2 className="w-3.5 h-3.5" />}
            testTag="parent_ack_btn"
          />
          <TnButton
            text={isMessagingOpen ? "Close Message" : "Message Teacher"}
            variant="SECONDARY"
            onClick={() => setIsMessagingOpen(!isMessagingOpen)}
            size="SM"
            leadingIcon={<MessageSquare className="w-3.5 h-3.5" />}
            testTag="parent_msg_btn"
          />
        </div>

        {isMessagingOpen && (
          <form onSubmit={handleSendMessage} className="pt-3 border-t border-pearl-border/60 dark:border-acrylic-border/60 space-y-2 animate-fade-in">
            <textarea
              value={parentMessage}
              onChange={(e) => setParentMessage(e.target.value)}
              placeholder="Write a message or inquiry to Prof. Santos regarding Juan's progress..."
              rows={2}
              required
              className="w-full p-3 text-xs rounded-xl bg-pearl dark:bg-acrylic-surface border border-pearl-border dark:border-acrylic-border text-acrylic dark:text-pearl-pure focus:outline-none focus:border-electric"
            />
            {messageSent && (
              <p className="text-[11px] font-mono text-electric">
                Message transmitted to faculty inbox.
              </p>
            )}
            <div className="flex justify-end">
              <TnButton
                type="submit"
                text="Transmit to Teacher"
                size="SM"
              />
            </div>
          </form>
        )}
      </TnCard>

      {/* Competency Mastery Parent Breakdown */}
      <div>
        <h3 className="text-xs font-mono font-bold text-charcoal dark:text-charcoal-darkMuted tracking-widest uppercase mb-3">
          JUAN'S CURRICULUM MASTERY BREAKDOWN
        </h3>

        <div className="space-y-3">
          {INITIAL_STUDENT_MASTERY.map((item) => (
            <TnCard key={item.code} className="p-4 flex items-center justify-between gap-4">
              <div className="space-y-0.5">
                <div className="flex items-center gap-2">
                  <span className="font-mono text-xs font-bold text-electric">
                    {item.code}
                  </span>
                  <TnBadge text={item.status.toUpperCase()} variant="BLUE" />
                </div>
                <h4 className="text-xs font-bold text-acrylic dark:text-pearl-pure">
                  {item.title}
                </h4>
                <p className="text-[11px] text-charcoal dark:text-charcoal-darkMuted">
                  Subject: {item.subject} • Last Assessed: {item.lastAssessed}
                </p>
              </div>

              <div className="text-right shrink-0">
                <div className="text-base font-black font-mono text-electric">
                  {Math.round(item.score * 100)}%
                </div>
                <div className="text-[10px] text-charcoal dark:text-charcoal-darkMuted font-mono">
                  Mastery
                </div>
              </div>
            </TnCard>
          ))}
        </div>
      </div>
    </div>
  );
};
