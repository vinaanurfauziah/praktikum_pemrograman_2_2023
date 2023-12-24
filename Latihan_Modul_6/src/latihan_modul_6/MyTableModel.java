// Latihan 6 Modul ke-6
// JOptionPane, JScrollPane, JTable
// untuk menampung inputan data dari HelloTable

package latihan_modul_6;

import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;

public class MyTableModel extends AbstractTableModel { //kelas MyTableModel yang merupakan turunan dari kelas AbstractTableModel
    private String[] columnNames = {"Nama", "Jenis Member"};// membuat array columnNames
    private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>(); // membuat array data

    public int getColumnCount(){ // membuat fungsi getColumnCount
        return columnNames.length; // mengembalikan nilai panjang columnNames
    }
    public int getRowCount(){ // membuat fungsi getRowCount
        return data.size(); // mengembalikan nilai panjang data
    }
    public String getColumnName(int col){ // membuat fungsi getColumnName
        return columnNames[col];// mengembalikan nilai columnNames
    }



    public Object getValueAt(int row, int col) { // membuat fungsi getValueAt
        List<String> rowItem = data.get(row);// membuat list rowItem dengan nilai data pada indeks row
        return rowItem.get(col);// mengembalikan nilai rowItem pada indeks col
    }
    public boolean isCellEditable(int row, int col){ // membuat fungsi isCellEditable
        return false; // mengembalikan nilai false
    }

    public void addData(ArrayList<String> value){ // membuat fungsi addData
        data.add(value);// menambahkan nilai value ke data
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);// menambahkan baris pada table
    }

    void add(ArrayList<String> value) { // membuat fungsi add yang merupakan arraylist dari string
        data.add(value);// menambahkan nilai value ke data
        fireTableRowsInserted(data.size()-1, data.size()-1);// menambahkan baris pada table
    }
}
