package main;

// Modul 8 (Pertemuan 9) - Latihan 2

// Mengimpor pustaka untuk event handling
import java.awt.event.*;

// Kelas yang mengimplementasikan antarmuka ActionListener
public class MainButtonActionListener implements ActionListener {
    // Variabel instance untuk mereferensikan objek MainFrame
    private MainFrame mainFrame;
    // Konstruktor dengan parameter MainFrame
    public MainButtonActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    // Metode yang diimplementasikan dari antarmuka ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        // Memeriksa apakah sumber event adalah tombol pendidikan
        if(e.getSource() == mainFrame.getButtonPendidikan()) {
            // Memanggil metode showPendidikanFrame() dari objek MainFrame
            mainFrame.showPendidikanFrame();
        }
        // Jika sumber event bukan tombol pendidikan
        else {
            // Memanggil metode showBiodataFrame() dari objek MainFrame
            mainFrame.showBiodataFrame();
        }
    }
}