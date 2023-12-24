// Package ini untuk membuat setter dan getter pada biodata
package biodata;

// Modul 8 (Pertemuan 9) - Latihan 2 

// Mengimpor kelas Pendidikan dari package pendidikan untuk digunakan di dalam kelas ini
import pendidikan.Pendidikan;

// Deklarasi kelas Biodata
public class Biodata {
    // Atribut id untuk menyimpan identitas biodata
    public String id;
    // Atribut nama untuk menyimpan nama biodata
    public String nama;
    // Atribut nomor telepon untuk menyimpan nomor telepon biodata
    public String noTelp;
    // Atribut alamat untuk menyimpan alamat biodata
    public String alamat;
    // Atribut jenis kelamin untuk menyimpan jenis kelamin biodata
    public String jenisKelamin;
    // Atribut jenisMember untuk menyimpan objek JenisMember yang menentukan jenis member
    private Pendidikan pendidikan;

    // Method untuk mendapatkan nilai id
    public String getId() {
        return id;
    }

    // Method untuk mengatur nilai id
    public void setId(String id) {
        this.id = id;
    }

    // Method untuk mendapatkan nilai nama
    public String getNama() {
        return nama;
    }

    // Method untuk mengatur nilai nama
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Method untuk mendapatkan nilai noTelp
    public String getNoTelp() {
        return noTelp;
    }

    // Method untuk mengatur nilai noTelp
    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    // Method untuk mendapatkan nilai alamat
    public String getAlamat() {
        return alamat;
    }

    // Method untuk mengatur nilai alamat
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    // Method untuk mendapatkan nilai jenisKelamin
    public String getJenisKelamin() {
        return jenisKelamin;
    }

    // Method untuk mengatur nilai jenisKelamin
    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    // Method untuk mengatur pendidikan dengan objek Pendidikan yang diberikan
    public Pendidikan getPendidikan() {
        return pendidikan;
    }

    // Method untuk mendapatkan pendidikan dengan objek Pendidikan yang diberikan
    public void setPendidikan(Pendidikan pendidikan) {
        this.pendidikan = pendidikan;
    }
}