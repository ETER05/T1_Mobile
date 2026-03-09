data class NilaiMahasiswa(
    val nim: String,
    val nama: String,
    val mataKuliah: String,
    val nilai: Int
)

fun getGrade(nilai: Int): String { // Fungsi untuk memberi grade berdasarkan nilai
    return when (nilai) {
        in 86..100 -> "A"
        in 71..85  -> "B"
        in 61..70  -> "C"
        in 51..60  -> "D"
        in 0..50   -> "E"
        else       -> "Invalid"
    }
}

fun format(input: List<NilaiMahasiswa>) { // Fungsi untuk format sehingga tidak ditulis berulang kali
    println(String.format("%-3s %-10s %-20s %-15s %-5s",
        "No", "NIM", "Nama", "Matakuliah", "Nilai"))

    // forEachIndexed { index, element ->
    input.forEachIndexed { index, mhs ->                        // disini tidak menggunakan it karena terdapat 2 parameter
        println(String.format("%-3s %-10s %-20s %-15s %-5s",    // yaitu index dan objek mahasiswa
            index+1,
            mhs.nim,
            mhs.nama,
            mhs.mataKuliah,
            mhs.nilai
        ))
    }
}

fun main() {
    // IDENTITAS
    println("Nama: Wicaksono Hadidul Mannan")
    println("NIM : F1D02310095\n")

    val daftarNilaiMahasiswa = listOf( // Membuat List
        NilaiMahasiswa("2024001", "Budi Santosa", "Pemrograman", 85),
        NilaiMahasiswa("2024002", "Ani Wijaya", "Pemrograman", 92),
        NilaiMahasiswa("2024003", "Citra Dewi", "Pemrograman", 68),
        NilaiMahasiswa("2024004", "Goib Budiman", "Pemrograman", 45),
        NilaiMahasiswa("2024005", "Cahya Nurhati", "Pemrograman", 85),
        NilaiMahasiswa("2024006", "Norman Supratman", "Pemrograman", 95),
        NilaiMahasiswa("2024007", "Suprihatin", "Pemrograman", 74),
        NilaiMahasiswa("2024008", "Aditya Adit", "Pemrograman", 83),
        NilaiMahasiswa("2024009", "Naufal Merdeka", "Pemrograman", 70),
        NilaiMahasiswa("2024010", "Makmur Santoso", "Pemrograman", 72)
    )

    while (true) { // Loop
        println("\nKetik Angka (0 - 8)")
        println("0. Keluar")
        println("1. Tampilkan Semua Data")
        println("2. Tampilkan Data Lulus")
        println("3. Tampilkan Data Tidak Lulus")
        println("4. Tampilkan Statistik (Rata2, Tertinggi, Terendah)")
        println("5. Tampilkan Data Sortir (ASC/DESC)")
        println("6. Tampilkan Data Berdasarkan Grade")
        println("7. Tampilkan Jumlah Data Berdasarkan Grade")
        println("8. Tampilkan Data Pencarian")
        print("> ")
        var input = readln() // readln akan membaca string kemudian dicocokkan dengan persyaratan (if)

        if (input == "0") { // === KELUAR ===
            println("Terima Kasih Telah Menggunakan Sistem!")
            break
        } else if (input == "1") { // === TAMPILKAN SEMUA DATA ===

            println("\n ===== DATA NILAI MAHASISWA =====\n")
            format(daftarNilaiMahasiswa)

        } else if (input == "2") { // === FILTER MAHASISWA YANG LULUS ===

            println("\n ===== DATA MAHASISWA LULUS (Nilai >= 70) =====\n")
            val lulus = daftarNilaiMahasiswa.filter {it.nilai >= 70}
            // it merupakan nama variabel otomatis yang diberikan Kotlin pada lambda
            // jika lambda hanya memiliki satu parameter
            // contoh lambda { parameter -> kode }
            // dalam kasus ini it merepresentasikan satu objek NilaiMahasiswa dari daftarNilaiMahasiswa

            format(lulus) 
            // contoh lambda 2 parameter ada di fungsi format
            // yaitu pada forEachIndexed { index, mhs -> }

            println("\n ===== MAHASISWA LULUS =====\n")
            val descGrade = lulus.sortedByDescending { it.nilai }
            descGrade.forEachIndexed { index, list ->
                println("${index+1}. ${list.nama} - ${list.nilai} (${getGrade(list.nilai)})")
            }

        } else if (input == "3") { // === FILTER MAHASISWA YANG TIDAK LULUS ===

            println("\n ===== DATA MAHASISWA TIDAK LULUS (Nilai < 70) =====\n")
            val tidaklulus = daftarNilaiMahasiswa.filter {it.nilai < 70}
            format(tidaklulus)

            println("\n ===== MAHASISWA TIDAK LULUS =====\n")
            val descGrade = tidaklulus.sortedByDescending { it.nilai }
            descGrade.forEachIndexed { index, list ->
                println("${index+1}. ${list.nama} - ${list.nilai} (${getGrade(list.nilai)})")
            }
            

        } else if (input == "4") { // === STATISTIK ===

            println("\n===== STATISTIK =====")
            println("Total Mahasiswa : ${daftarNilaiMahasiswa.size}")

            // === RATA-RATA NILAI KESELURUHAN
            val average = daftarNilaiMahasiswa.map { it.nilai }.average()
            println("Rata-rata Nilai : $average")
            
            // === NILAI TERTINGGI ===
            val tertinggi = daftarNilaiMahasiswa.maxByOrNull { it.nilai }
            println("Nilai Tertinggi : ${tertinggi?.nilai} (${tertinggi?.nama})")

            // === NILAI TERENDAH ===
            val terendah = daftarNilaiMahasiswa.minByOrNull { it.nilai }
            println("Nilai Terendah  : ${terendah?.nilai} (${terendah?.nama})")

        } else if (input == "5") { // === URUTKAN BERDASARKAN NILAI (ASC/DESC) ===
            
            val sortAsc = daftarNilaiMahasiswa.sortedBy { it.nilai }
            println("\n ===== SORT ASC =====\n")
            format(sortAsc)

            val sortDesc = daftarNilaiMahasiswa.sortedByDescending { it.nilai }
            println("\n ===== SORT DESC =====\n")
            format(sortDesc)

        } else if (input == "6") { // === KELOMPOKKAN BERDASARKAN GRADE ===

            val groupGrade = daftarNilaiMahasiswa.groupBy { getGrade(it.nilai) } 
            // Variabel ini menyimpan data mahasiswa berdasarkan grade dengan menggunakan fungsi groupBy()
            // Jadi groupBy akan menghasilkan struktur data Map, yakni
            // Key yang berupa Grade yaitu A, B, C, D
            // Setiap Key akan memiliki nilai berupa objek dari list misal "A" = [Ani Wijaya, Norman Supratman]

            groupGrade.toSortedMap().forEach { (grade, mhsList) -> // digunakan toSortedMap() sehingga diawali dengan grade A
                println("\n===== GRADE $grade =====")              // tanpa itu akan ditampilkan nilai awal yang muncul yaitu B
                mhsList.forEachIndexed {index, mhs ->
                    println("${index+1} ${mhs.nama} - ${mhs.nilai}")
                }
            }

        } else if (input == "7") { // === HITUNG JUMLAH MAHASISWA PER GRADE ===

            val groupGrade = daftarNilaiMahasiswa.groupBy { getGrade(it.nilai) }
            println("\n===== JUMLAH PER GRADE =====")
            groupGrade.toSortedMap().forEach { (grade, list) ->  // digunakan toSortedMap() sehingga diawali dengan grade A
                println("Grade $grade : ${list.size} mahasiswa") // tanpa itu akan ditampilkan nilai awal yang muncul yaitu B
            }

        } else if (input == "8") { // === CARI MAHASISWA BERDASARKAN NAMA ===
            
            println("\n===== CARI NAMA (CONTAINS) =====")
            print("Masukkan Nama : ")
            val cari = readln().lowercase() // Membaca string dan akan terbaca lowercase

            val hasil = daftarNilaiMahasiswa.filter { // Mencari string yang memiliki kesamaan character
                it.nama.lowercase().contains(cari)    // String akan dibaca lowercase
            }

            if (hasil.isEmpty()) { // Data tidak ditemukan
                println("Data Tidak Ditemukan.")
            } else { // Data ditemukan
                println("\n===== HASIL PENCARIAN =====")
                format(hasil)
            }

        } else {
            println("Invalid Input, Sistem Diberhentikan.")
            break
        }
    }
}