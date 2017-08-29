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

	// 모든 Class 를 List에 담음
	public List<ClassDTO> selectAll() {
		List<ClassDTO> cllist = new ArrayList<>();
		String sql = "SELECT * FROM class";
		conn = DBUtil.getConnect();
		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				ClassDTO dto = makeDTO(rs);
				cllist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		return cllist;
	}

	// classin(대분류)로 선택된 Class 리턴
	public List<ClassDTO> selectByIn(String no) {
		List<ClassDTO> cllist = new ArrayList<>();
		String sql = "SELECT * FROM class WHERE classin = ?";
		conn = DBUtil.getConnect();
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, no);
			rs = st.executeQuery();
			while (rs.next()) {
				ClassDTO dto = makeDTO(rs);
				cllist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		return cllist;
	}

	// Subclass(중분류)로 선택된 Class 리턴
	public List<ClassDTO> selectBySub(String no) {
		List<ClassDTO> cllist = new ArrayList<>();
		String sql = "SELECT * FROM class WHERE subClass = ?";
		conn = DBUtil.getConnect();
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, no);
			rs = st.executeQuery();
			while (rs.next()) {
				ClassDTO dto = makeDTO(rs);
				cllist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		return cllist;
	}
	
	// Class(분류) 추가(by DTO), 추가된 수만큼 리턴
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
		
		// Class(분류) 삭제(by 대분류), 삭제된 수만큼 리턴
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
