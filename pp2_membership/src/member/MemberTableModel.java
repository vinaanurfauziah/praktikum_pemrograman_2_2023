package member;

import jenis_member.JenisMember;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MemberTableModel extends AbstractTableModel {

    private String[] columnNames = {"Nama", "Jenis Member"};
    private List<Member> data;
    public MemberTableModel(List<Member> data) {
        this.data = data;
    }
    public int getColumnCount() {
        return columnNames.length;
    }
    public int getRowCount() {
        return data.size();
    }
    public String getColumnName(int col) {
        return columnNames[col];
    }
    public Object getValueAt(int row, int col) {
        Member rowItem = data.get(row);
        String value = "";

        switch (col) {
            case 0:
                value = rowItem.getNama();
                break;
            case 1:
                value = rowItem.getJenisMember().getNama();
                break;
        }
        return value;
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }
    public void add(Member value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void update(Member update, int selected) {
        data.set(selected, update);
        fireTableCellUpdated(selected, 0);
        fireTableCellUpdated(selected, 1);
    }

    public void remove(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < data.size()) {
            data.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex); // Memberi tahu JTable bahwa baris telah dihapus
        } else {
            throw new IndexOutOfBoundsException("Index tidak valid");
        }
    }
}
