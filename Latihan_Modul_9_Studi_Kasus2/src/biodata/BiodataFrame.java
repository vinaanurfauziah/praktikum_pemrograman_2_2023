package biodata;

// Modul 8 (Pertemuan 9) - Latihan 2

import pendidikan.Pendidikan;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import dao.BiodataDao;
import dao.PendidikanDao;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

// Frame untuk menangani biodata menggunakan GUI
public class        BiodataFrame extends JFrame {
    // List pendidikanList untuk menyimpan data pendidikan dari database
    private List<Pendidikan> pendidikanList;
    // List biodataList untuk menyimpan biodata dari database
    private List<Biodata> biodataList;
    // JTextField untuk input nama
    private JTextField textFieldNama;
    // JTextField untuk input noTelp
    private JTextField textFieldNoTelp;
    // JTextArea untuk input alamat
    private JTextArea textAreaAlamat;
    // JRadioButton untuk jenis kelamin Laki-laki
    private JRadioButton radioButtonL;
    // JRadioButton untuk jenis kelamin Perempuan
    private JRadioButton radioButtonP;
    // JTable untuk menampilkan table
    javax.swing.JTable table = new JTable();
    // BiodataTableModel untuk mengatur model tabel
    private BiodataTableModel tableModel;
    // JComboBox untuk mengatur pendidikan
    private JComboBox<String> comboJenis;
    // BiodataDao untuk mengakses biodata dari database
    private BiodataDao biodataDao;
    // PendidikanDao untuk mengakses data pendidikan dari database
    private PendidikanDao pendidikanDao;

    // Konstruktor untuk inisialisasi BiodataFrame dengan BiodataDao dan PendidikanDao
    public BiodataFrame(BiodataDao biodataDao, PendidikanDao pendidikanDao) {
        // Mengatur operasi penutupan frame
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.biodataDao = biodataDao;
        this.pendidikanDao = pendidikanDao;

        // Mengambil daftar biodata dan pendidikan dari database
        this.biodataList = biodataDao.findAll();
        this.pendidikanList = pendidikanDao.findAll();

        // Membuat label untuk judul Form
        JLabel label = new JLabel("Form Biodata");
        label.setBounds(60, 5, 350, 30);

        // Label untuk input nama
        JLabel labelInputNama = new JLabel("Nama: ");
        labelInputNama.setBounds(15, 40, 350, 10);

        // JTextField untuk memasukkan nama
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        // Label untuk input noTelp
        JLabel labelInputNoTelp = new JLabel("Nomor Telepon: ");
        labelInputNoTelp.setBounds(15, 100, 350, 15);

        // JTextField untuk memasukkan noTelp
        textFieldNoTelp = new JTextField();
        textFieldNoTelp.setBounds(15, 120, 350, 30);

        // Label untuk input alamat
        JLabel labelInputAlamat = new JLabel("Alamat: ");
        labelInputAlamat.setBounds(15, 160, 350, 10);

        // JTextArea untuk memasukkan alamat
        textAreaAlamat = new JTextArea();
        textAreaAlamat.setBounds(15, 180, 350, 60);

        // Label untuk input jenisKelamin
        JLabel labelJenisKelamin = new JLabel("Jenis Kelamin: ");
        labelJenisKelamin.setBounds(15, 250, 350, 20);

        // JRadioButton untuk memilih jenisKelamin "Laki-laki"
        radioButtonL = new JRadioButton("Laki-Laki", true);
        radioButtonL.setBounds(15, 270, 350, 30);

        // JRadioButton untuk memilih jenisKelamin "Laki-laki"
        radioButtonP = new JRadioButton("Perempuan");
        radioButtonP.setBounds(15, 290, 350, 30);

        // Membuat label dan combo box untuk Pendidikan
        JLabel labelPendidikan = new JLabel("Pendidikan: ");
        labelPendidikan.setBounds(15, 330, 350, 10);

        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 350, 150, 30);

        // Tombol untuk menyimpan biodata
        JButton button = new JButton("Simpan");
        button.setBounds(15, 390, 100, 40);

        // Tombol untuk mengubah biodata
        JButton buttonUpdate = new JButton("Edit");
        buttonUpdate.setBounds(120, 390, 100, 40);

        // Tombol untuk menghapus biodata
        JButton buttonDelete = new JButton("Hapus");
        buttonDelete.setBounds(225, 390, 100, 40);

        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 440, 500, 200);

        // Mengatur model tabel dengan BiodataTableModel
        tableModel = new BiodataTableModel(biodataList);
        table.setModel(tableModel);

        // Membuat grup untuk button radio agar hanya bisa dipilih satu
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButtonL);
        bg.add(radioButtonP);

        // Menggunakan actionListener dari tombol simpan
        BiodataButtonSimpanActionListener actionListener = new BiodataButtonSimpanActionListener(this, biodataDao);

        // Menggunakan actionListener dari tombol hapus
        BiodataButtonDeleteActionListener deleteActionListener = new BiodataButtonDeleteActionListener(this, biodataDao);

        // Menambahkan actionListener dari tombol edit
        buttonUpdate.addActionListener(new BiodataButtonUpdateActionListener(this, biodataDao));

        button.addActionListener(actionListener);
        buttonDelete.addActionListener(deleteActionListener);

        // Menambahkan komponen-komponen ke frame
        this.add(button);
        this.add(buttonUpdate);
        this.add(buttonDelete);
        this.add(textFieldNama);
        this.add(textFieldNoTelp);
        this.add(textAreaAlamat);
        this.add(radioButtonL);
        this.add(radioButtonP);
        this.add(labelInputNama);
        this.add(labelInputNoTelp);
        this.add(labelInputAlamat);
        this.add(labelJenisKelamin);
        this.add(labelPendidikan);
        this.add(comboJenis);
        this.add(scrollableTable);

        // Mengatur ukuran dan layout frame
        this.setSize(550, 650);
        this.setLayout(null);

        // Menambahkan ListSelectionListener ke JTable
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Panggil metode untuk menangani perubahan seleksi
                handleTableSelection();
            }
        });
    }

    // Mengisi combo box Pendidikan dengan data dari database
    public void populateComboJenis() {
        this.pendidikanList = this.pendidikanDao.findAll();
        comboJenis.removeAllItems();
        for (Pendidikan pendidikan : this.pendidikanList) {
            comboJenis.addItem(pendidikan.getNama());
        }
    }

    // Mendapatkan nilai dari input field Nama
    public String getNama() {
        return textFieldNama.getText();
    }

    // Mendapatkan nilai dari input field Nomor Telepon
    public String getNoTelp() {
        return textFieldNoTelp.getText();
    }

    // Mendapatkan nilai dari input field Alamat
    public String getAlamat() {
        return textAreaAlamat.getText();
    }

    // Mendapatkan nilai dari input field Jenis Kelamin
    public String getJenisKelamin() {
        String jenisKelamin = "";

        if (radioButtonL.isSelected()) {
            jenisKelamin = radioButtonL.getText();
        }

        if (radioButtonP.isSelected()) {
            jenisKelamin = radioButtonP.getText();
        }
        return jenisKelamin;
    }

    // Mendapatkan pendidikan yang dipilih dari combo box
    public Pendidikan getPendidikan() {
        return pendidikanList.get(comboJenis.getSelectedIndex());
    }

    // Menambahkan isi biodata ke dalam tabel
    public void addBiodata(Biodata biodata) {
        tableModel.add(biodata);
        textFieldNama.setText("");
        textFieldNoTelp.setText("");
        textAreaAlamat.setText("");
    }

    // Metode untuk mengubah data biodata ke dalam tabel
    public String takeBiodata() {
        int[] selection = table.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }
        if (selection.length > 0) {
            String namaPendidikan = (String) tableModel.getValueAt(selection[0], 5);
            return namaPendidikan;
        } else {
            return "";
        }
    }

    // Metode untuk menghapus data biodata ke dalam tabel
    public void deleteBiodata() {
        int[] selection = table.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }
        for (int i = selection.length - 1; i >= 0; i--) {
            tableModel.remove(selection[i]);
        }
    }

    // Metode untuk membuat pesan peringatan
    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message, "Peringatan", JOptionPane.WARNING_MESSAGE);
    }

    // Metode untuk menangani perubahan seleksi pada JTable
    private void handleTableSelection() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Ambil data dari baris terpilih dan tampilkan di JTextField
            String selectedNama = (String) tableModel.getValueAt(selectedRow, 0);
            String selectedPendidikan = (String) tableModel.getValueAt(selectedRow, 1);
            String selectedNoTelp = (String) tableModel.getValueAt(selectedRow, 2);
            String selectedAlamat = (String) tableModel.getValueAt(selectedRow, 3);
            String selectedJenisKelamin = (String) tableModel.getValueAt(selectedRow, 4);

            textFieldNama.setText(selectedNama);
            textFieldNoTelp.setText(selectedNoTelp);
            textAreaAlamat.setText(selectedAlamat);

            // Sesuaikan dengan jenis kelamin yang terpilih
            if (selectedJenisKelamin.equals("Laki-Laki")) {
                radioButtonL.setSelected(true);
            } else {
                radioButtonP.setSelected(true);
            }

            // Sesuaikan dengan pendidikan yang terpilih
            for (int i = 0; i < comboJenis.getItemCount(); i++) {
                if (comboJenis.getItemAt(i).equals(selectedPendidikan)) {
                    comboJenis.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    // Metode untuk mengupdate data biodata ke dalam tabel
    public void updateBiodata(String selectedNama, Biodata biodata) {
        // Mendapatkan baris terpilih dari model tabel
        int selectedRow = getSelectedRow(selectedNama);

        // Pastikan baris terpilih valid
        if (selectedRow >= 0) {
            // Menggantikan nilai pada baris yang sesuai dengan data yang baru
            tableModel.setValueAt(biodata.getNama(), selectedRow, 0);
            tableModel.setValueAt(biodata.getPendidikan().getNama(), selectedRow, 1);
            tableModel.setValueAt(biodata.getNoTelp(), selectedRow, 2);
            tableModel.setValueAt(biodata.getAlamat(), selectedRow, 3);
            tableModel.setValueAt(biodata.getJenisKelamin(), selectedRow, 4);
        }
    }

    // Metode untuk mendapatkan indeks baris berdasarkan nama
    private int getSelectedRow(String selectedNama) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(selectedNama)) {
                return i;
            }
        }
        return -1; // Mengembalikan -1 jika tidak ada baris yang sesuai
    }

}