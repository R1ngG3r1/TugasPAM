package com.example.myprofileapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource

import myprofileapp.shared.generated.resources.Res
import myprofileapp.shared.generated.resources.foto


@Composable
// Menampilkan profile header 
fun ProfileHeader() {
    Card(
        modifier = Modifier
            .width(350.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = androidx.compose.ui.graphics.Color(0xFFFFF8E7)
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .background(
                        color = androidx.compose.ui.graphics.Color(0xFFD4A373),
                        shape = CircleShape
                    )
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.foto),
                    contentDescription = "Foto Profil",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
            }

            Text(
                text = "Hafidz Raihan Putra Anfa",
                fontSize = 22.sp,
                modifier = Modifier.padding(top = 12.dp)
            )

            Text(
                text = "Mahasiswa teknik informatika ITERA angkatan tahun 2024",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

// template informasi untuk profile card
@Composable
fun InfoItem(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f)
        )

        Text(text = value)
    }
}

// menampilkan profile card
@Composable
fun ProfileCard() {
    Card(
        modifier = Modifier
            .width(350.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = androidx.compose.ui.graphics.Color(0xFFFFF8E7)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "Informasi Kontak",
                style = MaterialTheme.typography.titleMedium
            )

            InfoItem(
                label = "Email",
                value = "hafidz@gmail.com"
            )

            InfoItem(
                label = "Phone",
                value = "089172171265"
            )

            InfoItem(
                label = "Lokasi",
                value = "Bandar Lampung"
            )
        }
    }
}

// fungsi utama
@Composable
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            ProfileHeader()

            ProfileCard()

            Button(
                onClick = {},
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Follow")
            }
        }
    }
}