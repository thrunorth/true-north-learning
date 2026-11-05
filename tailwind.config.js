/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  darkMode: 'class',
  theme: {
    extend: {
      colors: {
        pearl: {
          DEFAULT: '#F8F9FA',
          pure: '#FFFFFF',
          elevated: '#F1F2F4',
          border: '#E5E7EB',
        },
        acrylic: {
          DEFAULT: '#0A0A0A',
          deep: '#050505',
          surface: '#121212',
          elevated: '#1A1A1A',
          border: '#262626',
        },
        charcoal: {
          DEFAULT: '#4A4A4A',
          muted: '#6E6E6E',
          darkMuted: '#9E9E9E',
        },
        electric: {
          DEFAULT: '#0052FF',
          hover: '#0043D6',
          subtle: 'rgba(0, 82, 255, 0.12)',
          light: '#5287FF',
        }
      },
      fontFamily: {
        sans: ['Inter', 'system-ui', '-apple-system', 'BlinkMacSystemFont', 'Segoe UI', 'Roboto', 'sans-serif'],
        mono: ['JetBrains Mono', 'ui-monospace', 'SFMono-Regular', 'Menlo', 'Monaco', 'Consolas', 'monospace'],
      }
    },
  },
  plugins: [],
}
