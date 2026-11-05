import React, { useState } from 'react';
import { X, Sparkles, Send, Bot, User } from 'lucide-react';
import { TnButton } from '../UiComponents';

interface Message {
  id: string;
  sender: 'ai' | 'user';
  text: string;
  time: string;
}

interface AiTutorDialogProps {
  onDismiss: () => void;
}

export const AiTutorDialog: React.FC<AiTutorDialogProps> = ({ onDismiss }) => {
  const [messages, setMessages] = useState<Message[]>([
    {
      id: 'm1',
      sender: 'ai',
      text: 'Mabuhay Juan! I am your THRU NORTH Socratic Learning Guide. Which DepEd MATATAG concept are we mastering today?',
      time: 'Just now'
    }
  ]);
  const [inputText, setInputText] = useState('');
  const [isTyping, setIsTyping] = useState(false);

  const samplePrompts = [
    'How do I calculate net displacement?',
    'Why use fine adjustment on high power?',
    'Levels of cellular organization'
  ];

  const handleSend = (textToSend?: string) => {
    const text = (textToSend || inputText).trim();
    if (!text) return;

    const userMsg: Message = {
      id: String(Date.now()),
      sender: 'user',
      text,
      time: 'Just now'
    };

    setMessages((prev) => [...prev, userMsg]);
    setInputText('');
    setIsTyping(true);

    setTimeout(() => {
      let reply = 'Let us break this down step-by-step. What is the first principle we should identify?';
      if (text.toLowerCase().includes('displacement')) {
        reply = 'Remember: displacement considers direction as well as distance! If east is positive (+4.5 km) and west is negative (-1.75 km), what arithmetic operation links them?';
      } else if (text.toLowerCase().includes('fine adjustment') || text.toLowerCase().includes('high power') || text.toLowerCase().includes('knob')) {
        reply = 'Under 40x High Power, the lens sits mere millimeters above the glass. What would happen if we used the coarse knob that moves the stage drastically?';
      } else if (text.toLowerCase().includes('cell') || text.toLowerCase().includes('organization')) {
        reply = 'Think of building a house: bricks form walls, walls form rooms, rooms form the building. How does that compare to cells, tissues, and organs?';
      }

      const aiMsg: Message = {
        id: String(Date.now() + 1),
        sender: 'ai',
        text: reply,
        time: 'Just now'
      };
      setMessages((prev) => [...prev, aiMsg]);
      setIsTyping(false);
    }, 600);
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
                THRU NORTH AI Socratic Tutor
              </h2>
              <p className="text-[10px] text-charcoal dark:text-charcoal-darkMuted font-mono">
                Guided Inquiry • Zero Direct Answers • Spiral Progression
              </p>
            </div>
          </div>
          <button
            onClick={onDismiss}
            data-testid="ai_tutor_close_btn"
            className="p-1.5 rounded-lg text-charcoal dark:text-charcoal-darkMuted hover:bg-pearl-elevated dark:hover:bg-acrylic-elevated"
          >
            <X className="w-4 h-4" />
          </button>
        </div>

        {/* Quick Prompts */}
        <div className="px-5 py-2.5 bg-pearl-elevated/50 dark:bg-acrylic-deep/40 border-b border-pearl-border/50 dark:border-acrylic-border/50 flex gap-1.5 overflow-x-auto text-xs no-scrollbar">
          {samplePrompts.map((p, idx) => (
            <button
              key={idx}
              onClick={() => handleSend(p)}
              className="shrink-0 px-2.5 py-1 rounded-full bg-pearl-pure dark:bg-acrylic-surface border border-pearl-border dark:border-acrylic-border text-[10.5px] text-charcoal dark:text-charcoal-darkMuted hover:text-electric dark:hover:text-electric-light hover:border-electric transition-colors"
            >
              {p}
            </button>
          ))}
        </div>

        {/* Message Feed */}
        <div className="flex-1 overflow-y-auto p-5 space-y-4">
          {messages.map((m) => (
            <div
              key={m.id}
              className={`flex gap-3 ${m.sender === 'user' ? 'justify-end' : 'justify-start'}`}
            >
              {m.sender === 'ai' && (
                <div className="w-7 h-7 rounded-full bg-electric/15 text-electric flex items-center justify-center shrink-0 mt-0.5">
                  <Bot className="w-4 h-4" />
                </div>
              )}
              <div
                className={`max-w-[80%] rounded-2xl px-4 py-2.5 text-xs md:text-sm leading-relaxed ${
                  m.sender === 'user'
                    ? 'bg-electric text-pearl-pure font-medium rounded-tr-none'
                    : 'bg-pearl dark:bg-acrylic-elevated text-acrylic dark:text-pearl-pure border border-pearl-border/80 dark:border-acrylic-border rounded-tl-none'
                }`}
              >
                <p>{m.text}</p>
                <span
                  className={`block text-[9.5px] mt-1 font-mono ${
                    m.sender === 'user' ? 'text-pearl-pure/80 text-right' : 'text-charcoal dark:text-charcoal-darkMuted'
                  }`}
                >
                  {m.time}
                </span>
              </div>
              {m.sender === 'user' && (
                <div className="w-7 h-7 rounded-full bg-pearl-elevated dark:bg-acrylic-elevated text-charcoal dark:text-charcoal-darkMuted flex items-center justify-center shrink-0 mt-0.5">
                  <User className="w-4 h-4" />
                </div>
              )}
            </div>
          ))}

          {isTyping && (
            <div className="flex items-center gap-2 text-xs text-charcoal dark:text-charcoal-darkMuted font-mono">
              <Bot className="w-4 h-4 text-electric animate-pulse" />
              <span>Socratic Guide is thinking...</span>
            </div>
          )}
        </div>

        {/* Input Bar */}
        <div className="p-4 border-t border-pearl-border dark:border-acrylic-border bg-pearl dark:bg-acrylic-deep flex gap-2">
          <input
            type="text"
            value={inputText}
            onChange={(e) => setInputText(e.target.value)}
            onKeyDown={(e) => e.key === 'Enter' && handleSend()}
            placeholder="Ask a question about your lessons or quiz..."
            className="flex-1 px-3.5 py-2 text-xs md:text-sm rounded-xl bg-pearl-pure dark:bg-acrylic-surface border border-pearl-border dark:border-acrylic-border text-acrylic dark:text-pearl-pure focus:outline-none focus:border-electric"
          />
          <TnButton
            text="Send"
            onClick={() => handleSend()}
            leadingIcon={<Send className="w-3.5 h-3.5" />}
            size="SM"
            testTag="ai_tutor_send_btn"
          />
        </div>
      </div>
    </div>
  );
};
