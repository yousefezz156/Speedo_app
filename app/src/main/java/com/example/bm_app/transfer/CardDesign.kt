package com.example.bm_app.transfer

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bm_app.R

@Composable
fun CardFrontDesign(shimmerPhase: Float) {

    val Burgundy = Color(0xFF871E35)
    val Cream = Color(0xFFFCF7E9)
    val SoftGold = Color(0xFFF5E6C8)
    val TextLight = Color(0xFFFFF8EE)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Burgundy,
                        Burgundy.copy(alpha = 0.95f),
                        Color(0xFF5E1223)
                    )
                )
            )
    ) {

        // ===== Metallic Top Glow =====
        Canvas(Modifier.fillMaxSize()) {
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Cream.copy(alpha = 0.25f),
                        Color.Transparent
                    ),
                    startY = 0f,
                    endY = size.height * 0.4f
                )
            )
        }

        // ===== Subtle Diagonal Reflection =====
        Canvas(Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            val shimmerStart = -w + (w * 3 * shimmerPhase)

            drawRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Transparent,
                        Cream.copy(alpha = 0.15f),
                        Color.Transparent
                    ),
                    start = Offset(shimmerStart, 0f),
                    end = Offset(shimmerStart + w * 0.5f, h)
                )
            )
        }

        // ===== Inner Border Highlight =====
        Canvas(Modifier.fillMaxSize()) {
            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Cream.copy(alpha = 0.4f),
                        Color.Transparent
                    )
                ),
                style = Stroke(width = 3f),
                cornerRadius = CornerRadius(28.dp.toPx())
            )
        }

        // ===== Content =====
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(26.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // Top Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(
                                        Cream.copy(alpha = 0.4f),
                                        Color.Transparent
                                    )
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.flashicon),
                            contentDescription = null,
                            tint = TextLight,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(Modifier.width(12.dp))

                    Text(
                        "SPEEDO",
                        color = TextLight,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp
                    )
                }

                Text(
                    "PLATINUM",
                    color = SoftGold,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Bottom Section
            Column {

                Text(
                    "••••  ••••  ••••  1234",
                    color = SoftGold,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 2.sp
                )

                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        "VALID THRU 12/28",
                        color = SoftGold.copy(alpha = 0.7f),
                        fontSize = 12.sp
                    )

                    Text(
                        "VISA",
                        color = Cream,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = (-1).sp
                    )
                }
            }
        }
    }
}

@Composable
fun CardBackDesign(shimmerPhase: Float) {

    val Burgundy = Color(0xFF871E35)
    val DeepBurgundy = Color(0xFF5E1223)
    val Cream = Color(0xFFFCF7E9)
    val SoftGold = Color(0xFFF5E6C8)
    val TextLight = Color(0xFFFFF8EE)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Burgundy,
                        Burgundy.copy(alpha = 0.95f),
                        DeepBurgundy
                    )
                )
            )
    ) {

        // ===== Metallic Top Light =====
        Canvas(Modifier.fillMaxSize()) {
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Cream.copy(alpha = 0.18f),
                        Color.Transparent
                    ),
                    startY = 0f,
                    endY = size.height * 0.35f
                )
            )
        }
        Canvas(Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            val shimmerStart = -w + (w * 3 * shimmerPhase)

            drawRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Transparent,
                        Cream.copy(alpha = 0.15f),
                        Color.Transparent
                    ),
                    start = Offset(shimmerStart, 0f),
                    end = Offset(shimmerStart + w * 0.5f, h)
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 28.dp)
        ) {

            // ===== Magnetic Strip =====
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF2C2C2C),
                                Color(0xFF000000),
                                Color(0xFF1A1A1A)
                            )
                        )
                    )
            ) {
                // subtle gloss
                Canvas(Modifier.fillMaxSize()) {
                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.08f),
                                Color.Transparent
                            )
                        )
                    )
                }
            }

            Spacer(Modifier.height(28.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(42.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Cream.copy(alpha = 0.9f),
                                    Cream.copy(alpha = 0.7f)
                                )
                            )
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                    Text(
                        "Authorized Signature",
                        color = Color.Black.copy(alpha = 0.6f),
                        fontSize = 11.sp,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(start = 14.dp)
                    )

                        Spacer(Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .width(50.dp)
                                .height(24.dp)
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Cream,
                                            SoftGold
                                        )
                                    )
                                )
                                //.shadow(6.dp, RoundedCornerShape(8.dp))
                            ,
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "892",
                                color = Burgundy,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily.Monospace,
                                letterSpacing = 2.sp
                            )
                        }

                    }
                }

                Spacer(Modifier.width(16.dp))

                Column(horizontalAlignment = Alignment.End) {


                    Text(
                        text = "PLATINUM",
                        color= SoftGold,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically, modifier= Modifier.padding(horizontal = 16.dp)) {

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    Cream.copy(alpha = 0.4f),
                                    Color.Transparent
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.flashicon),
                        contentDescription = null,
                        tint = TextLight,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(Modifier.width(12.dp))

                Text(
                    "SPEEDO",
                    color = TextLight,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
            }

        }

        // ===== Inner Border Highlight =====
        Canvas(Modifier.fillMaxSize()) {
            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Cream.copy(alpha = 0.35f),
                        Color.Transparent
                    )
                ),
                style = Stroke(width = 3f),
                cornerRadius = CornerRadius(28.dp.toPx())
            )
        }
    }
}

@Preview
@Composable
private fun wetPaintPrev() {
    val infiniteTransition = rememberInfiniteTransition(label = "Gloss")
    val shimmerPhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "Shimmer"
    )
    //CardFrontDesign(shimmerPhase)
    //PremiumEnergyCardBack()
}
