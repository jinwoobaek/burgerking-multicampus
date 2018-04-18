package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class EmployeeView extends JPanel implements ActionListener {
	JTextField tf_EmpNo, tf_EmpName, tf_IdNo, tf_Gender, tf_Addr, tf_Job, tf_Salary, tf_Home, tf_EmpNoSearch;

	JButton btn_Regist, btn_Modify, btn_Delete, btn_Home, btn_EmpNoSearch, btn_EmpNameSearch;

	JTable tableEmployee;

	public EmployeeView() {
		addLayout(); // 화면설계
		eventProc();
	}

	public void addLayout() {
		tf_EmpNo = new JTextField();
		tf_EmpName = new JTextField();
		tf_IdNo = new JTextField();
		tf_Gender = new JTextField();
		tf_Addr = new JTextField();
		tf_Job = new JTextField();
		tf_Salary = new JTextField();
		tf_Home = new JTextField();
		tf_EmpNoSearch = new JTextField();

		btn_Regist = new JButton();
		btn_Modify = new JButton();
		btn_Delete = new JButton();
		btn_Home = new JButton();
		btn_EmpNoSearch = new JButton();
		btn_EmpNameSearch = new JButton();
		
		setLayout(new GridLayout(1,2));
		// ************화면구성************
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
		p_west_center.add(new JLabel("성별")); // 콤보박스로 만들기
		p_west_center.add(tf_Gender);
		p_west_center.add(new JLabel("주소"));
		p_west_center.add(tf_Addr);
		p_west_center.add(new JLabel("계급"));
		p_west_center.add(tf_Job);
		p_west_center.add(new JLabel("연봉"));
		p_west_center.add(tf_Salary);

		// 왼쪽 아래
		JPanel p_west_south = new JPanel();
		p_west_south.setLayout(new GridLayout(1, 3));
		p_west_south.add(btn_Regist);
		p_west_south.add(btn_Modify);
		p_west_south.add(btn_Delete);

		p_west.add(p_west_center, BorderLayout.CENTER);
		p_west.add(p_west_south, BorderLayout.SOUTH);

		//JPanel p_east = new JPanel();
		
		//p_east.add(p_west_center);

		add(p_west, BorderLayout.WEST);
		//add(p_east, BorderLayout.EAST);
		// // 왼쪽 가운데의 가운데
		// JPanel p_west_center_center = new JPanel();
		// p_west_center_center.setLayout(new BorderLayout());
		// // BorderLayout은 영역 설정도 해야함
		// p_west_center_center.add(new JLabel("설명"), BorderLayout.WEST);
		// p_west_center_center.add(taVideoContent, BorderLayout.CENTER);

		// 왼쪽 화면에 붙이기
		// p_west_center.add(p_west_center_north, BorderLayout.NORTH);
		// p_west_center.add(p_west_center_center, BorderLayout.CENTER);
		// p_west_center.setBorder(new TitledBorder("회원 정보입력"));

	}

	public void eventProc() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();

	}

}
