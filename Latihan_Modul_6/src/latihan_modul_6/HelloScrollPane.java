// Latihan 5 - ScrollPane Modul ke-6
// JOptionPane, JScrollPane, JTable
// tambahkan penggunaan ScrollPane untuk menggulirkan TextArea yang menjadi komponen output.

package latihan_modul_6;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelloScrollPane extends  JFrame { // kelas helloscrollpane mewarisi kelas jframe
    public HelloScrollPane(){ // konstruktor atau fungsi yang memiliki nama yang sama dengan nama kelas
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // operasi penutupan frame

        JLabel labelInput = new JLabel("Input nama: "); // membuat label dengan nama Input nama
        labelInput.setBounds(130, 40, 100, 10); // mengatur posisi label

        JTextField textField = new JTextField(); // membuat textfield
        textField.setBounds(130, 60, 100, 30);

        JButton button = new JButton("Klik");// membuat button dengan nama Klik
        button.setBounds(130, 100, 100, 40);

        JTextArea txtOutput = new JTextArea();// membuat textarea
        JScrollPane scrollableTextArea = new JScrollPane(txtOutput); // membuat scrollable textarea
        scrollableTextArea.setBounds(130, 150, 100, 100); // mengatur posisi scrollable textarea
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // mengatur scroll horizontal
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);// mengatur scroll vertikal

        button.addActionListener(new ActionListener() { // menambahkan action listener pada button
            @Override
            public void actionPerformed(ActionEvent e) { // membuat fungsi action performed
                String nama = textField.getText();// mengambil nilai dari textfield
                txtOutput.append("Hello "+ nama + "\n");// menampilkan nilai dari textfield ke textarea
                textField.setText(""); // mengatur textfield menjadi kosong
            }
        });
        // menambahkan komponen ke frame
        this.add(labelInput);
        this.add(textField);
        this.add(button);
        this.add(scrollableTextArea);

        this.setSize(400, 400);
        this.setLayout(null);
    }
    public static void main (String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HelloScrollPane s = new HelloScrollPane();
                s.setVisible(true);
            }
        });
    }

}
