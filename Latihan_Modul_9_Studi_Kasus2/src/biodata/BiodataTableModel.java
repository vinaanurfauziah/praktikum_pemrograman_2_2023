package biodata;

// Modul 8 (Pertemuan 9) - Latihan 2

import javax.swing.table.*;
import java.util.List;

public class BiodataTableModel extends AbstractTableModel {
    // Array yang berisi nama kolom tabel
    private String[] columnNames = {"Nama", "Pendidikan", "No Telp", "Alamat", "Jenis Kelamin"};
    // List yang berisi data Biodata yang akan ditampilkan di tabel
    private List<Biodata> data;
    
    // Konstruktor kelas BiodataTableModel
    public BiodataTableModel(List<Biodata> data) {
        this.data = data;
    }
    
    // Mengembalikan jumlah kolom dalam tabel
    public int getColumnCount() {
        return columnNames.length;
    }
    
    // Mengembalikan jumlah baris dalam tabel sesuai dengan jumlah data Biodata
    public int getRowCount() {
        return data.size();
    }
    
    // Mengembalikan nama kolom pada indeks kolom tertentu
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    // Mengembalikan nilai yang akan ditampilkan pada sel tabel pada baris dan kolom tertentu
    public Object getValueAt(int row, int col) {
        Biodata rowItem = data.get(row);
        String value = "";
        
        // Switch case untuk menentukan nilai yang akan ditampilkan berdasarkan indeks kolom
        switch(col) {
            case 0: 
                value = rowItem.getNama();
                break;
            case 1:
                value = rowItem.getPendidikan().getNama();
                break;
            case 2:
                value = rowItem.getNoTelp();
                break;
            case 3:
                value = rowItem.getAlamat();
                break;
            case 4:
                value = rowItem.getJenisKelamin();
                break;
            case 5 : 
                value = rowItem.getId();
                break;
        }
        return value;
    }
    
    // Mendefinisikan apakah sel tabel dapat di-edit (dalam hal ini, tidak bisa di-edit)
    public boolean isCellEditable(int row, int col) {
        return false;
    }
    
    // Menambahkan data Biodata ke tabel dan memberi tahu tampilan untuk memperbarui dirinya
    public void add(Biodata value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
    
    // Menghapus data Biodata ke tabel dan memberi tahu tampilan untuk memperbarui dirinya
    public void remove(int value) {
        data.remove(value);
        fireTableRowsDeleted(data.size() - 1, data.size() - 1);
    }
}