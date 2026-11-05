import React from 'react';
import {
  Sun,
  Moon,
  Monitor,
  LogOut,
  GraduationCap,
  Users,
  Shield,
  HeartHandshake,
  Sparkles
} from 'lucide-react';
import { UserRole } from '../types';
import { useTheme } from '../context/ThemeContext';
import { ThruNorthHeaderLogo } from './ThruNorthBrand';
import { OfflineSyncChip, TnButton } from './UiComponents';

interface NavbarProps {
  currentRole: UserRole;
  onSelectRole: (role: UserRole) => void;
  onSignOut: () => void;
  isOfflineSimulated: boolean;
  onToggleOffline: () => void;
  onOpenAiTutor: () => void;
  onOpenAiStudio: () => void;
}

export const Navbar: React.FC<NavbarProps> = ({
  currentRole,
  onSelectRole,
  onSignOut,
  isOfflineSimulated,
  onToggleOffline,
  onOpenAiTutor,
  onOpenAiStudio
}) => {
  const { themeMode, toggleTheme } = useTheme();

  const roleNavItems: { role: UserRole; label: string; icon: React.ReactNode }[] = [
    { role: 'STUDENT', label: 'Student', icon: <GraduationCap className="w-4 h-4" /> },
    { role: 'TEACHER', label: 'Teacher', icon: <Users className="w-4 h-4" /> },
    { role: 'ADMIN', label: 'Admin', icon: <Shield className="w-4 h-4" /> },
    { role: 'PARENT', label: 'Parent', icon: <HeartHandshake className="w-4 h-4" /> }
  ];

  return (
    <header className="sticky top-0 z-40 w-full bg-pearl/90 dark:bg-acrylic-deep/90 backdrop-blur-md border-b border-pearl-border dark:border-acrylic-border">
      {/* Upper Main Nav Row */}
      <div className="max-w-7xl mx-auto px-4 py-2.5 flex items-center justify-between gap-4">
        <ThruNorthHeaderLogo
          iconSize={36}
          showTagline={true}
          onClick={() => onSelectRole('STUDENT')}
        />

        {/* Right side controls: Offline Sync + Day/Dark Toggle + Sign Out */}
        <div className="flex items-center gap-2">
          <OfflineSyncChip
            isOfflineSimulated={isOfflineSimulated}
            onClick={onToggleOffline}
          />

          {/* Theme Toggle Button (Default: Day Mode) */}
          <button
            onClick={toggleTheme}
            data-testid="theme_toggle_button"
            title={`Toggle Theme Mode (Current: ${themeMode})`}
            className="w-8 h-8 rounded-full bg-pearl-elevated dark:bg-acrylic-surface border border-pearl-border/80 dark:border-acrylic-border text-charcoal dark:text-charcoal-darkMuted hover:text-acrylic dark:hover:text-pearl-pure flex items-center justify-center transition-colors"
          >
            {themeMode === 'LIGHT' && <Sun className="w-4 h-4 text-electric" />}
            {themeMode === 'DARK' && <Moon className="w-4 h-4 text-electric-light" />}
            {themeMode === 'SYSTEM' && <Monitor className="w-4 h-4 text-charcoal dark:text-charcoal-darkMuted" />}
          </button>

          {/* Sign Out Button */}
          <button
            onClick={onSignOut}
            data-testid="sign_out_button"
            title="Sign Out"
            className="w-8 h-8 rounded-full bg-pearl-elevated dark:bg-acrylic-surface border border-pearl-border/80 dark:border-acrylic-border text-charcoal dark:text-charcoal-darkMuted hover:text-acrylic dark:hover:text-pearl-pure flex items-center justify-center transition-colors"
          >
            <LogOut className="w-4 h-4" />
          </button>
        </div>
      </div>

      {/* Secondary Header Banner */}
      <div className="border-t border-pearl-border/60 dark:border-acrylic-border/60 bg-pearl-pure/80 dark:bg-acrylic-surface/80">
        <div className="max-w-7xl mx-auto px-4 py-2 flex flex-col sm:flex-row sm:items-center justify-between gap-2">
          <div>
            <h1 className="text-xs font-bold font-sans text-acrylic dark:text-pearl-pure tracking-wide">
              THRU NORTH — Stay Ahead. Be Smart.
            </h1>
            <p className="text-[10px] text-charcoal dark:text-charcoal-darkMuted font-mono">
              Philippine K-12 Mastery Operating System
            </p>
          </div>

          <div className="flex items-center gap-2">
            <TnButton
              text="AI Tutor"
              onClick={onOpenAiTutor}
              size="SM"
              leadingIcon={<Sparkles className="w-3.5 h-3.5" />}
              testTag="top_ai_tutor_btn"
            />
            {(currentRole === 'TEACHER' || currentRole === 'ADMIN') && (
              <TnButton
                text="AI Studio"
                variant="SECONDARY"
                onClick={onOpenAiStudio}
                size="SM"
                testTag="top_ai_studio_btn"
              />
            )}
          </div>
        </div>
      </div>

      {/* Role Switcher Strip */}
      <div className="border-t border-pearl-border/50 dark:border-acrylic-border/50 bg-pearl-elevated/40 dark:bg-acrylic-deep/40">
        <div className="max-w-7xl mx-auto px-4 py-1.5 flex gap-1.5 overflow-x-auto no-scrollbar">
          {roleNavItems.map(({ role, label }) => {
            const isSelected = role === currentRole;
            return (
              <button
                key={role}
                onClick={() => onSelectRole(role)}
                data-testid={`nav_role_tab_${role.toLowerCase()}`}
                className={`px-3 py-1 rounded-lg text-[10.5px] font-mono tracking-wider uppercase transition-all ${
                  isSelected
                    ? 'bg-electric text-pearl-pure font-bold shadow-sm'
                    : 'text-charcoal dark:text-charcoal-darkMuted hover:text-acrylic dark:hover:text-pearl-pure bg-pearl dark:bg-acrylic-surface/60 border border-pearl-border/50 dark:border-acrylic-border/50 font-medium'
                }`}
              >
                {label}
              </button>
            );
          })}
        </div>
      </div>
    </header>
  );
};
