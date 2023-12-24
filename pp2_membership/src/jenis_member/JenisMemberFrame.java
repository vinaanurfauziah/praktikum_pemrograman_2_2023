package jenis_member;

import javax.swing.*;
import java.util.*;
import dao.JenisMemberDao;
import member.Member;

public class JenisMemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private JTextField textFieldNama;
    private JenisMemberTableModel tableModel;
    private JenisMemberDao jenisMemberDao;
    private JTable table;

    public JenisMemberFrame(JenisMemberDao jenisMemberDao) {
        this.jenisMemberDao = jenisMemberDao;
        this.jenisMemberList = jenisMemberDao.findAll();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelInput = new JLabel("Nama :");
        labelInput.setBounds(15, 40, 350, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JButton button = new JButton("Simpan");
        button.setBounds(15,100,100,40);

        JButton buttonHapus = new JButton("Hapus");
        buttonHapus.setBounds(115,100,100,40);

        JButton buttonEdit = new JButton("Edit");
        buttonEdit.setBounds(215,100,100,40);

        this.table = new JTable();
        JScrollPane scrollableTale = new JScrollPane(table);
        scrollableTale.setBounds(15,150,350,200);

        tableModel = new JenisMemberTableModel(jenisMemberList);
        table.setModel(tableModel);

        JenisMemberButtonSimpanActionListener actionListener = new JenisMemberButtonSimpanActionListener(this, jenisMemberDao);
        JenisMemberButtonHapusActionListener actionListener_hapus = new JenisMemberButtonHapusActionListener(this, jenisMemberDao );
        JenisMemberButtonEditActionListener actionListener_edit = new JenisMemberButtonEditActionListener(this, jenisMemberDao, this.jenisMemberList);

        button.addActionListener(actionListener);
        buttonEdit.addActionListener(actionListener_edit);
        buttonHapus.addActionListener(actionListener_hapus);
        this.add(button);
        this.add(buttonEdit);
        this.add(buttonHapus);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(scrollableTale);

        this.setSize(400,500);
        this.setLayout(null);
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public void setNama(String nama) {
        this.textFieldNama.setText(nama);
    }

    public void addJenisMember(JenisMember jenisMember) {
        tableModel.add(jenisMember);
        textFieldNama.setText("");
    }
    public JTable getTable() { return this.table; }
    public boolean isEmptyField() {
        return Objects.equals(this.textFieldNama.getText(), "");
    }

    public void removeData(int selected) {
        this.tableModel.remove(selected);
    }
    public void updateJenisMember(JenisMember update, int selected) {
        this.tableModel.update(update, selected);
    }
}
