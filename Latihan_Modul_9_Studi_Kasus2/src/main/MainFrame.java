package main;

// Modul 8 (Pertemuan 9) - Latihan 2

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import pendidikan.PendidikanFrame;
import biodata.BiodataFrame;
import dao.*;

public class MainFrame extends JFrame {
    // List untuk melakukan manajemen dan membuka frame, serta mengakses data dari database
    private PendidikanFrame pendidikanFrame;
    private BiodataFrame biodataFrame;
    private JButton buttonPendidikan;
    private JButton buttonBiodata;
    private PendidikanDao pendidikanDao;
    private BiodataDao biodataDao;
    
    // Konstruktor kelas MainFrame
    public MainFrame() {
        // Mengatur operasi default saat tombol close di klik
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Mengatur ukuran frame
        this.setSize(400, 500);
        
        // Inisialisasi objek dao untuk jenis member dan member
        this.pendidikanDao = new PendidikanDao();
        this.biodataDao = new BiodataDao();
        
        // Inisialisasi frame jenis member dan member
        this.pendidikanFrame = new PendidikanFrame(pendidikanDao);
        this.biodataFrame = new BiodataFrame(biodataDao, pendidikanDao);
        
        // Mengatur layout frame menggunakan FlowLayout
        this.setLayout(new FlowLayout());
        // Membuat objek listener untuk menangani aksi tombol
        MainButtonActionListener actionListener = new MainButtonActionListener(this);
        
        // Membuat tombol untuk membuka frame pendidikan dan biodata
        this.buttonPendidikan = new JButton("Pendidikan");
        this.buttonBiodata = new JButton("Biodata");
        
        // Menambahkan listener ke tombol-tombol
        this.buttonPendidikan.addActionListener(actionListener);
        this.buttonBiodata.addActionListener(actionListener);
        
        // Menambahkan tombol-tombol ke frame
        this.add(buttonPendidikan);
        this.add(buttonBiodata);
    }
    
    // Getter untuk tombol pendidikan
    public JButton getButtonPendidikan() {
        return buttonPendidikan;
    }
    
    // Getter untuk tombol biodata
    public JButton getButtonMember() {
        return buttonBiodata;
    }
    
    // Menampilkan frame pendidikan
    public void showPendidikanFrame() {
        if (pendidikanFrame == null) {
            pendidikanFrame = new PendidikanFrame(pendidikanDao);
        }
        pendidikanFrame.setVisible(true);
    }

    // Menampilkan frame biodata
    public void showBiodataFrame() {
        if (biodataFrame == null) {
            biodataFrame = new BiodataFrame(biodataDao, pendidikanDao);
        }
        // Mengisi combo box jenis member di frame member
        biodataFrame.populateComboJenis();
        biodataFrame.setVisible(true);
    }

    // Metode main untuk menjalankan aplikasi Swing    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame f = new MainFrame();
                f.setVisible(true);
            }
        });
    }
}