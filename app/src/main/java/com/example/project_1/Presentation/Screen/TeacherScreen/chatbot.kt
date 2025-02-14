package com.example.project_1.Presentation.Screen.TeacherScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.project_1.Domain.Model.ChatBotEnum
import com.example.project_1.Domain.Model.chatbotData
import com.example.project_1.Presentation.ViewModel.Project1ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatBox(viewModel: Project1ViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Help Ai",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        ), modifier = Modifier.padding(start = 90.dp)
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFF8ED84) // Custom color for the TopAppBar background
                )

            )
        },
        content = { innerpadding ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().padding(innerpadding)
            ) {


                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    if(viewModel.list.isEmpty()){
                        Text("No Chat Available")
                    }
                    else {
                        ChatBot(list = viewModel.list)
                    }
                }
                    ChatFooter {
                        if (it.isNotEmpty()) {
                            viewModel.sendMessage(it)
                        }
                    }

                }

        })
}

@Composable
fun ChatBot(list: MutableList<chatbotData>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list) {
            if (it.role == ChatBotEnum.User.role) {
                Text(
                    text = it.message,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .padding(12.dp),
                    color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold
                )
            } else {
                Text(
                    text = it.message,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                        .padding(12.dp),
                    color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Normal
                )
            }

        }
    }
}
@Composable
fun ChatFooter(onClick: (text: String) -> Unit) {
    var inputText by remember { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .background(
                Color.LightGray
            )
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            placeholder = { Text("Enter your InputText") },
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .background(Color.Gray)
        )


        IconButton(onClick = {
            onClick(inputText)
            inputText = ""
        }) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(
                        CircleShape
                    )
                    .background(Color.Black)
                    .padding(8.dp),
                tint = Color.White
            )
        }
    }

}

