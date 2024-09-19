package pack_current;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pack_client.ClientDAO;
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
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ClientCreateDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField dateTextField;
	private JTextField nameTextField;
	private JComboBox genderComboBox;
	private JComboBox classificationComboBox;

	// 메인메서드는 주석 처리 후, MiniUI에서 구현.
//	public static void main(String[] args) {
//		try {
//			JoinUpDialog dialog = new JoinUpDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
////

	public ClientCreateDialog() {
		// 사이즈 조정을 위해 메인으로 옮김.
		setBounds(100, 100, 895, 613);
		

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel titleLabel = new JLabel("이용자 신규 등록");
			titleLabel.setForeground(Color.BLACK);
			titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
			titleLabel.setBounds(320, 53, 266, 93);
			contentPanel.add(titleLabel);
		}
		{
			JButton createButton = new JButton("등록");
			createButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ClientDAO dao = new ClientDAO();
					try {
						
						String gender = (String) genderComboBox.getSelectedItem();
	                    String classification = (String) classificationComboBox.getSelectedItem();
						
						dao.createClient(nameTextField.getText(), dateTextField.getText(), gender, classification);
						JOptionPane.showMessageDialog( ClientCreateDialog.this, "이용자 등록이 완료되었습니다.");
						dispose();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog( ClientCreateDialog.this, "이용자 등록이 불가합니다. 생년월일을 YYYY-MM-DD형식으로 입력해주세요.");
					}
					
					}
				}
			);
			createButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
			createButton.setBounds(320, 446, 98, 50);
			contentPanel.add(createButton);
			createButton.setActionCommand("OK");
			getRootPane().setDefaultButton(createButton);
		}
		{
			JButton cancelButton = new JButton("취소");
			cancelButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
					// 창 끄기
				}
			});
			cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
			cancelButton.setBounds(477, 446, 98, 50);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		dateTextField = new JTextField();
		dateTextField.setBounds(362, 234, 182, 21);
		contentPanel.add(dateTextField);
		dateTextField.setColumns(10);
		
		JLabel dateLabel = new JLabel("생년월일");
		dateLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		dateLabel.setBounds(285, 234, 77, 15);
		contentPanel.add(dateLabel);
		
		JLabel genderLabel = new JLabel("성별");
		genderLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		genderLabel.setBounds(309, 282, 38, 15);
		contentPanel.add(genderLabel);
		
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		nameLabel.setBounds(309, 183, 38, 15);
		contentPanel.add(nameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(362, 183, 182, 21);
		contentPanel.add(nameTextField);
		
		JLabel classificationLabel = new JLabel("대상구분");
		classificationLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		classificationLabel.setBounds(287, 326, 60, 15);
		contentPanel.add(classificationLabel);
		
		genderComboBox = new JComboBox();
		genderComboBox.setModel(new DefaultComboBoxModel(new String[] {"남성", "여성"}));
		genderComboBox.setBounds(362, 281, 182, 23);
		contentPanel.add(genderComboBox);
		
		classificationComboBox = new JComboBox();
		classificationComboBox.setModel(new DefaultComboBoxModel(new String[] {"일반 대상", "사례 대상", "결연후원 대상"}));
		classificationComboBox.setBounds(362, 325, 182, 23);
		contentPanel.add(classificationComboBox);
	}
}
