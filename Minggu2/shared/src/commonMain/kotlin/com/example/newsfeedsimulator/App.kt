package com.example.newsfeedsimulator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.time.Duration.Companion.seconds


data class Berita(
    val judul: String,
    val sumber: String,
    val kategori: String
)

class BacaBerita {
    private val terbaca = MutableStateFlow(0)

    val dibaca: StateFlow<Int> = terbaca.asStateFlow()

    fun beritaTerbaca() {
        terbaca.value++
    }
}


suspend fun ambilDetailBerita(): String {
    delay(1.seconds)
    return "Detail berita berhasil diambil"
}


fun beritaFlow(): Flow<Berita> = flow {

    val daftarBerita = listOf(
        Berita("Mobil Terbang Menggunakan energi Elektromagnetik", "cnnIndonessia", "Teknologi"),
        Berita("Perusahaan A berhasil Menemukan Kendaraan Untuk Menembus Mantel Bumi", "Kompas", "Teknologi"),
        Berita("Mahasiswa Itera Berhasil Menerbitkan Paper Tembus Scopus Dengan Grade Q1", "Itera", "Pendidikan"),
        Berita("Gunung Toba Aktif Kembali Diperkirakan Akan Meletus pada tahun 20515", "BMKG", "Lingkungan"),
        Berita("Kenaikan Air Laut diakibatkan Oleh Pemanasan Global yang meningkat sebesar 5%", "Detik", "Lingkugan"),
        Berita("Space-X Berhasil Membuat Roket Berkecepatan Cahaya", "Space-x", "Teknologi")
    )

    for (berita in daftarBerita) {
        delay(2.seconds)
        emit(berita)
    }
}


suspend fun jalankanBerita(
    dataBerita: BacaBerita,
    tampilkanBerita: (String) -> Unit
) {

    beritaFlow()
        .filter { berita ->
            berita.kategori == "Teknologi"
        }
        .onEach { berita ->

            println("Berita diterima: ${berita.judul}")

            tampilkanBerita(
                "Berita diterima: ${berita.judul}\n"
            )
        }
        .map { berita ->
            "Judul   : ${berita.judul}\n" +
                    "Sumber  : ${berita.sumber}\n" +
                    "Kategori: ${berita.kategori}"
        }
        .collect { hasil ->

            println(hasil)

            tampilkanBerita(
                "$hasil\n"
            )

            coroutineScope {

                val detail = async(Dispatchers.IO) {
                    ambilDetailBerita()
                }

                val hasilDetail = detail.await()

                println(hasilDetail)

                tampilkanBerita(
                    "$hasilDetail\n"
                )

                dataBerita.beritaTerbaca()

                println("Jumlah berita dibaca: ${dataBerita.dibaca.value}")
                println()

                tampilkanBerita(
                    "Jumlah berita dibaca: ${dataBerita.dibaca.value}\n\n"
                )
            }
        }
}


@Composable
fun App() {

    val dataBerita = remember {
        BacaBerita()
    }

    var outputBerita by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {

        jalankanBerita(
            dataBerita = dataBerita,
            tampilkanBerita = { hasil ->
                outputBerita += hasil
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "NEWS FEED SIMULATOR"
        )

        Text(
            text = outputBerita
        )
    }
}