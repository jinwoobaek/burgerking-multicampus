package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import main.BurgerKing;
import model.StockModel;
import vo.Employee;
import vo.Stock;

public class StockView extends JPanel implements ActionListener {

	JTextField tf_StockNo, tf_StockName, tf_Amount, tf_ValidPeriod, tf_EnteringDate;
	JLabel btn_Import, btn_Export, btn_Home;
	JLabel lb_StockNo, lb_StockName, lb_Amount, lb_ValidPeriod, lb_EnteringDate,top_Img;
	JPanel wholePanel;

	JComboBox com_StockSearch;
	JTextField tf_StockSearch;

	TitledBorder border;
	JTable tableStock;

	StockTableModel tb_ModelStock;

	StockModel model;

	public StockView() {
		addLayout(); // 화면설계
		initStyle();
		connectDB(); // db 연결
		eventProc();
	}

	public void initStyle() {
		tf_StockNo.setEditable(false);
		tf_EnteringDate.setEditable(false);
	}

	public void addLayout() {
		tf_StockNo = new JTextField();
		tf_StockName = new JTextField();
		tf_ValidPeriod = new JTextField();
		tf_EnteringDate = new JTextField();
		tf_Amount = new JTextField();
		lb_StockNo = new JLabel("제품코드");
		lb_StockNo.setForeground(new Color(255, 160, 20));
		lb_StockName = new JLabel("제품명");
		lb_StockName.setForeground(new Color(255, 160, 20));
		lb_Amount= new JLabel("수량");
		lb_Amount.setForeground(new Color(255, 160, 20));
		lb_ValidPeriod = new JLabel("유통기한");
		lb_ValidPeriod.setForeground(new Color(255, 160, 20));
		lb_EnteringDate= new JLabel("입고일");
		lb_EnteringDate.setForeground(new Color(255, 160, 20));
		
		
		border = new TitledBorder("");

		btn_Import = new JLabel();
		btn_Import.setBounds(10, 0, 160, 45);
		btn_Import.setOpaque(true);
		btn_Import.setBackground(Color.BLACK);
		btn_Import.setIcon(getIcon("입고", 160, 45));
		
		
		btn_Export = new JLabel();
		btn_Export.setBounds(180, 0, 160, 45);
		btn_Export.setOpaque(true);
		btn_Export.setBackground(Color.BLACK);
		btn_Export.setIcon(getIcon("삭제", 160, 45));
		
		
		btn_Home = new JLabel();
		btn_Home.setBounds(350, 0, 160, 45);
		btn_Home.setOpaque(true);
		btn_Home.setBackground(Color.BLACK);
		btn_Home.setIcon(getIcon("홈으로", 160, 45));
		
		

		String[] cbStockSearch = { "품명", "제품코드" };
		com_StockSearch = new JComboBox(cbStockSearch);
		tf_StockSearch = new JTextField(15);

		tb_ModelStock = new StockTableModel();
		tableStock = new JTable(tb_ModelStock);
		tableStock.setBackground(Color.WHITE);

		// *********화면 구성*******************************
		// 맨위쪽
		JPanel p_north = new JPanel();
		p_north.setLayout(new GridLayout(1, 2));

		// 맨위>왼쪽
		JPanel p_north_west = new JPanel();
		p_north_west.setLayout(new GridLayout(3, 4,5,5));
		p_north_west.setBackground(Color.WHITE);
		p_north_west.add(lb_StockNo);
		p_north_west.add(tf_StockNo);
		p_north_west.add(lb_StockName);
		p_north_west.add(tf_StockName);
		p_north_west.add(lb_ValidPeriod);
		p_north_west.add(tf_ValidPeriod);
		p_north_west.add(lb_EnteringDate);
		p_north_west.add(tf_EnteringDate);
		p_north_west.add(lb_Amount);
		p_north_west.add(tf_Amount);
		border.setTitle("재고 관리");
		border.setTitleColor(new Color(255, 160, 20));
		p_north_west.setBorder(border);

		// 맨위>오른쪽
		JPanel p_north_east = new JPanel();
		p_north_east.setLayout(new GridLayout(2, 1));
		// 맨위>오른쪽>위
		JPanel p_north_east_1 = new JPanel();
		p_north_east_1.setLayout(new FlowLayout());
		p_north_east_1.setBackground(Color.WHITE);
		p_north_east_1.add(com_StockSearch);
		p_north_east_1.add(tf_StockSearch);
		// 맨위>오른쪽>아래
		JPanel p_north_east_2 = new JPanel();
		p_north_east_2.setLayout(null);
		p_north_east_2.setBackground(Color.WHITE);
		p_north_east_2.add(btn_Import);
		p_north_east_2.add(btn_Export);
		p_north_east_2.add(btn_Home);
		// 맨위>오른쪽>위아래 합체
		p_north_east.add(p_north_east_1);
		p_north_east.add(p_north_east_2);
		// 위쪽 합체
		p_north.add(p_north_west);
		p_north.add(p_north_east);
		// 아래쪽 (테이블)
		JPanel p_south = new JPanel();
		p_south.setBackground(Color.WHITE);
		p_south.setLayout(new BorderLayout());
		p_south.add(new JScrollPane(tableStock));

		// 전체 화면에 위아래 붙이기
		wholePanel= new JPanel();
		wholePanel.setLayout(new BorderLayout());
		wholePanel.add(p_north, BorderLayout.NORTH);
		wholePanel.add(p_south, BorderLayout.CENTER);
		
		//상단 데코 이미지
		top_Img = new JLabel();
		top_Img.setIcon(new ImageIcon("./src/img/maintop.png"));
		top_Img.setBorder(new LineBorder(new Color(204, 204, 204), 1));
		
		setLayout(new BorderLayout());
		add(wholePanel,BorderLayout.CENTER);
		add(top_Img,BorderLayout.NORTH);
		

	}

	void connectDB() {
		try {
			model = new StockModel();
			System.out.println("자재관리 연결성공");
		} catch (Exception e) {
			System.out.println("자재관리 연결실패");
			e.printStackTrace();
		}
	}

	void eventProc() {
		btn_Import.addMouseListener(mlstnr);
		btn_Export.addMouseListener(mlstnr);
		btn_Home.addMouseListener(mlstnr);
		tf_StockSearch.addActionListener(this);

		tableStock.addMouseListener(mlstnr);

	}
	
	MouseListener mlstnr = new MouseAdapter() {
		
		
		@Override
		public void mouseClicked(MouseEvent e) {
			Object evt = e.getSource();
			if (evt == btn_Home) {
				BurgerKing.card.first(BurgerKing.cardPanel);
				BurgerKing.f.setSize(1060, 700);
			} else if (evt == btn_Import) {
				importStock();
				searchStock();
			}else if (evt == tableStock){
				
				int row = tableStock.getSelectedRow();
				int col = 0;
				int no = (int) tableStock.getValueAt(row, col);
				Stock vo = new Stock();

				try {
					vo = model.selectByPk(no);
				} catch (Exception ex) {
					System.out.println("클릭 이벤트 실패 : " + ex.getMessage());
				}

				tf_StockNo.setText(String.valueOf(no));
				tf_StockName.setText(vo.getStockName());
				tf_ValidPeriod.setText(String.valueOf(vo.getValidPeriod()));
				tf_EnteringDate.setText(vo.getEnteringDate());
				tf_Amount.setText(String.valueOf(vo.getAmount()));
				
			}
			
		}
	};

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if (evt == tf_StockSearch) {
			searchStock();
		}
	}

	void importStock() {
		Stock vo = new Stock();
		vo.setStockName(tf_StockName.getText());
		vo.setValidPeriod(Integer.parseInt(tf_ValidPeriod.getText()));
		vo.setAmount(Integer.parseInt(tf_Amount.getText()));

		try {
			model.importStock(vo);
		} catch (Exception e) {
			System.out.println("자재 입고 실패 : " + e.getMessage());
		}
		JOptionPane.showMessageDialog(null, "자재 입고 성공");
	}

	void searchStock() {
		int idx = com_StockSearch.getSelectedIndex();
		String str = tf_StockSearch.getText();

		try {
			ArrayList data = model.searchStock(idx, str);
			tb_ModelStock.data = data;
			tableStock.setModel(tb_ModelStock);
			tb_ModelStock.fireTableDataChanged();
		} catch (Exception e) {
			System.out.println("검색 실패 : " + e.getMessage());
		}
	}
	
	public ImageIcon getIcon(String name, int width, int height) {
		return new ImageIcon(new ImageIcon
					("src\\img\\" + name + ".png").getImage().getScaledInstance(width, height,
				Image.SCALE_DEFAULT));
	}
}

// 화면에 테이블 붙이는 메소드
class StockTableModel extends AbstractTableModel {

	ArrayList data = new ArrayList();
	String[] columnNames = { "제품코드", "제품명", "유통기한", "입고일", "수량" };

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
