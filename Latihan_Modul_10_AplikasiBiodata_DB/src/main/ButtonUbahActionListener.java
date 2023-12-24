package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import biodata.Biodata;

public class ButtonUbahActionListener implements ActionListener {
    private MainFrame mainFrame;

    public ButtonUbahActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Mendapatkan indeks baris yang dipilih di tabel
        int selectedRowIndex = mainFrame.getTable().getSelectedRow();

        // Pastikan ada baris yang dipilih
        if (selectedRowIndex == -1) {
            return;
        }

        // Mendapatkan biodata dari baris yang dipilih
        Biodata selectedBiodata = mainFrame.getTableModel().getBiodataAt(selectedRowIndex);

        // Menetapkan data dari biodata ke field input di GUI
        mainFrame.setBiodataToForm(selectedBiodata);

        mainFrame.setJenisKelamin(selectedBiodata.getJenisKelamin());
    }
}