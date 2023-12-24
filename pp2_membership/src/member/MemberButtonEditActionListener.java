package member;

import dao.MemberDao;

import java.awt.event.*;
import jenis_member.JenisMember;

import java.util.List;
import java.util.UUID;
import dao.MemberDao;

import javax.swing.*;

public class MemberButtonEditActionListener implements ActionListener {
    private final List<Member> memberList;
    private MemberFrame memberFrame;
    private MemberDao memberDao;
    public MemberButtonEditActionListener(MemberFrame memberFrame, MemberDao memberDao, List<Member> member) {
        this.memberDao = memberDao;
        this.memberFrame = memberFrame;
        this.memberList = member;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = this.memberFrame.getTable();
        int selected = table.getSelectedRow();
        String nama = (String) table.getValueAt(selected,0);
        if (this.memberFrame.isEmptyField()) {
            memberFrame.setTextFieldNama(nama);
        } else {
            Member updateMember = new Member();
            Member member = memberList.get(selected);
            updateMember.setId(member.getId());
            updateMember.setNama(memberFrame.getNama());
            updateMember.setJenisMember(memberFrame.getJenisMember());
            this.memberDao.update(updateMember);
            this.memberFrame.updateMember(updateMember, selected);
            memberFrame.setTextFieldNama("");
        }
    }
}
