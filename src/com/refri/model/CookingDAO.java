package com.refri.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

	public class CookingDAO {
		Connection conn;
		PreparedStatement st;
		ResultSet rs;
		int count;
	
		private CookingDTO makeDTO(ResultSet rs) throws SQLException {
			String cooking = rs.getString(1);
			String classin = rs.getString(2);
			String subClass = rs.getString(2);
			return new CookingDTO(cooking, classin, subClass);
		}
	
		// ��� �丮�� List�� ����
		public List<CookingDTO> selectAll() {
			List<CookingDTO> clist = new ArrayList<>();
			String sql = "SELECT * FROM cooking";
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				rs = st.executeQuery();
				while (rs.next()) {
					CookingDTO dto = makeDTO(rs);
					clist.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}
			return clist;
		}
	
		// Cooking(�丮��)���� ���õ� Cooking ����
		public List<CookingDTO> selectByCooking(String no) {
			List<CookingDTO> clist = new ArrayList<>();
			String sql = "SELECT * FROM cooking WHERE cooking = ?";
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, no);
				rs = st.executeQuery();
				while (rs.next()) {
					CookingDTO dto = makeDTO(rs);
					clist.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}
			return clist;
		}
	
		// classin(��з�)�� ���õ� Cooking ����
		public List<CookingDTO> selectByIn(String no) {
			List<CookingDTO> clist = new ArrayList<>();
			String sql = "SELECT * FROM cooking WHERE classin = ?";
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, no);
				rs = st.executeQuery();
				while (rs.next()) {
					CookingDTO dto = makeDTO(rs);
					clist.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}
			return clist;
		}
	
		// Subclass(�ߺз�)�� ���õ� Cooking ����
		public List<CookingDTO> selectBySub(String no) {
			List<CookingDTO> clist = new ArrayList<>();
			String sql = "SELECT * FROM cooking WHERE subClass = ?";
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, no);
				rs = st.executeQuery();
				while (rs.next()) {
					CookingDTO dto = makeDTO(rs);
					clist.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}
			return clist;
		}
	
		// Cooking(�丮) �߰�(by DTO), �߰��� ����ŭ ����
		public int insertCooking(CookingDTO dto) {
			String sql = "INSERT INTO cooking VALUES(?,?,?)";
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, dto.getCooking());
				st.setString(2, dto.getClassin());
				st.setString(3, dto.getSubClass());
				count = st.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}
			return count;
		}
	
		// Cooking(�丮) ����(by ��з�), ������ ����ŭ ����
		public int deleteCooking(String no) {
			String sql = "DELETE FROM cooking WHERE classin = ?";
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
		
		// Cooking(�丮) ����(by DTO), ����� ����ŭ ����
		public int updateCooking(CookingDTO dto) {
			String sql = "UPDATE cooking" + " SET" + " classin = ?," + " WHERE cooking = ?";
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, dto.getClassin());
				st.setString(2, dto.getCooking());

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
