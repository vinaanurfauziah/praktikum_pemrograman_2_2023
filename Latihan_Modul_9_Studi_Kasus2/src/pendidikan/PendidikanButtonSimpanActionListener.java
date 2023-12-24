package pendidikan;

// Modul 8 (Pertemuan 9) - Latihan 2

import java.awt.event.*;
import java.util.UUID;
import javax.swing.*;
import dao.PendidikanDao;

// ActionListener untuk tombol simpan pada Pendidikan
public class PendidikanButtonSimpanActionListener implements ActionListener {
    // Frame Pendidikan yang akan diatur oleh ActionListener ini
    private PendidikanFrame pendidikanFrame;
    // Objek JenisMemberDao untuk berinteraksi dengan database
    private PendidikanDao pendidikanDao;
    
    // Konstruktor untuk inisialisasi ActionListener dengan PendidikanFrame dan PendidikanDao
    public PendidikanButtonSimpanActionListener(PendidikanFrame pendidikanFrame, PendidikanDao pendidikanDao) {
        this.pendidikanFrame = pendidikanFrame;
        this.pendidikanDao = pendidikanDao;
    }    
    
    // Method yang dipanggil ketika tombol simpan ditekan
    @Override
    public void actionPerformed(ActionEvent e) {
        // Mendapatkan nama dari PendidikanFrame
        String nama = this.pendidikanFrame.getNama();
        
        // Validasi data kosong
        if(nama.trim().isEmpty()) {
            this.pendidikanFrame.showAlert("Data Tidak Boleh Kosong!");
        }
        else {
            // Mengonfirmasi sebelum melakukan penyimpanan data
            int konfirmasi = JOptionPane.showConfirmDialog(this.pendidikanFrame, 
                    "Masukkan Data Pendidikan.", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                // Membuat objek Pendidikan dengan ID acak dan nama yang diambil dari PendidikanFrame
                Pendidikan pendidikan = new Pendidikan();
                pendidikan.setId(UUID.randomUUID().toString());
                pendidikan.setNama(nama);

                // Menambahkan Pendidikan ke PendidikanFrame
                this.pendidikanFrame.addPendidikan(pendidikan);
                // Menyimpan JenisMember ke database menggunakan JenisMemberDao
                this.pendidikanDao.insert(pendidikan);
            }
            else {
                JOptionPane.showMessageDialog(this.pendidikanFrame, "Anda tidak memasukan data!");
            }
        }
    }
}