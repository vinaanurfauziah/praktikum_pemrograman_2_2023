package biodata;

// Modul 8 (Pertemuan 9) - Latihan 2

import pendidikan.Pendidikan;
import java.awt.event.*;
import javax.swing.*;
import java.util.UUID;
import dao.BiodataDao;

// ActionListener untuk tombol simpan pada BiodataFrame
public class BiodataButtonSimpanActionListener implements ActionListener {
    // Frame Biodata yang akan diatur oleh ActionListener ini
    private BiodataFrame biodataFrame;
    // Objek BiodataDao untuk berinteraksi dengan database
    private BiodataDao biodataDao;

    // Konstruktor untuk inisialisasi ActionListener dengan MemberFrame dan MemberDao
    public BiodataButtonSimpanActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    // Method yang dipanggil ketika tombol simpan ditekan
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

        // Memeriksa apakah semua nilai kosong
        if(nama.isEmpty() || noTelp.isEmpty() || alamat.isEmpty() || jenisKelamin.isEmpty()) {
            // Menampilkan peringatan jika ada nilai yang kosong
            this.biodataFrame.showAlert("Data Tidak Boleh Kosong");
        }
        // Memeriksa apakah nomor telepon hanya terdiri dari angka
        else if(!noTelp.matches("[0-9]+")) {
            // Menampilkan peringatan jika nomor telepon tidak valid
            this.biodataFrame.showAlert("Nomor telepon hanya angka!");
        }
        else {
            // Menampilkan konfirmasi untuk memastikan pengguna ingin menyimpan data
            int konfirmasi = JOptionPane.showConfirmDialog(this.biodataFrame,
                    "Masukkan datanya", "Konfirmasi", JOptionPane.YES_NO_OPTION);

            // Jika pengguna mengkonfirmasi untuk menyimpan
            if(konfirmasi == JOptionPane.YES_OPTION) {
                // Membuat objek biodata dengan mengatur nilai ID, nama, noTelp, alamat, jenisKelamin, dan pendidikan
                Biodata biodata = new Biodata();
                biodata.setId(UUID.randomUUID().toString()); // ID dihasilkan secara acak
                biodata.setNama(nama);
                biodata.setNoTelp(noTelp);
                biodata.setAlamat(alamat);
                biodata.setJenisKelamin(jenisKelamin);
                biodata.setPendidikan(pendidikan);

                // Menambahkan biodata ke objek biodataFrame dan menyimpannya ke dalam database menggunakan biodataDao
                this.biodataFrame.addBiodata(biodata);
                this.biodataDao.insert(biodata);
            }
            else {
                // Menampilkan pesan bahwa pengguna membatalkan operasi menyimpan data
                JOptionPane.showMessageDialog(this.biodataFrame, "Anda tidak memasukkan data");
            }
        }
    }
}