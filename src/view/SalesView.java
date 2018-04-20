package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import main.BurgerKing;

public class SalesView extends JPanel implements ActionListener {

	JComboBox com_Year, com_Month, com_Day, com_TypeChoice;
	JButton btn_Home;
	JTextField tf_TotalSales, tf_TotalOrders;
	JRadioButton[] rb_TypeChoice;
	ButtonGroup group;
	// 년 월 일 핸들링 할 배열 선언
	Integer[] strY = new Integer[11]; // 대충 10년 (이건 임의로 설정한값임)
	Integer[] strM = new Integer[12]; // 월 12달
	String[] strD = new String[31]; // 1달 = 최대 31일
	// 캘린더는 인터페이스여서 객체생성 이렇게
	Calendar c = Calendar.getInstance(); // calendar class 사용하기 위해

	int[] lastDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public SalesView() {
		addLayout();
		initStyle();
		// connentDB();
		eventProc();
	}

	public void initStyle() {
	}

	void addLayout() {

		CalendarTest();

		btn_Home = new JButton("홈으로");
		tf_TotalSales = new JTextField(15);
		tf_TotalOrders = new JTextField(15);

		// 위쪽
		JPanel p_north = new JPanel();
		p_north.setLayout(new BorderLayout());
		// 위>왼쪽
		JPanel p_north_west = new JPanel();
		p_north_west.setLayout(new GridLayout(2, 1));
		// 위>왼쪽>위
		JPanel p_north_west_north = new JPanel();
		p_north_west_north.add(com_Year);
		p_north_west_north.add(new JLabel("년"));
		p_north_west_north.add(com_Month);
		p_north_west_north.add(new JLabel("월"));
		p_north_west_north.add(com_Day);
		p_north_west_north.add(new JLabel("일"));
		// 위>왼쪽>아래
		JPanel p_north_west_south = new JPanel();
		p_north_west_south.setLayout(new GridLayout(1, 2));
		// 라디오 버튼 패널
		JPanel p_rbp = new JPanel();
		p_rbp.setLayout(new GridLayout(1, 3));
		
		String[] rbTypeChoice = { "일", "월", "년" };
		group = new ButtonGroup();
		rb_TypeChoice = new JRadioButton[3];
		for (int i = 0; i < rb_TypeChoice.length; i++) {
			rb_TypeChoice[i] = new JRadioButton(rbTypeChoice[i]);
			group.add(rb_TypeChoice[i]);
			p_rbp.add(rb_TypeChoice[i]);
		}

		p_north_west_south.add(btn_Home);
		p_north_west_south.add(p_rbp);
		// 위>왼쪽>위아래>합체
		p_north_west.add(p_north_west_north);
		p_north_west.add(p_north_west_south);
		// 위>오른쪽
		JPanel p_north_east = new JPanel();
		p_north_east.setLayout(new GridLayout(2, 2));
		p_north_east.add(new JLabel("총매출"));
		p_north_east.add(new JLabel("주문 건수"));
		p_north_east.add(tf_TotalSales);
		p_north_east.add(tf_TotalOrders);
		p_north_east.setBorder(new TitledBorder("매출 관리"));

		p_north.add(p_north_west, BorderLayout.WEST);
		p_north.add(p_north_east, BorderLayout.EAST);

		add(p_north, BorderLayout.NORTH);

	}

	// void connectDB() {
	// try {
	// model = new SalesModel();
	// System.out.println("매출관리 연결성공");
	// } catch (Exception e) {
	// System.out.println("매출관리 연결실패");
	// e.printStackTrace();
	// }
	// }

	void eventProc() {
		com_Year.addActionListener(this);
		com_Month.addActionListener(this);
		com_Day.addActionListener(this);
		btn_Home.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();

		if (evt == com_Month || evt == com_Year) {
			setDay();
		} else if (evt == btn_Home) {
			BurgerKing.card.first(BurgerKing.cardPanel);
			BurgerKing.f.setSize(1050, 700);
		}

	}

	void CalendarTest() {
		// 1~12 월들의 배열에 숫자 레이블 넣기 == 콤보박스 눌렀을때 택스트들
		for (int i = 0; i < strM.length; i++) {
			strM[i] = i + 1; // ex) strM[0] = 1;
		}

		// 현재 년도의 -5년 ~ +5년
		for (int i = 0, y = c.get(Calendar.YEAR) - 10; i < strY.length; i++, y++) {
			strY[i] = y; // strY[0] = 2018-10;
		}

		// 뒤에서 만든 객체 생성
		com_Year = new JComboBox(strY);
		com_Month = new JComboBox(strM);
		com_Day = new JComboBox();

		setDay(); // 콤보박스의 년도와 월이 있으면 그 해당 월에 마지막 날짜 까지 day 콤보박스 label 생성

		// 프로그램을 실행 하였을때 콤보박스의 초기 값을 컴퓨터상 오늘 날자로 셋팅
		com_Year.setSelectedItem(c.get(Calendar.YEAR));
		com_Month.setSelectedItem(c.get(Calendar.MONTH));
		com_Day.setSelectedItem(c.get(Calendar.DATE));
	}

	void setDay() {
		// 예외처리 부분, 윤년 계산
		int year = (Integer) com_Year.getSelectedItem();
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			lastDays[1] = 29; // 윤년에 해당하면 2월의 마지막날 29
		} else {
			lastDays[1] = 28; // 윤년이 아니라면 28
		}
		int month = com_Month.getSelectedIndex();
		com_Day.removeAllItems();
		// 선택 월의 마지막 날짜까지 cbD 의 label을 채움
		for (int i = 1; i <= lastDays[month]; i++) {
			com_Day.addItem(i);
		}
	}

}
