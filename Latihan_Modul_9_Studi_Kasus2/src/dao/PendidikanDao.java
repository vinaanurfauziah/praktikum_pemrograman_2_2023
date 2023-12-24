// Package ini untuk menempatkan akses seluruh data objek di dalam database
package dao;

// Modul 8 (Pertemuan 9) - Latihan 2

// Import library untuk menyambungkan database dan fungsi lainnya
import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pendidikan.Pendidikan;

// Deklarasi kelas PendidikanDao
public class PendidikanDao {
    // Method untuk memasukkan data Pendidikan ke dalam database
    public int insert(Pendidikan pendidikan) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            // Membuat PreparedStatement untuk menjalankan query INSERT
            PreparedStatement statement = connection.prepareStatement("insert into pendidikan (id, nama) values (?, ?)");

            // Mengisi parameter pada PreparedStatement dengan nilai dari objek Pendidikan
            statement.setString(1, pendidikan.getId());
            statement.setString(2, pendidikan.getNama());

            // Menjalankan query INSERT dan menyimpan hasilnya
            result = statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Method untuk memperbarui data pendidikan di dalam database berdasarkan ID
    public int update(Pendidikan pendidikan) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            // Membuat PreparedStatement untuk menjalankan query UPDATE
            PreparedStatement statement = connection.prepareStatement("update pendidikan set nama = ? where id = ?");

            // Mengisi parameter pada PreparedStatement dengan nilai baru dari objek Pendidikan
            statement.setString(1, pendidikan.getId());
            statement.setString(2, pendidikan.getNama());

            // Menjalankan query UPDATE dan menyimpan hasilnya
            result = statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Method untuk menghapus data Pendidikan dari database berdasarkan ID
    public int delete(Pendidikan pendidikan) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            // Membuat PreparedStatement untuk menjalankan query DELETE
            PreparedStatement statement = connection.prepareStatement("delete from pendidikan where id = ?");

            // Mengisi parameter pada PreparedStatement dengan nilai ID dari objek Pendidikan
            statement.setString(1, pendidikan.getId());

            // Menjalankan query DELETE dan menyimpan hasilnya
            result = statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Method untuk mengambil semua data Pendidikan dari database
    public List<Pendidikan> findAll() {
        List<Pendidikan> list = new ArrayList<>();
        try(Connection connection = MySqlConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();) {
            try(ResultSet resultSet = statement.executeQuery("select * from pendidikan");) {
                while(resultSet.next()) {
                    // Membuat objek Pendidikan
                    Pendidikan pendidikan = new Pendidikan();

                    // Mengisi nilai atribut objek Pendidikan dari hasil query
                    pendidikan.setId(resultSet.getString("id"));
                    pendidikan.setNama(resultSet.getString("nama"));

                    // Menambahkan objek Pendidikan ke dalam daftar
                    list.add(pendidikan);
                }
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}