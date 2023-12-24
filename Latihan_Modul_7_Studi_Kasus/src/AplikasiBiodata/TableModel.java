
package AplikasiBiodata;

import javax.swing.table.*;// Import semua class dari javax.swing.table
import java.util.ArrayList;// Import class ArrayList dari java.util
import java.util.List;// Import class List dari java.util

public class TableModel extends AbstractTableModel {
    private String[] columnNames = {"Nama", "Nomor HP", "Jenis Kelamin", "Alamat"}; // array string yang berisi nama-nama kolom yang akan ditampilkan di tabel.
    private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>(); // struktur data untuk menyimpan data yang akan ditampilkan dalam tabel.

    // digunakan untuk mengembalikan jumlah kolom yang ada dalam tabel.
    public int getColumnCount() {
        return columnNames.length;
    }

    // digunakan untuk mengembalikan jumlah baris (entri data) yang ada dalam tabel.
    public int getRowCount() {
        return data.size();
    }

    // digunakan untuk mengembalikan nama kolom yang sesuai dengan indeks kolom yang diberikan.
    public String getColumnName(int col) {
        return columnNames[col];
    }

    // digunakan untuk mendapatkan nilai (data) yang akan ditampilkan dalam sel (baris dan kolom tertentu) dari tabel.
    public Object getValueAt(int row, int col) {
        List<String> rowItem = data.get(row);
        return rowItem.get(col);
    }

    // digunakan untuk menentukan apakah sel dalam tabel dapat diedit. (tidak dapat diedit)
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    // digunakan untuk menambahkan baris data baru ke model tabel.
    public void add(ArrayList<String> value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    // Menghapus baris pada indeks tertentu
    public void remove(int rowIndex) {
        data.remove(rowIndex);
        // Memberi tahu tabel bahwa baris telah dihapus
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    // Mengambil data pada baris tertentu
    public ArrayList<String> getDataAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < data.size()) {
            return data.get(rowIndex);
        }
        return null;
    }

    // Memperbarui data pada baris tertentu
    public void update(int rowIndex, ArrayList<String> updatedData) {
        if (rowIndex >= 0 && rowIndex < data.size()) {
            data.set(rowIndex, updatedData);
            // Memberi tahu tabel bahwa baris telah diperbarui
            fireTableRowsUpdated(rowIndex, rowIndex);
        }
    }

    // Menghapus semua data dalam model tabel
    public void clearData() {
        int size = data.size();
        data.clear();
        // Memberi tahu tabel bahwa semua baris telah dihapus
        fireTableRowsDeleted(0, size - 1);
    }
}