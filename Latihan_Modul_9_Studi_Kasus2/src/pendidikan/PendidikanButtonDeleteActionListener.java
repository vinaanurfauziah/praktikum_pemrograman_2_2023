package pendidikan;

// Modul 8 (Pertemuan 9) - Latihan 2

import java.awt.event.*;
import javax.swing.*;
import java.util.UUID;
import dao.PendidikanDao;

// ActionListener untuk tombol simpan pada Pendidikan
public class PendidikanButtonDeleteActionListener implements ActionListener {
    // Frame Pendidikan yang akan diatur oleh ActionListener ini
    private PendidikanFrame pendidikanFrame;
    // Objek JenisMemberDao untuk berinteraksi dengan database
    private PendidikanDao pendidikanDao;
    
    // Konstruktor untuk inisialisasi ActionListener dengan PendidikanFrame dan PendidikanDao
    public PendidikanButtonDeleteActionListener(PendidikanFrame pendidikanFrame, PendidikanDao pendidikanDao) {
        this.pendidikanFrame = pendidikanFrame;
        this.pendidikanDao = pendidikanDao;
    }    
    
    // Method yang dipanggil ketika tombol ubah dan hapus ditekan
    @Override
    public void actionPerformed(ActionEvent e) {
        int konfirmasi = JOptionPane.showConfirmDialog(this.pendidikanFrame, 
                "Apakah Anda yakin Data Pendidikan akan dihapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if(konfirmasi == JOptionPane.YES_OPTION) {
            Pendidikan namaP = new Pendidikan();
            namaP.setId(this.pendidikanFrame.takePendidikan());
            
            if(namaP.getId() != "") {
                this.pendidikanFrame.deletePendidikan();
                pendidikanDao.delete(namaP);
            }
            else {
               this.pendidikanFrame.showAlert("Mohon untuk memilih baris tabel yang akan dihapus");
            }
        }
        else {
            JOptionPane.showMessageDialog(this.pendidikanFrame, "Menghapus Data gagal!");
        }
    }
}