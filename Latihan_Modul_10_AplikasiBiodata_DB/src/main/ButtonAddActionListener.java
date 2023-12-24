package main;

import biodata.Biodata;
import dao.BiodataDao;

import javax.swing.*;
import java.awt.event.*;
import java.util.UUID;

public class ButtonAddActionListener implements ActionListener{
    private MainFrame mainFrame;
    private BiodataDao biodataDao;

    public ButtonAddActionListener(MainFrame mainFrame, BiodataDao bidoataDao) {
        this.mainFrame = mainFrame;
        this.biodataDao = new BiodataDao();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = this.mainFrame.getTable();

        // Mengecek apakah nama sudah diisi atau belum
        if(mainFrame.getNama().equals("")) {
            JOptionPane.showMessageDialog(mainFrame, "Tolong isi nama!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Mengecek apakah no hp sudah diisi atau belum
        if(mainFrame.getNoHp().equals("")) {
            JOptionPane.showMessageDialog(mainFrame, "Tolong isi nomor hp!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Mengatur kondisi untuk jenis kelamin
        String jk = "";
        if(mainFrame.getJenisKelamin() == null ) {
            JOptionPane.showMessageDialog(mainFrame, "Tolong pilih jenis kelamin!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Mengecek apakah alamat sudah diisi atau belum
        if(mainFrame.getAlamat().equals("")) {
            JOptionPane.showMessageDialog(mainFrame, "Tolong isi alamat!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Mengatur konfirmasi sebelum menyimpan data
        int confirmStatus = JOptionPane.showConfirmDialog(mainFrame, "Apakah kamu yakin ingin menyimpan data ini?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if(confirmStatus != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(mainFrame, "Proses Dibatalkan!", "Cancel", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String nama = this.mainFrame.getNama();
        String noHp = this.mainFrame.getNoHp();
        String jenisKelamin = this.mainFrame.getJenisKelamin();
        String status = this.mainFrame.getStatus();
        String alamat = this.mainFrame.getAlamat();
        Biodata biodata = new Biodata();
        biodata.setId(UUID.randomUUID().toString());
        biodata.setNama(nama);
        biodata.setNoHp(noHp);
        biodata.setJenisKelamin(jenisKelamin);
        biodata.setStatus(status);
        biodata.setAlamat(alamat);

        this.mainFrame.addBiodata(biodata);
        this.biodataDao.insert(biodata);
        this.mainFrame.clearForm();

    }
}