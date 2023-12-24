// Latihan 2 Modul ke-5 Layout 2
// Agar posisi layout-nya di tengah

package latihan_modul_5;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class HelloGridBagLayout extends JFrame { // membuat kelas HelloGridBagLayout yang merupakan turunan dari JFrame
    public HelloGridBagLayout(){ // membuat fungsi HelloGridBagLayout
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // operasi penutupan frame
        JLabel headerLabel = new JLabel("Layout in action : GridBagLayout", JLabel.CENTER); // membuat label header dengan nama Layout in action : GridBagLayout dan posisi tengah

        JPanel controlPanel = new JPanel(); // membuat panel
        controlPanel.setLayout(new FlowLayout());// mengatur layout panel

        JPanel panel = new JPanel(); // membuat panel
        panel.setBackground(Color.darkGray); // mengatur warna panel
        panel.setSize(300,300); // mengatur ukuran panel
        GridBagLayout layout = new GridBagLayout(); //  membuat layout
        panel.setLayout(layout); // mengatur layout panel
        GridBagConstraints gbc = new GridBagConstraints(); // membuat gridbagconstraints

        gbc.fill = GridBagConstraints.HORIZONTAL; // mengatur gridbagconstraints
        gbc.gridx = 0; // mengatur gridbagconstraints posisi x menjadi 0
        gbc.gridy = 0; // mengatur gridbagconstraints posisi y menjadi 0
        panel.add(new JButton("Button 1"), gbc); // menambahkan button dengan memanggil fungsi JButton

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new JButton("Button 2"), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20; // mengatur gridbagconstraints pading ke y menjadi 20
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JButton("Button 3"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(new JButton("Button 4"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2; // mengatur gridbagconstraints lebar menjadi 2
        panel.add(new JButton("Button 5"), gbc);

        controlPanel.add(panel); // menambahkan panel ke controlPanel

        this.setLayout(new GridLayout(2,1)); // mengatur layout frame menjadi gridlayout dengan 2 baris dan 1 kolom
        this.add(headerLabel); // menambahkan headerLabel ke frame
        this.add(controlPanel); // menambahkan controlPanel ke frame
        this.setSize(400, 400);

    }

    public  static void main(String[] args){ // fungsi main
        javax.swing.SwingUtilities.invokeLater(new Runnable() { // membuat thread
            @Override
            public void run() {
                HelloGridBagLayout h = new HelloGridBagLayout(); // membuat objek h
                h.setVisible(true); // menampilkan frame
            }
        });
    }

}
