// Latihan 1 Modul ke-2 Komponen Swing 2
// Memperlihatkan cara kerja event ketika nilai checkbox berubah.
// Aplikasi hanya akan menerima inputan jika checkbox diceklis

package latihan_modul_2.pkg2;

import java.awt.event.*;
import javax.swing.*;

public class HelloCheckBox extends JFrame{
    // Variabel untuk menyimpan status kotak centang
    private boolean checkBoxSelected;
    //Konstruktor untuk kelas HelloCheckBox.
    public HelloCheckBox(){
        this.checkBoxSelected = false;
        // Mengatur operasi yang akan dilakukan saat jendela ditutup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Membuat label "Input Nama" dan mengatur posisinya 
        JLabel labelInput =  new JLabel("Input Nama: ");
        labelInput.setBounds(15,40,350,10);
        
        // Membuat TextField untuk memasukkan nama dan mengatur posisinya
        JTextField textField = new JTextField();
        textField.setBounds (15,60,350, 30);
        
        // Membuat kotak centang dengan teks "Saya Menyetujui Syarat dan Ketentuan Yang Berlaku" dan mengatur posisinya 
        JCheckBox checkBox = new JCheckBox("Saya Menyetujui Syarat dan Ketentuan Yang Berlaku");
        checkBox.setBounds(15,100,350,30);
        
        // Membuat tombol "Simpan" dan mengatur posisinya di jendela
        JButton button = new JButton("Simpan");
        button.setBounds(15,150,100,40);
        
        // Membuat JTextArea untuk menampilkan hasil keluaran dan mengatur posisinya di jendela
        JTextArea txtOutput =  new JTextArea();
        txtOutput.setBounds(15,200,350,100);
        
        // Menambahkan listener untuk kotak centang
        checkBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                checkBoxSelected = e.getStateChange() == 1; // Memperbarui checkBoxSelected saat kotak centang berubah
            }
        });
        
        // Menambahkan listener untuk tombol "Simpan"
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(checkBoxSelected){
                    // Jika kotak centang tercentang, ambil nama dari TextField dan tampilkan di JTextArea
                    String nama = textField.getText();
                    txtOutput.append("Hello "+nama+"\n");
                    textField.setText(""); // Mengosongkan TextField
                }else{
                    // Jika kotak centang tidak tercentang, tampilkan pesan kesalahan di JTextArea
                    txtOutput.append("Anda tidak mencetak kotak persetujuan");
                }
            }
        });
        
        // Menambahkan semua komponen ke jendela
        this.add(button);
        this.add(textField);
        this.add(checkBox);
        this.add(labelInput);
        this.add(txtOutput);
        
        // Mengatur ukuran jendela dan layout menjadi null
        this.setSize(400,500);
        this.setLayout(null);
    }
    
    /**
     * Metode utama yang akan membuat objek HelloCheckBox dan menampilkannya di EDT.
     */
    public static void main(String [] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run(){
                HelloCheckBox h = new HelloCheckBox();
                h.setVisible(true); // Menampilkan jendela
            }
        });
    }
}
