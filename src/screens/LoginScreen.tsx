import React, { useState } from 'react';
import {
  GraduationCap,
  Users,
  Shield,
  HeartHandshake,
  ArrowRight,
  Mail,
  Lock
} from 'lucide-react';
import { UserRole } from '../types';
import { ThruNorthHeroBrand } from '../components/ThruNorthBrand';
import { TnCard, TnBadge, TnButton } from '../components/UiComponents';

interface LoginScreenProps {
  onLoginSuccess: (role: UserRole) => void;
}

export const LoginScreen: React.FC<LoginScreenProps> = ({ onLoginSuccess }) => {
  const [selectedTab, setSelectedTab] = useState<'demo' | 'custom'>('demo');
  const [selectedFormRole, setSelectedFormRole] = useState<UserRole>('STUDENT');
  const [emailInput, setEmailInput] = useState('juan.delacruz@makati.deped.gov.ph');
  const [passwordInput, setPasswordInput] = useState('••••••••');

  const demoAccounts: {
    role: UserRole;
    name: string;
    detail: string;
    icon: React.ReactNode;
    testTag: string;
  }[] = [
    {
      role: 'STUDENT',
      name: 'Juan de la Cruz',
      detail: 'Grade 7 - Rizal • Makati Science Academic Center',
      icon: <GraduationCap className="w-5 h-5 text-electric" />,
      testTag: 'login_as_student'
    },
    {
      role: 'TEACHER',
      name: 'Prof. Rogelio Santos, LPT',
      detail: 'Science & Math Lead • Sections Rizal & Bonifacio',
      icon: <Users className="w-5 h-5 text-electric" />,
      testTag: 'login_as_teacher'
    },
    {
      role: 'ADMIN',
      name: 'Dr. Alicia Valdez, EdD',
      detail: 'Academic Director • Dual-Stack Systems Administrator',
      icon: <Shield className="w-5 h-5 text-electric" />,
      testTag: 'login_as_admin'
    },
    {
      role: 'PARENT',
      name: 'Maria Elena de la Cruz',
      detail: 'Parent / Guardian of Juan de la Cruz',
      icon: <HeartHandshake className="w-5 h-5 text-electric" />,
      testTag: 'login_as_parent'
    }
  ];

  return (
    <div className="min-h-screen flex flex-col items-center justify-center px-4 py-10 bg-pearl dark:bg-acrylic-deep text-acrylic dark:text-pearl-pure selection:bg-electric selection:text-pearl-pure">
      <div className="w-full max-w-md space-y-6">
        {/* Brand Hero */}
        <ThruNorthHeroBrand logoSize={92} />

        <div className="flex flex-col items-center text-center space-y-2">
          <TnBadge text="PHILIPPINE K-12 SECURE ACCESS" variant="BLUE" />
          <p className="text-xs text-charcoal dark:text-charcoal-darkMuted leading-relaxed max-w-sm">
            Single sign-on gateway for DepEd MATATAG and Legacy K-12 learner and educator profiles.
          </p>
        </div>

        {/* Tab switch */}
        <div className="flex p-1 rounded-xl bg-pearl-elevated dark:bg-acrylic-surface border border-pearl-border/70 dark:border-acrylic-border">
          <button
            onClick={() => setSelectedTab('demo')}
            data-testid="tab_quick_select"
            className={`flex-1 py-2 text-xs font-mono font-bold tracking-wider rounded-lg transition-all ${
              selectedTab === 'demo'
                ? 'bg-electric text-pearl-pure shadow-sm'
                : 'text-charcoal dark:text-charcoal-darkMuted hover:text-acrylic dark:hover:text-pearl-pure'
            }`}
          >
            DEMO ACCOUNTS
          </button>
          <button
            onClick={() => setSelectedTab('custom')}
            data-testid="tab_custom_credentials"
            className={`flex-1 py-2 text-xs font-mono font-bold tracking-wider rounded-lg transition-all ${
              selectedTab === 'custom'
                ? 'bg-electric text-pearl-pure shadow-sm'
                : 'text-charcoal dark:text-charcoal-darkMuted hover:text-acrylic dark:hover:text-pearl-pure'
            }`}
          >
            CUSTOM SIGN-IN
          </button>
        </div>

        {/* Content */}
        {selectedTab === 'demo' ? (
          <div className="space-y-3">
            {demoAccounts.map((acc) => (
              <TnCard
                key={acc.role}
                onClick={() => onLoginSuccess(acc.role)}
                testTag={acc.testTag}
                className="p-4 flex items-center justify-between group"
              >
                <div className="flex items-center gap-3.5">
                  <div className="w-11 h-11 rounded-xl bg-electric/15 flex items-center justify-center shrink-0">
                    {acc.icon}
                  </div>
                  <div>
                    <div className="flex items-center gap-2">
                      <span className="font-bold text-sm text-acrylic dark:text-pearl-pure">
                        {acc.name}
                      </span>
                      <TnBadge text={acc.role} variant={acc.role === 'STUDENT' ? 'BLUE' : 'SILVER'} />
                    </div>
                    <p className="text-[11px] text-charcoal dark:text-charcoal-darkMuted mt-0.5 leading-snug">
                      {acc.detail}
                    </p>
                  </div>
                </div>
                <ArrowRight className="w-4 h-4 text-charcoal dark:text-charcoal-darkMuted group-hover:text-electric transition-colors shrink-0" />
              </TnCard>
            ))}
          </div>
        ) : (
          <TnCard className="p-5 space-y-4">
            <div>
              <label className="block text-xs font-bold text-acrylic dark:text-pearl-pure mb-2 font-mono uppercase">
                Role Authorization Target
              </label>
              <div className="grid grid-cols-3 gap-2">
                {(['STUDENT', 'TEACHER', 'ADMIN'] as UserRole[]).map((r) => (
                  <button
                    key={r}
                    onClick={() => {
                      setSelectedFormRole(r);
                      if (r === 'STUDENT') setEmailInput('juan.delacruz@makati.deped.gov.ph');
                      if (r === 'TEACHER') setEmailInput('r.santos@true-north.ph');
                      if (r === 'ADMIN') setEmailInput('a.valdez@true-north.ph');
                    }}
                    className={`py-2 text-xs font-mono font-bold rounded-lg border transition-all ${
                      selectedFormRole === r
                        ? 'border-electric bg-electric/15 text-electric'
                        : 'border-pearl-border dark:border-acrylic-border bg-pearl dark:bg-acrylic-elevated text-charcoal dark:text-charcoal-darkMuted'
                    }`}
                  >
                    {r}
                  </button>
                ))}
              </div>
            </div>

            <div>
              <label className="block text-xs text-charcoal dark:text-charcoal-darkMuted mb-1 font-mono">
                DepEd Account Email / LRN
              </label>
              <div className="relative">
                <Mail className="w-4 h-4 text-charcoal dark:text-charcoal-darkMuted absolute left-3 top-3" />
                <input
                  type="email"
                  value={emailInput}
                  onChange={(e) => setEmailInput(e.target.value)}
                  className="w-full pl-9 pr-3 py-2 text-xs rounded-xl bg-pearl-pure dark:bg-acrylic-surface border border-pearl-border dark:border-acrylic-border text-acrylic dark:text-pearl-pure focus:outline-none focus:border-electric"
                />
              </div>
            </div>

            <div>
              <label className="block text-xs text-charcoal dark:text-charcoal-darkMuted mb-1 font-mono">
                Password
              </label>
              <div className="relative">
                <Lock className="w-4 h-4 text-charcoal dark:text-charcoal-darkMuted absolute left-3 top-3" />
                <input
                  type="password"
                  value={passwordInput}
                  onChange={(e) => setPasswordInput(e.target.value)}
                  className="w-full pl-9 pr-3 py-2 text-xs rounded-xl bg-pearl-pure dark:bg-acrylic-surface border border-pearl-border dark:border-acrylic-border text-acrylic dark:text-pearl-pure focus:outline-none focus:border-electric"
                />
              </div>
            </div>

            <TnButton
              text="Authorize Session"
              onClick={() => onLoginSuccess(selectedFormRole)}
              size="MD"
              className="w-full mt-2"
              testTag="submit_login_btn"
            />
          </TnCard>
        )}

        {/* Security & Compliance Footer */}
        <div className="p-3 rounded-xl bg-pearl-elevated dark:bg-acrylic-surface border border-pearl-border/80 dark:border-acrylic-border flex items-center justify-center gap-2 text-[10px] font-mono text-charcoal dark:text-charcoal-darkMuted">
          <span className="w-1.5 h-1.5 rounded-full bg-electric shrink-0" />
          <span>THRU NORTH • STAY AHEAD. BE SMART. • DepEd LIS Single Sign-On</span>
        </div>
      </div>
    </div>
  );
};
