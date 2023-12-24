package member;

import jenis_member.JenisMember;

import javax.swing.*;
import java.util.*;
import dao.MemberDao;
import dao.JenisMemberDao;

public class MemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField textFieldNama;
    private MemberTableModel tableModel;
    private JComboBox comboJenis;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;
    private JTable table;

    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        this.memberList = memberDao.findAll();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;

        JLabel labelInput = new JLabel("Nama: ");
        labelInput.setBounds(15, 40, 350, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JLabel labelJenis = new JLabel("Jenis Member: ");
        labelJenis.setBounds(15,100,350,10);

        comboJenis = new JComboBox();
        comboJenis.setBounds(15, 120,150, 30);

        JButton button = new JButton("Simpan");
        button.setBounds(15,160,100,40);

        JButton buttonHapus = new JButton("Hapus");
        buttonHapus.setBounds(115,160,100,40);

        JButton buttonEdit = new JButton("Edit");
        buttonEdit.setBounds(215,160,100,40);

        this.table = new JTable();
        JScrollPane scrollableTale = new JScrollPane(table);
        scrollableTale.setBounds(15,220,350,200);

        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);

        MemberButtonSimpanActionListener actionListener = new MemberButtonSimpanActionListener(this, memberDao);
        MemberButtonHapusActionListener actionListenerHapus = new MemberButtonHapusActionListener(this, memberDao);
        MemberButtonEditActionListener actionListenerEdit = new MemberButtonEditActionListener(this, memberDao,  this.memberList);
        button.addActionListener(actionListener);
        buttonHapus.addActionListener(actionListenerHapus);
        buttonEdit.addActionListener(actionListenerEdit);

        this.add(button);
        this.add(buttonHapus);
        this.add(buttonEdit);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(scrollableTale);

        this.setSize(400,500);
        this.setLayout(null);
    }
    public boolean isEmptyField() {
        return Objects.equals(this.textFieldNama.getText(), "");
    }
    public void populateComboJenis() {
        this.jenisMemberList = this.jenisMemberDao.findAll();
        comboJenis.removeAllItems();
        for(JenisMember jenisMember : this.jenisMemberList) {
            comboJenis.addItem(jenisMember.getNama());
        }
    }

    public String getNama() {return textFieldNama.getText();}
    public void setTextFieldNama(String nama) {this.textFieldNama.setText(nama);}
    public JTable getTable() { return table; }

    public JenisMember getJenisMember() {
        return jenisMemberList.get(comboJenis.getSelectedIndex());
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void removeData(int selected) {
//        this.memberList.remove(selected);
        this.tableModel.remove(selected);
    }

    public void addMember(Member member) {
        tableModel.add(member);
        textFieldNama.setText("");
    }

    public void updateMember(Member update, int selected) {
        this.tableModel.update(update, selected);
    }
}
