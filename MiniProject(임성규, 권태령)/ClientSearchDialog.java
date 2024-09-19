package pack_current;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pack_client.ClientDAO;
import pack_client.ClientTO;
import pack_counselor.CounselorDAO;
import pack_login.JoinUpDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;

public class ClientSearchDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameTextField;
	private JTable table;

	// 메인메서드는 주석 처리 후, MiniUI에서 구현.
//	public static void main(String[] args) {
//		try {
//			ClientSearchDialog dialog = new ClientSearchDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
////

	public ClientSearchDialog() {
		// 사이즈 조정을 위해 메인으로 옮김.
		setBounds(100, 100, 895, 613);
		

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel titleLabel = new JLabel("이용자 조회");
			titleLabel.setForeground(Color.BLACK);
			titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
			titleLabel.setBounds(366, 36, 191, 93);
			contentPanel.add(titleLabel);
		}
		{
			JButton createButton = new JButton("확인");
			createButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			createButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
					
					}
				}
			);
			createButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
			createButton.setBounds(404, 447, 98, 50);
			contentPanel.add(createButton);
			createButton.setActionCommand("OK");
			getRootPane().setDefaultButton(createButton);
		}
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(353, 166, 182, 32);
		contentPanel.add(nameTextField);
		
		JButton cancelButton = new JButton("검색");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			// 조회 메서드 입력해야 함.
			String searchName = nameTextField.getText();
			ClientDAO dao = new ClientDAO();
			ArrayList<ClientTO> clients = dao.searchClient(searchName);
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.setRowCount(0);
			for (ClientTO clientTO : clients) {
				model.addRow(new Object[]{
						clientTO.getCt_id(),
						clientTO.getCt_name(),
						clientTO.getCt_birth(),
						clientTO.getCt_gender(),
						clientTO.getCt_classification()
		            });
			}	
				
			}
		});
		cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		cancelButton.setActionCommand("Cancel");
		cancelButton.setBounds(558, 160, 74, 37);
		contentPanel.add(cancelButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(142, 219, 632, 200);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		table.setRowHeight(30); // 	테이블 간 높이를 조절하는 메서드
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"\uC5F0\uBC88", "\uC774\uB984", "\uC0DD\uB144\uC6D4\uC77C", "\uC131\uBCC4", "\uB300\uC0C1\uAD6C\uBD84"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(41);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(98);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(91);
	}
}
