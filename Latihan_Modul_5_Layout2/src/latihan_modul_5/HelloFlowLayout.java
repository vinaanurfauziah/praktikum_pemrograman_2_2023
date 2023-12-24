// Latihan 1 Modul ke-5 Layout 2
// Button 1-10

package latihan_modul_5;

import java.awt.*;
import javax.swing.*;

public class HelloFlowLayout extends JFrame{ // membuat kelas HelloFlowLayout yang merupakan turunan dari JFrame
    public HelloFlowLayout(){ // membuat fungsi HelloFlowLayout
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // operasi penutupan frame

        JButton button1 = new JButton("1"); // membuat tombol 1
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button5 = new JButton("5");
        JButton button4 = new JButton("4");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton button10 = new JButton("10");
        this.setLayout(new FlowLayout()); // setLayout untuk frame

        this.add(button1); // menambahkan tombol 1 pada frame
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.add(button5);
        this.add(button6);
        this.add(button7);
        this.add(button8);
        this.add(button9);
        this.add(button10);

        this.setSize(400, 400); // mengatur ukuran frame
    }

    public static void main(String[] args) { // fungsi main
        javax.swing.SwingUtilities.invokeLater(new Runnable() { // membuat thread
            @Override
            public void run() { // membuat fungsi run
               HelloFlowLayout h = new HelloFlowLayout(); // membuat objek h
               h.setVisible(true); // menampilkan frame
            }
        });
    }

}
