/* Latihan 2 Modul ke-4 layout 1
untuk mengatur komponen dalam sebuah grid yang terdiri dari baris
dan kolom. Satu komponen ditampilkan di setiap sel/kotak dari grid tersebut.
*/
package latihan_modul_4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Deklarasi kelas HelloGridLayout yang merupakan turunan dari JFrame
public class HelloGridLayout extends JFrame implements ActionListener {

    // Deklarasi tombol-tombol untuk papan permainan
    private JButton buttonA;
    private JButton buttonB;
    private JButton buttonC;
    private JButton buttonE;
    private JButton buttonD;
    private JButton buttonF;
    private JButton buttonG;
    private JButton buttonH;
    private JButton buttonI;

    // Array untuk menyimpan semua tombol
    private JButton[] buttons;

    // Variabel untuk menandai apakah permainan sudah selesai
    private boolean gameOver;

    // Konstruktor kelas HelloGridLayout
    public HelloGridLayout(){
        // Pengaturan operasi penutupan frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menginisialisasi variabel gameOver sebagai false
        gameOver = false;

        // Menginisialisasi tombol-tombol
        buttonA = new JButton(""); // Tombol kosong awal
        buttonB = new JButton("");
        buttonC = new JButton("");
        buttonD = new JButton("");
        buttonE = new JButton("");
        buttonF = new JButton("");
        buttonG = new JButton("");
        buttonH = new JButton("");
        buttonI = new JButton("");

        // Menginisialisasi array tombol
        buttons = new JButton[9];
        buttons[0] = buttonA;
        buttons[1] = buttonB;
        buttons[2] = buttonC;
        buttons[3] = buttonD;
        buttons[4] = buttonE;
        buttons[5] = buttonF;
        buttons[6] = buttonG;
        buttons[7] = buttonH;
        buttons[8] = buttonI;

        // Menambahkan ActionListener ke setiap tombol
        buttonA.addActionListener(this);
        buttonB.addActionListener(this);
        buttonC.addActionListener(this);
        buttonD.addActionListener(this);
        buttonE.addActionListener(this);
        buttonF.addActionListener(this);
        buttonG.addActionListener(this);
        buttonH.addActionListener(this);
        buttonI.addActionListener(this);

        // Mengatur tata letak frame menggunakan GridLayout 3x3
        this.setLayout(new GridLayout(3,3));

        // Menambahkan tombol-tombol ke frame
        this.add(buttonA);
        this.add(buttonB);
        this.add(buttonC);
        this.add(buttonD);
        this.add(buttonE);
        this.add(buttonF);
        this.add(buttonG);
        this.add(buttonH);
        this.add(buttonI);

        // Mengatur ukuran frame
        this.setSize(300,300);
    }

    // Metode actionPerformed untuk menangani aksi tombol
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver)
        {
            JButton button = (JButton) e.getSource();
            if (button.getText().isEmpty())
            {
                button.setText("O"); // Menetapkan "O" pada tombol yang diklik
                checkWinner(); // Memeriksa apakah ada pemenang setelah setiap langkah pemain "O"
                if(!gameOver)
                {
                    for (int i = 0; i < buttons.length; i++) {
                        if (buttons[i].getText().isEmpty())
                        {
                            buttons[i].setText("X"); // Menetapkan "X" pada tombol yang tersisa
                            break;
                        }
                    }
                    checkWinner(); // Memeriksa apakah ada pemenang setelah setiap langkah pemain "X"
                }
            }
        }
    }

    // Metode untuk memeriksa apakah ada pemenang
    private void checkWinner()
    {
        String winner = ""; // Variabel untuk menyimpan pemenang (jika ada)

        // Memeriksa baris horizontal atas
        if (!buttonA.getText().isEmpty()
                && buttonA.getText().equals(buttonB.getText()) // Memeriksa apakah isi tombol A sama dengan isi tombol B
                && buttonA.getText().equals(buttonC.getText())) // Memeriksa apakah isi tombol A sama dengan isi tombol C
        {
            winner = buttonA.getText(); // Menyimpan pemenang
            buttonA.setBackground(Color.RED); // Mengubah latar belakang tombol pemenang menjadi merah
            buttonB.setBackground(Color.RED);
            buttonC.setBackground(Color.RED);
        }
        // Memeriksa baris horizontal tengah
        else if (!buttonD.getText().isEmpty()
                && buttonD.getText().equals(buttonE.getText()) // Memeriksa apakah isi tombol D sama dengan isi tombol E
                && buttonD.getText().equals(buttonF.getText())) // Memeriksa apakah isi tombol D sama dengan isi tombol F
        {
            winner = buttonD.getText();
            buttonD.setBackground(Color.RED); // Mengubah latar belakang tombol pemenang menjadi merah
            buttonE.setBackground(Color.RED);
            buttonF.setBackground(Color.RED);
        }
        // Memeriksa baris horizontal bawah
        else if (!buttonG.getText().isEmpty()
                && buttonG.getText().equals(buttonH.getText()) // Memeriksa apakah isi tombol G sama dengan isi tombol H
                && buttonG.getText().equals(buttonI.getText())) // Memeriksa apakah isi tombol G sama dengan isi tombol I
        {
            winner = buttonG.getText();
            buttonG.setBackground(Color.RED); // Mengubah latar belakang tombol pemenang menjadi merah
            buttonH.setBackground(Color.RED);
            buttonI.setBackground(Color.RED);
        }
        // Memeriksa baris vertikal kiri
        else if (!buttonA.getText().isEmpty()
                && buttonA.getText().equals(buttonD.getText())
                && buttonA.getText().equals(buttonG.getText()))
        {
            winner = buttonA.getText();
            buttonA.setBackground(Color.RED);
            buttonD.setBackground(Color.RED);
            buttonG.setBackground(Color.RED);
        }
        // Memeriksa baris vertikal tengah
        else if (!buttonB.getText().isEmpty()
                && buttonB.getText().equals(buttonE.getText())
                && buttonB.getText().equals(buttonH.getText()))
        {
            winner = buttonB.getText();
            buttonB.setBackground(Color.RED);
            buttonE.setBackground(Color.RED);
            buttonH.setBackground(Color.RED);
        }
        // Memeriksa baris vertikal kanan
        else if (!buttonC.getText().isEmpty()
                && buttonC.getText().equals(buttonF.getText())
                && buttonC.getText().equals(buttonI.getText()))
        {
            winner = buttonC.getText();
            buttonC.setBackground(Color.RED);
            buttonF.setBackground(Color.RED);
            buttonI.setBackground(Color.RED);
        }
        // Memeriksa diagonal dari kiri atas ke kanan bawah
        else if (!buttonA.getText().isEmpty()
                && buttonA.getText().equals(buttonE.getText())
                && buttonA.getText().equals(buttonI.getText()))
        {
            winner = buttonA.getText();
            buttonA.setBackground(Color.RED);
            buttonE.setBackground(Color.RED);
            buttonI.setBackground(Color.RED);
        }
        // Memeriksa diagonal dari kanan atas ke kiri bawah
        else if (!buttonC.getText().isEmpty()
                && buttonC.getText().equals(buttonE.getText())
                && buttonC.getText().equals(buttonG.getText()))
        {
            winner = buttonC.getText();
            buttonC.setBackground(Color.RED);
            buttonE.setBackground(Color.RED);
            buttonG.setBackground(Color.RED);
        }

        gameOver = !winner.isEmpty(); // Mengubah variabel gameOver menjadi true jika ada pemenang
    }


    // Metode utama untuk menjalankan aplikasi
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() { // Metode run untuk menjalankan aplikasi
                HelloGridLayout h = new HelloGridLayout(); // Membuat objek dari kelas HelloGridLayout
                h.setVisible(true); // Menampilkan jendela permainan
            }
        });
    }
}

















