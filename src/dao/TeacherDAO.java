package dao;

import model.Teachers;
import util.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO implements DAO<Teachers> {
    

    @Override
    public boolean insert(Teachers t){
        String sql = "INSERT INTO Teachers(user_id, username, password, full_name, role) VALUES (?,?,?,?,?)";
        try(Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, t.getUserId());
            pstmt.setString(2, t.getUserName());
            pstmt.setString(3, t.getPassWord());
            pstmt.setString(4, t.getFullName());
            pstmt.setString(5, "Teacher");

            return pstmt.executeUpdate() > 0;
        }catch(SQLException e){
            System.out.println("loi khi them giao vien: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Teachers t){
        String sql = "UPDATE Teachers SET username = ?, password = ?, full_name = ?, role = ? WHERE user_id = ?";
        try(Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(5, t.getUserId());
            pstmt.setString(1, t.getUserName());
            pstmt.setString(2, t.getPassWord());
            pstmt.setString(3, t.getFullName());
            pstmt.setString(4, "Teacher");

            return pstmt.executeUpdate() > 0;
        }catch(SQLException e){
            System.out.println("loi khi sua giao vien: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id){
        String sql = "DELETE FROM Teachers WHERE user_id = ?";
        try(Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }catch(SQLException e){
            System.out.println("loi khi xoa giao vien: " + e.getMessage());
            return false;
        }
    }

    public Teachers getById(int id){
        String sql = "SELECT * FROM Teachers WHERE user_id = ?";
        try(Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return new Teachers(id, rs.getString("username"), rs.getString("password"), rs.getString("full_name"));
            }
        }catch(SQLException e){
            System.out.println("loi khi lay tim kiem giao vien: " + e.getMessage());
        }
        return null;
    }

    public List<Teachers> getAll(){
        List<Teachers> list = new ArrayList<>();
        String sql = "SELECT * FROM Teachers";
        try(Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                list.add(new Teachers(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("full_name")));
            }

            return list;
        }catch(SQLException e){
            System.out.println("loi khi lay thong tin: " + e.getMessage());
        }
        return list;
    }
}
