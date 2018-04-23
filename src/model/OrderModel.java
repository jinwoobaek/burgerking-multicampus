package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Menu;
import vo.Stock;

public class OrderModel {
	Connection con;

	public OrderModel() throws Exception {
		con = DBCon.getConnection();
	}

	public ArrayList getMenuInfo(String name) throws Exception {
		String sql = "SELECT * FROM TABLE_MENU WHERE MENU_NAME= ? ";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, name);
		ResultSet rs=ps.executeQuery();
		
		ArrayList data = new ArrayList();

		rs.next();
			data.add(rs.getString("MENU_NAME"));
			data.add(rs.getString("PRICE"));
	

		rs.close();
		ps.close();
		 return data;
	}

	public void orderHistory(String menuName,int count, String totalPrice) throws Exception {
		
		
		String sql = "INSERT INTO TABLE_ORDER VALUES (ORDER_NO_SEQUENCE.NEXTVAL, ? ,10020 ,SYSDATE, ?) ";

		System.out.println(sql);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, menuName/*+"외 "+count+"개"*/);
		ps.setString(2, totalPrice);
		ps.executeUpdate();

		
		ps.close();

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
