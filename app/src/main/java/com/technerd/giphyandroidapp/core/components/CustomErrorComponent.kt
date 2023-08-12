package com.technerd.giphyandroidapp.core.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.technerd.giphyandroidapp.R

@Composable
fun CustomErrorComponent(
    desiredRawAnimationFile: Int? = null, additionalButtonFunction: (() -> Unit)? = null,
) {
    // to keep track if the animation is playing
    // and play pause accordingly
    val isPlaying by remember {
        mutableStateOf(true)
    }

    // for speed
    val speed by remember {
        mutableFloatStateOf(1f)
    }

    // remember lottie composition, which
    // accepts the lottie composition result
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(desiredRawAnimationFile ?: R.raw.something_went_wrong)
    )

    // to control the animation
    val progressMethod by animateLottieCompositionAsState(
        // pass the composition created above
        composition,

        // Iterates Forever
        iterations = LottieConstants.IterateForever,

        // pass isPlaying we created above,
        // changing isPlaying will recompose
        // Lottie and pause/play
        isPlaying = isPlaying,

        speed = speed,

        // this makes animation to restart
        // when paused and play
        // pass false to continue the animation
        // at which it was paused
        restartOnPlay = false

    )

    Column(
        verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize(),
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progressMethod },
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
        )
        AnimatedVisibility(visible = additionalButtonFunction != null) {
            TextButton(modifier = Modifier.fillMaxWidth(), onClick = {
                if (additionalButtonFunction != null) {
                    additionalButtonFunction()
                }
            }) {
                Text(
                    "Try Again",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.subtitle1.copy(
                        color = Color.Black, fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }

}