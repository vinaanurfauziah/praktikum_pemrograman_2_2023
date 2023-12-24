// Latihan 1 Modul ke-2
// Memperlihatkan cara kerja event aksi button. 
// Jika button klik ditekan, maka label Hello World berubah menjadi Hello Pemrograman II

package latihan_modul_2;

import java.awt.event.*;
import javax.swing.*;

public class ButtonEventSwing extends JFrame {
    
    public ButtonEventSwing(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel label = new JLabel("Hello World");
        label.setBounds(130,40,400,10);
        
        JButton button = new JButton("KLIK");
        button.setBounds(130,100,100,40);
        
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                label.setText("Hello Pemrograman II");
            }
        });
        
        this.add(button);
        this.add(label);
        
        this.setSize(400,500);
        this.setLayout(null);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ButtonEventSwing b = new ButtonEventSwing();
                b.setVisible(true);
            }
        });
    }
    
}
