package com.example.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CompassLogo(
  modifier: Modifier = Modifier,
  size: Dp = 32.dp,
  showText: Boolean = true,
  onClick: (() -> Unit)? = null,
) {
  ThruNorthHeaderLogo(
    modifier = modifier,
    iconSize = size,
    showTagline = showText,
    onClick = onClick
  )
}
