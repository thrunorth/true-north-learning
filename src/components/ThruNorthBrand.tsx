import React from 'react';

interface ThruNorthHeaderLogoProps {
  iconSize?: number;
  showTagline?: boolean;
  onClick?: () => void;
  className?: string;
}

export const ThruNorthHeaderLogo: React.FC<ThruNorthHeaderLogoProps> = ({
  iconSize = 36,
  showTagline = true,
  onClick,
  className = ''
}) => {
  return (
    <div
      onClick={onClick}
      className={`flex items-center gap-2.5 ${onClick ? 'cursor-pointer select-none group' : ''} ${className}`}
      data-testid="app_logo"
    >
      <div
        className="rounded-lg bg-black p-0.5 overflow-hidden flex items-center justify-center border border-pearl-border dark:border-acrylic-border shrink-0 shadow-sm"
        style={{ width: iconSize, height: iconSize }}
      >
        <img
          src="/logo.png"
          alt="THRU NORTH Logo"
          className="w-full h-full object-contain"
        />
      </div>

      <div className="flex flex-col">
        <span className="font-extrabold text-sm tracking-[0.16em] leading-tight text-acrylic dark:text-pearl-pure font-sans">
          THRU NORTH
        </span>
        {showTagline && (
          <span className="font-semibold text-[8.5px] tracking-[0.24em] text-charcoal dark:text-charcoal-darkMuted leading-tight mt-0.5 uppercase">
            STAY AHEAD. BE SMART.
          </span>
        )}
      </div>
    </div>
  );
};

interface ThruNorthHeroBrandProps {
  logoSize?: number;
  className?: string;
}

export const ThruNorthHeroBrand: React.FC<ThruNorthHeroBrandProps> = ({
  logoSize = 96,
  className = ''
}) => {
  return (
    <div className={`flex flex-col items-center text-center ${className}`}>
      <div
        className="rounded-2xl bg-black p-2 overflow-hidden flex items-center justify-center border border-pearl-border dark:border-acrylic-border shadow-md"
        style={{ width: logoSize, height: logoSize }}
      >
        <img
          src="/logo.png"
          alt="THRU NORTH — Stay Ahead. Be Smart."
          className="w-full h-full object-contain"
        />
      </div>

      <h1 className="mt-4 font-black text-2xl md:text-3xl tracking-[0.2em] text-acrylic dark:text-pearl-pure font-sans">
        THRU NORTH
      </h1>

      <p className="mt-1 font-bold text-xs tracking-[0.32em] text-charcoal dark:text-charcoal-darkMuted uppercase">
        STAY AHEAD. BE SMART.
      </p>
    </div>
  );
};
