package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalTabbedPaneUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import main.BurgerKing;
import model.EmployeeModel;
import model.OrderModel;

public class OrderView extends JPanel implements ActionListener {

	JTabbedPane tab_Menu;
	JPanel pane_Order_Main, pane_Right, pane_Left, pane_BurgerMenu, pane_BeverageMenu;
	JScrollPane jsp_tabpane;
	JTextField tf_subtotal, tf_discount, tf_total;
	String[] text = new String[10];
	JTextArea selectedItems;
	String temp;
	JOptionPane jOption;
	JLabel btnNewButton, btnNewButton2, btnNewButton3, btnNewButton4;
	JLabel lblNewLabel, lb_Discount, lb_Subtotal, lb_Sum, lb_Received, lb_Change;
	JTable tableOrder;
	OrderTableModel tb_ModelOrder;
	JScrollPane jsp_table;
	OrderModel model;
	ArrayList data;

	int dcafter;
	int subtotal = 0;
	int count = 4;
	// Item items[]=new Item[count]; //상품배열
	JLabel[] itembtn = new JLabel[count]; // 버튼배열

	JButton btnpay, btndiscount, btnadd, btncancle, bhome;
	String[] cardOption = new String[3];
	String[] paymentOption = new String[6];
	JTextField tf_receivedMoney, tf_change;

	public OrderView() {

		addLayout();
		evntProc();
		connectDB();

	}

	void evntProc() {
		 itembtn[0].addMouseListener(mlstnr);
//		 btnNewButton.addMouseListener(mlstnr);
//		 itembtn[1].addActionListener(mlstnr);
//		 itembtn[2].addActionListener(this);
//		 itembtn[3].addActionListener(this);
//		

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
		pane_Order_Main.add(pane_Left);

		pane_Left.setBounds(12, 10, 380, 744);
		pane_BurgerMenu.setLayout(new GridLayout(3, 3, 0, 0));

		pane_Left.add(tab_Menu, BorderLayout.CENTER);

		tab_Menu.setOpaque(true);
		tab_Menu.setBackground(new Color(187, 34, 26));
		tab_Menu.addTab("버거류", pane_BurgerMenu);
		pane_BurgerMenu.setBackground(new Color(146, 21, 15));

		btnNewButton = new JLabel("트러플콰트로머쉬룸버거");
		btnNewButton.setForeground(new Color(0, 0, 0,0));
		pane_BurgerMenu.add(btnNewButton);
		itembtn[0] = btnNewButton;
		btnNewButton.setIcon(getIcon("트러플콰트로", 170, 220));
		// items[0]=new Item(itembtn[0].getText(),3800,0);

		btnNewButton2 = new JLabel();
		pane_BurgerMenu.add(btnNewButton2);
		itembtn[1] = btnNewButton2;
		btnNewButton2.setIcon(getIcon("트러플콰트로", 170, 220));

		btnNewButton3 = new JLabel();
		pane_BurgerMenu.add(btnNewButton3);
		itembtn[2] = btnNewButton3;
		btnNewButton3.setIcon(getIcon("트러플콰트로", 170, 220));

		btnNewButton4 = new JLabel();
		pane_BurgerMenu.add(btnNewButton4);
		itembtn[3] = btnNewButton4;
		btnNewButton4.setIcon(getIcon("트러플콰트로", 170, 220));

		pane_Right = new JPanel();
		pane_Right.setBounds(408, 30, 406, 724);
		pane_Right.setBackground(new Color(146, 21, 15));
		pane_Right.setBorder(new LineBorder(new Color(204, 204, 204),2));
		pane_Order_Main.add(pane_Right);
		pane_Right.setLayout(null);

		lb_Subtotal = new JLabel("소계");
		lb_Subtotal.setBounds(94, 456, 70, 27);
		pane_Right.add(lb_Subtotal);

		lb_Discount = new JLabel("할인");
		lb_Discount.setBounds(94, 479, 60, 27);
		pane_Right.add(lb_Discount);

		lb_Sum = new JLabel("합계");
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
		lb_Received.setBounds(53, 539, 111, 40);
		pane_Right.add(lb_Received);

		lb_Change = new JLabel("거스름 돈");
		lb_Change.setBounds(53, 566, 121, 40);
		pane_Right.add(lb_Change);

		btnpay = new JButton("결제");
		btnpay.setBounds(160, 624, 107, 40);
		btnpay.addActionListener(this);
		pane_Right.add(btnpay);

		btndiscount = new JButton("할인적용");
		btndiscount.setBounds(40, 624, 107, 40);
		btndiscount.addActionListener(this);
		pane_Right.add(btndiscount);

		bhome = new JButton("홈으로");
		bhome.setBounds(280, 624, 107, 40);
		bhome.addActionListener(this);
		pane_Right.add(bhome);

		btncancle = new JButton("취소");
		btncancle.setBounds(160, 674, 107, 40);
		btncancle.addActionListener(this);
		pane_Right.add(btncancle);

		btnadd = new JButton("메뉴추가");
		btnadd.setBounds(40, 674, 107, 40);
		btnadd.addActionListener(this);
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

	int subtotal(int prices) {
		subtotal += prices;
		return subtotal;
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

			tf_subtotal.setText(null);
			tf_discount.setText(null);
			tf_total.setText(null);
			selectedItems.setText(null);
			tf_change.setText(null);
			tf_receivedMoney.setText(null);

			dcafter = 0;
			subtotal = 0;

			for (int i = 0; i < text.length; i++) {
				text[i] = "";
			}

			// for(int i =0;i<items.length;i++){
			// if(items[i]!=null)
			// items[i].initialize();
			// }
			return;

		} else if (evt == btndiscount) { // 할인 누르면
			int option = JOptionPane.showOptionDialog(null, "맴버십선택", "", JOptionPane.OK_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, cardOption, null);

			discount(option);
			return;
		} else if (evt == btncancle) {

			tf_subtotal.setText(null);
			tf_discount.setText(null);
			tf_total.setText(null);
			selectedItems.setText(null);
			tf_change.setText(null);
			tf_receivedMoney.setText(null);

			// Item.clickOrderCount=0;
			dcafter = 0;
			subtotal = 0;

			for (int i = 0; i < text.length; i++) {
				text[i] = "";
			}

			// for(int i =0;i<items.length;i++){
			// if(items[i]!=null)
			// items[i].initialize();
			// }
			return;
		} else if (evt == bhome) {
			BurgerKing.card.first(BurgerKing.cardPanel);
			BurgerKing.f.setSize(1050, 700);
		}
//
//		for (int i = 0; i < count; i++) { // 메뉴 중 하나 누르면
//			if (evt == itembtn[i]) {
//				// int subresult = subtotal(items[i].getPrice());
//				// tf_subtotal.setText(Integer.toString(subresult));
//				// 소계 누적계산
//
//				// items[i].btnCount+=1; // 동일 메뉴 누른 횟수 누적
//				//
//				// if(items[i].exist==true){ // 이전에 동일한 메뉴를 누른적이 있는 경우
//				// text[items[i].clickOrder]=
//				// (String.format("%13s\t%d\t%2d %6d\n",
//				// items[i].getName(),items[i].getPrice(),items[i].getBtnCount(),
//				// (items[i].getPrice()*items[i].getBtnCount())));
//				break;
//			} else { // 누른 메뉴가 이전에 없는경우
//				//
//				// text[Item.clickOrderCount]=
//				// (String.format("%13s\t%d\t%2d %6d\n",
//				// items[i].getName(),items[i].getPrice(),items[i].getBtnCount(),
//				// (items[i].getPrice()*items[i].getBtnCount())));
//				// items[i].clickOrder=Item.clickOrderCount;
//				// items[i].exist=true;
//				// Item.clickOrderCount++;
//				break;
//			}
//		}
		// }

		temp = "";
		for (int i = 0; i < text.length; i++) {
			if (text[i] != null) {
				temp += text[i];
			}
		}
//		selectedItems.setText(null);
//		selectedItems.setText(temp);

	}

	MouseListener mlstnr = new MouseAdapter() {

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			Object evt= e.getSource();
			
			if(evt==itembtn[0]){
				try {
					ArrayList temp = new ArrayList();
					temp= model.addMenuTabel(itembtn[0].getText());
					temp.add("1");
					temp.add(temp.get(1).toString());
					
					if(data.contains(temp.get(0).toString())){
						
						
					}
					
					
					data.add(temp);
					tb_ModelOrder.data= data;
					tableOrder.setModel(tb_ModelOrder);
					tb_ModelOrder.fireTableDataChanged();
					System.out.println("test");
					
					
					
				} catch (Exception e1) {
					System.out.println("POS오류: " +e1.getMessage());
					e1.printStackTrace();
				}
				
			}
			
			
		}
	};

	public ImageIcon getIcon(String name, int width, int height) {
		return new ImageIcon(new ImageIcon("src\\img\\" + name + ".png").getImage().getScaledInstance(width, height,
				Image.SCALE_DEFAULT));
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
