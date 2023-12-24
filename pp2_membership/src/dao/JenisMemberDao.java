package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import jenis_member.JenisMember;
import member.Member;

public class JenisMemberDao {
    public int insert(JenisMember jenisMember) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("Insert into jenis_member (id, nama) values (?, ?)");
            statement.setString(1, jenisMember.getId());
            statement.setString(2, jenisMember.getNama());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int update(JenisMember jenisMember) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("Update jenis_member set nama = ? where id = ?");
            statement.setString(1, jenisMember.getNama());
            statement.setString(2, jenisMember.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int delete(JenisMember jenisMember) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("delete from jenis_member where id = ?");
            statement.setString(1, jenisMember.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public JenisMember findByName(String nama) {
        JenisMember jenisMember = null;
        try {
            ResultSet resultSet = getResultSetNama(nama);
            while (resultSet.next()) {
                jenisMember = new JenisMember();
                jenisMember.setId(resultSet.getString("id"));
                jenisMember.setNama(resultSet.getString("nama"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jenisMember;
    }

    public List<JenisMember> findAll() {
        List<JenisMember> list = new ArrayList<>();
        try(Connection connection = MySqlConnection.getInstance().getConnection(); Statement statement = connection.createStatement();) {
            try(ResultSet resultSet = statement.executeQuery("select * from jenis_member");){
                while (resultSet.next()) {
                    JenisMember jenisMember = new JenisMember();
                    jenisMember.setId(resultSet.getString("id"));
                    jenisMember.setNama(resultSet.getString("nama"));
                    list.add(jenisMember);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private static ResultSet getResultSetNama(String nama) throws SQLException {
        Connection connection = MySqlConnection.getInstance().getConnection();
        String query = "select * from jenis_member where nama = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nama);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
}
