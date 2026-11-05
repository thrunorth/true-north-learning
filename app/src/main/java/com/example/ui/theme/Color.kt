package com.example.ui.theme

import androidx.compose.ui.graphics.Color

// =========================================================================
// STRICT COLOR CONSTRAINT:
// Only Pearl White, Acrylic Black, Charcoal Gray, and Electric Blue (#0052FF)
// NO OTHER COLORS.
// =========================================================================

// Pearl White Palette
val PearlWhite = Color(0xFFF8F9FA)           // Crisp Pearl White Background
val PearlWhitePure = Color(0xFFFFFFFF)       // Pure Pearl White
val CrispWhite = PearlWhitePure              // Backward-compatible alias
val PearlWhiteTranslucent = Color(0xD9FFFFFF) // Layered translucent light pearl rgba(255,255,255,0.85)
val PearlWhiteElevated = Color(0xFFF1F2F4)   // Subtle off-white container
val PearlWhiteBorder = Color(0xFFE5E7EB)     // Subtle pearl border

// Acrylic Black Palette
val AcrylicBlack = Color(0xFF0A0A0A)         // Acrylic Black Primary Text & Headings
val DeepAcrylicBlack = Color(0xFF050505)     // Deep Acrylic Black Background for Dark Mode
val DarkAcrylicSurface = Color(0xFF121212)   // Deep Acrylic Surface for Dark Mode
val DarkAcrylicElevated = Color(0xFF1A1A1A)  // Deep Acrylic Elevated Container
val DarkAcrylicBorder = Color(0xFF262626)    // Subtle Acrylic Border

// Charcoal / Dark Gray (Secondary & Muted Text)
val CharcoalGray = Color(0xFF4A4A4A)         // Dark gray / charcoal secondary text
val MutedCharcoal = Color(0xFF6E6E6E)        // Muted gray
val DarkMutedGray = Color(0xFF9E9E9E)        // Secondary text for dark mode

// Electric Blue (Strictly for Buttons & Interactive Elements)
val ElectricBlue = Color(0xFF0052FF)         // Strictly Electric Blue (#0052FF)
val ElectricBlueSubtle = Color(0x1F0052FF)   // Electric blue translucent tint
val ElectricBlueLight = Color(0xFF5287FF)    // High contrast electric blue tint
val ElectricBlueHover = Color(0xFF0043D6)

// Aliases mapped strictly to the Pearl / Acrylic / Electric Blue constraint
val SuccessGreen = ElectricBlue              // Strict constraint: No green allowed -> Electric Blue
val SuccessGreenSubtle = ElectricBlueSubtle
val WarningAmber = CharcoalGray              // Strict constraint: No amber allowed -> Charcoal
val WarningAmberSubtle = Color(0x140A0A0A)
val ErrorRed = CharcoalGray                  // Strict constraint: No red allowed -> Charcoal
val ErrorRedSubtle = Color(0x140A0A0A)
