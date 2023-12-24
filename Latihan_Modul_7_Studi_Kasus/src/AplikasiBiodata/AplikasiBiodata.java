// Studi Kasus Modul ke-7
// dapat menampung data dalam JTable, belum terhubung ke database

package AplikasiBiodata;

// kumpulan library yang digunakan
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;


public class AplikasiBiodata extends JFrame { // kelas BiodataApp yang merupakan turunan dari JFrame
    private boolean checkBoxSelected; // membuat variabel checkBoxSelected dengan tipe data boolean
    
    private static final long serialVersionUID = 1L; // membuat variabel serialVersionUID dengan tipe data long

    // Method untuk memeriksa apakah sebuah string adalah angka.
    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
    
    public AplikasiBiodata() { // membuat fungsi BiodataApp
        this.checkBoxSelected = false; // mengatur nilai checkBoxSelected menjadi false
        
        // Mengatur operasi default saat menutup jendela.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat label dengan nama Biodata App
        JLabel labelJudul = new JLabel("Biodata App");
        labelJudul.setBounds(190, 5, 365, 50);

        // Membuat label dengan nama Nama
        JLabel labelNama = new JLabel("Nama: ");
        labelNama.setBounds(15, 40, 365, 10);

        // Membuat textfield
        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 400, 30);

        JLabel labelTelepon = new JLabel("Nomor HP: ");
        labelTelepon.setBounds(15, 100, 365, 10);

        JTextField textFieldTelepon = new JTextField();
        textFieldTelepon.setBounds(15, 120, 400, 30);

        JLabel labelRadio = new JLabel("Jenis Kelamin:");
        labelRadio.setBounds(15, 160, 350, 10);

        JRadioButton radioButton1 = new JRadioButton("Laki-Laki", true);
        radioButton1.setBounds(15, 180, 400, 30);

        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(15, 205, 400, 30);

        //membuat group button dari radiobutton 1 dan 2
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);
        
        JLabel labelAlamat = new JLabel("Alamat: ");
        labelAlamat.setBounds(15, 230, 350, 30);
        
        JTextArea textFieldAlamat = new JTextArea();
        textFieldAlamat.setBounds(15, 260, 400, 70);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 340, 80, 30);
        
        JButton editButton = new JButton("Edit");
        editButton.setBounds(105, 340, 80, 30);

        JButton deleteButton = new JButton("Hapus");
        deleteButton.setBounds(195, 340, 80, 30);

        JButton saveToFileButton = new JButton("Simpan ke File");
        saveToFileButton.setBounds(285, 340, 120, 30);

        // Membuat objek table
        JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 390, 400, 200);

        // Membuat objek table model
        TableModel tableModel = new TableModel();
        table.setModel(tableModel);

        // Mengatur aksi yang dilakukan ketika tombol "Simpan" ditekan.
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(AplikasiBiodata.this,
                        "Apakah anda yakin ingin menyimpan data?", "konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    String jenisKelamin = "";

                    // jika radioButton1 dipilih maka ambil text dari radioButton1
                    if (radioButton1.isSelected()) {
                        jenisKelamin = radioButton1.getText();
                        radioButton1.setSelected(false);
                    } // jika radioButton2 dipilih maka ambil text dari radioButton2
                    else
                    if (radioButton2.isSelected()) {
                        jenisKelamin = radioButton2.getText();
                        radioButton2.setSelected(false);
                    }

                    // Mengambil nilai dari textfield
                    String nama = textFieldNama.getText();
                    String telepon = textFieldTelepon.getText();
                    String alamat = textFieldAlamat.getText();

                    if (nama.isEmpty()) { // jika nama kosong maka tampilkan pesan dialog
                        JOptionPane.showMessageDialog(AplikasiBiodata.this, "Nama tidak boleh kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    } else if (telepon.isEmpty()) {
                        JOptionPane.showMessageDialog(AplikasiBiodata.this, "Nomor hp tidak boleh kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    } else if (alamat.isEmpty()) {
                        JOptionPane.showMessageDialog(AplikasiBiodata.this, "Alamat tidak boleh kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    } else if (!isNumeric(telepon)) {
                        JOptionPane.showMessageDialog(null, "Nomor HP harus berisi angka.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    } else {
                        // Menambahkan data ke table model
                        tableModel.add(new ArrayList<>(Arrays.asList(nama, telepon, jenisKelamin, alamat)));
                        textFieldNama.setText("");
                        textFieldTelepon.setText("");
                        textFieldAlamat.setText("");
                        JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        // Mengatur aksi yang dilakukan ketika tombol "Edit" ditekan.
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String nama = (String) tableModel.getValueAt(selectedRow, 0);
                    String nomorHp = (String) tableModel.getValueAt(selectedRow, 1);
                    String jenisKelamin = (String) tableModel.getValueAt(selectedRow, 2);
                    String alamat = (String) tableModel.getValueAt(selectedRow, 3);

                    textFieldNama.setText(nama);
                    textFieldTelepon.setText(nomorHp);
                    if (jenisKelamin.equals("Laki-Laki")) {
                        radioButton1.setSelected(true);
                    } else {
                        radioButton2.setSelected(true);
                    }
                    textFieldAlamat.setText(alamat);

                    tableModel.remove(selectedRow);

                    JOptionPane.showMessageDialog(null, "Data berhasil disalin ke formulir. Klik Simpan jika sudah selesai mengedit.", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih data yang ingin Anda edit terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Mengatur aksi yang dilakukan ketika tombol "Hapus" ditekan.
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    tableModel.remove(selectedRow);
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih data yang ingin Anda hapus terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Mengatur aksi yang dilakukan ketika tombol "Simpan ke File" ditekan.
        saveToFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String filePath = "D:\\01. KULIAH\\sms5\\pp2\\PraktikumPemrograman2\\src\\pertemuan7\\database.txt"; // mengatur path file
                    File file = new File(filePath); // membuat objek file
                    FileWriter writer = new FileWriter(file); // membuat objek file writer

                    // Menulis data ke file
                    for (int i = 0; i < tableModel.getRowCount(); i++) { // mengulang sebanyak jumlah baris pada table model
                        for (int j = 0; j < tableModel.getColumnCount(); j++) { // mengulang sebanyak jumlah kolom pada table model
                            writer.write(tableModel.getValueAt(i, j).toString()); // menulis data ke file
                            writer.write("\t"); // menulis tab ke file
                        }
                        writer.write("\n"); // menulis newline ke file
                    }

                    writer.close(); // menutup file writer
                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan ke file", "Info", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan ke file.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        // Mengatur aksi yang dilakukan saat jendela ditutup.
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin keluar?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.add(labelJudul);
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelTelepon);
        this.add(textFieldTelepon);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(labelAlamat);
        this.add(textFieldAlamat);
        this.add(button);
        this.add(scrollableTable);
        this.add(editButton);
        this.add(deleteButton);
        this.add(saveToFileButton);

        this.setSize(500, 700);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AplikasiBiodata h = new AplikasiBiodata();
                h.setVisible(true);
            }
        });
    }
}
