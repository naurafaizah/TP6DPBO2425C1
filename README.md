# TP6DPBO2425C1

// Naura Nur Faizah // NIM 2408352 // Ilmu Komputer C1

Saya Naura Nur Faizah dengan NIM 2408352 mengerjakan TP 6 dalam mata kuliah Desain Pemrograman Berbasis Objek untuk keberkahan-Nya maka saya tidak akan melakukan kecurangan seperti yang telah di spesifikasikan

# desain program
program ini merupakan game sederhana flappy bird berbasis java swing, di mana pemain mengendalikan seekor burung agar tidak menabrak pipa yang bergerak dari kanan ke kiri.
desain programnya dibagi menjadi beberapa kelas dengan fungsi masing-masing:

class app
- berfungsi sebagai main class atau titik awal program.
- membuat jendela utama (jframe) dengan ukuran tertentu dan judul “flappy bird”.
- menginisialisasi objek logic dan view, lalu menambahkan tampilan (view) ke frame.
- mengatur properti seperti posisi tengah, ukuran tetap, dan menampilkan frame ke layar.

class logic
- berfungsi sebagai pengendali utama game (game controller).
- mengatur seluruh mekanisme permainan, seperti:
  - pergerakan burung (terpengaruh gravitasi dan input tombol).
  - pergerakan pipa yang berjalan ke kiri.
  - penambahan pipa baru secara berkala menggunakan timer.
  - deteksi tabrakan antara burung dan pipa.
  - perhitungan skor ketika burung berhasil melewati pipa.
  - kondisi game over dan restart game.
menggunakan dua timer:
  - gameLoop → menjalankan logika game setiap frame (sekitar 60 fps).
  - pipesCooldown → menambahkan pipa baru setiap 1.5 detik.

class view (tidak kamu tampilkan di sini tapi digunakan dalam program)
- berfungsi sebagai panel tampilan (canvas) tempat semua gambar digambar.
- menggambar latar belakang, burung, dan pipa berdasarkan data dari logic.
- menerima event dari keyboard melalui keylistener.

class player
- menyimpan informasi tentang burung pemain seperti posisi, kecepatan, ukuran, dan gambar.
- digunakan oleh logic untuk mengatur gerakan burung dan deteksi tabrakan.

class pipe
- menyimpan informasi setiap pipa seperti posisi, ukuran, dan gambar (atas atau bawah).
- diatur oleh logic untuk bergerak dan dideteksi tabrakannya dengan burung.

# alur program
1. program dimulai dari class app
   - objek logic dan view dibuat.
   - view ditambahkan ke jendela utama (frame) dan ditampilkan di layar.

2. objek logic mulai bekerja
   - timer pertama (pipesCooldown) membuat pipa baru setiap 1.5 detik.
   - timer kedua (gameLoop) memanggil fungsi move() secara berulang untuk memperbarui posisi burung dan pipa setiap frame.

3. pergerakan burung
   - burung jatuh ke bawah karena efek gravitasi.
   - jika pemain menekan spasi, burung naik ke atas dengan kecepatan negatif.

4. pergerakan pipa
   - setiap pipa bergerak ke kiri secara terus-menerus.
   - jika pipa atas sudah dilewati burung, skor bertambah.

5. deteksi tabrakan
   - ketika burung menabrak salah satu pipa atau jatuh ke bawah layar, permainan berakhir.
   - timer berhenti, dan muncul pesan “game over”.

6. restart game
   - jika pemain menekan tombol R, semua nilai direset:
     - posisi burung kembali ke awal.
     - daftar pipa dihapus.
     - skor direset ke 0.
     - timer berjalan kembali.

7. penggambaran tampilan
   - view.repaint() dipanggil setiap frame agar tampilan layar selalu diperbarui sesuai kondisi terbaru dari logic.


# dokumentasi 
muncul skor
<img width="535" height="970" alt="Screenshot 2025-11-02 075057" src="https://github.com/user-attachments/assets/74860cca-bd4a-4369-ace1-2dbfaf31a23f" />

restart tekan R
<img width="540" height="974" alt="Screenshot 2025-11-02 075026" src="https://github.com/user-attachments/assets/fbe52d32-ad19-4464-acf7-6a18c68c39d0" />


