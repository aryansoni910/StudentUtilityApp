package com.example.project_1.Presentation.Screen.StudentScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project_1.Presentation.Navigation.Routes
import com.example.project_1.Presentation.ViewModel.AppState
import com.example.project_1.Presentation.ViewModel.Project1ViewModel

@Composable
fun PasswordManagerEditScreen(
    viewModel: Project1ViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    passwordState : AppState = AppState(),
    onEvent:() -> Unit = {}
) {

    var isPasswordVisible = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2E0138))
         ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.Start)
                .size(50.dp).clickable {
                    navController.navigate(Routes.PasswordManagerScreen)
                }
        )
        Text(
            "Add Password",
            style = TextStyle(fontSize = 40.sp, color = Color.White),
            modifier = Modifier.padding(top = 50.dp)

        )
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp).background(Color.White), RoundedCornerShape(30.dp)
        ) {
         Spacer(modifier = Modifier.padding(30.dp))
            OutlinedTextField(
                value = passwordState.title.value,
                onValueChange = { passwordState.title.value = it },
                label = { Text("Title", color = Color.Black) },
                placeholder = { Text("Enter your title", color = Color.Black) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            Spacer(modifier = Modifier.padding(5.dp))

            OutlinedTextField(
                value = passwordState.name.value,
                onValueChange = { passwordState.name.value = it },
                label = { Text("Username", color = Color.Black) },
                placeholder = { Text("Enter your username", color = Color.Black) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            Spacer(modifier = Modifier.padding(5.dp))

            OutlinedTextField(
                value = passwordState.password.value,
                onValueChange = { passwordState.password.value = it },
                label = { Text("Password", color = Color.Black) },
                leadingIcon = {
                    IconButton(onClick = { isPasswordVisible.value = !isPasswordVisible.value }) {
                        Icon(
                            imageVector = if (isPasswordVisible.value) Icons.Default.Lock else Icons.Default.Lock,
                            contentDescription = if (isPasswordVisible.value) "Hide password" else "Show password"
                        )
                    }
                },
                visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),

                placeholder = { Text("Enter your password", color = Color.Black) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)


            )


            Button(onClick = {
                onEvent.invoke()
                navController.navigateUp()
            }, modifier = Modifier.fillMaxWidth().padding(40.dp)) {
                Text(text = "Save")
            }
        }
    }
}