package com.refri.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class ClassDAO {
	Connection conn;
	PreparedStatement st;
	ResultSet rs;
	int count;

	private ClassDTO makeDTO(ResultSet rs) throws SQLException {
		String classin = rs.getString(1);
		String subClass = rs.getString(2);
		return new ClassDTO(classin, subClass);
	}

	// ��� Class �� List�� ����
	public List<ClassDTO> selectAll() {
		List<ClassDTO> slist = new ArrayList<>();
		String sql = "SELECT * FROM instructor";
		conn = DBUtil.getConnect();
		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				ClassDTO dto = makeDTO(rs);
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

	// classin(��з�)�� ���õ� Class ����
	public ClassDTO selectByIn(String no) {
		ClassDTO dto = null;
		String sql = "SELECT * FROM class WHERE classin = ?";
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

	// Subclass(�ߺз�)�� ���õ� Class ����
	public ClassDTO selectBySub(String no) {
		ClassDTO dto = null;
		String sql = "SELECT * FROM class WHERE subClass = ?";
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
	
	// Class(�з�) �߰�(by DTO), �߰��� ����ŭ ����
		public int insertClass(ClassDTO dto) {
			String sql = "INSERT INTO class VALUES(?,?)";
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, dto.getClassin());
				st.setString(2, dto.getSubClass());
				count = st.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}
			return count;
		}
		
		// Class(�з�) ����(by ��з�), ������ ����ŭ ����
		public int deleteClass(String no) {
			String sql = "DELETE FROM class WHERE classin = ?";
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
		
		
		
}
