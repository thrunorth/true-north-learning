package com.example.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color

val LocalIsDarkTheme = compositionLocalOf { false }

/**
 * Default Light / Day Mode Scheme:
 * - Background: Crisp pearl white (#F8F9FA)
 * - Cards & Surfaces: Layered translucent light pearl (#FFFFFF / rgba(255,255,255,0.85))
 * - Primary Text & Headings: Acrylic black (#0A0A0A)
 * - Secondary & Muted Text: Dark gray / charcoal (#4A4A4A)
 * - Buttons & Interactive Elements: Strictly Electric Blue (#0052FF) with pearl white text
 */
val LightColorScheme = lightColorScheme(
  primary = ElectricBlue,
  onPrimary = PearlWhitePure,
  primaryContainer = ElectricBlueSubtle,
  onPrimaryContainer = ElectricBlue,
  secondary = CharcoalGray,
  onSecondary = PearlWhitePure,
  secondaryContainer = PearlWhiteElevated,
  onSecondaryContainer = AcrylicBlack,
  tertiary = ElectricBlue,
  onTertiary = PearlWhitePure,
  background = PearlWhite,            // #F8F9FA
  onBackground = AcrylicBlack,        // #0A0A0A
  surface = PearlWhitePure,           // Translucent / crisp layered pearl
  onSurface = AcrylicBlack,           // #0A0A0A
  surfaceVariant = PearlWhiteElevated,// #F1F2F4
  onSurfaceVariant = CharcoalGray,    // #4A4A4A
  outline = PearlWhiteBorder,         // #E5E7EB
  outlineVariant = PearlWhiteBorder,
  error = CharcoalGray,
  onError = PearlWhitePure,
)

/**
 * System Dark Mode Scheme:
 * - Deep acrylic black (#050505) background
 * - Pearl white text (#FFFFFF)
 * - Surfaces: Deep acrylic (#121212)
 * - Secondary text: Dark muted gray (#9E9E9E)
 * - Buttons & Interactive: Electric Blue (#0052FF)
 */
val DarkColorScheme = darkColorScheme(
  primary = ElectricBlue,
  onPrimary = PearlWhitePure,
  primaryContainer = ElectricBlueSubtle,
  onPrimaryContainer = ElectricBlueLight,
  secondary = DarkMutedGray,
  onSecondary = DeepAcrylicBlack,
  secondaryContainer = DarkAcrylicElevated,
  onSecondaryContainer = PearlWhitePure,
  tertiary = ElectricBlue,
  onTertiary = DeepAcrylicBlack,
  background = DeepAcrylicBlack,      // #050505
  onBackground = PearlWhitePure,      // #FFFFFF
  surface = DarkAcrylicSurface,       // #121212
  onSurface = PearlWhitePure,         // #FFFFFF
  surfaceVariant = DarkAcrylicElevated, // #1A1A1A
  onSurfaceVariant = DarkMutedGray,   // #9E9E9E
  outline = DarkAcrylicBorder,        // #262626
  outlineVariant = DarkAcrylicBorder,
  error = DarkMutedGray,
  onError = PearlWhitePure,
)

@Composable
fun animatedColorScheme(target: ColorScheme): ColorScheme {
  val primary by animateColorAsState(target.primary, tween(250), label = "c_primary")
  val onPrimary by animateColorAsState(target.onPrimary, tween(250), label = "c_onPrimary")
  val primaryContainer by animateColorAsState(target.primaryContainer, tween(250), label = "c_primaryContainer")
  val onPrimaryContainer by animateColorAsState(target.onPrimaryContainer, tween(250), label = "c_onPrimaryContainer")
  val secondary by animateColorAsState(target.secondary, tween(250), label = "c_secondary")
  val onSecondary by animateColorAsState(target.onSecondary, tween(250), label = "c_onSecondary")
  val secondaryContainer by animateColorAsState(target.secondaryContainer, tween(250), label = "c_secondaryContainer")
  val onSecondaryContainer by animateColorAsState(target.onSecondaryContainer, tween(250), label = "c_onSecondaryContainer")
  val background by animateColorAsState(target.background, tween(250), label = "c_background")
  val onBackground by animateColorAsState(target.onBackground, tween(250), label = "c_onBackground")
  val surface by animateColorAsState(target.surface, tween(250), label = "c_surface")
  val onSurface by animateColorAsState(target.onSurface, tween(250), label = "c_onSurface")
  val surfaceVariant by animateColorAsState(target.surfaceVariant, tween(250), label = "c_surfaceVariant")
  val onSurfaceVariant by animateColorAsState(target.onSurfaceVariant, tween(250), label = "c_onSurfaceVariant")
  val outline by animateColorAsState(target.outline, tween(250), label = "c_outline")
  val outlineVariant by animateColorAsState(target.outlineVariant, tween(250), label = "c_outlineVariant")
  val error by animateColorAsState(target.error, tween(250), label = "c_error")
  val onError by animateColorAsState(target.onError, tween(250), label = "c_onError")

  return ColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    primaryContainer = primaryContainer,
    onPrimaryContainer = onPrimaryContainer,
    inversePrimary = target.inversePrimary,
    secondary = secondary,
    onSecondary = onSecondary,
    secondaryContainer = secondaryContainer,
    onSecondaryContainer = onSecondaryContainer,
    tertiary = target.tertiary,
    onTertiary = target.onTertiary,
    tertiaryContainer = target.tertiaryContainer,
    onTertiaryContainer = target.onTertiaryContainer,
    background = background,
    onBackground = onBackground,
    surface = surface,
    onSurface = onSurface,
    surfaceVariant = surfaceVariant,
    onSurfaceVariant = onSurfaceVariant,
    surfaceTint = target.surfaceTint,
    inverseSurface = target.inverseSurface,
    inverseOnSurface = target.inverseOnSurface,
    error = error,
    onError = onError,
    errorContainer = target.errorContainer,
    onErrorContainer = target.onErrorContainer,
    outline = outline,
    outlineVariant = outlineVariant,
    scrim = target.scrim,
    surfaceBright = target.surfaceBright,
    surfaceDim = target.surfaceDim,
    surfaceContainer = target.surfaceContainer,
    surfaceContainerHigh = target.surfaceContainerHigh,
    surfaceContainerHighest = target.surfaceContainerHighest,
    surfaceContainerLow = target.surfaceContainerLow,
    surfaceContainerLowest = target.surfaceContainerLowest,
  )
}

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit,
) {
  val targetScheme = if (darkTheme) DarkColorScheme else LightColorScheme
  val animatedScheme = animatedColorScheme(targetScheme)

  CompositionLocalProvider(LocalIsDarkTheme provides darkTheme) {
    MaterialTheme(
      colorScheme = animatedScheme,
      typography = Typography,
      content = content,
    )
  }
}
