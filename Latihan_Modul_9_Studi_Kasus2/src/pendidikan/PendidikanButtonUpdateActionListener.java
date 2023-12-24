package pendidikan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import javax.swing.JOptionPane;
import dao.PendidikanDao;

// ActionListener untuk tombol update pada Pendidikan
public class PendidikanButtonUpdateActionListener implements ActionListener {
    // Frame Pendidikan yang akan diatur oleh ActionListener ini
    private PendidikanFrame pendidikanFrame;
    // Objek PendidikanDao untuk berinteraksi dengan database
    private PendidikanDao pendidikanDao;

    // Konstruktor untuk inisialisasi ActionListener dengan PendidikanFrame dan PendidikanDao
    public PendidikanButtonUpdateActionListener(PendidikanFrame pendidikanFrame, PendidikanDao pendidikanDao) {
        this.pendidikanFrame = pendidikanFrame;
        this.pendidikanDao = pendidikanDao;
    }

    // Method yang dipanggil ketika tombol update ditekan
    @Override
    public void actionPerformed(ActionEvent e) {
        // Mendapatkan nama dari PendidikanFrame
        String nama = this.pendidikanFrame.getNama();

        // Validasi data kosong
        if (nama.trim().isEmpty()) {
            this.pendidikanFrame.showAlert("Data Tidak Boleh Kosong!");
        } else {
            // Mendapatkan nama pendidikan yang dipilih
            String selectedNama = this.pendidikanFrame.takePendidikan();

            // Validasi pemilihan data
            if (selectedNama.isEmpty()) {
                this.pendidikanFrame.showAlert("Pilih data pendidikan yang akan diupdate!");
            } else {
                // Mengonfirmasi sebelum melakukan update data
                int konfirmasi = JOptionPane.showConfirmDialog(this.pendidikanFrame,
                        "Update Data Pendidikan.", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (konfirmasi == JOptionPane.YES_OPTION) {
                    // Membuat objek Pendidikan dengan ID acak dan nama yang diambil dari PendidikanFrame
                    Pendidikan updatedPendidikan = new Pendidikan();
                    updatedPendidikan.setId(UUID.randomUUID().toString());
                    updatedPendidikan.setNama(nama);

                    // Menghapus data pendidikan lama dari tabel
                    this.pendidikanFrame.deletePendidikan();

                    // Menambahkan data pendidikan yang telah diupdate ke dalam tabel
                    this.pendidikanFrame.addPendidikan(updatedPendidikan);

                    // Menyimpan data pendidikan yang telah diupdate ke database menggunakan PendidikanDao
                    this.pendidikanDao.update(updatedPendidikan);
                } else {
                    JOptionPane.showMessageDialog(this.pendidikanFrame, "Anda tidak memasukkan data!");
                }
            }
        }
    }
}