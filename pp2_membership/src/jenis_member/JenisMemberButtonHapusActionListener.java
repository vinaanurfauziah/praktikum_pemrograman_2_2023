package jenis_member;

import java.awt.event.*;
import java.util.List;
import dao.JenisMemberDao;
import dao.MemberDao;
import jenis_member.JenisMember;
import jenis_member.JenisMemberFrame;

import javax.swing.*;
public class JenisMemberButtonHapusActionListener implements ActionListener {
    private JenisMemberFrame jenisMemberFrame;
    private JenisMemberDao jenisMemberDao;
    private List<JenisMember> data;

    public JenisMemberButtonHapusActionListener(JenisMemberFrame jenisMemberFrame, JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = this.jenisMemberFrame.getTable();
        int selected = table.getSelectedRow();
        String nama = (String) table.getValueAt(selected,0);
        JenisMember jenisMember = this.jenisMemberDao.findByName(nama);
        this.jenisMemberFrame.removeData(selected);
        this.jenisMemberDao.delete(jenisMember);
    }
}
