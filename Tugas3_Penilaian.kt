fun validasiNilai(pesan: String): Double {
    print(pesan) // Pesan dimasukkan manual
    while (true) {
        val input = readln().toDoubleOrNull() // bila input bukan double maka akan jadi null
        if (input == null) { // pesan error
            print("Input harus berupa angka, coba lagi: ")
        } else if (input < 0 || input > 100) { //pesan error 2
            print("Input tidak valid, masukkan nilai antara 0 hingga 100: ")
        } else { // ketika berhasil return nilai
            return input
        }
    }
}

fun main() {
    // IDENTITAS
    println("Nama: Wicaksono Hadidul Mannan")
    println("NIM : F1D02310095\n")

    // VARIABEL (digunakan untuk menyimpan perubahan)
    var loop: String = "1" // -> Untuk looping, lalu digunakan string agar tidak error bila input merupakan character
    var nama: String
    var grade: String
    var keterangan: String
    var status: String

    var uts: Double
    var uas: Double
    var tugas: Double
    var akhir: Double

    while (loop == "1") { // looping awal langsung bekerja
        println("Sistem Penilaian ")
        println("- Ketik 1 / mulai untuk lanjut ")
        println("- Ketik 0 / selesai untuk berhenti ")
        print("> ")
        loop = readln() // perubahan nilai looping
        if (loop == "1" || loop == "lanjut") {
            println("\n==== SISTEM PENILAIAN ====\n")
            print("Masukkan Nama Mahasiswa: ")
            nama = readln()
            uts = validasiNilai("Masukkan Nilai UTS (0-100): ")
            uas = validasiNilai("Masukkan Nilai UAS (0-100): ")
            tugas = validasiNilai("Masukkan Nilai Tugas (0-100): ")
            
            // NILAI (menghitung nilai akhir)
            akhir = (uts * 0.3) + (uas * 0.4) + (tugas * 0.3)

            // grade = when (akhir) {
            //     in 85.0..100.0 -> "A"
            //     in 70.0..84.0  -> "B"
            //     in 60.0..69.0  -> "C"
            //     in 50.0..59.0  -> "D"
            //     in 0.0..49.0   -> "E"
            //     else           -> "invalid"
            // }
            // HASIL PENILAIAN (disini menggunakan double, jadi bisa seperti di bawah atau seperti komen di atas)
            grade = when {                          
                akhir >= 85 && akhir <= 100  -> "A" 
                akhir >= 70 && akhir <= 84   -> "B" 
                akhir >= 60 && akhir <= 69   -> "C" 
                akhir >= 50 && akhir <= 59   -> "D" 
                akhir >= 0 &&  akhir <= 49   -> "E" 
                else                         -> "Invalid"
            }                                       

            keterangan = when (grade) {
                "A"  -> "Sangat Baik"
                "B"  -> "Baik"
                "C"  -> "Cukup"
                "D"  -> "Kurang"
                "E"  -> "Sangat Kurang"
                else -> "Invalid"
            }

            status = when {
                akhir >= 60 && akhir <= 100   -> "LULUS"
                else                          -> "TIDAK LULUS"
            }

            // TAMPILAN HASIL
            println("==== HASIL PENILAIAN ====")
            println("Nama           : $nama")
            println("Nilai UTS      : $uts")
            println("Nilai UAS      : $uas")
            println("Nilai Tugas    : $tugas")
            println("-------------------------")
            println("Nilai Akhir    : $akhir")
            println("Grade          : $grade")
            println("Keterangan     : $keterangan")
            println("Status         : $status")

            if (status == "LULUS") {
                println("\nSelamat! Anda dinyatakan $status \n")
            } else {
                println("\nMohon maaf! Anda dinyatakan $status \n")
            }
        } else if (loop == "0" || loop == "selesai") { 
            println("=== SISTEM BERHENTI ===")
        } else { // memberhentikan sistem, ketika salah input
            println("Input Invalid, Sistem Berhenti")
        }
    }
}