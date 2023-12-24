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
import biodata.Biodata;
import pendidikan.Pendidikan;

// Deklarasi kelas BiodataDao
public class BiodataDao {
    // Method untuk memasukkan biodata ke dalam database
    public int insert(Biodata biodata) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            // Membuat PreparedStatement untuk menjalankan query INSERT
            PreparedStatement statement = connection.prepareStatement("insert into biodata (id, nama, pendidikan_id, no_telp, alamat, jenis_kelamin) values (?, ?, ?, ?, ?, ?)");

            // Mengisi parameter pada PreparedStatement dengan nilai dari objek biodata
            statement.setString(1, biodata.getId());
            statement.setString(2, biodata.getNama());
            statement.setString(3, biodata.getPendidikan().getId());
            statement.setString(4, biodata.getNoTelp());
            statement.setString(5, biodata.getAlamat());
            statement.setString(6, biodata.getJenisKelamin());

            // Menjalankan query INSERT dan menyimpan hasilnya
            result = statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Method untuk memperbarui biodata dalam database berdasarkan ID
    public int update(String biodataId, Biodata newBiodata) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();) {
            // Membuat PreparedStatement untuk menjalankan query UPDATE
            PreparedStatement statement = connection.prepareStatement("update biodata set nama = ?, pendidikan_id = ?, no_telp = ?, alamat = ?, jenis_kelamin = ? where id = ?");

            // Mengisi parameter pada PreparedStatement dengan nilai baru dari objek biodata
            statement.setString(1, newBiodata.getNama());
            statement.setString(2, newBiodata.getPendidikan().getId());
            statement.setString(3, newBiodata.getNoTelp());
            statement.setString(4, newBiodata.getAlamat());
            statement.setString(5, newBiodata.getJenisKelamin());
            statement.setString(6, biodataId); // Menggunakan parameter ID untuk klausa WHERE

            // Menjalankan query UPDATE dan menyimpan hasilnya
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Method untuk menghapus biodata dari database
    public int delete(Biodata biodata) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            // Membuat PreparedStatement untuk menjalankan query DELETE
            PreparedStatement statement = connection.prepareStatement("delete from biodata where id = ?");

            // Mengisi parameter pada PreparedStatement dengan nilai ID dari objek biodata
            statement.setString(1, biodata.getId());

            // Menjalankan query DELETE dan menyimpan hasilnya
            result = statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Method untuk mengambil semua biodata dari database
    public List<Biodata> findAll() {
        List<Biodata> list = new ArrayList<>();
        try(Connection connection = MySqlConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement.executeQuery("select biodata.*, pendidikan.* " +
                    "from biodata join pendidikan on pendidikan.id = biodata.pendidikan_id");) {
                while(resultSet.next()) {
                    // Membuat objek Biodata
                    Biodata biodata = new Biodata();

                    // Mengisi nilai atribut objek Biodata dari hasil query
                    biodata.setId(resultSet.getString("id"));
                    biodata.setNama(resultSet.getString("nama"));
                    biodata.setNoTelp(resultSet.getString("no_telp"));
                    biodata.setAlamat(resultSet.getString("alamat"));
                    biodata.setJenisKelamin(resultSet.getString("jenis_kelamin"));

                    // Membuat objek Pendidikan
                    Pendidikan pendidikan = new Pendidikan();

                    // Mengisi nilai atribut objek Pendidikan dari hasil query
                    pendidikan.setId(resultSet.getString("biodata.pendidikan_id"));
                    pendidikan.setNama(resultSet.getString("pendidikan.nama"));

                    // Menetapkan objek Pendidikan ke objek Biodata
                    biodata.setPendidikan(pendidikan);

                    // Menambahkan objek Biodata ke dalam daftar
                    list.add(biodata);
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