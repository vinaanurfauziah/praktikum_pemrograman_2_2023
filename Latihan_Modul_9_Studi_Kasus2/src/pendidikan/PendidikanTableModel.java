package pendidikan;

// Modul 8 (Pertemuan 9) - Latihan 2

import javax.swing.table.*;
import java.util.List;

public class PendidikanTableModel extends AbstractTableModel {
    // Array yang berisi nama kolom tabel
    private String[] columnNames = {"Nama"};
    // List yang berisi data Biodata yang akan ditampilkan di tabel
    private List<Pendidikan> data;
    
    // Konstruktor kelas PendidikanTableModel
    public PendidikanTableModel(List<Pendidikan> data) {
        this.data = data;
    }
    
    // Mengembalikan jumlah kolom dalam tabel
    public int getColumnCount() {
        return columnNames.length;
    }
    
    // Mengembalikan jumlah baris dalam tabel sesuai dengan jumlah data Pendidikan
    public int getRowCount() {
        return data.size();
    }
    
    // Mengembalikan nama kolom pada indeks kolom tertentu
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    // Mengembalikan nilai yang akan ditampilkan pada sel tabel pada baris dan kolom tertentu
    public Object getValueAt(int row, int col) {
        Pendidikan rowItem = data.get(row);
        String value = "";
        
        // Switch case untuk menentukan nilai yang akan ditampilkan berdasarkan indeks kolom
        switch(col) {
            case 0: 
                value = rowItem.getNama();
                break;
            case 1 :
                value = rowItem.getId();
                break;
        }
        return value;
    }
    
    // Mendefinisikan apakah sel tabel dapat di-edit (dalam hal ini, tidak bisa di-edit)
    public boolean isCellEditable(int row, int col) {
        return false;
    }
    
    // Menambahkan data Pendidikan ke tabel dan memberi tahu tampilan untuk memperbarui dirinya
    public void add(Pendidikan value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
    
    // Menghapus data Pendidikan ke tabel dan memberi tahu tampilan untuk memperbarui dirinya
    public void remove(int value) {
        data.remove(value);
        fireTableRowsDeleted(data.size() - 1, data.size() - 1);
    }
}