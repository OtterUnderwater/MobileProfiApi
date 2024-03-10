package com.example.appprofiapi.models

//С помощью JSON плагина добавляем модели и дописываем :java.io.Serializable
data class Catalog(
    val bio: String,
    val category: String,
    val description: String,
    val id: Int,
    val name: String,
    val preparation: String,
    val price: Int,
    val timeResul: String
):java.io.Serializable