package pack_current;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.CompoundBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import pack_login.LoginDialog;

import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class CurrentMainUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldName;
	private JTable table;
	private CurrentMainDao dao = new CurrentMainDao(); // dao 필드 초기화
	private JLabel counselorNameLbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CurrentMainUI frame = new CurrentMainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public CurrentMainUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel l1 = new JLabel("상담 현황");
		l1.setFont(new Font("굴림", Font.PLAIN, 30));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(446, 66, 146, 57);
		contentPane.add(l1);
		
		JLabel l2 = new JLabel("상담 검색");
		l2.setFont(new Font("굴림", Font.PLAIN, 14));
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setBounds(87, 187, 99, 23);
		contentPane.add(l2);
		
		JComboBox<Integer> yearComboBox = new JComboBox<>();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		yearComboBox.addItem(null); // 초기값으로 null 
		for (int year = currentYear - 24; year <= currentYear; year++) {
		    yearComboBox.addItem(year);
		}
		yearComboBox.setBounds(198, 187, 82, 23);
		contentPane.add(yearComboBox);
		
		JComboBox<Integer> monthComboBox = new JComboBox<>();
		monthComboBox.addItem(null); // 초기값으로 null
		for (int month = 1; month <= 12; month++) {
		    monthComboBox.addItem(month);
		}
		monthComboBox.setBounds(324, 187, 82, 23);
		contentPane.add(monthComboBox);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(98, 284, 908, 210);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		JTableHeader header = table.getTableHeader();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.setRowHeight(30);
		scrollPane_1.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"\uC5F0\uBC88", "\uC0C1\uB2F4 \uC77C\uC790", "\uC0C1\uB2F4 \uC720\uD615", "\uC0C1\uB2F4 \uBC29\uBC95", "\uC0C1\uB2F4\uC0AC", "\uB300\uC0C1 \uAD6C\uBD84", "\uC774\uC6A9\uC790", "\uC0C1\uB2F4 \uB0B4\uC6A9"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(0).setMinWidth(40);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(63);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(62);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(53);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(63);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(6).setPreferredWidth(53);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(7).setPreferredWidth(209);
		
		JButton btn2 = new JButton("이용자 신규등록");
		btn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ClientCreateDialog dialog = new ClientCreateDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btn2.setBounds(98, 547, 150, 40);
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("이용자 조회");
		btn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ClientSearchDialog dialog = new ClientSearchDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				
			}
		});
		btn3.setBounds(341, 547, 150, 40);
		contentPane.add(btn3);
		
		JButton btn4 = new JButton("상담 등록");
		btn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				CR_InsertDialog dialog = new CR_InsertDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				
				
			}
		});
		btn4.setBounds(607, 547, 150, 40);
		contentPane.add(btn4);
		
		JButton btn5 = new JButton("상담 수정, 삭제");
		btn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow(); // 선택한 행의 인덱스 가져오기
				if (selectedRow != -1) { // -> 선택한 행이 있는 경우 : 정상 작동 : 선택된 데이터 가져오기
					
					int crId = (int) table.getValueAt(selectedRow, 0);
					Date crDate = (Date)table.getValueAt(selectedRow, 1);
					String crType = (String)table.getValueAt(selectedRow, 2);
					String crMethod = (String)table.getValueAt(selectedRow, 3);
					String ctClassification = (String)table.getValueAt(selectedRow, 5);
					String ctName = (String)table.getValueAt(selectedRow, 6);
					String crNote = (String)table.getValueAt(selectedRow, 7);
					
					
					// 팝업 창 열기
					CR_UpdateDelteDialog dialog = new CR_UpdateDelteDialog(crId, (Date) crDate, (String) crType, (String) crMethod, (String) ctClassification, (String) ctName, (String) crNote);
	 				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} else {
					// 선택된 행이 없는 경우, 비정상 작동 : 메시지 출력
					 JOptionPane.showMessageDialog(CurrentMainUI.this, "수정 및 삭제할 이용자데이터가 선택되지 않았습니다.", "알림", JOptionPane.WARNING_MESSAGE);
				}
				
				
			} 
		});
		btn5.setBounds(856, 547, 150, 40);
		contentPane.add(btn5);
		
		JLabel lbYear = new JLabel("년");
		lbYear.setBounds(292, 187, 31, 23);
		contentPane.add(lbYear);
		
		JLabel lbMonth = new JLabel("월");
		lbMonth.setBounds(418, 191, 20, 15);
		contentPane.add(lbMonth);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(555, 187, 265, 23);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lbName = new JLabel("이용자 이름 :");
		lbName.setBounds(471, 191, 72, 15);
		contentPane.add(lbName);
		
		JButton btn1 = new JButton("검색");
		btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				
				Integer selectedYear = yearComboBox.getSelectedItem() != null ? (int)yearComboBox.getSelectedItem() : 0;
				Integer selectedMonth = monthComboBox.getSelectedItem() != null ? (int)monthComboBox.getSelectedItem() : 0;				
				String searchName = textFieldName.getText();
				
				// DB 검색 로직
				List<Object[]> data = new ArrayList<>();
				
				// value값에 대한 검색 조건 체크
				if (selectedYear != null || selectedMonth != null || !searchName.isEmpty() ) {
					// DB에서 검색 조건(년, 월, 이름)에 맞는 데이터 가져오기
					
					data = dao.getDataFromDB(selectedYear, selectedMonth, searchName);
				} else {
					// 모든 검색 조건이 비어있는 경우, 전체 데이터 가져오기
					data = dao.getAllDataFromDB();
				}
				
				// 테이블에 데이터 추가
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0); // 기존 데이터 초기화
				if(data != null) {
					for(Object[] row : data) {
						model.addRow(row);
					}
				}
			}

			private List<Object[]> getAllDataFromDB() {
				return null;
			}

			private List<Object[]> getDataFromDB(int selectedYear, int selectedMonth, String searchName) {
				return null;
			}
		});
		btn1.setBounds(832, 182, 174, 34);
		contentPane.add(btn1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\uB85C\uADF8\uC778\uD55C \uACC4\uC815", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(851, 23, 184, 90);
		contentPane.add(panel);
		panel.setLayout(null);
		LoginDialog loginDialog = new LoginDialog();
		CurrentMainDao dao = new CurrentMainDao();
		
		counselorNameLbl = new JLabel(dao.counselorChange(loginDialog.loginID));
		
//		아래 코드는 아이디 화면에 보여주는거
//		counselorNameLbl = new JLabel(loginDialog.loginID);
		
		// 로그인 한 계정 아이디 불러오기.
		counselorNameLbl.setBounds(13, 36, 65, 15);
		panel.add(counselorNameLbl);
		counselorNameLbl.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		
		JLabel lbl = new JLabel("상담사");
		lbl.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lbl.setBounds(90, 36, 65, 15);
		panel.add(lbl);
	}
	// 로그인 한 데이터 가져오는 메서드 
	public void setLoginID(String loginId) {
		 counselorNameLbl.setText(loginId);
	}
	
	
}
