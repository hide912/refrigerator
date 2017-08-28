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
	
	// ��� User �� List�� ����
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
	
			// userID�� ���õ� User ����
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
			
			// UserName(������̸�)���� ���õ� User ����
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
			
			// User �߰�(by DTO), �߰��� ����ŭ ����
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
			
			// User ����(by ��з�), ������ ����ŭ ����
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
			
			// User (password, ����� �̸�) ����(by DTO), ����� ����ŭ ����
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
