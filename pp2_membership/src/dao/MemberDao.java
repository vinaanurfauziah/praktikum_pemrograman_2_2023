package dao;

import db.MySqlConnection;
import jenis_member.JenisMember;
import member.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    public int insert(Member member) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("Insert into member (id, nama, jenis_member_id) values (?, ?, ?)");
            statement.setString(1, member.getId());
            statement.setString(2, member.getNama());
            statement.setString(3, member.getJenisMember().getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int update(Member member) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("update member set nama = ?, jenis_member_id = ? where id = ?");
            statement.setString(1, member.getNama());
            statement.setString(2, member.getJenisMember().getId());
            statement.setString(3, member.getId());
            result = statement.executeUpdate();
            System.out.println("Update should be successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int delete(Member member) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("delete from member where id = ?");
            statement.setString(1, member.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Member findByName(String nama) {
        Member member = null;
        try {
            ResultSet resultSet = getResultSetNama(nama);
            while (resultSet.next()) {
                member = new Member();
                member.setId(resultSet.getString("id"));
                member.setNama(resultSet.getString("nama"));

                JenisMember jenisMember = new JenisMember();
                jenisMember.setId(resultSet.getString("jenis_member_id"));
                jenisMember.setNama(resultSet.getString("jenis_member_nama"));
                member.setJenisMember(jenisMember);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return member;
    }

    private static ResultSet getResultSetNama(String nama) throws SQLException {
        Connection connection = MySqlConnection.getInstance().getConnection();
        String query = "select member.id, member.nama, jenis_member.id jenis_member_id, jenis_member.nama jenis_member_nama from member join jenis_member on jenis_member.id = member.jenis_member_id where member.nama = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nama);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public List<Member> findAll() {
        List<Member> list = new ArrayList<>();
        try(Connection connection = MySqlConnection.getInstance().getConnection(); Statement statement = connection.createStatement();) {
            try(ResultSet resultSet = statement.executeQuery("select member.id, member.nama, jenis_member.id jenis_member_id, jenis_member.nama jenis_member_nama from member join jenis_member on jenis_member.id = member.jenis_member_id ");){
                while (resultSet.next()) {
                    Member member = new Member();
                    member.setId(resultSet.getString("id"));
                    member.setNama(resultSet.getString("nama"));

                    JenisMember jenisMember = new JenisMember();
                    jenisMember.setId(resultSet.getString("jenis_member_id"));
                    jenisMember.setNama(resultSet.getString("jenis_member_nama"));

                    member.setJenisMember(jenisMember);
                    list.add(member);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Member> find() {
        List<Member> list = new ArrayList<>();
        try(Connection connection = MySqlConnection.getInstance().getConnection(); Statement statement = connection.createStatement();) {
            try(ResultSet resultSet = statement.executeQuery("select member.id, member.nama, jenis_member.id jenis_member_id, jenis_member.nama jenis_member_nama from member join jenis_member on jenis_member.id = member.jenis_member_id ");){
                while (resultSet.next()) {
                    Member member = new Member();
                    member.setId(resultSet.getString("id"));
                    member.setNama(resultSet.getString("nama"));

                    JenisMember jenisMember = new JenisMember();
                    jenisMember.setId(resultSet.getString("jenis_member_id"));
                    jenisMember.setNama(resultSet.getString("jenis_member_nama"));

                    member.setJenisMember(jenisMember);
                    list.add(member);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
