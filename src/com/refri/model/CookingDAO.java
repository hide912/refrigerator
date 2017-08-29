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
	
		// 모든 요리를 List에 담음
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
	
		// Cooking(요리명)으로 선택된 Cooking 리턴
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
	
		// classin(대분류)로 선택된 Cooking 리턴
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
	
		// Subclass(중분류)로 선택된 Cooking 리턴
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
	
		// Cooking(요리) 추가(by DTO), 추가된 수만큼 리턴
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
	
		// Cooking(요리) 삭제(by 대분류), 삭제된 수만큼 리턴
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
		
		// Cooking(요리) 변경(by DTO), 변경된 수만큼 리턴
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
