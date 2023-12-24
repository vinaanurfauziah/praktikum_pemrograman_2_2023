package jenis_member;

import dao.JenisMemberDao;
import member.Member;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class JenisMemberButtonEditActionListener implements ActionListener {
    private final JenisMemberDao jenisMemberDao;
    private final JenisMemberFrame jenisMemberFrame;
    private final List<JenisMember> jenisMemberList;

    public JenisMemberButtonEditActionListener(JenisMemberFrame jenisMemberFrame, JenisMemberDao jenisMemberDao, List<JenisMember> jenisMemberList) {
        this.jenisMemberDao = jenisMemberDao;
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberList = jenisMemberList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = this.jenisMemberFrame.getTable();
        int selected = table.getSelectedRow();
        String nama = (String) table.getValueAt(selected,0);
        if (this.jenisMemberFrame.isEmptyField()) {
            System.out.println("its empty! get name : "+this.jenisMemberFrame.getNama());
            this.jenisMemberFrame.setNama(nama);
            System.out.println("setname must be setted");
        } else {
            JenisMember updateJenisMember = new JenisMember();
            JenisMember jenisMember = jenisMemberList.get(selected);
            updateJenisMember.setId(jenisMember.getId());
            updateJenisMember.setNama(this.jenisMemberFrame.getNama());
            this.jenisMemberDao.update(updateJenisMember);
            this.jenisMemberFrame.updateJenisMember(updateJenisMember, selected);
            this.jenisMemberFrame.setNama("");
        }
    }
}
