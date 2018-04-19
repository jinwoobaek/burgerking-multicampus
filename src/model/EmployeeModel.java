package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Employee;

public class EmployeeModel {

	Connection con;

	public EmployeeModel() throws Exception {
		con = DBCon.getConnection();
	}

	// 사원정보 등록
	public void registEmp(Employee vo) throws Exception {
		String sql = "INSERT INTO table_employee VALUES (emp_no_sequence.nextval, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, vo.getEmpName());
		ps.setString(2, vo.getIdNo());
		ps.setString(3, vo.getGender());
		ps.setString(4, vo.getAddr());
		ps.setString(5, vo.getJob());
		ps.setInt(6, vo.getSalary());

		ps.executeUpdate();

		ps.close();
	}

	public ArrayList searchEmp(int idx, String str) throws Exception {
		String[] key = { "emp_Name", "emp_No" };
		String sql = "SELECT * FROM table_employee WHERE " + key[idx] + " LIKE '%" + str + "%'";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ArrayList data = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("EMP_NO"));
			temp.add(rs.getString("EMP_NAME"));
			temp.add(rs.getString("ID_NO"));
			temp.add(rs.getString("GENDER"));
			temp.add(rs.getString("ADDR"));
			temp.add(rs.getString("JOB"));
			temp.add(rs.getInt("SALARY"));

			data.add(temp);
		}

		rs.close();
		ps.close();

		return data;
	}

	// 기본키로 검색
	public Employee selectByPk(int no) throws Exception {
		Employee vo = new Employee();
		String sql = "SELECT * FROM table_employee WHERE emp_no = " + no;

		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			vo.setEmpNo(rs.getInt("EMP_NO"));
			vo.setEmpName(rs.getString("EMP_NAME"));
			vo.setIdNo(rs.getString("ID_NO"));
			vo.setGender(rs.getString("GENDER"));
			vo.setAddr(rs.getString("ADDR"));
			vo.setJob(rs.getString("JOB"));
			vo.setSalary(rs.getInt("SALARY"));
		}
		rs.close();
		ps.close();
		return vo;
	}

	public void modifyEmp(Employee vo) throws Exception {
		String sql = "UPDATE table_employee SET emp_name = ?, id_no = ?, gender = ?, addr = ?, job = ?, salary = ? WHERE emp_no = ? ";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, vo.getEmpName());
		ps.setString(2, vo.getIdNo());
		ps.setString(3, vo.getGender());
		ps.setString(4, vo.getAddr());
		ps.setString(5, vo.getJob());
		ps.setInt(6, vo.getSalary());
		ps.setInt(7, vo.getEmpNo());
		
		ps.executeUpdate();
		
		ps.close();
	}

}
