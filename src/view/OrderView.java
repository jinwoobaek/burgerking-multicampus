package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

import main.BurgerKing;
import model.MenuModel;
import model.OrderModel;

public class OrderView extends JPanel implements ActionListener {

	JTabbedPane tab_Menu;
	JPanel pane_Order_Main, pane_Right, pane_Left, pane_BurgerMenu, pane_BeverageMenu, pane_SideMenu;
	JScrollPane jsp_tabpane;
	JTextField tf_subtotal, tf_discount, tf_total;
	String[] text = new String[10];
	JTextArea selectedItems;
	String temp;
	JOptionPane jOption;
	JLabel lblNewLabel, lb_Discount, lb_Subtotal, lb_Sum, lb_Received, lb_Change;
	JTable tableOrder;
	OrderTableModel tb_ModelOrder;
	JScrollPane jsp_table;
	OrderModel model;
	ArrayList<ArrayList> data;

	MenuAddView menuView;
	MenuModel m_Model;
	ArrayList<ArrayList> menuList;

	int subtotal;

	int dcafter;
	int count = 4;
	// Item items[]=new Item[count]; //상품배열
	ArrayList<JLabel> burgerList = new ArrayList<JLabel>(); // 버거버튼배열
	ArrayList<JLabel> beverageList = new ArrayList<JLabel>(); // 음료버튼 배열
	ArrayList<JLabel> sideList = new ArrayList<JLabel>(); // 사이드버튼 배열

	JLabel btnpay, btndiscount, btnadd, btncancel, bhome;
	String[] cardOption = new String[3];
	String[] paymentOption = new String[6];
	JTextField tf_receivedMoney, tf_change;

	public OrderView() {

		addLayout();
		evntProc();
		connectDB();

	}

	void evntProc() {

		for (int i = 0; i < burgerList.size(); i++) {
			burgerList.get(i).addMouseListener(mlstnr);
		}

		for (int i = 0; i < beverageList.size(); i++) {
			beverageList.get(i).addMouseListener(mlstnr);
		}

		for (int i = 0; i < sideList.size(); i++) {
			sideList.get(i).addMouseListener(mlstnr);
		}

		tf_receivedMoney.addMouseListener(mlstnr);
		btnadd.addMouseListener(mlstnr);
		btnpay.addMouseListener(mlstnr);
		btncancel.addMouseListener(mlstnr);
		btndiscount.addMouseListener(mlstnr);
		bhome.addMouseListener(mlstnr);

	}

	void connectDB() {
		try {
			model = new OrderModel();
			data = new ArrayList();
			System.out.println("주문관리 연결성공");
		} catch (Exception e) {
			System.out.println("주문관리 연결실패");
			e.printStackTrace();
		}
	}

	void addLayout() { // 화면 구성***************

		setLayout(new BorderLayout());
		temp = "";
		pane_Order_Main = new JPanel();
		pane_Order_Main.setBackground(new Color(187, 34, 26));
		pane_Order_Main.setLayout(null);
		tab_Menu = new JTabbedPane();
		pane_Left = new JPanel(new BorderLayout());
		pane_Right = new JPanel();
		pane_BurgerMenu = new JPanel();
		pane_BeverageMenu = new JPanel();
		pane_SideMenu = new JPanel();
		pane_Order_Main.add(pane_Left);

		pane_Left.setBounds(12, 10, 380, 744);
		pane_BurgerMenu.setLayout(new GridLayout(3, 3, 0, 0));
		pane_BeverageMenu.setLayout(new GridLayout(3, 3, 0, 0));
		pane_SideMenu.setLayout(new GridLayout(3, 3, 0, 0));

		pane_Left.add(tab_Menu, BorderLayout.CENTER);

		tab_Menu.setOpaque(true);
		tab_Menu.setBackground(new Color(187, 34, 26));
		tab_Menu.setForeground(new Color(232, 75, 0));
		tab_Menu.addTab("버거류", pane_BurgerMenu);
		pane_BurgerMenu.setBackground(new Color(146, 21, 15));
		tab_Menu.addTab("음료류", pane_BeverageMenu);
		pane_BeverageMenu.setBackground(new Color(146, 21, 15));
		tab_Menu.addTab("사이드", pane_SideMenu);
		pane_SideMenu.setBackground(new Color(146, 21, 15));

		try {
			menuList = new ArrayList();
			m_Model = new MenuModel();

			menuList = m_Model.refreshMenu();
		} catch (Exception e) {
			System.out.println("메뉴추가 실패: "+e.getMessage());
			e.printStackTrace();
		}

		for (int i = 0; i < menuList.size(); i++) {
			JLabel temp = new JLabel(menuList.get(i).get(0).toString());
			temp.setForeground(new Color(0, 0, 0, 0));
			temp.setIcon(getIcon(menuList.get(i).get(1).toString(), 170, 220));
			int categori = Integer.parseInt(menuList.get(i).get(2).toString());
			switch (categori) {
			case 100:
				burgerList.add(temp);
				pane_BurgerMenu.add(temp);
				break;
			case 200:
				beverageList.add(temp);
				pane_BeverageMenu.add(temp);
				break;
			case 300:
				sideList.add(temp);
				pane_SideMenu.add(temp);
				break;
			}
		}

		pane_Right.setBounds(408, 30, 406, 724);
		pane_Right.setBackground(new Color(146, 21, 15));
		pane_Right.setBorder(new LineBorder(new Color(204, 204, 204), 2));
		pane_Order_Main.add(pane_Right);
		pane_Right.setLayout(null);

		lb_Subtotal = new JLabel("소계");
		lb_Subtotal.setForeground(new Color(255, 160, 20));
		lb_Subtotal.setBounds(94, 456, 70, 27);
		pane_Right.add(lb_Subtotal);

		lb_Discount = new JLabel("할인");
		lb_Discount.setForeground(new Color(255, 160, 20));
		lb_Discount.setBounds(94, 479, 60, 27);
		pane_Right.add(lb_Discount);

		lb_Sum = new JLabel("합계");
		lb_Sum.setForeground(new Color(255, 160, 20));
		lb_Sum.setBounds(94, 518, 70, 27);
		pane_Right.add(lb_Sum);

		tf_subtotal = new JTextField();
		tf_subtotal.setBounds(166, 456, 149, 27);
		pane_Right.add(tf_subtotal);
		tf_subtotal.setColumns(10);

		tf_discount = new JTextField();
		tf_discount.setColumns(10);
		tf_discount.setBounds(166, 481, 149, 27);
		pane_Right.add(tf_discount);

		tf_total = new JTextField();
		tf_total.setColumns(10);
		tf_total.setBounds(166, 516, 149, 27);
		pane_Right.add(tf_total);

		JPanel orderlist = new JPanel();
		orderlist.setLayout(new BorderLayout());
		tb_ModelOrder = new OrderTableModel();
		tableOrder = new JTable(tb_ModelOrder);
		orderlist.add(new JScrollPane(tableOrder), BorderLayout.CENTER);

		pane_Right.add(orderlist);
		orderlist.setBounds(0, 0, 406, 386);

		tf_receivedMoney = new JTextField();
		tf_receivedMoney.setColumns(10);
		tf_receivedMoney.setBounds(166, 548, 149, 27);
		pane_Right.add(tf_receivedMoney);

		tf_change = new JTextField();
		tf_change.setColumns(10);
		tf_change.setBounds(166, 576, 149, 27);
		pane_Right.add(tf_change);

		lb_Received = new JLabel("   받은 돈");
		lb_Received.setForeground(new Color(255, 160, 20));
		lb_Received.setBounds(53, 539, 111, 40);
		pane_Right.add(lb_Received);

		lb_Change = new JLabel("거스름 돈");
		lb_Change.setForeground(new Color(255, 160, 20));
		lb_Change.setBounds(53, 566, 121, 40);
		pane_Right.add(lb_Change);

		btnpay = new JLabel();
		btnpay.setIcon(getIcon("결제.png", 107, 40));
		btnpay.setBounds(160, 624, 107, 40);
		pane_Right.add(btnpay);

		btndiscount = new JLabel();
		btndiscount.setIcon(getIcon("할인적용.png", 107, 40));
		btndiscount.setBounds(40, 624, 107, 40);
		pane_Right.add(btndiscount);

		bhome = new JLabel();
		bhome.setIcon(getIcon("홈으로2.png", 107, 40));
		bhome.setBounds(280, 624, 107, 40);
		pane_Right.add(bhome);

		btncancel = new JLabel();
		btncancel.setIcon(getIcon("취소.png", 107, 40));
		btncancel.setBounds(160, 674, 107, 40);
		pane_Right.add(btncancel);

		btnadd = new JLabel();
		btnadd.setIcon(getIcon("메뉴추가.png", 107, 40));
		btnadd.setBounds(40, 674, 107, 40);
		pane_Right.add(btnadd);
		add(pane_Order_Main);

		cardOption[0] = "SKT";
		cardOption[1] = "LG u+";
		cardOption[2] = "KT ohlle";
		paymentOption[0] = "현금";
		paymentOption[1] = "카드";
		paymentOption[2] = "삼성페이";
		paymentOption[3] = "카카오페이";
		paymentOption[4] = "PAYCO";
		paymentOption[5] = "Paynow";

	}

	void discount(int option) {
		dcafter = (int) (subtotal * 0.8);
		tf_discount.setText(Integer.toString((subtotal - dcafter)));
		tf_total.setText(Integer.toString((dcafter)));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();

		if (evt == btnpay) { // 결제버튼 누르면
			if (dcafter == 0)
				tf_total.setText(Integer.toString(subtotal));

			if (tf_receivedMoney.getText().length() > 0) {
				if (Integer.parseInt(tf_total.getText()) <= Integer.parseInt(tf_receivedMoney.getText())) {
					tf_change.setText(Integer.toString(
							Integer.parseInt(tf_receivedMoney.getText()) - Integer.parseInt(tf_total.getText())));
				}
			}

			int option = JOptionPane.showOptionDialog(null, "결제방식 선택", "", JOptionPane.OK_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, paymentOption, null);

			int count = 0;
			for (int i = 0; i < data.size(); i++) { // 선택 항목 수 계산
				count += Integer.parseInt(data.get(i).get(2).toString());
			}
			count = count - 1;
			String totalPrice = "";

			if (tf_total.getText().length() < 2)
				totalPrice = tf_subtotal.getText();
			else
				totalPrice = tf_total.getText();

			try {
				model.orderHistory(data.get(0).get(0).toString(), count, totalPrice);

			} catch (Exception e1) {
				System.out.println("결제실패: " + e1.getMessage());
				e1.printStackTrace();
			}

			tf_subtotal.setText(null);
			tf_discount.setText(null);
			tf_total.setText(null);
			tf_change.setText(null);
			tf_receivedMoney.setText(null);
			dcafter = 0;
			data.clear();
			tb_ModelOrder.data = data;
			tableOrder.setModel(tb_ModelOrder);
			tb_ModelOrder.fireTableDataChanged();

			return;

		} else if (evt == btndiscount) { // 할인 누르면
			int option = JOptionPane.showOptionDialog(null, "맴버십선택", "", JOptionPane.OK_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, cardOption, null);

			discount(option);
			return;
		} else if (evt == btncancel) {

			subtotal = 0;
			dcafter = 0;
			tf_subtotal.setText(null);
			tf_discount.setText(null);
			tf_total.setText(null);
			tf_change.setText(null);
			tf_receivedMoney.setText(null);
			data.clear();
			tb_ModelOrder.data = data;
			tableOrder.setModel(tb_ModelOrder);
			tb_ModelOrder.fireTableDataChanged();

			for (int i = 0; i < text.length; i++) {
				text[i] = "";
			}

			return;
		} else if (evt == bhome) {

			subtotal = 0;
			dcafter = 0;
			tf_subtotal.setText(null);
			tf_discount.setText(null);
			tf_total.setText(null);
			tf_change.setText(null);
			tf_receivedMoney.setText(null);
			data.clear();

			BurgerKing.card.first(BurgerKing.cardPanel);
			BurgerKing.f.setSize(1060, 700);
		} else if (evt == tf_receivedMoney) {
			int receivedMoney = Integer.parseInt(tf_receivedMoney.getText());

			if (tf_total.getText().length() == 0) {
				int change = receivedMoney - Integer.parseInt(tf_subtotal.getText());
				tf_change.setText(String.valueOf(change));
			} else {
				int change = receivedMoney - Integer.parseInt(tf_total.getText());
				tf_change.setText(String.valueOf(change));
			}

		} else if (evt == btnadd) {
			menuView = new MenuAddView();
		}

	}

	MouseListener mlstnr = new MouseAdapter() {

		@Override
		public void mouseClicked(MouseEvent e) {
			Object evt = e.getSource();

	try {
		if(burgerList.contains(evt)||beverageList.contains(evt)||sideList.contains(evt)){

				ArrayList temp = new ArrayList();
				if (burgerList.contains(evt)) { // 버거메뉴이면

					for (int inx = 0; inx < burgerList.size(); inx++) {
						if (evt == burgerList.get(inx)) {

							temp = model.getMenuInfo(burgerList.get(inx).getText()); // 해당메뉴의
																						// 데이터를
																						// DB에서
																						// 얻어옴

							for (int i = 0; i < data.size(); i++) { // 이전 선택 목록
																	// 중

								if ((data.get(i)).contains(temp.get(0))) { // 같은
																			// 메뉴를
																			// 선택한
																			// 이력이
																			// 있으면
									data.get(i).set(2, Integer.parseInt(data.get(i).get(2).toString()) + 1); // 수량을
																												// 1
																												// 늘려줌
									data.get(i).set(3, Integer.parseInt(data.get(i).get(1).toString())
											* Integer.parseInt(data.get(i).get(2).toString())); // 단가와
																								// 갯수를
																								// 곱하여
																								// 금액컬럼에
																								// 저장

									subtotal = 0; // 소계 계산
									for (int j = 0; j < data.size(); j++) {
										subtotal = subtotal + Integer.parseInt(data.get(j).get(3).toString());
									}
									tf_subtotal.setText(Integer.toString(subtotal));

									tb_ModelOrder.data = data;
									tableOrder.setModel(tb_ModelOrder);
									tb_ModelOrder.fireTableDataChanged();

									return; // 목록에 추가하지 않고 수량만 1 늘리고 종료

								}
							}
						}

					}

				} else if (beverageList.contains(evt)) { // 음료메뉴이면

					for (int inx = 0; inx < beverageList.size(); inx++) {
						if (evt == beverageList.get(inx)) {

							temp = model.getMenuInfo(beverageList.get(inx).getText()); // 해당메뉴의
																						// 데이터를
																						// DB에서
																						// 얻어옴

							for (int i = 0; i < data.size(); i++) { // 이전 선택 목록
																	// 중

								if ((data.get(i)).contains(temp.get(0))) { // 같은
																			// 메뉴를
																			// 선택한
																			// 이력이
																			// 있으면
									data.get(i).set(2, Integer.parseInt(data.get(i).get(2).toString()) + 1); // 수량을
																												// 1
																												// 늘려줌
									data.get(i).set(3, Integer.parseInt(data.get(i).get(1).toString())
											* Integer.parseInt(data.get(i).get(2).toString())); // 단가와
																								// 갯수를
																								// 곱하여
																								// 금액컬럼에
																								// 저장

									subtotal = 0; // 소계 계산
									for (int j = 0; j < data.size(); j++) {
										subtotal = subtotal + Integer.parseInt(data.get(j).get(3).toString());
									}
									tf_subtotal.setText(Integer.toString(subtotal));

									tb_ModelOrder.data = data;
									tableOrder.setModel(tb_ModelOrder);
									tb_ModelOrder.fireTableDataChanged();

									return; // 목록에 추가하지 않고 수량만 1 늘리고 종료

								}
							}
						}

					}

				} else if (sideList.contains(evt)) {

					for (int inx = 0; inx < sideList.size(); inx++) {
						if (evt == sideList.get(inx)) {

							temp = model.getMenuInfo(sideList.get(inx).getText()); // 해당메뉴의
																					// 데이터를
																					// DB에서
																					// 얻어옴

							for (int i = 0; i < data.size(); i++) { // 이전 선택 목록
																	// 중

								if ((data.get(i)).contains(temp.get(0))) { // 같은
																			// 메뉴를
																			// 선택한
																			// 이력이
																			// 있으면
									data.get(i).set(2, Integer.parseInt(data.get(i).get(2).toString()) + 1); // 수량을
																												// 1
																												// 늘려줌
									data.get(i).set(3, Integer.parseInt(data.get(i).get(1).toString())
											* Integer.parseInt(data.get(i).get(2).toString())); // 단가와
																								// 갯수를
																								// 곱하여
																								// 금액컬럼에
																								// 저장

									subtotal = 0; // 소계 계산
									for (int j = 0; j < data.size(); j++) {
										subtotal = subtotal + Integer.parseInt(data.get(j).get(3).toString());
									}
									tf_subtotal.setText(Integer.toString(subtotal));

									tb_ModelOrder.data = data;
									tableOrder.setModel(tb_ModelOrder);
									tb_ModelOrder.fireTableDataChanged();

									return; // 목록에 추가하지 않고 수량만 1 늘리고 종료

								}
							} // 이전에 같은 메뉴를 선택한 이력이 없으면
						}

					}

				}

				temp.add("1"); // 제품명,단가를 버퍼에 저장 후 갯수정보 1을 저장
				temp.add(temp.get(1).toString()); // 선택갯수 1개이므로 단가 = 금액
				data.add(temp);
				tb_ModelOrder.data = data;
				tableOrder.setModel(tb_ModelOrder);
				tb_ModelOrder.fireTableDataChanged();


			subtotal = 0;
			for (int i = 0; i < data.size(); i++) { // 소계 계산
				subtotal = subtotal + Integer.parseInt(data.get(i).get(3).toString());
			}
			tf_subtotal.setText(Integer.toString(subtotal));
			
			
	}else
				
			if (evt == btnpay) { // 결제버튼 누르면
				if (dcafter == 0)
					tf_total.setText(Integer.toString(subtotal));

				if (tf_receivedMoney.getText().length() > 0) {
					if (Integer.parseInt(tf_total.getText()) <= Integer.parseInt(tf_receivedMoney.getText())) {
						tf_change.setText(Integer.toString(
								Integer.parseInt(tf_receivedMoney.getText()) - Integer.parseInt(tf_total.getText())));
					}
				}

				int option = JOptionPane.showOptionDialog(null, "결제방식 선택", "", JOptionPane.OK_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, paymentOption, null);

				int count = 0;
				for (int i = 0; i < data.size(); i++) { // 선택 항목 수 계산
					count += Integer.parseInt(data.get(i).get(2).toString());
				}
				count = count - 1;
				String totalPrice = "";

				if (tf_total.getText().length() < 2)
					totalPrice = tf_subtotal.getText();
				else
					totalPrice = tf_total.getText();

				
					model.orderHistory(data.get(0).get(0).toString(), count, totalPrice);

				

				tf_subtotal.setText(null);
				tf_discount.setText(null);
				tf_total.setText(null);
				tf_change.setText(null);
				tf_receivedMoney.setText(null);
				dcafter = 0;
				data.clear();
				tb_ModelOrder.data = data;
				tableOrder.setModel(tb_ModelOrder);
				tb_ModelOrder.fireTableDataChanged();

				return;

			} else if (evt == btndiscount) { // 할인 누르면
				int option = JOptionPane.showOptionDialog(null, "맴버십선택", "", JOptionPane.OK_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, cardOption, null);

				discount(option);
				return;
			} else if (evt == btncancel) {

				subtotal = 0;
				dcafter = 0;
				tf_subtotal.setText(null);
				tf_discount.setText(null);
				tf_total.setText(null);
				tf_change.setText(null);
				tf_receivedMoney.setText(null);
				data.clear();
				tb_ModelOrder.data = data;
				tableOrder.setModel(tb_ModelOrder);
				tb_ModelOrder.fireTableDataChanged();

				for (int i = 0; i < text.length; i++) {
					text[i] = "";
				}

				return;
			} else if (evt == bhome) {

				subtotal = 0;
				dcafter = 0;
				tf_subtotal.setText(null);
				tf_discount.setText(null);
				tf_total.setText(null);
				tf_change.setText(null);
				tf_receivedMoney.setText(null);
				data.clear();

				BurgerKing.card.first(BurgerKing.cardPanel);
				BurgerKing.f.setSize(1060, 700);
			} else if (evt == tf_receivedMoney) {
				int receivedMoney = Integer.parseInt(tf_receivedMoney.getText());

				if (tf_total.getText().length() == 0) {
					int change = receivedMoney - Integer.parseInt(tf_subtotal.getText());
					tf_change.setText(String.valueOf(change));
				} else {
					int change = receivedMoney - Integer.parseInt(tf_total.getText());
					tf_change.setText(String.valueOf(change));
				}

			} else if (evt == btnadd) {
				menuView = new MenuAddView();
			}

			} catch (Exception e1) {
				System.out.println("POS오류: " + e1.getMessage());
				e1.printStackTrace();
			}
			
			
			

		}
	};

	public ImageIcon getIcon(String name, int width, int height) {
		return new ImageIcon(
				new ImageIcon("src\\img\\" + name).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}

}

class OrderTableModel extends AbstractTableModel {

	ArrayList data = new ArrayList();
	String[] columnNames = { "제품명", "단가", "수량", "금액" };

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
