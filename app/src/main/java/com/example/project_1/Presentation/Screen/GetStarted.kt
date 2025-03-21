package com.example.project_1.Presentation.Screen

import Choice
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.project_1.Presentation.Navigation.Routes
import com.example.project_1.R

@Composable
fun GetStarted(navController: NavController) {
    val state by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.gets)
    )
    val progress by animateLottieCompositionAsState(
        state, iterations = LottieConstants.IterateForever
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color(0xFFE3B1FD)
                )
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            )
            {
                LottieAnimation(
                    composition = state,
                    progress = { progress },
                    modifier = Modifier.size(600.dp)

                )
            }
            Spacer(modifier = Modifier.padding(30.dp))

            Button(
                onClick = {
                    navController.navigate(Routes.choice)
                }, colors = ButtonDefaults.buttonColors(Color(0xFF020933)), modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)


            ) {
                Text(text = "Get Started", fontWeight = FontWeight.W800, style = TextStyle.Default, color = Color.White)
            }


        }
    }
}