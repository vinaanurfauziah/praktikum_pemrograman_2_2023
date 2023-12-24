// Latihan 6 Modul ke-6 
// JOptionPane, JScrollPane, JTable
// jika nama dan jenis member udah diisi, lalu klik simoan, maka data inputannya akan ditampung ke MyTableModel

package latihan_modul_6;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class HelloTable extends JFrame { //kelas HelloTable yang merupakan turunan dari kelas JFrame
    public HelloTable(){ //membuat fungsi HelloTable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //operasi penutupan frame

        JLabel labelInput = new JLabel("Input nama: "); //membuat label dengan nama Input nama
        labelInput.setBounds(15, 40, 350, 10); //mengatur posisi label

        JTextField textField = new JTextField();
        textField.setBounds(15, 60, 350, 30);

        JLabel labelRadio = new JLabel("Jenis Member: ");
        labelRadio.setBounds(15, 100, 350, 10);

        JRadioButton radioButton1 = new JRadioButton("Silver", true);
        radioButton1.setBounds(15, 115, 350, 30);

        JRadioButton radioButton2 = new JRadioButton("Gold");
        radioButton2.setBounds(15, 145, 350, 30);

        JRadioButton radioButton3 = new JRadioButton("Platinum");
        radioButton3.setBounds(15, 175, 350, 30);

        // membuat buttongroup
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1); // menambahkan radiobutton1 ke buttongroup
        bg.add(radioButton2);
        bg.add(radioButton3);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 235, 100, 40);

        javax.swing.JTable table = new javax.swing.JTable(); // membuat objek table
        JScrollPane scrollableTable = new JScrollPane(table);// membuat objek scrollable table
        scrollableTable.setBounds(15, 300, 350, 200); // mengatur posisi scrollable table

        MyTableModel tableModel = new MyTableModel();// membuat objek tableModel
        table.setModel(tableModel); // mengatur model table

        button.addActionListener(new ActionListener() { // menambahkan action listener pada button
            @Override
            public void actionPerformed(ActionEvent e) {// membuat fungsi action performed

                String jenisMember = ""; // membuat variabel jenisMember dengan tipe data string
                if (radioButton1.isSelected()) { // jika radiobutton1 dipilih
                    jenisMember = radioButton1.getText(); // ambil text dari radiobutton1
                }if(radioButton2.isSelected()){
                    jenisMember = radioButton2.getText();
                }if(radioButton3.isSelected()) {
                    jenisMember = radioButton3.getText();
                }
                String nama = textField.getText(); // mengambil nilai dari textfield
                tableModel.addData(new ArrayList<>(Arrays.asList(nama, jenisMember)));// menambahkan data ke tableModel

            }
        });
        // menambahkan komponen ke frame
        this.add(button);
        this.add(textField);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(radioButton3);
        this.add(labelInput);
        this.add(scrollableTable);

        this.setSize(400, 400);
        this.setLayout(null);
    }

    //main method untuk menjalankan program
    public static void main (String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HelloTable s = new HelloTable();
                s.setVisible(true);
            }
        });
    }

}