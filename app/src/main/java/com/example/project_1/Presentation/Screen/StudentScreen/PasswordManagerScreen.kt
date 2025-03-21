package com.example.project_1.Presentation.Screen.StudentScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.project_1.Data.Database.PasswordManager
import com.example.project_1.Presentation.Navigation.Routes
import com.example.project_1.Presentation.ViewModel.AppState
import com.example.project_1.Presentation.ViewModel.Project1ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun PasswordManagerScreen(
    viewModel: Project1ViewModel = hiltViewModel(),
    navController: NavController,
    passwordState: AppState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .background(Color.Black),
                title = {
                    Row(Modifier.fillMaxWidth()) {

                        Text(
                            "Password Manager",
                            style = TextStyle(
                                fontStyle = FontStyle.Italic,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFF2C9FA)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Routes.AddPasswordManagerScreen) }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            items(passwordState.allpassword) {
                CardItems(passworManager = it, viewModel = viewModel,navController = navController,passwordState = passwordState)
                Spacer(modifier = Modifier.height(5.dp))

            }
        }
    }
}

@Composable
fun CardItems(passworManager: PasswordManager, viewModel: Project1ViewModel = hiltViewModel(),navController: NavController,passwordState: AppState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                passwordState.id.value = passworManager.id
                passwordState.title.value = passworManager.title
                passwordState.name.value = passworManager.user_name
                passwordState.password.value = passworManager.password
                navController.navigate(Routes.AddPasswordManagerScreen)
            }
            .padding(8.dp), RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEBD7EF),
            Color.Black
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically // Add padding to Row for spacing
        ) {
            Column {
                Text(
                    text = "Title : ${passworManager.title}",
                    modifier = Modifier.padding(start = 10.dp),
                    style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Username : ${passworManager.user_name}",
                    modifier = Modifier.padding(start = 10.dp, top = 4.dp),
                    style = TextStyle(color = Color.Black, fontWeight = FontWeight.Normal)
                )
            }
            // Delete Icon aligned to the end of the row
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        viewModel.passwordstate.value.id.value = passworManager.id
                        viewModel.passwordstate.value.title.value = passworManager.title
                        viewModel.passwordstate.value.name.value = passworManager.user_name
                        viewModel.passwordstate.value.password.value = passworManager.password
                        viewModel.deletepassword()
                    })
        }
    }
}
