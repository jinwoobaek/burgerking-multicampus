package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Employee;
import vo.Stock;

public class StockModel {
	Connection con;

	public StockModel() throws Exception {
		con = DBCon.getConnection();
	}

	public void importStock(Stock vo) throws Exception {
		String sql = "INSERT INTO table_stock VALUES (stock_no_sequence.nextval, ?, ?, sysdate, ?) ";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, vo.getStockName());
		ps.setInt(2, vo.getValidPeriod());
		ps.setInt(3, vo.getAmount());

		ps.executeUpdate();

		ps.close();
	}

	public ArrayList searchStock(int idx, String str) throws Exception {
		String[] key = { "stock_Name", "stock_No" };
		String sql = "SELECT * FROM table_stock WHERE " + key[idx] + " LIKE '%" + str + "%'";

		System.out.println(sql);
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ArrayList data = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("STOCK_NO"));
			temp.add(rs.getString("STOCK_NAME"));
			temp.add(rs.getInt("VALID_PERIOD"));
			temp.add(rs.getString("ENTERING_DATE"));
			temp.add(rs.getInt("AMOUNT"));

			data.add(temp);
		}

		rs.close();
		ps.close();

		return data;
	}

	public Stock selectByPk(int no) throws Exception {
		Stock vo = new Stock();
		String sql = "SELECT * FROM table_stock WHERE stock_no = " + no;

		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			vo.setStockNo(rs.getInt("STOCK_NO"));
			vo.setStockName(rs.getString("STOCK_NAME"));
			vo.setValidPeriod(rs.getInt("VALID_PERIOD"));
			vo.setEnteringDate(rs.getString("ENTERING_DATE"));
			vo.setAmount(rs.getInt("AMOUNT"));
		}
		rs.close();
		ps.close();
		return vo;
	}
}
