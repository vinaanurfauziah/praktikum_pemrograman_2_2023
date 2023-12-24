package biodata;

// Modul 8 (Pertemuan 9) - Latihan 2

import pendidikan.Pendidikan;
import java.awt.event.*;
import javax.swing.*;
import java.util.UUID;
import dao.BiodataDao;

// ActionListener untuk tombol simpan pada BiodataFrame
public class BiodataButtonDeleteActionListener implements ActionListener {
    // Frame Biodata yang akan diatur oleh ActionListener ini
    private BiodataFrame biodataFrame;
    // Objek BiodataDao untuk berinteraksi dengan database
    private BiodataDao biodataDao;

    // Konstruktor untuk inisialisasi ActionListener dengan MemberFrame dan MemberDao
    public BiodataButtonDeleteActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    // Method yang dipanggil ketika tombol hapus ditekan
    @Override
    public void actionPerformed(ActionEvent e) {
        // Menampilkan dialog konfirmasi sebelum menghapus data
        int konfirmasi = JOptionPane.showConfirmDialog(this.biodataFrame,
                "Apakah Anda yakin data akan dihapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

        // Jika pengguna mengkonfirmasi untuk menghapus
        if(konfirmasi == JOptionPane.YES_OPTION) {
            // Membuat objek Biodata baru untuk menyimpan ID biodata yang akan dihapus
            Biodata biodataToDelete = new Biodata();
            biodataToDelete.setId(this.biodataFrame.takeBiodata());

            // Memeriksa apakah ID biodata yang akan dihapus tidak kosong
            if(!biodataToDelete.getId().isEmpty()) {
                // Menghapus biodata dari tampilan dan database
                this.biodataFrame.deleteBiodata();
                biodataDao.delete(biodataToDelete);
            }
            else {
                // Menampilkan peringatan jika pengguna belum memilih baris tabel yang akan dihapus
                this.biodataFrame.showAlert("Mohon untuk memilih baris tabel yang akan dihapus");
            }
        }
        else {
            // Menampilkan pesan bahwa pengguna membatalkan penghapusan data
            JOptionPane.showMessageDialog(this.biodataFrame, "Menghapus Data gagal!");
        }
    }
}