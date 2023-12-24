// Package ini untuk membuat setter dan getter pada pendidikan
package pendidikan;

// Modul 8 (Pertemuan 9) - Latihan 2

// Deklarasi kelas Pendidikan
public class Pendidikan {
    // Atribut id untuk menyimpan identitas pendidikan
    private String id;
    // Atribut nama untuk menyimpan nama pendidikan
    private String nama;
    
    // Method untuk mengatur nilai id
    public void setId(String id) {
        this.id = id;
    }
    
    // Method untuk mendapatkan nilai id
    public String getId() {
        return id;
    }
    
    // Method untuk mengatur nilai nama
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    // Method untuk mendapatkan nilai nama
    public String getNama() {
        return nama;
    }
}  