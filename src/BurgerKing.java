import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BurgerKing implements ActionListener {

	JFrame f;
	public static CardLayout card;
	JPanel btnPanel, cardPanel, testPanel;
	JButton btnOrderView, btnStockView, btnSalesView, btnEmployeeView;

	public BurgerKing() {
		addLayout();
		eventProc();
	}

	public static void main(String[] args) {
		new BurgerKing();
	}

	void eventProc() {
		btnOrderView.addActionListener(this);
		btnStockView.addActionListener(this);
		btnSalesView.addActionListener(this);
		btnSalesView.addActionListener(this);
	}

	void addLayout() {
		f = new JFrame(); // 메인 프레임
		card = new CardLayout();
		btnPanel = new JPanel(); // 버튼패널
		btnOrderView = new JButton("주문");
		btnStockView = new JButton("재고");
		btnSalesView = new JButton("매출");
		btnEmployeeView = new JButton("직원");

		btnPanel.setLayout(new GridLayout(4, 1, 5, 5));
		btnPanel.add(btnOrderView);
		btnPanel.add(btnStockView);
		btnPanel.add(btnSalesView);
		btnPanel.add(btnEmployeeView);
		btnPanel.setSize(20, 15);

		cardPanel = new JPanel(card);
		cardPanel.add(btnPanel, "btnPanel");

		f.add(cardPanel);

		f.setSize(800, 600);
		f.setVisible(true);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object evt = e.getSource();

		if (evt == btnOrderView) {
			card.show(cardPanel, "pan");
		} else if (evt == btnStockView) {

		} else if (evt == btnSalesView) {

		} else if (evt == btnEmployeeView) {

		}

	}

}
