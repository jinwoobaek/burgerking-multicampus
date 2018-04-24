package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import main.BurgerKing;
import model.EmployeeModel;
import vo.Employee;

public class EmployeeView extends JPanel implements ActionListener {
	JTextField tf_EmpNo, tf_EmpName, tf_IdNo, tf_Gender, tf_Addr, tf_Job, tf_Salary;
	JButton btn_Regist, btn_Modify, btn_Delete, btn_Home;

	JComboBox com_EmpSearch;
	JTextField tf_EmpSearch;

	JTable tableEmployee;

	EmployeeTableModel tb_ModelEmployee;

	EmployeeModel model;

	public EmployeeView() {
		addLayout(); // 화면설계
		initStyle();
		connectDB(); // db 연결
		eventProc();
	}

	public void initStyle() {
		tf_EmpNo.setEditable(false);
	}

	void addLayout() {

		tf_EmpNo = new JTextField();
		tf_EmpName = new JTextField();
		tf_IdNo = new JTextField();
		tf_Gender = new JTextField();
		tf_Addr = new JTextField();
		tf_Job = new JTextField();
		tf_Salary = new JTextField();

		btn_Regist = new JButton("직원등록");
		btn_Modify = new JButton("정보변경");
		btn_Delete = new JButton("집으로가");
		btn_Home = new JButton("홈으로");

		String[] cbEmpSearch = { "이름", "사번" };
		com_EmpSearch = new JComboBox(cbEmpSearch);
		tf_EmpSearch = new JTextField(15);

		tb_ModelEmployee = new EmployeeTableModel();
		tableEmployee = new JTable(tb_ModelEmployee);

		// ************화면구성**************************
		// 왼쪽영역
		JPanel p_west = new JPanel();
		p_west.setLayout(new BorderLayout());

		// 왼쪽 위에서 가운데
		JPanel p_west_center = new JPanel();
		p_west_center.setLayout(new GridLayout(7, 2));
		p_west_center.add(new JLabel("사원번호"));
		p_west_center.add(tf_EmpNo);
		p_west_center.add(new JLabel("이름"));
		p_west_center.add(tf_EmpName);
		p_west_center.add(new JLabel("주민등록번호"));
		p_west_center.add(tf_IdNo);
		p_west_center.add(new JLabel("성별")); // **콤보박스로 만들기
		p_west_center.add(tf_Gender);
		p_west_center.add(new JLabel("주소"));
		p_west_center.add(tf_Addr);
		p_west_center.add(new JLabel("계급")); // **콤보박스로 만들기
		p_west_center.add(tf_Job);
		p_west_center.add(new JLabel("연봉"));
		p_west_center.add(tf_Salary);
		p_west_center.setBorder(new TitledBorder("사원 정보입력"));

		// 왼쪽 아래
		JPanel p_west_south = new JPanel();
		p_west_south.setLayout(new GridLayout(1, 4));
		p_west_south.add(btn_Regist);
		p_west_south.add(btn_Modify);
		p_west_south.add(btn_Delete);
		p_west_south.add(btn_Home);

		// 왼쪽 위아래 합체
		p_west.add(p_west_center, BorderLayout.CENTER);
		p_west.add(p_west_south, BorderLayout.SOUTH);

		// 오른쪽 영역
		JPanel p_east = new JPanel();
		p_east.setLayout(new BorderLayout());

		// 오른쪽 위
		JPanel p_east_north = new JPanel();
		p_east_north.add(com_EmpSearch);
		p_east_north.add(tf_EmpSearch);
		p_east_north.setBorder(new TitledBorder("사원 검색"));

		// 오른쪽 아래 (테이블)
		p_east.add(p_east_north, BorderLayout.NORTH);
		p_east.add(new JScrollPane(tableEmployee), BorderLayout.CENTER);

		// 전체 화면에 왼쪽 오른쪽 붙이기
		setLayout(new GridLayout(1, 2));
		add(p_west);
		add(p_east);

	}

	void connectDB() {
		try {
			model = new EmployeeModel();
			System.out.println("직원관리 연결성공");
		} catch (Exception e) {
			System.out.println("직원관리 연결실패");
			e.printStackTrace();
		}
	}

	void eventProc() {

		btn_Regist.addActionListener(this);
		btn_Modify.addActionListener(this);
		btn_Delete.addActionListener(this);
		btn_Home.addActionListener(this);
		tf_EmpSearch.addActionListener(this);

		// 테이블에서 목록 클릭시 왼쪽에 정보 띄어줌
		tableEmployee.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableEmployee.getSelectedRow();
				int col = 0;
				int no = (int) tableEmployee.getValueAt(row, col);
				Employee vo = new Employee();

				try {
					vo = model.selectByPk(no);
				} catch (Exception ex) {
					System.out.println("클릭 이벤트 실패 : " + ex.getMessage());
				}

				tf_EmpNo.setText(String.valueOf(no));
				tf_EmpName.setText(vo.getEmpName());
				tf_IdNo.setText(vo.getIdNo());
				tf_Gender.setText(vo.getGender());
				tf_Addr.setText(vo.getAddr());
				tf_Job.setText(vo.getJob());
				tf_Salary.setText(String.valueOf(vo.getSalary()));
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if (evt == btn_Home) {
			BurgerKing.card.first(BurgerKing.cardPanel);
			BurgerKing.f.setSize(1060, 700);
		} else if (evt == btn_Regist) {
			registEmp();
			searchEmp();
		} else if (evt == tf_EmpSearch) {
			searchEmp();
		} else if (evt == btn_Modify) {
			modifyEmp();
			searchEmp();
		} else if (evt == btn_Delete) {
			fireEmp();
			searchEmp();
		}

	}

	void registEmp() {
		Employee vo = new Employee();
		vo.setEmpName(tf_EmpName.getText());
		vo.setIdNo(tf_IdNo.getText());
		vo.setGender(tf_Gender.getText());
		vo.setAddr(tf_Addr.getText());
		vo.setJob(tf_Job.getText());
		vo.setSalary(Integer.parseInt(tf_Salary.getText()));

		try {
			model.registEmp(vo);
		} catch (Exception e) {
			System.out.println("직원 등록 실패 : " + e.getMessage());
		}
		JOptionPane.showMessageDialog(null, "직원 등록 성공");
	}

	public void searchEmp() {
		int idx = com_EmpSearch.getSelectedIndex();
		String str = tf_EmpSearch.getText();

		try {
			ArrayList data = model.searchEmp(idx, str);
			tb_ModelEmployee.data = data;
			tableEmployee.setModel(tb_ModelEmployee);
			tb_ModelEmployee.fireTableDataChanged();
		} catch (Exception e) {
			System.out.println("검색 실패 : " + e.getMessage());
		}
	}

	void modifyEmp() {
		Employee vo = new Employee();

		vo.setEmpNo(Integer.parseInt(tf_EmpNo.getText()));
		vo.setEmpName(tf_EmpName.getText());
		vo.setIdNo(tf_IdNo.getText());
		vo.setGender(tf_Gender.getText());
		vo.setAddr(tf_Addr.getText());
		vo.setJob(tf_Job.getText());
		vo.setSalary(Integer.parseInt(tf_Salary.getText()));

		try {
			model.modifyEmp(vo);
		} catch (Exception e) {
			System.out.println("직원 정보 수정 실패 : " + e.getMessage());
		}
		JOptionPane.showMessageDialog(null, "직원 정보 수정 성공");
	}

	void fireEmp() {
		int emp_No = Integer.parseInt(tf_EmpNo.getText());

		try {
			model.fireEmp(emp_No);
		} catch (Exception e) {
			System.out.println("삭제실패 : " + e.getMessage());
		}
		JOptionPane.showMessageDialog(null, "삭제완료");

	}

}

// 화면에 테이블 붙이는 메소드
class EmployeeTableModel extends AbstractTableModel {

	ArrayList data = new ArrayList();
	String[] columnNames = { "사원번호", "이름", "주민등록번호", "성별", "주소", "직책", "연봉" };

	// =============================================================
	// 1. 기본적인 TabelModel 만들기
	// 아래 세 함수는 TabelModel 인터페이스의 추상함수인데
	// AbstractTabelModel에서 구현되지 않았기에...
	// 반드시 사용자 구현 필수!!!!

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int col) {
		ArrayList temp = (ArrayList) data.get(row);
		return temp.get(col);
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
}
