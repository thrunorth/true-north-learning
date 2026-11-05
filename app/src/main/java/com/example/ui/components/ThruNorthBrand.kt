package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.LocalIsDarkTheme

@Composable
fun ThruNorthHeaderLogo(
  modifier: Modifier = Modifier,
  iconSize: Dp = 34.dp,
  showTagline: Boolean = true,
  onClick: (() -> Unit)? = null,
) {
  val isDark = LocalIsDarkTheme.current
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier.then(
      if (onClick != null) Modifier.clickable { onClick() } else Modifier
    )
  ) {
    // Custom Attached Logo Image
    Box(
      modifier = Modifier
        .size(iconSize)
        .clip(RoundedCornerShape(8.dp))
        .background(Color.Black)
        .then(if (!isDark) Modifier.border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp)) else Modifier),
      contentAlignment = Alignment.Center
    ) {
      Image(
        painter = painterResource(id = R.drawable.ic_thru_north_logo),
        contentDescription = "THRU NORTH Logo",
        modifier = Modifier.size(iconSize)
      )
    }

    Spacer(modifier = Modifier.width(10.dp))

    Column {
      Text(
        text = "THRU NORTH",
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 14.sp,
        fontWeight = FontWeight.ExtraBold,
        fontFamily = FontFamily.SansSerif,
        letterSpacing = 1.6.sp
      )
      if (showTagline) {
        Text(
          text = "STAY AHEAD. BE SMART.",
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          fontSize = 8.5.sp,
          fontWeight = FontWeight.SemiBold,
          fontFamily = FontFamily.SansSerif,
          letterSpacing = 2.0.sp
        )
      }
    }
  }
}

@Composable
fun ThruNorthHeroBrand(
  modifier: Modifier = Modifier,
  logoSize: Dp = 96.dp
) {
  val isDark = LocalIsDarkTheme.current
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Custom Graphic Attached Brand Asset
    Box(
      modifier = Modifier
        .size(logoSize)
        .clip(RoundedCornerShape(18.dp))
        .background(Color.Black)
        .then(if (!isDark) Modifier.border(1.5.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(18.dp)) else Modifier),
      contentAlignment = Alignment.Center
    ) {
      Image(
        painter = painterResource(id = R.drawable.ic_thru_north_logo),
        contentDescription = "THRU NORTH — Stay Ahead. Be Smart.",
        modifier = Modifier.size(logoSize)
      )
    }

    Spacer(modifier = Modifier.height(14.dp))

    Text(
      text = "THRU NORTH",
      color = MaterialTheme.colorScheme.onBackground,
      fontSize = 28.sp,
      fontWeight = FontWeight.Black,
      fontFamily = FontFamily.SansSerif,
      letterSpacing = 3.sp
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
      text = "STAY AHEAD. BE SMART.",
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold,
      fontFamily = FontFamily.SansSerif,
      letterSpacing = 3.5.sp
    )
  }
}
