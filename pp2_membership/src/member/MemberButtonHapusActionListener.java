package member;
import java.awt.event.*;
import java.util.List;
import dao.MemberDao;
import javax.swing.*;

public class MemberButtonHapusActionListener implements ActionListener {
    private MemberFrame memberFrame;
    private MemberDao memberDao;
    private List<Member> data;

    public MemberButtonHapusActionListener(MemberFrame memberFrame, MemberDao memberDao) {
        this.memberFrame = memberFrame;
        this.memberDao = memberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = this.memberFrame.getTable();
        int selected = table.getSelectedRow();
        String nama = (String) table.getValueAt(selected,0);
        Member member = this.memberDao.findByName(nama);
        this.memberFrame.removeData(selected);
        this.memberDao.delete(member);
    }

}
