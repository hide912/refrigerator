package com.refri.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class UserDAO {
	Connection conn;
	PreparedStatement st;
	ResultSet rs;
	int count;

	private UserDTO makeDTO(ResultSet rs) throws SQLException {
		String userID = rs.getString(1);
		String password = rs.getString(2);
		String userName = rs.getString(2);
		return new UserDTO(userID, password, userName);
	}
	
	// 모든 User 를 List에 담음
			public List<UserDTO> selectAll() {
				List<UserDTO> slist = new ArrayList<>();
				String sql = "SELECT * FROM user";
				conn = DBUtil.getConnect();
				try {
					st = conn.prepareStatement(sql);
					rs = st.executeQuery();
					while (rs.next()) {
						UserDTO dto = makeDTO(rs);
						slist.add(dto);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					DBUtil.dbClose(conn, st, rs);
				}
				return slist;
			}
	
			// userID로 선택된 User 리턴
			public UserDTO selectByid(String no) {
				UserDTO dto = null;
				String sql = "SELECT * FROM user WHERE userID = ?";
				conn = DBUtil.getConnect();
				try {
					st = conn.prepareStatement(sql);
					st.setString(1, no);
					rs = st.executeQuery();
					while (rs.next()) {
						dto = makeDTO(rs);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					DBUtil.dbClose(conn, st, rs);
				}
				return dto;
			}
			
			// UserName(사용자이름)으로 선택된 User 리턴
			public UserDTO selectByIng(String no) {
				UserDTO dto = null;
				String sql = "SELECT * FROM User WHERE username = ?";
				conn = DBUtil.getConnect();
				try {
					st = conn.prepareStatement(sql);
					st.setString(1, no);
					rs = st.executeQuery();
					while (rs.next()) {
						dto = makeDTO(rs);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					DBUtil.dbClose(conn, st, rs);
				}
				return dto;
			}
			
			// User 추가(by DTO), 추가된 수만큼 리턴
			public int insertUser(UserDTO dto) {
				String sql = "INSERT INTO user VALUES(?,?,?)";
				conn = DBUtil.getConnect();
				try {
					st = conn.prepareStatement(sql);
					st.setString(1, dto.getUserID());
					st.setString(2, dto.getPassword());
					st.setString(3, dto.getUserName());
					count = st.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					DBUtil.dbClose(conn, st, rs);
				}
				return count;
			}
			
			// User 삭제(by 대분류), 삭제된 수만큼 리턴
			public int deleteUser(String no) {
				String sql = "DELETE FROM user WHERE userID = ?";
				conn = DBUtil.getConnect();
				try {
					st = conn.prepareStatement(sql);
					st.setString(1, no);
		
					count = st.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					DBUtil.dbClose(conn, st, rs);
				}
				return count;
			}
			
			// User (password, 사용자 이름) 변경(by DTO), 변경된 수만큼 리턴
			public int updateUser(UserDTO dto) {
				String sql = "UPDATE user" + " SET" + " password = ?," + "Username = ?"  + " WHERE userID = ?";
				conn = DBUtil.getConnect();
				try {
					st = conn.prepareStatement(sql);
					st.setString(1, dto.getPassword());
					st.setString(2, dto.getUserName());
					st.setString(3, dto.getUserID());
		
					count = st.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					DBUtil.dbClose(conn, st, rs);
				}
				return count;
			}
	
}
