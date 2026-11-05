import React, { useState } from 'react';
import {
  Sparkles,
  Server,
  Layers,
  FileCheck2,
  RefreshCw,
  Cpu,
  CheckCircle2
} from 'lucide-react';
import { TnCard, TnBadge, TnButton } from '../components/UiComponents';

interface AdminPortalScreenProps {
  onOpenAiStudio: () => void;
}

export const AdminPortalScreen: React.FC<AdminPortalScreenProps> = ({ onOpenAiStudio }) => {
  const [isDualStackEnforced, setIsDualStackEnforced] = useState(true);
  const [isSyncingHubs, setIsSyncingHubs] = useState(false);
  const [syncedHubsMessage, setSyncedHubsMessage] = useState(false);

  const hubs = [
    { name: 'Server-Alpha (Science Lab 1)', ip: '192.168.1.101 • 12ms', status: 'ONLINE (240 Cached Syncs)' },
    { name: 'Server-Beta (Junior High Bldg)', ip: '192.168.1.102 • 15ms', status: 'ONLINE (310 Cached Syncs)' },
    { name: 'Server-Gamma (Library Micro-Hub)', ip: '192.168.1.103 • 8ms', status: 'ONLINE (185 Cached Syncs)' }
  ];

  const handleSyncAll = () => {
    setIsSyncingHubs(true);
    setTimeout(() => {
      setIsSyncingHubs(false);
      setSyncedHubsMessage(true);
      setTimeout(() => setSyncedHubsMessage(false), 3000);
    }, 700);
  };

  return (
    <div className="space-y-5 animate-fade-in">
      {/* Admin Header */}
      <div className="flex flex-col md:flex-row md:items-center justify-between gap-3">
        <div>
          <h2 className="text-xl md:text-2xl font-black text-acrylic dark:text-pearl-pure font-sans">
            Institutional Administration: Dr. Alicia Valdez, EdD
          </h2>
          <p className="text-xs text-charcoal dark:text-charcoal-darkMuted font-mono mt-0.5">
            Makati Science Academic Center • Dual-Stack DepEd Systems Oversight
          </p>
        </div>

        <div className="flex gap-2">
          <TnButton
            text="AI Content Studio"
            onClick={onOpenAiStudio}
            size="SM"
            leadingIcon={<Sparkles className="w-3.5 h-3.5" />}
            testTag="admin_studio_btn"
          />
        </div>
      </div>

      {/* 4 Telemetry Status Cards (2x2 Grid) */}
      <div className="grid grid-cols-2 md:grid-cols-4 gap-3">
        <TnCard className="p-4 space-y-1">
          <div className="flex items-center gap-2 text-electric">
            <Server className="w-4 h-4" />
            <span className="font-mono text-[10px] font-bold uppercase tracking-wider text-charcoal dark:text-charcoal-darkMuted">
              OFFLINE SYNC NODES
            </span>
          </div>
          <div className="text-sm font-bold text-acrylic dark:text-pearl-pure">
            8 Local Servers
          </div>
          <div className="text-[11px] font-mono text-electric">
            All hubs operational
          </div>
        </TnCard>

        <TnCard className="p-4 space-y-1">
          <div className="flex items-center gap-2 text-electric">
            <Cpu className="w-4 h-4" />
            <span className="font-mono text-[10px] font-bold uppercase tracking-wider text-charcoal dark:text-charcoal-darkMuted">
              AI GATEWAY STATUS
            </span>
          </div>
          <div className="text-sm font-bold text-acrylic dark:text-pearl-pure">
            Zero Key Exposure
          </div>
          <div className="text-[11px] text-charcoal dark:text-charcoal-darkMuted">
            Protected server proxy
          </div>
        </TnCard>

        <TnCard className="p-4 space-y-1">
          <div className="flex items-center gap-2 text-electric">
            <Layers className="w-4 h-4" />
            <span className="font-mono text-[10px] font-bold uppercase tracking-wider text-charcoal dark:text-charcoal-darkMuted">
              ACTIVE CURRICULA
            </span>
          </div>
          <div className="text-sm font-bold text-acrylic dark:text-pearl-pure">
            Dual-Stack Enabled
          </div>
          <div className="text-[11px] text-charcoal dark:text-charcoal-darkMuted">
            MATATAG & Legacy 2016
          </div>
        </TnCard>

        <TnCard className="p-4 space-y-1">
          <div className="flex items-center gap-2 text-electric">
            <FileCheck2 className="w-4 h-4" />
            <span className="font-mono text-[10px] font-bold uppercase tracking-wider text-charcoal dark:text-charcoal-darkMuted">
              SF10 EXPORT PIPELINE
            </span>
          </div>
          <div className="text-sm font-bold text-acrylic dark:text-pearl-pure">
            DepEd LIS Synced
          </div>
          <div className="text-[11px] text-charcoal dark:text-charcoal-darkMuted">
            100% automated
          </div>
        </TnCard>
      </div>

      {/* Dual-Stack Runtime Architecture Switch */}
      <TnCard className="p-5 space-y-4">
        <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-3">
          <div className="space-y-0.5">
            <h3 className="text-sm font-bold text-acrylic dark:text-pearl-pure font-sans">
              Dual-Stack Runtime Architecture
            </h3>
            <p className="text-xs text-charcoal dark:text-charcoal-darkMuted max-w-xl leading-relaxed">
              Guarantees historical learner permanent records remain intact when transitioning between the 2016 K-12 framework and the decongested DepEd MATATAG curriculum.
            </p>
          </div>
          <button
            onClick={() => setIsDualStackEnforced(!isDualStackEnforced)}
            data-testid="dual_stack_switch"
            className={`w-12 h-6 flex items-center rounded-full p-1 transition-colors duration-200 shrink-0 ${
              isDualStackEnforced ? 'bg-electric' : 'bg-pearl-border dark:bg-acrylic-border'
            }`}
          >
            <div
              className={`bg-pearl-pure w-4 h-4 rounded-full shadow-md transform transition-transform duration-200 ${
                isDualStackEnforced ? 'translate-x-6' : 'translate-x-0'
              }`}
            />
          </button>
        </div>

        {/* Key Stages Config List */}
        <div className="pt-3 border-t border-pearl-border/60 dark:border-acrylic-border/60 space-y-2">
          {[
            { stage: 'Kindergarten to Grade 10 (K-10)', desc: 'Standard: DepEd MATATAG Curriculum (Decongested Framework)', tag: 'ACTIVE DEFAULT' },
            { stage: 'Senior High School (Grades 11 & 12)', desc: 'Standard: 2016 Legacy K-12 Specialized Track (STEM, ABM, HUMSS)', tag: 'LEGACY COMPATIBLE' },
            { stage: 'Special Bridged Programs', desc: 'Standard: International Baccalaureate & TVL Bridged Competencies', tag: 'STANDBY' }
          ].map((item, idx) => (
            <div
              key={idx}
              className="p-3 rounded-xl bg-pearl dark:bg-acrylic-elevated/60 border border-pearl-border/80 dark:border-acrylic-border flex flex-col sm:flex-row sm:items-center justify-between gap-2"
            >
              <div>
                <h4 className="text-xs font-bold text-acrylic dark:text-pearl-pure">{item.stage}</h4>
                <p className="text-[11px] text-charcoal dark:text-charcoal-darkMuted">{item.desc}</p>
              </div>
              <TnBadge text={item.tag} variant={idx === 0 ? 'BLUE' : 'SILVER'} />
            </div>
          ))}
        </div>
      </TnCard>

      {/* School Local Hub Nodes (PWA Sync) */}
      <TnCard className="p-5 space-y-4">
        <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-3">
          <div>
            <h3 className="text-sm font-bold text-acrylic dark:text-pearl-pure font-sans">
              School Local Hub Nodes (PWA Sync)
            </h3>
            <p className="text-xs text-charcoal dark:text-charcoal-darkMuted">
              On-Premise LAN caching nodes for remote island and low-connectivity campuses.
            </p>
          </div>

          <TnButton
            text={isSyncingHubs ? "Syncing Nodes..." : "Sync All Hubs"}
            onClick={handleSyncAll}
            disabled={isSyncingHubs}
            size="SM"
            leadingIcon={<RefreshCw className={`w-3.5 h-3.5 ${isSyncingHubs ? 'animate-spin' : ''}`} />}
            testTag="admin_sync_hubs_btn"
          />
        </div>

        {syncedHubsMessage && (
          <div className="p-3 rounded-xl border border-electric/40 bg-electric/10 text-xs font-mono text-electric flex items-center gap-2 animate-fade-in">
            <CheckCircle2 className="w-4 h-4 shrink-0" />
            <span>Local on-premises cache synced across all 3 campus nodes.</span>
          </div>
        )}

        <div className="divide-y divide-pearl-border/60 dark:divide-acrylic-border/60">
          {hubs.map((hub, idx) => (
            <div key={idx} className="py-2.5 flex items-center justify-between gap-3 first:pt-0 last:pb-0">
              <div>
                <h4 className="text-xs font-semibold text-acrylic dark:text-pearl-pure">{hub.name}</h4>
                <p className="text-[10.5px] font-mono text-charcoal dark:text-charcoal-darkMuted">{hub.ip}</p>
              </div>
              <TnBadge text={hub.status} variant="BLUE" />
            </div>
          ))}
        </div>
      </TnCard>
    </div>
  );
};
