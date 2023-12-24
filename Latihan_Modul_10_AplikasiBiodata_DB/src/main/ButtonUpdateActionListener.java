package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import biodata.Biodata;
import dao.BiodataDao;

public class ButtonUpdateActionListener implements ActionListener {
    private MainFrame mainFrame;
    private BiodataDao biodataDao;

    public ButtonUpdateActionListener(MainFrame mainFrame, BiodataDao biodataDao) {
        this.mainFrame = mainFrame;
        this.biodataDao = biodataDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Mendapatkan indeks baris yang dipilih di tabel
        int selectedRowIndex = mainFrame.getTable().getSelectedRow();

        // Pastikan ada baris yang dipilih
        if (selectedRowIndex == -1) {
            JOptionPane.showMessageDialog(mainFrame, "Pilih baris untuk diupdate.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Mendapatkan biodata dari baris yang dipilih
        Biodata selectedBiodata = mainFrame.getTableModel().getBiodataAt(selectedRowIndex);

        // Mendapatkan data dari input field di GUI
        String nama = mainFrame.getNama();
        String alamat = mainFrame.getAlamat();
        String noHp = mainFrame.getNoHp();
        String jenisKelamin = mainFrame.getJenisKelamin();
        String status = mainFrame.getStatus();

        // Menampilkan pesan konfirmasi
        int option = JOptionPane.showConfirmDialog(
                mainFrame,
                "Anda yakin ingin mengubah data?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            // Menetapkan data ke objek Biodata yang sudah ada
            selectedBiodata.setNama(nama);
            selectedBiodata.setAlamat(alamat);
            selectedBiodata.setNoHp(noHp);
            selectedBiodata.setJenisKelamin(jenisKelamin);
            selectedBiodata.setStatus(status);

            // Melakukan update pada database
            int result = biodataDao.update(selectedBiodata);

            if (result > 0) {
                JOptionPane.showMessageDialog(mainFrame, "Data berhasil diupdate.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                mainFrame.getTableModel().update(selectedRowIndex, selectedBiodata); // Perbarui data langsung pada model
                mainFrame.clearForm(); // Mengosongkan field input setelah update
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Gagal mengupdate data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}