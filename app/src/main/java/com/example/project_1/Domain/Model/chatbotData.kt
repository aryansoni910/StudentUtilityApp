package com.example.project_1.Domain.Model

data class chatbotData(
    val message : String,
    val role : String
)

enum class ChatBotEnum(val role: String){
    User("user"),
    Model("model")
}
