// Latihan 3 showConfirmDialog Modul ke-6
// JOptionPane, JScrollPane, JTable
// ketika tombol Exit diklik, akan muncul sebuah dialog konfirmasi

package latihan_modul_6;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelloConfirmDialog extends  JFrame { // membuat class ShowConfirmDialog yang merupakan turunan dari JFrame
    
    public HelloConfirmDialog(){ // membuat fungsi ShowConfirmDialog
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// operasi penutupan frame
        this.setLayout(new FlowLayout());// mengatur layout frame

        JButton button = new JButton("Exit");// membuat button dengan nama Exit
        button.setBounds(130, 100, 100, 40);// mengatur posisi button

        button.addActionListener(new ActionListener() { // menambahkan action listener
            public void actionPerformed(ActionEvent e) { // membuat fungsi action performed
                int confirmation = JOptionPane.showConfirmDialog(HelloConfirmDialog.this, "Apakah anda yakin ingin keluar dari aplikasi?", "Konfirmasi", JOptionPane.YES_NO_OPTION);// menampilkan pesan dialog
                if (confirmation == JOptionPane.YES_OPTION){ // jika menekan tombol yes
                    System.exit(0); // keluar dari program
                } else { // jika menekan tombol no
                    JOptionPane.showConfirmDialog(HelloConfirmDialog.this, "Anda menekan tombol no");// menampilkan pesan dialog
                }
            }
        });
        // menambahkan button ke frame
        this.add(button);
        this.setSize(400, 400);
    }

    // main method untuk menjalankan program
    public static void main (String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HelloConfirmDialog s = new HelloConfirmDialog();
                s.setVisible(true);
            }
        });
    }

}