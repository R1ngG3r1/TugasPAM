package com.example.myprofileapp.viewmodel

import com.example.myprofileapp.data.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel {

    private val _uiState = MutableStateFlow(ProfileUiState())

    val uiState: StateFlow<ProfileUiState> =
        _uiState.asStateFlow()

    fun updateName(name: String) {
        _uiState.update { currentState ->
            currentState.copy(name = name)
        }
    }

    fun updateBio(bio: String) {
        _uiState.update { currentState ->
            currentState.copy(bio = bio)
        }
    }

    fun modeDarkMode() {
        _uiState.update { currentState ->
            currentState.copy(
                isDarkMode = !currentState.isDarkMode
            )
        }
    }
}