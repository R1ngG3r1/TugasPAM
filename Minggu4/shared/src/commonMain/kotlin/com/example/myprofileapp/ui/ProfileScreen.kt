package com.example.myprofileapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myprofileapp.data.ProfileUiState
import com.example.myprofileapp.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    viewModel: ProfileViewModel,
    name: String,
    bio: String,
    onNameChange: (String) -> Unit,
    onBioChange: (String) -> Unit,
    isEditing: Boolean,
    onEditClick: () -> Unit,
    onCancel: () -> Unit
) {
    Button(
        onClick = onEditClick
    ) {
        Text("Edit Profile")
    }

    if (isEditing) {
        AlertDialog(
            onDismissRequest = onCancel,
            title = {
                Text("Edit Profile")
            },
            text = {
                Column {
                    TextField(
                        value = name,
                        onValueChange = onNameChange,
                        label = {
                            Text("Nama")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    TextField(
                        value = bio,
                        onValueChange = onBioChange,
                        label = {
                            Text("Bio")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.updateName(name)
                        viewModel.updateBio(bio)
                        onCancel()
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                Button(
                    onClick = onCancel
                ) {
                    Text("Batal")
                }
            }
        )
    }
}