package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import PosSystem.ItemDAO;
import PosSystem.ItemDTO;

public class POS_pos extends JPanel implements ActionListener{
	private JLabel labelItemName;
	private JLabel labelItemStock;
	private JComboBox comboBoxItem;
	private JTextField jTextFieldStock;
	private JButton buttonItemAdd;
	private JButton buttonPay;
	private JButton buttonCancel;
	private JButton buttonRefresh;
	
	private JTable jTableBasket;
	private JLabel labelTotal;
	
	private JTextField jTextFieldTotal;
	
	public POS_pos() throws SQLException {
		ItemDAO itemdao = ItemDAO.getInstance(); // 싱글톤 객체를 생성
		// 콤보 박스 세팅하기
		setLayout(null);
		
		// 상품 라벨
		labelItemName = new JLabel("상품");
		labelItemName.setSize(100,30);
		labelItemName.setLocation(20, 90);
		
		add(labelItemName);
		
		// 상품 콤보 박스
		DefaultComboBoxModel combomodel = combo_model_update();
		
		comboBoxItem = new JComboBox(combomodel);
		comboBoxItem.setSize(200, 30);
		comboBoxItem.setLocation(70, 90);
		add(comboBoxItem);
		
		// 상품 수량
		labelItemStock = new JLabel("수량");
		labelItemStock.setBounds(10, 140, 10,30);
		
		add(labelItemStock);
		
		// 상품 구매 수량 텍스트 필드
		jTextFieldStock = new JTextField();
		jTextFieldStock.setBounds(70, 140 ,200, 30);
		add(jTextFieldStock);
		
		// 상품 총가격 레이블
		labelTotal = new JLabel("총가격");
		labelTotal.setBounds(20, 250, 100, 40);
		add(labelTotal);
		
		// 상품 총가격 텍스트 필드
		jTextFieldTotal = new JTextField();
		jTextFieldTotal.setBounds(70, 250, 200, 40);
		jTextFieldTotal.setEditable(false);
		add(jTextFieldTotal);
		
		//상품목록 새로고침
		buttonRefresh = new JButton("제품 불러오기");
		buttonRefresh.setBounds(20, 20, 140, 40);
		add(buttonRefresh);
		
		// 장바구니 추가 버튼
		buttonItemAdd = new JButton("추가");
		buttonItemAdd.setBounds(170, 190, 100, 40);
		add(buttonItemAdd);
		
		// 장바구니 추가 버튼
		buttonPay = new JButton("결제");
		buttonPay.setBounds(300, 250, 100, 40);
		add(buttonPay);
		
		// 장바구니 추가 버튼
		buttonCancel = new JButton("취소");
		buttonCancel.setBounds(410, 250, 100, 40);
		add(buttonCancel);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("상품명");
		model.addColumn("구매개수");
		model.addColumn("물품가격");
		jTableBasket = new JTable(model);
		jTableBasket.setBounds(300, 20 , 210, 210);
		jTableBasket.setEnabled(false);
		add(jTableBasket);
	}
	
	// tabel 엡더이트
	private DefaultComboBoxModel combo_model_update() throws SQLException {
		DefaultComboBoxModel combomodel = new DefaultComboBoxModel(ItemDAO.getInstance().getItem());
		return combomodel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
