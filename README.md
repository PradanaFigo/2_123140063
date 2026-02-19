# 📰 News Feed Simulator

Proyek ini adalah simulasi aliran berita (*News Feed*) reaktif yang dibangun menggunakan **Kotlin** dan **Compose Multiplatform**. Aplikasi ini menerapkan arsitektur **MVVM (Model-View-ViewModel)** yang bersih (Clean Architecture) untuk memisahkan logika data dan antarmuka pengguna.

Proyek ini disusun untuk memenuhi tugas mata kuliah **Pengembangan Aplikasi Mobile - RB**.

##  Pemenuhan Rubrik Penilaian

Aplikasi ini telah mengimplementasikan seluruh kriteria penilaian tugas:

### 1. Implementasi Flow 
- **Lokasi:** `data/NewsRepository.kt`
- **Penjelasan:** Menggunakan *Flow builder* (`flow { ... }`) untuk membuat *stream* data, di mana data berita baru disimulasikan dan dikirim menggunakan `emit()` setiap 2 detik secara terus-menerus.

### 2. Penggunaan Operators 
- **Lokasi:** `ui/NewsFeedScreen.kt` dan `presentation/NewsFeedViewModel.kt`
- **Penjelasan:** - `onEach`: Digunakan di dalam ViewModel untuk memantau aliran *flow* dan menambahkan berita baru ke *state list* setiap kali ada data yang di-*emit*.
    - `filter`: Digunakan di UI untuk menyaring daftar berita berdasarkan kategori yang sedang dipilih oleh pengguna (Tech, Sports, dll).
    - `map`: Digunakan untuk mentransformasi format data mentah menjadi format *display* (menambahkan tag kategori di depan judul berita).

### 3. StateFlow Implementation 
- **Lokasi:** `presentation/NewsFeedViewModel.kt`
- **Penjelasan:** Menggunakan `MutableStateFlow` dan `.asStateFlow()` untuk mengelola *state* yang reaktif secara aman. *State* yang dikelola meliputi:
    - `readCount`: Menyimpan jumlah berita yang sudah diklik/dibaca.
    - `selectedCategory`: Menyimpan status tab filter kategori yang aktif.
    - `allNews`: Menyimpan daftar seluruh berita yang telah masuk dari *repository*.

### 4. Coroutines Usage 
- **Lokasi:** `presentation/NewsFeedViewModel.kt`
- **Penjelasan:** Menggunakan `scope.launch` untuk menjalankan *Coroutines*. Di dalamnya, terdapat simulasi pemanggilan jaringan asinkron (mengambil detail berita) menggunakan `async(Dispatchers.Default)` dan menunggunya dengan `await()` tanpa memblokir *Thread* utama (UI).

### 5. Kode dan Dokumentasi 
- **Lokasi:** Seluruh *source code*.
- **Penjelasan:** Mengimplementasikan *Clean Code* dengan memisahkan kode ke dalam *package* yang terstruktur: `model` (struktur data), `data` (sumber data/repository), `presentation` (ViewModel dan *state*), serta `ui` (Jetpack Compose Screen).

---

##  Cara Menjalankan Aplikasi (Langkah-langkah)

Karena proyek ini menggunakan basis **Compose Multiplatform**, aplikasi akan berjalan sebagai aplikasi Desktop. Berikut adalah panduan langkah demi langkah untuk menjalankannya:

1. **Persiapan IDE:** Pastikan Anda menggunakan **Android Studio** atau **IntelliJ IDEA** versi terbaru.
2. **Buka Proyek:** Pilih menu `File > Open...` dan arahkan ke folder proyek `NewsFeedSimulator` ini.
3. **Tunggu Gradle Sync:** Perhatikan bagian pojok kanan bawah IDE. Tunggu hingga proses sinkronisasi Gradle selesai sepenuhnya (mengunduh *dependencies* Kotlin, Coroutines, dan Compose).
4. **Jalankan Aplikasi:** Klik tombol **Run** (segitiga hijau) atau tekan `Shift + F10`.
5. **Simulasi Berjalan:** Jendela aplikasi Desktop akan terbuka. Anda akan melihat berita baru masuk secara otomatis setiap 2 detik. Cobalah klik filter kategori di bagian atas, dan klik salah satu *card* berita untuk melihat *counter* "Dibaca" bertambah.

---
**Dikerjakan Oleh:**
- **Nama:** Pradana Figo Ariasya
- **NIM:** 123140063
- **Mata Kuliah:** Pengembangan Aplikasi Mobile RB

**ScrEenshoot:**
<img width="1021" height="753" alt="image" src="https://github.com/user-attachments/assets/0bd9394d-b334-4670-a5ab-e63bee89b2e9" />

