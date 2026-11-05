import React from 'react';
import { UserRole } from '../types';

export type ButtonVariant = 'PRIMARY' | 'SECONDARY' | 'OUTLINE' | 'GHOST';
export type ButtonSize = 'SM' | 'MD' | 'LG';

interface TnButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  text: string;
  variant?: ButtonVariant;
  size?: ButtonSize;
  leadingIcon?: React.ReactNode;
  testTag?: string;
}

export const TnButton: React.FC<TnButtonProps> = ({
  text,
  onClick,
  variant = 'PRIMARY',
  size = 'MD',
  leadingIcon,
  disabled = false,
  testTag = 'tn_button',
  className = '',
  ...rest
}) => {
  const sizeClasses = {
    SM: 'px-3 py-1.5 text-xs font-semibold gap-1.5',
    MD: 'px-4 py-2 text-sm font-bold gap-2',
    LG: 'px-6 py-3 text-base font-bold gap-2.5'
  }[size];

  const variantClasses = {
    // Strictly Electric Blue (#0052FF) with pearl white text
    PRIMARY: 'bg-electric hover:bg-electric-hover text-pearl-pure shadow-sm active:scale-[0.98]',
    SECONDARY: 'bg-pearl-elevated dark:bg-acrylic-surface hover:bg-pearl-border dark:hover:bg-acrylic-elevated text-acrylic dark:text-pearl-pure',
    OUTLINE: 'border border-pearl-border dark:border-acrylic-border hover:bg-pearl-elevated dark:hover:bg-acrylic-surface text-acrylic dark:text-pearl-pure',
    GHOST: 'bg-transparent hover:bg-pearl-elevated dark:hover:bg-acrylic-surface text-charcoal dark:text-charcoal-darkMuted'
  }[variant];

  return (
    <button
      onClick={onClick}
      disabled={disabled}
      data-testid={testTag}
      className={`inline-flex items-center justify-center rounded-xl transition-all duration-150 disabled:opacity-50 disabled:cursor-not-allowed select-none ${sizeClasses} ${variantClasses} ${className}`}
      {...rest}
    >
      {leadingIcon && <span className="shrink-0">{leadingIcon}</span>}
      <span>{text}</span>
    </button>
  );
};

interface TnCardProps {
  children: React.ReactNode;
  highlight?: boolean;
  onClick?: () => void;
  className?: string;
  testTag?: string;
}

export const TnCard: React.FC<TnCardProps> = ({
  children,
  highlight = false,
  onClick,
  className = '',
  testTag = 'tn_card'
}) => {
  return (
    <div
      onClick={onClick}
      data-testid={testTag}
      className={`rounded-2xl transition-all duration-200 backdrop-blur-sm ${
        highlight
          ? 'border-[1.5px] border-electric/80 shadow-md shadow-electric/5'
          : 'border border-pearl-border/80 dark:border-acrylic-border'
      } ${
        // Layered translucent light pearl rgba(255, 255, 255, 0.85) in light mode; deep acrylic in dark mode
        'bg-white/85 dark:bg-acrylic-surface/95'
      } ${onClick ? 'cursor-pointer hover:border-electric/50 active:scale-[0.99]' : ''} ${className}`}
    >
      {children}
    </div>
  );
};

export type BadgeVariant = 'BLUE' | 'SILVER' | 'SUCCESS' | 'WARNING';

interface TnBadgeProps {
  text: string;
  variant?: BadgeVariant;
  className?: string;
}

export const TnBadge: React.FC<TnBadgeProps> = ({
  text,
  variant = 'SILVER',
  className = ''
}) => {
  // STRICT COLOR CONSTRAINT: Only Pearl White, Acrylic Black, and Electric Blue
  const styleClasses = (variant === 'BLUE' || variant === 'SUCCESS')
    ? 'bg-electric/15 text-electric dark:text-electric-light dark:bg-electric/25'
    : 'bg-pearl-elevated dark:bg-acrylic-elevated text-charcoal dark:text-charcoal-darkMuted';

  return (
    <span
      className={`inline-flex items-center px-2.5 py-0.5 rounded-full text-[10.5px] font-bold tracking-wider uppercase font-mono ${styleClasses} ${className}`}
    >
      {text}
    </span>
  );
};

interface MasteryRingIndicatorProps {
  percentage: number;
  size?: number;
  strokeWidth?: number;
  className?: string;
}

export const MasteryRingIndicator: React.FC<MasteryRingIndicatorProps> = ({
  percentage,
  size = 56,
  strokeWidth = 4.5,
  className = ''
}) => {
  const radius = (size - strokeWidth) / 2;
  const circumference = 2 * Math.PI * radius;
  const strokeDashoffset = circumference - (percentage / 100) * circumference;

  return (
    <div className={`relative flex items-center justify-center shrink-0 ${className}`} style={{ width: size, height: size }}>
      <svg width={size} height={size} className="transform -rotate-90">
        {/* Track */}
        <circle
          cx={size / 2}
          cy={size / 2}
          r={radius}
          stroke="currentColor"
          strokeWidth={strokeWidth}
          fill="none"
          className="text-pearl-elevated dark:text-acrylic-elevated"
        />
        {/* Strictly Electric Blue Progress */}
        <circle
          cx={size / 2}
          cy={size / 2}
          r={radius}
          stroke="#0052FF"
          strokeWidth={strokeWidth}
          fill="none"
          strokeDasharray={circumference}
          strokeDashoffset={strokeDashoffset}
          strokeLinecap="round"
          className="transition-all duration-700 ease-out"
        />
      </svg>
      <span className="absolute font-mono text-[11px] font-bold text-acrylic dark:text-pearl-pure">
        {percentage}%
      </span>
    </div>
  );
};

interface OfflineSyncChipProps {
  isOfflineSimulated: boolean;
  onClick: () => void;
  className?: string;
}

export const OfflineSyncChip: React.FC<OfflineSyncChipProps> = ({
  isOfflineSimulated,
  onClick,
  className = ''
}) => {
  return (
    <button
      onClick={onClick}
      data-testid="offline_sync_chip"
      title="Toggle Simulated DepEd LAN Hub vs Cloud Sync"
      className={`inline-flex items-center gap-1.5 px-2.5 py-1 rounded-full text-[10px] font-mono font-medium transition-all duration-150 bg-pearl-elevated dark:bg-acrylic-elevated hover:bg-pearl-border dark:hover:bg-acrylic-border text-charcoal dark:text-charcoal-darkMuted ${className}`}
    >
      <span
        className={`w-1.5 h-1.5 rounded-full transition-colors ${
          isOfflineSimulated ? 'bg-charcoal dark:bg-charcoal-darkMuted' : 'bg-electric'
        }`}
      />
      <span>{isOfflineSimulated ? 'Local Hub' : 'Cloud Sync'}</span>
    </button>
  );
};

interface RoleSelectorTabsProps {
  selectedRole: UserRole;
  onRoleSelected: (role: UserRole) => void;
  className?: string;
}

export const RoleSelectorTabs: React.FC<RoleSelectorTabsProps> = ({
  selectedRole,
  onRoleSelected,
  className = ''
}) => {
  const roles: UserRole[] = ['STUDENT', 'TEACHER', 'ADMIN', 'PARENT'];

  return (
    <div className={`inline-flex rounded-xl bg-pearl-elevated dark:bg-acrylic-surface p-1 gap-1 border border-pearl-border/60 dark:border-acrylic-border ${className}`}>
      {roles.map((role) => {
        const isSelected = role === selectedRole;
        return (
          <button
            key={role}
            onClick={() => onRoleSelected(role)}
            data-testid={`role_tab_${role.toLowerCase()}`}
            className={`px-3 py-1.5 rounded-lg text-[10.5px] font-mono tracking-wider uppercase transition-all duration-150 ${
              isSelected
                ? 'bg-electric text-pearl-pure font-bold shadow-sm'
                : 'text-charcoal dark:text-charcoal-darkMuted hover:text-acrylic dark:hover:text-pearl-pure font-medium'
            }`}
          >
            {role}
          </button>
        );
      })}
    </div>
  );
};
