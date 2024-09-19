package pack_login;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pack_counselor.CounselorDAO;

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

public class JoinUpDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField idTextField;
	private JPasswordField passwordTextField;
	private JTextField nameTextField;

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

	public JoinUpDialog() {
		// 사이즈 조정을 위해 메인으로 옮김.
		setBounds(100, 100, 895, 613);
		

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel titleLabel = new JLabel("상담자 회원가입");
			titleLabel.setForeground(Color.BLACK);
			titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
			titleLabel.setBounds(320, 53, 266, 93);
			contentPanel.add(titleLabel);
		}
		{
			JButton joinUPButton = new JButton("회원가입");
			joinUPButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					CounselorDAO dao = new CounselorDAO();
					try {
						String password = passwordTextField.getText();
						dao.createCounselor(idTextField.getText(), nameTextField.getText(), password);
						JOptionPane.showMessageDialog( JoinUpDialog.this, nameTextField.getText() + "님 안녕하세요. 회원가입이 완료되었습니다.");
//						panel_CreateUser.setVisible( false ); 패널 전환? 
//						panel_Initial.setVisible( true ); 패널 전환? 코드 넣으면 될 것 같음.
						dispose();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog( JoinUpDialog.this, "회원가입이 불가합니다. ID중복을 확인하거나 비밀번호를 4자 이상 입력해주세요.");
					}
				}
			});
			joinUPButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
			joinUPButton.setBounds(290, 446, 113, 50);
			contentPanel.add(joinUPButton);
			joinUPButton.setActionCommand("OK");
			getRootPane().setDefaultButton(joinUPButton);
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
		
		idTextField = new JTextField();
		idTextField.setBounds(362, 234, 182, 21);
		contentPanel.add(idTextField);
		idTextField.setColumns(10);
		
		JLabel IdLabel = new JLabel("아이디");
		IdLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		IdLabel.setBounds(290, 234, 57, 15);
		contentPanel.add(IdLabel);
		
		JLabel passwordLabel = new JLabel("비밀번호");
		passwordLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		passwordLabel.setBounds(281, 285, 60, 15);
		contentPanel.add(passwordLabel);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(362, 285, 182, 21);
		contentPanel.add(passwordTextField);
		
		JLabel nameLabel2 = new JLabel("이름");
		nameLabel2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		nameLabel2.setBounds(302, 183, 38, 15);
		contentPanel.add(nameLabel2);
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(362, 183, 182, 21);
		contentPanel.add(nameTextField);
		
		JButton checkBtn = new JButton("중복 확인");
		checkBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CounselorDAO dao = new CounselorDAO();
				try {
					if (dao.checkId(idTextField.getText())) {
						JOptionPane.showMessageDialog( JoinUpDialog.this, "ID가 중복됩니다. 다른 ID를 사용해주세요.");
						idTextField.setText( "" );
					} else {
						JOptionPane.showMessageDialog( JoinUpDialog.this, "사용가능한 ID입니다.");
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
//		checkBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		checkBtn.setBounds(579, 233, 97, 23);
		contentPanel.add(checkBtn);
	}
}
