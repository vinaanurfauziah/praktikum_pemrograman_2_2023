package main;

import biodata.Biodata;
import biodata.BiodataTableModel;
import dao.BiodataDao;


import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class MainFrame extends JFrame{
    private List<Biodata> biodataList;
    
    private JLabel judulLabel;

    private JLabel namaLabel;
    private JTextField namaTextField;

    private JLabel alamatLabel;
    private JTextArea alamatTextArea;

    private JLabel noHpLabel;
    private JTextField noHpTextField;

    private JLabel jenisKelaminLabel;
    private JRadioButton lakiLakiRadioButton;
    private JRadioButton perempuanRadioButton;

    private JLabel statusLabel;
    private JCheckBox statusCheckBox;

    private JButton simpanButton;
    private JButton ubahButton;
    private JButton hapusButton;

    private JButton updateButton;

    private JButton simpanKeFileButton;

    private JTable table;
    private JScrollPane scrollableTable;

    private BiodataDao biodataDao;
    private BiodataTableModel tableModel;

    public MainFrame( BiodataDao biodataDao) {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(JOptionPane.showConfirmDialog(
                        MainFrame.this,
                        "Apakah anda yakin ingin keluar?",
                        "Exit", JOptionPane.YES_NO_OPTION
                ) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        this.biodataDao = biodataDao;
        this.biodataList = biodataDao.findAll();
        
        judulLabel =  new JLabel ("Aplikasi Biodata");
        judulLabel.setBounds(250, 10, 500, 10);
        
        namaLabel = new JLabel("Nama");
        namaLabel.setBounds(15, 40, 500, 10);

        namaTextField = new JTextField();
        namaTextField.setBounds(15, 60, 500, 30);

        noHpLabel = new JLabel("No HP");
        noHpLabel.setBounds(15, 100, 500, 10);

        noHpTextField = new JTextField();
        noHpTextField.setBounds(15, 120, 500, 30);

        jenisKelaminLabel = new JLabel("Jenis Kelamin");
        jenisKelaminLabel.setBounds(15, 160, 500, 10);

        lakiLakiRadioButton = new JRadioButton("Laki-laki");
        lakiLakiRadioButton.setBounds(15, 180, 500, 20);

        perempuanRadioButton = new JRadioButton("Perempuan");
        perempuanRadioButton.setBounds(15, 210, 500, 20);

        ButtonGroup bg = new ButtonGroup();
        bg.add(lakiLakiRadioButton);
        bg.add(perempuanRadioButton);

        statusLabel = new JLabel("Status");
        statusLabel.setBounds(15, 240, 500, 10);

        statusCheckBox = new JCheckBox("Warga Negara Asing");
        statusCheckBox.setBounds(15, 260, 500, 20);

        alamatLabel = new JLabel("Alamat");
        alamatLabel.setBounds(15, 290, 500, 10);

        alamatTextArea = new JTextArea();
        alamatTextArea.setBounds(15, 310, 500, 90);

        simpanButton = new JButton("Simpan");
        simpanButton.setBounds(15, 410, 100, 40);

        ubahButton = new JButton("Ubah");
        ubahButton.setBounds(120, 410, 100, 40);

        updateButton = new JButton("Update");
        updateButton.setBounds(225, 410, 100, 40);

        hapusButton = new JButton("Hapus");
        hapusButton.setBounds(330, 410, 100, 40);

        simpanKeFileButton = new JButton("Simpan ke File");
        simpanKeFileButton.setBounds(435, 410, 160, 40);


        table = new JTable();
        scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 460, 500, 200);

        tableModel = new BiodataTableModel(this.biodataList);
        table.setModel(tableModel);

        ButtonAddActionListener addActionListener = new ButtonAddActionListener(this, biodataDao);
        ButtonDeleteActionListener deleteActionListener = new ButtonDeleteActionListener(this, biodataDao);
        ButtonUpdateActionListener updateActionListener = new ButtonUpdateActionListener(this, biodataDao);
        ButtonUbahActionListener ubahActionListener = new ButtonUbahActionListener(this);
        ButtonSimpanKeFileActionListener simpanKeFileActionListener = new ButtonSimpanKeFileActionListener(this, biodataDao);

        simpanButton.addActionListener(addActionListener);
        hapusButton.addActionListener(deleteActionListener);
        ubahButton.addActionListener(ubahActionListener);
        updateButton.addActionListener(updateActionListener);
        simpanKeFileButton.addActionListener(simpanKeFileActionListener);
        
        this.add(judulLabel);
        this.add(namaLabel);
        this.add(namaTextField);
        this.add(noHpLabel);
        this.add(noHpTextField);
        this.add(jenisKelaminLabel);
        this.add(lakiLakiRadioButton);
        this.add(perempuanRadioButton);
        this.add(statusLabel);
        this.add(statusCheckBox);
        this.add(alamatLabel);
        this.add(alamatTextArea);
        this.add(simpanButton);
        this.add(ubahButton);
        this.add(hapusButton);
        this.add(updateButton);
        this.add(simpanKeFileButton);
        this.add(scrollableTable);

        this.setSize(700, 800);
        this.setLayout(null);
        this.setVisible(true);
    }

    public String getNama() {
        return this.namaTextField.getText();
    }

    public String getAlamat() {
        return this.alamatTextArea.getText();
    }

    public String getNoHp() {
        return this.noHpTextField.getText();
    }

    public String getJenisKelamin() {
        if(this.lakiLakiRadioButton.isSelected()) {
            return "Laki-laki";
        }
        else if(this.perempuanRadioButton.isSelected()) {
            return "Perempuan";
        }
        else {
            return "";
        }
    }

    public String getStatus() {
        if(this.statusCheckBox.isSelected()) {
            return "WNA";
        }
        else {
            return "WNI";
        }
    }

    public JTable getTable() {
        return this.table;
    }

    public void clearForm() {
        this.namaTextField.setText("");
        this.alamatTextArea.setText("");
        this.noHpTextField.setText("");
        this.lakiLakiRadioButton.setSelected(false);
        this.perempuanRadioButton.setSelected(false);
        this.statusCheckBox.setSelected(false);
    }

    public BiodataTableModel getTableModel() {
        return this.tableModel;
    }
    public void addBiodata(Biodata biodata) {
        this.tableModel.add(biodata);
    }

    public void deleteBiodata() {
        this.tableModel.delete(this.table.getSelectedRow());
    }

    public void setNama(String nama) {
        this.namaTextField.setText(nama);
    }

    public void setAlamat(String alamat) {
        this.alamatTextArea.setText(alamat);
    }

    public void setNoHp(String noHp) {
        this.noHpTextField.setText(noHp);
    }

    public void setBiodataToForm(Biodata biodata) {
        setNama(biodata.getNama());
        setAlamat(biodata.getAlamat());
        setNoHp(biodata.getNoHp());

        getStatusCheckBox().setSelected("WNA".equals(biodata.getStatus()));
    }

    public void setJenisKelamin(String jenisKelamin) {
        if("laki-laki".equals(jenisKelamin)) {
            this.lakiLakiRadioButton.setSelected(true);
        }
        else if("perempuan".equals(jenisKelamin)) {
            this.perempuanRadioButton.setSelected(true);
        }
    }

    public JCheckBox getStatusCheckBox() {
        return this.statusCheckBox;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame f = new MainFrame(new BiodataDao());
                f.setVisible(true);
            }
        });
    }
}