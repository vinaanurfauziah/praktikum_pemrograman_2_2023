package pendidikan;

// Modul 8 (Pertemuan 9) - Latihan 2

import javax.swing.*;
import javax.swing.*;
import java.util.*;
import dao.PendidikanDao;


public class PendidikanFrame extends JFrame{
    // List pendidikanList untuk menyimpan data pendidikan dari database
    private List<Pendidikan> pendidikanList;
    // JTextField untuk input nama
    private JTextField textFieldNama;
    // PendidikanTableModel untuk mengatur model tabel
    public PendidikanTableModel tableModel;
    // PendidikanDao untuk mengakses data pendidikan dari database
    private PendidikanDao pendidikanDao;
    // JTable untuk membuat sebuah tabel
    javax.swing.JTable table = new JTable();



    // Konstruktor PendidikanFrame, menerima PendidikanDao sebagai parameter
    public PendidikanFrame(PendidikanDao pendidikanDao) {
        this.pendidikanDao = pendidikanDao;
        // Mengambil semua data pendidikan dari database
        this.pendidikanList = pendidikanDao.findAll();
        // Mengatur operasi penutupan frame
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Label untuk input nama
        JLabel labelInput = new JLabel("Nama: ");
        labelInput.setBounds(15, 40, 350, 10);

        // JTextField untuk memasukkan nama
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        // Tombol untuk menyimpan data pendidikan
        JButton button = new JButton("Simpan");
        button.setBounds(15, 100, 100, 40);

        // Tombol untuk mengubah data pendidikan
        JButton buttonUpdate = new JButton("Edit");
        buttonUpdate.setBounds(120, 100, 100, 40);

        // Tombol untuk menghapus data pendidikan
        JButton buttonDelete = new JButton("Hapus");
        buttonDelete.setBounds(225, 100, 100, 40);

        // Tabel untuk menampilkan data pendidikan
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 150, 350, 200);

        // Mengatur model tabel dengan PendidikanTableModel
        tableModel = new PendidikanTableModel(pendidikanList);
        table.setModel(tableModel);

        // Menggunakan actionListener dari tombol simpan
        PendidikanButtonSimpanActionListener actionListener = new PendidikanButtonSimpanActionListener(this, pendidikanDao);
        // Menggunakan actionListener dari tombol hapus
        PendidikanButtonDeleteActionListener deleteActionListener = new PendidikanButtonDeleteActionListener(this, pendidikanDao);
        // Menggunakan actionListener dari tombol update
        PendidikanButtonUpdateActionListener updateActionListener = new PendidikanButtonUpdateActionListener(this, pendidikanDao);
        buttonUpdate.addActionListener(updateActionListener);

        button.addActionListener(actionListener);
        buttonDelete.addActionListener(deleteActionListener);

        // Menambahkan komponen ke frame
        this.add(button);
        this.add(buttonUpdate);
        this.add(buttonDelete);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(scrollableTable);

        // Mengatur ukuran dan layout frame
        this.setSize(400, 500);
        this.setLayout(null);


    }

    // Metode untuk mendapatkan nilai dari input nama
    public String getNama() {
        return textFieldNama.getText();
    }

    // Metode untuk menambahkan data pendidikan ke dalam tabel
    public void addPendidikan(Pendidikan pendidikan) {
        tableModel.add(pendidikan);
        textFieldNama.setText("");
    }

    // Metode untuk mengubah data pendidikan ke dalam tabel
    public String takePendidikan() {
        int[] selection = table.getSelectedRows();
        for(int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }
        if(selection.length > 0) {
            String namaPendidikan = (String) tableModel.getValueAt(selection[0], 1);
            return namaPendidikan;
        }
        else {
            return "";
        }
    }


    // Metode untuk menghapus data pendidikan ke dalam tabel
    public void deletePendidikan() {
        int[] selection = table.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }
        tableModel.remove(selection[0]);
    }

    // Metode untuk membuat pesan peringatan
    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message, "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
}