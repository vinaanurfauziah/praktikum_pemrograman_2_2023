// Latihan 1 & 2 showMessageDialog
// Modul ke-6 JOptionPane, JScrollPane, JTable
// Jika tombol tulisan Klik ditekan, maka akan muncul kotak dialog

package latihan_modul_6;

import java.awt.*; // import package awt
import java.awt.event.*; // import package event
import javax.swing.*; // import package swing

public class HelloMessageDialog extends JFrame { // membuat class HelloMessageDialog yang merupakan turunan dari JFrame
    public HelloMessageDialog(){ // membuat fungsi HelloMessageDialog
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // operasi penutupan frame
        this.setLayout(new FlowLayout()); // mengatur layout frame

        JButton button = new JButton("Klik"); // membuat button dengan nama Klik
        button.setBounds(130, 100, 100, 40); // mengatur posisi button

        button.addActionListener(new ActionListener() { // menambahkan action listener
            @Override
            public void actionPerformed(ActionEvent e) { // membuat fungsi action performed
                // Latihan 2 - showMessageDialog
                //JOptionPane.showMessageDialog(HelloMessageDialog.this, "Hallo, Selamat datang di Praktikum Pemrograman II");
                //JOptionPane.showMessageDialog(HelloMessageDialog.this, "Hallo, Selamat datang di Praktikum Pemrograman II" , "Hello", JOptionPane.ERROR_MESSAGE);
                //JOptionPane.showMessageDialog(HelloMessageDialog.this, "Hallo, Selamat datang di Praktikum Pemrograman II" , "Hello", JOptionPane.INFORMATION_MESSAGE);
                //JOptionPane.showMessageDialog(HelloMessageDialog.this, "Hallo, Selamat datang di Praktikum Pemrograman II" , "Hello", JOptionPane.WARNING_MESSAGE);
                //JOptionPane.showMessageDialog(HelloMessageDialog.this, "Hallo, Selamat datang di Praktikum Pemrograman II" , "Hello", JOptionPane.QUESTION_MESSAGE);
                JOptionPane.showMessageDialog(HelloMessageDialog.this, "Halo, Selamat datang di Praktikum Pemrograman II" , "Message", JOptionPane.PLAIN_MESSAGE); // menampilkan pesan dialog
            }
        });

        this.add(button); // menambahkan button ke frame
        
        this.setSize(400, 400); // mengatur ukuran frame
    }
    
    public  static void main(String[] args){ // fungsi main
        javax.swing.SwingUtilities.invokeLater(new Runnable() { // membuat thread
            public void run() {
                HelloMessageDialog h = new HelloMessageDialog(); // membuat objek h
                h.setVisible(true); // menampilkan frame
            }
        });
    }
}
