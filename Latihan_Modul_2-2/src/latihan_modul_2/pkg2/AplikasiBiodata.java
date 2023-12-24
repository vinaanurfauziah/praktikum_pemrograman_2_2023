// Latihan 3 Modul ke-2 Komponen Swing 2
// Melengkapi Aplikasi Biodata pada modul 2 biar ada radio button laki-laki&perempuan dan juga checkbox

package latihan_modul_2.pkg2;

import java.awt.event.*;
import javax.swing.*;

public class AplikasiBiodata extends JFrame {
    //Konstruktor untuk kelas HelloRadioButton.
    public AplikasiBiodata(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Membuat label "Nama" dan mengatur posisinya 
        JLabel labelInput = new JLabel("Nama : ");
        labelInput.setBounds(15, 40, 350, 10);
        
        // Membuat TextField untuk memasukkan nama dan mengatur posisinya 
        JTextField textField = new JTextField();
        textField.setBounds(15, 60, 350, 30);
        
        // Membuat label "Nomor HP" dan mengatur posisinya 
        JLabel labelInput1 = new JLabel("Nomor HP : ");
        labelInput1.setBounds(15, 95, 350, 10);
        
        // Membuat TextField untuk memasukkan nomor HP dan mengatur posisinya 
        JTextField textField1 = new JTextField();
        textField1.setBounds(15, 110, 350, 30);
        
        // Membuat label "Jenis Kelamin" dan mengatur posisinya
        JLabel labelRadio = new JLabel("Jenis Kelamin");
        labelRadio.setBounds(15, 130, 350, 10);
        
        // Membuat RadioButton "Laki-Laki" dan mengatur posisinya 
        JRadioButton radioButton1 = new JRadioButton("Laki-Laki", true); // Default terpilih adalah laki laki
        radioButton1.setBounds(15, 145, 350, 30); //atur posisi
        
        // Membuat RadioButton "Perempuan" dan mengatur posisinya 
        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(15, 175, 350, 30);
        
        // Membuat CheckBox "Warga Negara Asing" dan mengatur posisinya 
        JCheckBox checkBox = new JCheckBox("Warga Negara Asing");
        checkBox.setBounds(15, 205, 350, 30);
        
        // Membuat grup RadioButton agar hanya satu yang bisa dipilih pada satu waktu
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);
        
        // Membuat tombol "Simpan" dan mengatur posisinya 
        JButton button = new JButton("Simpan");
        button.setBounds(15, 240, 100, 40);
        
        // Membuat JTextArea untuk menampilkan hasil keluaran dan mengatur posisinya 
        JTextArea txtOutput = new JTextArea("");
        txtOutput.setBounds(15, 290, 350, 200);
        
        // Menambahkan listener untuk tombol "Simpan"
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String jenisKelamin = "";
                //jika radio button 1 yang di pilih maka jenis kelamin akan mengambil data dari radio button yang di pilih
                if (radioButton1.isSelected()) {
                    jenisKelamin = radioButton1.getText();
                }
                if (radioButton2.isSelected()) {
                    jenisKelamin = radioButton2.getText();
                }
                
                String nama = textField.getText();
                String noHp = textField1.getText();
                String wnaStatus = checkBox.isSelected() ? "Ya" : "Bukan";
                
                // Menampilkan hasil input ke JTextArea
                txtOutput.append("Nama : " + nama + "\n");
                txtOutput.append("Nomor HP : " + noHp + "\n");
                txtOutput.append("Jenis Kelamin : " + jenisKelamin + "\n");
                txtOutput.append("WNA : " + wnaStatus + "\n");
                txtOutput.append("==============\n");
                
                textField.setText(""); // Mengosongkan TextField setelah tombol "Simpan" ditekan
            }
        });
        
        // Menambahkan komponen-komponen ke frame
        this.add(button);
        this.add(textField);
        this.add(textField1);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(checkBox);
        this.add(labelInput);
        this.add(labelInput1);
        this.add(txtOutput);
        
        this.setSize(400, 800); // Mengatur ukuran jendela
        this.setLayout(null); // Mengatur layout menjadi null (bebas posisi)
    }
    
    /**
     * Metode utama yang akan membuat objek HelloRadioButton dan menampilkannya di EDT.
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AplikasiBiodata h = new AplikasiBiodata();
                h.setVisible(true); // Menampilkan jendela
            }
        });
    }
}
