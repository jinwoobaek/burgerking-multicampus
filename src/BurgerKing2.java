import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;

public class BurgerKing2 extends JFrame {

	JFrame f;
	CardLayout card;
	JPanel btnPanel;
	JButton btnOrderView, btnStockView, btnSalesView, btnEmployeeView;

	public BurgerKing2() {

		f = new JFrame(); // 메인 프레임
		card = new CardLayout(); 
		btnPanel = new JPanel(); // 버튼패널
		btnOrderView = new JButton("주문"); 
		btnStockView = new JButton("재고"); 
		btnSalesView = new JButton("매출");
		btnEmployeeView = new JButton("직원");
		
		
		btnPanel.setLayout(new GridLayout(4,1,5,5));
		btnPanel.add(btnOrderView);
		btnPanel.add(btnStockView);
		btnPanel.add(btnSalesView);
		btnPanel.add(btnEmployeeView);
		btnPanel.setSize(20,15);
		
		
		f.setLayout(card);
		f.add(btnPanel);
		

		f.setSize(800, 600);
		f.setVisible(true);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new BurgerKing2();
	}

}
