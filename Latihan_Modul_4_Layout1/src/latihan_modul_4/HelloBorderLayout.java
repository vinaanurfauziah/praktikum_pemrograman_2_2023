// Latihan 1 Modul ke-4 Layout 1
// mengatur komponen di lima wilayah: utara, selatan, timur, barat, dan tengah.
// Setiap region (area) dapat berisi satu komponen saja. Layout ini adalah layout default dari frame atau window.

package latihan_modul_4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelloBorderLayout extends JFrame {
    
    public HelloBorderLayout(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel labelPertanyaan = new JLabel("Apakah ibukota Indonesia?");
        JLabel labelHasil = new JLabel("Jawab pertanyaan di atas");
        
        JButton buttonA = new JButton("Jakarta");
        JButton buttonB = new JButton("Bandung");
        JButton buttonC = new JButton("Surabaya");
        
        buttonA.addActionListener((ActionEvent e) -> {
            labelHasil.setText("Jawaban anda benar");
        });
        
        buttonB.addActionListener((ActionEvent e) -> {
            labelHasil.setText("Jawaban anda salah");
        });
        
        buttonC.addActionListener((ActionEvent e) -> {
            labelHasil.setText("Jawaban anda salah");
        });
        
        this.add(labelPertanyaan, BorderLayout.NORTH);
        this.add(labelHasil, BorderLayout.SOUTH);
        this.add(buttonA, BorderLayout.WEST);
        this.add(buttonB, BorderLayout.CENTER);
        this.add(buttonC, BorderLayout.EAST);
        
        this.setSize(400, 200);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HelloBorderLayout h = new HelloBorderLayout();
                h.setVisible(true);
            }
        });
    } 
}
