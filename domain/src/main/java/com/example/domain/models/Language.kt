package com.example.domain.models

enum class Language(val code: String) {
    EN("en"), UA("uk");

    companion object {
        fun fromCode(code: String) = values().firstOrNull { it.code == code } ?: EN
    }
}
