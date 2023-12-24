// Modul ke-6 JOptionPane, JScrollPane, JTable
package latihan_modul_6;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AplikasiBiodata extends JFrame { // kelas biodataApp yang merupakan turunan dari JFrame
    private DefaultTableModel tableModel; // membuat objek tableModel
    private JTable jTable; // membuat objek jTable
    private int selectedRow = -1; // membuat variabel selectedRow dengan nilai -1

    public AplikasiBiodata() { // membuat fungsi biodataApp
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // operasi penutupan frame

        JLabel labelInput = new JLabel("Nama : "); // membuat label dengan nama Nama
        labelInput.setBounds(15, 40, 350, 10); // mengatur posisi label

        JTextField textField = new JTextField(); // membuat textfield
        textField.setBounds(15, 60, 350, 30); // mengatur posisi textfield

        JLabel labelInput1 = new JLabel("Nomor HP : "); // membuat label dengan nama Nomor HP
        labelInput1.setBounds(15, 95, 350, 10); // mengatur posisi label

        JTextField textField1 = new JTextField(); // membuat textfield
        textField1.setBounds(15, 110, 350, 30);

        JLabel labelRadio = new JLabel("Jenis Kelamin");
        labelRadio.setBounds(15, 130, 350, 10);

        JRadioButton radioButton1 = new JRadioButton("Laki-Laki", true); // membuat radiobutton dengan nama Laki-Laki
        radioButton1.setBounds(15, 145, 350, 30);

        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(15, 175, 350, 30);

        JCheckBox checkBox = new JCheckBox("Warga Negara Asing"); // membuat checkbox dengan nama Warga Negara Asing
        checkBox.setBounds(15, 205, 350, 30);

        ButtonGroup bg = new ButtonGroup(); // membuat buttongroup
        bg.add(radioButton1); // menambahkan radiobutton1 ke buttongroup
        bg.add(radioButton2); // menambahkan radiobutton2 ke buttongroup

        JButton buttonTambah = new JButton("Simpan");
        buttonTambah.setBounds(15, 240, 100, 40);

        JButton buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(125, 240, 100, 40);

        JButton buttonHapus = new JButton("Hapus");
        buttonHapus.setBounds(235, 240, 100, 40);

        // membuat table model dengan kolom
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nama");
        tableModel.addColumn("Nomor HP");
        tableModel.addColumn("Jenis Kelamin");
        tableModel.addColumn("WNA");

        jTable = new JTable(tableModel); // membuat objek jTable
        JScrollPane jScrollPane = new JScrollPane(jTable); // membuat objek jScrollPane
        jScrollPane.setBounds(15, 290, 350, 200);

        buttonTambah.addActionListener(new ActionListener() { // menambahkan action listener pada buttonTambah
            public void actionPerformed(ActionEvent e) {
                String jenisKelamin = "";
                if (radioButton1.isSelected()) { // jika radiobutton1 dipilih maka ambil text dari radiobutton1
                    jenisKelamin = radioButton1.getText();
                } else if (radioButton2.isSelected()) { // jika radiobutton2 dipilih maka ambil text dari radiobutton2
                    jenisKelamin = radioButton2.getText();
                }

                String nama = textField.getText(); // mengambil text dari textfield
                String noHp = textField1.getText();// mengambil text dari textfield
                String wnaStatus = checkBox.isSelected() ? "Ya" : "Bukan"; // jika checkbox dipilih maka ambil text Ya, jika tidak maka ambil text Bukan

                // menambahkan data ke table model
                tableModel.addRow(new Object[]{nama, noHp, jenisKelamin, wnaStatus});

                textField.setText(""); // mengatur textfield menjadi kosong
                textField1.setText("");
                radioButton1.setSelected(true);// mengatur radiobutton1 menjadi dipilih
                checkBox.setSelected(false);// mengatur checkbox menjadi tidak dipilih


               JOptionPane.showMessageDialog(null, "Data telah ditambahkan.", "Info", JOptionPane.INFORMATION_MESSAGE); // menampilkan pesan dialog
            }
        });

        buttonUpdate.addActionListener(new ActionListener() { // menambahkan action listener pada buttonUpdate
            public void actionPerformed(ActionEvent e) {
                if (selectedRow >= 0) {
                    String jenisKelamin = "";
                    if (radioButton1.isSelected()) {
                        jenisKelamin = radioButton1.getText();
                    } else if (radioButton2.isSelected()) {
                        jenisKelamin = radioButton2.getText();
                    }

                    String nama = textField.getText();
                    String noHp = textField1.getText();
                    String wnaStatus = checkBox.isSelected() ? "Ya" : "Bukan";

                    // mengubah data pada table model
                    tableModel.setValueAt(nama, selectedRow, 0);
                    tableModel.setValueAt(noHp, selectedRow, 1);
                    tableModel.setValueAt(jenisKelamin, selectedRow, 2);
                    tableModel.setValueAt(wnaStatus, selectedRow, 3);

                    textField.setText("");
                    textField1.setText("");
                    radioButton1.setSelected(true);
                    checkBox.setSelected(false);
                    selectedRow = -1; // mereseset selected row
                    
                    JOptionPane.showMessageDialog(null, "Data telah diperbarui.", "Info", JOptionPane.INFORMATION_MESSAGE); // menampilkan pesan dialog
                }
            }
        });

        buttonHapus.addActionListener(new ActionListener() { // menambahkan action listener pada buttonHapus
            public void actionPerformed(ActionEvent e) {
                if (selectedRow >= 0) { // jika selectedRow lebih dari sama dengan 0
                    int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus Data", JOptionPane.YES_NO_OPTION); // menampilkan pesan dialog konfirmasi
                    
                    if (confirm == JOptionPane.YES_OPTION) { // jika user memilih yes
                        tableModel.removeRow(selectedRow); // menghapus baris pada table model
                        selectedRow = -1; //mereset selected row
                        
                        JOptionPane.showMessageDialog(null, "Data telah dihapus.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        jTable.getSelectionModel().addListSelectionListener(e -> { // menambahkan action listener pada jTable
            selectedRow = jTable.getSelectedRow(); // mengambil baris yang dipilih
            if (selectedRow >= 0) { // jika selectedRow lebih dari sama dengan 0
                textField.setText(tableModel.getValueAt(selectedRow, 0).toString()); // mengatur textfield dengan text dari table model
                textField1.setText(tableModel.getValueAt(selectedRow, 1).toString()); // mengatur textfield dengan text dari table model
                String jenisKelamin = tableModel.getValueAt(selectedRow, 2).toString(); // mengatur textfield dengan text dari table model
                if (jenisKelamin.equals("Laki-Laki")) { // jika jenisKelamin sama dengan Laki-Laki
                    radioButton1.setSelected(true); // mengatur radiobutton1 menjadi dipilih
                } else if (jenisKelamin.equals("Perempuan")) { // jika jenisKelamin sama dengan Perempuan
                    radioButton2.setSelected(true); // mengatur radiobutton2 menjadi dipilih
                }
                String wnaStatus = tableModel.getValueAt(selectedRow, 3).toString();
                checkBox.setSelected(wnaStatus.equals("Ya")); // mengatur checkbox menjadi dipilih jika wnaStatus sama dengan Ya
            }
        });

        // menambahkan komponen ke frame
        this.add(buttonTambah);
        this.add(buttonUpdate);
        this.add(buttonHapus);
        this.add(textField);
        this.add(textField1);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(checkBox);
        this.add(labelInput);
        this.add(labelInput1);
        this.add(jScrollPane);

        this.setSize(400, 600);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AplikasiBiodata h = new AplikasiBiodata();
                h.setVisible(true);
            }
        });
    }
}
