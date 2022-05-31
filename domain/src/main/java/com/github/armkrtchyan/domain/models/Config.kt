package com.github.armkrtchyan.domain.models

data class Config(val ui: UiConfig?, val language: LanguageConfig?) {
    data class UiConfig(val homeScreen: HomeScreen?)
    data class HomeScreen(val isButtonVisible: Boolean?)
    data class LanguageConfig(val languages: List<Language>?)
    data class Language(val languageName: String)
}
