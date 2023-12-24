package biodata;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import dao.BiodataDao;
import pendidikan.Pendidikan;

// ActionListener untuk tombol edit pada BiodataFrame
public class BiodataButtonUpdateActionListener implements ActionListener {
    // Frame Biodata yang akan diatur oleh ActionListener ini
    private BiodataFrame biodataFrame;
    // Objek BiodataDao untuk berinteraksi dengan database
    private BiodataDao biodataDao;

    // Konstruktor untuk inisialisasi ActionListener dengan BiodataFrame dan BiodataDao
    public BiodataButtonUpdateActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Mendapatkan nama dari BiodataFrame
        String nama = this.biodataFrame.getNama();
        // Mendapatkan noTelp dari BiodataFrame
        String noTelp = this.biodataFrame.getNoTelp();
        // Mendapatkan alamat dari BiodataFrame
        String alamat = this.biodataFrame.getAlamat();
        // Mendapatkan jenisKelamin dari BiodataFrame
        String jenisKelamin = this.biodataFrame.getJenisKelamin();
        // Jika semua nilai tidak kosong, mendapatkan pendidikan dari objek biodataFrame
        Pendidikan pendidikan = this.biodataFrame.getPendidikan();

        // Mendapatkan data biodata yang akan diperbarui
        String selectedNama = this.biodataFrame.takeBiodata();

        // Memeriksa apakah semua nilai kosong
        if (nama.isEmpty() || noTelp.isEmpty() || alamat.isEmpty() || jenisKelamin.isEmpty()) {
            // Menampilkan peringatan jika ada nilai yang kosong
            this.biodataFrame.showAlert("Data Tidak Boleh Kosong");
        } else if (!noTelp.matches("[0-9]+")) {
            // Menampilkan peringatan jika nomor telepon tidak valid
            this.biodataFrame.showAlert("Nomor telepon hanya angka!");
        } else if (selectedNama.isEmpty()) {
            // Menampilkan peringatan jika tidak ada data yang dipilih untuk diupdate
            this.biodataFrame.showAlert("Pilih data yang akan diupdate");
        } else {
            // Menampilkan konfirmasi untuk memastikan pengguna ingin menyimpan data yang telah diupdate
            int konfirmasi = JOptionPane.showConfirmDialog(this.biodataFrame,
                    "Update data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

            // Jika pengguna mengkonfirmasi untuk menyimpan perubahan
            if (konfirmasi == JOptionPane.YES_OPTION) {
                // Membuat objek biodata dengan data yang baru
                Biodata biodata = new Biodata();
                biodata.setNama(nama);
                biodata.setNoTelp(noTelp);
                biodata.setAlamat(alamat);
                biodata.setJenisKelamin(jenisKelamin);
                biodata.setPendidikan(pendidikan);

                // Update biodata ke objek biodataFrame dan menyimpannya ke dalam database menggunakan biodataDao
                // Perhatikan bahwa kita menggunakan selectedNama sebagai referensi untuk mencari data yang akan diupdate
                this.biodataFrame.updateBiodata(selectedNama, biodata);
                this.biodataDao.update(selectedNama, biodata);
            } else {
                // Menampilkan pesan bahwa pengguna membatalkan operasi update data
                JOptionPane.showMessageDialog(this.biodataFrame, "Anda tidak memasukkan data");
            }
        }
    }

}