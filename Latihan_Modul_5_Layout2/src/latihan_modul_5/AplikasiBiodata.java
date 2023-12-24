// Latihan 3 Modul ke-3 Layout 2
// mengubah aplikasi biodata agar memakai panel dan layout lainnya pada modul 5

package latihan_modul_5;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AplikasiBiodata extends JFrame {


    public AplikasiBiodata(){ // membuat fungsi biodataApp
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // operasi penutupan frame
        JLabel headerLabel = new JLabel("From Biodata", JLabel.CENTER); // membuat label header

        JPanel controlPanel = new JPanel(); // membuat panel
        controlPanel.setLayout(new FlowLayout()); // mengatur layout panel

        JLabel labelInputNama = new JLabel("Nama:"); // membuat fungsi label nama
        JLabel labelInputJenisKelamin = new JLabel("Jenis Kelamin:"); // membuat fungsi label jenis kelamin
        JLabel labelInputNoHP = new JLabel("No HP:"); // membuat fungsi label no hp
        JTextField textFieldNama = new JTextField(); // membuat fungsi textfield nama
        JTextField textFieldNoHP = new JTextField(); // membuat fungsi textfield no hp
        JCheckBox checkBoxWNA = new JCheckBox("Warga Negara Asing"); // membuat fungsi checkbox wna
        JTextArea txtOutput = new JTextArea();

        JRadioButton lakiLakiRadioButton = new JRadioButton("Laki-Laki"); // membuat fungsi radiobutton laki-laki
        JRadioButton perempuanRadioButton = new JRadioButton("Perempuan"); // membuat fungsi radiobutton perempuan

        ButtonGroup genderButtonGroup = new ButtonGroup(); // membuat fungsi buttongroup untuk menggabungkan dua radio button bisa di pilih salah satu
        genderButtonGroup.add(lakiLakiRadioButton); // menambahkan radiobutton laki-laki ke buttongroup
        genderButtonGroup.add(perempuanRadioButton); // menambahkan radiobutton perempuan ke buttongroup

        JButton buttonSimpan = new JButton("Simpan"); // membuat fungsi button simpan

        JPanel panel = new JPanel(); // membuat panel
        panel.setSize(400,400); // mengatur ukuran panel
        GridBagLayout layout = new GridBagLayout(); // membuat layout
        panel.setLayout(layout); // mengatur layout panel
        GridBagConstraints gbc = new GridBagConstraints(); // membuat gridbagconstraints

        gbc.fill = GridBagConstraints.HORIZONTAL; // mengatur gridbagconstraints
        gbc.ipadx = 100; // mengatur gridbagconstraints pading ke x menjadi 100
        gbc.gridx = 0; // mengatur gridbagconstraints posisi x menjadi 0
        gbc.gridy = 0; // mengatur gridbagconstraints posisi y menjadi 0
        panel.add(labelInputNama, gbc); // menambahkan label dengan memanggil fungsi labelInputNama

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(labelInputJenisKelamin, gbc); // menambahkan label dengan memanggil fungsi labelInputJenisKelamin
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(textFieldNama, gbc); // menambahkan textfield dengan memanggil fungsi textFieldNama

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(lakiLakiRadioButton, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(labelInputNoHP, gbc); // menambahkan label dengan memanggil fungsi labelInputNoHP

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(perempuanRadioButton, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10; // mengatur gridbagconstraints pading ke y menjadi 10
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(textFieldNoHP, gbc); // menambahkan textfield dengan memanggil fungsi textFieldNoHP

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(checkBoxWNA, gbc); // menambahkan checkbox dengan memanggil fungsi checkBoxWNA



        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20,0,0,0); // mengatur jarak antar komponen ke atas
        gbc.gridwidth = 2;
        panel.add(buttonSimpan, gbc); // menambahkan button dengan memanggil fungsi buttonSimpan


        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.ipady = 100;
        gbc.ipadx = 100;
        gbc.insets = new Insets(20,0,0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        panel.add(txtOutput, gbc); // menambahkan textarea dengan memanggil fungsi txtOutput


        controlPanel.add(panel); // menambahkan panel ke controlPanel

        this.setLayout(new GridLayout(2,1)); // mengatur layout frame
        this.add(headerLabel); // menambahkan headerLabel ke frame
        this.add(controlPanel); // menambahkan controlPanel ke frame
        this.setSize(800, 800); // mengatur ukuran frame


        //actionlistener
        buttonSimpan.addActionListener(new ActionListener() { // menambahkan actionlistener ke buttonSimpan
            @Override
            public void actionPerformed(ActionEvent e) { // membuat fungsi actionlistener
                String jenisKelamin = "";
                //mengecek jenis kelamin dan mengambil data yang di select
                if (lakiLakiRadioButton.isSelected()) {
                    jenisKelamin = lakiLakiRadioButton.getText();
                }
                if(perempuanRadioButton.isSelected()){
                    jenisKelamin = perempuanRadioButton.getText();
                }

                String nama = textFieldNama.getText(); // mengambil data dari textFieldNama
                String noHP = textFieldNoHP.getText(); // mengambil data dari textFieldNoHP
                String wna = checkBoxWNA.isSelected() ? "Ya" : "Bukan"; // mengecek checkbox apakah di select atau tidak

                //menampilkan ke txtOutput

                txtOutput.append("Nama: "+nama+"\n"); // menampilkan nama ke txtOutput
                txtOutput.append("Jenis Kelamin: "+jenisKelamin+"\n"); // menampilkan jenis kelamin ke txtOutput
                txtOutput.append("No HP: "+noHP+"\n"); // menampilkan no hp ke txtOutput
                txtOutput.append("WNA: "+wna+"\n"); // menampilkan wna ke txtOutput
                txtOutput.append("====================================\n"); // menampilkan garis ke txtOutput

            }
        });

    }

    public  static void main(String[] args){ // membuat fungsi main
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() { // membuat fungsi run
                AplikasiBiodata h = new AplikasiBiodata(); // membuat objek h
                h.setVisible(true); // menampilkan frame
            }
        });
    }

}
