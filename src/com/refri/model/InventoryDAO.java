package com.refri.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class InventoryDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	int count;

	// 모두조회
	public List<InventoryDTO> selectAll() {
		List<InventoryDTO> ilist = new ArrayList<>();
		String sql = "select * from inventory";
		conn = DBUtil.getConnect();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				InventoryDTO dto = makeInventory(rs);
				ilist.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return ilist;
	}

	// 재료명으로 한건조회
	public InventoryDTO selectByIng(String ing) {
		InventoryDTO dto = null;
		String sql = "select * from inventory where ingredient=?";
		conn = DBUtil.getConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, ing);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = makeInventory(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return dto;
	}
	//유효기간으로 여러건 조회
	public List<InventoryDTO> selectByOutputdate(Date day) {
		List<InventoryDTO> ilist = new ArrayList<>();
		String sql = "select * from inventory where outputdate=?";
		conn = DBUtil.getConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setDate(1, day);
			rs = ps.executeQuery();
			while (rs.next()) {
				InventoryDTO dto = makeInventory(rs);
				ilist.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return ilist;
	}
	
	//일주일 유효기간이 남은 건 조회
		public List<InventoryDTO> selectByDuedate(Date day) {
			List<InventoryDTO> ilist = new ArrayList<>();
			String sql = "select * from inventory where outputdate <= ?+7";
			conn = DBUtil.getConnect();
			try {
				ps = conn.prepareStatement(sql);
				ps.setDate(1, day);
				rs = ps.executeQuery();
				while (rs.next()) {
					InventoryDTO dto = makeInventory(rs);
					ilist.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, ps, rs);
			}
			return ilist;
		}

	// 입력
	public int inventoryInsert(InventoryDTO it) {
		String sql = "insert into inventory values (?,?,?,?,?,?)";
		conn = DBUtil.getConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, it.getIngredient());
			ps.setDate(2, it.getImportdate());
			ps.setInt(3, it.getStock());
			ps.setString(4, it.getUnit());
			ps.setDate(5, it.getOutputdate());
			ps.setString(6, it.getLocation());

			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return count;

	}

	// 수정
	public int inventoryUpdate(InventoryDTO it) {
		String sql = "update STUDENT set importdate=?,stock=?,unit=?,outputdate=?,location=? where ingredient=?";
		conn = DBUtil.getConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(6, it.getIngredient());
			ps.setDate(1, it.getImportdate());
			ps.setInt(2, it.getStock());
			ps.setString(3, it.getUnit());
			ps.setDate(4, it.getOutputdate());
			ps.setString(5, it.getLocation());
	
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return count;

	}
	
	//재료 삭제
	public int inventoryDelete(String ing) {
		String sql = "delete from inventory where ingredient=?";
		conn = DBUtil.getConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, ing);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, ps, rs);
		}
		return count;

	}
	
	private InventoryDTO makeInventory(ResultSet rs2) throws SQLException {
		String ingredient = rs.getString(1);
		Date importdate = rs.getDate(2);
		int stock = rs.getInt(3);
		String unit = rs.getString(4);
		Date outputdate = rs.getDate(5);
		String location = rs.getString(6);
		return new InventoryDTO(ingredient, importdate, stock, unit, outputdate, location);		
	}
}
