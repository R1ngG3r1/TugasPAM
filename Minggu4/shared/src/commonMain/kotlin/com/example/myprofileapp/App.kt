package com.example.myprofileapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myprofileapp.data.ProfileUiState
import com.example.myprofileapp.ui.ProfileScreen
import com.example.myprofileapp.viewmodel.ProfileViewModel
import myprofileapp.shared.generated.resources.Res
import myprofileapp.shared.generated.resources.foto
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProfilePhoto() {
    Box(
        modifier = Modifier
            .size(80.dp)
            .background(Color(0xFFD4A373), CircleShape)
            .padding(5.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.foto),
            contentDescription = "Foto Profil",
            modifier = Modifier.fillMaxSize().clip(CircleShape)
        )
    }
}

@Composable
fun ProfileHeader(
    name: String,
    bio: String,
    dark: Boolean,
    onDarkMode: () -> Unit
) {
    val text = if (dark) Color.White else Color.Black
    val card = if (dark) Color(0xFF2B2B2B) else Color(0xFFE8E8E8)

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ) {
        val screenWidth = this.maxWidth

        Card(
            modifier = Modifier.fillMaxWidth().widthIn(max = 500.dp),
            colors = CardDefaults.cardColors(containerColor = card)
        ) {
            if (screenWidth < 500.dp) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ProfilePhoto()

                        Column(
                            modifier = Modifier.weight(1f).padding(start = 12.dp)
                        ) {
                            Text(name, fontSize = 19.sp, color = text)
                            Text(bio, fontSize = 12.sp, color = text)
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Dark Mode", color = text)

                        Switch(
                            checked = dark,
                            onCheckedChange = { onDarkMode() }
                        )
                    }
                }
            } else {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProfilePhoto()

                    Column(
                        modifier = Modifier.weight(1f).padding(horizontal = 12.dp)
                    ) {
                        Text(name, fontSize = 19.sp, color = text)
                        Text(bio, fontSize = 12.sp, color = text)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Dark Mode", color = text)

                        Switch(
                            checked = dark,
                            onCheckedChange = { onDarkMode() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InfoItem(label: String, value: String, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
    ) {
        Text(
            "$label :",
            modifier = Modifier.weight(1f),
            color = color
        )

        Text(value, color = color)
    }
}

@Composable
fun ProfileCard(
    state: ProfileUiState,
    viewModel: ProfileViewModel,
    name: String,
    bio: String,
    onNameChange: (String) -> Unit,
    onBioChange: (String) -> Unit,
    editing: Boolean,
    onEdit: () -> Unit,
    onCancel: () -> Unit
) {
    val text = if (state.isDarkMode) Color.White else Color.Black
    val card = if (state.isDarkMode) Color(0xFF2B2B2B) else Color(0xFFE8E8E8)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 500.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = card)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Informasi Kontak",
                style = MaterialTheme.typography.titleMedium,
                color = text
            )

            InfoItem("Email", state.email, text)
            InfoItem("Phone", state.phone, text)
            InfoItem("Lokasi", state.location, text)

            ProfileScreen(
                uiState = state,
                viewModel = viewModel,
                name = name,
                bio = bio,
                onNameChange = onNameChange,
                onBioChange = onBioChange,
                isEditing = editing,
                onEditClick = onEdit,
                onCancel = onCancel
            )
        }
    }
}

@Composable
fun App() {
    val viewModel = remember { ProfileViewModel() }
    val state by viewModel.uiState.collectAsState()

    var editing by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(state.name) }
    var bio by remember { mutableStateOf(state.bio) }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize().safeDrawingPadding(),
            color = if (state.isDarkMode) Color(0xFF121212) else Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileHeader(
                    name = state.name,
                    bio = state.bio,
                    dark = state.isDarkMode,
                    onDarkMode = { viewModel.modeDarkMode() }
                )

                ProfileCard(
                    state = state,
                    viewModel = viewModel,
                    name = name,
                    bio = bio,
                    onNameChange = { name = it },
                    onBioChange = { bio = it },
                    editing = editing,
                    onEdit = {
                        name = state.name
                        bio = state.bio
                        editing = true
                    },
                    onCancel = { editing = false }
                )
            }
        }
    }
}