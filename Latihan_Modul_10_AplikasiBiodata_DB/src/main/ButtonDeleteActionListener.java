package main;

import biodata.BiodataTableModel;
import dao.BiodataDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonDeleteActionListener implements ActionListener{
    private MainFrame mainFrame;
    private BiodataDao biodataDao;
    private BiodataTableModel tableModel;

    public ButtonDeleteActionListener(MainFrame mainFrame, BiodataDao bidoataDao) {
        this.mainFrame = mainFrame;
        this.biodataDao = new BiodataDao();
        this.tableModel = new BiodataTableModel(biodataDao.findAll());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = this.mainFrame.getTable();

        // Mengecek apakah ada baris yang dipilih atau tidak
        if(table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(mainFrame, "Tolong pilih baris yang ingin dihapus!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Mengambil baris yang dipilih
        int row = table.getSelectedRow();

        // Mengatur konfirmasi sebelum menghapus data
        int confirmStatus = JOptionPane.showConfirmDialog(mainFrame, "Apakah kamu yakin ingin menghapus data ini?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if(confirmStatus != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(mainFrame, "Proses Dibatalkan!", "Cancel", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String id = this.mainFrame.getTable().getValueAt(this.mainFrame.getTable().getSelectedRow(), 0).toString();

        // Menampilkan pesan berhasil
        JOptionPane.showMessageDialog(mainFrame, "Proses Berhasil!" + id, "Success", JOptionPane.INFORMATION_MESSAGE);

        this.mainFrame.deleteBiodata();
        this.biodataDao.delete(id);
        this.mainFrame.clearForm();

    }
}